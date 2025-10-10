[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/zk0ras-q)
## Orientações Gerais: 🚨
1. Utilize **apenas** tipos **wrapper** para criar atributos e métodos.
2. **Respeite** os nomes de atributos e métodos definidos no exercício.
3. Tome **cuidado** com os **argumentos** especificados no exercício.
   **Não** adicione argumentos não solicitados e mantenha a ordem definida no enunciado.
4. Verifique se **não** há **erros de compilação** no projeto antes de enviar.

# Introdução a Data Access Object (DAO) 📎

DAO (Data Access Object) é um padrão de projeto utilizado em aplicações para abstrair a lógica de acesso a banco de 
dados. Ele separa a camada de persistência (banco de dados) da lógica de negócio, tornando o código mais modular, 
reutilizável e fácil de manter.

### Vantagens de se utilizar DAOs
* Facilita a manutenção → Separação de responsabilidades
* Código organizado → Fica mais fácil mudar a lógica de persistência
* Facilidade para testar → Podemos testar a camada de negócios sem acessar o banco

### Exercício - Criando uma DAO de Músicas

Esse projeto já possui classes para conexão com o banco de dados `H2` e criação de objetos de música
assim podemos focar apenas na criação da DAO.

Está é a estrutura da tabela de músicas:
```sql
CREATE TABLE musica (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  artista VARCHAR(255) NOT NULL,
  album VARCHAR(255) NOT NULL,
  duracao INT NOT NULL -- (em segundos)
)
```
""
**Não altere as classes `ConexaoBanco` e `Musica`.**\
**Uilize o atributo `jdbcTemplate` da classe `MusicaDao` para realizar as operações no banco de dados.**

Dentro da classe `MusicaDao` implemente os seguintes métodos para manipular o banco de dados:

* `findAll()`
  * Deve retornar um `List<Musica>` com todas as músicas da base de dados.


* `findById(Integer id)`
  * Deve retornar apenas um objeto do tipo `Musica` com o id especificado.
  * Caso a música não exista na base retorne null.


* `findByNomeLike(String nome)`
  * **Recebe** uma parte do nome da música. 
  * Deve retornar uma lista de músicas em que o nome corresponde a essa parte do nome especificada.
  * **Não deve** diferenciar maiúsculas de minúsculas 


* `findByArtista(String artista)`
  * **Recebe** o nome completo do artista
  * Retorna a lista de música do artista especificado.


* `findByAlbum(String album)`
  * **Recebe** o nome completo do álbum
  * Retorna a lista de músicas do álbum especificado.


* `findByDuracaoGreaterThan(Integer duracao)`
  * **Recebe** uma duração em segundos
  * Retorna a lista de músicas que possúi duração **maior** que a especificada.


* `findByAlbumAndNomeLike(String album, String nome)`
  * **Recebe** o nome completo do álbum.
  * **Recebe** uma parte do nome da música.
  * Retorna uma lista de músicas que corresponde ao album e parte do nome solicitados.
  * Para o critério `nome`, **não deve** diferenciar maiúsculas de minúsculas 


* `save(Musica musica)`
  * **Recebe** um objeto do tipo `Musica`
  * Caso o objeto possua id nulo, deve realizar o insert da música na base de dados
  * Caso o objeto contenha o id preenchido, deve realizar o ‘update’ do registro com id especificado, na base.


* `deleteById(Integer id)`
  * **Recebe** o id do registro a ser deletado
  * Remove a música com o id especificado da base de dados.
