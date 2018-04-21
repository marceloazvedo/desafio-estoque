package br.com.controleestoque.service.interfaces;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Autenticacao;
import br.com.controleestoque.model.Usuario;
import br.com.controleestoque.model.enums.Dispositivo;

public interface IUsuarioService {

	Usuario buscarPorLogin(String login) throws ErrorsValidationsException;

	Autenticacao login(String login, String senha, Dispositivo dispositivo)
			throws ErrorsValidationsException, Exception;

	Usuario getUsuarioPorToken(String token) throws ErrorsValidationsException;

}
