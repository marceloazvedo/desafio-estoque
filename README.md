# desafio-estoque

### Instalação

  1. Para executar o projeto basta fazer o clone e importar no eclipse como **Existing Maven Projects**;
  2. Antes de executar o projeto, é neccessário criar uma base de dados no PostgreSQL chamada controle-estoque, é necessário que o PostgreSQL esteja rodando na prota 5432, nome de usuário postgres e senha root, caso contrário haverá problemas de conexão;
  -   Caso não sejam essas as configurações do PostgreSQL, você pode editar as propriedades de conexão no arquivo **application.properties** dentro da pasta **src/main/resources**.
  3. Depois de importar, é necessário executar um **Maven Update** no próprio eclipse, para que baixe todas as dependências necessárias para rodar o projeto, depois execute a classe **ControleEstoqueApplication** que possui um mêtodo main que executa o projeto inteiro e sobre um servidor **Tomcat** embarcado;

  4. Depois disso o projeto está pronto para ser executar, você pode observar toda a documentção do projeto acessando http://localhost:8080.

### Endpoints

  - CRUD Loja
  ```
    Cadastrar 
    Method: POST
    /api/loja

    Editar 
    Method: PUT
    /api/loja/:idLoja

    Listar 
    Method: GET
    /api/loja

    Buscar 
    Method: GET
    /api/loja/:idLoja
  ```
### Na edição de um produto, ao alterar o Id da Loja automaticamente esse produto passa a pertecer a outra loja.

  - CRUD Produto
  ```
    Cadastrar 
    Method: POST
    /api/loja/:idLoja/produto

    Editar
    Method: PUT
    /api/produto/:idProduto

    Listar 
    Method: GET
    /api/loja/:idLoja/produto

    Buscar 
    Method: GET
    /api/produto/:idProduto

  - Realizar venda
  ```
    Realizar venda
    Method: POST
    /api/loja/:idLoja/venda

    Buscar 
    Method: GET
    /api/venda/:id

### Todas as validações de campos e questão de lógica estão sendo realizadas.