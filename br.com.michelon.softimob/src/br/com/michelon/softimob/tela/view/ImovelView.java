package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.ImovelService;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.tela.editor.ImovelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.imovelXViewer.ImovelXViewer;

import com.google.common.collect.Lists;

public class ImovelView extends GenericView<Imovel>{

	public static final String ID = "br.com.michelon.softimob.tela.view.ImovelView";
	
	private List<ColumnProperties> atributos;
	private ImovelService service = new ImovelService();

	private List<Imovel> input;
	
	public ImovelView() {
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Código", "id"));
//		atributos.add(new ColumnProperties("Endereço", "endereco"));
		
		input = service.findAll();
	}
	
	@Override
	protected String getTitleView() {
		return "Imóveis";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.IMOVEL_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Imovel t) {
		return new ImovelEditorInput();
	}

	@Override
	protected String getEditorId(Imovel t) {
		return ImovelEditor.ID;
	}

	@Override
	protected List<Imovel> getInput() {
		return input;
	}

	@Override
	protected void createMenuItens(Menu menu) {
		super.createMenuItens(menu);
		
		MenuItem miFotos = new MenuItem(menu, SWT.BORDER);
		miFotos.setText("Visualizar Fotos");
		miFotos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Imovel imovel = getSelecionado();
				try {
					PhotosView showView = (PhotosView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(PhotosView.ID);
					showView.setFotos(imovel.getFotos());
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		MenuItem miMaps = new MenuItem(menu, SWT.BORDER);
		miMaps.setText("Visualizar imóvel em mapa");
		miMaps.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ImovelMapView showView = (ImovelMapView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ImovelMapView.ID);
					if(getSelecionado().getEndereco() != null)
						showView.setEnderecoPadrao(getSelecionado().getEndereco());
				} catch (PartInitException e1) {
					log.error("Erro ao abrir imóvel na view de Mapa.", e1);
				}
			}
		});
	}
	
	@Override
	protected ColumnViewer criarTabela(Composite composite) {
		Composite cpTable = new Composite(composite, SWT.NONE);
		cpTable.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		XViewer imovelXViewer = new ImovelXViewer(cpTable, SWT.BORDER | SWT.FULL_SELECTION);
		
		return imovelXViewer;
	}
	
	@Override
	protected GenericService<Imovel> getService(Object obj) {
		return service;
	}

	public void setInput(List<Imovel> imoveis) {
		input = imoveis;
		atualizar();
	}	
	
}
