package br.com.controleestoque.model.enums;

public enum MensagemAPI {

	SUCESSO("00","Sucesso"),
	ERRO_VALIDACAO("01","Erro: %s"),
	NADA_ENCONTRADO("02", "Não foi possível encontrar nenhuma informação"),
	OPERACAO_NAO_PERMITIDA("03", "Opração não permitida"),
	INSIRA_LOJA_VALIDA("04", "Insira uma loja válida"),
	INSIRA_ITENS_PARA_VENDA("05", "Selecione ao menos um item para venda"),
	PRODUTO_LOJA_DIFERENTE("06", "Produto de loja diferente, operação abortada"),
	INSIRA_PRODUTO_VALIDO("07", "Insira uma produto válido"),
	INSIRA_VENDA_VALIDA("08", "Insira uma venda válida"),
	ERRO_INTERNO_PROCESSAMENTO("99","Erro interno de processamento");
	
	private String codigo;
	private String mensagem;
	
	MensagemAPI(String codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
}
