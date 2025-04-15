---
title: Considerações sobre o uso de IA
categories: [Carreira]
tags:
---

# Introdução
Sou entusiasta de IA. Uso IA no trabalho e no lazer. Porém, sou crítico do mundo corporativo e do hype de IA.
Esse papo de AGI daqui a 6 meses tem o mesmo gosto daquele papo de [só mais 72 horas](https://especiais.g1.globo.com/politica/2023/so-mais-72-horas-acampamento-bolsonaristas-radicais/) que os militares vão ajudar.

As ferramentas são [boas](https://www.ipea.gov.br/cts/pt/central-de-conteudo/noticias/noticias/464-ia-ajuda-medicos-a-detectar-mais-casos-de-cancer-de-mama). Ajudam em um [monte de coisa](https://www.ipea.gov.br/cts/pt/central-de-conteudo/noticias/noticias/349-ferramenta-de-inteligencia-artificial-aprimora-vacina-de-mrna-contra-a-covid-19). [Aumentam a produtividade](https://www.researchgate.net/profile/Prashant-Ahlawat-2/publication/349284327_The_Prospect_of_Artificial_Intelligence_AI_in_Precision_Agriculture_for_Farming_Systems_Productivity_in_Sub-Tropical_India_A_Review/links/606d77294585159de501477c/The-Prospect-of-Artificial-Intelligence-AI-in-Precision-Agriculture-for-Farming-Systems-Productivity-in-Sub-Tropical-India-A-Review.pdf) e [ganham um monte de tempo](https://www.mdpi.com/2071-1050/15/11/8934). Mas estão longe de ser esses oráculos ultra inteligentes que pregam por aí.

Contudo, gostaria de fazer umas reflexões baseadas apenas nas minhas percepções sobre como talvez as expectativas estejam alta e temos muito mais hype do que qualquer outra coisa. Lembrando que não sou pesquisador de IA. Não sou especialista em nada nessa área. Sou apenas um nerd gordinho que está dando seu pitaco. Isso aqui é mais mesa de bar do que produção séria.

# IAs de propósito geral e games

Na imagem abaixo, temos os updates de uma variação do [Claude](https://claude.ai/) para jogar Pokemon. Ele está preso em uma caverna simples. Desafio do nível de uma criança de 6 ou 7 anos. [Até o dia 7 de março](https://www.lesswrong.com/posts/HyD3khBjnBhvsp8Gb/so-how-well-is-claude-playing-pokemon), ele ainda estava preso. Segundo a empresa dona do modelo (A Anthropic) versão do [modelo 3.7](https://www.anthropic.com/research/visible-extended-thinking) teria chego na cidade de Vermillion (cumprindo 2/9 do jogo, mais ou menos) com um custo computacional enorme. Contudo, na live stream, ele ainda estava preso (dica pra vida, nunca confie em uma empresa pra dizer o quão bom seu produto é). 

Para vocês entenderem o quão louco isso é, [um peixe terminou pokémon safira](https://www.polygon.com/2020/11/9/21556590/fish-pokemon-sapphire-stream-twitch) apenas dando comandos aleatórios no jogo. Afinal, ele é um peixe.

![Alt claude jogando pokémon](/images/claude_pokemon.jpeg)

Tudo isso para dizer que os modelos de LLM de agora ainda não estão maduros o suficiente.

# Problemas de IA e escrita de código

E é aí que entra um dos erros mais comuns no uso de IA: a crença cega nas respostas fornecidas. LLMs não são oráculos. Tudo o que geram precisa ser validado, revisado e contextualizado. O que parece uma resposta precisa pode ser um erro sutil, uma alucinação, um dado desatualizado ou qualquer outra abobriga.

Em especial, temos que tomar muito cuidado ao usar ferramentas como o copilot e cursor. Essa [issue de 2024](https://github.com/microsoft/vscode-copilot-release/issues/847) (duplicada dessa [issue de 2023](https://github.com/microsoft/vscode-copilot-release/issues/560)) apontava que o copilot estava usando dados até 2021. Isso quer dizer que, em 2024, a gente tinha um gap de 3 anos de inovação que não foi usada porque os modelos não sabiam que elas existiam. Digo mais, muitos modelos são treinados com cógido ruim, isso quer dizer que confiar cegamente no código gerado pelo modelo vai te levar a umas pérolas como essa daqui que o copilot me trouxe:

![Alt copilot escrevendo código ruim](/images/copilot_escrevendo_codigo_ruim.jpg)

Isso é código de nível abaixo de estagiário. Existem [pesquisas sérias](https://arxiv.org/abs/2307.12488) para tentar formular essa engenharia de prompt para podermos usar melhor essas ferramentas. Talvez eu tenha falhado na minha utilização, talvez seja culpa do treinamento.

# Minhas experiências
Em algum lugar do bluesky, disseram que [IA é um dev júnior com a confiança de um Linus Torvalds que cheirou meio quilo de cocaína](https://bsky.app/profile/cefzanella.bsky.social/post/3lmi3qk2ww22a). Acho que ela tá mais pra estagiário empolgado com confiança (e cocaína). Usei o copilot para testar o desenrolar dele e vocês viram o tipo de if que ele escreve.

Falando do meu uso com o copilot com VS code. Não gostei da parte de agente porque ele é lento e não posso mexer no código. Além disso, se tu fizer alteração de agente no meio do código seu que tem alteração, tem chance dele alterar seu código e estragar o que foi feito (passei um tanto de raiva com isso).

Minhas dores foram mitigadas quando passei a usar a IA apenas para responder pergunta e gerar código. Dessa forma, consigo trabalhar enquanto a IA pensa. Daí coloco o código de exemplo para definir o contexto para ele e digo "considerando a classe X, reproduza o comportamento Y".

Além disso, ele tem a opção de auto-complete mais eficiente. Isso aí eu achei legal porque ajudou a escrever pequenos trechos de código apenas dando tabs. Essa parte eu achei bem legal mesmo. Teve uns trechos que ele sugeriu que não foram muito bons (aquele if), mas, no geral, são boas soluções.

Fiquei e ainda fico com a sensação de que eu sou burro e não sei usar as ferramentas porque o que tem de gente dizendo que é a nona maravilha do mundo (a oitava a gente sabe que é [bolo de chocolate](/posts/Quarkus_jpa_panache/)) ou que conseguem entregar uma aplicação inteira só com alguns prompts (vou falar sobre essa segunda parte mais pro futuro).

# Conclusão

IA é algo que está em hype. O hype em torno da IA frequentemente ignora essas limitações. Se um modelo não consegue sair da primeira caverna no Pokémon, como ele vai conduzir a humanidade a uma era de superinteligência em seis meses? A narrativa de uma AGI iminente se parece mais com promessas militares sobre prazos eternamente renováveis: sempre está logo ali, a poucos meses de distância, mas nunca chega.

As ferramentas são úteis, produtivas e revolucionárias em muitas áreas, mas também são limitadas, previsíveis dentro de certos padrões e totalmente dependentes de dados e da supervisão humana. Se usarmos IA sem entender suas limitações, o problema não está na tecnologia, mas está na soberba de quem acha que prompt resolve tudo e esquece de pensar.

