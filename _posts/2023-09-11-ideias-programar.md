---
title: Mais ideias sobre o que programar
categories: [Carreira, Júnior]
tags: []
---

Depois de várias pessoas pedirem (mentira ninguém me pediu nada) resolvi fazer minha lista de exercícios para programação para rir um pouco e programar. Nessa lista, seguirimos na jornada pela criação do CaprichApp um aplicativo para uma revista especialista em questionários adolescentes que faz muito sucesso com dicas de maquiagens, modelos bonitos e inseguranças das pessoas. 

Nossa jornada vai passar por programação de linha de comando, desvios condicionais, tratamento de erros, laços de repetição, manipulação de arquivos, banco de dados, construção de APIs e docker. Conforme for a recepção desse exercício, posso pensar em outros. Então vamos interagir, comentar e fazer barulho para eu me sentir amado S2

# Capítulo 1: Prova de conceito
Você foi recém contratado para estagiar na revista Capriche. É a revista jovem mais balada do Brasil, o carro chefe da revista são os questionários que respondem perguntas extremamente importantes sobre: "será que o Crush está afim de você", "Qual profissão é mais a sua cara" e "De qual casa de hogwarts você seria". 

Você cehga no seu primeiro dia de trabalho cheio de energia e vontade de programar. Por uma série de problemas na contratação, o seu chefe, Delipe Fylon, não estava preparado para fazer uma recepção apropriada. Por causa disso, ele inventa uma atividade em cima da hora, sem muito planejamento e com a cobrança de prazo para o quanto antes. Você precisa automatizar a execução desses questionários para ele apresentar para a diretoria. 

Seguem as instruções:
```
* Escreva um programa em linha de comando para fazer questionário com o tema "Você está afim do seu melhor amigo?";
* Esse programa fará várias perguntas diferentes e aceitará apenas as entradas 'S' e 'N';
* Para cada pergunta respondida 'S', ele deve somar um ponto. Cada pergunta respondida 'N' ele não soma nada;
* Ao final da execução, o programa responderá o usuário de acordo com a soma dos pontos. 
* A lista de perguntas é:
A) "Você já sonhou que seu melhor amigo era um unicórnio voando sobre arco-íris cor-de-rosa enquanto segurava um buquê de salsichas?"
B) "Você já se viu dançando a dança do frango em homenagem ao aniversário do seu melhor amigo, vestida de pinguim?"
C) "Se seu melhor amigo fosse um sorvete, ele seria o sorvete de pistache?"
D) "Você pensa em patos de borracha quando olha para o seu melhor amigo?"
E) "Você já escreveu um poema de amor épico para o seu melhor amigo usando apenas emojis de vegetais?"
F) "Você acha que seu melhor amigo seria um bom companheiro numa luta contra zumbis alienígenas usando almofadas como armas?"

* E a pontuação:
- De 0 a 2 pontos: você colocou seu melhor amigo na friendzone. O que é ótimo porque talvez ele seja apenas seu amigo
- De 3 a 4 pontos: Talvez haja amor, talvez seja hormônios. Vale a pena experimentar uns cinco minutos de trocação de beijo sem estragar a amizade.
- 5 ou mais pontos: É o amor /Que mexe com minha cabeça e me deixa assim/ Que faz eu pensar em você e esquecer de mim/ Que faz eu esquecer que a vida é feita pra viver
```

# Capítulo 2: Evolução
Você chega no dia seguinte ao trabalho. Você mal dormiu para conseguir terminar no prazo a tarefa que ele definiu em cima da hora e criou um senso de urgência desnecessário. Apesar disso, o animo está tomando conta de você. É a sua primeira entrega e você mostra seus avanços para Delipe Fylon. 
Após olhar sério, ele pergunta se a inteligência é algo que falta para você. Não importa de você fez exatamente o que ele falou, você fez errado. Quem liga para um questionário que tem sempre as mesmas perguntas? Você precisa criar um banco de perguntas e usar as perguntas desse banco. A ordem das respostas deve ser aleatória a cada execução. Nas palavras dele "A vida não é uma versão de meu primeiro amor onde as pessoas descobrem que estão apaixonados pelo melhor amigo. A vida é mais como Donnie Darko onde nada faz sentido. Como eu sou uma boa pessoa, vou lhe passar os requisitos para uma boa enquete bem como perguntas de qualidade:"

