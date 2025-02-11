---
title: Como fazer um teste de performança/teste de stress
categories: [Arquitetura]
tags: 
---
# Introdução
Nesse domingo (09/02/2025) ocorreu a final do futebol americano. Também conhecido como superbowl. Na firma, esse é um grande dia e começamos nossa preparação com meses de antecedência. Em particular, o único ponto alto do meu dia foi ter [criado uma música dizendo como eu estava entediado](https://suno.com/song/66ec8384-386f-4518-87db-90ec5bad42e7). Isso só aconteceu porque já havíamos testado bem a performance do sistema e sabíamos o quanto nós conseguíamos aguentar. Como gosto de compartilhar conhecimento, montei esse pequeno e simples guia explicando como funciona para criar um teste de performance ou teste de stress. Porém, primeiro, vamos aos conceitos.

* **SLA:** Um SLA (Acordo de Nível de Serviço) é um contrato que define os níveis mínimos aceitáveis de desempenho, disponibilidade e qualidade de um serviço. Ele estabelece métricas como tempo de resposta, vazão média e taxa de erro máxima permitida. 
* **Teste de performance:** O teste de performance avalia como um sistema se comporta sob diferentes condições de carga. O objetivo é garantir que a aplicação atenda aos requisitos do SLA.
* **Teste de stress:** O teste de stress leva o sistema além dos seus limites normais para observar como ele lida com situações extremas. Trocando em miúdos, ele serve pra ver onde a aplicação quebra.

# O processo
Apesar de ser algo bem divertido e interessante, o processo de teste de performance e stress é bem metódico. É tão metódico, que consigo até escrever um algoritmo para ele:

```
1. Definir os níveis de SLA e outras restrições 
2. Montar o cenário(s) de teste(s) e monitoramento
3. Enquanto os níveis de SLA e restrições não forem atingidos
3.1 Executar o teste de stress/performance
3.2 Identificar onde o sistema quebrou e porque
3.3 Aplicar alterações necessárias
```

E é isso. A parte legal é está em identificar os motivos do sistema ter quebrado e propor as soluções. Porém, no geral, o processo acaba seguindo um método bem definido. Vamos explorar cada um desses tópicos e com algumas considerações.

## Definir os níveis de SLA e outras restrições
Já dizia meu amigo Pedro: Todo mundo quer comer batata frita, mas ninguém quer descascar o turbéculo. Particularmente, acho essa uma das etapas mais importantes. É bem importante definir exatamente o que nós estamos querendo testar. É possível ter um sistema que tem vazão de 5 mil transações por segundo, mas uma transação dura 2 dias para ser processado. É isso o que nós queremos? Definir a SLA é muito importante. Porém, essa decisão precisa estar alinhada com os objetivos e necessiades do negócio. Por exemplo, se o seu sistema pode ter latência de 15 minutos, a forma de você trabalhar é bem diferente do sistema que tolera uma latênciad e 10ms. Outro exemplo, você cria a solução ideal porém ela precisa que você alugue um supercomputador para funcionar corretamente - essa solução pode ser economicamente inviável. Por isso é importante alinhar com o time de negócios o que eles esperam da performance do sistema. Alguns itens interessantes para observar nessa etapa:
* Latência: tempo para processar uma requisição
* Vazão: quantidade de requisições processadas por unidade de tempo (por exemplo requisições/segundo)
* Taxa de erro: Qual a porcentagem de requisições podem não respeitar o SLA acordado
* Custo total: o quanto que vai custar para manter a sua solução funcionando. Aqui vale a pena descobrir o quanto a sua solução aguenta e, caso ela seja uma solução elástica, qual é o custo por requisição. Dependendo de como os contratos estão estruturados, a sua empresa pode optar por perder transações para não ficar no prejuízo.

## Montar o cenário(s) de teste(s) e monitoramento
Uma vez definido o SLA, é necessário montar os cenários de teste. Esses cenários devem refletir os fluxos que você quer analisar. Também é importante verificar se, na sua aplicação, existem outros fluxos concorrentes que podem afetar a performance e que precisam de atenção e serem modelados em conjunto. Existem três observações muito importante ao montar os cenários de testes e monitoramento:
* Máquina que roda os testes deve ser uma máquina diferente da que roda a aplicação. Se forem na mesma máquina, pode haver uma briga de recurso entre o teste e aplicação.
* Tomar cuidado com o excesso de monitoramento. Monitorar é essencial, mas exagerar nisso pode acabar gerando uma disputa por recursos. Para ilustrar, imagine uma aplicação que salva dados em disco e escreve logs no mesmo local. Sempre que um log é gravado, o sistema precisa mover o cabeçote do disco para a posição correta, consumindo tempo que poderia estar sendo usado para salvar arquivos de forma mais eficiente. Isso não significa que o monitoramento seja ruim, mas é importante garantir que ele não atrapalhe o desempenho da própria aplicação.
* Cuidado com sistemas externos: Seus provedores de serviços externos tem ambiente para testes de stress? Você paga por requisição feita ao ambiente externo? Eles estão cientes desses casos? Muitas vezes, é interessante utilizar simuladores ou mocks desses serviços para focar nas coisas que você pode controlar.

## Enquanto os níveis de SLA e restrições não forem atingidos/Executar o teste de stress/performance
Agora entramos no nosso loop. Se tivermos sorte, no momento que executarmos o primeito teste, vamos descobrir que a aplicação está perfeitamente dentro do SLA e podemos ir tomar um caldo de cana. Se não, vamos para os próximos passos.

![caldo de cana image](/images/caldo.jpg)

## Identificar onde o sistema quebrou e porque
A farramenta a ser utilizada aqui é o gráfico de vazão por latência. Conforme você vai processando mais requisições, chegará um momento onde a sua aplicação vai aumentar mais e mais os tempos de resposta (assumindo que você não está num ambiente elástico adicionando mais recursos e consegue escalar horizontalmente a sua aplicação). No momento que a sua latência subir descontorladamente, é o momento que a performance da sua aplicação quebrou. Achado esse momento, é necessário observar o momento anterior e entender quem são os gargalos de performance nesse ponto. Algumas opções possíveis de gargalos são:

* Gargalos de CPU e memória: Se o uso da CPU estiver constantemente em 100% ou houver um consumo excessivo de memória que obriga a aplicação a fazer [swap em disco](https://diolinux.com.br/tutoriais/o-que-e-memoria-swap.html).
* Dependências externas: Se o sistema depende de APIs externas, filas, serviços de terceiros, bancos de dados, etc. Aqui temos fortes problemas de I/O.
* Concorrência e sincronização: Aplicações altamente concorrentes podem sofrer com bloqueios ou contenção de recursos.

## Aplicar alterações necessárias
Depois de identificar o problema, a solução pode envolver ajustes na aplicação, na infraestrutura ou até no próprio SLA. Algumas ações comuns incluem:

* Otimizações de código: Melhorar código, reduzir operações, usar cache, evitar operações inúteis.
* Não fazer operações: dependendo do seu negócio, talvez você consiga deixar algo para ser executado depois. Ou memos se existem etapas do processo que não precisam ser mais executados.
* Ajustes na infraestrutura: Adicionar mais máquinas, balanceadores de carga ou otimizar a configuração de servidores.
* Melhoria no banco de dados: Criação de índices, ajuste de consultas, reescrita de queries.
* Revisão de configurações: vocês ficariam enjoados se soubessem como a gente perde dinheiro com configuração errada.
* Revisão do SLA: Se os requisitos de performance forem irreais para a estrutura disponível, pode ser necessário reavaliar o SLA com o time de negócios.
Após cada mudança, a gente volta lá pro topo. O teste deve ser repetido para validar se o problema foi resolvido e se o sistema agora atende aos requisitos. É importante fazer uma única mudança por vez para ter certeza que foi determinada mudança que garantiu o resultado esperado.

# Conclusão
É massa e é gostoso. Ao mesmo tempo que é chato e metódico. Porém, fazer testes de stress e performance trazem tranquilidade para os momentos de pico da aplicação ou situações onde você sabe que ela vai apanhar mais do que pandeiro no pagode. No final, a ideia é garantir que o sistema possa lidar com os desafios esperados sem sustos e sem precisar correr atrás de correções de última hora quando um evento de grande impacto acontecer.