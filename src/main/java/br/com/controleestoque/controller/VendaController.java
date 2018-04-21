package br.com.controleestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleestoque.converter.VendaConverter;
import br.com.controleestoque.dto.VendaDTO;
import br.com.controleestoque.dto.VendaResponse;
import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Venda;
import br.com.controleestoque.model.enums.MensagemAPI;
import br.com.controleestoque.service.interfaces.IUsuarioService;
import br.com.controleestoque.service.interfaces.IVendaService;

@RestController
public class VendaController {
	
	@Autowired
	private IVendaService vendaService;
	
	@Autowired
	private IUsuarioService usuarioService;

	/**
	 * @api {post} /api/loja/:idLoja/venda Realizar uma nova venda
	 * @apiName Realizar Venda
	 * @apiGroup Venda
	 *
	 * @apiParam {Number} idLoja Id da loja para qual você quer realizar a venda.
	 *
	 * @apiParamExample {json} Exemplo de requisição:
     * HTTP/1.1 200 OK
     * {
     * 	"itens": [
     * 		{
     * 			"idProduto": 7,
     * 			"quantidade": 2
     * 		}
     * 	]
     * }
	 *
	 * @apiSuccessExample {json} Resposta de sucesso:
	 * HTTP/1.1 200 OK
	 *  {
     * 	 "codigo": "00",
     * 	 "mensagem": "Sucesso",
     * 	 "id": 11
     *  }
	 **/
	@RequestMapping(value = "/api/loja/{idLoja}/venda", method = RequestMethod.POST)
	public VendaResponse realizarVenda(@PathVariable("idLoja") Long idLoja, @RequestBody VendaDTO vendaDTO, @RequestHeader("Authorization") String token) {
		try {
			Venda venda = vendaService.realizarVenda(idLoja, usuarioService.getUsuarioPorToken(token), vendaDTO);
			return new VendaResponse(MensagemAPI.SUCESSO, venda.getId());
		} catch (ErrorsValidationsException e) {
			return new VendaResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new VendaResponse(MensagemAPI.ERRO_INTERNO_PROCESSAMENTO);
		}
	}
	
	/**
	 * @api {get} /api/venda/:id Buscar venda por Id
	 * @apiName Buscar Venda
	 * @apiGroup Venda
	 *
	 * @apiParam {Number} id Id da venda a ser buscada.
	 *
	 * @apiSuccessExample {json} Requisição com sucesso:
	 * HTTP/1.1 200 OK
	 * {
	 *	"codigo": "00",
	 *	"mensagem": "Sucesso",
	 *	"id": 11,
	 *	"dataVenda": "21/04/2018 12:57:00",
	 *	"itens": [
	 *		{
	 *			"id": 12,
	 *			"nome": "Addias Mecury",
	 *			"descricao": "Um chuteira de alta qualidade utilizada por lionel messi!",
	 *			"produto": 7,
	 *			"valorTotal": 300.0,
	 *			"valorUnitario": 150.0,
	 *			"quantidade": 2
	 *		}
	 *	],
	 *	"valorTotal": 300.0,
	 *	"loja": 4
	 * }
	 **/
	@RequestMapping(value = "/api/venda/{id}", method = RequestMethod.GET)
	public VendaResponse realizarVenda(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
		try {
			Venda venda = vendaService.buscarVendaPorIdEUsuario(id, usuarioService.getUsuarioPorToken(token));
			return VendaConverter.converter(venda).parser(MensagemAPI.SUCESSO);
		} catch (ErrorsValidationsException e) {
			return new VendaResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new VendaResponse(MensagemAPI.ERRO_INTERNO_PROCESSAMENTO);
		}
	}
	
}
