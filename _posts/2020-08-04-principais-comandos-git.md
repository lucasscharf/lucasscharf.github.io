---
title: Alguns dos principais comandos do git
categories: [git]
tags: [Ferramentas, git]
---

Seja bem vindo a mais uma ~gentil~ introdução ao git e hoje nós vamos conhecer alguns dos principais comandos que nós usamos. 
Antes de começar, vamos lembrar do nosso planejamento de posts:

* [O que é o GIT e como usar](/posts/introducao-git/)
* [Principais comandos do git](/posts/principais-comandos-git/) **⟵ você está aqui**
* [Como fazer um rebase de presença](/posts/como-fazer-rebase/)
* [Como escrever um README.md](/posts/como-escrever-readme/)
* [Boas práticas para utilizar o git](/posts/boas-praticas-git/)



# Quais comandos vamos estudar?
Atualmente, o git possui 137 comandos e uma quantidade infinita de parâmetros, configurações, etc...
Infelizmente, não dá para falar tudo em menos de mil palavras, então selecionei os mais comuns para falar sobre. 

Caso você queira se aprofundar mais, é só dar uma olhada na [documentação oficial](https://git-scm.com/docs).

Os comandos que vamos estudar são: clone, add, reset, commit, checkout, push e pull.

Então, vocês estão prontas crianças?

![Alt capitão bob sponja](https://dev-to-uploads.s3.amazonaws.com/i/t1wxtlny670khcy6oc5x.jpeg)

#Clone
O primeiro comando que nós temos é o **clone**. Ele tem a função de copiar o repositório remoto (que geralmente está armazenado no [github](https://github.com/), [bitbucket](https://bitbucket.org/) ou [gitlab](https://gitlab.com/explore)) para a sua máquina. A forma mais comum desse comando é:

```
git clone <URL>
```

E o conteúdo da URL é o caminho para o repositório remoto.

Por exemplo:
```
git clone https://github.com/lucasscharf/blog-posts-code
```

Fazendo isso, o git vai criar um diretório com o nome igual ao do repositório (no caso do exemplo será blog-posts-code) e copiar todo o conteúdo do repositório remoto para o local.

## Add
Você se lembra lá do outro post que nós falamos sobre as áreas do git? Se não lembra, [corre lá](https://dev.to/lucasscharf/uma-gentil-introducao-ao-git-e-seus-comandos-2lf) que eu te espero.

O comando **add** tem a seguinte cara:

```
git add <caminho para o diretório ou arquivo>
```

Ele faz com que os arquivos que não estavam na área de estagiamento passem a fazer parte da área da estagiamento*. 

O git é extremamente versátil na hora de indicar o caminho para o **add**. 
Abaixo temos alguns exemplos:

```properties
#Adiciona um arquivo
git add AlgumaClasse.java

#Adiciona um diretório*
git add AlgumDiretório

#Adiciona um diretório* com uma barra
git add AlgumDiretório/

#Adiciona todos os arquivos e diretórios* que estão abaixo do diretório local
git add .

#Adiciona o conteúdo interno de um diretório (e por consequência o diretório)
git add AlgumDiretório/Algum_conteúdo_interno

#Adiciona vários arquivos e diretórios* num mesmo comando
git add Classe1.java Classe2.java Diretório3

#Adiciona arquivos com os caracteres coringa
git add *.java 
```

## Reset
O **reset** é o irmão gêmeo do mal do **add**. Enquanto o **add** adiciona um diretório na área de estagiamento, o **reset** tira.

![Alt Paola Brach](https://dev-to-uploads.s3.amazonaws.com/i/hdizizj29j687vvh4i12.jpg)

Além disso, o **reset** tem uma versão anabolizada que é o ``reset --hard``. Que não somente retira os arquivos da área de estagiamento como também excluí os mesmos.

A sintaxe do comando de **reset** é:

```
git reset [--hard] [commit]
```

O no commit, você coloca o **hash** do commit. Você também pode utilizar atalhos commit ``HEAD~1`` para pegar o último commit.

## Commit 
Após usarmos o ``git add`` para levar as mudanças do diretório de trabalho para a área de estagiamento, nós usamos o comando **commit** para levar os arquivos para o repositório local. A sintaxe do commit é mais fácil do que andar pra frente:

```
git commit
```

E é só isso. Após o enter, o git abrirá o seu editor de texto favorito para você escrever a mensagem de commit. 
É EXTREMAMENTE importante escrever uma mensagem ou o *commit* não será realizado.

## Branch
O comando ``git branch`` permite criar, listar ou deletar as *branchs***. Suas três principais formas são:

```
git branch
git branch <nome_da_branch>
git branch -D <nome_da_branch>
```

O primeiro comando lista todas as *branchs* existentes no repositório local. 
O segundo vai criar uma *branch* com o nome que nós passamos por parâmetro.
E o terceiro vai deletar a *branch* com o nome passado por parâmetro. 

É importante notar que os nomes de branch não podem ter espaços.

#Checkout 
O **checkout** serve para movimentar entre diferentes *branchs* de desenvolvimento. A sintaxe é:

```
git checkout <nome_da_branch>
```

Supondo que tenhamos uma *branch* chamada meu_exemplo, então, nós podemos usar o comando:

```
git checkout meu_exemplo
```

Todos os *commits* que estavam salvos na *branch* anterior sumirão e, no lugar, vão surgir os commits da *branch* meu_exemplo. E o nosso diretório de trabalho vai se referência à *branch* meu_exemplo.

## Push
O *push* é a última parte do processo. Ele envia as  alterações do repositório local para o repositório remoto.*

A sintaxe é:

```
git push
```

## Pull

O **pull** é um comando* bem simples que atualiza o diretório local com o conteúdo do repositório remoto.
Esse é outro comando tão fácil quanto andar pra frente. A sintaxe é:

```
git pull
```

# Considerações

Foi uma tarefa difícil escolher apenas alguns comandos para listar. É bem provável que eu faça uma parte 2 e uma parte 3 para esses comandos, mas isso é algo para o futuro.
Foi ainda mais difícil tentar não falar todas as coisas legais que o git faz para executar esses comandos. Mas é isso. No próximo post, vamos falar sobre um assunto que eu gosto muito que é como fazer um rebase de presença.

*Isso é uma meia verdade. Nos comentários eu explico um pouco mais detalhado sobre o que acontece. É possível seguir uma vida tranquila sem olhar para os comentários.

\*\* Pensem numa *branch* como uma linha de desenvolvimento que tem todos os *commits* feitos.
