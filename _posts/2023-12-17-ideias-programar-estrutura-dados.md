---
title: Lista de exercícios com estrutura de dados 
categories: [Carreira, Júnior]
tags: []
---

Depois do sucesso das piadas do CaprichApp com direito a pelo menos uma unidade de pessoa fazendo todo o desafio e de eu ter gostando de ter escrito essas histórias, resolvi escrever mais um capítulo, dessa vez focado em exercícios de manipulação de estrutura de dados. Mas assim como no mundo real, ninguém vai chegar pra você e dizer: inverta um árvore binária. Aqui vamos ter um caso de uso já existente. Para fins de história, vamos assumir que você já vai escrever seu programa com um menu e persistindo os dados para não ficar repetindo isso. Caso você não saiba como fazer isso, se liga na jornada que tivemos com o [CaprichApp](/posts/ideias-programar). Também, teremos a volta do carismático Clark com dicas sobre como resolver cada exercício. 

# Calcular a quantidade a quantidade necessária
Num belo dia, você estava jogando videogame e pensando como é bom ter uma vida sem problemas. Quando você recebe uma mensagem da sua tia favorita Namaria. Ela tem um programa de culinário de bichos de pelúcia na televisão. Na mensagem ela pede uma ajuda para lidar com um problema no trabalho. A equipe dela não é boas com conta e acaba sempre comprando mais comida do que o necessário. Sabendo que você mexe nessas coisas de computador, ela quer que você escreva um programinha, onde ela passa os ingredientes necessários para as receitas com a quantidade, quantas porções ela vai fazer e programa responde a quantide necessária para comprar.

Inicialmente, o programa recebe 1 número inteiro contendo quantas receitas serão feitas. Após isso, ele recebe um número contendo quantas porções daquela receita serão feitas, o nome da receita e os ingredientes (o número 0 indica que acabou uma receita e deve começar uma nova). 
Após isso, o programa deverá calcular quanto de cada item precisará comprar. Porém, tem um problema, as receitas são feitas baseadas em xícaras ou unidades, enquanto os itens são vendidos em kilo, litro ou dúzia. Para fins de cálculo, assuma que 1 xícara contém 250g ou 250ml. Açúcar, farinha e fermento são vendidos em pacotes de 1 kilo por outro lado leite condensado, leite integral e óleo são vendidos em pacotes de 1 litro.

Um exemplo de entrada é:
```
3
Pudim
1 xícara açúcar
1 xícara leite condensado
1 xícara leite 
0
1
Bolo de chocolate
5 xícara farinha
1 xícara açúcar
1 xícara óleo
1 xícara fermento
0
7
Panqueca
1 xícara farinha
1 xícara leite
1 xícara açúcar
1 xícara fermento 
0
```
E o programa deve responder:
```
Açúcar: 3 Kilos
Leite condensado: 1 Litro
Leite: 3 Litros
Farinha: 4 Kilos
Óleo: 1 Litro
Fermento: 3 Kilos
```

//TODO ajuda do Clark
# Calcular a quantidade de porções (travessia de lista)

Seu programa foi um sucesso. A produção do programa quer mais e sua tia está mais do que disposta a usar suas habilidades. Ela diz que estão rolando boatos de uma guerra que está por vir e por isso a logística vai ser alterada. Agora eles querem saber quantas porções é possível fazer de uma única receita. O programa receberá uma receita com título, lista de ingredientes e quantidades para uma porção. Após receber um zero indicando que terminou a receita, ele receverá uma lista de ingredientes (com o 0 indicando o fim da entrada) e deverá responder quantas porções serão feitas.

Um exemplo de entrada:
```
Panqueca australiana
1 xícara farinha
3 xícara leite
2 xícara açúcar
1 xícara fermento 
0
5 Kilos açúcar
3 Litros leite
4 Kilos farinha
3 Kilos fermento
0
```
E o programa deve responder:
```
3 Porções de Paqueca australiana
```
//TODO ajuda do Clark

# Garantir que as batatas meia foram colocadas na ordem correta (parentetização)

"UM ABSURDO. A PRODUÇÃO NÃO SE IMPORTA MAIS COM O QUE É IMPORTANTE". Foi com essa mensagem da sua tia que você acordou. Após algumas trocas de mensagem, você entendeu o problema. Para reduzir custos, a produção contratou uma estagiária para ser decoradora de batatas. Porém, essa decoradora não obedece as sagradas regras de decoração de batata. As regras para decorar batatas balanceadas são:

