---
title: Passo a passo sobre como começar a programar em Java
categories: [Carreira, Júnior]
tags: [Java]
---

Com grande frequência, várias pessoas vem até o [meu linkedin](https://www.linkedin.com/in/lucasscharf/) perguntando como começar a programar. Por causa disso, resolvi fazer uma trilha com um monte de conteúdo gratuito com um plano para a pessoa se tornar um programador Júnior diferenciado no mercado. Por uma questão de conhecimento os links são com Java porque é isso o que eu conheço e porque ninguém me paga pra estudar o C# alheio.

Recomendo que todas as fases desse processo sejam documentadas. Seja num blog, no linkedin ou em qualquer outro meio. Já falei isso antes, mas não custa repetir: [A hora de compartilhar conhecimento é agora](/posts/compartilhe-agora)

É importante deixar o disclaimer de que eu nenhum fiz nenhum dos cursos aqui indicados. A seleção deles se deu por base do plano de aula e dos comentários sobre a qualidade deles. Mais improtante do que as dicas dos cursos, é entender a estrutura do plano e adaptar ele para a sua linguagem escolhida.

# Fase 0: Lógica de programação
O primeiro passo para saber programar é estudar lógica de programação. No Brasil, não existe melhor fonte no mundo do que o [curso de lógica de programação](https://www.cursoemvideo.com/curso/curso-de-algoritmo/) do professor Gustavo Guanabara.

# Fase 1: Conhecer a Linguagem
Depois que você aprendeu o básico sobre escrita de código, desvio condicional, estrutura de repetição, você vai aprender tudo de novo.  Dessa vez usando a sua linguagem de programação escolhida. 

Para Java, o melhor curso que tem também é o curso de Java ([Parte 1](https://www.cursoemvideo.com/curso/java-basico/) e [Parte 2](https://www.cursoemvideo.com/curso/java-poo/)) do Gustavo Guanabara. Ele possui cursos para outras linguagens também. Com um pouco de sorte, o caminho que você vai seguir já tem um curso do Guanabara ou de mesmo nível.

# Fase 2: Banco de dados e conexão com o banco
A menos que seu programa receba todos os dados na inicialização, faça o processamento e entregue os resultados para alguém. Ele vai precisar salvar (no jargão nos chamamos persistir) os dados. A forma mais comum de trabalhar com persistência é através do banco de dados. 

Para um dev em começo de carreira, o trabalhar com banco de dados pode ser dividido em 3 partes: modelagem dos dados, SQL e conexão com banco de dados. 

Como não achei nenhum curso legal que envolva essas 3 partes, vou indicar alguns cursos. 

Para a modelagem, o [curso da Boson Treinamentos](https://youtube.com/playlist?list=PLucm8g_ezqNoNHU8tjVeHmRGBFnjDIlxD) é bem completo.

Para SQL, o [curso do Lets Data](https://youtube.com/playlist?list=PLn_z5E4dh_LgWmEGn2lcdOp5TDKw6nkde) é um curso pequeno porém bem interessante nas principais partes de SQL. Esse curso junto com [curso do Gustavo Guanabara do MySQL](https://www.cursoemvideo.com/curso/mysql/) são as melhores opções de cursos gratuitos para aprender SQL que eu encontrei até agora. Uma nota que o curso do Guanabara acaba falando sobre outras coisas além do SQL básico.

Sabendo dessas coisas, agora você pode fazer o curso de Java com banco de dados. Mas é pra usado Java puro e não frameworks como hibernate, jooq, panache ou outras coisas do JPA. Nessa fase você vai precisar abrir uma connection, fazer um statement, pegar um result set e por aí vai. 

Um erro como comum que devs comentem é usar direto os frameworks sem entender o que está acontecendo. Não cometa esse erro. Por isso recomendo da aula 140 até a aula 153 do [curso Maratona Java do Dev Dojo](https://www.youtube.com/watch?v=kkOSweUhGZM&list=PL62G310vn6nHrMr1tFLNOYP_c73m6nAzL&ab_channel=DevDojo).

# Fase 3: Estrutura de dados

Infelizmente, a parte de estrutura de dados é algo que acaba sendo deixado de lado por parte de muitas pessoas que estão começando a programar. 

Porém, se o seu problema for corretamente modelado e utilizado as estruturas corretas, naturalmente a melhor solução aparecerá. A maravilhosa Loiane Groner tem um [curso onde apresenta as principais estruturas de dados e como usá-las em Java](https://www.youtube.com/playlist?list=PLGxZ4Rq3BOBrgumpzz-l8kFMw2DLERdxi). Depois disso, você pode se arriscar a ver esse vídeo sobre estrutura de dados do [Fábio Akita](https://www.youtube.com/watch?v=9GdesxWtOgs).

# Pausa 1
Aqui é um momento interessante para dar uma pausa nos estudos e praticar bastante. Recomendo fazer os primeiros 400 primeiros exercícios do [compilado de ideias para programar que eu fiz](/posts/ideias-para-programar/). 

Também recomendo fazer um monte de projetinho com interface de linha de comando. Tipo um jogo da velha, um gerenciador de atividades, um sistema de cadastro de alunos e notas. Sabe, colocar a mão na massa. Porque é só assim que você vai ganhar experiência.

# Fase 4: Gestão de configuração de projeto
Projetos complexos não são fáceis de montar. Eles tem uma série de passos para serem executados. Nessa fase, você vai estudar como montar um projeto mais complexo. Um projeto que tem vários módulos, testes e partes móveis. 

Para o mundo Java, a ferramenta mais usada é o Maven. Esses cursos da [Algaworks](https://www.youtube.com/watch?v=ZQICkNszEuI&ab_channel=AlgaWorks) e do [Curso de Tecnologia](https://www.youtube.com/watch?v=bN6ZOjhrOm4&ab_channel=CursoDeTecnologia). Explicam como usar essas ferramentas. 

# Fase 5: Testes
Particularmente, eu gostaria de colcar a disciplina de testes o mais antes possível dado a sua importância. Porém, era importante ter os conhecimentos de Gestão de configuração de projeto. A disciplina de teste pode ser dividida em duas partes: o que testar e como testar.

Para a parte do que testar, não achei cursos que me agradassem, esse da [Daniele Leão](https://www.youtube.com/watch?v=be3T6PXJEfk&ab_channel=DanieleLe%C3%A3o) quase é o que eu busco. 

Aqui, o livro [Test Driven Development: By Example de Kent Back](https://www.oreilly.com/library/view/test-driven-development/0321146530/) acaba se tornando uma leitura quase que obrigatória para saber o que testar.

A parte de como testar é dependente da linguagem. Para o mundo Java os vídeos [280](https://www.youtube.com/watch?v=jdBsFPF_w9Q&ab_channel=DevDojo) e [281](https://www.youtube.com/watch?v=N5iaGF90XGg&ab_channel=DevDojo) da Maratona Java virado no Jiraya do Dev Dojo, bem como essa série de vídeos do [Valdir Cezar Tutoriais](https://www.youtube.com/playlist?list=PLA8Qj9w4RGkWgyYa485pgf-VAoJgL4rW1) explicam sobre duas das principais ferramentas que você vai usar nesse processo: o jUnit e Mockito. 


# Fase 6: Utilitários

Toda linguagem de programação terá seu conjunto de códigos prontos que executam as tarefas mais comuns: manipulação de arquivo, JSON, data, texto, expressões regular, tratamento de erro, etc. Conhecer essas ferramentas é algo bem importante. 

**Atenção: não busque os utlitários de paralelismo, Threads ou programação paralela nessa fase. Haverá um momento para isso no futuro**

Uma das coisas mais incríveis e poderosas do Java são seus vários frameworks e bibliotecas já embutidos que ajudam a lidar com os problemas mais comuns do desenvolvedor. O curso do [Maratona Java do DevDojo da aula 67 até 139](https://www.youtube.com/watch?v=kkOSweUhGZM&list=PL62G310vn6nHrMr1tFLNOYP_c73m6nAzL&ab_channel=DevDojo) e o [Maratona Java virado no Jiraya da aula 95 até 244](https://www.youtube.com/playlist?list=PL62G310vn6nFIsOCC0H-C2infYgwm8SWW) dá uma visão superficial, porém bem interessante sobre muitas ferramentas e frameworks. 

# Fase 7: Comunicação entre Aplicações

Depois de ver os utilitários, você vai aprender a fazer diferentes aplicações conversarem entre si, aqui entram os protocolos HTTP, Sockets, Signals, gRPC, CORBA, RPC, etc. Entendendo a base de conceitos e protocolos, aprender novos protocolos fica algo fácil. Por exemplo, se você entende de SOAP, entender gRPC fica algo muito simples, mudando apenas alguns conceitos. Existem dois cursos interessantes, o primeiro deles é o curso da [Hardware Redes Brasil](https://www.youtube.com/watch?v=YbRHeRFYyCQ&list=PLAp37wMSBouDdpuuYhZfEK9oH0qk0IANb&ab_channel=HardwareRedesBrasil), depois de entender essa base é possível o [curso de Sistemas Distribuídos da UNIVESP](https://www.youtube.com/watch?v=e4zmQQU3ZqE&list=PLxI8Can9yAHdAU8zIvJTKcbhgRyzwjII2&ab_channel=UNIVESP) e o [curso do professor Fábio Pinheiro](https://www.youtube.com/watch?v=JnVwYSsyovs&list=PLRe8NlqFzKJXKCbMO6FIgMhqrXnAJnlCE&ab_channel=ProfF%C3%A1bioPinheiro).

> Em paralelo a isso, já é interessante entender um pouco mais sobre sistemas operacionais. Isso pode ser feito através de livros como Sistemas Operacionais Modernos ou o [curso de Sistemas Operacionais da UNIVESP](https://www.youtube.com/watch?v=Rl6HhDvW984&list=PLxI8Can9yAHeK7GUEGxMsqoPRmJKwI9Jw&ab_channel=UNIVESP).


# Pausa 2
Agora você já está mais do que apto a completar os outros exercícios da [grande lista de exercícios](/posts/ideias-para-programar/) e até começar a brincar com as APIs dos exercícios mais avançados. Aproveite esse momento para finalizar toda a lista.

Você também pode ver meus exercícios como essa jornada no [CaprichApp](/posts/ideias-programar).


# Fase 8: Programação paralela, assíncrona e concorrente

Todo esse assunto é bem complexo e denso, além de se ramificar muito. Para começar a entender bem esse tema, eu diria que é necessário pelo menos uns 6 meses de estudo constante. Diria mais, esse passo pode ser feito em paralelo com as fases 9 e 10.

Entre os principais assuntos temos: Threads, ThreadPool, Mutex e Monitores, Fork/Join, ThreadPool, Processos, Estruturas de dados paralelas, frameworks de programação assíncrona. 

Depois disso, é importante ver como a sua linguagem de programação lida com esses assuntos, no caso de Java, entra a parte de Threads, Runnables, Atomic operators, Parallel Collections, Parallelal Streams, Executors, AtomicOperators. 

Não encontrei nenhum curso que funciona da forma como eu quero, então vamos de partes em partes. Alguns desses cursos se sobrepõem em conteúdo, contudo, acabam fixando conteúdo. 
Começando com o [curso de programação concorrente professor Fernando Santos](https://www.youtube.com/watch?v=lvcJxv-teC0&list=PLPrYObOisEDHo0zGqngAXoVQD5TaDtxzk&ab_channel=FernandodosSantos). Depois disso vem o [curso de programação paralela em C](https://www.youtube.com/watch?v=Q4Biti6q4XQ&list=PL_9px37PNj6pyE7GbTyGyR598_T7T_cwG&index=1&ab_channel=Laborat%C3%B3rioHiPES), por ser em C, acaba forçando a pessoa a escrever bem tudo e entender por baixo dos panos. É um assunto que precisa ser entendido com calma e tempo.

Depois disso, seguimos com a versão em [Java de multiprogramação do Reinaldo Dev](https://www.youtube.com/watch?v=HNSRrbktJ9o&list=PLuYctAHjg89YNXAXhgUt6ogMyPphlTVQG&ab_channel=RinaldoDev).

# Pausa 3
Desse ponto em diante você está totalmente apto para contribuir em diferentes projetos open source. [Aqui](https://github.com/camilatigre/listamaravilhosaopensource) tem uma lista linda com vários projetos para os diferentes níveis. Em especial, se você for do Java recomendo contribuir com o framework [Quarkus](https://quarkus.io/blog/contributing-for-the-first-time/). Mesmo que você ache que você é ruim (spoiler: se você está nesse ponto você não é), você sempre pode contribuir com documentação.

# Fase 9: Frameworks all-in-one

Notem que só depois de aprender como as coisas funcionam é que você deve aprender a usar um framework que contém todas as soluções. Infelizmente, muitas pessoas colocam a carroça na frente dos bois e viram programador de framework. 

Falando em termos de front-end, é só aqui que você vai usar, um React.js, um Vue.js ou mesmo um Angular. 

Em Java, o framework que domina o mercado é o Spring. Não gosto disso, mas não posso negar a realidade. Logo após dele, vem o Quarkus. Vou indicar os dois, dando prioridade para o Quarkus porque o blog é meu e eu decido as coisas u.u

Para o curso de Quarkus, eu recomendo o Vinícius Ferraz da udemy. A [versão básica](https://www.udemy.com/course/des-web-quarkus-basico/) é grátis. A versão [avançada é paga](https://www.udemy.com/course/des-web-quarkus/), porém de tempos em tempos ele cria descontos com 100% de desconto.

Para Spring, eu recomendo o curso do YouTube da Loiane Groner de [Spring com Angular](https://www.youtube.com/watch?v=qJnjz8FIs6Q&list=PLGxZ4Rq3BOBpwaVgAPxTxhdX_TfSVlTcY&ab_channel=LoianeGroner).

# Pausa Final
 
Fazendo tudo num ritmo muito bom, você já deve estar programando há mais ou menos um ano e meio. Agora é um momento muito bom para procurar emprego com mais convicção. 
Se tudo foi feito bem certinho você já:
* Estudou mais de 500 horas de programação;
* Já fez quase mil exercícios de programação;
* Contribuiu com a comunidade de desenvolvimento;
* Já compartilhou seus estudos;
* Já trabalhou em software livre; 
* É um programador Jr totalmente diferenciado no mercado. 

A jornada de estudos não terminou, mas agora a [lista de empresas para procurar vaga está esperando você](/posts/sites-empregos/). Boa sorte.