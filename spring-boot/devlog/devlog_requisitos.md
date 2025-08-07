# DevLog - Especificação Back-end

## Requisitos Funcionais

- [ x ] O sistema deve permitir o cadastro de usuários com nome, username e senha  
- [ x ] O sistema deve permitir login de usuários com username e senha  
- [ x ] O sistema deve gerar um token JWT após o login  
- [ x ] O sistema deve permitir que usuários criem, editem, visualizem e excluam artigos  
- [ x ] O sistema deve suportar rascunhos e publicações de artigos  
- [ ] O sistema deve permitir que artigos tenham tags e imagens de capa  (aws s3)
- [ ] O sistema deve permitir a busca de artigos por título, conteúdo ou tags  (entidade tags)
- [ ] O sistema deve listar artigos públicos com paginação  (paginação)
- [ ] O sistema deve permitir que usuários curtam e salvem artigos  
- [ ] O sistema deve permitir comentários em artigos  
- [ ] O sistema deve permitir comentários aninhados (respostas)  
- [ ] O sistema deve permitir que cada usuário veja seus próprios artigos  
- [ ] O sistema deve permitir a edição e exclusão de comentários próprios  
- [ ] O sistema deve oferecer rotas administrativas para visualização de usuários, artigos e comentários  
- [ ] O sistema deve registrar data de criação e atualização de artigos e comentários  

## Requisitos Não Funcionais

- [ x ] A API deve seguir o padrão RESTful  
- [ x ] As respostas da API devem estar no formato JSON  
- [ x ] A autenticação deve ser feita com tokens JWT  
- [ x ] As senhas devem ser armazenadas com hash seguro (ex: bcrypt)  
- [ x ] O sistema deve utilizar banco de dados relacional (ex: PostgreSQL)  
- [ x ] O código deve ser organizado em camadas (ex: controller, service, repository)  
- [ x ] A API deve estar documentada com Swagger ou Postman  
- [ x ] A aplicação deve ter testes automatizados (unitários e integração)  
- [ x ] O sistema deve ser preparado para deploy com Docker  
- [ ] A aplicação deve implementar controle de CORS e validação de entrada  

## Regras de Negócio

- [ x ] Apenas usuários autenticados podem criar, editar ou excluir artigos  
- [ x ] Apenas o autor de um artigo pode editá-lo ou excluí-lo  
- [ ] Apenas usuários autenticados podem comentar  
- [ ] Apenas o autor de um comentário pode editá-lo ou excluí-lo  
- [ ] Um artigo só pode ser **curtido** ou **salvo** uma vez por usuário  
- [ ] Artigos com status "rascunho" só podem ser visualizados pelo autor  
- [ ] Um artigo pode conter múltiplas tags  
- [ ] Um comentário pode conter respostas (máximo de 1 nível de profundidade)  
- [ ] Apenas administradores podem visualizar todos os usuários ou banir contas  
- [ ] O sistema deve validar campos obrigatórios na criação de artigos, usuários e comentários  

## Rotas da API

### Autenticação

```http
[ x ] POST   /auth/register     # Cadastro de novo usuário  
[ x ] POST   /auth/login        # Login e geração de token  
POST   /auth/refresh      # Renovação de token (opcional)  
POST   /auth/forgot       # Enviar username de recuperação  
POST   /auth/reset        # Redefinir senha  
```

### Usuário

```http
GET    /users/me          # Buscar dados do usuário logado  
GET    /users/:id         # Buscar perfil público de um autor  
GET    /users             # Listar usuários (admin)  
PATCH  /users/:id         # Atualizar dados (nome, bio, etc.)  
DELETE /users/:id         # Banir usuário (admin)  
```

### Artigos

```http
[ x ] POST   /articles          # Criar novo artigo  
[ x ] POST   /articles/:articleId # Publicar novo artigo  

GET    /articles          # Listar artigos públicos  
GET    /articles/me       # Listar artigos do usuário logado  
GET    /articles/:slug    # Buscar artigo por slug  
[ x ] PATCH  /articles/:id      # Editar artigo  
[ x ] DELETE /articles/:id      # Excluir artigo  
```

### Comentários

```http
POST   /articles/:id/comments     # Criar comentário em artigo  
POST   /comments/:id/reply        # Responder a um comentário  
GET    /articles/:id/comments     # Listar comentários do artigo  
PATCH  /comments/:id              # Editar comentário  
DELETE /comments/:id              # Excluir comentário  
```

### Tags

```http
GET    /tags             # Listar todas as tags  
GET    /tags/:tag        # Listar artigos por tag  
```

### Curtidas e Favoritos

```http
POST   /articles/:id/like         # Curtir artigo  
DELETE /articles/:id/like         # Remover curtida  
POST   /articles/:id/save         # Salvar artigo para leitura posterior  
DELETE /articles/:id/save         # Remover dos salvos  
GET    /articles/saved            # Listar artigos salvos pelo usuário  
```