package br.com.controleestoque.converter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.controleestoque.dto.ProdutoDTO;
import br.com.controleestoque.model.Loja;
import br.com.controleestoque.model.Produto;
import br.com.controleestoque.util.StringUtils;

public class ProdutoConverter {

	public static Produto converter(ProdutoDTO produtoDTO) throws ParseException {
		if(produtoDTO == null) return null;
		Produto produto = new Produto();
		produto.setDataCadastro(
				produtoDTO.getDataCadastro() == null || produtoDTO.getDataCadastro().isEmpty() ? Calendar.getInstance()
						: StringUtils.getCalendarFullDate(produtoDTO.getDataCadastro()));
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setId(produto.getId());
		produto.setNome(produtoDTO.getNome());
		produto.setQuantidade(produtoDTO.getQuantidade());
		produto.setValor(produtoDTO.getValor() == null ? null : new BigDecimal(produtoDTO.getValor()));
		Loja loja = new Loja();
		loja.setId(produtoDTO.getLoja());
		produto.setLoja(loja);
		return produto;
	}

	public static ProdutoDTO converter(Produto produto) throws ParseException {
		if(produto == null) return null;
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setDataCadastro(StringUtils.getDataCompleta(produto.getDataCadastro()));
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setId(produto.getId());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setQuantidade(produto.getQuantidade());
		produtoDTO.setValor(produto.getValor().doubleValue());
		produtoDTO.setLoja(produto.getLoja().getId());
		return produtoDTO;
	}
	
	public static List<ProdutoDTO> converterListNormal(List<Produto> produtos) {
		List<ProdutoDTO> lista = new ArrayList<>();
		produtos.forEach(produto -> {
			try {
				lista.add(converter(produto));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
		return lista;
	}

	public static List<Produto> converterListDTO(List<ProdutoDTO> produtos) {
		List<Produto> lista = new ArrayList<>();
		produtos.forEach(produto -> {
			try {
				lista.add(converter(produto));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
		return lista;
	}

}
