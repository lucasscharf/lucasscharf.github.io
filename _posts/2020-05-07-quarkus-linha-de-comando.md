---
title: Usando o Quarkus como como uma aplicação de linha de comando!
categories: [Java, Quarkus]
tags: [Java, Quarkus]
---

Apesar de ser um grande framework para desenvolvimento WEB, desenvolver pequenos programas com Quarkus era uma tarefa complicada e cheia de g̶a̶m̶b̶i̶a̶r̶r̶a̶s̶ soluções alternativas. Afinal, o Quarkus era apenas um framework para desenvolvimento WEB. 

Caso o desenvolvedor quisesse fazer um programa que comessasse a executar logo após ser invocado e finalizasse após isso, era necessário usar algumas g̶a̶m̶b̶i̶a̶r̶r̶a̶s̶ [soluções alternativas](https://quarkus.io/guides/cdi-reference).

Além disso, não existia uma forma fácil de passar parâmetros para a aplicação e nem mesmo como fazer um [pipe](https://www.vivaolinux.com.br/dica/Pipes-no-Linux) de execuções.

Isso mudou à partir da [versão 1.4](https://quarkus.io/blog/quarkus-1-4-final-released/) do Quarkus. Essa versão trouxe várias melhorias, entre elas o modo de comando (**command mode**). Nesse artigo veremos o que é esse **command mode** e um pequeno passo-à-passo de como usá-lo.

#O que é?
O modo de comando do Quarkus permite permite levantar uma aplicação Quarkus sem endpoints para REST e receber parâmetros por linha de comando. 
Isso permite programar scripts ou aplicações de linha de commando tendo todo o poder do framework [Quarkus](https://quarkus.io/).

#Como usar
Como toda aplicação Quarkus, para utilizar, basta apenas adicionar o bom no arquivo pom.xml.

```xml
<dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-universe-bom</artifactId>
        <version>1.5.2.Final</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
</dependencyManagement>
```
Também no pom.xml basta adicionar a dependência do **quarkus-arc**.

```xml
 <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-arc</artifactId>
 <dependency>
```

Já na parte do código, basta adicionar a anotação **@QuarkusMain** na classe que será o *entrypoint* da aplicação e fazer ela implementar a interface **QuarkusApplication** e *Voilá*. 
Todos os recursos do Quarkus estão disponíveis para nós usarmos. 

Nisso, é só compilar com o comando

```
mvn clean install
```

e a aplicação estará dentro do diretório **./target**. Pronto para ser chamado com um "java -jar".

#Exemplo de uso

Nesse pequeno exemplo, existem duas classes: ExamploModoComando e ServicoInjetado. 

A classe ExamploModoComando é nossa classe de *entrypoint*. O programa começará rodar à partir do seu método run. Existem outras formas de chamar esse método e podem ser encontrados na [documentação oficial](https://quarkus.io/guides/command-mode-reference). 

Já classe ServicoInjetado é um bean gerenciável. Por isso precisa da anotação @Dependent e tem o seu @PostConstruct chamado (caso você seja iniciante, não precisa pensar muito no porquê disso).
 
```java

import javax.inject.Inject;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class ExamploModoComando implements QuarkusApplication {

  @Inject
  ServicoInjetado servico;

  @Override
  public int run(String... args) throws Exception {

    if (args.length > 0) {
      servico.chamar(args[0]);
    } else {
      servico.chamar("");
    }

    return 0;
  }
}
```

```java

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

@Dependent
public class ServicoInjetado {

  @PostConstruct
  void postConstruct() {
    System.out.println("Sou um serviço injetado que funciona perfeitamente com CDI!");
  }


  void chamar(String variavel) {
    System.out.println("Fui chamado com a variável: " + variavel);
  }
}
```

Executando essa classe usando um "java -jar", temos a seguinte saída:

```java
 java -jar ./target/code-with-quarkus-1.0.0-SNAPSHOT-runner.jar Parâmetro 
__  ____  __  _____   ___  __ ____  ______ 
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2020-07-06 19:00:30,838 INFO  [io.quarkus] (main) code-with-quarkus 1.0.0-SNAPSHOT on JVM (powered by Quarkus 1.5.2.Final) started in 0.191s. 
2020-07-06 19:00:30,852 INFO  [io.quarkus] (main) Profile prod activated. 
2020-07-06 19:00:30,852 INFO  [io.quarkus] (main) Installed features: [cdi]
Sou um serviço injetado que funciona perfeitamente com CDI!
Fui chamado com a variável: Parâmetro
2020-07-06 19:00:30,855 INFO  [io.quarkus] (main) code-with-quarkus stopped in 0.003s
```

#Dicas, truques e informações

###O modo de comando já vem por padrão
Uma classe feita pelo [gerador do quarkus](http://code.quarkus.io/) tem como dependência o *quarkus-resteasy* que já traz o *quarkus-arc* como dependência transitiva.

###Como fazer uma saída mais limpa
Uma boa aplicação de linha de comando [não fala nada se não tiver nada de surpreendente a dizer](http://www.catb.org/esr/writings/taoup/html/ch01s06.html#id2878450).
Para retirar os logs colocados é só alterar o arquivo application.proprties e adicionar o seguinte texto.

```properties
quarkus.log.level=SEVERE
quarkus.hibernate-orm.log.sql=false
quarkus.banner.enabled=false
```

Isso mudará a saída do programa para algo do tipo:

```java
java -jar ./target/code-with-quarkus-1.0.0-SNAPSHOT-runner.jar Parâmetro
Sou um serviço injetado que funciona perfeitamente com CDI!
Fui chamado com a variável: Parâmetro
```

###Como passar parâmetros em modo de desenvolvimento
Normalmente, quando desenvolvemos, executamos com o comando **mvn quarkus:dev**. Para passar parâmetros, basta usar o parâmetro **-Dquarkus.args=parâmetro**.

Isso permite *debugar*, traz o *hot reload*, altera o perfil para o dev entre outras coisas. O código o código executando o comando o resultado será mais ou menos o seguinte:
```bash
mvn quarkus:dev -Dquarkus.args=Parâmetro
executing mvnw instead of mvn
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------------< org.acme:code-with-quarkus >---------------------
[INFO] Building code-with-quarkus 1.0.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- quarkus-maven-plugin:1.5.2.Final:dev (default-cli) @ code-with-quarkus ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 2 source files to /home/aleatorio/VisualCodeProjects/postagem_quarkus/quarkus-command-mode/target/classes
Listening for transport dt_socket at address: 5005
Sou um serviço injetado que funciona perfeitamente com CDI!
Fui chamado com a variável: Parâmetro
Quarkus application exited with code 0
Press Enter to restart or Ctrl + C to quit

```

#Considerações
Já usei algumas g̶a̶m̶b̶i̶a̶r̶r̶a̶s̶ soluções alternativas porque queria usar as vantagens do Quarkus em pequenos pequenos programas e utilitários para o dia-à-dia. O modo de comando veio como uma luva para mim. Ainda mais, quando posso compilar a aplicação em [modo nativo](https://dev.to/lucasscharf/uma-breve-explicacao-sobre-o-modo-nativo-do-quarkus-1kn7) para ter um programa [pequeno e rápido](https://dev.to/lucasscharf/quarkus-trazendo-o-java-de-volta-para-a-briga-dos-microsservicos-184g).

Confesso que não uso para scripts extramemnte simples, pois prefiro usar direto o bash. Mas em pequenos programas e utilitários mais complexos tenho usado bastante o Quarkus em linha de comando e tem sido uma ótima experiência.

Ah e o código de hoje pode ser encontrado em inglês no [git](https://github.com/lucasscharf/blog-posts-code/tree/master/quarkus-command-mode).