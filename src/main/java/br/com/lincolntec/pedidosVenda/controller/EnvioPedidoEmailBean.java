package br.com.lincolntec.pedidosVenda.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

import com.outjected.email.api.MailMessage;

import br.com.lincolntec.pedidosVenda.model.Pedido;
import br.com.lincolntec.pedidosVenda.util.jsf.FacesUtil;
import br.com.lincolntec.pedidosVenda.util.mail.Mailer;
import br.com.lincolntec.pedidosVenda.util.mail.VelocityTemplateUTF8;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Mailer mailer;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	public void enviarPedido(){
		
		MailMessage message = mailer.novaMensagem();
		
		message.to(pedido.getCliente().getEmail())
		.subject("Pedido " + pedido.getId())
			.bodyHtml(new VelocityTemplateUTF8(getClass().getResourceAsStream("/emails/pedido.template")))
				.put("pedido", this.pedido)
				.put("numberTool", new NumberTool())
				.put("locale", new Locale("pt", "BR"))
				.send();
		
		FacesUtil.addInfoMessage("Pedido enviado por email com sucesso!!");
		
	}

}
