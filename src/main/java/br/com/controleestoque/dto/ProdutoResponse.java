package br.com.controleestoque.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Produto;
import br.com.controleestoque.model.enums.MensagemAPI;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProdutoResponse extends DefaultResponse {

	private Long id;
	private ProdutoDTO produto;
	private List<ProdutoDTO> produtos;
	
	

	public ProdutoResponse(MensagemAPI mensagem) {
		super(mensagem);
		setProduto(null);
		setProdutos(null);
		setId(null);
		setErros(null);
	}
	
	public ProdutoResponse(MensagemAPI mensagem, Long idProduto) {
		super(mensagem);
		setId(idProduto);
		setProduto(null);
		setProdutos(null);
		setErros(null);
	}

	public ProdutoResponse(MensagemAPI mensagem, ProdutoDTO produto) {
		super(mensagem);
		setProduto(produto);
		setId(null);
		setProdutos(null);
		setErros(null);
	}
	
	public ProdutoResponse(MensagemAPI mensagem, List<ProdutoDTO> produtos) {
		super(mensagem);
		setProdutos(produtos);
		setId(null);
		setProduto(null);
		setErros(null);
	}

	public ProdutoResponse(ErrorsValidationsException e) {
		super(e);
		setProduto(null);
		setProdutos(null);
		setId(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}

}
