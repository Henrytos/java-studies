# Desafio - Sistema de Livraria

Faaala Dev,

Sejam todos muito bem-vindos ao desafio Sistema de Livraria da formação de Java.

Nesse desafio você reforçará de forma prática os **conceitos** que aprendemos nesse módulo.

Como se trata de um desafio, ele necessita de alguns conhecimentos além dos abordados nesse módulo, então é importante ter autonomia para conseguir pesquisar essas coisas caso não saiba como resolver. Por isso, lembre-se, t**enha calma** e **acredite no seu processo.**

O aprendizado daqui é muito importante e com certeza você conseguirá sair com muito conhecimento bacana 💜

## Sobre o Desafio

Neste desafio, você desenvolverá um sistema básico para gerenciar o empréstimo de livros em uma biblioteca. O foco será na listagem de livros disponíveis e na funcionalidade de empréstimo.

Você deverá criar um loop de interação com o usuário, perguntando se ele gostaria de ver os livros disponíveis, caso ele afirme, a tela do console mostrará os livros disponíveis. 

Caso SIM: Ele deverá escolher o id do livro, e após isso inserir o nome, no final, uma mensagem deverá ser gerada, informando que o livro foi emprestado com sucesso. 

Caso NÃO: O sistema se encerrará, lembre-se de colocar uma mensagem, informando o fim da aplicação.

## Funcionalidades do Sistema

### Gerenciamento de Livros

- **Adicionar um novo livro**: incluindo título e autor. (Como é um desafio introdutório, você pode apenas definir já dentro da classe, igual o Gleyson ensinou nas aulas).
- **Listar todos os livros disponíveis**: mostrar apenas os livros que estão disponíveis para empréstimo.
- **Realizar empréstimo de um livro**: permitir que um usuário escolha um livro disponível e registre o empréstimo.

## Estrutura do Projeto

O projeto será dividido em algumas classes simples para manter a organização. Aqui estão as classes principais e suas funcionalidades:

### Livro

- **id**: Identificador único do livro
- **titulo**: Título do livro
- **autor**: Autor do livro (objeto do tipo Autor)
- **disponivel**: Indica se o livro está disponível para empréstimo
- dataCadastro: Data que o livro foi cadastrado
- dataAtualização: Data que o livro foi atualizado

### Autor

- **id**: Identificador único do autor
- **nome**: Nome do autor
- dataNascimento: Nascimento do autor

### Biblioteca

- **livros**: Lista de livros na biblioteca
- autores: Lista de autores da biblioteca
- emprestimos: Lista de empréstimos da biblioteca

Uma dica para extruturar a  classe:

```java
private List<Livro> livros = new ArrayList<>();
private List<Autor> autores = new ArrayList<>();
private List<Emprestimo> emprestimos = new ArrayList<>();
```

## Regras de Negócio

### Gerenciamento de Livros

- Apenas livros marcados como disponíveis podem ser emprestados.
- O usuário deverá poder informar seu nome ao fazer empréstimo do livro

### Empréstimo de Livros

- Ao realizar um empréstimo, o livro escolhido deve ser marcado como indisponível e durante esta execução do programa NÃO poderá fazer o empréstimo do mesmo livro novamente.

## Indo além

Algumas sugestões do que pode ser implementado:

- Cadastro de Clientes: crie uma classe Cliente para representar os usuários da biblioteca, com atributos como id, nome, dataNascimento, e email.
    
    Implemente uma funcionalidade para listar todos os clientes cadastrados.
    Adicione a capacidade de associar empréstimos aos clientes, permitindo que você veja quais livros um cliente específico emprestou e quando.
    
    Adicione uma funcionalidade para consultar o histórico de empréstimos de um livro ou cliente específico, mostrando datas de empréstimo e devolução.
    
- Mantenha um registro de todos os empréstimos, incluindo os devolvidos.
- Implemente funcionalidades para buscar livros por título ou autor.
Adicione filtros para listar apenas livros de determinados gêneros, ou livros que foram adicionados recentemente.
- Indo mais além, você pode adicionar um menu, que ao iniciar o sistema, pergunta ao usuário se ele quer cadastrar um novo livro, porém para isso, deverá inserir todos os parâmetros do livro, e após adicionar, o livro ficará disponível para empréstimo.

## Entrega

Após concluir o desafio, você deve enviar a URL do seu código no GitHub para a plataforma. 

Além disso, que tal fazer um post no LinkedIn compartilhando o seu aprendizado e contando como foi a experiência?
É uma excelente forma de demonstrar seus conhecimentos e atrair novas oportunidades!

Feito com 💜 por Rocketseat 👋

## Repositório de consulta

Antes de acessar o repositório com o desafio concluído, recomendamos fortemente que tente realizar o desafio.

Caso tenha dúvidas/dificuldades técnicas, temos o fórum para te ajudar.