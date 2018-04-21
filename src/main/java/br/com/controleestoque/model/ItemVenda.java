package br.com.controleestoque.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class ItemVenda implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id;
	@ManyToOne
	private Produto produto;
	@NotNull(message = "Valor inválido para item")
	private BigDecimal valorTotal;
	@NotNull(message = "Valor inválido para item")
	@Min(value = 1, message = "Deve haver ao menos 1 item para venda")
	private Integer quantidade;
	@NotNull(message = "Valor inválido para valor unitário item")
	private BigDecimal valorUnitario;
	private String descricao;
	private String nome;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "ItemVenda [id=" + id + ", produto=" + produto.getId() + ", valorTotal=" + valorTotal + ", quantidade="
				+ quantidade + ", valorUnitario=" + valorUnitario + ", descricao=" + descricao + ", nome=" + nome + "]";
	}
	
	
}
