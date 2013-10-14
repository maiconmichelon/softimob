package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.AluguelEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.helper.ContratoHelper;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.service.AluguelService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.tela.dialog.ReajusteDialog;
import br.com.michelon.softimob.tela.editor.AluguelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;

import com.google.common.collect.Lists;

public class AluguelView extends GenericView<Aluguel>{

	private List<ColumnProperties> atributos;
	private AluguelService service = new AluguelService();
	
	public AluguelView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Imóvel", "contrato", 30));
		atributos.add(new ColumnProperties("Locatário", "cliente.nome", 15));
		atributos.add(new ColumnProperties("Corretor", "funcionario.nome", 15));
		atributos.add(new ColumnProperties("Fiador", "fiador.nome",15));
		atributos.add(new ColumnProperties("Valor", "valor", 8, FormatterHelper.getDefaultValueFormatterToMoney()));
		atributos.add(new ColumnProperties("Data", "dataAssinaturaContrato", 10, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Vencimento", "dataVencimento", 10, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Reajuste", "reajuste", 8));
	}
	
	@Override
	protected String getTitleView() {
		return "Locação";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.LOCACAO_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Aluguel t) {
		return new AluguelEditorInput();
	}

	@Override
	protected String getEditorId(Aluguel t) {
		return AluguelEditor.ID;
	}

	@Override
	protected List<Aluguel> getInput() {
		return service.findAll();
	}

	@Override
	protected GenericService<Aluguel> getService(Object obj) {
		return service;
	}
	
	@Override
	protected void createMenuItens(Menu menu) {
		super.createMenuItens(menu);
		
		MenuItem miContrato = new MenuItem(menu, SWT.BORDER);
		miContrato.setText("Gerar contrato");
		miContrato.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Aluguel aluguel = getSelecionado();
				
				if(!aluguel.isOkCheckList()){
					DialogHelper.openWarning("Para gerar o contrato é necessário que todos os itens da checklist, tidos como obrigatório, sejam finalizados.");
					return;
				}
				
				ContratoHelper.gerarContrato(aluguel.getModeloContrato().getArquivo(), aluguel);
			}
		});
		miContrato.setImage(ImageRepository.CONTRATO_16.getImage());
		
		MenuItem miReajuste = new MenuItem(menu, SWT.BORDER);
		miReajuste.setText("Reajustar");
		miReajuste.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Aluguel aluguel = getSelecionado();
				
				if(!aluguel.isOkCheckList()){
					DialogHelper.openWarning("Para reajustar é necessário que todos os itens da checklist, tidos como obrigatório, sejam finalizados.");
					return;
				} else if(aluguel.getReajuste() == null){
					DialogHelper.openWarning("Para reajustar é necessário colocar o indice do aluguel.");
					return;
				}
				
				new ReajusteDialog(aluguel.getParcelas(), aluguel.getReajuste()).open();
			}
		});
		miContrato.setImage(ImageRepository.CONTRATO_16.getImage());
		
	}
	
}
