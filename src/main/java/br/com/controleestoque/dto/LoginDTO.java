package br.com.controleestoque.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.controleestoque.model.enums.Dispositivo;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class LoginDTO {

	private String login;
	private String senha;
	private Dispositivo dispositivo;
	
	public LoginDTO(String login, String senha, Dispositivo dispositivo) {
		this.login = login;
		this.senha = senha;
		this.dispositivo = dispositivo;
	}
	
	public LoginDTO() {
		super();
	}



	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	@Override
	public String toString() {
		return "LoginDTO [login=" + login + ", senha=" + senha + ", dispositivo=" + dispositivo + "]";
	}
	
}
