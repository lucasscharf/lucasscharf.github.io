---
title: Interpretação e Resumo sobre o texto State of Valhalla Parte 2 Object Model
categories: [Java]
tags: [Java, Resumo]
---

# Introdução

Dando continuidade à parte 2 do texto "State of Valhalla", escrito pelo grandioso [Brian Goetz](https://inside.java/u/BrianGoetz/). Como [já expliquei](/posts/novos-rumos), meu objetivo aqui é apenas ler o texto, fazer alguns resumos e comentários. Todo o crédito pela qualidade do conteúdo vai para o autor original. Você pode ler o texto original [aqui](https://openjdk.org/projects/valhalla/design-notes/state-of-valhalla/02-object-model) e conferir a primeira parte [aqui](/posts/road-for-valhalla).

O texto a seguir discutirá a diferença entre tipos primitivos, tipos por referência, identidade, migração para o Valhalla, *records* e outros assuntos. Há vários conceitos e conteúdos muito interessantes. Recomendo vivamente que você não se limite ao meu resumo e leia o texto original. Ele está realmente muito bom.

# Tipos primitivos e tipos por referência

Você sabe o que é algo [fungível](https://www.tjdft.jus.br/institucional/imprensa/campanhas-e-produtos/direito-facil/edicao-semanal/bens-fungiveis-x-bens-infungiveis)? É um conceito do direito que significa que um bem (A) pode substituir outro bem (B) sem perda de valor. Por exemplo, se tenho uma nota de 10 reais, posso trocá-la por 10 moedas de 1 real e continuei com o mesmo tanto de dinheiro. Esse conhecimento jurídico pode ser útil em uma conversa de bar. Além disso, é um bom ponto de partida para entender que, na linguagem Java, os objetos **não** são fungíveis.

Cada objeto existe em um lugar específico na memória. Se queremos acessá-lo, precisamos saber onde ele está. Veja o exemplo abaixo:

```java
MeuObjeto objeto1 = new MeuObjeto(); // linha 1
objeto1.metodo(); // linha 2
MeuObjeto objeto2 = objeto1; // linha 3
```

Na linha 1, estamos criando uma instância de `MeuObjeto` e atribuindo à variável `objeto1` (um [ponteiro](https://pt.wikipedia.org/wiki/Ponteiro_(programa%C3%A7%C3%A3o), ou seja, uma variável que armazena um endereço de memória) a localização desse objeto. Na linha 2, estamos manipulando o objeto por meio da referência `objeto1`. Em outras palavras, não manipulamos o objeto Java diretamente, mas sim através dessa referência. Tanto é assim que, na linha 3, ao fazer a atribuição `objeto2 = objeto1`, não criamos um novo objeto, apenas compartilhamos a mesma referência entre as duas variáveis.

O oposto dos tipos por referência (objetos e arrays) são os tipos primitivos. Esses tipos são fungíveis, pois não possuem uma identidade propriamente dita. Um valor inteiro 3 pode ser substituído ou copiado por outro valor 3 sem que nada de errado aconteça. Tipos primitivos não possuem identidade ou valor padrão. Ao compará-los com objetos, percebemos que, no modelo do Java, eles funcionam de maneira completamente diferente, conforme demonstrado na tabela abaixo.

| Primitivos                                    | Objetos                                          |
|-----------------------------------------------|--------------------------------------------------|
| Sem identidade (valores puros)                | Possuem identidade                               |
| `==` compara valores                          | `==` compara a identidade do objeto              |
| Integrados à linguagem                        | Declarados em classes                            |
| Não anuláveis (não podem ser nulos)                                 | Anuláveis (pode ser nulos)                                        |
| Sem membros (campos, métodos, construtores)   | Possuem membros (incluindo campos mutáveis)      |
| Sem supertipos ou subtipos                    | Herança de classes e interfaces                  |
| Acessados diretamente                         | Acessados por meio de referências a objetos      |
| Valor padrão é zero                           | Valor padrão é `null`                            |
| Arrays de primitivos são monomórficos         | Arrays são covariantes                           |
| Sujeitos a falhas sob condições de corrida    | Garantias de segurança na inicialização          |
| Convertíveis em objetos polimórficos          | Polimórficos                                     |

Compreender essa tabela significa entender mais profundamente alguns dos comportamentos subjacentes à linguagem Java. Reconhecer que as decisões tomadas para criar tipos primitivos visavam melhorias de desempenho, mas acabaram gerando uma série de outros problemas, é parte essencial para compreender o Projeto Valhalla.


# *Value Classes*
Sendo sincero, não sei exatamente como o termo *value classes* será traduzido. Provavelmente será algo como “classes de valor”, mas receio começar a traduzir o termo e, depois, surgir um nome diferente, deixando meu texto confuso. *Ma bene,* o objetivo do Projeto Valhalla é tornar o modelo de objetos do Java mais denso e mais achatado (se você tem dúvidas sobre o que isso significa, veja o [primeiro post](/posts/road-for-valhalla)). Isso será feito removendo a identidade indesejada dos objetos.

Ao remover a identidade dos objetos de uma determinada classe, muitas coisas acontecem. Vamos explorar brevemente algumas delas:

A) As classes ainda são classes: podem ter valor nulo, possuem métodos, atributos, construtores, garantias de segurança na inicialização e podem ser usadas junto com genéricos.

B) Podem fazer herança com algumas restrições: podem herdar de `Object` ou de classes abstratas que não tenham atributos de instância, inicializadores de instância, construtores com parâmetros ou métodos `synchronized`.

C) As classes são finais (ou efetivamente finais).

D) As comparações com `==` comparam os valores do objeto.

