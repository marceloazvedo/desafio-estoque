define({ "api": [
  {
    "type": "post",
    "url": "/api/autenticar",
    "title": "Autenticacao",
    "name": "Autenticacao",
    "group": "Autenticacao",
    "description": "<p>Neste serviço você obtém o token necessário para consumir qualquer serviço dentro da API, o login padrão é <code>admin</code> e senha <code>123456</code>. O dispositivo pode ser WEB ou MOBILE, utilize WEB.</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "codigo",
            "description": "<p>Código de retorno da API.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "mensagem",
            "description": "<p>Mensagem de retorno da API.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>Token utilizado para efetuar qualquer requisição dentro da API.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "validade",
            "description": "<p>Até quando o token utilizado é válido, o token é valido por 5 horas depois da da última requisição feita ao serviço.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Requisição com sucesso:",
          "content": "HTTP/1.1 200 OK\n{\n\t\"codigo\": \"00\",\n\t\"mensagem\": \"Sucesso\",\n\t\"token\": \"9A54036D6C611D367C71D55651A6D5D91D6BD64422A2CF510B533E16959D1585\",\n\t\"validade\": \"21/04/2018 19:17:36\"\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Exemplo de requisição:",
          "content": "HTTP/1.1 200 OK\n{\n\t\"login\":\"admin\",\n\t\"senha\":\"123456\",\n\t\"dispositivo\": \"WEB\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "../java/br/com/controleestoque/controller/AuthenticationController.java",
    "groupTitle": "Autenticacao"
  },
  {
    "type": "get",
    "url": "/api/loja/:id",
    "title": "Buscar loja por id",
    "name": "Buscar_Loja",
    "group": "Loja",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Id da loja que você quer buscar.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de requisição:",
          "content": "HTTP/1.1 200 OK\n{\n\t\"nome\": \"Chuteira\",\n\t\"descricao\": \"Marca Adidas, ótima qualidade\",\n\t\"quantidade\": 0,\n\t\"valor\": 120.00\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "codigo",
            "description": "<p>Código de retorno da API.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "mensagem",
            "description": "<p>Mensagem de retorno da API.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Requisição com sucesso:",
          "content": "HTTP/1.1 200 OK\n {\n\t \"codigo\": \"00\",\n\t \"mensagem\": \"Sucesso\"\n }",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "../java/br/com/controleestoque/controller/LojaController.java",
    "groupTitle": "Loja"
  },
  {
    "type": "post",
    "url": "/api/loja",
    "title": "Cadastrar nova loja",
    "name": "Cadastrar_Loja",
    "group": "Loja",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "codigo",
            "description": "<p>Código de retorno da API.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "mensagem",
            "description": "<p>Mensagem de retorno da API.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Id da loja cadastrada, esse Id pode ser utlizado para visualizar a loja posteriormente.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Requisição com sucesso:",
          "content": "HTTP/1.1 200 OK\n {\n\t \"codigo\": \"00\",\n\t \"mensagem\": \"Sucesso\",\n\t \"id\": 11\n }",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Exemplo de requisição:",
          "content": "HTTP/1.1 200 OK\n{\n\t\"nome\":\"Netshoes\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "../java/br/com/controleestoque/controller/LojaController.java",
    "groupTitle": "Loja"
  },
  {
    "type": "put",
    "url": "/api/loja/:id",
    "title": "Editar loja",
    "name": "Editar_Loja",
    "group": "Loja",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Id da loja que você quer editar.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de requisição:",
          "content": "HTTP/1.1 200 OK\n{\n\t\"nome\":\"Netshoes\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "codigo",
            "description": "<p>Código de retorno da API.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "mensagem",
            "description": "<p>Mensagem de retorno da API.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Requisição com sucesso:",
          "content": "HTTP/1.1 200 OK\n {\n\t \"codigo\": \"00\",\n\t \"mensagem\": \"Sucesso\",\n }",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "../java/br/com/controleestoque/controller/LojaController.java",
    "groupTitle": "Loja"
  },
  {
    "type": "get",
    "url": "/api/loja",
    "title": "Listar todas as lojas",
    "name": "Listar_Lojas",
    "group": "Loja",
    "success": {
      "examples": [
        {
          "title": "Requisição com sucesso:",
          "content": "{\n\t\"codigo\": \"00\",\n\t\"mensagem\": \"Sucesso\",\n\t\"lojas\": [\n\t\t{\n\t\t\t\"id\": 4,\n\t\t\t\"nome\": \"Carreiro Sportes\",\n\t\t\t\"dataCadastro\": \"21/04/2018 11:33:28\"\n\t\t},\n\t\t{\n\t\t\t\"id\": 3,\n\t\t\t\"nome\": \"Netshoes\",\n\t\t\t\"dataCadastro\": \"21/04/2018 11:35:04\"\n\t\t}\n\t]\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "../java/br/com/controleestoque/controller/LojaController.java",
    "groupTitle": "Loja"
  },
  {
    "type": "get",
    "url": "/api/produto/:idProduto",
    "title": "Buscar produto por Id",
    "name": "Buscar_produto",
    "group": "Produto",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Id do produto a ser buscado.</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Requisição com sucesso:",
          "content": "HTTP/1.1 200 OK\n{\n\t\"codigo\": \"00\",\n\t\"mensagem\": \"Sucesso\",\n\t\"produto\": {\n\t\t\"id\": 5,\n\t\t\"nome\": \"Addias Mecury\",\n\t\t\"descricao\": \"Um chuteira de alta qualidade utilizada por lionel messi!\",\n\t\t\"valor\": 150.0,\n\t\t\"quantidade\": 0,\n\t\t\"dataCadastro\": \"21/04/2018 11:59:30\",\n\t\t\"loja\": 4\n\t}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "../java/br/com/controleestoque/controller/ProdutoController.java",
    "groupTitle": "Produto"
  },
  {
    "type": "post",
    "url": "/api/loja/:idLoja/produto",
    "title": "Cadastrar novo produto para loja",
    "name": "Cadastrar_Produto",
    "group": "Produto",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "idLoja",
            "description": "<p>Id da loja para qual você quer cadastrar o produto.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de requisição:",
          "content": "HTTP/1.1 200 OK\n{\n\t\"nome\": \"Chuteira\",\n\t\"descricao\": \"Marca Adidas, ótima qualidade\",\n\t\"quantidade\": 0,\n\t\"valor\": 120.00\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "codigo",
            "description": "<p>Código de retorno da API.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "mensagem",
            "description": "<p>Mensagem de retorno da API.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Id do produto cadastrado, esse Id pode ser utlizado para visualizar o produto posteriormente.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Requisição com sucesso:",
          "content": "HTTP/1.1 200 OK\n {\n\t \"codigo\": \"00\",\n\t \"mensagem\": \"Sucesso\",\n\t \"id\": 11\n }",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "../java/br/com/controleestoque/controller/ProdutoController.java",
    "groupTitle": "Produto"
  },
  {
    "type": "put",
    "url": "/api/produto/:idProduto",
    "title": "Editar produto loja",
    "name": "Editar_Produto",
    "group": "Produto",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "idProduto",
            "description": "<p>Id do produto que você quer editar.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de requisição:",
          "content": "HTTP/1.1 200 OK\n{\n\t\"nome\": \"Chuteira\",\n\t\"descricao\": \"Marca Adidas, ótima qualidade\",\n\t\"quantidade\": 0,\n\t\"valor\": 120.00\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "codigo",
            "description": "<p>Código de retorno da API.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "mensagem",
            "description": "<p>Mensagem de retorno da API.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Requisição com sucesso:",
          "content": "HTTP/1.1 200 OK\n {\n\t \"codigo\": \"00\",\n\t \"mensagem\": \"Sucesso\"\n }",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "../java/br/com/controleestoque/controller/ProdutoController.java",
    "groupTitle": "Produto"
  },
  {
    "type": "get",
    "url": "/api/loja/:idLoja/produto",
    "title": "Listar produtos por loja",
    "name": "Listar_Produtos",
    "group": "Produto",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "idLoja",
            "description": "<p>Id da loja que se quer listar os produtos.</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Requisição com sucesso:",
          "content": "HTTP/1.1 200 OK\n{\n\t\"codigo\": \"00\",\n\t\"mensagem\": \"Sucesso\",\n\t\"produtos\": [\n\t\t{\n\t\t\t\"id\": 6,\n\t\t\t\"nome\": \"Chuteira\",\n\t\t\t\"descricao\": \"Marca Adidas, ótima qualidade\",\n\t\t\t\"valor\": 120.0,\n\t\t\t\"quantidade\": 0,\n\t\t\t\"dataCadastro\": \"21/04/2018 11:43:07\",\n\t\t\t\"loja\": 4\n\t\t},\n\t\t{\n\t\t\t\"id\": 5,\n\t\t\t\"nome\": \"Addias Mecury\",\n\t\t\t\"descricao\": \"Um chuteira de alta qualidade utilizada por lionel messi!\",\n\t\t\t\"valor\": 150.0,\n\t\t\t\"quantidade\": 0,\n\t\t\t\"dataCadastro\": \"21/04/2018 11:59:30\",\n\t\t\t\"loja\": 4\n\t\t},\n\t\t{\n\t\t\t\"id\": 7,\n\t\t\t\"nome\": \"Addias Mecury\",\n\t\t\t\"descricao\": \"Um chuteira de alta qualidade utilizada por lionel messi!\",\n\t\t\t\"valor\": 150.0,\n\t\t\t\"quantidade\": 8,\n\t\t\t\"dataCadastro\": \"21/04/2018 11:54:07\",\n\t\t\t\"loja\": 4\n\t\t}\n\t]\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "../java/br/com/controleestoque/controller/ProdutoController.java",
    "groupTitle": "Produto"
  },
  {
    "type": "get",
    "url": "/api/venda/:id",
    "title": "Buscar venda por Id",
    "name": "Buscar_Venda",
    "group": "Venda",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Id da venda a ser buscada.</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Requisição com sucesso:",
          "content": "HTTP/1.1 200 OK\n{\n\t\"codigo\": \"00\",\n\t\"mensagem\": \"Sucesso\",\n\t\"id\": 11,\n\t\"dataVenda\": \"21/04/2018 12:57:00\",\n\t\"itens\": [\n\t\t{\n\t\t\t\"id\": 12,\n\t\t\t\"nome\": \"Addias Mecury\",\n\t\t\t\"descricao\": \"Um chuteira de alta qualidade utilizada por lionel messi!\",\n\t\t\t\"produto\": 7,\n\t\t\t\"valorTotal\": 300.0,\n\t\t\t\"valorUnitario\": 150.0,\n\t\t\t\"quantidade\": 2\n\t\t}\n\t],\n\t\"valorTotal\": 300.0,\n\t\"loja\": 4\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "../java/br/com/controleestoque/controller/VendaController.java",
    "groupTitle": "Venda"
  },
  {
    "type": "post",
    "url": "/api/loja/:idLoja/venda",
    "title": "Realizar uma nova venda",
    "name": "Realizar_Venda",
    "group": "Venda",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "idLoja",
            "description": "<p>Id da loja para qual você quer realizar a venda.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de requisição:",
          "content": "HTTP/1.1 200 OK\n{\n\t\"itens\": [\n\t\t{\n\t\t\t\"idProduto\": 7,\n\t\t\t\"quantidade\": 2\n\t\t}\n\t]\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Resposta de sucesso:",
          "content": "HTTP/1.1 200 OK\n {\n\t \"codigo\": \"00\",\n\t \"mensagem\": \"Sucesso\",\n\t \"id\": 11\n }",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "../java/br/com/controleestoque/controller/VendaController.java",
    "groupTitle": "Venda"
  }
] });
