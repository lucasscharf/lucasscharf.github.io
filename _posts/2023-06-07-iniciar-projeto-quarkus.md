---
title: Como iniciar um projeto em Quarkus
categories: [Java, Quarkus]
tags: [Java, Quarkus]
---

Não basta o Quarkus ser o ̶ú̶n̶i̶c̶o̶ melhor framework supersônico e subatômico do mercado, ele ainda tem esse logo lindo.

![Quarkus tem um logotipo lindo](/images/quarkus_logo.png)

Porém, muitas pessoas ficam com dúvidas sobre como criar um novo projeto usando essa ferramenta maravilhosa. No tutorial de hoje, iremos aprender como criar um novo projeto de duas formas diferentes: usando o [Quarkus CLI (também chamado de linha de comando Quarkus)](https://quarkus.io/guides/cli-tooling) e usando o site [code.quarkus.io](https://code.quarkus.io/).

# Quarkus CLI

O Quarkus CLI é uma ferramenta de linha de comando bastante completa, com diversos utilitários que ajudam a vida do desenvolvedor. Um desses utilitários permite a criação de um projeto Quarkus do zero. Primeiro, precisamos instalar essa ferramenta.

Para usuários do Linux, macOS e Windows que usam ferramentas compatíveis com WSL, basta executar os seguintes comandos:

```bash
curl -Ls https://sh.jbang.dev | bash -s - trust add https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/
curl -Ls https://sh.jbang.dev | bash -s - app install --fresh --force quarkus@quarkusio
```

Para usuários windows que usam Powershell, os comandos ficam:

```bash
iex "& { $(iwr https://ps.jbang.dev) } trust add https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/"
iex "& { $(iwr https://ps.jbang.dev) } app install --fresh --force quarkus@quarkusio"
```


Após ter o Quarkus CLI instalado, basta dar o comando __create__:

```bash
quarkus create && cd code-with-quarkus
````

Depois de esperar algum tempo na primeira execução, está pronto!

![](/images/its_alive.jpg)

Você pode usar o comando:

```
mvn quarkus:dev
```

E acessar o navegador em ``localhost:8080`` para receber um maravilhoso "Hello, World!". O modo "create" permite várias customizações, como a escolha das extensões que você deseja utilizar, nome de pacotes, códigos de exemplo, etc.

![](/images/polishop_e_nao_eh_soh_isso.png)

O Quarkus CLI permite adicionar novas extensões, executar a aplicação, construir imagens Docker, implantar o projeto em um cluster Kubernetes, atualizar versões do seu projeto e muito mais...

# code.quarkus.io

O Quarkus também possui seu site gerador de código semelhante ao [Spring Initializr](https://start.spring.io). O primeiro passo é entrar no link do [code.quarkus.io](https://code.quarkus.io). Fazendo você verá a seguinte tela:

![](/images/quarkus-code-generator.png)

No bloco 1, existem opções para configurar seu projeto, incluindo o grupo, o artefato, a ferramenta de build (Maven ou Gradle), a versão da aplicação, a versão do Java e se você deseja usar códigos de exemplo das extensões.

No bloco 2, há uma lista de extensões que você pode adicionar. Existem várias extensões com diferentes propósitos.

Após configurar a aplicação e selecionar as extensões desejadas, basta clicar no botão **Generate your application** (bloco 3) e a mágica acontece. O gerador fará o download de um arquivo .zip contendo o projeto. Basta descompactá-lo e executar.

# Conclusões

Com isso, temos duas formas legais e rápidas de iniciar um projeto Quarkus. Nos artigos, vou assumir que você já possui um projeto e farei edições diretamente no arquivo ``pom.xml``. Espero que este tutorial tenha sido útil. Até a próxima o/