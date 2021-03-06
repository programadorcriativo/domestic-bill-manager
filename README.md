#Domestic Bill Manager

API Rest para controle de contas domésticas desenvolvida no nosso canal do Youtube.

Acesse a nossa playlist para aprender a desenvolver um aplicativo web completo com `SpringBoot` e `Kotlin`.

Você vai aprender `testes integrados`, persistência com `JPA`, `boas práticas` para manipulação de `erros`, `paginação` e `documentação de API`.

## Branches

A evolução do projeto foi desenvolvida utilizando branches. 
As branches abaixo foram desenvolvidas em uma ordem lógica para melhor aprendizagem das tecnologias envolvidas. <br>
Basta seguir nossa playlist no youtube, cada aula é uma branch.

- **feature/part-1-project-structure**: Aula 1, criação da estrutura inicial do projeto utilizando `Spring Initializer`.

## Ambiente de desenvolvimento

### Pré-requisitos

- Java8 ou Java11

### Execução

#### Build e Testes da aplicação

Na raiz do projeto execute os comandos maven para:

- Testes e build do projeto
  ```
  ./mvnw clean install
  ```

#### Execução da aplicação

1. E logo em seguida execute uma das formas para iniciar a aplicação. Existe pelo menos 3 formas de executar ela:
   
- Execute a classe principal da aplicação `BillManagerApp.kt`.
- Ou execute jar gerado no build:
```shell
java -jar ./target/domestic-bill-manager-0.0.1-SNAPSHOT.jar
```

- Ou execute a aplicação com plugin do SpringBoot para maven:
```shell
./mvnw spring-boot:run
```
