# 💻 Desafio: Sistema de Cadastro e Histórico de Tarefas (Java Fundamentos)

## 🧠 Objetivo

Criar um sistema simples em Java que permita cadastrar usuários e tarefas com controle de tempo, utilizando conceitos como classes, métodos, controle de fluxo, exceções, entrada e saída, datas e expressões regulares.

---

## ✨ Requisitos

1. **Cadastro de Usuário**
    - Criar uma classe `Usuario` com os atributos: `nome`, `email`, `senha`.
    - Armazenar os usuários em uma lista.

2. **Autenticação Simples**
    - Criar um método de login que verifica se o email e a senha existem.

3. **Cadastro de Tarefas**
    - Criar uma classe `Tarefa` com: `titulo`, `descricao`, `dataCriacao (LocalDateTime)`, `status`.

4. **Listagem e Filtros**
    - Mostrar todas as tarefas cadastradas.
    - Filtrar tarefas por **status**: pendente, em andamento ou concluída.
    - Exibir tarefas criadas nos **últimos 7 dias** usando `java.time`.

5. **Salvar e Carregar Arquivos**
    - Salvar e carregar as tarefas do usuário usando **Java N-IO** (`Files`, `Paths`, `BufferedWriter`, `BufferedReader`).

6. **Tratamento de Erros**
    - Utilizar `try-catch` para capturar e exibir mensagens de erro amigáveis durante entrada, leitura de arquivos e autenticação.

7. **Expressões Regulares**
    - Utilizar **Regex** para validar o e-mail no momento do cadastro.

---

## 🧱 Tecnologias/Ferramentas

- Java 17+
- `Scanner`, `ArrayList`, `LocalDateTime`, `Regex`, `Files`
- Orientação a Objetos (POO)
- Estrutura em pacotes

---

## ✅ Critérios de Avaliação

- Código organizado e legível
- Separação adequada em métodos e classes
- Uso correto dos conceitos fundamentais do Java
- Manipulação de arquivos com N-IO
- Validação com expressões regulares
- Tratamento de exceções

---

> Desafio inspirado nos tópicos do curso de Fundamentos Java da [Rocketseat](https://app.rocketseat.com.br/journey/java/contents).
