/**
 * 
 */
package br.com.lincolntec.pedidosVenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import br.com.lincolntec.pedidosVenda.model.Pedido;
import br.com.lincolntec.pedidosVenda.repository.Pedidos;
import br.com.lincolntec.pedidosVenda.util.cdi.CDIServiceLocator;

/**
 * @author lincoln
 *
 */

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

	//@Inject
	private Pedidos pedidos;
	
	public PedidoConverter() {
		pedidos = CDIServiceLocator.getBean(Pedidos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pedido retorno = null;
		
		if (value != null && StringUtils.isNotEmpty(value)) {
			@SuppressWarnings("deprecation")
			Long id = new Long(value);
			retorno = pedidos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pedido pedido = (Pedido) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}
		
		return "";
	}

}