### Agendamento Dojo API
API REST para gerenciamento de matrículas, aulas e agendamentos de um Dojo.

#### Tecnologias
- Java 21, Spring Boot 4
- Spring Security + JWT (Auth0 java-jwt)
- Spring Data JPA/Hibernate, PostgreSQL
- Flyway, MapStruct, Lombok
- Springdoc OpenAPI (Swagger UI), Spring Cloud OpenFeign, Maven

#### Arquitetura/Padrões
- DTOs para entrada/saída
- Eventos de domínio para criação de perfis (Aluno/Professor)
- Tratamento global de erros com @RestControllerAdvice

#### Funcionalidades
- Registro de usuários com validação de CPF e e-mail
- Autenticação JWT e emissão de token
- Endpoints para alunos, aulas e agendamentos

#### Como executar
Requisitos: Java 21, Maven, PostgreSQL.
Variáveis de ambiente obrigatórias:
- DB_URL
- DB_USER, DB_PASS
- JWT_SECRET

Swagger UI: http://localhost:8080/swagger-ui.html
