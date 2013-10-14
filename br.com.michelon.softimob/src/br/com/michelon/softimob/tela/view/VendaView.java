package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.VendaEditorInput;
import br.com.michelon.softimob.aplicacao.helper.ContratoHelper;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.VendaService;
import br.com.michelon.softimob.modelo.Venda;
import br.com.michelon.softimob.tela.editor.VendaEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;

import com.google.common.collect.Lists;

public class VendaView extends GenericView<Venda>{

	private List<ColumnProperties> atributos;
	private VendaService service = new VendaService();
	
	public VendaView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Imóvel", "contrato.imovel", 30));
		atributos.add(new ColumnProperties("Data", "dataAssinaturaContrato", 10, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Cliente", "cliente.nome", 15));
		atributos.add(new ColumnProperties("Vendedor", "funcionario.nome", 15));
		atributos.add(new ColumnProperties("Valor", "valor", 10, FormatterHelper.getDefaultValueFormatterToMoney()));
	}
	
	@Override
	protected String getTitleView() {
		return "Vendas";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.VENDA_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Venda t) {
		return new VendaEditorInput();
	}

	@Override
	protected String getEditorId(Venda t) {
		return VendaEditor.ID;
	}

	@Override
	protected List<Venda> getInput() {
		return service.findAll();
	}

	@Override
	protected GenericService<Venda> getService(Object obj) {
		return service;
	}
	
	@Override
	protected void createMenuItens(Menu menu) {
		super.createMenuItens(menu);
		
		MenuItem miGerarContratos = new MenuItem(menu, SWT.BORDER);
		miGerarContratos.setText("Gerar contrato");
		miGerarContratos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Venda venda = getSelecionado();
				
				if(!venda.isOkCheckList()){
					DialogHelper.openWarning("Para gerar o contrato é necessário que todos os itens da checklist, tidos como obrigatório, sejam finalizados.");
					return;
				}
				
				ContratoHelper.gerarContrato(venda.getModeloContrato().getArquivo(), venda);
			}
		});
		miGerarContratos.setImage(ImageRepository.CONTRATO_16.getImage());
	}
	
}
