/**
 * 
 */
package br.com.lincolntec.pedidosVenda.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.lincolntec.pedidosVenda.model.Categoria;
import br.com.lincolntec.pedidosVenda.repository.Categorias;
import br.com.lincolntec.pedidosVenda.util.cdi.CDIServiceLocator;

/**
 * @author lincoln
 *
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter, Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//@Inject
		private Categorias categorias;
	
 
	public CategoriaConverter() {

		categorias = CDIServiceLocator.getBean(Categorias.class);
	}
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		
		if (value != null) {
			@SuppressWarnings("deprecation")
			Long id = new Long(value);
			retorno = categorias.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Categoria) value).getId().toString();
		}
		
		return "";
	}

}