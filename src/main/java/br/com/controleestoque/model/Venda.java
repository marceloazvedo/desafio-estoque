package br.com.controleestoque.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ItemVenda> itens;
	@NotNull(message = "Valor total é um campo necessário")
	@Min(value = 0)
	private BigDecimal valorTotal;
	@NotNull(message = "Necessário cadastrar a data da venda")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataVenda;
	@ManyToOne
	private Loja loja;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> produtos) {
		this.itens = produtos;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Calendar getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", produtos=" + itens + ", valorTotal=" + valorTotal + ", dataVenda=" + dataVenda
				+ ", loja=" + loja.getId() + "]";
	}

}
