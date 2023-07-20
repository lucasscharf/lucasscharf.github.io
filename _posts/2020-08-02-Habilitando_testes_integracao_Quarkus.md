---
title: Habilitando os testes de integração para rodar em situações específicas com JUnit 5
categories: [Java, Quarkus]
tags: [Java, Quarkus, jUnit, Teste]
---

Que o [JUnit](https://junit.org/junit5/) é um framework ótimo para testes todo mundo já sabe. Por anos ele tem sido utilizado como ferramenta para testes unitários e testes de integração. Porém, muitas pessoas deixam de utilizar algumas das suas ferramentas por não conhecê-las. Nesse artigo vamos ver como fazer para habilitar/desabilitar testes. Isso é muito útil quando temos testes de integração que costumam ser testes bem mais lentos. 

Antes de seguir, definiremos os conceitos de testes utilizados **nesse** artigo. 

## Teste unitário

É a forma de se testar unidades individuais de código fonte. A unidade individual de uma classe são seus métodos. As dependências da classe tendem ser sobreescritas com [*mocks*](https://site.mockito.org/) afim de isolar corretamente o que queremos testar.

## Teste de integração
Testes de integração testam diferentes unidades do sistema em conjunto. Nesses testes, mockear certas dependências (como a conexão com o banco de dados ou mensageria) acaba sendo algo ruim, pois são muitas dependências e a tarefa deixa de ser trivial.
Dessa forma, os testes de integração se dividiram em dois tipos de testes: 

* Teste com dependências em memória: as dependências (mensageria, cache externo, banco de dados, etc...) são levantadas em memória para os testes. O JUnit dá suporte a essa funcionalidade através das [_extensions_](https://www.baeldung.com/junit-5-extensions).

* Teste com dependências verdadeiras: as dependências são levantadas e os testes roda contra essas implementações verdadeiras. 

Aplicações que seguem os [doze-fatores](https://12factor.net/pt_br/) evitam fazer testes com dependências em memória pois isso cria um ambiente diferente entre o ambiente de testes e produção. 

Isso é algo ruim como o descrito no [item X](https://12factor.net/pt_br/dev-prod-parity).

#Problema
Isso nos traz um problema, durante o desenvolvimento, nem sempre queremos ou podemos rodar todos os testes integrados. Seja por não estarmos com as dependências prontas, por ser algo demorado ou pelo fato de nosso ambiente não ser robusto o suficiente.

#Solução

![Alt problemas modermos e soluções modernas](https://dev-to-uploads.s3.amazonaws.com/i/jkz3w3tksxyuqwcdltgd.jpg)

## Desabilitando os testes de integração

O JUnit 5 dá suporte para habilitarmos/desabilitarmos testes em diferentes situações através dos testes condicionais e variáveis de ambiente, como explicado aqui no [Bealdung](https://www.baeldung.com/junit-5-conditional-test-execution). 

Porém, esse artigo, nós usaremos uma outra abordagem. Nossa solução será baseada em [Tags](https://junit.org/junit5/docs/current/user-guide/#writing-tests-tagging-and-filtering). Tag é uma ferramenta usada para marcar os testes. Isso nos permite incluir ou excluir testes da execução.

Agora, vamos para a nossa classe de teste já anotada com as tags.

```java

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

@QuarkusTest
public class ExemploTest {
  @Test
  public void SemTag() {
    System.out.println("Rodando teste sem tag...");
  }

  @Tag("integracao")
  @Test
  public void testeDeIntegracao() {
    System.out.println("Rodando teste com tag integração...");
  }

  @Test
  @Tag("outra")
  public void outraTag() {
    System.out.println("Rodando teste com tag outra...");
  }
}
```

O código é bem simples. Importamos as bibliotecas de teste e de tag. Depois criamos 3 testes: um sem tag, um com a tag "outra" e o terceiro com a tag "integracao".

Usando o comando **mvn test** temos o seguinte retorno:

```java
[INFO] Running org.acme.ExemploTest
2020-07-08 19:30:23,622 INFO  [io.quarkus] (main) Quarkus 1.6.0.Final on JVM started in 1.481s. Listening on: http://0.0.0.0:8081
2020-07-08 19:30:23,623 INFO  [io.quarkus] (main) Profile test activated. 
2020-07-08 19:30:23,623 INFO  [io.quarkus] (main) Installed features: [cdi, resteasy]
Rodando teste sem tag...
Rodando teste com tag outra...
Rodando teste com tag integração...
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.205 s - in org.acme.ExemploTest
```
Isso mostra que todos os três testes rodaram normalmente. 

Agora, vamos impedir testes de integração de rodarem.

precisar fazer uma edição no nosso pom.xml. Lá na parte de plugins, vamos adicionar a configuração **excludedGroups** para o plugin do [surefire](https://maven.apache.org/surefire/maven-surefire-plugin/).

```xml
<plugin>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>${surefire-plugin.version}</version>
  <configuration>
    <excludedGroups>integracao</excludedGroups>
  </configuration>
</plugin>
```

Se você baixou o código direto do [gerador de código do Quarkus](http://code.quarkus.io/), então esse plugin já está presente e com algumas configurações. É só adicionar a linha do **excludedGroups** que tudo funcionará corretamente. 

Agora, vamos rodar de novo o teste, para ver o resultado.

```java
[INFO] Running org.acme.ExemploTest
2020-07-08 19:29:56,324 INFO  [io.quarkus] (main) Quarkus 1.6.0.Final on JVM started in 1.376s. Listening on: http://0.0.0.0:8081
2020-07-08 19:29:56,326 INFO  [io.quarkus] (main) Profile test activated. 
2020-07-08 19:29:56,326 INFO  [io.quarkus] (main) Installed features: [cdi, resteasy]
Rodando teste sem tag...
Rodando teste com tag outra...
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.982 s - in org.acme.ExemploTest
```

Como vimos, apenas os testes outraTag e testeSemTag rodaram. O teste de integração ficou sem rodar e nosso problema já está resolvido =D

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/6zj6os0rtke6iluvnddl.jpeg)

Ou não. 

Com essa soluação, os testes de integração não irão rodar. Nunca. Never. Nai. Nein. No. Non. (acho que já deu para entender).

O que fazer caso a gente queira rodar o teste de integração?

## Habilitando testes de integração

Para habilitar os testes de integração e ele rodar apenas quando quisermos, nós precisamos fazer mais uma alteração no nosso pom.xml. 

Essa alteração irá adicionar um novo perfil de execução, que irá sobreescrever as regras que a gente alterou lá em cima. 
Para isso, basta ir na tag profiles (ou criá-la caso ela não exista) e adicionar a tag com esse profile.

```xml
 <profile>
  <id>integration-test</id>
  <activation>
    <property>
      <name>integration-test</name>
    </property>
  </activation>
  <build>
    <plugins>
      <plugin>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>${surefire-plugin.version}</version>
        <configuration>
          <!-- Remova a próxima linha se você quer que todos os testes rodem em conjunto com o teste de integração -->
          <groups>integracao</groups>
          <excludedGroups>none()</excludedGroups>
        </configuration>
      </plugin>
    </plugins>
  </build>
</profile>
```

Essa tag cria um novo perfil de execução. Nesse nosso novo perfil, a única tag que irá rodar é a tag **integracao**. Além disso, nós alteramos a tag **excludedGroups** para "dessexcluirmos" a nossa tag da execução.

E para rodar os testes agora, é preciso adicionar um parâmetro indicando que é para o maven utilizar o perfil integration-test. Fazendo isso, temos o seguinte resultado.

Para rodar, usamos o comando

```bash
mvn test -Pintegration-test
```

E esse comando nos dará o seguinte retorno.

```java
[INFO] Running org.acme.ExemploTest
2020-07-08 19:31:57,933 INFO  [io.quarkus] (main) Quarkus 1.6.0.Final on JVM started in 1.100s. Listening on: http://0.0.0.0:8081
2020-07-08 19:31:57,945 INFO  [io.quarkus] (main) Profile test activated. 
2020-07-08 19:31:57,945 INFO  [io.quarkus] (main) Installed features: [cdi, resteasy]
Rodando teste com tag integração...
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.64 s - in org.acme.ExemploTest
```

Com  isso o único teste executado foi o testeDeIntegracao. E agora sim, nosso problema está realmente resolvido. 

#Considerações
Quando comecei a fazer esse artigo, pretendia adotar uma abordagem baseada em [execuções condicionais](https://www.baeldung.com/junit-5-conditional-test-execution). 

Acabei caindo nas tags e gostei bastante e pretendia começar a utilizar nos meus projetos. Até que vi uma [thread](https://groups.google.com/forum/#!topic/quarkus-dev/zoXRyHFDAYI) na lista de e-mail do Quarkus falando sobre a criação de uma anotação @IntegrationTest. Agora vamos esperar para ver o que virá por aí =D

Num artigo futuro, vou falar sobre os [tests containers](https://www.testcontainers.org/) que também é uma abordagem muito boa para auxiliar nos testes de integração. E também quero falar sobre os [perfis de teste](https://quarkus.io/guides/getting-started-testing#testing-different-profiles). Posso ficar feliz porque assunto é o que não falta para falar.

Ah, e o código de hoje pode ser encontrado no [github](https://github.com/lucasscharf/blog-posts-code/tree/master/enable-tests-with-tag).