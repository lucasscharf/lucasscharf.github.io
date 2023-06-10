---
title: Como usar Kafka num projeto Quarkus
categories: [Java, Quarkus]
tags: [Java, Quarkus, Microsservicos]
---

Kafka é uma ferramenta muito boa para troca de mensagens entre diferentes aplicações, porém, ele é bem intimidador e com um de detalhes para começar a consumir as mensagens. 

E agora, quem poderá nos defender?

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/o7ytg0exznaok9zjq3yq.jpg)

#O Que faremos?
Vamos alterar a nossa [gloriosa fábrica de bolo](https://dev.to/lucasscharf/quarkus-openapi-uma-combinacao-tao-boa-que-deveria-se-chamar-bolo-de-chocolate-4eb7) que já está [persistindo os dados](https://dev.to/lucasscharf/trabalhando-com-jpa-quarkus-panache-e-bolo-de-chocolate-36k7). Após fazer a operação será enviado uma mensagem para o broker Kafka com a entidade transformada em JSON.

Depois disso, por fim de simplicidade, vamos criar um consumidor para essas mensagens no mesmo projeto. Como vamos enviar as mensagens ao broker Kafka é possível consumir essas mensagens em algum outro projeto. 

Para esse artigo é necessário saber um pouco sobre Kafka e ter o feito projeto da [fábrica de bolo com banco de dados](https://dev.to/lucasscharf/trabalhando-com-jpa-quarkus-panache-e-bolo-de-chocolate-36k7). 

#É hora da ação

Caso você não tenha o projeto da fábrica, você pode pegar lá no meu [git](https://github.com/lucasscharf/blog-posts-code/tree/master/jpa-panache). Tendo o código em mãos, vamos adicionar as dependências do Kafka e do Jackson (para mandar a mensagem em JSON). Isso é feito colocando o código abaixo na parte de dependências do arquivo *pom.xml*:

```xml
    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-smallrye-reactive-messaging-kafka</artifactId>
    </dependency>
    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-jackson</artifactId>
    </dependency>
```

## Escrevendo a mensagem
Vamos começar escrevendo a mensagem. Lá no nosso arquivo BoloResource, vamos adicionar os emitters. O código para isso é:

```java
  @Inject //1
  @Channel("add-bolo") //2
  Emitter<Bolo> addEmitter; //3
  
  @Inject
  @Channel("delete-bolo")
  Emitter<String> deleteEmitter;
```

1. ``@Inject`` é utilizado para fazer a inejção do nosso emitter
2. O ``@Channel`` vem do import ``org.eclipse.microprofile.reactive.messaging.Channel`` ele é responsável por identificar para onde vamos enviar as mensagens e configurar tudo corretamente (vamos ver mais sobre a configuração lá pra frente)
3. A classe Emitter vem do import ``org.eclipse.microprofile.reactive.messaging.Emitter`` e é ele quem envia a mensagem para o Kafka*

O Envio de mensagem é feito através do método [Emitter#send](https://smallrye.io/smallrye-reactive-messaging/2.0.1/apidocs/org/eclipse/microprofile/reactive/messaging/Emitter.html). Vamos alterar BoloResource adicionando  esse método logo antes do return. O código vai ficar assim:

```java
  @POST
  @Transactional
  public List<Bolo> add(Bolo bolo) {
    bolo.id = null; //coisa feia, não façam isso em casa
    bolo.persist();
    addEmitter.send(bolo); //1
    return list();
  }

  @DELETE
  @Path("/{nome}")
  @Transactional
  public List<Bolo> delete(String nome) {
    Bolo.delete("nome", nome);
    deleteEmitter.send(nome); //2
    return Bolo.listAll();
  }
```

1. Estamos enviando para o kafka todo o bolo
2. Estamos enviando para o kafka apenas o nome do bolo

Além disso, é necessário descrever e envio de mensagens no arquivo *application.properties*. Para tanto, basta adicionar as seguintes linhas nele. 

```properties
mp.messaging.outgoing.add-bolo.connector=smallrye-kafka
mp.messaging.outgoing.add-bolo.topic=add-bolo
mp.messaging.outgoing.add-bolo.value.serializer=io.quarkus.kafka.client.serialization.ObjectMapperSerializer

mp.messaging.outgoing.delete-bolo.connector=smallrye-kafka
mp.messaging.outgoing.delete-bolo.topic=delete-bolo
mp.messaging.outgoing.delete-bolo.value.serializer=org.apache.kafka.common.serialization.StringSerializer
```

Todas as configurações tem o mesmo formato ``mp.messaging.outgoing.<nome do canal>.<nome da propriedade>``. As propriedades que nós definimos servem para dizer que vamos nos comunicar com Kafka e que vamos serializar a mensagem mensagem como JSON usando o Jackson ou enviando uma String normal. 
[Qualquer propriedade](https://docs.confluent.io/platform/current/installation/configuration/producer-configs.html) dos produtores do kafka podem ser utilizados. 

## Lendo a mensagem
A classe abaixo pode estar tanto no mesmo projeto quanto em um outro projeto (desde que tenha as dependências corretas). 

```java
import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class BoloKafkaConsumer {
  
  @Incoming("add-bolo-consumer")
  public void consumeAdd(String bolo) {
    System.out.println("Bolo adicionado: " + bolo);
  }

  @Incoming("delete-bolo-consumer")
  public void consumeDelete(String nome) {
    System.out.println("Nome do bolo deletado: " + nome);
  }
}
```

Os dois métodos recebem o JSON do Bolo e escrevem na saída padrão. Notem que, apesar de termos enviado o bolo como objeto, estamos lendo como String. É possível configurar para receber um Bolo ou fazer qualquer transformação que seja interessante para nós. 

Assim como no envio, também temos que configurar a leitura no *application.properties*. Isso pode ser feito através do código:

```properties
mp.messaging.incoming.add-bolo-consumer.connector=smallrye-kafka
mp.messaging.incoming.add-bolo-consumer.topic=add-bolo
mp.messaging.incoming.add-bolo-consumer.value.deserializer=org.apache.kafka.common.serialization.StringDeserializer

mp.messaging.incoming.delete-bolo-consumer.connector=smallrye-kafka
mp.messaging.incoming.delete-bolo-consumer.topic=delete-bolo
mp.messaging.incoming.delete-bolo-consumer.value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
```

Semelhante ao produtor, no consumidor as propriedades tem o formato ``mp.messaging.outgoing.<nome do canal>.<nome da propriedade>`` e todas as propriedades podem ser encontradas no site da [confluent](https://docs.confluent.io/platform/current/installation/configuration/consumer-configs.html).

## Preparar para rodar

Para rodar essa aplicação, nós vamos precisar do [docker-compose](https://docs.docker.com/compose/). Para isso, basta usar o seguinte arquivo docker-compose.yml:

```yml
version: '2'
services:
  postgres:
    image: postgres:12-alpine
    ports: 
      - "5432:5432"
    environment:
      POSTGRES_USER: Sarah
      POSTGRES_PASSWORD: Connor
      POSTGRES_DB: skynet
  zookeeper:
    image: strimzi/kafka:0.19.0-kafka-2.5.0
    command: [
      "sh", "-c",
      "bin/zookeeper-server-start.sh config/zookeeper.properties"
    ]
    ports:
      - "2181:2181"
    environment:
      LOG_DIR: /tmp/logs

  kafka:
    image: strimzi/kafka:0.19.0-kafka-2.5.0
    command: [
      "sh", "-c",
      "bin/kafka-server-start.sh config/server.properties --override listeners=$${KAFKA_LISTENERS} --override advertised.listeners=$${KAFKA_ADVERTISED_LISTENERS} --override zookeeper.connect=$${KAFKA_ZOOKEEPER_CONNECT}"
    ]
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      LOG_DIR: "/tmp/logs"
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
```

Esse docker-compose.yml instância e configura um banco de dados, o kafka e o zookeeper (que é um requisito para o Kafka funcionar). 

## Hora do teste

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/71nxc1jpg0rj5vaea7r4.jpg)

Depois de levantar o docker-compose (``docker-compose up`` no mesmo diretório que está o arquivo docker-compose.yml), levantar o projeto (``mvn quarkus:dev`` no diretório base do projeto), entrar na url http://localhost:8080/swagger-ui e fazer a cadastro de um bolo.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/ig2p7d8h6nhhmipdofu4.png)

Depois de apertar o botão do execute. É só olhar no terminal e vamos ver a saída da aplicação:

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/xi7cdre96en0y8yi0nhu.png)

E é isso, agora é possível brincar enviando novos bolos, excluíndo, etc...

#Considerações
Nossa [fábrica de bolo](https://dev.to/lucasscharf/quarkus-openapi-uma-combinacao-tao-boa-que-deveria-se-chamar-bolo-de-chocolate-4eb7) que já [salva as coisas no banco de dados](https://dev.to/lucasscharf/trabalhando-com-jpa-quarkus-panache-e-bolo-de-chocolate-36k7) está evoluindo e está se comunicando via mensagens <3

Nos próximos episódios, vamos garantir a atualização de banco de dados com flyway, adicionar rastreabilidade, resiliência e mais um monte de coisa massa =D

Outra coisa muito massa é que conseguimos enviar mensagens para o MQTT, AMQP ou JMS sem mexer no código. Só ajustando o *pom.xml* e o *application.properties*. Isso é mesmo é algo supimpa. 

Ah, e o código desse post pode ser encontrado no [github](https://github.com/lucasscharf/blog-posts-code/tree/master/kafka-consumer-emitter).

*Na realidade, ele a função do emitter é colocar a mensagem dentro de um fluxo reativo do Smallrye. O Smallrye possui um conector que acaba fazendo o processo de enviar a mensagem para o kafka. Porém, é possível utilizar esses fluxos sem enviar para o kafka ou para qualquer outro broker de mensageria. 
