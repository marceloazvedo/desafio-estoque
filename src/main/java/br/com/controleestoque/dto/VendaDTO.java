package br.com.controleestoque.dto;

import java.util.ArrayList;
import java.util.List;

public class VendaDTO {
	
	private List<ItemDTO> itens;
	
	public VendaDTO() {
		itens = new ArrayList<>();
	}

	public List<ItemDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemDTO> itens) {
		this.itens = itens;
	}
	
}
