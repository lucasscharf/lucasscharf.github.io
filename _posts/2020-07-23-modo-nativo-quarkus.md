---
title: Uma breve explicação sobre o modo nativo do Quarkus
categories: [Java, Quarkus]
tags: [Java, Quarkus, GraalVM]
---

Uma das funcionalidades mais interessantes do Quarkus é a possibilidade de rodar aplicações nativamente o que permite que o startup seja [muito rápido e o _footprint_ de memória seja bem pequeno](https://dev.to/lucasscharf/quarkus-trazendo-o-java-de-volta-para-a-briga-dos-microsservicos-184g). Nesse artigo, será feito uma revisão sobre o que é o modo nativo, como ele funciona, quais são as limitações e como compilar o código de forma nativa.

#O Que é?
O modo nativo simplesmente é o código Quarkus compilado como se fosse um arquivo executável normal (pense num código C compilado ou mesmo no seu navegador). Esse processo é feito através de uma máquina virtual especial chamada [GraalVM](https://www.graalvm.org/).

#Como Funciona?
Num processo tradicional do Java, o código é compilado em um formato chamado **bytecode**. Na hora de executar, a JVM pega esse código e transforma em um código binário nativo para o computador onde a JVM está rodando (esse processo é chamado de JIT ou _Just In Time compilation_).

Para gerar uma imagem nativa é feito um processo diferente. A GraalVM usa uma técnica chamada [compilação Ahead of time](https://pt.wikipedia.org/wiki/Compila%C3%A7%C3%A3o_AOT) (AOT compilation). Esse processo nada mais é do que compilar o código diretamente para um formato binário **sem** passar pelo bytecode.

Nesse processo, a GraalVM aplica uma série de otimizações bem agressiva. Entre essas otimizações, uma das mais se destaca é a análise do código e retirada de classes que de alguma forma não serão executadas pela aplicação final. 

Somando a retirada das classes inúteis, com a compilação do código para código nativo, o resultado final é um arquivo executável pequeno, rápido de executar e com baixo consumo de memória.

Uma explicação mais detalhada pode ser encontrada no [manual da GraalVM](https://www.graalvm.org/docs/reference-manual/native-image/).

#Problemas e limitações
Usar o modo nativo não é uma bala de prata. Existem vários pré-requisitos que o código precisa cumprir para que possa ser compilado pela GraalVM:

1. Para compilar nativamente na sua máquina, é necessário instalar ̶v̶á̶r̶i̶a̶s̶ algumas [dependências e fazer algumas configurações](https://quarkus.io/guides/building-native-image);
2. Os códigos que utilizam reflexão podem apresentar problemas, pois provavelmente as classes serão retiradas do executável final;
3. O JNI que utiliza muito de reflexão não irá funcionar;
4. A serialização Java não funcionará;
5. O Java RMI que depende da serialização também não vai funcionar;
6. Testes unitários precisam de uma anotação especial para serem testados em modo nativo;
7. Proxys dinâmicos também poderão falhar.

Além de tudo isso, o processo para compilar o código em modo nativo demora muito tempo.

A lista completa das limitações pode ser encontrado no [github da GraalVM](https://github.com/oracle/graal/blob/master/substratevm/LIMITATIONS.md).

#Como Compilar
Para compilar no modo nativo, basta ter a GraalVM instalada e compilar o projeto com o profile correto. Isso pode ser feito através do seguinte código.

```bash
./mvnw package -Pnative.
```

Porém, o processo de instalar a GraalVM pode ser um pouco confuso e conter alguns erros. Por isso, uma outra forma de compilar o código em formato nativo é utilizar um container docker que já tenha tudo corretamente configurado e construir a imagem com um docker build de múltiplos estágios. 
Felizmente já existe uma imagem docker pronta para utilizar. **Basta editar o arquivo .dockerignore para deixar de filtrar tudo que não seja o diretório target** e executar o dockerfile abaixo.

```dockerfile
## Estágio 1: compilando nativamente
# baixando uma imagem que já tenha a GraalVM instalada e configurada
FROM quay.io/quarkus/centos-quarkus-maven:19.3.1-java11 AS build
# copiando o pom.xml
COPY pom.xml /usr/src/app/ 
# baixando todas as dependências do projeto
RUN mvn -f /usr/src/app/pom.xml -B de.qaware.maven:go-offline-maven-plugin:1.2.5:resolve-dependencies
# copiando o código-fonte e alterando o dono para o usuário quarkus
COPY src /usr/src/app/src
USER root
RUN chown -R quarkus /usr/src/app
USER quarkus
# compilando o código em formato nativo
RUN mvn -f /usr/src/app/pom.xml -Pnative clean package

## Estágio 2: criando a imagem docker utilizando o código recém compilado
# Usando a imagem da redhat
FROM registry.access.redhat.com/ubi8/ubi-minimal
WORKDIR /work/
# copiando o executável do container anterior
COPY --from=build /usr/src/app/target/*-runner /work/application

# dando as permissões ao usuário `1001`
RUN chmod 775 /work /work/application \
  && chown -R 1001 /work \
  && chmod -R "g+rwX" /work \
  && chown -R 1001:root /work

# expondo a porta 8080
EXPOSE 8080
USER 1001

# executando a aplicação
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]

```

Para criar a imagem com esse dockerfile, basta salvar o arquivo acima dentro do diretório **src/main/docker/** com o nome **Dockerfile.multistage** usar o seguinte comando:

```java
docker build -f src/main/docker/Dockerfile.multistage -t imagem-nativa-compilada .
```

Isso criará uma imagem docker com o seu programa já compilado em modo nativo. 

```java
docker run -i --rm -p 8080:8080 imagem-nativa-compilada
```

#Considerações
Apesar da [surpreendente velocidade](https://dev.to/lucasscharf/quarkus-trazendo-o-java-de-volta-para-a-briga-dos-microsservicos-184g) nunca usei o modo nativo em produção. Tenho algumas dúvidas sobre como debugar, não tenho muita paciência para ficar esperando ele fazer todo o processo de compilação e ainda não passei por algum cenário onde fosse essencial essa otimização de recursos. 
