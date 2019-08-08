package br.com.lincolntec.pedidosVenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.lincolntec.pedidosVenda.model.ItemPedido;
import br.com.lincolntec.pedidosVenda.model.Pedido;
import br.com.lincolntec.pedidosVenda.repository.Pedidos;
import br.com.lincolntec.pedidosVenda.util.jpa.Transactional;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public void baixarItensEstoque(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());
		
		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}

	public void retornarItensEstoque(Pedido pedido) {
		
		pedido = pedidos.porId(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()){
			
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
		
	}
	
}