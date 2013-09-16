package br.com.michelon.softimob.tela.view;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.ImageRepository;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PendenciaService;
import br.com.michelon.softimob.modelo.Pendencia;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class PendenciaView extends GenericView<Pendencia>{

	private List<ColumnProperties> atributos;
	private PendenciaService service = new PendenciaService();
	private Table table;
	
	public PendenciaView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Descrição", "descricao"));
		atributos.add(new ColumnProperties("Data de Origem", "dataGeracao"));
		atributos.add(new ColumnProperties("Data de Vencimento", "dataVencimento"));
		atributos.add(new ColumnProperties("Valor", "valor",10));
	}
	
	@Override
	protected void createMenuItens(Menu menu) {
		MenuItem miFinalizar = new MenuItem(menu, SWT.BORDER);
		miFinalizar.setText("Finalizar");
		miFinalizar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Pendencia pendencia = getSelecionado();
					pendencia.finalizarPendencia();
				} catch (Exception e1) {
					log.error("Erro ao finalizar pendencia");
				}
			}
		});
		miFinalizar.setImage(ImageRepository.SOLVED_16.getImage());
	}
	
	@Override
	protected String getTitleView() {
		return "Pendências";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.PENDENCIA_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Pendencia t) {
		return (GenericEditorInput<?>) t.getEditorInput();
	}

	@Override
	protected String getEditorId(Pendencia t) {
		return t.getIdEditor();
	}

	@Override
	protected List<Pendencia> getInput() {
		return service.findPendencias();
	}

	@Override
	@SuppressWarnings("unchecked")
	protected GenericService<Pendencia> getService(Object obj) {
		return (GenericService<Pendencia>) ((Pendencia)obj).getService();
	}
	
	@Override
	protected List<Action> createMoreActions() {
		return null;
	}

	@Override
	protected ColumnViewer createTable(Composite composite) {
		composite.setLayout(new GridLayout(1, false));
		
		TableViewer tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableViewerColumn tvcDataGeracao = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDataDeGerao = tvcDataGeracao.getColumn();
		tblclmnDataDeGerao.setWidth(150);
		tblclmnDataDeGerao.setText("Data de Geração");
		tvcDataGeracao.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				Pendencia p = (Pendencia) element;
				return FormatterHelper.getSimpleDateFormat().format(p.getDataGeracao());
			}
			
			@Override
			public Color getForeground(Object element) {
				return getColorPendencia((Pendencia) element);
			}
		});
		
		TableViewerColumn tvcDataVencimento = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnData = tvcDataVencimento.getColumn();
		tblclmnData.setWidth(150);
		tblclmnData.setText("Data de Vencimento");
		tvcDataVencimento.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				Pendencia p = (Pendencia) element;
				if(p.getDataVencimento() == null)
					return StringUtils.EMPTY;
				return FormatterHelper.getSimpleDateFormat().format(p.getDataVencimento());
			}
			
			@Override
			public Color getForeground(Object element) {
				return getColorPendencia((Pendencia) element);
			}
			
		});
		
		TableViewerColumn tvcValor = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnValor = tvcValor.getColumn();
		tblclmnValor.setWidth(100);
		tblclmnValor.setText("Valor");
		tvcValor.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				Pendencia p = (Pendencia) element;
				return FormatterHelper.getDefaultValueFormatterToMoney().format(p.getValor());
			}
			
			@Override
			public Color getForeground(Object element) {
				return getColorPendencia((Pendencia) element);
			}
		});

		TableViewerColumn tvcDescricao = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDescrio = tvcDescricao.getColumn();
		tblclmnDescrio.setWidth(700);
		tblclmnDescrio.setText("Descrição");
		tvcDescricao.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				Pendencia p = (Pendencia) element;
				return p.getDescricao();
			}
			
			@Override
			public Color getForeground(Object element) {
				return getColorPendencia((Pendencia) element);
			}
		});
		
		tableViewer.addFilter(getFilter());
		
		return tableViewer;
	}
	
	private Color COLOR_A_VENCER = SWTResourceManager.getColor(255, 165, 0);
	private Color COLOR_VENCIDA = SWTResourceManager.getColor(255, 215, 0);
	
	public Color getColorPendencia(Pendencia pendencia) {
		Calendar c = Calendar.getInstance();
		if(pendencia.getDataVencimento() == null)
			return COLOR_A_VENCER;
		if(pendencia.getDataVencimento().compareTo(c.getTime()) < 0)
			return COLOR_VENCIDA;
		else{
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 10);
			if(pendencia.getDataVencimento().compareTo(c.getTime()) > 0)
				return COLOR_A_VENCER;
		}
		
		return ResourceManager.getColor(SWT.COLOR_BLACK);
	}
	
}
