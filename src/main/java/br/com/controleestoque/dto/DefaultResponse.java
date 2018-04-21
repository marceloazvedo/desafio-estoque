package br.com.controleestoque.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.enums.MensagemAPI;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DefaultResponse {

	private String codigo;
	private String mensagem;
	private List<ErroCampo> erros;

	public DefaultResponse() {
	}

	public DefaultResponse(String codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public DefaultResponse(ErrorsValidationsException erros) {
		this.codigo = erros.getMensagem().getCodigo();
		this.mensagem = erros.getMensagem().getMensagem();
		setValidationErrors(erros);
		if (erros.getMensagem() == MensagemAPI.ERRO_VALIDACAO)
			this.mensagem = String.format(erros.getMensagem().getMensagem(), erros.getMessage());
		else
			setErros(null);
	}

	public DefaultResponse(MensagemAPI mensagem) {
		setCodigoEMensagem(mensagem);
	}

	public void setCodigoEMensagem(MensagemAPI mensagem) {
		if (mensagem != null) {
			this.codigo = mensagem.getCodigo();
			this.mensagem = mensagem.getMensagem();
		}
	}

	private void setValidationErrors(ErrorsValidationsException erros) {
		this.erros = new ArrayList<>();
		erros.getValidationErrors().forEach((err) -> {
			this.erros.add(new ErroCampo(err.getField(), err.getValue(), err.getMessage()));
		});
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<ErroCampo> getErros() {
		return erros;
	}

	public void setErros(List<ErroCampo> erros) {
		this.erros = erros;
	}

	@Override
	public String toString() {
		return "DefaultResponse [codigo=" + codigo + ", mensagem=" + mensagem + ", erros=" + erros + "]";
	}

}
