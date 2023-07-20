---
title: Uma gentil introdução ao git e seus comandos
categories: [git]
tags: [Ferramentas, git]
---

10 em cada 10 desenvolvedores recomendam a utilização do git como ferramenta de controle de versão. 

Ao longo de todos esses anos, o git tem ajudado desenvolvedores ao redor do mundo a organizarem seus projetos, fazerem backups, trocarem informações, escreverem livros, montarem blogs, etc... Porém, apesar de ser uma ferramenta tão incrível muita gente acaba usando sem saber exatamente o que tá fazendo, dependendo da IDE e passando por algumas dificuldades sem necessidade. 

E é por isso que eu resolvi fazer uma sequência de posts falando sobre o git e como usar. Esses posts serão divididos nas seguintes partes:
* O que é o GIT e como usar **⟵ você está aqui**
* [Principais comandos do git](/posts/principais-comandos-git/)
* [Como fazer um rebase de presença](/posts/como-fazer-rebase/)
* [Como escrever um README.md](/posts/como-escrever-readme/)
* [Boas práticas para utilizar o git](/posts/boas-praticas-git/)

# O que é esse tal git?

Também conhecido como melhor invenção desde [aquela paradinha para coçar as costas](https://www.google.com/search?q=aquela+paradinha+pra+co%C3%A7ar+as+costas&oq=aquela+paradinha+pra+co%C3%A7ar+as+costas&aqs=chrome..69i57.4901j0j9&sourceid=chrome&ie=UTF-8). O [git](https://git-scm.com/) é uma ferramenta livre, de código aberto para controle de versão distribuído. 

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/1w2hh3b8438x6ok24qbv.png)

Com ele o código e suas alterações ficam nas máquinas de todos os usuários. Através da utilização de *snapshots* dos arquivos, é possível ter acesso rápido a tudo o que foi feito pelo resto do time.

# Como funciona?

Antes de entrar nos detalhes de como o git funciona, é necessário entender que o git trabalha com 4 áreas diferentes. O diretório de trabalho (*working directory*), a área de estagiamento (*staging area*), o repositório local (*local repo*) e o repositório remoto (*remote repo*). Essas áreas vão se relacionando entre si, mais ou menos como na imagem abaixo. 

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/zbu0eocercv8edfs846m.png)

* **Diretório de trabalho** é o diretório que você está utilizando para trabalhar (duh!). Não vou entrar muito nos detalhes (já que essa é uma introdução gentil), mas pense no diretório de trabalho como um [sandbox](http://www.otimizacao-sites-busca.com/otpo/posicionamento/sandbox-caixa-areia.htm#:~:text=Sandbox%20foi%20usado%20primeiramente%20%E2%80%93%20e,evitar%20danos%20ao%20pr%C3%B3prio%20programa) para todas as alterações que você está fazendo. Ele permite que você faça/desfaça alterações sem compromisso ou afetar qualquer outra coisa.
* **Área de estagiamento** essa área é o meio termo entre o seu diretório de trabalho e o repositório local. Nesse momento, as alterações estão indicadas para serem salvas no repostório local, porém ainda não foram salvas. 
* **Repositório local** é onde as coisas ficam salvas. Todos arquivos que você cria e as alterações desses arquivos ficam salvas de forma compactada dentro do diretório .git.
* **Repositório remoto** é um local que centraliza os repositórios seus e de todas as pessoas envolvidas no projeto. Os repositórios mais remotos que nós temos são o [github](https://github.com/), o [bitbucket](http://bitbucket.org/) e o [gitlab](https://gitlab.com/explore).

Entendendo esses conceitos, fica mais fácil de entender como funcionam alguns comandos do git.

Nós criamos um repositório através do comando ``git init`` (esse comando cria o repostório local, a área de estagiamento e o diretório de trabalho);
Quando nós alteramos um arquivo, nós estamos alterando o diretório de trabalho;
Para persistir essa alteração, nós usamos o comando ``git add``;
Para levar essa alteração da área de estagiamento para o repositório local, é utilizado o comando ``git commit``;
o processo de levar o conteúdo do repositório local para o repositório remoto, é feito com o comando ``git push``.

E nós temos o caminho de volta, onde o ``git clone`` copia o repositório remoto e inicializa esse repositório (com a área de estagiamento e diretório de trabalho) num diretório específico;
O ``git fetch`` atualiza o repositório local com as informações do repositório remoto;
O ``git merge`` atualiza o diretório local com as mudanças feitas no diretório remoto. (nos próximos posts vou falar melhor sobre o pull e checkout).

Imagino que já deva ter ficado claro nesse momento, mas é bom frisar:

**O GIT É A FERRAMENTA DE CONTROLE DE VERSÃO, GITHUB/GITLAB/BITBUCKET PERMITEM O ARMAZENAMENTO DE REPOSITÓRIO REMOTOS.**

# Considerações
Espero do fundo do coração ter conseguido explicar de forma simples como as coisas funcionam. Inicialmente, eu iria fazer um único post, mas é tanta coisa legal para falar que fica complicado :/
Se tem algum tema legal sobre desenvolvimento que você queira que eu fale sobre, deixe um comentário. Você fará um aleatório muito feliz. 
