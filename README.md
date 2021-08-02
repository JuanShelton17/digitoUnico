# Desafio Dígito Único API

## Sobre

A aplicação **Desafio Digito Unico API** é uma aplicação para cálculos de digito únicos e gerenciamento de usuários.
Exemplo de como funciona o cálculo do dígito único:

- n: número inteiro

- k: número inteiro que representa o número de vezes que n será repetido

- p: resultado

**Exemplo**:

```
n = 9875, k = 4
p(n, k) = 9875 9875 9875 9875
somar Digitos de p = 116

Se o dígito encontrado ainda não for único, a operação de soma se repete

P = 116 = 1 + 1 + 6 = 8
```


### Tecnologias utilizadas

Para criar o projeto foram utilizados as seguintes ferramentas/frameworks:

- `SPRING-BOOT`
- `Maven`
- `Lombok`
- `JAVA 11` 
- `H2-DATABASE`
- `SWAGGER`



### Instruções para a executar o projeto

- O projeto possui com uma classe "DigitoUnicoApplication dentro do módulo de API para startar a aplicação através do SpringInitializer.
 
    `digitoUnico > com.juan.inter.digitoUnico.digitoUnico > DigitoUnicoApplication > Run as SpringBoot`
    
- Para build das dependências utilize o comando:
    `mvn install`

- Para compilar a aplicação pelo maven utilize o comando:
    `mvn spring-boot:run`

- Para executar os testes unitários utilize o comando:
  - `./mvn test` 
  - ou
  - `src > test > java > com.juan.inter.digitoUnico.digitoUnico > Run as JUnitTests`


