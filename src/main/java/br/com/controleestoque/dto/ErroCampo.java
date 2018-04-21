package br.com.controleestoque.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ErroCampo {

	private String campo;
	private String valor;
	private String erro;
	
	public ErroCampo() {
	}

	public ErroCampo(String campo, String valor, String erro) {
		super();
		this.campo = campo;
		this.valor = valor;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	@Override
	public String toString() {
		return "ErroCampo [campo=" + campo + ", valor=" + valor + ", erro=" + erro + "]";
	}
}
