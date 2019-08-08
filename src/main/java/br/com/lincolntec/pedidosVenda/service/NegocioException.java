/**
 * 
 */
package br.com.lincolntec.pedidosVenda.service;

/**
 * @author lincoln
 *
 */
public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NegocioException(String msg) {
		super(msg);
	}
	
}