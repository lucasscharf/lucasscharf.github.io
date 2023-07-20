---
title: Como fazer um rebase de presença
categories: [git]
tags: [Ferramentas, git]
---

Seja bem vindo a mais uma ~gentil~ introdução ao git. Hoje, nós vamos aprender a como fazer um rebase de presença e deixar a nossa árvora de commits um pitelzinho.

Antes de começar, vamos lembrar do nosso planejamento de posts:

* [O que é o GIT e como usar](https://dev.to/lucasscharf/uma-gentil-introducao-ao-git-e-seus-comandos-2lf)
* [Principais comandos do git](https://dev.to/lucasscharf/alguns-dos-principais-comandos-do-git-3dhd)
* Como fazer um rebase de presença ⟵ você está aqui
* [Como escrever um README.md](https://dev.to/lucasscharf/como-escrever-um-readme-md-26jj)
* [Boas práticas para utilizar o git](https://dev.to/lucasscharf/boas-praticas-para-usar-o-git-2e0e)

Porque essa é uma introdução bem gentil, vamos dividir o conteúdo sobre nosso *rebase* em algumas partes. O que é uma *branch*? O que é um *rebase* e como ele funciona? Quais são as opções de um *rebase* interativo? Um pequeno exemplo de como fazer um rebase para conquistar o coração daquele pichadão/tchutchuca que tá te filmando e quer ser seu namorado.

# O que é uma branch?
Quando nós fazemos um *commit*, git cria um objeto que indica quais mudanças foram feitas. Esse objeto tem um monte de informação interessante como autor, horário, *hash* e os pais do commit. 

Uma branch é, apenas, um ponteiro para algum desses *commits*. Ao fazer um *commit*, é adicionado um novo nó nessa árvore e o ponteiro é deslocado para esse novo nó. E quandos nós criamos uma nova *branch*, nós estamos apenas criando um novo ponteiro apontando para aquele *commit*. A imagem abaixo mostra isso.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/otu3ozthmb6a1vpwo35a.png)

Temos três *commits*, 98ca9, 34ac2 e f30ab. Inicialmente, tínhamos uma única branch (master) apontando para o commit f360ab. Após o comando ``git checkout -b testing`` criamos a *branch* testing que também aponta para o mesmo local. 

Se nós estivermos na branch testing e fizemos um commit (vamos chamar de 87ab2), a testing irá apontar para esse novo commit, enquanto a branch master continuará apontando para o f30ab.

Além disso, existe ponteiro especial chamado **HEAD** que aponta para a *branch* que nós estamos trabalhando. O movimento desse ponteiro poderá alterar os arquivos nós estamos vendo no nosso diretório de trabalho. E nós movimentamos o HEAD através do comando ``git checkout <hash do commit ou nome da branch>``

Na imagem abaixo, reunimos tudo o que vimos até agora. As branchs **master** e **testing** (com seus *commits*) e o ponteiro HEAD.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/ai8qvzhy3d04rbz2mfxm.png)

Num outro [post](https://dev.to/lucasscharf/alguns-dos-principais-comandos-do-git-3dhd) nós vimos o funcionamento do checkout. Agora nós conseguimos entender melhor o que ele faz. Ao usar o checkout, nós estamos movimentando a HEAD. É possível movimentar tanto entre **branchs** quanto entre **commits**. Quando fazemos essa movimentação, o git pode alterar, adicionar ou remover arquivos. E o git sabe sempre como se achar porque dentro do objeto dos commits tem todas as informações importantes para ele trabalhar fazer o que precisa ser feito. 

Na imagem abaixo, nós fizemos um ``git checkout master`` e fomos para a *branch* master. Depois disso, criamos um novo commit (c2b9e). Desse ponto em diante, as duas *branchs* começam a divergir e terão um ancestral comum (f30ab).

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/4j0ytxnh4pfndmi1e1ys.png)

Agora vem a parte mágica.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/92aqzw4nbjnrkgw0qspb.jpg)

# O que é um *rebase* e como ele funciona? 

A operação de *rebase* reaplica os *commits* já feitos em um árvore à partir de um determinado *commit* ou de uma determinada *branch*. 

Na sua versão mais básica um rebase pode ser utilizado para sincronizar duas branchs (como o ``git merge``) com a diferença que, enquanto o git merge gera um novo commit unindo as duas branchs. O rebase aplica todos os commits novos em cima dos commits já existentes de uma branch.

Vamos deixar os exemplos acima de lado e vamos trabalhar com um exemplo mais simplificado ainda (que tem uma pequena mentirinha explicada melhor no fim desse post). Aqui temos duas branchs (**master** e **testing**) e elas tem um ansestral e comum (*commit* E). 

```
          A---B---C testing
         /
    D---E---F---G master
```

Se nós estivermos na branch **testing** e fizermos o comando 

```bash
git rebase master
```
A árvore da branch testing ficará*:

```
D---E---F---G---A---B---C
```

Isso é, o git pegou todo o conteúdo *branch* master e, depois dele, colocou o conteúdo da branch testing.

Um detalhe importante é que se passarmos uma branch ao invés de um commit como parâmetro para o git rebase, então o commit considerado será o commit do topo daquele rebase.

Na sua forma super-ultra-plus-master-advanced, o rebase vira o rebase interativo. Nessa forma, podemos fazer alterações nos *commits* antes de aplicarmos eles. Vamos ver algo essas alterações.

# Quais são as opções de um rebase interativo?

Para fazermos um rebase interativo, nós precisamos apenas adicionar o parâmetro -i no nosso rebase. Quando fazemos isso, o git irá abrir o editor de texto padrão com um arquivo especial chamado **git-rebase-todo**. Esse arquivo contém todos os *commits* que serão utilizados para o rebase (sendo referenciados pelo seus hashs e suas mensagens de commit) e qual operação que será feita com ele (na parte de exemplos, vamos dar uma olhada na cara desse arquivo).

Nesse arquivo, nós podemos reordenar os *commits*, remover os *commits* ou escolher qual operação aplicar. As operações são:

* p, pick = utilizar o commit
* r, reword = utilizar o commit, mas alterando a mensagem de commit
* e, edit = utilizar o commit, mas parando para fazer alguma alteração (como adicionar ou remover algum elemento do commit)
* s, squash = utilizar o commit, mas juntar as alterações com o commit anterior (se dois commits alterarem o mesmo arquivo, a alteração mais recente será utilizada)
* f, fixup = semelhante ao squash, mas ignorando a mensagem de commit
* x, exec = executar algum comando shell
* d, drop = remover o commit

# Um pequeno exemplo de como fazer um *rebase* para conquistar o coração daquele pichadão/tchutchuca que tá te filmando e quer ser seu namorado

Se você não é a musa do verão, então você precisa ralar para conseguir um bom cobertor de orelhas. E a melhor forma de fazer isso é falar sobre as operações de *rebase* do git para aquela pessoa que você está de olho.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/wtckygybhb28gzwgrzkr.jpg)

