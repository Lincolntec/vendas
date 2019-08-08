/**
 * 
 */
package br.com.lincolntec.pedidosVenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import br.com.lincolntec.pedidosVenda.filter.PedidoFilter;
import br.com.lincolntec.pedidosVenda.model.Pedido;
import br.com.lincolntec.pedidosVenda.model.StatusPedido;
import br.com.lincolntec.pedidosVenda.repository.Pedidos;

/**
 * @author lincoln
 *
 */
@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	private List<Pedido> pedidosFiltrados;
	
	private PedidoFilter filtro;
	
	public PesquisaPedidosBean() {
		
		filtro = new PedidoFilter();
		pedidosFiltrados = new ArrayList<>();
		
	}
	
	public StatusPedido[] getStatues(){
		
		return StatusPedido.values();
		
	}
	
	public void pesquisar(){
		
		pedidosFiltrados = pedidos.filtrados(filtro);
		
	}

	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}
	
	public PedidoFilter getFiltro() {
		return filtro;
	}
	
	public void posProcessarXLS(Object documento){
		
		HSSFWorkbook planilha = (HSSFWorkbook) documento;
		HSSFSheet folha = planilha.getSheetAt(0);
		HSSFRow cabecalho = folha.getRow(0);
		HSSFCellStyle estiloCelular = planilha.createCellStyle();
		
		Font fonteCabecalho = planilha.createFont();
		
		fonteCabecalho.setColor(IndexedColors.WHITE.getIndex());
		fonteCabecalho.setBold(true);
		fonteCabecalho.setFontHeightInPoints((short)16);
		
		estiloCelular.setFont(fonteCabecalho);
		
		estiloCelular.setFillForegroundColor(IndexedColors.BLACK.index);
		estiloCelular.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		for(int i = 0; i< cabecalho.getPhysicalNumberOfCells(); i++){
			cabecalho.getCell(i).setCellStyle(estiloCelular);
		}
		
		
		
	}
	
}
