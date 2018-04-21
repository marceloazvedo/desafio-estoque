package br.com.controleestoque.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleestoque.converter.ProdutoConverter;
import br.com.controleestoque.dto.ProdutoDTO;
import br.com.controleestoque.dto.ProdutoResponse;
import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Produto;
import br.com.controleestoque.model.enums.MensagemAPI;
import br.com.controleestoque.service.interfaces.IProdutoService;
import br.com.controleestoque.service.interfaces.IUsuarioService;

@RestController
public class ProdutoController {

	@Autowired
	private IProdutoService produtoService;

	@Autowired
	private IUsuarioService usuarioService;
	
	/**
	 * @api {post} /api/loja/:idLoja/produto Cadastrar novo produto para loja
	 * @apiName Cadastrar Produto
	 * @apiGroup Produto
	 *
	 * @apiParam {Number} idLoja Id da loja para qual você quer cadastrar o produto.
	 *
	 * @apiSuccess {String} codigo Código de retorno da API.
	 * @apiSuccess {String} mensagem Mensagem de retorno da API.
	 * @apiSuccess {Number} id Id do produto cadastrado, esse Id pode ser utlizado para visualizar o produto posteriormente.
	 * 
	 * @apiParamExample {json} Exemplo de requisição:
     * HTTP/1.1 200 OK
     * {
     * 	"nome": "Chuteira",
	 * 	"descricao": "Marca Adidas, ótima qualidade",
	 * 	"quantidade": 0,
	 * 	"valor": 120.00
	 * }
	 *
	 * @apiSuccessExample {json} Requisição com sucesso:
	 * HTTP/1.1 200 OK
	 *  {
     * 	 "codigo": "00",
     * 	 "mensagem": "Sucesso",
     * 	 "id": 11
     *  }
	 **/
	@RequestMapping(value = "/api/loja/{idLoja}/produto", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoResponse cadastrar(@PathVariable(value = "idLoja") Long idLoja, @RequestBody ProdutoDTO produtoDTO, @RequestHeader("Authorization") String token) {
		try {
			produtoDTO.setLoja(idLoja);
			Produto produto = produtoService.salvarOuAtualizar(ProdutoConverter.converter(produtoDTO), usuarioService.getUsuarioPorToken(token));
			return new ProdutoResponse(MensagemAPI.SUCESSO, produto.getId());
		} catch (ErrorsValidationsException e) {
			return new ProdutoResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new ProdutoResponse(MensagemAPI.ERRO_INTERNO_PROCESSAMENTO);
		}
	}
	
	/**
	 * @api {put} /api/produto/:idProduto Editar produto loja
	 * @apiName Editar Produto
	 * @apiGroup Produto
	 *
	 * @apiParam {Number} idProduto Id do produto que você quer editar.
	 *
	 * @apiSuccess {String} codigo Código de retorno da API.
	 * @apiSuccess {String} mensagem Mensagem de retorno da API.
	 * 
	 * @apiParamExample {json} Exemplo de requisição:
     * HTTP/1.1 200 OK
     * {
     * 	"nome": "Chuteira",
	 * 	"descricao": "Marca Adidas, ótima qualidade",
	 * 	"quantidade": 0,
	 * 	"valor": 120.00
	 * }
	 *
	 * @apiSuccessExample {json} Requisição com sucesso:
	 * HTTP/1.1 200 OK
	 *  {
     * 	 "codigo": "00",
     * 	 "mensagem": "Sucesso"
     *  }
	 **/
	@RequestMapping(value = "/api/produto/{id}", method = RequestMethod.PUT)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoResponse editar(@PathVariable(value = "id") Long id, @RequestBody ProdutoDTO produtoDTO, @RequestHeader("Authorization") String token) {
		try {
			Produto produto = ProdutoConverter.converter(produtoDTO);
			produto.setId(id);
			produtoService.salvarOuAtualizar(produto, usuarioService.getUsuarioPorToken(token));
			return new ProdutoResponse(MensagemAPI.SUCESSO);
		} catch (ErrorsValidationsException e) {
			return new ProdutoResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new ProdutoResponse(MensagemAPI.ERRO_INTERNO_PROCESSAMENTO);
		}
	}
	
	/**
	 * @api {get} /api/produto/:idProduto Buscar produto por Id
	 * @apiName Buscar produto
	 * @apiGroup Produto
	 *
	 * @apiParam {Number} id Id do produto a ser buscado.
	 *
	 * @apiSuccessExample {json} Requisição com sucesso:
	 * HTTP/1.1 200 OK
	 * {
	 * 	"codigo": "00",
	 * 	"mensagem": "Sucesso",
	 * 	"produto": {
	 * 		"id": 5,
	 * 		"nome": "Addias Mecury",
	 * 		"descricao": "Um chuteira de alta qualidade utilizada por lionel messi!",
	 * 		"valor": 150.0,
	 * 		"quantidade": 0,
	 * 		"dataCadastro": "21/04/2018 11:59:30",
	 * 		"loja": 4
	 * 	}
	 * }
	 **/
	@RequestMapping(value = "/api/produto/{idProduto}", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoResponse buscar(@PathVariable(value = "idProduto") Long idProduto, @RequestHeader("Authorization") String token) {
		try {
			Produto produto = produtoService.getProdutoPorIdEUsuario(idProduto, usuarioService.getUsuarioPorToken(token));
			return new ProdutoResponse(MensagemAPI.SUCESSO, ProdutoConverter.converter(produto));
		} catch (ErrorsValidationsException e) {
			return new ProdutoResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new ProdutoResponse(MensagemAPI.ERRO_INTERNO_PROCESSAMENTO);
		}
	}
	
	/**
	 * @api {get} /api/loja/:idLoja/produto Listar produtos por loja
	 * @apiName Listar Produtos
	 * @apiGroup Produto
	 *
	 * @apiParam {Number} idLoja Id da loja que se quer listar os produtos.
	 *
	 * @apiSuccessExample {json} Requisição com sucesso:
	 * HTTP/1.1 200 OK
	* {
	* 	"codigo": "00",
	* 	"mensagem": "Sucesso",
	* 	"produtos": [
	* 		{
	* 			"id": 6,
	* 			"nome": "Chuteira",
	* 			"descricao": "Marca Adidas, ótima qualidade",
	* 			"valor": 120.0,
	* 			"quantidade": 0,
	* 			"dataCadastro": "21/04/2018 11:43:07",
	* 			"loja": 4
	* 		},
	* 		{
	* 			"id": 5,
	* 			"nome": "Addias Mecury",
	* 			"descricao": "Um chuteira de alta qualidade utilizada por lionel messi!",
	* 			"valor": 150.0,
	* 			"quantidade": 0,
	* 			"dataCadastro": "21/04/2018 11:59:30",
	* 			"loja": 4
	* 		},
	* 		{
	* 			"id": 7,
	* 			"nome": "Addias Mecury",
	* 			"descricao": "Um chuteira de alta qualidade utilizada por lionel messi!",
	* 			"valor": 150.0,
	* 			"quantidade": 8,
	* 			"dataCadastro": "21/04/2018 11:54:07",
	* 			"loja": 4
	* 		}
	* 	]
	* }
	 * 
	 **/
	@RequestMapping(value = "/api/loja/{idLoja}/produto", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoResponse listar(@PathVariable(value = "idLoja") Long idLoja, @RequestHeader("Authorization") String token) {
		try {
			List<Produto> produtos = produtoService.listar(idLoja, usuarioService.getUsuarioPorToken(token));
			return new ProdutoResponse(MensagemAPI.SUCESSO, ProdutoConverter.converterListNormal(produtos));
		} catch (ErrorsValidationsException e) {
			return new ProdutoResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new ProdutoResponse(MensagemAPI.ERRO_INTERNO_PROCESSAMENTO);
		}
	}
	
}