E pra te dar uma mão nesse plano, vou apresentar aqui um pequeno exemplo de um rebase interativo.

Imagine a situação, você abriu um **pull request** e o revisor pediu um monte de alterações. Você *comita* as alterações e ele pede outras. Por fim, ele diz "faz um **squash** e um **push** aí das alterações para ter um histórico limpo". 

O que ele quer é que todos os *commits* do tipo "ajustes do PR" sejam colocados como um único *commit* e, se for o caso, alterar a mensagem para representar as alterações feitas. 

Após todas as revisões, o histórico de commits antes do rebase era algo mais ou menos assim:
![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/zqksvs5rx2f7aoznnjgs.png)

Como foram feito 6 commits, nós precisamos fazer um rebase interativo à partir de 6 commits anteriores ao ponteiro da HEAD. Fazemos isso com o comando ``git rebase -i HEAD~6``. 
Isso vai abrir a tela de alteração dos *commits*. Daí nós só precisamos marcar os *commits* que não são o primeiro como **squash**. 

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/xg3nv2k84dwuau8urmao.png)

Após salvar e fechar, o git vai abrir a tela para nós editarmos a mensagem de *commit*. Nessa tela, todas as mensagens de commit que sofreram o **squash** estarão presentes. Com isso é só colocar a mensagem de commit que você quer e *voilá*. No final, a árvore de commits ficará mais ou menos assim:

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/jtossbc3e8iqzgaif8oa.png)

Por fim, na hora de devolver as alterações para a branch remota (aka. fazer um push), será necessário usar o parâmetro -f para reescrever o histórico. Ficará assim ``git push -f``

Mais informações legais podem ser encontrados lá na documentação oficial do git (e foi da [documentação dos branchs](https://git-scm.com/book/en/v2/Git-Branching-Branches-in-a-Nutshell#:~:text=A%20branch%20in%20Git%20is,Note) que eu peguei as imagens usadas)

*Nota: Sendo bem preciosista, os novos *commits* não serão A,B,C mas *commits* com mesmas alterações e mensagens de *commit*. Porém, o hash será diferente já que o **pai** do *commit* é diferente.