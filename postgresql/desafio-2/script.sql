CREATE TYPE ENUM_GENERO AS ENUM (
	'Ação',
	'Sci-Fi',
	'Animação',
	'Romance',
	'Crime',
	'Fantasia',
	'Aventura'
);
ALTER TYPE ENUM_GENERO ADD VALUE 'Drama';

ALTER TYPE ENUM_GENERO ADD VALUE 'Comédia';	

ALTER TYPE ENUM_GENERO ADD VALUE 'Mistério';


CREATE TABLE filmes(
	id SERIAL PRIMARY KEY,
	titulo VARCHAR(100) NOT NULL,
	diretor VARCHAR(50) NOT NULL,
	ano SMALLINT NOT NULL,
	genero ENUM_GENERO NOT NULL,
	duracao SMALLINT NOT NULL,
	avaliacao DECIMAL(4,2) NOT NULL,	
	bilheteria BIGINT NOT NULL,
	custo BIGINT NOT NULL
);

CREATE TYPE CANAL_ENUM AS ENUM(
	'AMC',
	'HBO',
	'Netflix',
	'NBC',
	'History Channel',
	'ABC',
	'CBS'
);

CREATE TYPE SITUACAO_ENUM AS ENUM(
	'Finalizada',
	'Em Andamento'
);

CREATE TABLE series(
	id SERIAL PRIMARY KEY,
	titulo VARCHAR(100) NOT NULL,
	criador VARCHAR(100) NOT NULL,
	ano SMALLINT NOT NULL DEFAULT EXTRACT(YEAR FROM CURRENT_DATE),
	genero ENUM_GENERO,
	temporadas SMALLINT NOT NULL DEFAULT 0,
	episodios SMALLINT NOT NULL DEFAULT 0,
	avaliacao DECIMAL(4,2) NOT NULL DEFAULT 0.0,
	canal CANAL_ENUM NOT NULL,
	situacao SITUACAO_ENUM NOT NULL DEFAULT 'Em Andamento'
);

INSERT INTO filmes (titulo, diretor, ano, genero, duracao, avaliacao, bilheteria, custo) VALUES
('Mad Max Fury Road', 'George Miller', 2015, 'Ação', 120, 8.1, 375200000, 150000000),
('Star Wars', 'George Lucas', 1977, 'Sci-Fi', 121, 8.6, 775388007, 11000000),
('Super Mario Bros', 'Aaron Horvath, Michael Jelenic', 2023, 'Animação', 92, 7.3, 1300000000, 100000000),
('Pride and Prejudice', 'Joe Wright', 2005, 'Romance', 129, 7.8, 121147947, 28000000),
('Back to the Future', 'Robert Zemeckis', 1985, 'Sci-Fi', 116, 8.5, 381109762, 19000000),
('The Godfather', 'Francis Ford Coppola', 1972, 'Crime', 175, 9.2, 246120974, 6000000),
('The Lord of the Rings: The Return of the King', 'Peter Jackson', 2003, 'Fantasia', 201, 8.9, 1146030912, 94000000),
('Treasure Planet', 'Ron Clements, John Musker', 2002, 'Animação', 85, 7.2, 109578115, 140000000),
('Jurassic Park', 'Steven Spielberg', 1993, 'Aventura', 127, 8.1, 1043589077, 63000000),
('About Time', 'Richard Curtis', 2013, 'Romance', 123, 7.8, 87000000, 12000000),
('Transformers', 'Michael Bay', 2007, 'Ação', 144, 7.0, 708709780, 150000000);

INSERT INTO series (titulo, criador, ano, genero, temporadas, episodios, avaliacao, canal, situacao) VALUES
('Breaking Bad', 'Vince Gilligan', 2008, 'Drama', 5, 62, 9.5, 'AMC', 'Finalizada'),
('Game of Thrones', 'David Benioff, D.B. Weiss', 2011, 'Fantasia', 8, 73, 9.3, 'HBO', 'Finalizada'),
('Stranger Things', 'The Duffer Brothers', 2016, 'Sci-Fi', 4, 34, 8.7, 'Netflix', 'Em Andamento'),
('Friends', 'David Crane, Marta Kauffman', 1994, 'Comédia', 10, 236, 8.9, 'NBC', 'Finalizada'),
('The Office', 'Greg Daniels', 2005, 'Comédia', 9, 201, 8.8, 'NBC', 'Finalizada'),
('Vikings', 'Michael Hirst', 2013, 'Drama', 6, 89, 8.5, 'History Channel', 'Finalizada'),
('Lost', 'J.J. Abrams, Damon Lindelof', 2004, 'Mistério', 6, 121, 8.4, 'ABC', 'Finalizada'),
('Once Upon a Time', 'Edward Kitsis, Adam Horowitz', 2011, 'Fantasia', 7, 155, 7.7, 'ABC', 'Finalizada'),
('The Mentalist', 'Bruno Heller', 2008, 'Crime', 7, 151, 8.1, 'CBS', 'Finalizada'),
('Star Trek', 'Gene Roddenberry', 1966, 'Sci-Fi', 3, 79, 8.4, 'NBC', 'Finalizada'),
('Cobra Kai', 'Josh Heald, Jon Hurwitz, Hayden Schlossberg', 2018, 'Ação', 5, 50, 8.6, 'Netflix', 'Em Andamento');


-- Além disso, crie também as seguintes consultas:

-- - Todos os filmes em ordem alfabética.
SELECT * FROM filmes ORDER BY titulo ASC;

-- - Todos os filmes com bilheteria acima de US$ 500 milhões.
SELECT * FROM filmes WHERE filmes.bilheteria >500000000 ORDER BY titulo ASC;


-- - Os IDs, nomes, anos de lançamento, gêneros, número de temporadas e episódios, avaliações e situações das séries, ordenadas da mais recente para a mais antiga.
SELECT 
	s.id,
	s.titulo,
	s.ano,
	s.genero,
	s.temporadas,
	s.episodios,
	s.avaliacao,
	s.situacao
FROM series AS s 
ORDER BY s.ano DESC;

-- - Todas as séries já finalizadas ordenadas da melhor avaliação para a pior.
SELECT * FROM series AS s
WHERE s.situacao = 'Finalizada'
ORDER BY s.avaliacao DESC;


-- - Todos os filmes lançados antes dos anos 2000.

SELECT * FROM series AS s
WHERE s.ano < 2000;

-- - Os títulos, anos de lançamento, gênero e avaliação dos filmes ordenados por sua avaliação pela crítica.
SELECT f.titulo, f.ano, f.genero, f.avaliacao FROM filmes AS f ORDER BY f.avaliacao;  

-- - A média de avaliação entre os filmes de até 2 horas e a média de avaliação dos filmes de mais de 2 horas (em colunas separadas).

SELECT 
	ROUND(AVG(CASE WHEN duracao <= 120 THEN avaliacao END), 2) AS media_filmes_ate_2h,
	ROUND(AVG(CASE WHEN duracao > 120 THEN avaliacao END), 2) AS media_filmes_mais_2h
FROM filmes;

-- - Os nomes, anos de lançamento e avaliações dos filmes 
-- ordenados pelo lucro obtido, além do próprio lucro obtido 
-- (considere lucro como bilheteria - custo).

SELECT 
 f.titulo,
 f.ano,
 f.avaliacao,
 f.bilheteria,
 f.custo,
 f.bilheteria - f.custo AS lucro
FROM filmes AS f ORDER BY f.bilheteria - f.custo;