As batatas podem estar viradas para direita "(" ou para a esquerda ")";
A primeira batata é sempre virada para a direita;
Para cada batata virada para a direita, existe uma batata virada para a esquerda;
As batatas viradas para direita e esquerda estão aninhadas.

Exemplos de batatas balanceadas:
```
()
()()
(())
((()()()))
```

Exemplos de batatas não balanceadas:
```
((((((())
())
(()()(()
)
()(
```

Para auxiliar a estagiária, você deve deve escrever um programa que recebe uma sequência de batatas (representadas por abre e fecha parênteses) e responde se a sequência está balanceada ou não.

//TODO ajuda do Clark

# Levar os ingredientes para o programa (preparação para o problema da mochila com repetição)
Sua tia manda mais uma mensagem pedindo sua ajuda. Devido a uma certa invasão alienígena e uma guerra envolvendo o povo toupeira, a logística do programa foi alterado. Agora as receitas são feitas numa cozinha e levados de caminhão até o estúdio. A viagem demora muito tempo, por isso o ideal é que o caminhão vá o mais cheio possível, porém os motoristas são preguiçosos e te pagaram um guarará para fazer com que o programa não tenha tantos itens. Além disso, o caminhão é suficientemente grande para caber qualquer quantidad de receitas, porém o motor é fraco e tem um peso máximo que pode carregar.

Para resolver essa situação sua tia quer que você desenvolva um programa para definir quantos de cada receita o caminhão vai levar. 

Inicialmente, o programa recebe um par de inteiros indicando a carga máxima que o caminhão consegue carregar e a quantidade de receitas disponíveis. Após isso, vem uma lista com as receitas e o peso de cada uma em kilos - cada receita tem um peso único. O seu programa deve retornar quanto de cada item será levado e a carga total do caminhão tentando minimizar a quantidade de produtos levados. 

Por exemplo:
```
501 3
Bolo de chocolate 46
Bolo de cenoura 19
Suco de tamarindo 2
```

Então o programa deverá responder:
```
Bolo de chocolate: 10
Bolo de cenoura: 2
Suco de tamarindo: 1
Carga total: 500
Total de itens: 13
```
//TODO ajuda do Clark

# Levar os ingredientes caro para o programa (problema da mochila com repetição)
Desafio. **AVISO, O EXERCÍCIO A SEGUIR É BEM COMPLEXO, NÃO FIQUE CHATEADO SE VOCÊ NÃO CONSEGUIR FAZER**
A guerra com o povo toupeira prejudicou a economia de uma forma muito ruim, os motoristas foram substituídos por robôs que não reclamam do trabalho. Porém, agora tudo gira em torno de dinheiro. O problema continua sendo o mesmo: levar o caminhão o mais cheio possível. Porém, agora toda receita possui um peso e um preço. O objetivo é que o caminhão carregue o maior preço possível sem ultrapassar o peso máximo. 

Inicialmente, o programa recebe um par de inteiros indicando a carga máxima que o caminhão consegue carregar e a quantidade de receitas disponíveis. Após isso, vem uma lista com as receitas, o peso e o preço de cada uma em kilos e reais - cada receita tem um par de preço e peso único. O seu programa deve retornar o valor da carga que será transportada para saber se haverá problemas com o governo do povo toupeira.


Por exemplo:
```
501 3
Bolo de chocolate 46 10
Bolo de cenoura 19 5
Suco de tamarindo 2 1
```
Então o programa deverá responder:
```
A carga terá custará R$136,00 (equivalente a 26 bolos de cenoura e 3 sucos de tamarindo)
```
//TODO ajuda do clark
# Dominação global
Anos se passam. A guerra contra o povo toupeira acabou. Clark mudou de cidade para começar um novo trabalho como jornalista. Porém ele estava confiante de que a tia Namaria e o problema ficaria bem em suas mãos. O programa é líder de audiência matinal. Porém existe um entrave entre você e a dominação mundial: publicar seus achados.

Você se lembra de ter lido em algum [blog](http://aleatorio.dev.br){:target="_blank"} que o GitHub é um lugar legal para armazenar o código. E você decide experimentar isso para divulgar o seu trabalho e dominar o mundo. 

````
Adicione todos os códigos num repositório no github;
Documente esse repositório explicando como fazer para levantar a aplicação e testar;
Cole o link aqui para que todos possam ver as soluções e você domine o mundo ;)
```
