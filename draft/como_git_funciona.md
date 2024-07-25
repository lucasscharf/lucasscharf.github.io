---
title: O que são commits do git?
categories: [git]
tags: [Ferramentas, git]
---

Vamos iniciar [curso de Git](/posts/introducao_curso_git). Nesta primeira etapa, vamos passar por alguns conceitos teóricos. Esses conceitos vão sempre nos acompanhar, e eu recomendo que, de tempos em tempos, você volte para revisar este conteúdo inicial. Mesmo que os conceitos não fiquem tão claros nesse primeiro momento, não desista. À medida que formos explorando as partes práticas, a teoria fará mais sentido.

Vamos começar entendendo, em linhas gerais, como o Git funciona. Para isso, é necessário entender que o Git é uma ferramenta para controle de versão (do inglês VCS, *Version Control System*). Mas, o que é uma ferramenta de controle de versão?

Ora, uma ferramenta de controle de versão é, como o nome sugere, uma ferramenta que controla a versão (duh). Esse tipo de ferramenta permite que o usuário salve um documento em diferentes estados ao longo do tempo, verifique as alterações feitas, veja quem fez as alterações, adicione marcações (ou tags) a essas versões, reverta alterações e realize muitos outros tipos de controle.

Essas funcionalidades já são extremamente úteis quando você está trabalhando sozinho, pois elas ajudam a manter um histórico organizado do seu projeto e permitem que você volte atrás em caso de erros. No entanto, essas ferramentas se tornam ainda mais fundamentais quando você trabalha com várias outras pessoas em um projeto colaborativo.

No caso do Git, esse tipo de controle de versão é feito através da persistência de pequenas e pontuais alterações chamadas **commits**. Não existe uma tradução perfeita para essa palavra. Um commit é como uma fotografia do seu arquivo, representando-o em um dado momento. Ou seja, cada vez que você faz um commit, está salvando uma fotografia do seu arquivo.

No entanto, essa fotografia precisa ser feita de forma inteligente, senão acabará ocupando muito espaço em disco. Vamos ver um exemplo de uma proposta de fotografia **não inteligente**. Imagine que você tem o seguinte arquivo com sua fotografia inicial:

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

Considerando que um projeto de verdade terá muitos e muitos kilobytes, não há condições de sermos eficientes e seguirmos essa abordagem de fazer essas fotografias completas dos dados.

E é aí que vem o pulo do gato. Pulo do gato lembra gato. Gato lembra Apollo. Antes de seguirmos, vamos fazer uma pausa para admirar o Apollo. 

![Alt apollo encarand](/images/apollo_encarando.jpeg)

Após admirarmos o Apollo, vamos seguir no pulo do gato. Quando o Git faz um commit, ele não salva todo o arquivo, ele salva apenas o que mudou desde o último commit.

Considerando o exemplo anterior e supondo que existe a anotação `+` que ocupa um byte e indica que adicionamos uma linha no fim do arquivo. Temos que o primeiro commit tem a informação `+aaaa`, o segundo tem `+bbbb`, e o terceiro tem `+cccc`. Dessa forma, cada commit ocupa 5 bytes, totalizando 15 bytes, ao invés dos 24 bytes da forma anterior.

E é assim que o Git funciona. Ele armazena os commits contendo as informações das alterações feitas desde o último commit. Dessa forma, o arquivo registra as adições e remoções de conteúdo de forma incremental, sem precisar salvar uma cópia completa a cada mudança.

O Git realiza uma gestão eficiente das alterações por meio da manipulação inteligente de commits, adição de metadados relevantes (como autor, data, e mensagens descritivas), e agrupamento de informações em repositórios organizados. Isso nos permite trabalhar de maneira colaborativa, garantindo que todos os envolvidos em um projeto tenham uma visão clara e controlada do histórico de mudanças. 

---
Hora do exercício.

A) Escreva com as suas palavras o que é uma ferramenta de controle de versões.

B) No git, cada commit gera um *hash*. Um *hash* é uma função matemática que, dado um valor de entrada, retorna um único valor de saída. Pesquise e explique com suas palavras o que é um *hash* e onde que ele é usado no git.

C) Cada commit feito tem uma série de informações extras sobre o commit. Essas informações são chamadas de metadados. Por exemplo, no caso de uma fotografia alguns metadados são: horário da fotografia, resolução da imagem, informações sobre a câmera, lente utilizada. Pesquisa e escreva quais são os metadados existentes em um commit?