---
title: Quarkus + OpenAPI uma combinação tão boa que deveria se chamar bolo de chocolate
categories: [Java, Quarkus]
tags: [Java, Quarkus]
---

O que acontece quando juntamos um dos [melhoeres frameworks de desenvolvimento web](https://quarkus.io/) com uma [ótima ferramenta](https://swagger.io/specification/) que permite que humanos e computadores entendam a nossa aplicação? O resultado nós vamos descobrir logo. 

Não vamos nos alongar muito sobre o porque o Swagger é uma ferramenta maravilhosa e porque todo mundo deveria usá-la. Tem vários artigos bons que fala sobre o tema.

#O que faremos
Vamos criar um microsserviço que adiciona, lista e remove informações sobre bolos. Tanto o microsserviço quanto a entidade são simplificados, pois o nosso foco é no OpenAPI.
Depois disso, vamos colocar as anotações para descrever a nossa aplicação e utilizar o [Swagger-UI](https://quarkus.io/guides/openapi-swaggerui) para descrever e manipular esse serviço.

#É hora da ação
Primeiro vamos criar o nosso serviço de bolos. Para fazer isso, vamos usar o plugin maven do Quarkus:

```java
mvn io.quarkus:quarkus-maven-plugin:1.6.1.Final:create \
    -DprojectGroupId=cadastro-de-bolo \
    -DprojectArtifactId=open-api \
    -DclassName="com.BoloResource" \
    -Dpath="/bolo" \
    -Dextensions="resteasy-jsonb, smallrye-openapi, quarkus-smallrye-openapi"
```

Com isso, criamos um projeto com as extensões necessárias. 

Para evitar maiores problemas, vamos excluir os dois arquivos de teste que foram criados automaticamente.

![Conselho do he-man](https://dev-to-uploads.s3.amazonaws.com/i/vi5bywq6l4awlarqj9ju.jpg)

Agora, vamos criar a entidade bolo. Para isso, vamos criar o arquivo **Bolo.java** dentro do diretório **src/main/java/com**. Depois, vamos colocar o código abaixo nesse arquivo.

```java
package com;

public class Bolo {
  String nome;
  String descricao;

  public Bolo() {
  }

  public Bolo(String nome, String descricao) {
    this.nome = nome;
    this.descricao = descricao;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

}
```

Além do arquivo **Bolo.java** que criamos, vamos alterar o arquivo **BoloResource.java** com as regras do nosso serviço. Ele vai ficar com essa cara:

```java
package com;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/bolo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoloResource {
  private Set<Bolo> bolos;

  public BoloResource() {
    bolos = new HashSet<>();
    bolos.add(new Bolo("Chocolate", "Melhor bolo do mundo"));
    bolos.add(new Bolo("Sensação", "Chocolate com morango"));
  }

  @GET
  public Set<Bolo> list() {
    return bolos;
  }

  @POST
  public Set<Bolo> add(Bolo bolo) {
    bolos.add(bolo);
    return bolos;
  }

  @DELETE
  @Path("/{nome}")
  public Set<Bolo> delete(PathParam("nome") String nome) {
    bolos.removeIf(boloExistente -> //
        boloExistente.nome.equalsIgnoreCase(nome));
    return bolos;
  }
}
```

Esse serviço é bem simples. Ele lista, adiciona ou remove bolos e sempre ao fazer uma operação de adição ou remoção, ele retorna todos os bolos cadastrados. 

Feito isso, basta apenas executar a aplicação com o comando:

```
mvn quarkus:dev
```

E está pronto. Temos nosso serviço de pé. Se acessarmos o endereço **https://localhost:8080/openapi** vamos ter o arquivo de descrição dos **endpoints** desse serviço. O arquivo tem mais ou menos essa cara:

```yml
---
openapi: 3.0.1
info:
  title: Generated API
  version: "1.0"
paths:
  /bolo:
    get:
      responses:
        "200":
          description: OK
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/SetBolo'
    post:
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/Bolo'
      responses:
        "200":
          description: OK
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/SetBolo'
  /bolo/{nome}:
    delete:
      requestBody:
        content:
          application/json:
            schema:
              type: string
      responses:
        "200":
          description: OK
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/SetBolo'
components:
  schemas:
    Bolo:
      type: object
      properties:
        descricao:
          type: string
        nome:
          type: string
    SetBolo:
      uniqueItems: true
      type: array
      items:
        $ref: '#/components/schemas/Bolo'

```

![Fry confuso](https://dev-to-uploads.s3.amazonaws.com/i/5xrp8ueos5d8rfbw44ym.jpg)

Realmente, temos uma descrição do nosso serviço. Porém, o fato de termos um arquivo de descrição, não quer dizer que essas informações são realmente úteis. Quer dizer, nós descobrimos que dentro do endpoint **/bolo** nós temos os métodos GET, Post e Delete. Mas e aí? O que é um bolo? O que ele recebe? 

![Globo reporter](https://dev-to-uploads.s3.amazonaws.com/i/86p9aed5nsdcjtoupufb.jpg)

Já temos a nossa descrição do **endpoint**, agora vamos tratar de fazer com que essa descrição tenha informações que sejam realmente úteis a quem for utilizar o nosso serviço.

Vamos começar descrevendo o serviço propriamente dito. Para isso, basta criar um arquivo chamado **Descricao.java** no diretório **com** e colar o seguinte código.

```java
package com;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(//
    tags = { //
        @Tag(name = "tutorial", description = "Exemplo de estudo."), //
        @Tag(name = "bolo", description = "Pessoas gostam de bolo.")//
    }, //
    info = @Info(//
        title = "A fantástica fábrica de bolo", //
        version = "1.0.0", //
        contact = @Contact(//
            name = "Fale com o Aleatório", //
            url = "https://github.com/lucasscharf")), //
    servers = { //
        @Server(url = "https://localhost:8080")//
    }) //
public class Descricao extends Application {
}
```

Essa classe serve como âncora para as anotações de descrição.
Essas anotações estão descrevendo o que essa aplicação faz, quem fez, informações importantes, etc... Uma explicação completa e descrição melhor dessas anotações, pode ser encontrada na documentação do [microprofile OpenAPI](https://github.com/eclipse/microprofile-open-api). 

Também podemos editar o nosso arquivo **BoloResource.java** para adicionar as anotações de descrição do OpenAPI. O novo arquivo ficará assim:

```java
package com;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/bolo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoloResource {
  private Set<Bolo> bolos;

  public BoloResource() {
    bolos = new HashSet<>();
    bolos.add(new Bolo("Chocolate", "Melhor bolo do mundo"));
    bolos.add(new Bolo("Sensação", "Chocolate com morango"));
  }

  @GET
  @Operation(summary = "Retorna todos os bolos cadastrados")
  @APIResponse(responseCode = "200", //
      content = @Content(//
          mediaType = MediaType.APPLICATION_JSON, //
          schema = @Schema(//
              implementation = Bolo.class, //
              type = SchemaType.ARRAY)))
  public Set<Bolo> list() {
    return bolos;
  }

  @Operation(summary = "Cadastra um bolo")
  @APIResponse(responseCode = "200", //
      description = "Retorna todos os todos os bolos cadastrados, incluindo o novo bolo", //
      content = @Content(//
          mediaType = MediaType.APPLICATION_JSON, //
          schema = @Schema(//
              implementation = Bolo.class, //
              type = SchemaType.ARRAY)))
  @POST
  public Set<Bolo> add(//
      @RequestBody(required = true, //
          content = @Content(//
              mediaType = MediaType.APPLICATION_JSON, //
              schema = @Schema(//
                  implementation = Bolo.class))) Bolo bolo) {
    bolos.add(bolo);
    return bolos;
  }

  @Operation(summary = "Deleta um bolo pelo nome do bolo")
  @APIResponse(responseCode = "200", //
      description = "Todos os bolos cadastrados menos aquele retirado", //
      content = @Content(mediaType = MediaType.APPLICATION_JSON, //
          schema = @Schema(implementation = Bolo.class, //
              type = SchemaType.ARRAY)))
  @DELETE
  @Path("/{nome}")
  public Set<Bolo> delete(//
      @Parameter(description = "Nome do bolo a ser retirado", required = true) //
      @PathParam("nome") String nome) {
    bolos.removeIf(boloExistente -> //
    boloExistente.nome.equalsIgnoreCase(nome));
    return bolos;
  }
}
```

Acessando a URL do OpenAPI, vamos ver que as informações estão bem mais detalhadas sobre a nossa aplicação. Porém, toda essa descrição ainda está presente em um arquivo yml o que não é tão amigável para um ser humano. Já ajuda, mas ainda dá para fazer melhor. 

Para fazer tudo isso e muito mais é que temos o Swagger UI.

## Swagger UI

Para quem não sabe, o Swagger UI é uma ferramenta que permite visualizar e interagir com os recursos da API. Ele é gerado através da documentação do OpenAPI (aquela que a gente descreveu lá em cima) e permite ter um acesso fácil ao back-end criando um cliente simples.

A parte mais legal é que o Quarkus já deixa tudo pronto para usarmos. Após colocar as anotações de descrição na nossa aplicação, é só acessar a url **/swagger-ui** do nosso serviço.

Ao fazer isso, nós teremos a seguinte tela:

![swagger-ui](https://dev-to-uploads.s3.amazonaws.com/i/sd3cvepkjyh1txdytz6l.png)

Ela traz a descrição da nossa aplicação, dos endpoints e até permite que a gente chame diretamente o serviço para fazermos testes sem ter que levantar uma interface gráfica. 

Aproveite bem para b̶r̶i̶n̶c̶a̶r̶ dedicar um tempo estudando e ver como tudo funciona. Testar outras anotações e adicionar diferentes comentários. 

# Considerações
Documentação é algo muito importante. O OpenAPI permite tanto documentar facilmente nossa aplicação, como poupa o trabalho de ter que ficar criando classe repetitivas. 

É vitória de tudo quanto é lado.

Uso muito o Swagger UI para testar diferentes entradas nos meus serviços e depois automatizar tudo em testes.

Ah, e o código desse post pode ser encontrado no [github](https://github.com/lucasscharf/blog-posts-code/tree/master/open-api).