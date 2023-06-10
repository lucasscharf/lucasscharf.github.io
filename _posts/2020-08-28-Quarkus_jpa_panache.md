---
title: Trabalhando com JPA, Quarkus, Panache e bolo de chocolate
categories: [Java, Quarkus]
tags: [Java, Quarkus, Banco de Dados]
---

Segundo o centro de pesquisas de números tirados da minha cabeça, JPA é uma das especificações Java mais utilizadas no mundo. Já existe [muito](https://www.devmedia.com.br/introducao-a-jpa-java-persistence-api/28173), [muito](https://blog.algaworks.com/tutorial-jpa/), [muito](https://www.caelum.com.br/apostila-java-web/uma-introducao-pratica-ao-jpa-com-hibernate), [muito](https://www.youtube.com/watch?v=398Ul6wq5U4) conteúdo mesmo ([é sério](https://www.google.com/search?q=jpa+java&oq=jpa+java&aqs=chrome..69i57j0l5j69i61j69i60.1055j0j7&sourceid=chrome&ie=UTF-8)).

Esse post é a preparação para um próximo post que farei sobre atualização de banco de dados com Flyway. Enquanto escrevia, notei que ele ficou bem longo porque tava trazendo vários conceitos de JPA e queria garantir que você, nobre leitor, soubesse do que eu estou falando. 
Então a gente vai deixar tudo bem bonitinho aqui para que o próximo post seja topzeira de entender.

#O que faremos?
Vamos evoluir o código da nossa [fantástica fábrica de bolo](https://dev.to/lucasscharf/quarkus-openapi-uma-combinacao-tao-boa-que-deveria-se-chamar-bolo-de-chocolate-4eb7) para salvar as informações num banco de dados. Depois, vamos usar o OpenAPI para validar o que fizemos.

#É hora da ação
Partido do código da fábrica de bolos. Vamos adicionar as extensões do JPA e o conector com PostgreSQL. Vamos usar o seguinte comando.

```java
./mvnw quarkus:add-extension -Dextensions="quarkus-hibernate-orm-panache,quarkus-jdbc-postgresql"
```

Depois, vamos colocar as configurações de acesso no nosso arquivo **application.properties**. Esse arquivo vai ficar com a seguinte cara:

```
quarkus.datasource.db-kind = postgresql
quarkus.datasource.username = Sarah
quarkus.datasource.password = Connor
quarkus.datasource.jdbc.url = jdbc:postgresql://localhost:5432/skynet

quarkus.hibernate-orm.database.generation = update
```

Com isso, nós informamos ao Quarkus que vamos usar um banco de dados PostgreSQL, que o usuário é Sarah, a senha é Connor, o banco se chama skynet e está rodando na máquina local na porta 5432. Também, informamos ao JPA que queremos que ele  gere as tabelas do banco de dados baseado nas nossas entidades.

E por falar em entidade, vamos alterar a classe Bolo para que ela vire uma entidade persistível. Para isso, é só trocar o código do arquivo **Bolo.java** por esse daqui.

```java
package com;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Bolo extends PanacheEntity {
  public String nome;
  public String descricao;

  public Bolo() {
  }
  
  public Bolo(String nome, String descricao) {
    this.nome = nome;
    this.descricao = descricao;
  }

}
```

Bem mais simples que a versão anterior, neah?! Extendendo a classe PanacheEntity, o Quarkus já faz um monte de coisa:

* Permite utilizar o padrão [Active Record](https://pt.wikipedia.org/wiki/Active_record);
* Todos os campos públicos são tratados como colunas no banco de dados (mais ou menos como se tivesse a anotação @Column);
* A entidade herdará um id do tipo long (chamado id) auto gerado;
* Em tempo de compilação, os getters e setters da entidade serão gerado;
* Em tempo de compilação, o acesso direto aos atributos da entidade serão transformados em chamadas **getters** e **setters** (se você duvida, crie um setter e coloque um **System.out** para ver a magia acontecer).

Nós adicionamos a anotação **@Entity** para que o JPA saiba que essa é uma das nossas entidades.

Também vamos precisamos alterar a nossa classe **BoloResource**. Ela ficará com a seguinte cara.

```java
package com;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
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

  @PostConstruct
  @Transactional
  public void init() {
    new Bolo("Chocolate", "Melhor bolo do mundo").persist();;
    new Bolo("Sensação", "Chocolate com morango").persist();;
  }


  @GET
  @Operation(summary = "Retorna todos os bolos cadastrados")
  @APIResponse(responseCode = "200", //
      content = @Content(//
          mediaType = MediaType.APPLICATION_JSON, //
          schema = @Schema(//
              implementation = Bolo.class, //
              type = SchemaType.ARRAY)))
  public List<Bolo> list() {
    return Bolo.listAll();
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
  @Transactional
  public List<Bolo> add(//
      @RequestBody(required = true, //
          content = @Content(//
              mediaType = MediaType.APPLICATION_JSON, //
              schema = @Schema(//
                  implementation = Bolo.class))) Bolo bolo) {
    bolo.id = null; //coisa feia, não façam isso em casa
    bolo.persist();
    return list();
  }

  @Operation(summary = "Deleta um bolo pelo nome do bolo")
  @APIResponse(responseCode = "200", //
      description = "Todos os bolos cadastrados menos aquele retirado", //
      content = @Content(mediaType = MediaType.APPLICATION_JSON, //
          schema = @Schema(implementation = Bolo.class, //
              type = SchemaType.ARRAY)))
  @DELETE
  @Path("/{nome}")
  @Transactional
  public List<Bolo> delete(//
      @Parameter(description = "Nome do bolo a ser retirado", required = true) //
      @PathParam("nome") String nome) {
    Bolo.delete("nome", nome);
    return Bolo.listAll();
  }
}
```

Aqui, nós tiramos o método construtor e criamos um método **init()** que com a anotação **PostConstruct**. Essa anotação garante que o método seja rodado logo após a construção do nosso **BoloResource**. Ao rodar esse método, nós vamos cadastrar os dois bolos iniciais.

![magic image](https://dev-to-uploads.s3.amazonaws.com/i/vkrfxda2j9tjactroysm.jpg)

Os métodos foram reescritos para usarem o *active record* e as operações que alteram dados no banco de dados receberam a anotação **@Transactional**. Essa anotação é necessária para que a operação seja feita em uma transação.

Só falta subir o banco de dados e testar. Isso será feito através do seguinte comando [docker](https://www.docker.com/) que criará um container com as nossas credenciais:

```shell
docker run -e POSTGRES_USER=Sarah -e POSTGRES_PASSWORD=Connor -e POSTGRES_DB=skynet -p 5432:5432 postgres:12-alpine
```

Para testar, basta rodar o seguinte comando 

```shell
mvn quarkus:dev
```

e voilá. O serviço está pronto para b̶r̶i̶n̶c̶a̶r̶m̶o̶s̶ testarmos através da url **localhost:8080/bolo**. Com a grande vantagem de que é possível baixar e subir o servidor que os dados estarão salvos. E, se nós quisermos recomeçar tudo do zero, é só parar o container docker que nós criamos. 

#Considerações
Esse artigo nasceu mais da necessidade de garantir que está todo mundo falando a mesma língua. 

Nos meus projetos, não utilizo o ActiveRecord porque tenho preconceito com métodos estáticos, mas reconheço que ele tem suas vantagens em projetos BEEEEEEM pequenos. 

Um ponto importante, esse código é um exemplo e que nós queremos apenas cadastrar um Bolo. Por isso que nós colocamos **null** no id. Num mundo real, não devemos ir expondo as nossas entidades do sistema, mas usarmos DTOs para isso. 

Estou ciente de que existe um bug de que cada vez que a gente sobe a aplicação, ele vai cadastrando novos bolos. Isso será resolvido no próximo tutorial. Agora, que agora venha o tutorial sobre flyway.

Ah, e o código de hoje pode ser encontrado no meu [github](https://github.com/lucasscharf/blog-posts-code/tree/master/jpa-panache).