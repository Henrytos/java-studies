# Desafio 01

## Introdução

Faaala Dev,

Sejam todos muito bem-vindos ao primeiro desafio da trilha de Java do Ignite.

Nesse desafio você reforçará de forma prática os **conceitos** que aprendemos nesse módulo.

Como se trata de um desafio, ele necessita de alguns conhecimentos além dos abordados nesse módulo, então é importante ter autonomia para conseguir pesquisar essas coisas caso não saiba como resolver. Por isso, lembre-se, t**enha calma** e **acredite no seu processo.**

O aprendizado daqui é muito importante e com certeza você conseguirá sair com muito conhecimento bacana 💜

## Sobre o desafio

Nesse desafio você desenvolverá uma API fictícia para uma empresa de cursos de programação, onde em um primeiro momento, você deverá utilizar o CRUD, para criação de cursos.

A API deve conter as seguintes funcionalidades:

- Criação de um novo curso
- Listagem de todos os cursos
- Atualização de um curso pelo `id`
- Remover um curso pelo `id`

### Rotas e regras de negócio

Antes das rotas, vamos entender qual a estrutura (propriedades) que um curso deve ter:

- `id` - Identificador único de cada curso
- `name` - Nome do curso
- `category` - Categoria do curso
- `Active` - Define se o curso está ativo ou não
- `created_at` - Data de quando o curso foi criado.
- `updated_at` - Deve ser sempre alterado para a data de quando o curso for atualizada.

Antes das rotas, vamos entender qual a estrutura (propriedades) que um usuario deve ter:

- `id` - Identificador único do usuário  
- `name` - Nome completo do usuário  
- `email` - Email único do usuário  
- `password` - Senha criptografada  
- `role` - Papel do usuário (`ADMIN`, `USER`)  
- `created_at` - Data de criação do usuário  
- `updated_at` - Data da última atualização  
    
# ✅ Requisitos + Rotas com Segurança (JWT e Roles)



## 👥 Papéis (Roles)

- `ADMIN`: pode **criar**, **editar** e **remover** cursos  
- `USER`: pode apenas **visualizar** cursos
---
## 🔐 Autenticação

| Método | Rota           | Descrição                       |
|--------|----------------|---------------------------------|
| POST   | /auth/register | Registrar novo usuário          |
| POST   | /auth/login    | Autenticação e retorno do JWT   |

- `POST - /cursos`
    
    Deve ser possível criar um curso no banco de dados, enviando os campos `name` e `category` por meio do `body` da requisição.
    
    Ao criar um curso, os campos: `id`, `created_at`   e `updated_at` devem ser preenchidos automaticamente, conforme a orientação das propriedades acima.
    
- `GET - /cursos`
    
    Deve ser possível listar todas os cursos salvos no banco de dados.
    
    Também deve ser possível realizar uma busca, filtrando os cursos pelo `name` e `category`
    
- `PUT - /cursos/:id`
    
    Deve ser possível atualizar um curso pelo `id`.
    
    No `body` da requisição, deve receber somente o `name` e/ou `category` para serem atualizados.
    
    Se for enviado somente o `name`, significa que o `category` não pode ser atualizado e vice-versa. Além disso `active` for informado nessa rota, ele deverá ser ignorado, pois a rota que deverá fazer essa atualização, é a de patch.
    
- `DELETE - /cursos/:id`
    
    Deve ser possível remover um curso pelo `id`.
    
- `PATCH - /cursos/:id/active`
    
    Essa rota servirá para marcar se o curso está ativo ou não, ou seja, um toggle entre true or false.


💡 Dica: 

A anotação `@CreationTimestamp` define que o valor do atributo `createdAt` será definido automaticamente pelo banco de dados no momento da criação da entidade. A anotação `@UpdateTimestamp` define que o valor do atributo `updatedAt` será definido automaticamente pelo banco de dados no momento da atualização da entidade.

## Indo além

Algumas sugestões do que pode ser implementado:

- Validar se as propriedades `name` e `category` das rotas `POST` e `PUT` estão presentes no `body` da requisição.
- Para a parte de definição se o curso está ativo ou não, você pode definir um ENUM(enumerador) para fazer esse ‘’check’’.   Sugestão de leitura: [clique aqui](https://www.devmedia.com.br/enums-no-java/38764).
- Para tratar as exceções, você pode se desafiar e criar as excpetion  😃



## Entrega

Após concluir o desafio, você deve enviar a URL do seu código no GitHub para a plataforma. 

Além disso, que tal fazer um post no LinkedIn compartilhando o seu aprendizado e contando como foi a experiência?
É uma excelente forma de demonstrar seus conhecimentos e atrair novas oportunidades!

Feito com 💜 por Rocketseat 👋