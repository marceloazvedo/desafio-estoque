package br.com.controleestoque.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.controleestoque.model.enums.MensagemAPI;

public class ListaLojaResponse extends DefaultResponse {
	
	private List<LojaDTO> lojas;

	public ListaLojaResponse(MensagemAPI mensagem) {
		super(mensagem);
		this.lojas = new ArrayList<>();
	}
	
	public ListaLojaResponse(MensagemAPI mensagem, List<LojaDTO> lojas) {
		super(mensagem);
		this.lojas = lojas;
	}

	public List<LojaDTO> getLojas() {
		return lojas;
	}

	public void setLojas(List<LojaDTO> lojas) {
		this.lojas = lojas;
	}

	@Override
	public String toString() {
		return "ListaLojaDTO [lojas=" + lojas + ", getCodigo()=" + getCodigo() + ", getMensagem()=" + getMensagem()
				+ "]";
	}

	
}
