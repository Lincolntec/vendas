/**
 * 
 */
package br.com.lincolntec.pedidosVenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import br.com.lincolntec.pedidosVenda.model.Produto;
import br.com.lincolntec.pedidosVenda.repository.Peididos;
import br.com.lincolntec.pedidosVenda.util.cdi.CDIServiceLocator;

/**
 * @author lincoln
 *
 */

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	//@Inject
	private Peididos produtos;
	
	public ProdutoConverter() {
		produtos = CDIServiceLocator.getBean(Peididos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;
		
		if (value != null && StringUtils.isNotEmpty(value)) {
			@SuppressWarnings("deprecation")
			Long id = new Long(value);
			retorno = produtos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Produto produto = (Produto) value;
			return produto.getId() == null ? null : produto.getId().toString();
		}
		
		return "";
	}

}