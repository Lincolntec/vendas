package br.com.lincolntec.pedidosVenda.controller;

import br.com.lincolntec.pedidosVenda.model.Pedido;

public class PedidoAlteradoEvent {

	private Pedido pedido;
	
	public PedidoAlteradoEvent(Pedido pedido) {

		pedido = this.pedido;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
}