```
* Altere o programa para consultar as perguntas do banco de perguntas
* A aplicação deverá escolher apenas 5 perguntas aleatórias desse banco, de tal forma que duas execuções seguidas tenham perguntas diferentes;
* A aplicação não pode repetir a pergunta, caso a pergunta escolhida já exista, a aplicação deverá escolher outra;
* Para cada pergunta respondida 'S', ele deve somar um ponto. Cada pergunta respondida 'N' ele não soma nada;
* Ao final da execução, o programa deve responder de acordo com a nota dada. 
* Você pode utilizar a mesma pontuação do Capítulo anterior.
* A lista para o banco de perguntas é:
"Você já sonhou em fazer uma viagem à Lua com seu melhor amigo?"
"Você acha que seu amigo é a reencarnação de um unicórnio?"
"Você já considerou mudar seu nome para Geleca apenas para combinar com o apelido do seu amigo?"
"Você acredita que seu amigo é secretamente um super-herói disfarçado?"
"Você já planejou uma festa surpresa de aniversário para o seu amigo no dia errado, só para ver a reação?"
"Você acha que seu amigo é a única pessoa capaz de decifrar porque o cocô das cabras é redondo e o do wombat é quadrado?"
"Você já pensou em criar um clube exclusivo para pessoas que usam pijamas de abacaxi nas segundas-feiras?"
"Você consegue segurar o tchan?"
"Você já considerou tatuar uma imagem de batata frita no braço em homenagem ao seu amigo?"
"Você já pensou em criar um podcast sobre teorias da conspiração envolvendo a vida secreta do seu melhor amigo?"
"Você acredita que seu amigo é a verdadeira inspiração por trás das músicas de karaokê?"
"Você acha que seu amigo possui um diploma honorário em Mímica Avançada?"
"Você acha que seu amigo é o verdadeiro criador das terríveis baratas voadas?"

```

# Capítulo 3: Promoção
Após a entrega, seu chefe apenas disse seu projeto estava tolerável e que ia levar para a diretoria. Semanas sem resposta. Muito pelo contrário. Delipe Fylon foi demitido por justa causas acusado de ter roubado o grampeador da firma e ter levado ele para as férias de verão tirando fotos do mesmo na frente de pontos turísticos. Com isso você acaba sendo promovido, passando a acumular funções sem aumento de salário. 
Sua nova função consiste em criar as enquetes para serem usadas. Meio sem uma orientação você acredita que pode utilizar a sua aplicação para facilitar sua vida. Após refletir um pouco você chega a conclusão de quais alterações no código você precisará escrever:

```
Altere o programa para ter 5 fases:

Fase 0) O programa perguntará qual é o título do questionário.
Fase 1) O programa perguntará quantas perguntas você deseja fazer.
Fase 2) Para cada pergunta, o programa perguntará quais são as opções e o peso da resposta de cada uma delas.
Fase 3) Em seguida, o programa perguntará quais são as respostas e quais são as faixas de valores utilizadas.
Fase 4) Por fim, o programa realizará a enquete ao usuário, usando como entrada os dados fornecidos nas etapas anteriores e respondendo ao que foi perguntado.
```

![](/images/grampeador_ferias.jpg)

# Capítulo 4: Utilização em arquivo
Contrataram um novo gerente para substituir Delipe Fylon. No lugar dele entrou um cara simpático e meio tímido chamado Clark. Ele é alto, usa óculos, meio corpulento, tem olhos claros e tinha o apelido de super chefe na outra firma. Na primeira semana de trabalho, Clark conversa para você para entender o que você estava fazendo. Ele se impressiona bastante com o seu trabalho de até então com a aplicação. Por brincadeira, ele dá o nome de CaprichApp. 

Clark diz que gostou tanto do CaprichApp que vai apresentá-lo para a diretoria (isso deixa no ar a dúvida Delipe Fylon realmente havia feito isso ou estava apenas pensando na Musa do Verão). Antes de transfomar o CaprichApp como um programa completo, Clark sugere criar um menu e deixar os questionários salvos em arquivos. Para conseguir fazer esse processo, ele pergunta se você sabe converter um objeto para json e domina o processo de manipular. 

"Caso você não saiba fazer isso, é recomendado estudar essas duas habilidades ou não será possível fazer um programa legal para apresentar para a diretoria."

Após confirmar que você entende bem esses conteúdos, Clark te passa os requisitos da sua nova atividade:

