package br.com.lincolntec.pedidosVenda.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.com.lincolntec.pedidosVenda.model.Cliente;
import br.com.lincolntec.pedidosVenda.repository.Clientes;
import br.com.lincolntec.pedidosVenda.util.jpa.Transactional;

public class ClienteService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;

	@Transactional
	public void excluirCliente(Cliente cliente) {

		try {
			cliente = clientes.porId(cliente.getId());
		//	manager.remove(cliente);
		//	manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O Cliente não pode ser excluído");
		}

	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
	
		//Cliente clienteExistente = new Cliente();
		 
		//if(!StringUtils.isNotEmpty(cliente.getId().toString())){
			
		//	clienteExistente = clientes.porId(cliente.getId());
			
		//}
		
		
	//	if(clienteExistente != null && clienteExistente.equals(cliente) ){
		//	
	//		throw new NegocioException("Cliente já cadastrado.");
		//}
		
		return clientes.guardar(cliente);
	}

	

	

}
