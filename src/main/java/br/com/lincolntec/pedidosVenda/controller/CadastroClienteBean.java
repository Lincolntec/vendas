package br.com.lincolntec.pedidosVenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lincolntec.pedidosVenda.model.Cliente;
import br.com.lincolntec.pedidosVenda.model.Endereco;
import br.com.lincolntec.pedidosVenda.model.TipoPessoa;
import br.com.lincolntec.pedidosVenda.service.ClienteService;
import br.com.lincolntec.pedidosVenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private List<TipoPessoa> tiposPessoa = new ArrayList<>();
	private Endereco endereco = new Endereco();

	@Inject
	private ClienteService clienteService;

	public CadastroClienteBean() {
	}

	public void inicializar() {
		if (cliente == null) {
			cliente = new Cliente();
			cliente.setTipo(TipoPessoa.FISICA);
		}
		tiposPessoa = Arrays.asList(TipoPessoa.values());
	}

	public void salvar() {
		this.cliente.setNome(cliente.getNome().toUpperCase());
		this.cliente.setEmail(cliente.getEmail().toLowerCase());
		this.cliente.setDocumentoReceitaFederal(
				cliente.getDocumentoReceitaFederal().replace(".", "").replace("-", "").replace("/", ""));

		this.cliente = clienteService.salvar(this.cliente);
		FacesUtil.addInfoMessage("Cliente salvo com sucesso");
	}

	public void prepararNovoEndereco() {
		System.out.println("inicio preparar novo endereco");
		this.endereco = new Endereco();
		System.out.println("fim preparar novo endereco");
	}

	public void incluiEndereco() {
		endereco.setCliente(cliente);
		cliente.getEnderecos().add(endereco);
		endereco = new Endereco();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<TipoPessoa> getTiposPessoa() {
		return tiposPessoa;
	}

	public void setTiposPessoa(List<TipoPessoa> tiposPessoa) {
		this.tiposPessoa = tiposPessoa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}