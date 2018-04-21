package br.com.controleestoque.service.interfaces;

import java.util.List;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Produto;
import br.com.controleestoque.model.Usuario;

public interface IProdutoService {

	Produto salvarOuAtualizar(Produto produto, Usuario usuario) throws ErrorsValidationsException;
	
	List<Produto> listar(Long idLoja, Usuario usuario) throws ErrorsValidationsException;
	
	Produto getProdutoPorIdEUsuario(Long idProduto, Usuario usuario) throws ErrorsValidationsException;
	
}
