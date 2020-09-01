# API - Produtos

Projeto disponibiliza 2 apis para cadastro de produtos e de url com imagens de produtos.
 

## Visão geral

O projeto é uma aplicação back-end java com objetivo de demonstrar a construção de uma API utilizando os frameworks [Spring Boot](https://projects.spring.io/spring-boot), [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html) , [Spring Web](https://spring.io/projects/spring-ws) e [Spring Kafka](https://spring.io/projects/spring-kafka) em conjunto.

---
#### Temas
1 - Tecnologias

2 - Setup da aplicação (local)

3 - Instalação da aplicação


---

## 1 - Tecnologias

- [Spring Boot](https://projects.spring.io/spring-boot) é uma ferramenta que simplifica a configuração e execução de aplicações Java stand-alone,  com conceitos de dependências “starters”, auto configuração e servlet container embutidos é proporcionado uma grande produtividade desde o start-up da aplicação até sua ida a produção.
 
- [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html) é um framework já consolidado no mercado, que a partir da versão fornece mecanismos simplificados para a criação de APIs RESTful através de anotação, além disso, também possui recursos de serialização e deserialização de objetos de forma transparente 
 
- [Spring Web](https://spring.io/projects/spring-ws) é um produto da comunidade Spring focado na criação de serviços da Web controlados por documentos.

- [Spring Kafka](https://spring.io/projects/spring-kafka) aplica os conceitos básicos do Spring ao desenvolvimento de soluções de mensagens baseadas em Kafka.
> Compilação do projeto realizado com Maven / IntelliJ IDEA

---
   
Estrutura do projeto
 ```
br.com.awm.pantherproducts
config
part01_products
part02_urls
part03_manager
 ```
 
 
# 2 - Setup da aplicação (local)

## Pré-requisito

Antes de rodar a aplicação é preciso garantir que as seguintes dependências estejam corretamente instaladas:
```
Java 8
Maven 3.1.0
Docker
```
## 3 - Preparando ambiente Docker (Kafka e MongoDB) 

Considerando que o docker-compose esteja up.

```
Start Ambiente
Execute o arquivo start-servers.sh na raiz do projeto

sh start-servers.sh

log...
Creating panther-products_zookeeper_1 ... done
Creating panther-products_mongodb_1   ... done
Creating panther-products_kafka_1     ... done
Creating panther-products_kafdrop_1   ... done

```

```
Stop Ambiente
Execute o arquivo stop-servers.sh na raiz do projeto

sh start-servers.sh

log...
Stopping panther-products_mongodb_1   ... done
Stopping panther-products_kafdrop_1   ... done
Stopping panther-products_kafka_1     ... done
Stopping panther-products_zookeeper_1 ... done
Removing panther-products_mongodb_1   ... done
Removing panther-products_kafdrop_1   ... done
Removing panther-products_kafka_1     ... done
Removing panther-products_zookeeper_1 ... done
Going to remove panther-products_mongodb_1, panther-products_kafdrop_1, panther-products_kafka_1, panther-products_zookeeper_1

```


## 4 - Instalação da aplicação 

Primeiramente, faça o clone do repositório:
```
https://github.com/antoniny/phanter-products.git
```
Acesse a pasta do projeto:
```
cd phanter-products
```
É preciso compilar o código e baixar as dependências do projeto:
```
mvn clean package
```
Finalizado esse passo, vamos iniciar a aplicação:
```
mvn spring-boot:run
```

A api já deverá estar disponível em 
>http://localhost:8888/


Swagger: http://localhost:8888/products/swagger-ui.html#/

```
.



   
   
