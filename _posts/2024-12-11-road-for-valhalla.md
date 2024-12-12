---
title: Opinião sobre o texto State of Valhalla Part 1 The Road to Valhalla
categories: [Java]
tags: [Java,Resumo]
---

# Introdução
Seguindo na linha sobre fazer um ano mais leve e compartilhar as coisas que estou estudando, vou resumir o texto [State of Valhalla Part 1: The Road to Valhalla](https://openjdk.org/projects/valhalla/design-notes/state-of-valhalla/01-background). Note que os textos serão bem parecidos porque o meu texto é totalmente derivado do texto original. Todos os créditos e coisas boas devem ser dados ao autor e não a mim. Vale ressaltar que o texto foi escrito em dezembro de 2021, desde então, algumas coisas já aconteceram e o projeto evoluiu.

Assim como o vovô Simpson que já dizia chamava a "Grande Guerra" de "Primeira Guerra Mundial", o autor [Brian Goetz](https://bsky.app/profile/briangoetz.bsky.social) já avisa que esse texto terá outars partes. 

O texto explica o que é o [projeto Valhalla](https://openjdk.org/projects/valhalla/) dando uma ideia por cima da solução, qual a origem dos problemas que ele quer resolver e termina fazendo considerações sobre a parte de [generics do Java](https://www.baeldung.com/java-generics).

# Conceitos importantes
Antes de explicarmos o assunto, é necessário entender dois conceitos importantes:
A) Identidade de objetos
B) Como um objeto é armazenado na memória

Sem esses conceitos, o funcionamento e os benefícios do Projeto Valhalla não serão evidentes.

## Identidade de Objetos

A identidade estabelece que **TODO** objeto Java, assim como todos os ***arrays***, possui um identificador único. É por meio desse identificador que o Java sabe quem é quem, consegue realizar conversões de tipo, sabe quantos endereços de memória um objeto ocupa, entre outras funcionalidades. Note que objetos do tipo `Integer` e `Double` possuem identificadores, mas os tipos primitivos `int` e `double` não possuem. Por conta desse conceito de identidade e devido a algumas otimizações, ocorrem comportamentos estranhos, como o apresentado no método a seguir:

```java
public static void main(String[] args) {
    Integer grande = 10000;
    Integer grande2 = 10000;
    
    Integer pequeno = 23;
    Integer pequeno2 = 23;
    
    System.out.println(grande.equals(grande2));
    System.out.println(grande == grande2);
    
    System.out.println(pequeno.equals(pequeno2));
    System.out.println(pequeno == pequeno2);
}
```

Que tem o retorno: 
```
true
false
true
true
```
A identidade não é algo ruim por si só, mas é um conceito que, se não for compreendido, pode gerar confusão. Devido às implementações internas do compilador, os objetos `pequeno` e `pequeno2` acabam sendo o mesmo objeto (possuindo o mesmo endereço de memória), enquanto `grande` e `grande2` são objetos diferentes (possuindo endereços de memória distintos).

## Como um objeto é armazenado em memória

Todo objeto Java possui um cabeçalho (em inglês, *header*). Esse cabeçalho contém informações sobre a identidade do objeto, entre outros dados, além do seu conteúdo. Quando lidamos com arrays, estes também possuem seus próprios cabeçalhos e armazenam um conjunto de ponteiros que apontam para o conteúdo dos objetos, um para cada objeto, para ser mais preciso. A figura abaixo ilustra, de maneira simplificada, a estrutura de um array do objeto `Points`.

![array pré vahalla image](/images/array-pre-valhalla.png)

Dessa forma, se você deseja acessar o ``Point`` de número 3, é necessário pegar a primeira posição do array na memória, deslocar três posições para obter o ponteiro do seu `Point` e, em seguida, acessar novamente a memória para obter o dado. Esse projeto original, concebido há décadas, fazia sentido porque o custo de acesso à memória era semelhante ao custo de uma operação matemática; separar o conteúdo do array em “ilhas de objetos” permitia maior flexibilidade na construção de cada objeto.

No entanto, atualmente, o custo de acesso à memória é muito mais elevado do que o custo das operações aritméticas. Desse modo, o Java perde muita performance devido a esse acesso indireto ao conteúdo da memória.

# O que é o Projeto Valhalla

O Projeto Valhalla pretende criar um tipo especial de objeto chamado **value object**. Esse tipo de objeto abre mão de sua identidade e de seu cabeçalho. O ganho imediato pode ser observado no exemplo do array. Com o Projeto Valhalla, o conteúdo do array ficaria assim:

![image](/images/array-pos-valhalla.png)

Sem indireções e sem cabeçalhos. Esse formato de memória é mais “achatado” ou possui menos indireções (tornando a aplicação mais eficiente no uso de cache) e também é mais denso (tornando a aplicação mais eficiente no uso de memória). O autor também aborda a possibilidade de remover as indireções nas chamadas de método. Em muitos casos, ao passar um `Point` para um método, apenas a referência para esse objeto é passada, e todos os acessos a esse objeto são feitos por meio dessa referência. Dependendo do caso, após o Projeto Valhalla, passar um `Point` poderia ser feito transmitindo apenas os valores do conteúdo desse objeto.

Além disso, o projeto abre espaço para converter certos objetos internos da linguagem (por exemplo, `Optional`, `LocalDateTime`) em value objects, além de melhorar a utilização de genéricos em Java.

# Quais são as origens do problema

Os problemas começaram lá em 1995, quando alguns de vocês não haviam nascido ou ainda estavam comendo terra. A proposta do Java era que **tudo** fosse um objeto. Entretanto, isso gerava um grande problema de performance para operações numéricas realizadas por meio de objetos (`Integer`, `Double`, `Character`, etc.). Por essa razão, foram desenvolvidos os tipos primitivos (`int`, `double`, `char`, etc.) e os `arrays`. Eles não são objetos, mas sim elementos “mágicos”, com um comportamento diferenciado dentro da JVM. Junto com esses tipos primitivos, também foram criadas as operações de encaixotamento (autoboxing), que fazem a conversão automática dos tipos primitivos para os objetos. Ou seja, quando você escreve algo do tipo:

```Java
Integer numero = 9;
```

A JVM transforma isso em:

```Java
Integer numero = Integer.of(9);
```

O que internamente tem o seguinte comportamento:

```java
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

Apesar da volta e do custo, esse processo de autoencaixotamento permitiria ao desenvolvedor trabalhar com Integers e ints como se fossem a mesma coisa. Certo? Errado. Com a vinda dos generics baseados feita com apagamento de tipos (do inglês, *type erasure*), passamos a ter mais um monte de comportamentos inesperados. Como o demonstrado nesses métodos:

```java
public static <T> boolean retornaTrue(T item, T[] set) {
  return false;
}

public static void main(String[] args) {  
  Character character = 'a';
  Character[] characters = new Character[] { 'a', 'b', 'c' };
  retornaTrue(character, characters); //1
  
  char ch = 'a';
  char[] chars = new char[] { 'a', 'b', 'c' };
  retornaTrue(ch, chars); //2
}
```

A chamada indicada pelo número 1 funciona, enquanto a indicada pelo número 2 não compila. Isso acontece porque, internamente, o Java espera que as chamadas genéricas sejam feitas com objetos, enquanto os tipos primitivos são entidades especiais que não se comportam como objetos.

A situação se complica com lambdas, pois estes utilizam muitos genéricos. Tentou-se criar soluções específicas para contornar o problema com `int`, `double` e `long`, o que resultou em funções pouco naturais, como `IntPredicate` e `IntToLongFunction`. À medida que mais recursos são adicionados, o custo de manter esses tipos “mágicos” que não são objetos cresce, forçando os projetistas de bibliotecas a darem cada vez mais voltas para contornar esse sistema problemático de tipos.

# E os *generics*?

Os *generics* são um dos pilares fundamentais do Java. Sem eles, nosso trabalho seria muito mais árduo. Porém, uma decisão tomada lá atrás estabeleceu que tipos genéricos só funcionariam com entidades que possuíssem identidade. Essa escolha traz consequências negativas, como a necessidade de usar `List<Integer>` quando, na verdade, gostaríamos de um `List<int>`. Mas se o Projeto Valhalla abre mão da identidade, o que acontece com essa parte dos *generics*?

Para resolver esse problema, foi criado um plano em duas fases: a introdução de [generics universais](https://openjdk.org/jeps/8261529) juntamente com [generics especializados](https://openjdk.org/jeps/218). Na primeira fase, espera-se que os *generics* possam ser utilizados com tipos primitivos, *value objects* e outros elementos sem identidade. Na segunda fase, permitirá-se que classes e métodos da JVM tratem classes e métodos genéricos de forma especial, mantendo as definições e capacidades que tornaram os *generics* tão importantes e úteis para nós.

# Considerações finais

O Projeto Valhalla é algo extraordinário. Ele permitirá escrever aplicações Java menores e mais rápidas. Você, como desenvolvedor, obtém esses benefícios sem precisar fazer praticamente nada além de atualizar sua JVM. Além disso, o projeto possibilitará a criação de seus próprios *value objects*, o que facilita, por exemplo, retornar dois valores em uma chamada de função sem ter que criar objetos auxiliares ou recorrer aos [*records* do Java](https://www.baeldung.com/java-record-keyword).

Este foi o primeiro texto escrito na minha [nova abordagem](/posts/novos-rumos). À medida que eu for lendo mais sobre o assunto, pretendo continuar escrevendo. Meus passos podem se tornar mais lentos, porém mais profundos.