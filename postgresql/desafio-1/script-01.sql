CREATE TABLE IF NOT EXISTS clientes(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(45) NOT NULL,
	telefone VARCHAR(15) NOT NULL,
	endereco VARCHAR(255) NOT NULL,
	data_de_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

CREATE TABLE IF NOT EXISTS fornecedores(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(45) NOT NULL,
	telefone VARCHAR(15) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	data_de_contratacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	observacoes TEXT
);


CREATE TABLE IF NOT EXISTS lanches(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(45) NOT NULL,
	descricao VARCHAR(255) NOT NULL,
	preco DECIMAL(10, 2) NOT NULL
);

CREATE TYPE SITUACAO AS ENUM (
	'pedendte', 
	'retirado',
	'entregue'
	);

CREATE TABLE IF NOT EXISTS pedidios(
	id SERIAL PRIMARY KEY,
	mesa VARCHAR(255),
	data_hora_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	situacao SITUACAO NOT NULL
);


CREATE TABLE IF NOT EXISTS ongredientes_em_estoque(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(45) NOT NULL,
	quantidade INT NOT NULL,
	categoria VARCHAR(50) NOT NULL
);

-- Inserindo dados na tabela clientes
INSERT INTO clientes (nome, telefone, endereco)
VALUES 
('João Silva', '11987654321', 'Rua das Flores, 123'),
('Maria Oliveira', '11976543210', 'Av. Paulista, 456'),
('Carlos Santos', '11965432109', 'Rua dos Pinheiros, 789'),
('Ana Souza', '11954321098', 'Rua Bela Vista, 101'),
('Pedro Lima', '11943210987', 'Rua Central, 202');

-- Inserindo dados na tabela fornecedores
INSERT INTO fornecedores (nome, telefone, email, observacoes)
VALUES 
('Distribuidora de Pães', '1132123456', 'paes@fornecedor.com', 'Entrega diária de pães frescos.'),
('Bebidas Geladas', '1132234567', 'bebidas@fornecedor.com', 'Fornece refrigerantes e sucos.'),
('Carnes Nobres LTDA', '1132345678', 'carnes@fornecedor.com', 'Carne bovina e de frango.'),
('Verduras e Hortifruti', '1132456789', 'verduras@fornecedor.com', 'Entrega segunda e quinta.'),
('Laticínios Bom Leite', '1132567890', 'laticinios@fornecedor.com', 'Leite, queijos e derivados.');

-- Inserindo dados na tabela lanches
INSERT INTO lanches (nome, descricao, preco)
VALUES 
('X-Burguer', 'Pão, hambúrguer, queijo e maionese', 15.00),
('X-Salada', 'Pão, hambúrguer, queijo, alface, tomate e maionese', 18.50),
('X-Bacon', 'Pão, hambúrguer, queijo, bacon e maionese', 20.00),
('X-Tudo', 'Pão, hambúrguer, queijo, presunto, ovo, bacon, alface, tomate e maionese', 25.00),
('Cachorro-Quente', 'Pão, salsicha, molho de tomate, milho e batata palha', 12.00);

-- Inserindo dados na tabela pedidios
INSERT INTO pedidios (mesa, situacao)
VALUES 
('Mesa 01', 'pedendte'),
('Mesa 02', 'retirado'),
('Mesa 03', 'entregue'),
('Mesa 04', 'pedendte'),
('Mesa 05', 'retirado');

-- Inserindo dados na tabela ongredientes_em_estoque
INSERT INTO ongredientes_em_estoque (nome, quantidade, categoria)
VALUES 
('Pão de Hambúrguer', 100, 'Padaria'),
('Queijo Mussarela', 50, 'Laticínios'),
('Carne Bovina', 40, 'Carnes'),
('Bacon', 30, 'Carnes'),
('Alface', 25, 'Verduras'),
('Tomate', 25, 'Verduras'),
('Salsicha', 60, 'Carnes'),
('Refrigerante Cola', 80, 'Bebidas');