E) Os valores são copiáveis sem dificuldades (no nosso exemplo, a linha 3 criaria 2 cópias de `MeuObjeto`).

Compreender o funcionamento das *Value Classes* e *Value Objects* é um passo muito importante para entender o Projeto Valhalla.

# *Value Records*

Em Java, nós temos os [Java Records](https://medium.com/experiencecode/usando-records-em-java-9afecf7495b30). Records são classes especiais pensadas para armazenar valores. Porém, elas não podem se beneficiar diretamente do Projeto Valhalla, pois ainda possuem identidade. Contudo, se um determinado record estiver disposto a perder sua identidade, então ele pode se tornar um *value record* e usufruir de todos os benefícios. A título de curiosidade, `java.lang.Enums` não atendem aos requisitos, portanto, não teremos enumeradores como *value classes* ou *value enumerators*.
# Migração

As bibliotecas Java que utilizamos, assim como a própria JDK, possuem várias classes candidatas a serem transformadas em *value classes* para obter os benefícios do Projeto Valhalla. Para que uma classe seja considerada uma boa candidata, ela precisa cumprir uma série de características:

- São finais e imutáveis (embora possam conter referências a objetos mutáveis);
- Possuem implementações de `equals`, `hashCode` e `toString` computadas exclusivamente com base no estado da instância, e não em sua identidade ou no estado de qualquer outro objeto ou variável;
- Não fazem uso de operações sensíveis à identidade, como igualdade de referência (`==`) entre instâncias, hash code de identidade das instâncias ou sincronização no bloqueio intrínseco da instância;
- São consideradas iguais exclusivamente com base no método `equals()`, e não pela igualdade de referência (`==`);
- Não possuem construtores acessíveis, sendo instanciadas por meio de métodos de fábrica que não assumem nenhum compromisso quanto à identidade das instâncias retornadas;
- São livremente substituíveis quando iguais, significando que a troca de duas instâncias `x` e `y`, iguais segundo `equals()`, em qualquer computação ou chamada de método não deve produzir nenhuma alteração visível no comportamento.

Chamamos as classes que atendem a essas características de [classes baseadas em valores](https://docs.oracle.com/javase/8/docs/api/java/lang/doc-files/ValueBased.html). O plano é migrar as classes que representam tipos primitivos (`Integer`, `Double`, `Character`, etc.) e as classes baseadas em valores. Para isso, [desde o Java 16 são enviados avisos](https://openjdk.org/jeps/390) para desencorajar a sincronização com classes baseadas em valores. A expectativa é que isso reduza o atrito na migração para o Projeto Valhalla.

Além disso, serão criadas duas interfaces que serão adicionadas de forma implícita às classes: `IdentityObject` e `ValueObject`. Uma classe não pode implementar ambas as interfaces.

# Lidando com operações baseadas em identidade

O Projeto Valhalla remove as identidades, mas não remove as operações originalmente baseadas em identidade. Como fica a situação?

A comparação baseada em `==` não precisa mais depender da identidade de um objeto; em vez disso, ela pode verificar cada um de seus atributos, garantindo que todos os valores sejam equivalentes sob o critério de `==`. Caso essa explicação não esteja clara, basta avisar, e posso tentar reescrevê-la.

Esse princípio pode se estender a outras operações que se baseavam na identidade. Por exemplo, o `System::identityHashCode`, que normalmente retornaria um valor relacionado ao objeto em si, poderia ser implementado de forma a considerar cada atributo, resultando em um valor que reflita o conjunto de seus campos. Da mesma forma, os métodos padrão da classe `Object` (`equals`, `toString` e `hashCode`), bem como a serialização, podem ser adaptados para analisar o conteúdo do objeto, ao invés de sua posição na memória ou referência. A serialização, por exemplo, hoje utiliza a identidade para manter a topologia dos objetos; contudo, seria possível fazê-la adotar um comportamento semelhante ao `==` baseado em valores, preservando não a identidade, mas as informações de cada atributo do objeto. Dessa maneira, todas essas operações, desde o `==` até a serialização, poderiam refletir fielmente o estado interno dos objetos, ao invés de sua identidade.

A sincronização é um caso à parte. Ela será proibida em *value objects*. Se o compilador identificar que estamos fazendo sincronização com um *value object*, e mesmo assim tentarmos usar `synchronized`, será lançada uma `IllegalMonitorStateException`.

# Classes Primitivas (ou classes de tipos primitivos)

Aqui chegamos a uma das partes mais interessantes do Valhalla. Você não apenas pode criar um **value object**, como também pode criar sua própria classe com o mesmo comportamento de um tipo primitivo. Nesse caso, você abre mão de ter valores nulos, mas obtém dados mais achatados e densos, com valores padrão diferentes de `null`. Para mais detalhes, consulte a [JEP](https://openjdk.org/jeps/8316779).

O restante do texto traz considerações muito interessantes sobre o funcionamento das classes primitivas, argumentando sobre os tipos primitivos, bem como os usos de tipos por referência. Fiquei com preguiça de escrever sobre essa parte. Talvez eu volte no futuro para escrever sobre isso (a quem estou mentindo? não vou voltar).

# Conclusão
O fim do texto traz essa tabela trazendo a unificação feita pelo Valhalla o uso primitivos e objetos. A tabela a seguir resume essa mudança:

| Mundo Atual                               | Valhalla                                                   |
|--------------------------------------------|------------------------------------------------------------|
| Todos os objetos têm identidade            | Alguns objetos têm identidade                              |
| Conjunto fixo e integrado de primitivos    | Conjunto aberto de primitivos, declarados com classes      |
| Primitivos não possuem métodos nem supertipos | Primitivos possuem classes, com métodos e supertipos     |
| Primitivos possuem caixas (boxes) ad hoc    | Primitivos possuem tipos de referência companheiros padronizados |
| Caixas possuem identidade acidental         | Objetos de valor não têm identidade                        |
| Conversões de boxing e unboxing            | Conversões de valores de objetos de valor e primitivos, mas com as mesmas regras |
| Arrays de primitivos são monomórficos       | Todos os arrays são covariantes                            |

Eu estou apaixonado por esse projeto e como eu acredito que ele vai revolucionar o uso do Java. Não somente o modelo de memória será mais eficiente, mas vamos retirar comportamentos estranhos, permitir o uso de novas coisas na linguagem, ser mais eficiente, entre outras coisas. 