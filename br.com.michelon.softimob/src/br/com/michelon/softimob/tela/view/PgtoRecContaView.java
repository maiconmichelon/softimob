package br.com.michelon.softimob.tela.view;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ImageRepository;
import org.eclipse.wb.swt.ResourceManager;

import br.com.michelon.softimob.aplicacao.editorInput.ContaPagarReceberEditorInput;
import br.com.michelon.softimob.aplicacao.exception.ContaNaoParametrizadaException;
import br.com.michelon.softimob.aplicacao.filter.ContaFilter;
import br.com.michelon.softimob.aplicacao.filter.PropertyFilter;
import br.com.michelon.softimob.aplicacao.helper.BoletoHelper;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.BoletoSoftimobService;
import br.com.michelon.softimob.aplicacao.service.ContaPagarReceberService;
import br.com.michelon.softimob.aplicacao.service.OrigemContaService;
import br.com.michelon.softimob.modelo.BoletoSoftimob;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.ArquivoRetorno;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.OrigemConta;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.dialog.GerarBoletoDialog;
import br.com.michelon.softimob.tela.editor.ContaPagarReceberEditor;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.NullStringValueFormatter;
import br.com.michelon.softimob.tela.widget.movimentacaoXViewer.MovimentacaoGenericXViewer;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewer;

import com.google.common.collect.Lists;

