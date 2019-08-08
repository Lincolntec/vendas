package br.com.lincolntec.pedidosVenda.model;

public enum FormaPagamento {

	DINHEIRO("Dinheiro"),
	CARTAO_CREDITO("Cartão de crédito"),
	CARTAO_DEBITO("Cartão de débito"), 
	CHEQUE("Cheque"),
	BOLETO_BANCARIO("Boleto bancário"),
	DEBITO_EM_CONTA("Debito em conta"),
	DEPOSITO_BANCARIO("Depósito bancário");
	
	private String descricao;
	
	private FormaPagamento(String descricao) {
		
		this.descricao = descricao;
		
	}
	
	public String getDescricao() {
		return descricao;
	}
}
