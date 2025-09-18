---

# 📦 **Desafio: TaskFlow - Gerenciador de Tarefas com Papéis**

---

## 🎯 Objetivo

Criar uma aplicação de gerenciamento de tarefas com múltiplos perfis de usuário. O sistema deve permitir que administradores criem quadros de tarefas (boards) e que membros colaborem neles. O foco aqui é aplicar conceitos de:

* Spring Boot + Angular
* DDD (Domain-Driven Design)
* Arquitetura Hexagonal (Ports & Adapters)
* Autenticação e autorização simples (JWT + RBAC)
* Boas práticas de design

---

## 🧩 Contexto

Você foi contratado para desenvolver uma pequena aplicação de gestão de tarefas colaborativas chamada **TaskFlow**. O sistema será usado por equipes pequenas para organizar e distribuir tarefas, com foco na simplicidade.

---

## 👥 Perfis de usuário

* **Admin**: pode criar e gerenciar quadros e usuários
* **Membro**: pode visualizar e interagir com tarefas de seus quadros

---

## 📘 Entidades

* **User**

    * id, name, email, password, role (ADMIN | MEMBER)
* **Board**

    * id, title, description, owner (User), members (User\[])
* **Task**

    * id, title, description, status (TO\_DO | IN\_PROGRESS | DONE), boardId, assignee (User), dueDate
* **Attachment (Opcional)**

    * fileUrl, taskId

---

## 🧱 Value Objects

* **Email** (validação e encapsulamento)
* **TaskStatus** (enum)
* **Role** (enum)

---

## 📋 Requisitos Funcionais

* [ ] Login com email e senha
* [ ] CRUD de usuários (restrito ao admin)
* [ ] CRUD de boards (admin é o "owner")
* [ ] Admin pode adicionar/remover membros de um board
* [ ] CRUD de tarefas dentro de boards
* [ ] Membro só pode visualizar e editar tarefas do board ao qual pertence
* [ ] Tarefa deve ter status (to do, in progress, done)
* [ ] Membro pode ser atribuído a uma tarefa
* [ ] Filtro de tarefas por status e responsável

---

## 📋 Requisitos Não Funcionais

* [ ] Autenticação baseada em JWT
* [ ] Controle de permissões por tipo de usuário (RBAC)
* [ ] Persistência em banco de dados relacional (PostgreSQL ou H2)
* [ ] Angular para o frontend (pode ser bem simples)
* [ ] Testes unitários no domínio
* [ ] Aplicação separada por camadas/domínios (hexagonal)
* [ ] Versionamento no GitHub

---

## 🔁 Regras de Negócio

* [ ] Apenas o admin pode criar boards e gerenciar membros
* [ ] Membros só podem interagir com boards onde são participantes
* [ ] Só o usuário atribuído a uma tarefa pode movê-la de status
* [ ] Não é permitido dois boards com o mesmo título para o mesmo admin
* [ ] Uma tarefa não pode ser marcada como "done" sem uma data de entrega

---

## 📈 Casos de Uso

* ✅ Login
* ✅ Criar board (admin)
* ✅ Adicionar membros ao board
* ✅ Criar tarefa em um board
* ✅ Atribuir tarefa a um membro
* ✅ Atualizar status da tarefa
* ✅ Visualizar todas as tarefas de um board
* ✅ Filtrar tarefas por status/responsável
* ✅ Editar dados de uma tarefa
* ✅ Visualizar tarefas atribuídas a mim (membro)

---

## 🌐 Rotas Sugeridas (API REST)

```http
POST   /auth/login
GET    /users/me
POST   /users           (admin)
GET    /users           (admin)
POST   /boards          (admin)
GET    /boards
GET    /boards/:id
POST   /boards/:id/members
GET    /boards/:id/tasks
POST   /boards/:id/tasks
PATCH  /tasks/:id/status
PATCH  /tasks/:id
```

---

## 🧭 Storyboard Simples (Fluxo do Usuário)

1. **Login**: usuário entra com email e senha
2. **Admin**:

    * Cria novo board
    * Adiciona membros ao board
    * Cria tarefas e atribui responsáveis
3. **Membro**:

    * Acessa o board
    * Vê tarefas atribuídas a ele
    * Atualiza status de tarefas
4. **Ambos**:

    * Podem filtrar tarefas por status ou responsável

---

## 🧠 Conceitos que você pode treinar

* DDD (Entidades, Regras, Value Objects, Casos de uso)
* Arquitetura Hexagonal (Domain, Application, Adapters)
* Separação de responsabilidades no código
* Autenticação e RBAC
* Angular para consumo da API e construção de UI simples
* Documentação (Swagger ou Postman)
* Git e GitHub para versionamento

---

## 🚀 Dicas

* Use UUIDs como IDs
* Simule notificações com console logs (ex: "Tarefa atribuída ao usuário")
* Mantenha o escopo simples, o objetivo é aprender, não ficar preso em detalhes visuais

---

Se quiser, posso te ajudar com o **esqueleto inicial do projeto em Spring Boot ou Angular**, ou ainda com **diagramas (casos de uso, classes, etc.)**. Deseja isso também?


src/main/java/com/estudo/hexagonal_ddd
│
├── application                # Casos de uso (Application Services)
│   ├── dtos                   # Objetos de transporte (entrada/saída)
│   ├── exceptions             # Exceções da camada de aplicação
│   └── services               # Implementações dos casos de uso
│
├── domain                     # Núcleo do negócio (puro, sem dependência externa)
│   ├── entities               # Entidades do domínio
│   ├── repositories           # Interfaces (ports outbound) de repositórios
│   ├── services               # Domain services (regras de negócio que não cabem só em entities)
│   └── valueobjects           # Value Objects (imutáveis, ricos em regra de negócio)
│
├── infrastructure             # Implementações técnicas (adapters)
│   ├── adapters
│   │   ├── inbound            # Pontos de entrada (ex: REST, gRPC, CLI, Kafka consumer)
│   │   │   └── rest           # Controllers REST
│   │   │
│   │   └── outbound           # Pontos de saída (banco, mensageria, APIs externas)
│   │       └── persistence
│   │           └── database
│   │               ├── entities   # Entidades persistentes (JPA, Hibernate, etc.)
│   │               ├── mapper     # Conversores (Domain <-> Entity)
│   │               └── repository # Implementações de repositórios (JpaRepository, etc.)
│   │
│   └── configuration          # Configuração do Spring (beans, etc.)
│
├── ports                      # Interfaces que definem a fronteira do app
│   ├── inbound                # Interfaces de caso de uso (ex: UserServicePort)
│   └── outbound               # Interfaces de persistência, mensageria, etc.
│
└── HexagonalDddApplication    # Classe principal (Spring Boot)


flowchart LR
A[Controller\n(infrastructure.adapters.inbound.rest)] --> B[Port Inbound\n(ports.inbound)]
B --> C[Application Service\n(application.services)]
C --> D[Port Outbound\n(ports.outbound)]
D --> E[Repository Impl\n(infrastructure.adapters.outbound.persistence.database.repository)]
E --> F[(Database)]
