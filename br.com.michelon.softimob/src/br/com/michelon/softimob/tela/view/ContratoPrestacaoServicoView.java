package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.service.ContratoPrestacaoServicoService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.tela.editor.ImovelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.NullStringValueFormatter;

import com.google.common.collect.Lists;

import de.ralfebert.rcputils.properties.IValueFormatter;

public class ContratoPrestacaoServicoView extends GenericView<ContratoPrestacaoServico>{

	private List<ColumnProperties> atributos;
	private ContratoPrestacaoServicoService service = new ContratoPrestacaoServicoService();
	
	public ContratoPrestacaoServicoView(){
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Imóvel", "imovel", 20));
		atributos.add(new ColumnProperties("Data", "dataInicio", 8, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Data de Vencimento", "dataVencimento", 8, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Valor do Imóvel", "valorImovel", 8, FormatterHelper.getDefaultValueFormatterToMoney()));
		atributos.add(new ColumnProperties("Funcionário", "funcionario.nome", 8, new NullStringValueFormatter()));
		atributos.add(new ColumnProperties("Tipo", "tipo", 8));
		atributos.add(new ColumnProperties("Divulgar", "divulgar", 4, new IValueFormatter<Boolean, String>() {

			private String SIM = "Sim";
			private String NAO = "Não";
			
			@Override
			public String format(Boolean arg0) {
				return arg0 ? SIM : NAO;
			}

			@Override
			public Boolean parse(String arg0) {
				return arg0.equals(SIM);
			}
		}));
	}
	
	@Override
	protected String getTitleView() {
		return "Contratos de prestação de serviço";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.CONTRATO_16.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(ContratoPrestacaoServico t) {
		return new ImovelEditorInput();
	}

	@Override
	protected Object getModelOfEditorInput(ContratoPrestacaoServico element) {
		return element.getImovel();
	}
	
	@Override
	protected String getEditorId(ContratoPrestacaoServico t) {
		return ImovelEditor.ID;
	}

	@Override
	protected List<ContratoPrestacaoServico> getInput() {
		return service.findAll();
	}
	
	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
	@Override
	protected GenericService<ContratoPrestacaoServico> getService(Object obj) {
		return service;
	}
	
}
