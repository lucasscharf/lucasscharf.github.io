---
title: Como usar Mockito no Quarkus (feat. jUnit)
categories: [Java, Quarkus]
tags: [Java, Quarkus, Ferramentas]
---

Nos últimos tempos fiz dois posts topzeira, um falando sobre [o que são testes](https://dev.to/lucasscharf/aprendendo-sobre-testes-em-java-com-as-princesas-da-disney-1jk5) e outro sobre como utilizar a lib do [testcontainers](https://www.testcontainers.org/) para fazer testes de integração. No artigo de hoje, vamos utilizar a biblioteca [Mockito](https://site.mockito.org/) para fazer os testes mockeados.

Esse tutorial será dividido em três partes, no primeiro vamos trabalhar com a criação de mocks e controlar o comportamento deles; no segundo vamos utilizar a biblioteca para fazer o retorno dos objetos de forma condicional; e, por fim, vamos conseguir analisar bem o objeto que estamos recebendo para ver se está tudo correto. 

Para conseguir esse feito, vamos trazer o mestre do universo, o único que fica lindo de cabelo chanel e tanguinha, aquele que dá os melhores conselhos: He-man.

![Alt Tex](/images/heman.jpg)

#O que faremos?
Nesse artigo vamos criar um o castelo de GraySkull onde as pessoas querem entrar. Esse castelo tem um porteiro que vai dizer se você entra na entrada VIP ou entrada normal.

O castelo ficará com a seguinte cara.

```java
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CasteloDeGraySkull {
  @Inject
  PortaoNormal portaoNormal;

  @Inject
  PortaoVip portaoVip;

  @Inject
  Porteiro porteiro;

  public void entrarNoCastelo(String nome) {
    System.out.println(nome + " está entrando no castelo");
    if(porteiro.identificarSeÉVip(nome)) {
      portaoVip.entrar(nome);
    } else {
      portaoNormal.entrar(nome);
    }
  }
}
```

E cada portão tem a seguinte forma:
```java
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PortaoNormal {
  public void entrar(String nome) {
    System.out.println(nome + " entrou pelo portão normal");
  }
}
```

```java
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PortaoVip {
  public void entrar(String nome) {
    System.out.println(nome + " entrou pelo portão VIP");
  }
}
```

Agora vem os problemas. Olha só o que aconteceu com o nosso porteiro.

```java

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class Porteiro {
  public boolean identificarSeÉVip(String nome) {
    throw new RuntimeException("O porteiro faltou!");
  }
}
```

Isso mesmo, nós não temos a classe do porteiro. Isso pode acontecer por vários motivos. Seja por nós não sabermos as regras ainda ou por não termos implementado. Ou mesmo porque o porteiro depende de alguma aplicação externa que nem sempre vamos poder usar nos nossos testes.

E é nesse ponto que a beleza do Quarkus e dos mocks entram. Nós não precisamos ter o objeto de verdade para seguirmos nossas implementações. Primeiro vamos adicionar a dependência para o Mockito. Fazemos isso adicionando o trecho abaixo entre as tags ``<dependencies>``do arquivo **pom.xml**.

```xml
    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-junit5</artifactId>
      <scope>test</scope>
    </dependency>
```

Agora, vamos para a nossa classe de teste que será chamada de **CasteloDeGraySkullTest**.

```
import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest //0
public class CasteloDeGraySkullTest {
  @Inject
  CasteloDeGraySkull castelo;

  @InjectMock //1
  Porteiro porteiroMock;

  @Test
  public void testeEntrarNoNormal() {
    castelo.entrarNoCastelo("He-man"); //2
  }

  @Test
  public void testeEntrarNoVip() {
    Mockito.when(porteiroMock.identificarSeÉVip(Mockito.anyString())).thenReturn(true); //3
    castelo.entrarNoCastelo("She-ha");//4
  }
}
```

Agora vamos analisar o nosso código.
* 0. A anotação @QuarkusTest indica que os testes  usarão a injeção de dependência do Quarkus.
* 1. Quando usamos a anotação ``@InjectMock``, estamos dizendo que não é para injetarmos a classe verdadeira, porém uma versão mockada dela.
* 2. Estamos fazendo o teste tendo o comportamento padrão do tipo **boolean** que é retornar **false**. Usando o comando ``mvn test``, veremos a seguinte saída:
```
He-man está entrando no castelo
He-man entrou pelo portão normal
```

* 3. Estamos mudando o comportamento do nosso mock. Estamos dizendo que para qualquer string que for passada (``Mockito.anyString()``) para o método ``porteiroMock.identificarSeÉVip()`` deve ser retornado o valor **true**.
* 4. Temos o teste com o valor alterado, rodando o teste, teremos a seguinte saída:

```
She-ha está entrando no castelo
She-ha entrou pelo portão VIP
```

#Considerações
Tentei dividir ao máximo o conteúdo para que todo mundo possa acompanhar sem maiores problemas e entender bem o que houve. 
Mockito é uma biblioteca extremamente poderosa e auxilia bastante no desenvolvimento de testes rápidos e eficientes. Espero que todos gostem.

Ah, e o código de hoje pode ser encontrado no [github](https://github.com/lucasscharf/blog-posts-code/tree/master/mockito).

E no próximo post, teremos uma participação especial:

![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/u9btf8038iv2xq9wc228.jpg)