package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.AluguelEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.VendaEditorInput;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.service.ComissaoService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.Comissao;
import br.com.michelon.softimob.tela.editor.AluguelEditor;
import br.com.michelon.softimob.tela.editor.VendaEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;

import com.google.common.collect.Lists;

public class ComissaoView extends GenericView<Comissao>{

	private List<ColumnProperties> atributos;
	private ComissaoService service = new ComissaoService();
	
	public ComissaoView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Nome", "comissionado.nome", 20));
		atributos.add(new ColumnProperties("Venda/Aluguel", "vendaAluguel", 60));
		atributos.add(new ColumnProperties("Data de Vencimento", "dataVencimento", 10, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Valor", "valor", 10, FormatterHelper.getDefaultValueFormatterToMoney()));
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
	@Override
	protected String getTitleView() {
		return "Comissão";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.COMISSAO_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Comissao t) {
		return t.getVendaAluguel() instanceof Aluguel ? new AluguelEditorInput() : new VendaEditorInput();
	}

	@Override
	protected Object getModelOfEditorInput(Comissao element) {
		return element.getVendaAluguel();
	}
	
	@Override
	protected String getEditorId(Comissao t) {
		return t.getVendaAluguel() instanceof Aluguel ? AluguelEditor.ID : VendaEditor.ID;
	}

	@Override
	protected List<Comissao> getInput() {
		return service.findAll();
	}

	@Override
	protected GenericService<Comissao> getService(Object obj) {
		return service;
	}
	
}
