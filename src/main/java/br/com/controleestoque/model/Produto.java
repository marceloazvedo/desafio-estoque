package br.com.controleestoque.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.controleestoque.util.StringUtils;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "Nome é um campo necessário")
	private String nome;
	@NotBlank(message = "Descrição é um campo necessário")
	private String descricao;
	@DecimalMin(value= "0.00", message = "Valor não pode ser menor que 0")
	@NotNull(message = "Valor é um campo necessário")
	private BigDecimal valor;
	@Min(value = 0, message = "Quantidade não pode ser menor que 0")
	@NotNull(message = "Quantidade é um campo necessário")
	private Integer quantidade;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro;
	@ManyToOne
	private Loja loja;
	
	public Produto() {
		dataCadastro = Calendar.getInstance();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Integer decrementarQuantidade(Integer quantidade) {
		this.quantidade -= quantidade;
		return this.quantidade;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", valor=" + valor
				+ ", quantidade=" + quantidade + ", dataCadastro=" + StringUtils.getDataCompleta(dataCadastro) + ", loja=" + loja.getNome() + "]";
	}

}
