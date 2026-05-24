---
title: A fotografia, fundamentos e as falácias do mundo distribuído
categories: [Carreira]
tags: [Reflexões]
---

# Introdução

Lembro que na minha brevíssima carreira no mundo da fotografia, li em vários lugares que era muito mais importante o olhar da pessoa do que o equipamento em si. Um bom fotógrafo consegue fazer fotos muito melhores com um equipamento amador do que o contrário. A câmera ajuda, mas é o olho treinado que decide o enquadramento, a luz, o instante certo. Sem isso, a câmera mais cara do mundo continua sendo apenas uma câmera cara.

Tenho pensado bastante nisso quando vejo as discussões sobre as novas ferramentas que aparecem no mercado todo ano. Framework novo, linguagem nova, abstração nova. Quem domina os fundamentos consegue tirar bom proveito de cada uma delas. Quem nunca aprendeu o básico fica perdido assim que a ferramenta começa a vazar pelas beiradas e mostrar que o problema continua sendo o mesmo de sempre.

# Fundamentos não saem de moda

A indústria de software adora uma novidade. Toda hora aparece um framework que promete resolver tudo, uma abstração que esconde a complexidade, uma ferramenta que faz o trabalho duro por você. Tudo isso é legal e ajuda bastante. Mas quando o sistema cresce, quando a carga aumenta, quando o cliente reclama, são os fundamentos que aparecem na conta.

Eventualmente, uma meia dúzia de pessoas sem capacidade técnica vai conseguir criar coisas que funcionam em contextos específicos. Inclusive, vão fazer um dinheiro interessante com isso. Aconteceu em tempos antigos e continua acontecendo agora. Muita gente boa se esforçou para simplificar as coisas, criar frameworks, criar abstrações, escrever ferramentas que escondem a complexidade. Sempre que isso acontece, abre uma janela para que gente com menos preparo técnico produza algo que funciona. Por um tempo. Quando a abstração quebra, e ela sempre quebra, alguém com fundamento vai precisar entrar para entender o que está acontecendo por baixo do capô.

# Rede é fundamento

De todos os fundamentos que a gente precisa lembrar ao escrever software, rede é um dos mais negligenciados. Talvez porque, no localhost, tudo parece dar certo. O processo conversa com o banco, o banco responde, a fila funciona, o cache está sempre disponível. Aí o sistema vai para produção e começa a aparecer aquele monte de problema esquisito que ninguém previu. Timeout estranho, retentativa em loop, conexão morrendo no meio da requisição, dado que sumiu sem deixar rastro.

Por isso, deixo a recomendação de leitura: as falácias da computação distribuída, creditadas a L. Peter Deutsch. São oito afirmações que parecem óbvias quando você está olhando para o seu localhost, mas que viram cilada assim que o sistema cresce. Como sou a garota de anime mais legal do mundo, já trago aqui um resumo dessas falácias.

## 1. A rede é confiável

Não é. Cabo cai, switch reinicia, roteador trava, o provedor faz manutenção sem avisar. Toda chamada que sai da sua máquina pode não chegar do outro lado, ou pode chegar e a resposta não voltar. Sua aplicação precisa pensar em retentativa, *timeout* e o que fazer quando a requisição simplesmente se perde no meio do caminho.

## 2. A latência é zero

Não é. Cada chamada para outro processo, outro servidor, outro datacenter, tem um custo de tempo. Localmente parece de graça, mas em produção, com clientes do outro lado do país ou do oceano, esse custo é percebido. Quando você empilha chamada em cima de chamada, o tempo total vira problema sério.

## 3. A banda é infinita

Não é. Mesmo com fibra ótica, você divide recurso com outras aplicações e outros usuários. Se a sua aplicação manda *payload* gigante para todo lado, alguma hora vai apertar. Pior ainda em ambientes móveis, onde a banda real depende do sinal do celular do usuário.

## 4. A rede é segura

Não é. Qualquer pacote que sai da sua máquina pode ser interceptado, modificado ou registrado em algum lugar do caminho. Assuma criptografia e autenticação como padrão, não como extra. E lembre que segurança não é só rede: é também controle de acesso, validação de entrada e auditoria do que está rodando.

## 5. A topologia não muda

Muda. Máquinas entram e saem do cluster, IPs mudam, balanceadores são trocados, datacenters são desligados. Sua aplicação não pode assumir que o endereço de hoje é o mesmo de amanhã.

## 6. Existe um único administrador

Não existe. Diferentes pedaços da infraestrutura têm donos diferentes, prioridades diferentes e agendas diferentes. O time de rede não fala com o time de banco. O fornecedor da API externa não fala com você. Coordenar mudança entre todos esses atores é parte do trabalho e costuma ser a parte mais difícil.

## 7. O custo de transporte é zero

Não é. Tráfego entre regiões custa dinheiro na fatura da nuvem. Serializar e deserializar mensagens custa CPU. Manter conexão aberta custa memória. Cada *hop* tem boleto no fim do mês, e em alguns casos esse boleto cresce mais rápido do que o uso da aplicação.

## 8. A rede é homogênea

Não é. Protocolos diferentes, versões diferentes, sistemas operacionais diferentes, ordenação de bytes (sim, máquinas diferentes colocam ordens diferentes (leia *sobre big endian* e *little endian*)) diferente. Mesmo que você controle os dois lados da conexão hoje, alguma hora vai precisar conversar com algo que não controla. E aí descobre que o JSON do outro lado tem um campo a mais, ou que o cliente antigo não entende o novo formato.

# Voltando para o ponto principal

A lista do Deutsch parece básica, mas é mais comum do que deveria ver gente experiente escrevendo código que assume rede confiável, latência zero e topologia fixa. Fundamento não é coisa de provinha de faculdade que você esquece depois que termina. Fundamento é o que sustenta o trabalho no dia a dia, principalmente quando a ferramenta da moda não dá conta do recado.

Continue estudando o básico. É menos glamoroso do que aprender o framework do mês, mas é o que faz o seu sistema continuar de pé quando o cabo cai, o IP muda e a banda aperta. Igual ao olho treinado do fotógrafo, o fundamento é o que aparece na hora em que a câmera nova já não basta.
