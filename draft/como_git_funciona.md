---
title: O que são commits do git?
categories: [git]
tags: [Ferramentas, git]
---
Iniciando nosso [curso de Git](). Nosso começo vai passar por alguns conceitos teóricos que vamos sempre voltar a revisar ao longo de todo o conteúdo. Mesmo que os conceitos não fiquem tão claros nesse primeiro momento, não desista quando formos vendo as partes práticas, a teoria fará mais sentid. Vamos começar entendendo, em linhas gerais, como o Git funciona. Para isso, é necessário entender que o Git é uma ferramenta para controle de versão (do ingês VCS, *Version Control System*). Mas, o que é uma ferramenta de controle de versão?

Ora, uma ferramenta de controle de versão é uma ferramenta que controla a versão (duh). Esse tipo de ferramenta permite que o usuário salve um documento, verifique as alterações feitas, veja quem fez as alterações, coloque marcações (tags) nessas versões, reverta alterações feitas e faça vários outros tipos de controle. Essas funcionalidades já são muito úteis quando você trabalha sozinho, mas são fundamentais quando você trabalha com várias outras pessoas.

No caso do Git, esse tipo de controle de versão é feito através de pequenas e pontuais alterações chamadas commits. Não existe uma tradução perfeita para essa palavra. Um commit é como uma fotografia do seu documento, representando-o em um dado momento. Ou seja, cada vez que você faz um commit, está salvando uma fotografia do documento.

No entanto, essa fotografia precisa ser feita de forma inteligente, senão acabará ocupando muito espaço em disco. Vamos ver um exemplo. Imagine que você tem o seguinte documento com sua fotografia inicial:

```
AAAA
```

Então a adiciono uma outra linha e faço uma fotografia:

```
AAAA
BBBB
```

Depois disso, eu adiciono uma nova linha e faço uma terceira fotografia:

```
AAAA
BBBB
CCCC
```

Após tudo isso, eu teria três fotografias (uma para cada linha). Se cada letra ocupar 1 byte, eu teria um documento que ocupa 12 bytes e 3 fotografias que ocupam 4, 8 e 12 bytes respectivamente. Ou seja, 12 bytes de dados e 24 bytes de fotografias.

Considerando que um projeto de verdade terá muitos e muitos kilobytes, não há condições de tirarmos essas fotografias completas dos dados.

E é aí que vem o pulo do gato. Pulo do gato lembra gato. Antes de seguirmos, vamos fazer uma pausa para admirar o Apollo. 

![Alt apollo encarand](/images/apollo_encarando.jpeg)

Após admirarmos o Apollo, vamos seguir no pulo do gato. Quando o git faz um commit, ele não salva todo o documento, ele salva apenas o que mudou desde o último commit.

Considerando o exemplo anterior, o primeiro commit teria a informação `+aaaa`, o segundo teria `+bbbb` e o terceiro `+cccc` (símbolo de + serve apenas para indicar que adicionamos linhas no fim). Dessa forma, cada commit ocupa 5 bytes, totalizando 15 bytes, ao invés dos 24 bytes da forma anterior.

E é assim que o git funciona. Ele pega uma fotografia do seu documento. Vai lendo os commits que contém adições e remoções do documento até chegar na versão final do mesmo. 

---
Hora do exercício.

A) Escreva com as suas palavras o que é uma ferramenta de controle de versões.

B) No git, cada commit gera um *hash*. Explique com suas palavras o que é um *hash*.