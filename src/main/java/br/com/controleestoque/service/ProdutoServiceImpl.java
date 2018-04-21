package br.com.controleestoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Loja;
import br.com.controleestoque.model.Produto;
import br.com.controleestoque.model.Usuario;
import br.com.controleestoque.model.enums.MensagemAPI;
import br.com.controleestoque.repository.interfaces.ILojaRepository;
import br.com.controleestoque.repository.interfaces.IProdutoRepository;
import br.com.controleestoque.service.interfaces.IProdutoService;

@Component("produtoService")
public class ProdutoServiceImpl extends GenericService implements IProdutoService {

	@Autowired
	private IProdutoRepository produtoRepository;
	@Autowired
	private ILojaRepository lojaRepository;

	@Override
	public Produto salvarOuAtualizar(Produto produto, Usuario usuario) throws ErrorsValidationsException {
		validateEntity(produto);
		Produto p = produto.getId() == null ? null : produtoRepository.findOneById(produto.getId());
		if(produto.getId() != null && p == null) {
			throw new ErrorsValidationsException(MensagemAPI.INSIRA_PRODUTO_VALIDO);
		}
		Loja loja = lojaRepository.findOneByIdAndUsuario(produto.getLoja().getId(), usuario);
		if(loja == null) {
			throw new ErrorsValidationsException(MensagemAPI.INSIRA_LOJA_VALIDA);
		}
		if (!loja.getUsuario().equals(usuario))
			throw new ErrorsValidationsException(MensagemAPI.OPERACAO_NAO_PERMITIDA);
		return produtoRepository.save(produto);
	}

	@Override
	public List<Produto> listar(Long idLoja, Usuario usuario) throws ErrorsValidationsException {
		Loja loja = lojaRepository.findOneByIdAndUsuario(idLoja, usuario);
		if(loja == null)
			throw new ErrorsValidationsException(MensagemAPI.INSIRA_LOJA_VALIDA);
		return produtoRepository.findByLoja(loja);
	}

	@Override
	public Produto getProdutoPorIdEUsuario(Long idProduto, Usuario usuario) throws ErrorsValidationsException {
		Produto p = produtoRepository.findOneById(idProduto);
		System.out.println(p==null? "null" : "hehehe");
		if(p == null)
			throw new ErrorsValidationsException(MensagemAPI.NADA_ENCONTRADO);
		if (!p.getLoja().getUsuario().equals(usuario))
			throw new ErrorsValidationsException(MensagemAPI.INSIRA_LOJA_VALIDA);
		return p;
	}

}