import de.ralfebert.rcputils.properties.BaseValue;
import de.ralfebert.rcputils.properties.IValue;
import de.ralfebert.rcputils.tables.ICellFormatter;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class PgtoRecContaView extends ViewPart {

	public static final String ID = "br.com.michelon.softimob.tela.view.PgtoRecContaView";

	private Logger log = Logger.getLogger(getClass());
	
	private Text txtFiltro;
	private Text txtConta;
	private Text txtDataBaixa;
	private DateTextField dtBaixa;

	private ContaPagarReceberService service = new ContaPagarReceberService();
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private GenericXViewer<MovimentacaoContabil> viewerMovimentacoes;
	private PropertyFilter propertyFilter;
	
	private List<ContaPagarReceber> selecionados;
	
	private WritableValue value = WritableValue.withValueType(ModeloPgtoConta.class);
	private Text text_1;
	private Text text_2;
	private TableViewerBuilder tvbEstorno;
	private TableViewerBuilder tvbContaPgto;
	private DateTextField dtFinal;
	private DateTextField dtInicial;
	private CTabFolder tfPrincipal;
	private CTabFolder tfPagamento;
	private Action actAdd;
	private Action actRefresh;
	private ContaFilter contaFilter = new ContaFilter();
	private Action actArqRetorno;
	
	public PgtoRecContaView() {
		createActions();
		service = new ContaPagarReceberService();
		propertyFilter = new PropertyFilter("origem.nome", "valor", "valorJurosDesconto", "dataConta", "dataVencimento", "dataPagamento", "tipoExtenso");
	}
	
	private void createActions() {
		{
			actAdd = new Action("Novo") {				@Override
				public void run() {
					try {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new ContaPagarReceberEditorInput(), ContaPagarReceberEditor.ID);
					} catch (PartInitException e) {
						log.error("Erro ao abrir tela de contas a pagar/receber.", e);
					}
				}
			};
			actAdd.setImageDescriptor(ImageRepository.ADD_16.getImageDescriptor());
		}
		{
			actRefresh = new Action("Atualizar") {				@Override
				public void run() {
					buscar();
				}
			};
			actRefresh.setImageDescriptor(ImageRepository.REFRESH_16.getImageDescriptor());
		}
		{
			actArqRetorno = new Action("Importar arquivo de retorno") {				@Override
				public void run() {
					FileDialog dialog = new FileDialog(ShellHelper.getActiveShell());
					dialog.setFilterExtensions(new String[]{"*.ret"});
					String file = dialog.open();
					
					if(file == null || file.isEmpty())
						return;
					
					try {
						BoletoSoftimobService service = new BoletoSoftimobService();
						
						List<ArquivoRetorno> retornos = BoletoHelper.getRetorno(new File(file));
						
						for(ArquivoRetorno retorno : retornos) {
							Long nossoNumero = BoletoSoftimob.extractNossoNumero(retorno.getNossoNumero());
							BoletoSoftimob boleto = service.findOne(nossoNumero);
							
							if(boleto == null)
								throw new Exception("Boleto não encontrado.");
							
							service.efetuarBaixa(boleto, retorno);
							
							service.salvar(boleto);
						}
						
						DialogHelper.openInformation("Arquivo de retorno importado com sucesso.");
						buscar();
					} catch (Exception e1) {
						DialogHelper.openErrorMultiStatus("Erro ao importar arquivo de retorno.", e1.getMessage());
						log.error("Erro ao importar arquivo de retorno.");
					}
				}
			};
			actArqRetorno.setImageDescriptor(ImageRepository.IMPORTACAO_ARQUIVORETORNO.getImageDescriptor());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void createPartControl(Composite parent) {
		
		Form frmContasAPagarreceber = formToolkit.createForm(parent);
		formToolkit.paintBordersFor(frmContasAPagarreceber);
		frmContasAPagarreceber.setText("Contas a Pagar/Receber");
		frmContasAPagarreceber.setImage(ImageRepository.CONTA_32.getImage());
		frmContasAPagarreceber.getBody().setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite cpPrincipal = formToolkit.createComposite(frmContasAPagarreceber.getBody(), SWT.NONE);
		formToolkit.paintBordersFor(cpPrincipal);
		cpPrincipal.setLayout(new GridLayout(2, false));
		
		Composite composite_4 = new Composite(cpPrincipal, SWT.NONE);
		composite_4.setLayout(new GridLayout(5, false));
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		formToolkit.adapt(composite_4);
		formToolkit.paintBordersFor(composite_4);
		
		Label lblPerodo = new Label(composite_4, SWT.NONE);
		lblPerodo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(lblPerodo, true, true);
		lblPerodo.setText("Período de");
		
		dtInicial = new DateTextField(composite_4);
		text_1 = dtInicial.getControl();
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblAt = new Label(composite_4, SWT.NONE);
		lblAt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(lblAt, true, true);
		lblAt.setText("até");
		
		dtFinal = new DateTextField(composite_4);
		text_2 = dtFinal.getControl();
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnBuscar = new Button(composite_4, SWT.NONE);
		btnBuscar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(btnBuscar, true, true);
		btnBuscar.setText("Buscar");
		btnBuscar.setImage(ImageRepository.SEARCH_16.getImage());
		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				buscar();
			}
		});
		
		Label label = formToolkit.createLabel(composite_4, "Filtro", SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtFiltro = new Text(composite_4, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1);
		gd_text.widthHint = 384;
		txtFiltro.setLayoutData(gd_text);
		txtFiltro.setMessage("Filtro...");
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				propertyFilter.setSearchText(((Text)e.widget).getText());
				
				tvbContaPgto.getTableViewer().refresh();
				tvbEstorno.getTableViewer().refresh();
			}
		});
		
		Label lblSituao = formToolkit.createLabel(composite_4, "Situação", SWT.NONE);
		lblSituao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		final ComboViewer cvStatus = new ComboViewer(composite_4, SWT.READ_ONLY);
		cvStatus.setContentProvider(ArrayContentProvider.getInstance());
		cvStatus.setInput(Arrays.asList(ContaFilter.TODAS, ContaFilter.PAGAR, ContaFilter.RECEBER));
		cvStatus.setSelection(new StructuredSelection(ContaFilter.TODAS));
		cvStatus.addPostSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				contaFilter.setStatus((String) SelectionHelper.getObject(cvStatus.getSelection()));
				
				tvbContaPgto.getTableViewer().refresh();
				tvbEstorno.getTableViewer().refresh();
			}
		});
		
		Label lblTipo = new Label(composite_4, SWT.NONE);
		lblTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(lblTipo, true, true);
		lblTipo.setText("Tipo");
		
		final ComboViewer cvTipo = new ComboViewer(composite_4, SWT.READ_ONLY);
		Combo cmbTipo = cvTipo.getCombo();
		GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gridData.widthHint = 150;
		gridData.minimumWidth = 129;
		cmbTipo.setLayoutData(gridData);
		formToolkit.paintBordersFor(cmbTipo);
		cvTipo.setContentProvider(ArrayContentProvider.getInstance());
		List findAtivados = new OrigemContaService().findAtivados();
		findAtivados.add(0, "");
		cvTipo.setInput(findAtivados);
		cvTipo.addPostSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Object object = SelectionHelper.getObject(cvTipo.getSelection());
				contaFilter.setOrigemConta((OrigemConta) (object instanceof OrigemConta ? object : null));
				
				tvbContaPgto.getTableViewer().refresh();
				tvbEstorno.getTableViewer().refresh();
			}
		});
		
		setarDatas();
		
		tfPrincipal = new CTabFolder(cpPrincipal, SWT.BORDER);
		tfPrincipal.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		formToolkit.adapt(tfPrincipal);
		formToolkit.paintBordersFor(tfPrincipal);
		tfPrincipal.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmPagamento = new CTabItem(tfPrincipal, SWT.NONE);
		tbtmPagamento.setText("Pagamento");
		
		tfPagamento = new CTabFolder(tfPrincipal, SWT.BORDER);
		tbtmPagamento.setControl(tfPagamento);
		tfPagamento.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		tfPagamento.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(tfPagamento.getSelectionIndex() == 0){
					buscar();
					viewerMovimentacoes.setInput(null);
					viewerMovimentacoes.refresh();
				}
			}
		});
		
		CTabItem tbtmContas = new CTabItem(tfPagamento, SWT.NONE);
		tbtmContas.setText("Contas");
		
		Composite composite_tvb = new Composite(tfPagamento, SWT.NONE);
		tbtmContas.setControl(composite_tvb);
		
		tvbContaPgto = criarTabelaContas(true, composite_tvb);
		
		CTabItem tbtmLanamentos = new CTabItem(tfPagamento, SWT.NONE);
		tbtmLanamentos.setText("Lançamentos");
		
		Composite composite_3 = new Composite(tfPagamento, SWT.NONE);
		tbtmLanamentos.setControl(composite_3);
		composite_3.setLayout(new GridLayout(4, false));
		
		Label lblDataPagamento = new Label(composite_3, SWT.NONE);
		lblDataPagamento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataPagamento.setText("Data pagamento");
		
		dtBaixa = new DateTextField(composite_3);
		txtDataBaixa = dtBaixa.getControl();
		txtDataBaixa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		
		Label lblConta = new Label(composite_3, SWT.NONE);
		lblConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblConta.setText("Conta");
		
		txtConta = new Text(composite_3, SWT.BORDER);
		txtConta.setEditable(false);
		txtConta.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar = new Button(composite_3, SWT.NONE);
		btnSelecionar.setImage(ImageRepository.SEARCH_16.getImage());
		
		Button btnRemoverConta = new Button(composite_3, SWT.NONE);
		btnRemoverConta.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnRemoverConta.setImage(ImageRepository.REMOVE_16.getImage());
		
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, btnSelecionar, btnRemoverConta, value, "conta");
		
		Button btnGerarLanamentos = new Button(composite_3, SWT.NONE);
		btnGerarLanamentos.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnGerarLanamentos.setText("Gerar Lançamentos");
		btnGerarLanamentos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gerarLancamentos();
			}
		});
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		
		Composite composite2 = new Composite(composite_3, SWT.NONE);
		composite2.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		formToolkit.adapt(composite2);
		formToolkit.paintBordersFor(composite2);
		
		criarTabelaDeMovimentacoes(composite2);
		
		CTabItem tbtmEstorno = new CTabItem(tfPrincipal, SWT.NONE);
		tbtmEstorno.setText("Estorno");
		
		Composite composite_1 = new Composite(tfPrincipal, SWT.NONE);
		tbtmEstorno.setControl(composite_1);
		formToolkit.paintBordersFor(composite_1);
		
		tvbEstorno = criarTabelaContas(false, composite_1);
		
		{
			tfPagamento.setSelection(0);
			tfPrincipal.setSelection(0);
		}
		GridData gd_text_11 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_11.widthHint = 79;
		
		Group group = new Group(cpPrincipal, SWT.NONE);
		group.setLayout(new GridLayout(3, false));
		group.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1));
		formToolkit.adapt(group);
		formToolkit.paintBordersFor(group);
		
		Button btnTodas = new Button(group, SWT.RADIO);
		btnTodas.setSelection(true);
		formToolkit.adapt(btnTodas, true, true);
		btnTodas.setText("Todas");
		btnTodas.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				contaFilter.setVencidaNaoVencida(ContaFilter.TODAS);
				tvbContaPgto.getTableViewer().refresh();
			}
		});
		
		Button btnVencidas = new Button(group, SWT.RADIO);
		formToolkit.adapt(btnVencidas, true, true);
		btnVencidas.setText("Vencidas");
		btnVencidas.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				contaFilter.setVencidaNaoVencida(ContaFilter.VENCIDA);
				tvbContaPgto.getTableViewer().refresh();
			}
		});
		
		Button btnAVencer = new Button(group, SWT.RADIO);
		formToolkit.adapt(btnAVencer, true, true);
		btnAVencer.setText("A vencer");
		btnAVencer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				contaFilter.setVencidaNaoVencida(ContaFilter.A_VENCER);
				tvbContaPgto.getTableViewer().refresh();
			}
		});
		
		Button btnBaixarConta = new Button(cpPrincipal, SWT.NONE);
		btnBaixarConta.setImage(ImageRepository.FINALIZAR_16.getImage());
		GridData gd_btnBaixarConta = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnBaixarConta.widthHint = 120;
		gd_btnBaixarConta.heightHint = 38;
		btnBaixarConta.setLayoutData(gd_btnBaixarConta);
		btnBaixarConta.setText("Baixar / Estornar");
		btnBaixarConta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isEstorno()){
					List<ContaPagarReceber> objects = SelectionHelper.getObjects(tvbEstorno.getTableViewer());
					estornar(objects, service);
				} else {
					efetuarBaixa(selecionados, service);
					selecionados = Lists.newArrayList();
					tfPagamento.setSelection(0);
				}
				
				buscar();
			}
		});

		value.setValue(new ModeloPgtoConta());
		initDataBindings();
		
		frmContasAPagarreceber.getToolBarManager().add(actAdd);
		frmContasAPagarreceber.getToolBarManager().add(actRefresh);
		frmContasAPagarreceber.getToolBarManager().add(actArqRetorno);
		
		frmContasAPagarreceber.updateToolBar();
		frmContasAPagarreceber.update();
		
	}
	
	private void setarDatas(){
		Calendar c = Calendar.getInstance();

		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);

		dtFinal.setValue(c.getTime());

		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 3);
		
		dtInicial.setValue(c.getTime());
	}
	
	private void gerarLancamentos() {
		if(dtBaixa.getValue() == null){
			DialogHelper.openWarning("Informe a data da baixa.");
			return;
		}
		
		selecionados = SelectionHelper.getObjects(tvbContaPgto.getTableViewer());
		List<MovimentacaoContabil> movs = Lists.newArrayList();
		ContaPagarReceberService service = new ContaPagarReceberService();
		
		try{
			Date dataBaixa = (Date) dtBaixa.getValue();

			for(ContaPagarReceber c : selecionados){
				MovimentacaoContabil geraMovimentacao = service.geraMovimentacao(c, (ModeloPgtoConta) value.getValue());
				
				if(geraMovimentacao.getLancamentos().isEmpty()){
					DialogHelper.openWarning("Verifique os planos de conta parametrizados.");
					return;
				}

				c.setMovimentacao(geraMovimentacao);
				c.setDataPagamento(dataBaixa);
				
				movs.add(geraMovimentacao);
			}
		}catch(ContaNaoParametrizadaException ce){
			DialogHelper.openWarning(ce.getMessage());
		}catch(Exception e1){
			log.error("Erro ao gerar movimentação.", e1);
			DialogHelper.openErrorMultiStatus("Erro ao gerar movimentação.", e1.getMessage());
		}
		
		viewerMovimentacoes.setInput(movs);
	}
	
	private TableViewerBuilder criarTabelaContas(boolean isPagamento, Composite composite) {
		final TableViewerBuilder tvbContas = new TableViewerBuilder(composite);
		
		tvbContas.createColumn("Tipo").setPercentWidth(1).bindToProperty("tipoExtenso").format(new NullStringValueFormatter()).build();
		tvbContas.createColumn("Data").setPercentWidth(1).bindToProperty("dataConta").format(new DateStringValueFormatter()).build();
		
		if(!isPagamento){
			tvbContas.createColumn("Data de Pagamento").setPercentWidth(1).bindToProperty("dataPagamento").format(new DateStringValueFormatter()).build();
		}
		
		tvbContas.createColumn("Data de Vencimento").setPercentWidth(1).bindToProperty("dataVencimento").format(new DateStringValueFormatter()).build();
		tvbContas.createColumn("Origem").setPercentWidth(1).bindToProperty("origem.nome").build();
		
		if(isPagamento){
			tvbContas.createColumn("Valor").setPercentWidth(1).bindToProperty("valor").format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
			
			tvbContas.createColumn("Valor com Juros e Descontos").setPercentWidth(1).bindToValue(new IValue() {
				
				@Override
				public void setValue(Object element, Object value) {
					ContaPagarReceber c = (ContaPagarReceber) element;
					try{
						BigDecimal valorComJurosDesconto = new BigDecimal(value.toString().replace(',', '.'));
						c.setValorJurosDesconto(valorComJurosDesconto.subtract(c.getValor()));
					}catch(Exception e){
						log.warn("Erro ao converter valor do juros/desconto.");
					}
				}
				
				@Override
				public Object getValue(Object element) {
					ContaPagarReceber c = (ContaPagarReceber) element;
					return FormatterHelper.getDecimalFormat().format(c.getValor().add(c.getValorJurDescTratado()));
				}
			}).makeEditable().build();
			
		}
		
		tvbContas.createColumn("Juros / Desconto").setPercentWidth(1).bindToValue(new BaseValue<ContaPagarReceber>() {
			@Override
			public Object get(ContaPagarReceber element) {
				return element.getValorJurDescTratado().abs();
			}
		}).format(new ICellFormatter() {
			
			@Override
			public void formatCell(ViewerCell arg0, Object arg1) {
				ContaPagarReceber conta = (ContaPagarReceber) arg0.getElement();
				int signum = conta.getValorJurDescTratado().signum();
				arg0.setForeground(ResourceManager.getColor(signum > 0 ? SWT.COLOR_RED : (signum < 0 ? SWT.COLOR_DARK_GREEN : SWT.COLOR_BLACK)));
				arg0.setText(FormatterHelper.getDefaultValueFormatterToMoney().format((BigDecimal) arg1));
			}
			
		}).build();
		
		tvbContas.getTableViewer().addFilter(propertyFilter);
		tvbContas.getTableViewer().addFilter(contaFilter);
		
		if(isPagamento){
			tvbContas.getTableViewer().addFilter(new ViewerFilter() {
				@Override
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					return ((ContaPagarReceber)element).getDataPagamento() == null;
				}
			});
		} else {
			tvbContas.getTableViewer().addFilter(new ViewerFilter() {
				@Override
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					return ((ContaPagarReceber)element).getDataPagamento() != null;
				}
			});
		}
		
		if(isPagamento){
			Menu menu = new Menu(tvbContas.getTable());
			tvbContas.getTable().setMenu(menu);
			
			MenuItem miAlterar = new MenuItem(menu, SWT.BORDER);
			miAlterar.setText("Alterar");
			miAlterar.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ContaPagarReceberEditorInput editorInput = new ContaPagarReceberEditorInput();
					editorInput.setModelo((ContaPagarReceber) SelectionHelper.getObject(tvbContaPgto.getTableViewer()));
					
					try {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(editorInput, ContaPagarReceberEditor.ID);
					} catch (PartInitException e1) {
						log.error("Erro ao abrir tela para modificação da conta a pagar/receber");
					}
				}
			});
			miAlterar.setImage(ImageRepository.ALTERAR16.getImage());
			
			MenuItem miGerarBoleto = new MenuItem(menu, SWT.BORDER);
			miGerarBoleto.setText("Gerar Boleto");
			miGerarBoleto.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					GerarBoletoDialog dialog = new GerarBoletoDialog(ShellHelper.getActiveShell());
					if(dialog.open() == IDialogConstants.OK_ID){
						ContaPagarReceber conta = (ContaPagarReceber) SelectionHelper.getObject(tvbContas.getTableViewer());
						
						try {
							BoletoHelper.gerarBoleto(conta, dialog.getCliente());
						} catch (Exception e1) {
							log.error("Erro ao gerar boleto.", e1);
							DialogHelper.openErrorMultiStatus("Erro ao gerar boleto.", e1.getMessage());
						}
					}
				}
			});
			miGerarBoleto.setImage(ImageRepository.BOLETO_16.getImage());
		}
		
		return tvbContas;
	}

	private void criarTabelaDeMovimentacoes(Composite composite_3) {
		viewerMovimentacoes = MovimentacaoGenericXViewer.createXviewer(composite_3);
	}
	
	private void efetuarBaixa(List<ContaPagarReceber> selecionados, ContaPagarReceberService service) {
		try {
			if(selecionados == null || selecionados.isEmpty() || tfPrincipal.getSelectionIndex() == 1 || tfPagamento.getSelectionIndex() == 0){
				DialogHelper.openWarning("Para efetuar a baixa das contas selecionadas deverá ser gerado todas as movimentações correspondentes.");
				return;
			}
			
			service.baixarContas(selecionados);
			DialogHelper.openInformation("Baixa de conta(s) efetuada com sucesso.");
			
			viewerMovimentacoes.setInput(null);
			viewerMovimentacoes.refresh();
		} catch (Exception e1) {
			log.error("Erro ao baixar contas.", e1);
		}
	}
	
	protected void estornar(List<ContaPagarReceber> selecionados, ContaPagarReceberService service) {
		try{
			if(selecionados == null || selecionados.isEmpty())
				return; 
			
			service.estornarContas(selecionados);
			
			DialogHelper.openInformation("Estorno da(s) conta(s) efetuada com sucesso.");
		}catch(Exception e){
			log.error("Erro ao estornar conta.", e);
		}
	}

	private boolean isEstorno() {
		return tfPrincipal.getSelectionIndex() == 1;
	}

	@SuppressWarnings("unused")
	private boolean isPagamento() {
		return tfPrincipal.getSelectionIndex() == 0;
	}
	
	private void buscar() {
		List<ContaPagarReceber> contas = service.buscarContas(dtInicial.getValue(), dtFinal.getValue());
		
		tvbContaPgto.setInput(contas);
		tvbEstorno.setInput(contas);
	}
	
	@Override
	public void setFocus() {
		buscar();
	}
	
	private DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataBaixa);
		IObservableValue valueDataBaixaObserveDetailValue = PojoProperties.value(ModeloPgtoConta.class, "dataBaixa", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueDataBaixaObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtContaObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtConta);
		IObservableValue valueContaObserveDetailValue = PojoProperties.value(ModeloPgtoConta.class, "conta", PlanoConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtContaObserveWidget, valueContaObserveDetailValue, null, null);
		//
		return bindingContext;
	}
	
	public class ModeloPgtoConta{
		
		private PlanoConta conta;
		
		private Date dataBaixa;
		
		public PlanoConta getConta() {
			return conta;
		}
		
		public Date getDataBaixa() {
			return dataBaixa;
		}
		
		public void setConta(PlanoConta conta) {
			this.conta = conta;
		}
		
		public void setDataBaixa(Date dataBaixa) {
			this.dataBaixa = dataBaixa;
		}
		
	}
}
