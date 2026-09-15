# SupportDesk

Sistema de Service Desk para gerenciamento de chamados de suporte de TI.

## Objetivo

Projeto de portfólio que simula uma operação real de suporte técnico: abertura de incidentes, triagem, diagnóstico, atendimento, resolução, histórico e acompanhamento de SLA.

## Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- React + TypeScript + Vite
- Tailwind CSS
- REST API
- Swagger/OpenAPI
- Docker
- JUnit + Mockito

## Estrutura

```text
supportdesk/
├── backend/
├── frontend/
├── database/
├── docs/
├── docker-compose.yml
└── README.md
```

## Fluxo principal

1. Usuário abre um chamado.
2. Suporte faz a triagem.
3. O analista registra diagnóstico e troubleshooting.
4. O chamado passa por atendimento/resolução.
5. A solução é documentada.
6. O chamado é encerrado e entra no histórico.
7. Dashboard acompanha volume, prioridades, status e SLA.

> Projeto independente para portfólio. Não possui vínculo oficial com nenhuma empresa.
