---
title: Configurando sua aplicação Java com Quarkus @ConfigProperty
categories: [Java, Quarkus]
tags: [Java, Quarkus]
---

ntes de configuração, o sistema precisa escolher entre uma delas. 

Ele faz isso seguindo a seguinte ordem:

1. Propriedades do sistema
2. Variáveis de ambiente
3. O arquivo .env
4. O arquivo application.properties
5. O valor default colocado na anotação @ConfigProperty

Se o arquivo não estiver em nenhuma fonte de configuração, a aplicação não subirá e dará um erro 

```shell
javax.enterprise.inject.spi.DeploymentException: No config value of type [<tipo de dado>] exists for: <nome da chave de configuração>
at io.quarkus.arc.runtime.ConfigRecorder.validateConfigProperties(ConfigRecorder.java:37)
```

#Perfil de configuração

Com o Quarkus fica muito fácil trocar as configurações em diferentes situações. Isso é feito através de uma ferramenta bem legal chamada **Perfil de configuração**. 

Dependendo do perfil que estiver sendo utilizado, as configurações utilizadas são diferentes. Isso permite colocar no mesmo arquivo de propriedade as diferentes configurações. 

Por padrão, o Quarkus trabalha com 3 perfis diferentes: o de produção (prod), o de desenvolvimento (dev) e o de testes (test). O perfil de desenvolvimento é ativado quando usamos o comando **mvn quarkus:dev** e o de testes é ativado quando executamos os testes.

![Não me diga](https://dev-to-uploads.s3.amazonaws.com/i/wk7uaa1mas6n6lokjjxl.jpg)

Para exemplificar isso, ainda com o servidor de pé vamos adicionar uma nova linha na no nosso **application.properties**. Com uma propriedade para ser executada com o perfil de desenvolvimento. Nosso arquivo deverá ficar assim: 

```properties
config=Configuração simples
C*O~M_caracteres-especiais!á=Usando caracteres especiais
inteiro=2
#Nova propriedade colocada abaixo
%dev.inteiro=10
```
Após salvar essa alteração e ao acessar a nossa URL, teremos o seguinte resultado.

![Config alterada](https://dev-to-uploads.s3.amazonaws.com/i/n0j449ic1xybtlcdul1u.png)

Como nós usamos o profixo "%dev", então esse novo valor é válido apenas durante o momento de debug. Se a aplicação não estivesse no modo debug então o valor da configuração usado seria outro (no caso o 2).

#Considerações
Sou apaixonado por essa parte de configuração no Quarkus. O que eu mais gosto é que é tão ridiculamente fácil alterar uma propriedade no ambiente de produção (através das variáveis de ambiente). 

Esse post acabou ficando um bem maior do que eu gostaria. Mesmo assim, tive que deixar de fora vários conteúdos bem interessantes. Para aqueles que tem alguma fluência em inglês, recomendo uma boa lida na [documentação oficial](https://quarkus.io/guides/config).

Ah, e o código de hoje pode ser encontrado no [github](https://github.com/lucasscharf/blog-posts-code/tree/master/config).