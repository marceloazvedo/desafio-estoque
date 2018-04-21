package br.com.controleestoque.dto;

public class LojaDTO {
	
	private Long id;
	private String nome;
	private String dataCadastro;
	
	public LojaDTO() {
	}
	
	public LojaDTO(Long id, String nome, String dataCadastro) {
		setId(id);
		setNome(nome);
		setDataCadastro(dataCadastro);
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

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "LojaDTO [id=" + id + ", nome=" + nome + ", dataCadastro=" + dataCadastro + "]";
	}
	
}
