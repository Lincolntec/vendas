package br.com.lincolntec.pedidosVenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import br.com.lincolntec.pedidosVenda.model.Cliente;
import br.com.lincolntec.pedidosVenda.repository.Clientes;
import br.com.lincolntec.pedidosVenda.util.cdi.CDIServiceLocator;


@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter {

	//@Inject
	private Clientes clientes;
	
	public ClienteConverter() {
		this.clientes = (Clientes) CDIServiceLocator.getBean(Clientes.class);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;

		if (value != null && StringUtils.isNotEmpty(value)) {
			retorno = this.clientes.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cliente cliente = (Cliente) value;
			return cliente.getId() == null ? null : cliente.getId().toString();
		}
		return "";
	}

}