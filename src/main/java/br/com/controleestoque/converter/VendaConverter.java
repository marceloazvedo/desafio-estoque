package br.com.controleestoque.converter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.controleestoque.dto.VendaResponse;
import br.com.controleestoque.model.Loja;
import br.com.controleestoque.model.Venda;
import br.com.controleestoque.util.StringUtils;

public class VendaConverter {

	public static Venda converter(VendaResponse vendaResponse) throws ParseException {
		if(vendaResponse == null) return null;
		Venda venda = new Venda();
		venda.setDataVenda(StringUtils.getCalendarFullDate(vendaResponse.getDataVenda()));
		venda.setItens(ItemVendaConverter.converterListDTO(vendaResponse.getItens()));
		Loja loja = new Loja();
		loja.setId(vendaResponse.getLoja());
		venda.setLoja(loja);
		venda.setValorTotal(new BigDecimal(vendaResponse.getValorTotal()));
		venda.setId(vendaResponse.getId());
		return venda;
	}

	public static VendaResponse converter(Venda venda) {
		if(venda == null) return null;
		VendaResponse vendaResponse = new VendaResponse(null, venda.getId());
		vendaResponse.setDataVenda(StringUtils.getDataCompleta(venda.getDataVenda()));
		vendaResponse.setValorTotal(venda.getValorTotal().doubleValue());
		vendaResponse.setItens(ItemVendaConverter.converterListNormal(venda.getItens()));
		vendaResponse.setLoja(venda.getLoja().getId());
		return vendaResponse;
	}

	public static List<VendaResponse> converterListNormal(List<Venda> vendas) {
		List<VendaResponse> lista = new ArrayList<>();
		vendas.forEach(venda -> {
			lista.add(converter(venda));
		});
		return lista;
	}

	public static List<Venda> converterListDTO(List<VendaResponse> vendas) {
		List<Venda> lista = new ArrayList<>();
		vendas.forEach(loja -> {
			try {
				lista.add(converter(loja));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
		return lista;
	}
	
}
