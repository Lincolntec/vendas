package br.com.lincolntec.pedidosVenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lincolntec.pedidosVenda.filter.ClienteFilter;
import br.com.lincolntec.pedidosVenda.model.Cliente;
import br.com.lincolntec.pedidosVenda.repository.ClienteRepository;
import br.com.lincolntec.pedidosVenda.service.ClienteService;
import br.com.lincolntec.pedidosVenda.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ClienteFilter filtro;
	private List<Cliente> clientesFiltrados = new ArrayList<>();
	private Cliente clienteExcluir;
	
	@Inject
	ClienteService clienteService;
	
	@Inject
	ClienteRepository clienteRepositorio;
	
	public PesquisaClientesBean(){
		
		filtro = new ClienteFilter();
		clientesFiltrados = new ArrayList<>();
		
	}
	
	public void pesquisar(){		
		System.out.println("pesquisando filtro");
		clientesFiltrados = clienteRepositorio.filtrados(filtro);				
	}
	
	
	public void excluirCliente(){
		
		clienteService.excluirCliente(clienteExcluir);
		clientesFiltrados.remove(clienteExcluir);
		FacesUtil.addInfoMessage("Cliente "+ clienteExcluir.getNome() +" excluído com sucesso.");
		
	}
	
	public ClienteFilter getFiltro() {
		return filtro;
	}
	public void setFiltro(ClienteFilter filtro) {
		this.filtro = filtro;
	}
	
	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}
	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}
	
	public Cliente getClienteExcluir() {
		return clienteExcluir;
	}
	public void setClienteExcluir(Cliente clienteExcluir) {
		this.clienteExcluir = clienteExcluir;
	}
}