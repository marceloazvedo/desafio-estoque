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

import br.com.controleestoque.converter.LojaConverter;
import br.com.controleestoque.dto.LojaDTO;
import br.com.controleestoque.dto.LojaResponse;
import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Loja;
import br.com.controleestoque.model.Usuario;
import br.com.controleestoque.model.enums.MensagemAPI;
import br.com.controleestoque.service.interfaces.ILojaService;
import br.com.controleestoque.service.interfaces.IUsuarioService;

@RestController
public class LojaController {

	@Autowired
	private ILojaService lojaService;

	@Autowired
	private IUsuarioService usuarioService;

	/**
	 * @api {post} /api/loja Cadastrar nova loja
	 * @apiName Cadastrar Loja
	 * @apiGroup Loja
	 *
	 * @apiSuccess {String} codigo Código de retorno da API.
	 * @apiSuccess {String} mensagem Mensagem de retorno da API.
	 * @apiSuccess {Number} id Id da loja cadastrada, esse Id pode ser utlizado para visualizar a loja posteriormente.
	 * 
	 * @apiParamExample {json} Exemplo de requisição:
     * HTTP/1.1 200 OK
     * {
	 *	"nome":"Netshoes"
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
	@RequestMapping(value = "/api/loja", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LojaResponse cadastrar(@RequestBody LojaDTO lojaDTO, @RequestHeader("Authorization") String token) {
		try {
			Loja loja = LojaConverter.converter(lojaDTO);
			loja.setUsuario(usuarioService.getUsuarioPorToken(token));
			loja = lojaService.salvarOuAtualizar(loja);
			return new LojaResponse(MensagemAPI.SUCESSO, loja);
		} catch (ErrorsValidationsException e) {
			return new LojaResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new LojaResponse(MensagemAPI.ERRO_INTERNO_PROCESSAMENTO);
		}
	}

	/**
	 * @api {put} /api/loja/:id Editar loja
	 * @apiName Editar Loja
	 * @apiGroup Loja
	 * 
	 * @apiParam {Number} id Id da loja que você quer editar.
	 *
	 * @apiSuccess {String} codigo Código de retorno da API.
	 * @apiSuccess {String} mensagem Mensagem de retorno da API.
	 * 
	 * @apiParamExample {json} Exemplo de requisição:
     * HTTP/1.1 200 OK
     * {
	 *	"nome":"Netshoes"
	 * }
	 *
	 * @apiSuccessExample {json} Requisição com sucesso:
	 * HTTP/1.1 200 OK
	 *  {
     * 	 "codigo": "00",
     * 	 "mensagem": "Sucesso",
     *  }
	 **/
	@RequestMapping(value = "/api/loja/{id}", method = RequestMethod.PUT)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LojaResponse editar(@PathVariable(value = "id") Long id, @RequestBody LojaDTO lojaDTO, @RequestHeader("Authorization") String token) {
		try {
			Loja loja = LojaConverter.converter(lojaDTO);
			System.out.println("ID: " + id);
			loja.setUsuario(usuarioService.getUsuarioPorToken(token));
			loja.setId(id);
			lojaService.salvarOuAtualizar(loja);
			return new LojaResponse(MensagemAPI.SUCESSO);
		} catch (ErrorsValidationsException e) {
			return new LojaResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new LojaResponse(MensagemAPI.ERRO_INTERNO_PROCESSAMENTO);
		}
	}

	/**
	 * @api {get} /api/loja/:id Buscar loja por id
	 * @apiName Buscar Loja
	 * @apiGroup Loja
	 *
	 * @apiParam {Number} id Id da loja que você quer buscar.
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
	@RequestMapping(value = "/api/loja", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public LojaResponse listar(@RequestHeader("Authorization") String token) {
		try {
			Usuario usuario = usuarioService.getUsuarioPorToken(token);
			List<Loja> lojas = lojaService.listar(usuario);
			return new LojaResponse(MensagemAPI.SUCESSO, LojaConverter.converterListNormal(lojas));
		} catch (ErrorsValidationsException e) {
			return new LojaResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new LojaResponse(MensagemAPI.ERRO_INTERNO_PROCESSAMENTO);
		}
	}

	/**
	 * @api {get} /api/loja Listar todas as lojas
	 * @apiName Listar Lojas
	 * @apiGroup Loja
	 *
	 * @apiSuccessExample {json} Requisição com sucesso:
	 * {
	 * 	"codigo": "00",
	 * 	"mensagem": "Sucesso",
	 * 	"lojas": [
	 * 		{
	 * 			"id": 4,
	 * 			"nome": "Carreiro Sportes",
	 * 			"dataCadastro": "21/04/2018 11:33:28"
	 * 		},
	 * 		{
	 * 			"id": 3,
	 * 			"nome": "Netshoes",
	 * 			"dataCadastro": "21/04/2018 11:35:04"
	 * 		}
	 * 	]
	 * }
	 **/
	@RequestMapping(value = "/api/loja/{id}", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public LojaResponse buscar(@PathVariable(value = "id") Long id, @RequestHeader("Authorization") String token) {
		try {
			Usuario usuario = usuarioService.getUsuarioPorToken(token);
			Loja loja = lojaService.getLojaPorIdEUsuario(id, usuario);
			return new LojaResponse(MensagemAPI.SUCESSO, LojaConverter.converter(loja));
		} catch (ErrorsValidationsException e) {
			return new LojaResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new LojaResponse(MensagemAPI.ERRO_INTERNO_PROCESSAMENTO);
		}
	}

}
