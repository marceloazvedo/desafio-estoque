package br.com.controleestoque.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleestoque.dto.AutenticacaoResponse;
import br.com.controleestoque.dto.LoginDTO;
import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Autenticacao;
import br.com.controleestoque.model.enums.MensagemAPI;
import br.com.controleestoque.service.interfaces.IUsuarioService;

@RestController
public class AuthenticationController {
	
	@Autowired
	private IUsuarioService usuarioService;
		
	/**
	 * @api {post} /api/autenticar Autenticacao
	 * @apiName Autenticacao
	 * @apiGroup Autenticacao
	 * 
	 * @apiDescription Neste serviço você obtém o token necessário para consumir qualquer serviço dentro da API, o login padrão é <code>admin</code> e senha <code>123456</code>. O dispositivo pode ser WEB ou MOBILE, utilize WEB.
	 *
	 * @apiSuccess {String} codigo Código de retorno da API.
	 * @apiSuccess {String} mensagem Mensagem de retorno da API.
	 * @apiSuccess {String} token Token utilizado para efetuar qualquer requisição dentro da API.
	 * @apiSuccess {String} validade Até quando o token utilizado é válido, o token é valido por 5 horas depois da da última requisição feita ao serviço.
	 * 
	 * @apiParamExample {json} Exemplo de requisição:
     * HTTP/1.1 200 OK
     * {
	 *	"login":"admin",
	 *	"senha":"123456",
	 *	"dispositivo": "WEB"
	 * }
	 * @apiSuccessExample {json} Requisição com sucesso:
	 * HTTP/1.1 200 OK
	 * {
	 * 	"codigo": "00",
	 * 	"mensagem": "Sucesso",
	 * 	"token": "9A54036D6C611D367C71D55651A6D5D91D6BD64422A2CF510B533E16959D1585",
	 * 	"validade": "21/04/2018 19:17:36"
	 * }
	 **/
	@RequestMapping(value = "/api/autenticar", method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public AutenticacaoResponse autenticar(@RequestBody LoginDTO login) {
		try {
			System.out.println(login);
			Autenticacao autenticacao = usuarioService.login(login.getLogin(), login.getSenha(), login.getDispositivo());
			return new AutenticacaoResponse(MensagemAPI.SUCESSO, autenticacao);
		} catch (ErrorsValidationsException e) {
			return new AutenticacaoResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new AutenticacaoResponse(MensagemAPI.ERRO_INTERNO_PROCESSAMENTO);
		}
        
    }
	
}
