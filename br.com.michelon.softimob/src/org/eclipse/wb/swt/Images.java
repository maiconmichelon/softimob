package org.eclipse.wb.swt;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image; 

import br.com.michelon.softimob.aplicacao.main.Activator;

public enum Images{

	ADD_16("icons/add/add16.png"),
	CLIENTE_16("icons/cliente/cliente16.png"),
	CLIENTE_32("icons/cliente/cliente32.png"),
	FUNCIONARIO_16("icons/funcionario/funcionario16.png"),
	FUNCIONARIO_32("icons/funcionario/funcionario32.png"),
	DEPARTAMENTO_16("icons/departamento/departamento16.png"),
	DEPARTAMENTO_32("icons/departamento/departamento32.png"),
	FEEDBACK_16("icons/feedback/feedback16.png"),
	FEEDBACK_32("icons/feedback/feedback32.png"),
	FEEDBACK_32_2("icons/feedback/feedback32_2.png"),
	IMOVEL_16("icons/casa/casa16.png"),
	IMOVEL_32("icons/casa/casa32.png"),
	TIPO_IMOVEL_16("icons/imovel/imovel16.png"),
	TIPO_IMOVEL_32("icons/imovel/imovel32.png"),
	CHAVE_16("icons/chave/key32.png"),
	CHAVE_32("icons/chave/key32.png"),
	COMISSAO_16("icons/comissao/comissao16.png"),
	COMISSAO_32("icons/comissao/comissao32.png"),
	FINALIZAR_16("icons/finalizar/finalizar16.png"),
	FINALIZAR_32("icons/finalizar/finalizar32.png"),
	NOVO_16("icons/novo/novo16.png"),
	NOVO_32("icons/novo/novo32.png"),
	PLACA_16("icons/placa/placa16.png"),
	PLACA_32("icons/placa/placa32.png"),
	REFORMA_16("icons/reforma/reforma16.png"),
	REFORMA_32("icons/reforma/reforma32.png"),
	SAVE_16("icons/save16/save16.png"),
	SAVE_32("icons/save32/save32.png"),
	SEARCH_16("icons/search/search16.png"),
	SEARCH_32("icons/search/search16.png"),
	VENDA_16("icons/venda/venda16.png"),
	VENDA_32("icons/venda/venda32.png"), 
	REMOVE_16("icons/delete/delete16.png"), 
	REFRESH_16("icons/refresh/refresh16.png"),
	REMOVE_32("icons/delete/delete32.png"), 
	ALUGUEL_32("icons/aluguel/aluguel32.png"), 
	PLANOCONTA_32("icons/planoConta/planoConta32.png"), 
	CONTA_32("icons/contaPagar/contaPagar32.png"), 
	MOVIMENTACAO_CONTABIL_32("icons/movimentacao/movimentacao32.png"), 
	CONTRATO_32("icons/contrato/contrato32.png"), 
	PROPOSTA_32("icons/proposta/proposta32.png"), 
	ENDERECO("icons/endereco/endereco32.png"),
	CHECKED("icons/check/checked16.png"),
	UNCHECKED("icons/check/unchecked16.png"),
	COMODO16("icons/comodo/door16.png"),
	COMODO32("icons/comodo/door32.png"),
	PENDENCIA_16("icons/pendencia/pendencia32.png"),
	PENDENCIA_32("icons/pendencia/pendencia32.png"),
	INDICE_16("icons/indice/indice16.png"),
	INDICE_32("icons/indice/indice32.png")
	;
	
	private String caminho;

	Images(String caminho) {
		this.caminho = caminho;
	}
	
	public String getCaminho() {
		return caminho;
	}
	
	@Override
	public String toString() {
		return caminho;
	}
	
	public ImageDescriptor getImageDescriptor(){
		return ResourceManager.getPluginImageDescriptor(Activator.PLUGIN_ID, getCaminho());
	}
	
	public Image getImage(){
		return ResourceManager.getPluginImage(Activator.PLUGIN_ID, getCaminho());
	}
	
}
