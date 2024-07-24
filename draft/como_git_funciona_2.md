---
title: Como é o modelo distribuído de versionamento do git?
categories: [git]
tags: [Ferramentas, git]
---

# Introdução
Na [última postagem]() nós vimos que o git é um VCS (Sistema de Controle de Versão) que trabalha com um modelo de fotografias de documentos chamados commit. 
Lembrando que o commit contém apenas as alteraçãoes feitas em relação com o commit anterior. Ao ler todos os commits, conseguimos sair de um documento vazio até a sua versão atual. 
Na postagem de hoje do nosso [curso de Git](/posts/introducao_curso_git) vamos entender o conceito de VCS distribuído. 

Ser um VCS distribuído significa que os commits estão copiados em diferentes máquinas. Para entender bem isso, existem alguns conceitos importantes que nós temos aprender. Ao longo do curso, vamos voltar várias vezes nesses conceitos. Tudo bem não entender tudo em uma tacada só, aos poucos, com a repetição tudo ficará mais claro. Esses conceitos são: diretório de trabalho, repositório e área de estagiamento. 

# Diretório de Trabalho (working directory)

Os antigos chamavam diretório de pasta. Como você é alguém jovem, moderno e da área de TI. Você vai falar diretório. Diretório é um caminho dentro do seu computador. Um diretório de trabalho é o diretório que você está trabalhando (duh). Ele vai conter a versão atual dos seus arquivos e um diretório oculto chamado `.git`. Nesse diretório, tem algumas configurações e todos os commits que já foram feitos. 

Quando você faz uma alteração no seu arquivo, essa alteração não está presente no git porque não foi feito a fotografia (commit) da alteração. Dessa forma, você pode fazer as alterações que quiser sem medo. Quando você estiver satisfeito com as alterações, então você poderá fazer um commit para fazer o controle de versão dessas mudanças.

# Área de Estagiamento (stagging area)

A área de estagiamento (também chamada de área de preparação) é uma etapa intermediária para a criação de um commit. Apesar de ser chamada de "área", não é algo que é visível no seu computador como uma região propriamente dita. Podemos considerar que a área de estagiamento como um estado da sua alteração.

Nessa "área", você consegue verificar se as mudanças que você vai fazer persistir em um commit são exatamente o que você está querendo persistir. Isso é muito útil para revisar/desfazer alterações ou mesmo preparar para fazer o commit em várias partes. 

Por exemplo, se você quiser fazer um commit com vários arquivos diferentes, você poderá ir mandando cada alteração arquivo para a área de estagiamento. Após mandar todas as mudanças, você faz o commit das alterações desejadas.

# Repositório (repository)

Algumas explicações acabam misturando os conceito de repositório e diretório de trabalho. O repositório é, em essência, aquele diretório `.git`. Todas as alterações feitas em todos os arquivos que estão persistidas em commits estão salvos nesse diretório. Quando criamos um commit, estamos adicionando um novo registro com as alterações feitas dentro desse diretório.

Cada arquivo que está sendo gerenciado pelo git pode estar em um dos três estados: 
* Modificado (*Modified*): a alteração foi feita no diretório de trabalho, porém não foi commitada ainda.


* Estagiada (*Staged*): a alteração foi feita, colocada na área de estagiamento, porém não foi salva no repositório.

* Commitada (*Commited*): a alteração foi feita, estagiada e persistida no repositório.

Vamos ter duas imagens importantes para visualizar. A primeira é sobre os três estados e mudanças de estados. A segunda, e mais importante, é do meu gato Apollo descansando.

![Alt 3 estados do git e suas transições](/images/git3stages.png)

As setas indicam as alterações que são feitas: do repositório para o diretório de trabalho nós iniciamos o projeto. Do diretório de trabalho para a área de estagiamento, nós armazenamentos previamente as alterações. Depois nós commitamos (criamos a fotografia) as alterações e salvamos no nosso diretório `.git`.

Agora, vamos ficar um bom tempo olhando o Apollo antes de falarmos sobre a parte distribuída do git.

![Alt apollo descansando](/images/apollo_descansando.png)


# VCS distribuído

Você se lembra que lá no começo do texto, nós falamos que o git é um VCS distribuído? Ele faz isso mantendo repositórios (o diretório `.git`) locais e remotos. O repositório local é que está na sua máquina e o repostório remoto é o que está outra. Em qual máquina? Não sabemos. Também não importa. O que importa é que são máquinas diferentes e existe todo um trabalho importante do git para fazer a sincronização desses repositórios.

Quando trabalhamos com o Git, é importante informar ao sistema quais são (e onde estão) os repositórios remotos. Isso nos permite, após fazer um commit em nosso repositório local, utilizar comandos especiais para sincronizar nossos repositórios, enviando os commits para os repositórios remotos.

De forma semelhante, também podemos obter commits de um repositório remoto e incorporá-los ao nosso repositório local. Se desejarmos, podemos até mesmo aplicar essas alterações ao nosso diretório de trabalho, garantindo que temos as versões mais recentes dos arquivos em que estamos trabalhando.

A figura abaixo demonstra todas as áreas do Git e alguns comandos que nós usamos e que permitem a moviementação dos dados. Não se preocupe muito se você não entender todos os detalhes agora. Quando finalizar o curso você vai olhar para ela e verá que tudo faz sentido. 
![Alt apollo descansando](/images/areas_git_comandos.png)

E por hoje é isso, na próxima aula vamos estudar o terceiro conceito importante para o entendimento do funcionamento do Git, a árvore de commits. Agora temos exercícios.

---
A) Pergunta: Suponha que você criou um novo arquivo chamado documento.txt no seu diretório de trabalho e fez algumas alterações. Explique em qual estado o arquivo se encontra imediatamente após a alteração. Quais são as etapas para movê-lo para o estado de "comitado"?

B) Descreva as vantagens do modelo distribuído de versionamento em comparação com um modelo centralizado. Considere aspectos como segurança, colaboração, e flexibilidade no desenvolvimento.
