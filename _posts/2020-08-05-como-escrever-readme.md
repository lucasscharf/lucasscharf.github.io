---
title: Como escrever um bom README.md
categories: [git]
tags: [Ferramentas, git]
---

Fala galerinha do barulho, prontos para se meterem em um monte de confusão enquanto seguimos desbravando como funciona o git?

Pra você não se perder, segue o nossa mapa maroto:

* [O que é o GIT e como usar](https://dev.to/lucasscharf/uma-gentil-introducao-ao-git-e-seus-comandos-2lf)
* [Principais comandos do git](https://dev.to/lucasscharf/alguns-dos-principais-comandos-do-git-3dhd)
* [Como fazer um rebase de presença](https://dev.to/lucasscharf/como-fazer-um-rebase-de-presenca-2jj7)
* Como escrever um README.md ⟵ você está aqui
* [Boas práticas para utilizar o git](https://dev.to/lucasscharf/boas-praticas-para-usar-o-git-2e0e)

Já aprendemos [o que é o git](https://dev.to/lucasscharf/uma-gentil-introducao-ao-git-e-seus-comandos-2lf), já aprendemos quais são os [principais comandos](https://dev.to/lucasscharf/alguns-dos-principais-comandos-do-git-3dhd) e até aprendemos [conquistar pessoas nas baladas da vida](https://dev.to/lucasscharf/como-fazer-um-rebase-de-presenca-2jj7) usando o rebase. 
No episódio de hoje, nós vamos dar um passo além na nossa jornada e aprender a fazer um documento README topzeira.

# O que é o README?
É um documento do tipo *markdown* (que é tipo um html simplificado) que descreve informações importantes sobre o seu projeto. [Esse artigo](https://medium.com/@raullesteves/github-como-fazer-um-readme-md-bonit%C3%A3o-c85c8f154f8) explica bem como faz para escrever um *markdown*. Nós vamos nos focar mais no conteúdo e menos nesses detalhes de tags. 

Logo abaixo, nós teremos quais sessões devem ter nesse  documento de descrição (incluindo quais sessões são opcionais) para você colocar no seu projeto do git. 

## Nome do projeto 
A primeira parte do projeto é para ser o nome do projeto. Com uma tag de título (#). Eu sei que quem for ler consegue ler o nome na URL, mas a ideia é simplificar a vida de quem está lendo tudo.

## Opcional: Logo, *Badges*
Sem tags de título, aqui nós colocamos qualquer *badge* que possa ser interessante para o projeto (status do jenkins, número de vezes que o projeto foi clonado, estrelas no github, etc...). Também vamos colocar logo e as coisas que identifiquem graficamente o projeto.

## Opcional: Status do projeto caso ele já esteja morto
Ninguém é obrigado a dar manuntenção num projeto para sempre. Caso você tenha decidido deixar o projeto de lado ou não consiga mais contribuir mais com ele é importante deixar claro aos demais que não haverá mais atualizações. 
Isso evita bastante frustração para as partes envolvidas.

## Descrição do projeto (em um parágrafo)
Também sem tag, aqui é o momento de explicar sucintamente o que o projeto faz. O ideal é que não passe de um parágrafo. 

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/5tzxtjjt56gt123ngb8k.jpeg)

## Como rodar
Todo o sistema tem pré requisitos para rodar (o menor deles é você ter um computador). Essa sessão serve para explicar o que o usuário precisa para baixar/instalar/rodar o  sistema. 
Caso o sistema tenha as dependências em docker, é importante colocar o docker-compose.yml e linká-lo aqui.

## Como testar
Tão importante quanto rodar a aplicação é fazer a rotina de testes. Aqui é explicado como a aplicação é testada, se é necessário levantar alguma dependência para os testes ou mesmo se existe a̶l̶g̶u̶m̶a̶ ̶m̶a̶g̶i̶a̶ algum comando especial para executar os testes. 

## Propriedades/variáveis de ambiente
Como falei lá no [post sobre configuração](https://dev.to/lucasscharf/configurando-sua-aplicacao-java-com-quarkus-configproperty-2h2c). "Não tem jeito. Inevitavelmente um software precisa de algum tipo de configuração. Seja as credenciais de acesso ao banco de dados, o endereço de algum serviço externo, regras de log, etc..."
Essa sessão, serve para explicar quais são as principais propriedades do sistema, para o que elas servem e como/onde alterar. 

## Opcional: como contribuir/como ajudar
Se o projeto é um software livre, então é muito importante explicar para as pessoas como elas podem ajudar. 
Esse espaço também serve para você deixar links de doação. 

## Opcional: *Acknowledgements* ou Agradecimentos
Esse espaço serve exclusivamente para os desenvolvedores se promoverem. Caso alguém esteja realmente interessado em saber quem trabalhou no projeto, a pessoa consegue ir no histórico de *commits*. 
Particularmente, considero um desperdício de espaço. 

# Dicas gerais
Essas dicas foram testadas no github, mas os demais serviços devem ter soluções parecidas. 

## Não usar *changelog*/*releases* no README.md
É possível usar a aba *releases* do github para colocar o histórico de alterações. Fica mais limpo. 
Também é possível criar um arquivo chamado CHANGELOG.md para as alterações sem a necessidade de ficar poluindo o README.md.

## Não usar link para licença
Você pode colocar o conteúdo da licença num arquivo LICENSE.txt que o github vai entender qual licença é.

## Usar *template* de repositório
Caso você precise fazer vários projetos. Você pode criar um *template* para servir de modelo e ir seguindo ele.