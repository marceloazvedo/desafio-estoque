package br.com.controleestoque.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.enums.MensagemAPI;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class VendaResponse extends DefaultResponse {

	private Long id;
	private String dataVenda;
	private List<ItemVendaDTO> itens;
	private Double valorTotal;
	private Long loja;

	public VendaResponse(ErrorsValidationsException erros) {
		super(erros);
	}

	public VendaResponse(MensagemAPI mensagem) {
		super(mensagem);
	}

	public VendaResponse(MensagemAPI mensagem, Long id) {
		setId(id);
		setCodigoEMensagem(mensagem);
		setDataVenda(null);
		setItens(null);
		setValorTotal(null);
	}
	
	public VendaResponse parser(MensagemAPI mensagem) {
		setCodigoEMensagem(mensagem);
		return this;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public List<ItemVendaDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemVendaDTO> itens) {
		this.itens = itens;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLoja() {
		return loja;
	}

	public void setLoja(Long loja) {
		this.loja = loja;
	}

}
