---
title: Porque não usar microsserviços
categories: [Arquitetura]
tags: [Reflexões, Microsservicos]
---

Definitivamente, este post teria sido mais polêmico há uns 4 anos. Perdi um pouco o timing para escrever estas palavras, porém antes tarde do que mais tarde. 

O mundo da programação, assim como o mundo da moda, segue tendências. A diferença é que no mundo da moda, as pessoas sabem quando uma peça deve ser usada apenas na passarela e quando que ela está apta para ser usada pelos simples mortais.

Parece haver um desconhecimento sobre como isso funciona no mundo da programação. As pessoas entendem que só porque uma empresa resolveu um problema específico usando uma determinada solução, todas as empresas devem resolver todos os problemas da mesma forma. Parece absurdo dizer isso, mas foi exatamente o que aconteceu com a febre dos microsserviços.

No ano de 2012, o [twitter reescreveu sua API](https://blog.twitter.com/developer/en_us/a/2012/changes-coming-to-twitter-api). Nesse processo, eles migraram de uma grande e poderosa aplicação única capaz de fazer tudo (chamada de monólito) para um conjunto de aplicações menores que interagiam entre si (chamadas de microsserviços). O Twitter tinha uma equipe grande, fazer as alterações levava tempo, gerenciar as dependências era complicado, iniciar o servidor era um processo demorado, entre outros problemas. Essa solução foi um sucesso, tão grande que outras empresas começaram a seguir o exemplo, [Spotify](https://kubernetes.io/case-studies/spotify/),[Uber](https://www.uber.com/en-BR/blog/microservice-architecture/), [Netflix](https://www.sayonetech.com/blog/microservices-netflix). Por causa disso, todo mundo começou a adotar essas tecnologias. 

Porém, o problema é que arquitetos e CTOs de empresas menores esqueceram de uma lição super importante do tempo das nossas mães: 

![Alt Text](/images/mae-gritando.jpeg)

**Você não é todo mundo!**

O fato é: houve todo um movimento grande de empresas que começaram a usar microsserviços sem realmente precisar deles. Isso gerou uma série de problemas. De tal forma que as pessoas iniciaram um movimento para afirmar que os microsserviços são ruins. Isso é tão sem sentido quanto dizer que um peixe é burro porque ele não consegue resolver equações matemáticas.

Dito isso, como em um teste da Capricho, vamos fazer um quiz para determinar se haverá uma combinação adequada entre o seu projeto de software e a arquitetura de microsserviços:


* Latência é um requisito crítico?
* Seu time de desenvolvimento é pequeno?
* Você possui poucas pessoas para cuidar da infraestrutura?
* Você pode escalar seu projeto vagarosamente?
* As funcionalidades do projeto possuem alto acoplamento?
* Sua aplicação terá poucas atualizações por mês?
* Seu projeto tem baixa tolerância a falhas?
* Você tem um baixo orçamento?
* O escopo do seu projeto é pequeno?
* Seu projeto está no início de desenvolvimento?
* Seu projeto é uma prova de conceito?
* Suas equipes não têm tempo para documentar as coisas?
* As divisões entre os módulos ainda não estão claras para o projeto?

Agora vamos a pontuação:

0 - 2: microsserviços podem ser a solução ideal para o seu projeto. Ao menos, não existe nada que diz vai dar ruim se você seguir com essa abordagem;

3+: sabe aquele tipo de relacionamento onde você vê que vai dar ruim? Então, isso é o seu projeto de software e microsserviços. Largue disso enquanto há tempo. 