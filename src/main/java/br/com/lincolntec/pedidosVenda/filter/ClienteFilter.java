package br.com.lincolntec.pedidosVenda.filter;

import java.io.Serializable;

public class ClienteFilter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String sku;
	
	private String nome;
	
	private String documentoFederal;

	

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumentoFederal() {
		return documentoFederal;
	}

	public void setDocumentoFederal(String documentoFederal) {
		this.documentoFederal = documentoFederal;
	}
	
}
