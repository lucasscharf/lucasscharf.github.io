---
title: Boas práticas para usar o git
categories: [git]
tags: [Ferramentas, git]
---

Seja bem vindo a mais uma ~gentil~ introdução ao git. Chegamos ao nosso último post e vamos ver sobre algumas boas práticas para utilizar o git. 
Caso você queira saber sobre tudo o que nós falamos até agora, basta olhar esse mapa maroto:

* [O que é o GIT e como usar](https://dev.to/lucasscharf/uma-gentil-introducao-ao-git-e-seus-comandos-2lf)
* [Principais comandos do git](https://dev.to/lucasscharf/alguns-dos-principais-comandos-do-git-3dhd)
* [Como fazer um rebase de presença](https://dev.to/lucasscharf/como-fazer-um-rebase-de-presenca-2jj7)
* [Como escrever um README.md](https://dev.to/lucasscharf/como-escrever-um-readme-md-26jj)
* Boas práticas para utilizar o git ⟵ você está aqui


#Dicas Gerais
## Conhecer bem o git
O git é uma ferramenta ótima. Porém, [como já vimos](https://dev.to/lucasscharf/alguns-dos-principais-comandos-do-git-3dhd), ele tem comando pra caramba. Não tô dizendo para vocês virarem especialistas no git, mas é importante conhecer bem os comandos, entender como funciona a árvore de commits, [saber fazer bem um rebase](https://dev.to/lucasscharf/como-fazer-um-rebase-de-presenca-2jj7), saber ler um reflog, entender os diferentes tipos de merge, etc...

## Saber usar a linha de comando
As ferramentas gráficas para git são bem interessantes e ajudam bastante. Mas tem muitas coisas que só dá para fazer corretamente pela linha de comando. O quanto antes aprender a mexer no git pela linha de comando, melhor.

## Siga padrões
É extremanete importante que todos os colaboradores falem a mesma língua, escrevam commits da mesma forma e sigam o mesmo fluxo de trabalho. Se não, fica praticamente impossível restrear qualquer coisa no git.

## Usar sempre o mesmo idioma
Ainda na linha de padrões, é **necessário usar o mesmo idioma**. 

## Configurar corretamente a autoria
A autoria permite saber quem exatamente fez cada commit (o famoso git blame). É bem simples, basta usar os seguintes comandos:

```bash
git config --global user.name "Seu nome"
git config --global user.email seu_email@exemplo.com
```

## Usar um repositório por projeto 
A menos que tenham pessoas que sabem o que estão fazendo para trabalhar como um projeto mono repo, utilize um repositório por projeto. Várias aplicações por projeto é a receita para o caos e confusão.

## Ter um workflow (e fazer as pessoas seguirem)
## Adicionar um pipeline de CD

## Use boas ferramentas
O git sozinho já é ótimo e existem algumas ferramentas que podem ajudar bastante em atividades específicas (visualizar a árvore de commits, fazer o blame automático enquanto você escreve o código, ajudar a ver os conflito, etc...).

Particularmente, eu uso o VSCode para resolver conflitos (com o plugin do gitlens) e o tig para ver a árvore. Vejo muita gente usando o git kraken, mas nunca usei.

## Usar repositórios de template
O Github tem uma ferramenta ótima q̶u̶e̶ ̶d̶e̶v̶e̶r̶i̶a̶ ̶s̶e̶r̶ ̶c̶o̶p̶i̶a̶d̶a̶ ̶p̶e̶l̶o̶ ̶b̶i̶t̶b̶u̶c̶k̶e̶t̶ para quem trabalha com vários projetos que é criar um template de repositório com todas as coisas corretamente configuradas. Isso acelera muito o processo de desenvolvimento

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/t3zwaopl8esqams42upa.gif)

#Dicas para bons *commits*
## Anotar a issue
Via de regra, você trabalhará com algum sistema de gestão de atividades/issues/tickets integrado com o seu git. No footer de cada commit, basta indicar a commit issue o commit se refere. Isso adiciona mais rastreabilidade no que foi feito e permite indica o caminho de documentações que não cabem dentro de uma mensagem de commit 

## Considerar os commits como documentação
Para muitas empresas, os commits não representam nada em relação ao código. Porém, ele é uma boa fonte de documentação histórica do código. 

## Escrever o que foi feito, o que não foi feito e porque
No uma boa mensagem de commit tem o que foi feito, o porque foi feito e o que tentou fazer mas não deu certo (claro que dentro do bom senso, afinal, nem todos os commits carregam esse peso). 

## Usar título 
A primeira linha de um commit serve como título. É ela quem aparecerá dentro das ferramentas e na hora de ler o log. Por isso é importante uma descrição simples do que aquele commit representa (de preferência com menos de 80 caracteres)

#Dicas para bons Pull Requests
## Fazer vários commits, porém fazer o rebase
Uma das funções do git é servir de backup remoto. Você pode fazer commits diretos com as tentativas de arrumar um problema, ou mesmo fazer commits todos os dias. Na hora do PR, é só juntar as alterações feitas, descartar o lixo e fazer um ou mais commits relevantes. 
O mesmo serve para a revisão de código de um PR. Basta ir fazendo as alterações necessárias e depois fazer um rebase para unir tudo com o commit principal. Isso gera uma árvore limpa e fácil de navegar. 

## Quando fazer o PR, já adicionar os testes
Sempre que possível, já adicione os testes dentro do PR. Além de ajudar a entender melhor o que foi feito, garante que a cobertura de testes do sistema permanecerá alta. 

## Fazer PR apenas de coisas relacionadas
Se você adicionou uma funcionalidade, abre um PR para isso e só isso. Não é para colocar formatação de arquivo ou outras coisas nesse PR. 

## Não integrar código comentado
O git já consegue manter o histórico de tudo o que foi feito. Se você seguir boas práticas, não há necessidade para fazer PR com código comentado

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/2rrs290hccdtdio4v4xz.jpg)

#Dicas para ter uma boa árvore
## Evitar de reescrever a história da Master 
Aqui estou falando de qualquer branch que mais de uma pessoa utiliza. Quando rola rebases, é possível que o trabalho de um acabe prejudicando o de outro. 

## Manter o repositório local atualizado (de preferência com rebase)
É só ficar fazendo ``git fetch --all`` e ``git rebase origin/<nome da branch principal>`` que você vai manter a sua árvore sempre atualizada e isso evita maiores dores de cabeça na hora de criar o PR.

## Abrir branchs
Se você trabalha com outras pessoas, não é bom trabalhar diretamente na branch principal. É p̶r̶o̶v̶á̶v̶e̶l̶ possível que alguém acabe fazendo um rebase que não deve ou algo assim. Por isso é bom sempre abrir uma branch, fazer suas alterações e então mergear via pull request.

## Proteger a master
Acidentes acontecem, por isso é importante impedir as pessoas de fazerem *pushs* direto na master ou develop. Isso é feito através da configuração do repositório. 

## Não comitar grandes arquivos binários
O git funciona muito bem para arquivos de textos e pequenos binários (coisa de alguns KB). No momento que você começa a colocar arquivos maiores, começa o diretório .git começa a dar ruim (principalmente se você mexer muito nesse arquivo)

## Usar um bom .gitignore
Um bom .gitiginore ajuda a evitar o envio de arquivos binários. Eu Eu gosto bastante o do [gitiginore.io](https://www.toptal.com/developers/gitignore).