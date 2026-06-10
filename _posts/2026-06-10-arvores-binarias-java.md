---
title: Como funciomam árvores binárias em Java
categories: [Java]
tags: [Java]
---

# Introdução

Árvores binárias. Uma estrutura de dados tchutchuca porém tão diferente do usual que deixa muita gente confusa. No post de hoje vamos aprender um pouco mais sobre elas e propriedades marotas. Depois vamos ver sobre árvores binárias de busca e criar nossa própria árvore.

# O que é uma árvore

Quando a gente começa a programar, começamos com estruturas de dados não hierárquicas. Lista, pilha e fila. Tudo bem comportadinho, um elemento atrás do outro. A árvore quebra essa ideia. Ao invés de cada elemento apontar para o próximo, cada elemento aponta seus filhos. Não é como se fosse algo que lembra uma árvore do mundo real, mas é o que tem para hoje. Sério, quem olharia para a imagem abaixo e diria "realmente parece uma árvore"?

![Árvore genérica](/images/arvore-generica.png)

Cada bolinha nessa estrutura é chamada de nó. O nó mais de cima (que não tem nenhum risco apontado para ele) é chamado de raiz. Enquanto os nós de baixo (que nenhuma seta aponta) é chamado de folha. Dizemos que um nó é pai de outro quando sai uma seta apontando dele para outro nó.

Aqui entra a primeira característica legal: cada nó em uma árvore é a raiz de uma sub-árvore. Isso se mantém para todo tipo de árvore. Essa característica de a estrutura se repetir dentro dela mesma é o que chamamos de recursão. Guarde isso com carinho porque praticamente todo código de árvore que a gente vai escrever vai ser recursivo.

# A estrela do show: árvore binária de busca

Uma árvore sozinha é só um monte de nó espalhado com alguma conexão entre pais e filhos. O que deixa ela útil de verdade é colocar uma regra de organização. A regra mais famosa é a da árvore binária de busca. A regra é a seguinte: para qualquer nó, tudo que for menor vai para a esquerda e tudo que for maior vai para a direita.

É bobo mas poderoso. Quando você procura um valor, a cada nó que você visita dá para descartar metade do que sobrou. É o mesmo princípio de procurar uma palavra no dicionário (só os da terceira idade se identificaram agora): você não lê página por página, você abre no meio e decide se vai para frente ou para trás. Numa árvore balanceada, procurar entre um milhão de elementos custa mais ou menos vinte comparações. Não é magia é tecnologia.

Vamos ver o exemplo de uma árvore balanceda. Essa árvore tem os seguitnes valores: 1, 3, 4, 7, 8, 10, 13 e 14. Repare que todo nó respeita a regra: menores à esquerda, maiores à direita. Se eu quero saber se o número 7 está na árvore eu vou descendo: 1, 3, 6 e 7.

![Árvore binária](/images/arvore-binaria-busca.png)

# Representando a árvore em Java

Chega de papo, vamos para o código. Para representar um nó a gente precisa de três coisas: o valor guardado, uma referência para o filho da esquerda e uma referência para o filho da direita. Em Java isso fica:

```java
public class No {
    int valor;
    No esquerda;
    No direita;

    No(int valor) {
        this.valor = valor;
    }
}
```

Quando `esquerda` ou `direita` são `null`, quer dizer que ali não tem filho. Vale notar que um nó não sabe se ele é raiz.

# Inserindo elementos

Inserir é colocar o valor no lugar certo respeitando a regra da nossa árvore binária. A gente começa na raiz e vai descendo: se o valor novo é menor que o nó atual, segue para a esquerda; se é maior, segue para a direita. Quando chega num espaço vazio, é ali que o elemento mora. Como a estrutura é recursiva, conseguimos fazer algo bem eficiente e simples para a inserção:

```java
public void inserir(No no) {
  if (valor < no.valor) {
    if(esquerda == null)
      esquerda = no;
    else
      esquerda.inserir(no);
  } else {
    if(direita == null)
      direita = no;
    else
      direita.inserir(no);
  }
}
```

Repare que sempre que a gente cai num `null`, a gente coloca o nó naquela posição. Esse retorno é o que reconecta o filho recém-criado ao pai dele. Aqui estamos assumindo que não tem repetição, mas você pode mudar esse comportamento dependendo do que precisar.

# Buscando um valor

A busca segue exatamente a mesma lógica da inserção, só que ao invés de criar um nó, a gente responde se achou ou não:

```java
public boolean contem(int valor) {
  if(this.valor == valor)
    return true;
  if(this.valor > valor && esquerda != null)
    esquerda.contem(valor);
  if(this.valor < valor && direita != null)
    return direita.contem(valor);
  return false;
}
```

Se o valor é o meu, eu retorno meu valor. Se o valor é menor, procuro no filho da esquerda. Se for maior, procura no da direita. Caso o filho específico não exista, retorna `false`.

# Percorrendo a árvore

Em vetor e lista a ordem de leitura é óbvia, você vai do começo ao fim. Numa árvore existem várias formas de visitar todos os nós, e cada uma serve para uma coisa.

Da esquerda pra direita: visita o filho da esquerda, depois o nó, depois a direita. Numa árvore de busca isso devolve os valores em ordem crescente, o que é maravilhoso.

Da direita para esquerda: primeiro o filho da direita, depois o do nó e então o da esquerda. Como o esperando, retorna a ordem decrescente.

Do meio para baixo. Você primeiro pega o nó, depois o filho da esquerda e da direita. Útil para copiar a árvore ou salvar a estrutura dela.

Vamos ao código do percurso da esquerda pra direita que é mais usado:

```java
private void percorrer() {
    if(esquerda != null)
      esquerda.percorrer(no);
    System.out.println(no.valor);
    if(direita != null)
      direita.percorrer(no);
}
```

Bricando com a posição do `println` e dos nós, você consegue percorrer a árvore da forma que for mais conviente.

# O elefante em na sala de cristais

![Elefante em uma árvore](/images/elefante-arvore.jpg)

Nem tudo são flores. Para a árvore binária ser uma árvore binária, é necessário que você já insira os dados na ordem certa. Caso contrário, você terá algo com sabor árvore. Por exemplo, inserindo os valores 1, 2, 3, 4, 5 nessa ordem, cada um vai ser maior que o anterior e todos vão parar na direita. O resultado é uma árvore que virou uma lista, comprida e torta. Aí já era, a busca volta a ser lenta igual percorrer uma lista comum.

Existem as árvores auto-balanceadas, que se reorganizam sozinhas conforme você mexe nelas. As mais famosas são a árvore [AVL](https://pt.wikipedia.org/wiki/%C3%81rvore_AVL) e a [árvore rubro-negra](https://pt.wikipedia.org/wiki/%C3%81rvore_rubro-negra) (red-black). Inclusive, quando você usa um `TreeMap` ou um `TreeSet` no Java, é uma árvore rubro-negra que está trabalhando escondida ali embaixo.

## Conclusão

Árvore binária de busca brilha quando você precisa manter dados ordenados e ao mesmo tempo inserir, buscar e remover de forma rápida. Se você só precisa de acesso por chave e não liga para ordem, uma tabela hash provavelmente resolve melhor. No dia em que a ordenação importar, a árvore vai estar lá esperando.

Esse post foi a primeira parte (acho que vou fazer uns quatro posts) estudando árvores. Os próximos serão sobre a árvore AVL, árvore rubro negra e o que o Java já nos traz pronto (aka. como você usar isso ao invés de ter que ficar implementado tudo na mão).