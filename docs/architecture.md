# Arquitetura

```text
React + TypeScript
       |
       | HTTP/JSON
       v
Spring Boot REST API
       |
       | JPA/Hibernate
       v
PostgreSQL
```

## Camadas

- Controller: recebe requisições HTTP.
- Service: concentra regras de negócio.
- Repository: acesso aos dados.
- Domain: entidades e enums do domínio.
- DTO: entrada controlada da API.
- Database: persistência dos chamados, usuários, equipamentos e histórico.

## Fluxo de atendimento

`ABERTO -> EM_ANALISE -> EM_ATENDIMENTO -> RESOLVIDO -> ENCERRADO`

Quando necessário, o chamado pode passar por `AGUARDANDO_USUARIO` antes de continuar o atendimento.
