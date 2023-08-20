---
title: Porque não usar microsserviços
categories: [Arquitetura]
tags: [Reflexões, Microsservicos]
---

Definitivamente, esse post seria mais polêmico uns 4 anos atrás. Perdi um pouco o tempo de escrever essas palavras, porém antes tarde do que mais tarde. Algumas das reflexões feitas nesse texto também servem para a febre de IA que está tendo agora ou sobre a próxima febre que ocorrerá daqui a um ou dois anos no desenvolvimento.

O mundo da programação, assim como o mundo da moda, se move por tendências. A diferença é que no mundo da moda, é claro quando uma peça é usada apenas como exemplo de alguma tendência a ser seguida e quando é para ser aplicado no dia-à-dia.

Na programação não. As pessoas entendem que só porque uma empresa resolveu algum problema específico usando uma determinada solução então todas as empresas devem resolver todos os problemas com a mesma solução. Parece absurdo dizer isso, porém foi exatamente isso o que aconteceu com a febre dos microsserviços. 

No ano de 2012, o [twitter reescreveu sua API](https://blog.twitter.com/developer/en_us/a/2012/changes-coming-to-twitter-api). Nesse processo, eles migraram de uma grande única e poderosa aplicação capaz de fazer tudo (chamado de monólito) para um conjunto de aplicações pequenas que interagiam entre si (chamado de microsserviços). O twitter tinha um time grande, fazer as alterações levavam tempo, gerenciar as dependências eram complicadas, iniciar o servidor era um processo demorado entre outros problemas. E essa solução foi um sucesso, foi tão sucesso que outras empresas começaram a suar como o [Spotify](https://kubernetes.io/case-studies/spotify/),[Uber](https://www.uber.com/en-BR/blog/microservice-architecture/), [Netflix](https://www.sayonetech.com/blog/microservices-netflix). Por causa disso, todo mundo começou a adotar essas tecnologias. 

Porém, o problema é que arquitetos e CTOs de empresas menores esqueceram de uma lição super importante do tempo das nossas mães: você não é todo mundo.

![Alt Text mãe gritando com criança](/images/mae-gritando.jpeg)
