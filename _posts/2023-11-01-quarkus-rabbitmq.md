---
title: Como usar RabbitMQ com AMQP num projeto Quarkus
categories: [Java, Quarkus]
tags: [Java, Quarkus, Microsservicos]
---

Se eu fosse um cara preguiçoso, eu diria diria que o Quarkus é framework topzeira e falaria apenas para você pegar o artigo que eu escrevi com Kafka e mudar as referências para RabbitMQ, porém sou um amorzinho e vamos refazer o passo-à-passo além de trazer várias informações que não estava presentes no artigo do Kafka.

# O que é o RabbitMQ ?
[RabbitMQ](https://www.rabbitmq.com/) é um sistema de mensageria leve, multiplataforma, de código aberto e amplamente utilizado para transmitir mensagens entre diferentes partes de uma aplicação. Ele atua como intermediário, permitindo a comunicação eficiente e assíncrona entre sistemas distribuídos, facilitando a escalabilidade e a confiabilidade em arquiteturas de microservices. Uma única instância do RabbitMQ é chamada de *broker*, enquanto um conjunto de instâncias é chamado de *cluster*. 

# Como ele funciona?
Aqui a porca torce o rabo.
//TODO adicionar imagem da porca com rabo torcido

Existem [vários protocolos](https://www.rabbitmq.com/protocols.html) possíveis de usar. Não vamos entrar no mérito de cada um, vamos nos limitar a dizer que será usado o protocolo [AMQP](https://www.amqp.org/).

## Modelo de *queues/exchanges/bindings*
A *queue* ou fila é onde a mensagem fica armazenada até ser consumida. Porém, no modelo do RabbitMQ, um cliente nunca envia a mensagem diretamente para a fila. Ao invés disso, ele envia a mensagem para um *exchange* ou contexto de troca e é esse contexto que armazena a mensagem na fila. Dessa forma acabamos tendo vários tipos de envios. 

## Tipos de Envios
Quando um cliente envia uma mensagem para o RabbitMQ, ele também envia uma *key* ou chave que, somado o tipo de envio e outras propriedades, é responsável por definir para qua tópico vai determinada mensagem. 

Direct (Direto) - o contexto de troca encaminha a mensagem para uma fila com base em uma chave de roteamento.
Fanout (Espalhar) - o contexto de troca ignora a chave de roteamento e encaminha a mensagem para todas as filas vinculadas.
Topic (Tópico) - o contexto de troca roteia a mensagem para filas vinculadas usando a correspondência entre um padrão definido no contexto de troca e as chaves de roteamento anexadas às filas.
Headers (Cabeçalhos) - neste caso, os atributos do cabeçalho da mensagem são usados, em vez da chave de roteamento, para vincular umo contexto de troca a uma ou mais filas.

# O que foi a fábrica de bolo?
A fábrica de bolo é um CRUD simples que uso para diferentes partes do framework Quarkus. Você pode encontrar o código básico [aqui](https://github.com/lucasscharf/blog-posts-code/tree/master/open-api), para ter uma explicação sobre as partes do projeto, você pode clicar [aqui](/posts/Quarkus_e_openAPI/).

# Hora da ação
Na simples fábrica de bolo, nós fazemos as operações de cadastrar, editar, recuperar e deletar. Vamos editar essa fábria para que todas as mensagens que alteram dados (cadastrar, editar e deletar) também enviem um evento para o RabbitMQ informando o evento que foi gerado e qual o id alterado mandando todos todas as mensagens na mesma fila. Além disso vamos criar um outro serviço que apenas recebe os eventos e escreve eles na saída padrão. E notoriamente, vamos deixar tudo conectado com um RabbitMQ.

## Configurando o servidor
//TODO Iniciar o projeto adicionar as configurações de RabbitMQ e passar as filas
//TODO configurar o servidor
//TODO alterar a fábrica de bolo pra adicionar
//TODO Configurar o cliente
//TODO Configurar o rabbit mq
//Testar tudo 
