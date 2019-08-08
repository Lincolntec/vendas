/**
 * 
 */
package br.com.lincolntec.pedidosVenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.lincolntec.pedidosVenda.model.Produto;
import br.com.lincolntec.pedidosVenda.repository.Peididos;
import br.com.lincolntec.pedidosVenda.util.jpa.Transactional;

/**
 * @author lincoln
 *
 */
public class CadastroProdutoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Peididos produtos;
	
	@Transactional
	public Produto salvar(Produto produto){

		Produto produtoExistente = produtos.porSku(produto.getSku());
		if(produtoExistente != null && !produtoExistente.equals(produto)){
			
			throw new NegocioException("Já existe um produto com o SKU informado!");
		}
		return produtos.guardar(produto);
	}

}
