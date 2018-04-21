package br.com.controleestoque.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Loja;
import br.com.controleestoque.model.enums.MensagemAPI;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class LojaResponse extends DefaultResponse {

	private Long id;
	private List<LojaDTO> lojas;
	private LojaDTO loja;

	public LojaResponse(MensagemAPI mensagem) {
		super(mensagem);
		setId(null);
		setLojas(null);
		setLoja(null);
	}

	public LojaResponse(ErrorsValidationsException erros) {
		super(erros);
		setId(null);
		setLojas(null);
		setLoja(null);
	}

	public LojaResponse(MensagemAPI mensagem, Loja loja) {
		super(mensagem);
		setId(loja.getId());
		setLojas(null);
		setLoja(null);
	}

	public LojaResponse(MensagemAPI mensagem, List<LojaDTO> lojas) {
		super(mensagem);
		setLojas(lojas);
		setLoja(null);
		setId(null);
	}

	public LojaResponse(MensagemAPI mensagem, LojaDTO loja) {
		super(mensagem);
		setLoja(loja);
		setLojas(null);
		setId(null);
		setErros(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LojaDTO> getLojas() {
		return lojas;
	}

	public void setLojas(List<LojaDTO> lojas) {
		this.lojas = lojas;
	}

	public LojaDTO getLoja() {
		return loja;
	}

	public void setLoja(LojaDTO loja) {
		this.loja = loja;
	}

}
