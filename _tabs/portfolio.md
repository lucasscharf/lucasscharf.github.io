---
title: Portfólio
icon: fas fa-briefcase
order: 6
---

Essa página é uma coletânea dos trabalhos que produzi ou que participei ao longo do tempo. Tem coisa acadêmica, tem podcast, tem live coding, tem aulas e tem código. Reuni tudo aqui para ficar mais fácil compartilhar e também para eu mesmo conseguir encontrar quando precisar. Os assuntos se conversam bastante entre si: sistemas distribuídos, paralelismo, mensageria e LLM acabam se misturando no dia a dia.

# Trabalhos acadêmicos

## Dissertação de mestrado UFSC (2024)

**Explorando paralelismo e desacoplamento no desenvolvimento de um serviço de log para aplicações utilizando replicação de máquina de estados**

Essa é minha dissertação de mestrado, defendida na UFSC. A ideia central foi propor uma técnica para reduzir o custo dos mecanismos de persistência em replicação de máquina de estados (SMR). A proposta desacopla a persistência das réplicas e do protocolo de comunicação, introduzindo processos chamados *loggers* que podem ser compartilhados entre diferentes aplicações. Nos experimentos, foi possível observar ganhos de até 70% de vazão em cenários com uso intensivo de I/O, até 60% em casos com alto uso de memória e uma redução de até 55% nos custos financeiros quando o serviço é compartilhado entre várias aplicações SMR independentes.

[Acessar a dissertação no repositório da UFSC](https://repositorio.ufsc.br/xmlui/handle/123456789/261372)

## Artigo no LADC '23 (ACM)

**Joining Parallel and Partitioned State Machine Replication Models for Enhanced Shared Logging Performance**

Artigo publicado no *12th Latin-American Symposium on Dependable and Secure Computing* (LADC '23), em coautoria com Luiz Gustavo C. Xavier e Odorico M. Mendizabal. Esse trabalho conversa diretamente com a dissertação, sendo um dos resultados publicados durante o mestrado. Aqui exploramos a junção dos modelos paralelo e particionado de replicação de máquina de estados para melhorar a performance de um serviço de log compartilhado. Para quem gosta de sistemas distribuídos, performance e desafios de concorrência, é uma leitura que vale a pena.

[Acessar o artigo na ACM Digital Library](https://dl.acm.org/doi/abs/10.1145/3615366.3615422)

### Repositórios relacionados ao mestrado

- [`parallel-state-machine-replication-simulator`](https://github.com/lucasscharf/parallel-state-machine-replication-simulator): simulador que compara três formas de tratar replicação de máquina de estados (sem paralelismo, por grafos e por bitmaps), feito originalmente para a disciplina de Computação Paralela.
- [`shared-logger-client`](https://github.com/lucasscharf/shared-logger-client): cliente Java do serviço de log compartilhado, parte do projeto que serviu de base para a dissertação e para o artigo do LADC '23.

# Participações em podcasts e lives

## Podcast do Carlos Nogueira sobre LLM

Bati um papo com o Carlos Nogueira sobre LLM, agentes de IA e como tudo isso vem afetando o dia a dia da galera que desenvolve. A conversa segue na mesma linha do que já escrevi aqui no blog sobre [uso saudável de LLMs](/posts/uso-saudavel-llm/).

<iframe width="420" height="315" src="https://www.youtube.com/embed/fG_qSu2JLWk" frameborder="0" allowfullscreen></iframe>

## Live coding com diferentes sistemas de mensageria usando Quarkus

Uma live em que mostro, na prática, como integrar diferentes sistemas de mensageria utilizando o Quarkus. Se você gosta dos posts sobre [Quarkus com Kafka](/posts/Quarkus-Kafka/) e [Quarkus com RabbitMQ](/posts/quarkus-rabbitmq/), aqui dá pra ver tudo isso funcionando ao vivo.

<iframe width="420" height="315" src="https://www.youtube.com/embed/Yt0ATbcWHSk" frameborder="0" allowfullscreen></iframe>

## AI, Year 3 (War Room)

Participação numa conversa sobre o terceiro ano da febre de IA: o que mudou, o que continua sendo hype e o que sobrou de útil no dia a dia de quem desenvolve. O papo conversa bastante com o que escrevo aqui no blog nos últimos tempos.

<iframe width="420" height="315" src="https://www.youtube.com/embed/2CjRglECUhg" frameborder="0" allowfullscreen></iframe>

# Série de aulas sobre threads em Java

Gravei uma série de aulas sobre threads em Java cobrindo desde o básico de concorrência até detalhes mais avançados. É um assunto que se conecta diretamente com o que estudei no mestrado e com tudo que envolve paralelismo e sistemas concorrentes. Recomendo seguir a ordem dos vídeos para não se perder.

## Aula 1

<iframe width="420" height="315" src="https://www.youtube.com/embed/yaFm5tCiFRY" frameborder="0" allowfullscreen></iframe>

## Aula 2

<iframe width="420" height="315" src="https://www.youtube.com/embed/TAO6m1MqA3A" frameborder="0" allowfullscreen></iframe>

## Aula 3

<iframe width="420" height="315" src="https://www.youtube.com/embed/gZLLfZmDOPE" frameborder="0" allowfullscreen></iframe>

## Aula 4

<iframe width="420" height="315" src="https://www.youtube.com/embed/uz6DecILPAU" frameborder="0" allowfullscreen></iframe>

## Aula 5

<iframe width="420" height="315" src="https://www.youtube.com/embed/ebl_XVQlyQc" frameborder="0" allowfullscreen></iframe>

# Projetos no GitHub

## Projetos pessoais

- [`url-shortener`](https://github.com/lucasscharf/url-shortener): encurtador de URL escrito em Rust como projeto de estudo. O foco está menos na regra de negócio e mais em explorar a linguagem, suas libs e diferentes abordagens.
- [`samsung-watch-plex-client`](https://github.com/lucasscharf/samsung-watch-plex-client): cliente Plex para Samsung Galaxy Watch (Wear OS) feito como experimento de vibe coding com Claude Code. É um caso prático do que discuto nos posts sobre [uso saudável de LLMs](/posts/uso-saudavel-llm/) e [considerações sobre IA](/posts/consideracao-ia/).

## Códigos do blog

- [`blog-posts-code`](https://github.com/lucasscharf/blog-posts-code): exemplos de código dos posts publicados [aqui](https://dev.to/lucasscharf), úteis pra acompanhar quando o post traz trechos parciais.
- [`lucasscharf.github.io`](https://github.com/lucasscharf/lucasscharf.github.io): sim, você pode estudar o código. Pode copiar, pode fazer o que quiser (só não pode comitar :v).