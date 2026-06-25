# Desafio01 - API de Reservas de Salas

API REST em Spring Boot para gerenciamento de salas, usuários e reservas.

## Resumo

O projeto organiza o fluxo em camadas com `controller -> service -> repository`, usa Spring Data JPA com H2 no perfil `dev`, e deixa o perfil `prod` configurável por variáveis de ambiente. As regras de negócio cobrem validações de intervalo, status da sala e conflito de reservas.

## Tecnologias

- Java 21
- Spring Boot 3.5.x
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- JUnit 5 + Mockito
- Docker e Docker Compose opcionais

## Estrutura principal

- `com.reservas.controller` - endpoints REST
- `com.reservas.service` - regras de negócio
- `com.reservas.repository` - acesso a dados
- `com.reservas.model` - entidades JPA
- `com.reservas.dto.request` - entradas da API
- `com.reservas.dto.response` - saídas da API
- `com.reservas.exception` - exceções e tratamento global
- `com.reservas.config` - configurações futuras

## Como executar

### Localmente

```bash
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080` usando o perfil `dev` por padrão.

### Testes

```bash
./mvnw test
```

### Docker

```bash
docker compose up --build
```

## Perfis

### `dev`

- H2 em memória
- Console H2 habilitado em `/h2-console`
- `ddl-auto: create-drop`
- `show-sql: true`

### `prod`

- Banco configurado via `DB_URL`, `DB_USER` e `DB_PASS`
- `ddl-auto: validate`

## Regras de negócio

- Uma sala precisa estar ativa para ser reservada.
- A capacidade da sala deve ser positiva.
- O horário inicial da reserva deve ser anterior ao horário final.
- Reservas seguem intervalo semiaberto: `[inicio, fim)`.
- Reservas canceladas não entram na checagem de conflito.

## Tratamento de erros

O projeto retorna erros em JSON com os campos:

- `timestamp`
- `status`
- `erro`
- `mensagem`
- `caminho`

## API

Base das rotas: `/api/v1`

### Salas - `/api/v1/salas`

- `GET /api/v1/salas` - lista salas de forma paginada
- `GET /api/v1/salas/{id}` - busca uma sala por id
- `POST /api/v1/salas` - cria uma sala
- `PUT /api/v1/salas/{id}` - atualiza uma sala
- `DELETE /api/v1/salas/{id}` - desativa a sala

Exemplo de payload para criar ou atualizar:

```json
{
  "nome": "Sala Azul",
  "capacidade": 12,
  "ativa": true
}
```

### Usuários - `/api/v1/usuarios`

- `GET /api/v1/usuarios` - lista usuários de forma paginada
- `GET /api/v1/usuarios/{id}` - busca um usuário por id
- `POST /api/v1/usuarios` - cria um usuário
- `PUT /api/v1/usuarios/{id}` - atualiza um usuário
- `DELETE /api/v1/usuarios/{id}` - remove um usuário

Exemplo de payload para criar ou atualizar:

```json
{
  "nome": "Ana Souza",
  "email": "ana.souza@empresa.com"
}
```

### Reservas - `/api/v1/reservas`

- `GET /api/v1/reservas` - lista reservas de forma paginada
- `GET /api/v1/reservas/{id}` - busca uma reserva por id
- `POST /api/v1/reservas` - cria uma reserva com validações de negócio
- `PATCH /api/v1/reservas/{id}/cancelar` - cancela a reserva

Exemplo de payload para criar:

```json
{
  "salaId": 1,
  "usuarioId": 1,
  "inicio": "2026-06-24T10:00:00",
  "fim": "2026-06-24T12:00:00"
}
```

## Respostas principais

### Sala

```json
{
  "id": 1,
  "nome": "Sala Azul",
  "capacidade": 12,
  "ativa": true
}
```

### Usuário

```json
{
  "id": 1,
  "nome": "Ana Souza",
  "email": "ana.souza@empresa.com"
}
```

### Reserva

```json
{
  "id": 1,
  "sala": {
    "id": 1,
    "nome": "Sala Azul",
    "capacidade": 12,
    "ativa": true
  },
  "usuario": {
    "id": 1,
    "nome": "Ana Souza",
    "email": "ana.souza@empresa.com"
  },
  "inicio": "2026-06-24T10:00:00",
  "fim": "2026-06-24T12:00:00",
  "status": "ATIVA"
}
```

## Observações

- As listagens usam paginação do Spring Data. Parâmetros comuns: `page`, `size` e `sort`.
- A collection `docs.json` na raiz do projeto pode ser importada no Postman.
