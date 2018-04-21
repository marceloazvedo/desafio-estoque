package br.com.controleestoque.exception;

import java.util.ArrayList;
import java.util.List;

import br.com.controleestoque.model.enums.MensagemAPI;

public class ErrorsValidationsException extends Exception {

	private static final long serialVersionUID = 1L;
	private List<ValidationException> validationErrors;
	private MensagemAPI mensagem;
	
	public ErrorsValidationsException(String message){
		super(message);
		this.validationErrors = new ArrayList<>();
	}
	
	public ErrorsValidationsException(MensagemAPI mensagem){
		setMensagem(mensagem);
		this.validationErrors = new ArrayList<>();
	}
	
	public ErrorsValidationsException(MensagemAPI mensagem, List<ValidationException> validationErrors, String message){
		super(message);
		setMensagem(mensagem);
		this.validationErrors = validationErrors;
	}
	
	public ErrorsValidationsException(List<ValidationException> validationErrors, String message){
		super(message);
		this.validationErrors = validationErrors;
	}

	public List<ValidationException> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<ValidationException> validationErrors) {
		this.validationErrors = validationErrors;
	}

	public MensagemAPI getMensagem() {
		return mensagem;
	}

	public void setMensagem(MensagemAPI mensagem) {
		this.mensagem = mensagem;
	}
}
