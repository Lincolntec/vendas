/**
 * 
 */
package br.com.lincolntec.pedidosVenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import br.com.lincolntec.pedidosVenda.model.Cliente;
import br.com.lincolntec.pedidosVenda.model.EnderecoEntrega;
import br.com.lincolntec.pedidosVenda.model.FormaPagamento;
import br.com.lincolntec.pedidosVenda.model.ItemPedido;
import br.com.lincolntec.pedidosVenda.model.Pedido;
import br.com.lincolntec.pedidosVenda.model.Produto;
import br.com.lincolntec.pedidosVenda.model.Usuario;
import br.com.lincolntec.pedidosVenda.repository.Clientes;
import br.com.lincolntec.pedidosVenda.repository.Produtos;
import br.com.lincolntec.pedidosVenda.repository.Usuarios;
import br.com.lincolntec.pedidosVenda.service.CadastroPedidoService;
import br.com.lincolntec.pedidosVenda.util.jsf.FacesUtil;
import br.com.lincolntec.pedidosVenda.validation.SKU;

/**
 * @author lincoln
 *
 */
@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private Produtos produtos;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	private String sku;
	
	@Produces
	@PedidoEdicao
	private Pedido pedido;
	
	private List<Usuario> vendedores;
	
	private Produto produtoLinhaEditavel;
	
	public CadastroPedidoBean() {
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			this.vendedores = this.usuarios.vendedores();
			
			if(pedido == null){
				
				pedido = new Pedido();
				pedido.setEnderecoEntrega(new EnderecoEntrega());
			}
			this.pedido.adicionarItemVazio();
			
			this.recalcularPedido();
		}
	}
	
	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public void pedidoAlterado(@Observes PedidoAlteradoEvent event) {
		this.pedido = event.getPedido();
	}
	
	public void salvar() {
		this.pedido.removerItemVazio();
		
		try {
			this.pedido = this.cadastroPedidoService.salvar(this.pedido);
		
			FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
		} finally {
			this.pedido.adicionarItemVazio();
		}
	}
	
	public void recalcularPedido() {
		if (this.pedido != null) {
			this.pedido.recalcularValorTotal();
		}
	}
	
	public void carregarProdutoPorSku() {
		if (StringUtils.isNotEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtos.porSku(this.sku);
			this.carregarProdutoLinhaEditavel();
		}
	}
	
	public void carregarProdutoLinhaEditavel() {
		ItemPedido item = this.pedido.getItens().get(0);
		
		if (this.produtoLinhaEditavel != null) {
			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
			} else {
				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());
				
				this.pedido.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;
				
				this.pedido.recalcularValorTotal();
			}
		}
	}
	
	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;
		
		for (ItemPedido item : this.getPedido().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}

	public List<Produto> completarProduto(String nome) {
		return this.produtos.porNome(nome);
	}
	
	public void atualizarQuantidade(ItemPedido item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getPedido().getItens().remove(linha);
			}
		}
		
		this.pedido.recalcularValorTotal();
	}
	
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}
	
	public List<Cliente> completarCliente(String nome) {
		return this.clientes.porNome(nome);
	}

	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}
	
	public boolean isEditando() {
		
		if(this.pedido == null){
			
			pedido = new Pedido();
			
			return this.pedido.getId() != null;
		}
		return this.pedido.getId() != null;
		}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	@SKU
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}