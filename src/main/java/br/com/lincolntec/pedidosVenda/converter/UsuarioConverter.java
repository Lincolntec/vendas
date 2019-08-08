package br.com.lincolntec.pedidosVenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.lincolntec.pedidosVenda.model.Usuario;
import br.com.lincolntec.pedidosVenda.repository.Usuarios;
import br.com.lincolntec.pedidosVenda.util.cdi.CDIServiceLocator;


@FacesConverter(forClass=Usuario.class)
public class UsuarioConverter implements Converter {

	//@Inject
	private Usuarios usuarios;
	
	public UsuarioConverter() {
		this.usuarios = (Usuarios) CDIServiceLocator.getBean(Usuarios.class);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;

		if (value != null) {
			retorno = this.usuarios.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Usuario) value).getId().toString();
		}
		return "";
	}

}