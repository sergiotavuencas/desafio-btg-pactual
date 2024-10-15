# Desafio de Microsserviço pela BTG Pactual

## Documentação de Execução
Este documento fornece um passo a passo para auxiliar na execução do projeto **Desafio Lista de Tarefas pela Simplify**.

## Pré-requisitos
Antes de tudo, certifique-se de ter o [Git](https://git-scm.com) e [Docker](https://docs.docker.com/engine/install/)
instalados e configurados em sua máquina.

## Passo a Passo

### 1. Clonando o Repositório
Clone o repositório no diretório de sua preferência utilizando o comando abaixo:
```
$ git clone https://github.com/sergiotavuencas/desafio-btg-pactual-orderms.git
```

### 2. Acessando o Diretório do Projeto
Acesse o repositório em sua máquina local com o comando:
```
$ cd desafio-btg-pactual-orderms
```

### 3. Executando o Projeto
Agora basta executar o seguinte comando para iniciar o projeto:
```
$ docker compose up -d
```


### 4. Verificando a Execução
Certifique-se de que os contêineres `desafio-btg-pactual-orderms-rabbitmq-1`, `orderms_api` e 
`desafio-btg-pactual-orderms-mongodb-1` em ***NAMES*** estão em execução. Você pode verificar isso com o comando:
```
$ docker ps
```

### 5. Acesso ao RabbitMQ Management:
Acesse o [RabbitMQ Management](http://localhost:15672) e insira o seguinte dado para `username` e `password`: 
```
orderms
```

### 6. Criando uma Order:
Link para o acesso direto a fila, caso consiga acessar, pule os dois primeiros passos abaixo:
[btg-pactual-order-created](http://localhost:15672/#/queues/%2F/btg-pactual-order-created)
- No RabbitMQ Management, acesse a aba `Queues and Streams`. 
- Em seguida clique em `btg-pactual-order-created`.
- Busque por `Publish message`.
- Insira o seguinte JSON em `Payload` e clique em `Publish message`.
```
   {
       "codigoPedido": 1001,
       "codigoCliente":1,
       "itens": [
           {
               "produto": "lápis",
               "quantidade": 100,
               "preco": 1.10
           },
           {
               "produto": "caderno",
               "quantidade": 10,
               "preco": 1.00
           }
       ]
   }
```

### 7. Requisição da Order
- Para a requisição através de um terminal, execute o seguinte comando:
    ```
    curl -X GET "http://localhost:8080/customers/1/orders"
    ```
- Para a requisição utilizando uma Interface, como Postman ou Insomnia, acesse o seguinte link e faça uma requisição do
tipo GET:
    ```
    http://localhost:8080/customers/1/orders
    ```

### 8. Parando os Contêiners
Para parar todos os contêineres em execução, use o seguinte comando:
```
$ docker compose down
```

## Importante

Não é necessário ter o Java, MongoDB e RabbitMQ instalado em sua máquina, pois o Docker se encarrega de fornecer todas as dependências
necessárias para a execução da aplicação.