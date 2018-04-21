package br.com.controleestoque.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Autenticacao;
import br.com.controleestoque.model.enums.MensagemAPI;
import br.com.controleestoque.util.StringUtils;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AutenticacaoResponse extends DefaultResponse {
	
	private String token;
	private String validade;

	public AutenticacaoResponse(MensagemAPI mensagem, String token, String validade) {
		super(mensagem);
		this.token = token;
		this.validade = validade;
	}
	
	public AutenticacaoResponse(ErrorsValidationsException erros) {
		super(erros);
	}
	
	public AutenticacaoResponse(MensagemAPI mensagem) {
		super(mensagem);
	}
	
	public AutenticacaoResponse(MensagemAPI mensagem, Autenticacao autenticacao) {
		super(mensagem);
		setToken(autenticacao.getToken());
		setValidade(StringUtils.getDataCompleta(autenticacao.getValidade()));
		setErros(null);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

}
