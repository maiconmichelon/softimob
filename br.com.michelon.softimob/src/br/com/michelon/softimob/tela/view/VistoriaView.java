package br.com.michelon.softimob.tela.view;

import java.io.IOException;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.AluguelEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.VendaEditorInput;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FileHelper;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.VistoriaService;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.Arquivo;
import br.com.michelon.softimob.modelo.Vistoria;
import br.com.michelon.softimob.tela.editor.AluguelEditor;
import br.com.michelon.softimob.tela.editor.VendaEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;

import com.google.common.collect.Lists;

public class VistoriaView extends GenericView<Vistoria>{

	private List<ColumnProperties> atributos;
	private VistoriaService service = new VistoriaService();
	
	public VistoriaView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Data", "data", 10, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Funcionário", "funcionario.nome", 20));
		atributos.add(new ColumnProperties("Venda ou Locação", "vendaAluguel", 60));
		atributos.add(new ColumnProperties("Observações", "observacoes", 60));		
	}
	
	@Override
	protected String getTitleView() {
		return "Vistorias";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.VISTORIA_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Vistoria t) {
		return t.getVendaAluguel() instanceof Aluguel ? new AluguelEditorInput() : new VendaEditorInput();
	}

	@Override
	protected Object getModelOfEditorInput(Vistoria element) {
		return element.getVendaAluguel();
	}
	
	@Override
	protected String getEditorId(Vistoria t) {
		return t.getVendaAluguel() instanceof Aluguel ? AluguelEditor.ID : VendaEditor.ID;
	}

	@Override
	protected List<Vistoria> getInput() {
		return service.findAll();
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
	@Override
	protected GenericService<Vistoria> getService(Object obj) {
		return service;
	}
	
	@Override
	protected void createMenuItens(Menu menu) {
		super.createMenuItens(menu);
		
		MenuItem miFotos = new MenuItem(menu, SWT.BORDER);
		miFotos.setText("Visualizar Fotos");
		miFotos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					List<Arquivo> arquivos = getSelecionado().getFotos();
					if(arquivos.isEmpty()){
						DialogHelper.openWarning("Esta vistoria não possui nenhuma foto.");
						return;
					}
					
					FileHelper.openFile(FileHelper.criarDiretorioArquivos(arquivos), arquivos.get(0).getNome());
				} catch (IOException e2) {
					log.error("Erro ao abrir fotos.", e2);
					DialogHelper.openErrorMultiStatus("Erro ao abrir as fotos da vistoria.\n", e2.getMessage());
				}
			}
		});
	}
}
