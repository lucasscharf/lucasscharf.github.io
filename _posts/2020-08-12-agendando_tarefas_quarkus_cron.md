---
title: Agendando tarefas repetitivas em Java com Quarkus
categories: [Java, Quarkus]
tags: [Java, Quarkus]
---

O Quarkus possui duas formas diferentes de agendar atividades, no post de hoje, vamos aprender uma forma mais simples que é para as atividades que ocorrem repetidamente em um ambiente não paralelizado. 

#Introdução

Não faltam exemplos sobre diferentes aplicações que utilizam atividades repetidas. Seja algum aplicativo que verifica se chegou um e-mail ou mesmo aquela ̶g̶a̶m̶b̶i̶a̶r̶r̶a̶ solução alternativa que precisa reiniciar a aplicação de tempos em tempos por algum vazamento de memória desconhecido (atire a primeira pedra que nunca fez isso).

Não importa o motivo, em algum momento, você acabará encontrando situações onde uma determinada ação precisa ser repetida de tempos em tempos.

#O que faremos
No exemplo de hoje, vamos fazer um pequeno servidor que escreve sons de golpes como **soc**, **tum**, **pow** de tempos em tempos.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/ynk5gemtkv4dlwm21nr5.jpeg)

A tabela abaixo mostra/explica qual será a regra utilizada para que cada golpe apareça. 

|Nome do golpe|Frequência|Delay|
|---|---|---|
|Soc|A cada 11 segundos|0 segundos|
|Tum|A cada segundo múltiplo de 17|0 segundos|
|Pow|A cada segundo múltiple de 23|0 segundos|
|Kabam|A cada 29 segundos|0 segundos
|kataplow|A cada 3 segundos|1 minuto|


#Hora da ação

Para criar um novo projeto Quarkus já com a extensão que controla as tarefas repetitivas basta usar o seguinte código.

```bash
mvn io.quarkus:quarkus-maven-plugin:1.6.0.Final:create \
    -DprojectGroupId=org.acme \
    -DprojectArtifactId=scheduler-soc-tum-pow \
    -DclassName="org.acme.schedule.SocTumPowGenerator" \
    -Dextensions="scheduler"
```

Isso vai criar uma pasta chamada scheduler-soc-tum-pow que terá todo o nosso código e já virá com a extensão **scheduler** instalada.

Depois disso, basta deletar os testes já gerados.