```
* Adicione um menu iterativo na sua aplicação. O menu terá as seguintes opções:
A) Cadastrar um questionário
B) Listar todos os questionários
C) Excluir um questionário
D) Descrever um questionário
E) Editar um questionário

* Todo questionário terá um id numérico, único e incremental
* Os questionários serão salvos em um diretório temporário (/tmp/caprichapp ou C://temp/caprichapp) chamado de *diretório base*
* Cada questionário será salvo em formato json como um arquivo separado
* No começo da execução, o aplicativo vai percorrer todos os arquivos do diretório base para saber qual é o valor atual do id
* Quando o usuário selecionar a opção cadastrar, ele terá que entrar com os dados para criar o questionário, após isso a aplicação transformará o questionário em um json e irá salvar no diretório base informando que a operação ocorreu com sucesso
* Quando o usuário selecionar a opção listar todos os questionários, a aplicação listar apenas o id e o nome do questionário
* Quando o usuário selecionar a opção excluir, ele informará um id. Após isso, a aplicação vai excluir o arquivo do diretório base e informar que o questionário foi excluído com sucesso
* Quando o usuário selecionar a opção descrever um questionário a aplicação pedirá um id. Tendo esse id, ela irá abrir o arquivo do questionário e escrever na tela o arquivo json
* Quando o usuário selecionar a opção editar, precisará informar um id. Após isso, ele terá que cadastrar as informações de um questionário como se fosse cadastrar, a aplicação excluirá o questionário e cadastrará um novo

```

# Capítulo 5: API
Clark chega extremamente empolgado na sua mesa. Sucesso é pouco para definir o resultado da apresentação do seu protótipo. A diretoria amou o resultado. Um dos diretores é sócio de um grande portal de variedades chamado Fuzz Beed e quer utilizar o CaprichApp o quanto antes no portal. Para que tudo funcione corretamente, o CaprichApp será transformado num servidor REST. Um dos sobrinhos dos diretores estava na reunião e já definou a assinatura das APIs que você deverá usar.  

Clark pergunta se você fez o CaprichApp em Java. Caso você tenha feito, ele consegue te indicar uma [série tutoriais para te auxiliar nesse processo](/posts/como-comecar-programar-java/) para auxiliar no trabalho. Além disso, ele te explica que num servidor REST cada requisição já vem meio que "completa" e usuário é quem irá passar os dados necessários em uma requisição sem interação com menu. Os testes da API podem ser feitos usando uma ferramenta como o curl ou o Postman.

```
Altere o CaprichApp removendo o menu e permitindo que ele atenda requisições REST conforme a especificação abaixo.
POST: /caprichapp/create -> Deve aceitar um JSON com a descrição do questionário e cadastrar ele (semelhante ao menu cadastrar)
GET: /caprichapp/ -> Deve retornar como um json todos questionários existentes (igual ao menu listar tudo)
DELETE: /caprichapp/{id} - Deve excluir um questionário (igual ao menu excluir)
GET: /caprichapp/{id} -> Deve retornar como um json os dados de um único questionár (igual ao menu descrever questionário)
PUT: /caprichapp/{id} -> Deve aceitar um JSON com a descrição do questionário e cadastrar ele (semelhante ao menu editar)
```

# Capítulo 6: Banco de Dados
Nada marcará mais a sua vida do que aquela saída do trabalho. Você estava indo a pé para casa, começou a cair uma garoa fria, sua cabeça estava cheia, a Michele do RH passou o dia todo cobrando a lista de requisitos para a nova vaga e você estava cogitando copiar os requisitos de uma vaga qualquer e mandar para ela apenas para ter um pouco de paz. Por causa desses pensamentos, você não percebe quando eles chegam. No primeiro momento, você não entende o que aqueles dois homens numa moto querem, após algum tempo de berros e confusão você compreend. Então o choque vem.

Eles são da gangue da canibais-comedores-de-desenvolvedores-que-nunca-trabalharam-com-banco-de-dados. Eles querem comer você porque você nunca trabalhou com banco de dados. Quando você entende o plano o desespero assume a sua mente. Depois de muita argumentação, eles permitiram que você reescrevesse o seu último programa para utilizar banco de dados. Seguem os requisitos que eles solicitaram:

```
Altere a aplicação para realizar as operações de leitura/escrita utilizando um banco de dados em vez de utilizar arquivos.
```

# Capítulo 7: Dominação Global

Anos se passam. Clark abandonou a empresa para começar um novo trabalho como jornalista. Porém ele estava confiante de que a empresa ficaria bem em suas mãos. O CaprichApp é líder nos aplicativos de enquete. Porém existe um entrave entre você e a dominação mundial: diferentes sistemas operacionais e configurações. 

Você se lembra de ter lido em algum [blog](http://aleatorio.dev.br){:target="_blank"} que existe uma tecnologia muito poderosa containers. E você decide  experimentar isso para divulgar o seu trabalho e dominar o mundo. 

````
Crie uma imagem docker com o seu aplicativo compilado e pronto para rodar;
Crie um docker-compose incluindo o banco de dados utilizado e a abertura de portas;
Adicione todos os códigos num repositório no github;
Documente esse repositório explicando como fazer para levantar o imagem e utilizar seu servidor;
Cole o link aqui para que todos possam ver sua versão do CaprichApp e você domine o mundo ;)
```