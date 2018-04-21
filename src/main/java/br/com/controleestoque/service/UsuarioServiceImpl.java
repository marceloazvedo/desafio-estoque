package br.com.controleestoque.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.exception.ValidationException;
import br.com.controleestoque.model.Autenticacao;
import br.com.controleestoque.model.Usuario;
import br.com.controleestoque.model.enums.Dispositivo;
import br.com.controleestoque.repository.interfaces.IAutenticacaoRepository;
import br.com.controleestoque.repository.interfaces.IUsuarioRepository;
import br.com.controleestoque.service.interfaces.IUsuarioService;
import br.com.controleestoque.util.StringUtils;
import br.com.controleestoque.validation.ValidationAttributes;

@Component("usuarioService")
public class UsuarioServiceImpl extends GenericService implements IUsuarioService {
	
	private static final int HOUR_INCREMENT = 5;

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IAutenticacaoRepository autenticacaoRepository;

	@Override
	public Usuario buscarPorLogin(String login) throws ErrorsValidationsException {
		List<ValidationException> validations = new ArrayList<>();
		try {
			ValidationAttributes.validateAttributeIsNotBlank(login, "login", "Login é um campo obrigatório");
		} catch (ValidationException e) {
			validations.add(e);
		}
		if (!validations.isEmpty())
			throw new ErrorsValidationsException(validations, "Ocorreram erros ao buscar usuário");
		return usuarioRepository.findByLogin(login);
	}
	
	@Override
	public Autenticacao login(String login, String senha, Dispositivo dispositivo) throws ErrorsValidationsException, Exception {
		Usuario usuario = this.buscarPorLogin(login);
		if(usuario == null) {
			throw ValidationAttributes.buildUniqueErrorValidation("Usuário não encontrado", "login", login);
		}
		if(!usuario.getSenha().equals(senha)) {
			throw ValidationAttributes.buildUniqueErrorValidation("Senha incorreta", "senha", null);
		}
		Autenticacao autenticacao = new Autenticacao();
		autenticacao.setDispositivo(dispositivo);
		Calendar validade = Calendar.getInstance();
		validade.add(Calendar.HOUR, HOUR_INCREMENT);
		autenticacao.setValidade(validade);
		autenticacao.setUsuario(usuario);
		try {
			autenticacao.setToken(StringUtils.getTokenHash(usuario.getId(), usuario.getSenha(), dispositivo));
		}catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return autenticacaoRepository.save(autenticacao);
	}
	
	@Override
	public Usuario getUsuarioPorToken(String token) throws ErrorsValidationsException {
		if(token == null || token.isEmpty()) {
			throw ValidationAttributes.buildUniqueErrorValidation("Token inválido", "token", token);
		}
		Autenticacao autenticacao = autenticacaoRepository.findOneByToken(token);
		return autenticacao.getUsuario();
	}

}
