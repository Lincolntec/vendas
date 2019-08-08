/**
 * 
 */
package br.com.lincolntec.pedidosVenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.com.lincolntec.pedidosVenda.model.Categoria;
import br.com.lincolntec.pedidosVenda.model.Produto;
import br.com.lincolntec.pedidosVenda.repository.Categorias;
import br.com.lincolntec.pedidosVenda.service.CadastroProdutoService;
import br.com.lincolntec.pedidosVenda.util.jsf.FacesUtil;

/**
 * @author lincoln
 *
 */
@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	private Produto produto;
	private Categoria categoriaPai;
	
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;
	
	public CadastroProdutoBean() {
		
		limpar();
		
	}
	
	public void inicializar() {
		
		if (FacesUtil.isNotPostBack()) {
			categoriasRaizes = categorias.raizes();
			
			if (this.categoriaPai != null) {
				carregarSubcategorias();
			}
		}
	}
	
	public void carregarSubcategorias() {
		subcategorias = categorias.subcategoriasDeCategoria(this.categoriaPai);
	}
	
	private void limpar() {
		produto = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
	}
	
	public void salvar() {
		
		System.out.println("Categoria" + categoriaPai.getDescricao());
		System.out.println("Categoria Filho:" + produto.getCategoria().getDescricao());
		
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();
		
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}

	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
		
		if (this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}
	
	public boolean isEditando() {
		
		if(produto == null){
			
			limpar();
			
		}
		
		 return this.produto.getId() != null;
	}

}