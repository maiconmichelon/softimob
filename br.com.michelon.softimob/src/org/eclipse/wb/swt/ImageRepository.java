package org.eclipse.wb.swt;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image; 

import br.com.michelon.softimob.aplicacao.main.Activator;

public enum ImageRepository{

	ADD_16("icons/add/add16.png"),
	CLIENTE_16("icons/cliente/cliente16.png"),
	CLIENTE_32("icons/cliente/cliente32.png"),
	FUNCIONARIO_16("icons/funcionario/funcionario16.png"),
	FUNCIONARIO_32("icons/funcionario/funcionario32.png"),
	DEPARTAMENTO_16("icons/departamento/departamento16.png"),
	DEPARTAMENTO_32("icons/departamento/departamento32.png"),
	FEEDBACK_16("icons/feedback/feedback16.png"),
	FEEDBACK_32("icons/feedback/feedback32.png"),
	IMOVEL_16("icons/casa/casa16.png"),
	IMOVEL_32("icons/casa/casa32.png"),
	TIPO_IMOVEL_16("icons/casa/casa16.png"),
	TIPO_IMOVEL_32("icons/casa/casa32.png"),
	CHAVE_16("icons/chave/key16.png"),
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
	SAVE_16("icons/save/save16.png"),
	SAVE_32("icons/save/save32.png"),
	SEARCH_16("icons/search/search16.png"),
	SEARCH_32("icons/search/search16.png"),
	VENDA_16("icons/venda/venda16.png"),
	VENDA_32("icons/venda/venda32.png"), 
	DELETE_16("icons/delete/delete16.png"), 
	REFRESH_16("icons/refresh/refresh16.png"),
	REMOVE_16("icons/delete/remove16.png"), 
	REMOVE_32("icons/delete/delete32.png"), 
	LOCACAO_32("icons/aluguel/aluguel32.png"), 
	LOCACAO_16("icons/aluguel/aluguel16.png"), 
	CONTA_32("icons/contaPagar/contaPagar32.png"), 
	MOVIMENTACAO_CONTABIL_32("icons/movimentacao/movimentacao32.png"),
	CONTRATO_16("icons/contrato/contrato16.png"),
	CONTRATO_32("icons/contrato/contrato32.png"), 
	PROPOSTA_16("icons/proposta/proposta16.png"), 
	PROPOSTA_32("icons/proposta/proposta32.png"), 
	ENDERECO16("icons/endereco/endereco16.png"),
	ENDERECO32("icons/endereco/endereco32.png"),
	CHECKED("icons/check/checked16.png"),
	UNCHECKED("icons/check/unchecked16.png"),
	COMODO16("icons/comodo/door16.png"),
	COMODO32("icons/comodo/door32.png"),
	PENDENCIA_16("icons/pendencia/pendencia32.png"),
	PENDENCIA_32("icons/pendencia/pendencia32.png"),
	INDICE_16("icons/indice/indice16.png"),
	INDICE_32("icons/indice/indice32.png"),
	ORIGEM_CONTA_16("icons/origemConta/origemConta16.png"),
	ORIGEM_CONTA_32("icons/origemConta/origemConta32.png"),
	PLANO_CONTA_16("icons/planoConta/planoConta16.png"),
	PLANO_CONTA_32("icons/planoConta/planoConta32.png"),
	RESERVA16("icons/reserva/reserva16.png"),
	RESERVA32("icons/reserva/reserva32.png"),
	VISTORIA_16("icons/vistoria/vistoria16.png"),
	VISTORIA_32("icons/vistoria/vistoria32.png"),
	IMOBILIARIA_16("icons/imobiliaria/imobiliaria32.png"),
	IMOBILIARIA_32("icons/imobiliaria/imobiliaria32.png"),
	CHECKLIST_16("icons/checkList/checkList16.png"),
	CHECKLIST_32("icons/checkList/checkList32.png"), 
	ALTERAR16("icons/alterar/alteracao16.png"), 
	BOLETO_16("icons/boleto/boleto16.png"), 
	INFO_16("icons/typeMessage/info16.png"), 
	INFO_32("icons/typeMessage/info32.png"), 
	WARN_16("icons/typeMessage/warn16.png"), 
	WARN_32("icons/typeMessage/warn32.png"), 
	ERROR_16("icons/typeMessage/error16.png"),
	ERROR_32("icons/typeMessage/error32.png"),
	SUCCESS_16("icons/typeMessage/success16.png"),
	SUCCESS_32("icons/typeMessage/success32.png"), 
	USER_32("icons/user/user32.png"),
	USER_16("icons/user/user16.png"),
	SOLVED_16("icons/solved/solved16.png"),
	FOTO_16("icons/foto/foto16.png"),
	MAP_16("icons/map/map16.png"),
	DESEMPENHO_16("icons/desempenho/desempenho16.png"),
	IMPORTACAO_ARQUIVORETORNO("icons/importacao/import16.png")
	;
	
	private String caminho;
	private final String symbolicName;
	
	ImageRepository(String symbolicName, String caminho) {
		this.caminho = caminho;
		this.symbolicName = symbolicName;
	}
	
	ImageRepository(String caminho) {
		this.caminho = caminho;
		this.symbolicName = Activator.PLUGIN_ID;
	}
	
	public String getCaminho() {
		return caminho;
	}
	
	public String getSymbolicName() {
		return symbolicName;
	}
	
	@Override
	public String toString() {
		return caminho;
	}
	
	public ImageDescriptor getImageDescriptor(){
		return ResourceManager.getPluginImageDescriptor(symbolicName, caminho);
	}
	
	public Image getImage(){
		return ResourceManager.getPluginImage(symbolicName, caminho);
	}
	
}
