package br.com.michelon.softimob.tela.view;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.ImageRepository;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.filter.PendenciaFilter;
import br.com.michelon.softimob.aplicacao.helper.DateHelper;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PendenciaService;
import br.com.michelon.softimob.modelo.Pendencia;
import br.com.michelon.softimob.modelo.Pendencia.TipoPendencia;
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

					if(pendencia.confirmarFinalizarPendencia() && !DialogHelper.openConfirmation("Deseja finalizar a pendência ?"))
						return;
					
					pendencia.finalizarPendencia();
					
					atualizar();
				} catch (Exception e1) {
					log.error("Erro ao finalizar pendencia");
				}
			}
		});
		miFinalizar.setImage(ImageRepository.SOLVED_16.getImage());
	}
	
	@Override
	public void createComponentsCpTop(Composite composite_4, FormToolkit formToolkit) {
		
		Label lblSituao = formToolkit.createLabel(composite_4, "Situação", SWT.NONE);
		lblSituao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		final ComboViewer cvTipo = new ComboViewer(composite_4, SWT.READ_ONLY);
		Combo cmbTipo = cvTipo.getCombo();
		cmbTipo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		formToolkit.paintBordersFor(cmbTipo);
		cvTipo.setContentProvider(ArrayContentProvider.getInstance());
		List<Object> list = Lists.newArrayList();
		list.add(0, "");
		list.addAll(Lists.newArrayList(TipoPendencia.values()));
		cvTipo.setInput(list);
		cvTipo.addPostSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Object object = SelectionHelper.getObject(cvTipo.getSelection());
				filter.setTipo((TipoPendencia) (object instanceof TipoPendencia ? object : null));
				getColumnViewer().refresh();
			}
		});
	}
	
	@Override
	public void createComponentsCpBotton(Composite parent, FormToolkit formToolkit2) {
		Composite cp = new Composite(parent, SWT.NONE);
		cp.setLayout(new GridLayout(3, false));
		cp.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false, 3, 1));
		formToolkit.adapt(cp);
		formToolkit.paintBordersFor(cp);
		
		Button btnTodas = new Button(cp, SWT.RADIO);
		btnTodas.setSelection(true);
		formToolkit.adapt(btnTodas, true, true);
		btnTodas.setText("Todas");
		btnTodas.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filter.setStatus(PendenciaFilter.TODAS);
				getColumnViewer().refresh();
			}
		});
		
		Button btnVencidas = new Button(cp, SWT.RADIO);
		formToolkit.adapt(btnVencidas, true, true);
		btnVencidas.setText("Vencidas");
		btnVencidas.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filter.setStatus(PendenciaFilter.VENCIDA);
				getColumnViewer().refresh();
			}
		});
		
		Button btnAVencer = new Button(cp, SWT.RADIO);
		formToolkit.adapt(btnAVencer, true, true);
		btnAVencer.setText("A vencer");
		btnAVencer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filter.setStatus(PendenciaFilter.AVENCER);
				getColumnViewer().refresh();
			}
		});
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
				if(p.getValor() == null)
					return StringUtils.EMPTY;
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
		filter = new PendenciaFilter();
		tableViewer.addFilter(filter);
		
		return tableViewer;
	}
	
	private Color COLOR_A_VENCER = SWTResourceManager.getColor(255, 165, 0);
	private Color COLOR_VENCIDA = SWTResourceManager.getColor(SWT.COLOR_RED);
	private PendenciaFilter filter;
	
	public Color getColorPendencia(Pendencia pendencia) {
		Calendar c = Calendar.getInstance();
		if(pendencia.getDataVencimento() == null)
			return COLOR_A_VENCER;
		if(pendencia.getDataVencimento().compareTo(DateHelper.zerarHoraMinutos(c.getTime())) < 0)
			return COLOR_VENCIDA;
		else{
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 10);
			if(pendencia.getDataVencimento().compareTo(c.getTime()) > 0)
				return COLOR_A_VENCER;
		}
		
		return ResourceManager.getColor(SWT.COLOR_BLACK);
	}
	
}
