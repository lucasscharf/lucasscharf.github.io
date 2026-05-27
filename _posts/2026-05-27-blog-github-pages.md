---
title: Como montar um blog usando GitHub Pages
categories: [Tutoriais]
tags: [Blogging, GitHub]
---

# Introdução

Volta e meia alguém me pergunta como esse blog aqui foi montado. Algumas pessoas imaginam um esquema complicado, com servidor, banco, painel de administração e dashboard cheio de gráfico. Não tem nada disso. É só um repositório no GitHub, um tema pronto, alguns arquivos de texto e a magia do git. Esse post é um guia curto para você montar algo parecido sem ter que pagar hospedagem, sem ter que instalar painel de WordPress, sem precisar lidar com atualização de plugin todo mês e gerenciando tudo com o git.

![Its magic meme](/images/magic.gif)

# O que você ganha com isso

GitHub Pages é o serviço gratuito do GitHub que pega o conteúdo de um repositório e publica como um site estático. Estático aqui quer dizer que não tem código rodando no servidor a cada requisição. O HTML já está pronto e o servidor só entrega o arquivo. É rápido, é barato **de graça** e é difícil de quebrar.

A combinação usa GitHub e Jekyll. O Jekyll é um gerador de site estático escrito em Ruby que transforma seus arquivos markdown em HTML. Você escreve o post em markdown, joga pro git e o github age como nosso Gandalf fazendo a magia. É o fluxo que uso aqui. E como vocês podem ver, funciona.

# Pré-requisitos

Antes de começar, você precisa de:

- Uma conta no GitHub.
- Git instalado na sua máquina.
- Saber escrever em markdown e usar o git.

Se você quer rodar o site localmente antes de publicar, vai precisar também de Ruby e Bundler. Dá para pular essa parte e ir testando direto no GitHub, mas é menos confortável.

# Passo 1: escolher um tema e criar o repositório

Jekyll tem temas para todo gosto. Você pode escrever um do zero, mas é trabalhoso, não acho que vale a pena e não sei fazer. Eu peguei o [Chirpy](https://github.com/cotes2020/jekyll-theme-chirpy). Você pode escolher um usando a busca do github. No presente momento, temos 1967 [templates](https://github.com/topics/jekyll-theme). 

Após escolher, faça um clone do repositório e seja feliz (alguns examplos tem guias de uso que facilita bastante a vida). Preste atenção que aqui é importante, ao clonar o repostório, você precisa por o nome `seuusuario.github.io`. Esse nome é especial: o GitHub reconhece esse padrão e publica o conteúdo automaticamente no endereço `https://seuusuario.github.io`. No meu caso é `lucasscharf.github.io`, que dá origem ao endereço original do blog antes do domínio próprio. Você pode colocar outro nome, mas fica algo estranho. A URL fica `https://seuusuario.github.io/nome-do-repo`. Falta elegância.

# Passo 3: configurar o blog

O coração da configuração é o arquivo `_config.yml`. Aqui vão alguns trechos importantes do que eu uso aqui no blog:

```yaml
theme: jekyll-theme-chirpy
lang: pt-BR
timezone: America/Sao_Paulo
title: Códigos Aleatórios
tagline: Reflexões sobre programação, carreira e muitos tutoriais
url: "https://aleatorio.dev.br"
github:
  username: lucasscharf
social:
  name: Aleatório
  email: lucas.scharf@gmail.com
  links:
    - https://twitter.com/aleatoriodevbr
    - https://github.com/lucasscharf
    - https://www.linkedin.com/in/aleatoriodevbr
avatar: ./images/java-chan.jpeg
paginate: 10
```

Vale ler o arquivo inteiro com calma e as instruções do README do repositório que você escolher. Tem opções para comentários (uso Disqus), Google Analytics, tema claro/escuro, busca, etc. É no seu arquivo de configuração que o blog terá a cara que você quer dar.

# Passo 4: escrever um post

Posts ficam na pasta `_posts/` e seguem o padrão de nome `YYYY-MM-DD-titulo-do-post.md`. Cada arquivo começa com um cabeçalho YAML que define metadados. Por exemplo:

```markdown
---
title: Como montar um blog usando GitHub Pages
categories: [Tutoriais]
tags: [Blogging, GitHub]
---

# Introdução

Volta e meia alguém me pergunta como esse blog aqui foi montado. Algumas pessoas imaginam um esquema complicado, com servidor, banco, painel de administração e dashboard cheio de gráfico. Não tem nada disso. É só um repositório no GitHub, um tema pronto, alguns arquivos de texto e a magia do git. Esse post é um guia curto para você montar algo parecido sem ter que pagar hospedagem, sem ter que instalar painel de WordPress, sem precisar lidar com atualização de plugin todo mês e gerenciando tudo com o git.

![Its magic meme](/images/magic.gif)
```

O resto é markdown puro. Você pode colocar código com imagens, links, iframes do YouTube, citações, listas e tudo o que o markdown permite. As imagens costumam ficar na pasta `images/` ou `assets/`. Para um humilde blog de desenvolvimento de software que tenta ser o melhor blog do mundo, é o suficiente.

# Passo 5: criar páginas fixas

Se você quer páginas tipo "Sobre", "Portfólio", "Arquivo", elas ficam em `_tabs/`. Cada arquivo é uma aba do menu lateral. Eles tem um cabeçalho como qualquer postagem e pode tem um ícone e uma ordem. O cabeçalho é o mesmo dos posts, porém, tem a vantagem de você escolher um ícone e uma ordem para o mesmo.

# Passo 6: dar push e ver no ar

Preparados?
Agora vem o passo mais difícil.
Commitar e fazer o push.
E é isso.

O GitHub roda o Jekyll automaticamente via [GitHub Actions](https://github.com/features/actions) e publica o resultado. Se algo der errado, a aba *Actions* do repositório mostra o log do build (acho que por padrão, o github também manda e-mail). É bom dar uma olhada ali sempre que mexer em coisa nova.

# Passo 7 (opcional): usar domínio próprio

Se você quer um endereço bonito, igual ao `aleatorio.dev.br` que eu uso, basta comprar um domínio (Registro.br, Namecheap, Cloudflare, etc) e configurar dois itens:

- No repositório, crie um arquivo `CNAME` na raiz com o domínio (sem `https://` e sem `/`). Nesse blog, o arquivo só tem `aleatorio.dev.br` dentro.
- No painel do seu provedor de domínio, aponte um registro `CNAME` ou `A` para os endereços do GitHub Pages, conforme a [documentação oficial](https://docs.github.com/en/pages/configuring-a-custom-domain-for-your-github-pages-site).

# Conclusão

GitHub Pages com Jekyll é uma escolha excelente para blog de texto. Não é tão boa para coisas dinâmicas tipo formulário com persistência, lojinha ou qualquer coisa de área logada. A grande vantagem é que o conteúdo do seu blog vira um arquivo de texto versionado. Acabou plataforma que sumiu, sem amarras com ferramenta, tudo versionado e registrado. Se um dia o GitHub Pages morrer, você pega as suas coisas e vai pro próximo.

Esse blog que você está lendo agora é exatamente isso: um repositório público no GitHub, um tema pronto, uns arquivos markdown e o tempo livre de um nerd gordinho genérico. Se eu consegui, você consegue.