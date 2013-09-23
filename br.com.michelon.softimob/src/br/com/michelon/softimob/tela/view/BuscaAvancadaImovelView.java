package br.com.michelon.softimob.tela.view;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.helper.NumberHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.CidadeService;
import br.com.michelon.softimob.aplicacao.service.ImovelService;
import br.com.michelon.softimob.aplicacao.service.TipoImovelService;
import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.TipoImovel;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.dialog.ComodoDialog;
import br.com.michelon.softimob.tela.widget.LoadOnFocus;
import br.com.michelon.softimob.tela.widget.MoneyTextField;

import com.google.common.collect.Lists;

import de.ralfebert.rcputils.properties.IValue;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class BuscaAvancadaImovelView extends ViewPart {
	
	public static final String ID = "br.com.michelon.softimob.tela.view.BuscaAvancadaImovelView"; //$NON-NLS-1$
	
	private WritableValue value = WritableValue.withValueType(ModeloBusca.class);
	
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private TableViewerBuilder tvbComodo;
	private Text txtCodigo;
	private Button btnVenda;
	private Button btnAluguel;
	private ComboViewer cvTipoImovel;
	private ComboViewer cvCidade;
	private ComboViewer cvBairro;
	private Button btnNaoReservado;
	private Button btnReservado;
	private Button btnTodos;

	private Button btnLocaovenda;

	public BuscaAvancadaImovelView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(1, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite = new GridLayout(5, false);
		gl_composite.verticalSpacing = 10;
		composite.setLayout(gl_composite);
		
		Label lblCdigo = new Label(composite, SWT.NONE);
		lblCdigo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCdigo.setText("Código");
		
		txtCodigo = new Text(composite, SWT.BORDER);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblValor = new Label(composite, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, true, 1, 1));
		lblValor.setText("Valor de");
		
		Composite composite_4 = new Composite(composite, SWT.NONE);
		GridLayout gl_composite_4 = new GridLayout(3, false);
		gl_composite_4.marginWidth = 0;
		composite_4.setLayout(gl_composite_4);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 2));
		
		MoneyTextField moneyTextField = new MoneyTextField(composite_4);
		text_3 = moneyTextField.getControl();
		
		Label lblAt = new Label(composite_4, SWT.NONE);
		lblAt.setText("até");
		
		MoneyTextField moneyTextField_1 = new MoneyTextField(composite_4);
		text = moneyTextField_1.getControl();
		
		text_2 = new Text(composite_4, SWT.BORDER);
		
		Label label = new Label(composite_4, SWT.NONE);
		label.setText("até");
		
		text_1 = new Text(composite_4, SWT.BORDER);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblMetragem = new Label(composite, SWT.NONE);
		lblMetragem.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		lblMetragem.setText("Metragem de");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Composite composite_15 = new Composite(composite, SWT.NONE);
		composite_15.setLayout(new GridLayout(4, false));
		composite_15.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		btnAluguel = new Button(composite_15, SWT.RADIO);
		btnAluguel.setText("Locação");
		
		btnVenda = new Button(composite_15, SWT.RADIO);
		btnVenda.setText("Venda");
		
		btnLocaovenda = new Button(composite_15, SWT.RADIO);
		btnLocaovenda.setText("Locação/Venda");
		
		btnTodos = new Button(composite_15, SWT.RADIO);
		btnTodos.setText("Todos");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Composite composite_5 = new Composite(composite, SWT.NONE);
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 5, 1));
		composite_5.setLayout(new GridLayout(8, false));
		
		Label lblAngariad = new Label(composite_5, SWT.NONE);
		lblAngariad.setText("Angariador");
		
		text_4 = new Text(composite_5, SWT.BORDER);
		text_4.setEditable(false);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(composite_5, SWT.NONE);
		btnNewButton.setImage(ImageRepository.SEARCH_16.getImage());
		
		Button button = new Button(composite_5, SWT.NONE);
		button.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnNewButton, button, value, "angariador");
		
		Label lblProprietario = new Label(composite_5, SWT.NONE);
		lblProprietario.setText("Proprietário");
		
		text_5 = new Text(composite_5, SWT.BORDER);
		text_5.setEditable(false);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar_7 = new Button(composite_5, SWT.NONE);
		btnSelecionar_7.setImage(ImageRepository.SEARCH_16.getImage());
		
		Button button_1 = new Button(composite_5, SWT.NONE);
		button_1.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, btnSelecionar_7, button_1, value, "proprietario");
		
		Label lblTipoImvel = new Label(composite_5, SWT.NONE);
		lblTipoImvel.setText("Tipo Imóvel");
		
		cvTipoImovel = new ComboViewer(composite_5, SWT.READ_ONLY);
		Combo combo = cvTipoImovel.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		cvTipoImovel.setContentProvider(ArrayContentProvider.getInstance());
		LoadOnFocus.setFocusGainedListener(cvTipoImovel, new TipoImovelService());
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		
		Label lblCidade = new Label(composite_5, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");
		
		cvCidade = new ComboViewer(composite_5, SWT.READ_ONLY);
		Combo combo_2 = cvCidade.getCombo();
		cvCidade.setContentProvider(ArrayContentProvider.getInstance());
		LoadOnFocus.setFocusGainedListener(cvCidade, new CidadeService());
		cvCidade.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Cidade cidade = SelectionHelper.getObject(cvCidade);
				if(cvBairro == null || cidade == null)
					return;
				
				cvBairro.setInput(cidade.getBairros());
				cvBairro.setSelection(null);
			}
		});
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Label lblBairro = new Label(composite_5, SWT.NONE);
		lblBairro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBairro.setText("Bairro");
		
		cvBairro = new ComboViewer(composite_5, SWT.READ_ONLY);
		Combo combo_1 = cvBairro.getCombo();
		cvBairro.setContentProvider(ArrayContentProvider.getInstance());
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		
		Label lblObservaes_3 = new Label(composite, SWT.NONE);
		lblObservaes_3.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes_3.setText("Observações");
		
		text_6 = new Text(composite, SWT.BORDER);
		GridData gd_text_6 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 4, 1);
		gd_text_6.heightHint = 60;
		gd_text_6.widthHint = 449;
		text_6.setLayoutData(gd_text_6);
		new Label(composite, SWT.NONE);
		
		Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setLayout(new GridLayout(2, false));
		
		btnNaoReservado = new Button(composite_3, SWT.CHECK);
		btnNaoReservado.setSelection(true);
		btnNaoReservado.setText("Não Reservado");
		
		btnReservado = new Button(composite_3, SWT.CHECK);
		btnReservado.setText("Reservado");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TabItem tbtmCmodos = new TabItem(tabFolder, SWT.NONE);
		tbtmCmodos.setText("Cômodos");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmCmodos.setControl(composite_1);
		composite_1.setLayout(new GridLayout(2, false));
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		criarTabelaComodo(composite_2);
		
		Button btnAdicionar = new Button(composite_1, SWT.NONE);
		btnAdicionar.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1));
		btnAdicionar.setText("Adicionar");
		btnAdicionar.setImage(ImageRepository.ADD_16.getImage());
		btnAdicionar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ComodoDialog dialog = new ComodoDialog(ShellHelper.getActiveShell());
				if(dialog.open() == IDialogConstants.OK_ID){
					((ModeloBusca)value.getValue()).getComodos().add(dialog.getComodo());
					tvbComodo.getTableViewer().refresh();
				}
			}
		});
		
		Button btnRemover = new Button(composite_1, SWT.NONE);
		btnRemover.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1));
		btnRemover.setText("Remover");
		btnRemover.setImage(ImageRepository.DELETE_16.getImage());
		btnRemover.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Comodo comodo = SelectionHelper.getObject(tvbComodo.getTableViewer());
				((ModeloBusca)value.getValue()).getComodos().remove(comodo);
				tvbComodo.getTableViewer().refresh();
			}
		});
		
		Button btnNewButton_1 = new Button(parent, SWT.NONE);
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 82;
		gd_btnNewButton_1.heightHint = 46;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("Buscar");
		btnNewButton_1.setImage(ImageRepository.SEARCH_32.getImage());
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				buscar();
			}
		});
		
		createActions();
		initializeToolBar();
		initializeMenu();
		
		value.setValue(new ModeloBusca());
		initDataBindings();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	
	private void buscar(){
		List<Imovel> imoveis = new ImovelService().findImoveis((ModeloBusca) value.getValue());
		
		try {
			ImovelView showView = (ImovelView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ImovelView.ID);
			showView.setInput(imoveis);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	private void criarTabelaComodo(Composite composite){
		tvbComodo = new TableViewerBuilder(composite);
		
		tvbComodo.createColumn("Cômodo").bindToProperty("tipoComodo.nome").build();
		tvbComodo.createColumn("Quantidade").bindToValue(new IValue() {
			
			@Override
			public void setValue(Object arg0, Object arg1) {
				((Comodo)arg0).setQuantidade(!arg1.toString().isEmpty() ? new Integer(NumberHelper.extractNumbers(arg1.toString())) : null);
			}
			
			@Override
			public Object getValue(Object arg0) {
				Integer quantidade = ((Comodo)arg0).getQuantidade();
				return quantidade == null ? StringUtils.EMPTY : quantidade;
			}
		}).makeEditable().build();
		tvbComodo.createColumn("Descrição").bindToProperty("descricao").makeEditable().build();
	}
	
	public class ModeloBusca{
	
		private Long codigo;
		
		private BigDecimal valMin;
		
		private BigDecimal valMax;
		
		private Integer metroMin;
		
		private Integer metroMax;
		
		private boolean isVenda = false;
		
		private boolean isLocacao = false;

		private boolean isVendaLocacao = false;
		
		private boolean todos = true;
		
		private Funcionario angariador;
		
		private Cliente proprietario;
		
		private TipoImovel tipoImovel;
		
		private Cidade cidade;
		
		private Bairro bairro;
		
		private String observacoes;
		
		private boolean reservado = true;
		
		private boolean naoReservado = true;
		
		private List<Comodo> comodos = Lists.newArrayList();
		
		public Long getCodigo() {
			return codigo;
		}

		public void setCodigo(Long codigo) {
			this.codigo = codigo;
		}

		public BigDecimal getValMin() {
			return valMin;
		}

		public void setValMin(BigDecimal valMin) {
			this.valMin = valMin;
		}

		public BigDecimal getValMax() {
			return valMax;
		}

		public void setValMax(BigDecimal valMax) {
			this.valMax = valMax;
		}

		public Integer getMetroMin() {
			return metroMin;
		}

		public void setMetroMin(Integer metroMin) {
			this.metroMin = metroMin;
		}

		public Integer getMetroMax() {
			return metroMax;
		}

		public void setMetroMax(Integer metroMax) {
			this.metroMax = metroMax;
		}

		public boolean isVenda() {
			return isVenda;
		}

		public void setVenda(boolean isVenda) {
			this.isVenda = isVenda;
		}

		public boolean isLocacao() {
			return isLocacao;
		}

		public void setLocacao(boolean isLocacao) {
			this.isLocacao = isLocacao;
		}

		public Funcionario getAngariador() {
			return angariador;
		}

		public void setAngariador(Funcionario angariador) {
			this.angariador = angariador;
		}

		public Cliente getProprietario() {
			return proprietario;
		}

		public void setProprietario(Cliente proprietario) {
			this.proprietario = proprietario;
		}

		public TipoImovel getTipoImovel() {
			return tipoImovel;
		}

		public void setTipoImovel(TipoImovel tipoImovel) {
			this.tipoImovel = tipoImovel;
		}

		public Cidade getCidade() {
			return cidade;
		}

		public void setCidade(Cidade cidade) {
			this.cidade = cidade;
		}

		public Bairro getBairro() {
			return bairro;
		}

		public void setBairro(Bairro bairro) {
			this.bairro = bairro;
		}

		public String getObservacoes() {
			return observacoes;
		}

		public void setObservacoes(String observacoes) {
			this.observacoes = observacoes;
		}

		public boolean isReservado() {
			return reservado;
		}

		public void setReservado(boolean reservado) {
			this.reservado = reservado;
		}

		public boolean isNaoReservado() {
			return naoReservado;
		}

		public void setNaoReservado(boolean naoReservado) {
			this.naoReservado = naoReservado;
		}
		
		public List<Comodo> getComodos() {
			return comodos;
		}
		
		public void setComodos(List<Comodo> comodos) {
			this.comodos = comodos;
		}
		
		public boolean isTodos() {
			return todos;
		}
		
		public void setTodos(boolean todos) {
			this.todos = todos;
		}

		public boolean isVendaLocacao() {
			return isVendaLocacao;
		}

		public void setVendaLocacao(boolean isVendaLocacao) {
			this.isVendaLocacao = isVendaLocacao;
		}
		
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtCodigoObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtCodigo);
		IObservableValue valueCodigoObserveDetailValue = PojoProperties.value(ModeloBusca.class, "codigo", Long.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtCodigoObserveWidget, valueCodigoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_3);
		IObservableValue valueValMinObserveDetailValue = PojoProperties.value(ModeloBusca.class, "valMin", BigDecimal.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_3ObserveWidget, valueValMinObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueValMaxObserveDetailValue = PojoProperties.value(ModeloBusca.class, "valMax", BigDecimal.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueValMaxObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_2);
		IObservableValue valueMetroMinObserveDetailValue = PojoProperties.value(ModeloBusca.class, "metroMin", Integer.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueMetroMinObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueMetroMaxObserveDetailValue = PojoProperties.value(ModeloBusca.class, "metroMax", Integer.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueMetroMaxObserveDetailValue, null, null);
		//
		IObservableValue observeSelectionBtnVendaObserveWidget = WidgetProperties.selection().observe(btnVenda);
		IObservableValue valueVendaObserveDetailValue = PojoProperties.value(ModeloBusca.class, "venda", boolean.class).observeDetail(value);
		bindingContext.bindValue(observeSelectionBtnVendaObserveWidget, valueVendaObserveDetailValue, null, null);
		//
		IObservableValue observeSelectionBtnAluguelObserveWidget = WidgetProperties.selection().observe(btnAluguel);
		IObservableValue valueLocacaoObserveDetailValue = PojoProperties.value(ModeloBusca.class, "locacao", boolean.class).observeDetail(value);
		bindingContext.bindValue(observeSelectionBtnAluguelObserveWidget, valueLocacaoObserveDetailValue, null, null);
		//
		IObservableValue observeSelectionBtnVendaAluguelObserveWidget = WidgetProperties.selection().observe(btnLocaovenda);
		IObservableValue valueVendaLocacaoObserveDetailValue = PojoProperties.value(ModeloBusca.class, "vendaLocacao", boolean.class).observeDetail(value);
		bindingContext.bindValue(observeSelectionBtnVendaAluguelObserveWidget, valueVendaLocacaoObserveDetailValue, null, null);
		//
		IObservableValue observeSelectionBtnTodosObserveWidget = WidgetProperties.selection().observe(btnTodos);
		IObservableValue valueTodosObserveDetailValue = PojoProperties.value(ModeloBusca.class, "todos", boolean.class).observeDetail(value);
		bindingContext.bindValue(observeSelectionBtnTodosObserveWidget, valueTodosObserveDetailValue, null, null);
		//
		IObservableValue observeSelectionBtnSemContratoObserveWidget = WidgetProperties.selection().observe(btnReservado);
		IObservableValue valueSemContratoObserveDetailValue = PojoProperties.value(ModeloBusca.class, "reservado", boolean.class).observeDetail(value);
		bindingContext.bindValue(observeSelectionBtnSemContratoObserveWidget, valueSemContratoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_4ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_4);
		IObservableValue valueAngariadorObserveDetailValue = PojoProperties.value(ModeloBusca.class, "angariador", Funcionario.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_4ObserveWidget, valueAngariadorObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_5ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_5);
		IObservableValue valueProprietarioObserveDetailValue = PojoProperties.value(ModeloBusca.class, "proprietario", Cliente.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_5ObserveWidget, valueProprietarioObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(cvTipoImovel);
		IObservableValue valueTipoImovelObserveDetailValue = PojoProperties.value(ModeloBusca.class, "tipoImovel", TipoImovel.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer, valueTipoImovelObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionComboViewer_2 = ViewerProperties.singleSelection().observe(cvCidade);
		IObservableValue valueCidadeObserveDetailValue = PojoProperties.value(ModeloBusca.class, "cidade", Cidade.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer_2, valueCidadeObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionComboViewer_1 = ViewerProperties.singleSelection().observe(cvBairro);
		IObservableValue valueBairroObserveDetailValue = PojoProperties.value(ModeloBusca.class, "bairro", Bairro.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer_1, valueBairroObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_6ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_6);
		IObservableValue valueObservacoesObserveDetailValue = PojoProperties.value(ModeloBusca.class, "observacoes", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_6ObserveWidget, valueObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeSelectionBtnReservadoObserveWidget = WidgetProperties.selection().observe(btnNaoReservado);
		IObservableValue valueNaoReservadoObserveDetailValue = PojoProperties.value(ModeloBusca.class, "naoReservado", boolean.class).observeDetail(value);
		bindingContext.bindValue(observeSelectionBtnReservadoObserveWidget, valueNaoReservadoObserveDetailValue, null, null);
		//
		IObservableValue observeSelectionBtnReservado_1ObserveWidget = WidgetProperties.selection().observe(btnReservado);
		IObservableValue valueReservadoObserveDetailValue = PojoProperties.value(ModeloBusca.class, "reservado", boolean.class).observeDetail(value);
		bindingContext.bindValue(observeSelectionBtnReservado_1ObserveWidget, valueReservadoObserveDetailValue, null, null);
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		tvbComodo.getTableViewer().setContentProvider(listContentProvider);
		IObservableList valueComodosObserveDetailList = PojoProperties.list(ModeloBusca.class, "comodos", Comodo.class).observeDetail(value);
		tvbComodo.getTableViewer().setInput(valueComodosObserveDetailList);
		//
		return bindingContext;
	}
}
