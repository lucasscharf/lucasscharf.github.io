---
title: Quarkus trazendo o Java de volta para a briga dos microsserviços
categories: [Java, Quarkus]
tags: [Java, Quarkus, GraalMV, Microsservicos]
---

Estamos no ano de 2020. Java completa 25 anos e permanece como uma das linguagens de programação mais utilizadas do mundo segundo o [TIOBE](https://www.tiobe.com/tiobe-index/). 

Nos últimos anos, a linguagem Java perdeu espaço no mundo de programação back-end para linguagens mais dinâmicas e eficientes como Python, Node.js, Go e C#. 

Além da verbosidade, uma das principais reclamações é que o Java é lento e pesado.

![](https://dev-to-uploads.s3.amazonaws.com/i/d7gox455pq7m1o219dar.jpeg)

É inaceitável em um mundo com FaaS, serveless, conteiners e  microsserviços, uma aplicação ocupe mais de 200mB e demore minutos para ficar pronta.

Para tentar resolver esse problema nos últimos tempos surgiu um novo competidor, o [Quarkus](https://quarkus.io/). Criado para ser usado com containers, com baixo consumo de memória, baseado em especificações e fácil de configurar. O Quarkus busca trazer o de volta o [prazer de programar em Java](https://quarkus.io/vision/developer-joy).

Nesse artigo, vamos comparar os tamanhos e tempo de start-up entre o Quarkus com o principal framework de desenvolvimento Java: [Spring](https://spring.io/).

# Plano de Ação

Será implementado um microsserviço com um único *endpoint* que responde com a string "Hello world!". Esse microsserviço rodará num container e será analisado tanto tempo para a aplicação incializar quanto o recurso consumido.

Isso será feito comparando Quarkus com Java 8, Quarkus com Java 11, [Quarkus Nativo](https://quarkus.io/guides/building-native-image) com Java 8, Quarkus Nativo com Java 11, Spring com Java 8 e Spring com Java 11. Vamos criar as aplicações usando os geradores de código de cada framework e não buscaremos nenhuma otmização nesse código gerado.

#Gerando código Spring
O Spring possui seu conhecido gerador de código: https://start.spring.io/. Vamos selecionar a biblioteca Spring Web. A estrutura de classes tem esse formato.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/0px1wq79ipixa5gn4wu3.png)

O modelo padrão não vem com nenhum controller, então foi escrito um. O controller ficou com esse formato.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/zpq5yclqtxn0avag7uzk.png)

# Gerando código Quarkus
Quarkus também possui o seu gerador que pode ser acessado em http://code.quarkus.io. Foi selecionado apenas o plugin para comunicação REST (que é obrigatório ser selecionado). O  código foi criado com a seguinte árvore.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/jhralw47cmrc5b75cpp2.png)

O modelo padrão já vem com um controller que faz o hello world e que ficou assim.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/fg5t45tyzwe6pj36qqis.png)

Agora que já vimos como eles são, gerar as imagens docker. Foram usadas as imagens base *openjdk:8-jre-slim* ou *openjdk:11-jre-slim* nos testes.

# Resultado final
Segue abaixo o resumo da execução dos serviços e do quanto de memória foi consumido.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/c99320ni0er73a11qmq9.png)

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/i/a8at17n291u7whpipkyy.png)

Para facilitar o entendimento, os dados foram colocados em uma tabela.

![Alt Text](/images/performance_quarkus_native.png)

Quarkus levou a melhor tanto no fato de consumir menos recurso quanto no tempo necessário para ficar pronto para receber as conexões. Notem que quando é usado o modo nativo, o tempo cai de 2.802s para 0.006s.

#Considerações
Esse simples teste não serve como estudo, pois muita coisa pode ter atrapalhado a medição, seria necessário rodar várias vezes e testar com outras combinações de bibliotecas. 

O objetivo aqui é mostrar que o Quarkus chegou com tudo e ajudará a linguagem Java a se manter competitiva nesse admirável mundo novo.

