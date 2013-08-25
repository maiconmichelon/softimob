package br.com.michelon.softimob.modelo;

import java.math.BigDecimal;
import java.util.Date;

import org.eclipse.ui.IEditorInput;

import br.com.michelon.softimob.aplicacao.service.GenericService;

public interface Pendencia {

	public static String CONTA_PAGAR = "Conta a pagar";
	public static String CONTA_RECEBER = "Conta a receber";
	public static String CONTRATO = "Contrato";
	public static String REFORMA = "Chamado de reforma";
	public static String COMISSAO = "Comissão";

	Date getDataGeracao();
	Date getDataVencimento();
	Date getDataFechamento();
	
	String getDescricao();
	
	String getIdEditor();
	IEditorInput getEditorInput();
	
	BigDecimal getValor();
	
	GenericService<?> getService();
	
	void finalizarPendencia() throws Exception;
	
}
