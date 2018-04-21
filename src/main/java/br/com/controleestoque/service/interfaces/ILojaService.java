package br.com.controleestoque.service.interfaces;

import java.util.List;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Loja;
import br.com.controleestoque.model.Usuario;

public interface ILojaService {

	Loja salvarOuAtualizar(Loja loja) throws ErrorsValidationsException;
	
	List<Loja> listar(Usuario usuario) throws ErrorsValidationsException;
	
	Loja getLojaPorIdEUsuario(Long id, Usuario usuario) throws ErrorsValidationsException;
	
}
