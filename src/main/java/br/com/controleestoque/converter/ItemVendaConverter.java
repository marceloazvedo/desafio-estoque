package br.com.controleestoque.converter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.controleestoque.dto.ItemVendaDTO;
import br.com.controleestoque.model.ItemVenda;
import br.com.controleestoque.model.Produto;

public class ItemVendaConverter {

	public static ItemVenda converter(ItemVendaDTO itemVendaDTO) throws ParseException {
		if(itemVendaDTO == null) return null;
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setDescricao(itemVendaDTO.getDescricao());
		itemVenda.setId(itemVendaDTO.getId());
		itemVenda.setNome(itemVendaDTO.getNome());
		Produto produto = new Produto();
		produto.setId(itemVenda.getProduto().getId());
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(itemVendaDTO.getQuantidade());
		itemVenda.setValorTotal(new BigDecimal(itemVendaDTO.getValorTotal()));
		itemVenda.setValorUnitario(new BigDecimal(itemVendaDTO.getValorUnitario()));
		return itemVenda;
	}

	public static ItemVendaDTO converter(ItemVenda itemVenda) {
		if(itemVenda == null) return null;
		ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
		itemVendaDTO.setId(itemVenda.getId());
		itemVendaDTO.setNome(itemVenda.getNome());
		itemVendaDTO.setDescricao(itemVenda.getDescricao());
		itemVendaDTO.setProduto(itemVenda.getProduto().getId());
		itemVendaDTO.setQuantidade(itemVenda.getQuantidade());
		itemVendaDTO.setValorTotal(itemVenda.getValorTotal().doubleValue());
		itemVendaDTO.setValorUnitario(itemVenda.getValorUnitario().doubleValue());
		return itemVendaDTO;
	}

	public static List<ItemVendaDTO> converterListNormal(List<ItemVenda> itens) {
		List<ItemVendaDTO> lista = new ArrayList<>();
		itens.forEach(loja -> {
			lista.add(converter(loja));
		});
		return lista;
	}

	public static List<ItemVenda> converterListDTO(List<ItemVendaDTO> itensDto) {
		List<ItemVenda> lista = new ArrayList<>();
		itensDto.forEach(loja -> {
			try {
				lista.add(converter(loja));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
		return lista;
	}
	
}
