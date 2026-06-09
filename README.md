# Agendamento Dojo API

API desenvolvida para o gerenciamento de matrículas, aulas e agendamentos de um Dojo. 

## Tecnologias Utilizadas

* Java
* Spring Boot 3
* Spring Security
* JSON Web Token (Auth0 JWT)
* Spring Data JPA e Hibernate
* Bean Validation (Hibernate Validator)
* Springdoc OpenAPI (Swagger UI)
* Lombok
* Maven

## Arquitetura e Padrões Aplicados

* **Data Transfer Objects (DTO):** Isolamento entre as entidades persistidas e as requesicoes da API.
* **Spring Events:** Utilizado para criação de perfis (de Aluno e Professor), ocorre via eventos disparados pela AuthService.
* **Global Exception Handling:** Centralização de erros através do `@RestControllerAdvice`, padronizando as saídas de erro da API.

## Funcionalidades Atuais

* Registro seguro de usuários com validação de CPF e E-mail.
* Autenticação baseada em JWT, retornando tokens.

## Documentação da API

Com a aplicação em execução, a documentação fica disponível no navegador através do endereço:

`http://localhost:8081/swagger-ui.html`
