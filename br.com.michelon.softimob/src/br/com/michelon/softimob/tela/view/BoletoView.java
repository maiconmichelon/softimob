package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.service.BoletoSoftimobService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.BoletoSoftimob;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;

import com.google.common.collect.Lists;

public class BoletoView extends GenericView<BoletoSoftimob>{

	private BoletoSoftimobService boletoService;
	private List<ColumnProperties> atributos;

	public BoletoView() {
		super(false);
		
		atributos = Lists.newLinkedList();
		
		atributos.add(new ColumnProperties("Nosso Número", "nossoNumeroFormatado"));
		atributos.add(new ColumnProperties("Data", "dataDocumento", 15, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Data de Vencimento", "dataVencimento", 15, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Valor", "valor", 15, FormatterHelper.getDefaultValueFormatterToMoney()));
		atributos.add(new ColumnProperties("Juros", "mora", 15, FormatterHelper.getDefaultValueFormatterToMoney()));
		atributos.add(new ColumnProperties("Desconto", "desconto", 15, FormatterHelper.getDefaultValueFormatterToMoney()));
		atributos.add(new ColumnProperties("Cliente", "cliente", 40));
		
		boletoService = new BoletoSoftimobService();
	}

	@Override
	protected String getTitleView() {
		return "Boleto";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.BOLETO_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(BoletoSoftimob t) {
		return null;
	}

	@Override
	protected String getEditorId(BoletoSoftimob t) {
		return null;
	}

	@Override
	protected List<BoletoSoftimob> getInput() {
		return boletoService.findAll();
	}

	@Override
	protected GenericService<BoletoSoftimob> getService(Object obj) {
		return boletoService;
	}
	
	@Override
	protected List<Action> createMoreActions() {
		return Lists.newArrayList();
	}
	
	@Override
	protected void createMenuItens(Menu menu) {
		createMenuItemRemover(menu);
	}
	
	@Override
	protected void alterar(BoletoSoftimob element) {}

}
