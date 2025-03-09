---
title: Interpretação e Resumo sobre o texto State of Valhalla Parte 3 The JVM Model
categories: [Java]
tags: [Java, Resumo]
---

# Introdução

Já chegou, o carnaval e ela não desfilou... Poderia ficar triste, poderia ficar chatedo, poderia ter desistido. Porém, assim como [Joseph Climber](https://www.youtube.com/watch?v=d88x4qZ_zKU), eu não me entrego. Por causa disso, vamos discutir a [parte 3](https://openjdk.org/projects/valhalla/design-notes/state-of-valhalla/03-vm-model) do longo, comprido, gostoso, sexy e voluptuoso texto State of Valhalla de [Brian Goetz](https://www.linkedin.com/in/briangoetz/) que fala sobre esse projeto que é do balacobaco. Caso você queira, você pode ler a [parte 1](/posts/road-for-valhalla/) e a [parte 2](posts/modelo-objetos-java/). O texto originall encontra-se [aqui](https://openjdk.org/projects/valhalla/design-notes/state-of-valhalla/03-vm-model).

No texto de hoje, o Valhalla é analisado através da ótica Máquina Virtual Java (JVM). Para mim, esse texto será o mais difícil de ler e interpretar pois não sou especialista em JVM e sinto que posso fazer adaptações equivocadas. Por isso, vou tentar fazer um texto mais tradução e menos revisão, resumo ou interpretação. Peço desculpas de antemão e aceito revisões.

# Separação entre JVM, linguagem Java e ecossistema Java
Antes de entrar no nosso texto, vale o entendimento que Java não uma "coisa". Mas pode ser dividido em 3 partes: A linguagem Java, A JVM e o ecossistema de bibliotecas e serviços criados em Java.

A Linguagem Java: Define a sintaxe, as regras e os paradigmas que os desenvolvedores utilizam para escrever código. Características como classes, interfaces e tipos de dados fazem parte da linguagem em si.

A JVM (Java Virtual Machine): É a plataforma de execução do código Java compilado. Ela transforma os arquivos `.class` gerados pelo compilador em instruções de máquina compatíveis com diferentes arquiteturas. A JVM não entende apenas Java, mas também outras linguagens que compilam para bytecode, como Kotlin, Scala e Groovy.

O Ecossistema Java: Envolve todas as bibliotecas, frameworks e ferramentas criadas em torno da linguagem e da JVM. Isso inclui desde APIs padrão, como java.util e java.nio, até frameworks populares como Spring, Quarkus e Micronaut.

Uma das maiores magias e forças do Java está na possibilidade de eu escrever bibliotecas em Java e utilizá-las junto com a linguagem Kotlin, por exemplo. Dessa forma, toda linguagem que roda na JVM acaba tendo um grau de interoperabilidade entre si. Dessa forma, quando a JVM e o ecossistema Java melhoram através do Valhalla, todo mundo melhora.

# Value classes e Value Objects
Na [parte 1](/posts/road-for-valhalla/), exploramos os conceitos de value objects (e, por consequência value classes). Aqui, vamos estudar como isso é aplicado dentro da JVM. 

Antes do projeto Valhalla, todos os objetos tinha um único tipo de identidade de objeto. Agora, é possível definir uma flag (`ACC_VALUE`) que vai definir se essa determinada instância possui identidade (chamado de identity object) ou se vem de um value class. Essa flag muda o comportamento pois se a instância possui identidade, ela ganha a interface `java.lang.IdentityObject`, caso contrário ela recebe a interface `java.lang.ValueObject`. 

Algumas classes também podem ser marcadas com a flag `ACC_PRIMITIVE`. Essas classes são efetivamente "apenas valores", fora de qualquer objeto. Esses casos especiais são chamados de "classes primitivas" e foram bem tratadas na [parte 2](/posts/modelo-objetos-java/). 

Assim como objetos que possuem identidade, os Value Objects são passados por referência.Tendo acesso a essas informações, a JVM sabe quais objetos possuem identidade e pode otimizar melhor o o layout de memória.

# Portardores e descritores

Os descritores de tipo da JVM ([JVMS 4.3.2](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.3.2)) usam letras para denotarem o tipo de dados (`I` para inteiro, `J` para long, `B` para byte, etc...). Além disso, tem o caracter `[`que serve para arrays e `L`serve para referências. Uma forma simples de ver esses descritores é através do código abaixo:

```java
public class Main
{
	public static void main(String[] args) {
		System.out.println(new long[4].toString());
		System.out.println(new int[4].toString());
	}
}
```

Que teve a seguinte saída:

```bash
[J@659e0bfd
[I@2a139a55
```

Ele diz que temos um array (`[`) de longs (`J`) que está no endereço (`659e0bfd`). Para entender mais sobre isso, leia a documentação do método [toString](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#toString%28%29).

Os nove descritores básicos (os 8 tipos básicos de Java e referência de objeto) são mapeados em 5 portadores (*carries*) - `int`, `long`, `float`, `double` e referência de objeto. Esses portadores representam os tipos que podem ser armazados na pilha ou em espaços de variáveis (os demais valores como `char` e `boolean` usam o portador de `int`). 

Para descrever os valores puros de classes primitas, foi criado um novo descritor primitivo `Q`. Que possui a mesma estrutura de um descritor de referência (`L`). De tal forma que portadores do `Q` e `L` podem operar sobre o mesmo bytecode. Apesar da mestra estrutura, os valores puros possuem grandes diferenças em relação aos tipos por referência:

* Referências a value objects, como todas as referências a objetos, podem ser nulas, enquanto valores puros não podem. Isso permite que um tipo `Q` “funcione como um int”, sem que a JVM precise representar um valor nulo em um inteiro de 32 bits.

* Carregamentos e armazenamentos de referências são atômicos entre si, mas carregamentos e armazenamentos de valores puros grandes podem ser partidos em condições de concorrência, como acontece com long e double.

* A JVM carrega classes mencionadas em descritores `Q` mais cedo do que aquelas mencionadas em descritores `L`.

* Estruturas de dados não podem ser circulares se forem vinculadas via descritores `Q`: uma classe `C` não pode referenciar `QC` (valor puro `C`) em seu layout, direta ou indiretamente. Para evitar ciclos, é necessário usar referências.

* Se o operando do bytecode [checkcast](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.checkcast)(verificação se um determinado objeto é de um determinado tipo) for um tipo de referência, a classe só será carregada se o operando não for nulo. Mas, se for um tipo `Q`, a classe será carregada imediatamente, independentemente do valor do operando. Isso afeta a ordem de processamento de erros e garante um comportamento mais consistente no carregamento de tipos `Q`.

* Nomes de descritores `L` seguem restrições do carregador de classes. Já nomes de descritores `Q` podem não seguir, caso sejam resolvidos na fase de preparação. (Como o carregamento dos tipos `Q` é antecipado, essas restrições podem ser verificadas quando os métodos são preparados. Implementações da JVM que resolvem descritores `Q` mais tarde precisam aplicar restrições do carregador de classes sobre eles.)


# 