![Conselho do he-man](https://dev-to-uploads.s3.amazonaws.com/i/vi5bywq6l4awlarqj9ju.jpg)

Com isso, vamos abrir o arquivo SocTumPowGenerator.java e editar para ficar com o seguinte código.


```java
package org.acme.schedule;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;

import java.util.concurrent.TimeUnit;

@ApplicationScoped  // (1)
public class SocTumPowGenerator {

  private static final Logger log 
      = LoggerFactory.getLogger(SocTumPowGenerator.class); // (2)

  @Scheduled(every = "11s") // (3)
  void soc() {
    log.info("soc");
  }

  @Scheduled(cron = "0/17 * * * * ?") // (4)
  void tum() {
    log.info("tum");
  }

  @Scheduled(cron = "{cron.expr}") // (5)
  void pow() {
    log.info("pow");
  }

  @Scheduled(every = "{every.expr}") // (6)
  void kabam() {
    log.info("kabam");
  }

  @Scheduled(delay = 1,  // (7)
            delayUnit = TimeUnit.MINUTES, // (8) 
            every = "3s") // (9)
  void kataplow() {
    log.info("kataplow");
  }
}
```

e no arquivo application.properties, colocar as seguintes propriedades.

```properties
quarkus.log.console.format=%d{HH:mm:ss}: %s%e%n # (10)
cron.expr=0/23 * * * * ? # (11)
every.expr=29s # (12)
```

Pequena nota: d̶ó̶ no arquivo **application.properties** é necessário retirar o jogo da velha e os parênteses, se não ocorrerá erros.

Agora vamos destrinchar o que cada uma das linhas faz.

1. Todo código que executa um cron, precisa ser um bean gerenciável. Adicionar a anotação **ApplicationScoped** é o melhor jeito de fazer isso. Além disso, essa anotação garante que será criado apenas um único agendador de tarefas;
2. Usei uma biblioteca de log para facilitar a escrita, pois ela mostrará o horário que o método foi executado;
3. A anotação **Scheduled** informa que o método será repetido de tempos em tempos. O atributo **every** informa de quanto em quanto tempo esse método será chamado;
4. O atributo **cron** permite que o controle de tempos seja feito baseado no padrão usado pelo [crontab](https://pt.wikipedia.org/wiki/Crontab);
5. O Quarkus possui uma integração muito legal com a parte de configuração. Usando essas chaves, ele vai buscar as informações no arquivo application.properties;
6. O mesmo vale para expressões do tipo every;
7. Instruções repetidas do tipo **every** começam a rodar assim que possível. Dessa forma, mesmo que você tenha algo do tipo **every="40m"**, a primeira execução ocorrerá logo após o servidor inicializar e a execução seguinte ocorrerá 40 minutos depois da primeira execução. É possível atrasar a primeira chamada do método através do atributo **delay**;
8. O atributo **delay** informa o tempo que a execução vai atrasar. O atributo **delayUnit** informa a unidade que de tempo que vai atrasar;
9. De forma semelhante ao item 3, aqui informamos que a operação ocorrerá a cada 3 segundos;
10. Configuração de log para imprimir apenas o horário e o texto do log;
11. Expressão **cron** utilizada para o item 5 e inserida como configuração;
12. Expressão **delay** utilizada para o item 6 e inserida como configuração.

Executando o código com essas configurações, a saída mais ou menos como essa:

```java
mvn quarkus:dev
executing mvnw instead of mvn
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------------< org.acme:code-with-quarkus >---------------------
[INFO] Building code-with-quarkus 1.0.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- quarkus-maven-plugin:1.6.0.Final:dev (default-cli) @ code-with-quarkus ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /home/aleatorio/VisualCodeProjects/blog-posts-code/scheduled-task/target/classes
Listening for transport dt_socket at address: 5005
__  ____  __  _____   ___  __ ____  ______ 
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
22:37:00: code-with-quarkus 1.0.0-SNAPSHOT on JVM (powered by Quarkus 1.6.0.Final) started in 0.772s. 
22:37:00: Profile dev activated. Live Coding activated.
22:37:00: Installed features: [cdi, scheduler]
22:37:01: kabam
22:37:01: soc
22:37:12: soc
22:37:17: tum
22:37:23: pow
22:37:23: soc
22:37:30: kabam
22:37:34: soc
22:37:34: tum
22:37:45: soc
22:37:46: pow
22:37:51: tum
22:37:56: soc
22:37:59: kabam
22:38:00: kataplow
22:38:00: pow
22:38:00: tum
22:38:03: kataplow
22:38:06: kataplow
22:38:07: soc
22:38:09: kataplow
22:38:12: kataplow
22:38:15: kataplow
22:38:17: tum
22:38:18: kataplow
22:38:18: soc
22:38:21: kataplow
22:38:23: pow
22:38:24: kataplow
```

#Considerações

Para tarefas simples e repetidas esse processo é mesmo uma mão na roda. Simplifica tudo. Prefiro trabalhar com os parâmetros definidos no arquivo de configuração, pois posso alterar tudo conforme a necessidade do meu teste.

Ainda estou me acostumando com essa questão de escrever artigos, espero que consiga ajudar mais e mais pessoas com isso. 

Ah, e o código de hoje pode ser encontrado no [github](https://github.com/lucasscharf/blog-posts-code/tree/master/scheduler-soc-tum-pow).