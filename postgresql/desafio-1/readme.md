# Banco de Dados para Lanchonete 🍔

Este projeto contém um script SQL (compatível com **PostgreSQL**) responsável pela criação do banco de dados de uma lanchonete.  

O script cria, caso não existam, as tabelas necessárias para armazenar as informações de clientes, fornecedores, lanches, pedidos e ingredientes em estoque.

---

## 📂 Estrutura do Banco de Dados

### 1. Clientes
- `id`
- `nome`
- `telefone`
- `endereço`
- `data_de_cadastro`

### 2. Fornecedores
- `id`
- `nome`
- `telefone`
- `email`
- `data_de_contratacao`
- `observacoes`

### 3. Lanches
- `id`
- `nome`
- `descricao`
- `preco`

### 4. Pedidos
- `id`
- `mesa`
- `data_hora_pedido`
- `situacao`

### 5. Ingredientes em Estoque
- `id`
- `nome`
- `categoria`
- `quantidade`
