package br.com.michelon.softimob.tela.editor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.nebula.jface.viewer.radiogroup.RadioGroupViewer;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;
import org.joda.time.DateMidnight;
import org.joda.time.Months;

import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.WidgetHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.AluguelService;
import br.com.michelon.softimob.aplicacao.service.ChamadoReformaService;
import br.com.michelon.softimob.aplicacao.service.CheckListService;
import br.com.michelon.softimob.aplicacao.service.ComissaoService;
import br.com.michelon.softimob.aplicacao.service.ContaPagarReceberService;
import br.com.michelon.softimob.aplicacao.service.ContratoPrestacaoServicoService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.IndiceService;
import br.com.michelon.softimob.aplicacao.service.VistoriaService;
import br.com.michelon.softimob.modelo.AcontecimentoChamado;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.ChamadoReforma;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.Comissao;
import br.com.michelon.softimob.modelo.Comissionado;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.FinalizacaoChamadoReforma;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.Indice;
import br.com.michelon.softimob.modelo.ItemCheckList;
import br.com.michelon.softimob.modelo.ModeloContrato;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.modelo.VendaAluguel;
import br.com.michelon.softimob.modelo.Vistoria;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.dialog.AcontecimentoChamadoDialog;
import br.com.michelon.softimob.tela.dialog.AdicionarContaPagarReformaDialog;
import br.com.michelon.softimob.tela.dialog.ParcelaDialog;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.LoadOnFocus;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import br.com.michelon.softimob.tela.widget.NullStringValueFormatter;
import br.com.michelon.softimob.tela.widget.NumberTextField2;
import br.com.michelon.softimob.tela.widget.PhotoComposite;
import br.com.michelon.softimob.tela.widget.TimeStringValueFormatter;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class AluguelEditor extends GenericEditor<Aluguel>{
	public static final String ID = "br.com.michelon.softimob.tela.editor.AluguelEditor";
	
	private Logger log = Logger.getLogger(getClass());
	
	private WritableValue valueVistoria = WritableValue.withValueType(Vistoria.class);
	private WritableValue valueChamado = WritableValue.withValueType(ChamadoReforma.class);
	private WritableValue valueFinalizacaoChamado = WritableValue.withValueType(FinalizacaoChamadoReforma.class);
	private WritableValue valueComissao = WritableValue.withValueType(Comissao.class);
	
	private AluguelService service = new AluguelService();
	
	private Text text;
	private Text txtCliente;
	private Text txtDataVencimento;
	private Text txtDataAssinaturaContrato;
	private Text txtValor;
	private Text txtFiador;
	private Text txtFuncionario;
	private Text txtDescricaoFinalReforma;
	private Text txtFuncionarioFinalizacaoReforma;
	private Text txtFinalizacaoReforma;
	private Text txtProblemaReforma;
	private Text txtFuncionarioReforma;
	private Text txtDataChamado;
	private Text txtObservacoesVistoria;
	private Text txtFuncionarioVistoria;
	private Text txtDataVistoria;
	private Text txtComissionado;
	private Text txtModeloContrato;
	private Text txtValorComissao;
	private Text txtIdChamado;
	private Text txtDataVencimentoComissao;
	private TableViewerBuilder tvbChamadoGeral;
	private TableViewerBuilder tvbAcontecimentoChamado;
	private TableViewerBuilder tvbVistoria;
	private TableViewer tvVistoria;
	private TableViewer tvAcontecimentoChamado;
	private TableViewer tvChamadoGeral;
	private TableViewer tvComissao;
	private TableViewer tvCheckListAluguel;
	private TableViewer tvCheckListVistoria;
	private TableViewer tvParcelas;
	private RadioGroupViewer radiouGroupStatusReforma;
	private CTabFolder tfItens;
	private Composite cpChamados;
	private Composite cpVistoria;
	private Composite cpComissao;
	private Table tableContasFinalizacaoReforma;
	private TableViewer tvContasFinalizacaoChamadoReforma;
	private ComboViewer cvAjuste;
	private PhotoComposite photoComposite;
	private Button btnSelecionarContrato;
	private Button btnRemoveContrato;
	private Button btnSelecionarCliente;
	private Button btnRemoveCliente;
	private Button btnSelecionarFiador;
	private Button btnRemoverFiador;
	private Button btnRemoveFuncionario;
	private Button btnSelecFuncionario;
	private Button btnAddContrato;
	private Button btnRemoverContrato;
	private DateTextField dtVencimento;
	private DateTextField dtAssinaturaContrato;

	public AluguelEditor() {
		super(Aluguel.class);
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(8, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblImvel = new Label(parent, SWT.NONE);
		lblImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblImvel.setText("Imóvel");
		
		text = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnSelecionarContrato = new Button(parent, SWT.NONE);
		btnSelecionarContrato.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CONTRATO_SERVICO_LOCACAO, btnSelecionarContrato, value, "contrato");
		
		btnRemoveContrato = new Button(parent, SWT.NONE);
		btnRemoveContrato.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnRemoveContrato, value, "contrato", ContratoPrestacaoServico.class);
		
		Label lblFuncionrio = new Label(parent, SWT.NONE);
		lblFuncionrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio.setText("Corretor");
		
		txtFuncionario = new Text(parent, SWT.BORDER);
		txtFuncionario.setEditable(false);
		txtFuncionario.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnSelecFuncionario = new Button(parent, SWT.NONE);
		btnSelecFuncionario.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecFuncionario, value, "funcionario");
		
		btnRemoveFuncionario = new Button(parent, SWT.NONE);
		btnRemoveFuncionario.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnRemoveFuncionario, value, "funcionario", Funcionario.class);
		
		Label lblValor = new Label(parent, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Locatário");
		
		txtCliente = new Text(parent, SWT.BORDER);
		txtCliente.setEditable(false);
		txtCliente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnSelecionarCliente = new Button(parent, SWT.NONE);
		btnSelecionarCliente.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, btnSelecionarCliente, value, "cliente");
		
		btnRemoveCliente = new Button(parent, SWT.NONE);
		btnRemoveCliente.setImage(ImageRepository.REMOVE_16.getImage());
		btnRemoveCliente.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		ListElementDialogHelper.addSelectionToRemoveButton(btnRemoveCliente, value, "cliente", Cliente.class);
		
		Label lblFiador = new Label(parent, SWT.NONE);
		lblFiador.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFiador.setText("Fiador");
		
		txtFiador = new Text(parent, SWT.BORDER);
		txtFiador.setEditable(false);
		txtFiador.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnSelecionarFiador = new Button(parent, SWT.NONE);
		btnSelecionarFiador.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, btnSelecionarFiador, value, "fiador");
		
		btnRemoverFiador = new Button(parent, SWT.NONE);
		btnRemoverFiador.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnRemoverFiador, value, "fiador", Cliente.class);
		
		Label lblModeloContrato = new Label(parent, SWT.NONE);
		lblModeloContrato.setText("Modelo de Contrato");
		lblModeloContrato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtModeloContrato = new Text(parent, SWT.BORDER);
		txtModeloContrato.setEditable(false);
		txtModeloContrato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnAddContrato = new Button(parent, SWT.NONE);
		btnAddContrato.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.MODELO_CONTRATO, btnAddContrato, value, "modeloContrato");
		
		btnRemoverContrato = new Button(parent, SWT.NONE);
		btnRemoverContrato.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnRemoverContrato, value, "modeloContrato", ModeloContrato.class);
		
		Label lblReajuste = new Label(parent, SWT.NONE);
		lblReajuste.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblReajuste.setText("Reajuste");
		
		cvAjuste = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo = cvAjuste.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		cvAjuste.setContentProvider(ArrayContentProvider.getInstance());
		LoadOnFocus.setFocusGainedListener(cvAjuste, new IndiceService());
		
		Label lblData = new Label(parent, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		dtAssinaturaContrato = new DateTextField(parent);
		dtAssinaturaContrato.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		txtDataAssinaturaContrato = dtAssinaturaContrato.getControl();
		txtDataAssinaturaContrato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDurao = new Label(parent, SWT.NONE);
		lblDurao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDurao.setText("Data de Vencimento");
		
		dtVencimento = new DateTextField(parent);
		dtVencimento.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		txtDataVencimento = dtVencimento.getControl();
		txtDataVencimento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblValor_1 = new Label(parent, SWT.NONE);
		lblValor_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_1.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(parent);
		txtValor = moneyTextField.getControl();
		txtValor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		tfItens = new CTabFolder(parent, SWT.BORDER);
		tfItens.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 8, 1));
		tfItens.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmComisso = new CTabItem(tfItens, SWT.NONE);
		tbtmComisso.setText("Comissão");
		
		cpComissao = new Composite(tfItens, SWT.NONE);
		tbtmComisso.setControl(cpComissao);
		cpComissao.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		cpComissao.setLayout(new GridLayout(1, false));
		
		Composite composite_1 = new Composite(cpComissao, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		criarTabelaComissao(composite_1);

		Group grpComisso = new Group(cpComissao, SWT.NONE);
		grpComisso.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpComisso.setText("Comissão");
		grpComisso.setLayout(new GridLayout(4, false));
		
		Label lblComissionado = new Label(grpComisso, SWT.NONE);
		lblComissionado.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblComissionado.setText("Comissionado");
		
		txtComissionado = new Text(grpComisso, SWT.BORDER);
		txtComissionado.setEditable(false);
		txtComissionado.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(grpComisso, SWT.NONE);
		btnNewButton.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.COMISSIONADO, btnNewButton, valueComissao, "comissionado");
		
		Button btnNewButton2 = new Button(grpComisso, SWT.NONE);
		btnNewButton2.setImage(ImageRepository.REMOVE_16.getImage());
		btnNewButton2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		ListElementDialogHelper.addSelectionToRemoveButton(btnNewButton2, valueComissao, "comissionado", Comissionado.class);
		
		Label lblDataDeVencimento = new Label(grpComisso, SWT.NONE);
		lblDataDeVencimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento.setText("Data de Vencimento");
		
		DateTextField dateTextField_1 = new DateTextField(grpComisso);
		txtDataVencimentoComissao = dateTextField_1.getControl();
		txtDataVencimentoComissao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		
		Label lblValor_2 = new Label(grpComisso, SWT.NONE);
		lblValor_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_2.setText("Valor");
		
		MoneyTextField moneyValorcomissao = new MoneyTextField(grpComisso);
		txtValorComissao = moneyValorcomissao.getControl();
		txtValorComissao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		
		Label lblPorcentagem = new Label(grpComisso, SWT.NONE);
		lblPorcentagem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPorcentagem.setText("Porcentagem");
		
		NumberTextField2 numberTextField = new NumberTextField2(grpComisso);
		Text txtPorcentagem = numberTextField.getControl();
		txtPorcentagem.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtPorcentagem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Text txtPorcentagem = (Text) e.widget;
				String texto = txtPorcentagem.getText();

				if(texto != null && !texto.isEmpty()){
					if(getCurrentObject().getValor() != null){
						Comissao c = (Comissao) valueComissao.getValue();
						BigDecimal p = FormatterHelper.getDefaultValueFormatterToMoney().parse(texto);
						
						BigDecimal valor = getCurrentObject().getValor().multiply(p).divide(new BigDecimal(100));
						c.setValor(valor);
						
						updateTargets();
					}
				}
			}
		});
		
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		
		createButtonAddItem(grpComisso, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addComissao(valueComissao);
			}
		});
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		
		CTabItem tbtmParcelas = new CTabItem(tfItens, SWT.NONE);
		tbtmParcelas.setText("Parcelas");
		
		Composite composite_4 = new Composite(tfItens, SWT.NONE);
		tbtmParcelas.setControl(composite_4);
		composite_4.setLayout(new GridLayout(2, false));
		
		Composite composite_5 = new Composite(composite_4, SWT.NONE);
		composite_5.setLayout(new GridLayout(1, false));
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));

		criarTabelaParcelas(composite_5);
		
		Button btnAddParcelas = new Button(composite_4, SWT.NONE);
		btnAddParcelas.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1));
		btnAddParcelas.setImage(ImageRepository.ADD_16.getImage());
		btnAddParcelas.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ParcelaDialog dialog = new ParcelaDialog(ShellHelper.getActiveShell(), getCurrentObject());
				if(dialog.open() == IDialogConstants.OK_ID){
					getCurrentObject().getParcelas().addAll(dialog.getParcelas());
					tvParcelas.refresh();
				}
			}
		});
		
		Button btnRemoveparcelas = new Button(composite_4, SWT.NONE);
		btnRemoveparcelas.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		btnRemoveparcelas.setImage(ImageRepository.REMOVE_16.getImage());
		btnRemoveparcelas.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				java.util.List<ContaPagarReceber> selecionados = SelectionHelper.getObjects((IStructuredSelection)tvParcelas.getSelection());
				getCurrentObject().getParcelas().removeAll(selecionados);
				tvParcelas.refresh();
			}
		});
		
		CTabItem tbtmVistoria = new CTabItem(tfItens, SWT.NONE);
		tbtmVistoria.setText("Vistorias");
		
		cpVistoria = new Composite(tfItens, SWT.NONE);
		tbtmVistoria.setControl(cpVistoria);
		cpVistoria.setLayout(new GridLayout(1, false));
		
		Composite cpCadVistoria = new Composite(cpVistoria, SWT.NONE);
		cpCadVistoria.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		criarTabelaVistoria(cpCadVistoria);
		
		CTabFolder tabFolder_1 = new CTabFolder(cpVistoria, SWT.BORDER);
		tabFolder_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		tabFolder_1.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmVistoria_1 = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmVistoria_1.setText("Vistoria");
		
		Composite cpAddVistoria = new Composite(tabFolder_1, SWT.NONE);
		tbtmVistoria_1.setControl(cpAddVistoria);
		GridLayout gl_grpVistoria = new GridLayout(4, false);
		cpAddVistoria.setLayout(gl_grpVistoria);
		
		Label lblData_2 = new Label(cpAddVistoria, SWT.NONE);
		lblData_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_2.setText("Data");
		
		DateTextField dateTextField_2 = new DateTextField(cpAddVistoria);
		txtDataVistoria = dateTextField_2.getControl();
		txtDataVistoria.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(cpAddVistoria, SWT.NONE);
		new Label(cpAddVistoria, SWT.NONE);
		
		Label lblFuncion = new Label(cpAddVistoria, SWT.NONE);
		lblFuncion.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncion.setText("Funcionário");
		
		txtFuncionarioVistoria = new Text(cpAddVistoria, SWT.BORDER);
		txtFuncionarioVistoria.setEditable(false);
		txtFuncionarioVistoria.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionarFuncionarioVistoria = new Button(cpAddVistoria, SWT.NONE);
		btnSelecionarFuncionarioVistoria.setImage(ImageRepository.SEARCH_16.getImage());
		
		Button btnt_2 = new Button(cpAddVistoria, SWT.NONE);
		btnt_2.setImage(ImageRepository.REMOVE_16.getImage());
		btnt_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionarFuncionarioVistoria, btnt_2, valueVistoria, "funcionario");
		
		Label lblObservaes_1 = new Label(cpAddVistoria, SWT.NONE);
		lblObservaes_1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes_1.setText("Observações");
		
		txtObservacoesVistoria = new Text(cpAddVistoria, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
//		gd_text_19.heightHint = 40;
		txtObservacoesVistoria.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 2));
		new Label(cpAddVistoria, SWT.NONE);
		
		CTabItem tbtmFotos = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmFotos.setText("Fotos");
		
		createPhotoComposite(tbtmFotos);
		
		CTabItem tbtmCheckList = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmCheckList.setText("Checklist");
		
		Composite composite_2 = new Composite(tabFolder_1, SWT.NONE);
		tbtmCheckList.setControl(composite_2);
		tvCheckListVistoria = criarTabelaCheckList(composite_2);
		
		createButtonAddItem(cpVistoria, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addVistoria(valueVistoria);
			}
		});
		
		tabFolder_1.setSelection(0);
		
		CTabItem tbtmChamada = new CTabItem(tfItens, SWT.NONE);
		tbtmChamada.setText("Chamados");
		
		cpChamados = new Composite(tfItens, SWT.NONE);
		tbtmChamada.setControl(cpChamados);
		cpChamados.setLayout(new GridLayout(1, false));
		
		Composite composite_7 = new Composite(cpChamados, SWT.NONE);
		composite_7.setLayout(new GridLayout(1, false));
//		gd_composite_7.heightHint = 95;
		composite_7.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		criarTabelaChamadosGerais(composite_7);
		
		Composite composite_9 = new Composite(cpChamados, SWT.NONE);
		composite_9.setLayout(new GridLayout(1, false));
		composite_9.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		CTabFolder tfChamado = new CTabFolder(composite_9, SWT.BORDER);
		GridData gd_tfChamado = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tfChamado.heightHint = 149;
		tfChamado.setLayoutData(gd_tfChamado);
		tfChamado.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmAbertura = new CTabItem(tfChamado, SWT.NONE);
		tbtmAbertura.setText("Abertura");
		
		Composite cpAberturaChamado = new Composite(tfChamado, SWT.NONE);
		tbtmAbertura.setControl(cpAberturaChamado);
		cpAberturaChamado.setLayout(new GridLayout(4, false));
		
		Label lblNmero_2 = new Label(cpAberturaChamado, SWT.NONE);
		lblNmero_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmero_2.setText("Número");
		
		txtIdChamado = new Text(cpAberturaChamado, SWT.BORDER);
		txtIdChamado.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtIdChamado.setEnabled(false);
		new Label(cpAberturaChamado, SWT.NONE);
		new Label(cpAberturaChamado, SWT.NONE);
		
		Label lblData_3 = new Label(cpAberturaChamado, SWT.NONE);
		lblData_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_3.setText("Data");
		
		DateTextField dateTextField_3 = new DateTextField(cpAberturaChamado);
		txtDataChamado = dateTextField_3.getControl();
		GridData gd_text_35 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_35.widthHint = 70;
		txtDataChamado.setLayoutData(gd_text_35);
		new Label(cpAberturaChamado, SWT.NONE);
		new Label(cpAberturaChamado, SWT.NONE);
		
		Label lblFuncionrio_2 = new Label(cpAberturaChamado, SWT.NONE);
		lblFuncionrio_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio_2.setText("Funcionário");
		
		txtFuncionarioReforma = new Text(cpAberturaChamado, SWT.BORDER);
		txtFuncionarioReforma.setEditable(false);
		txtFuncionarioReforma.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar_4 = new Button(cpAberturaChamado, SWT.NONE);
		btnSelecionar_4.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionar_4, valueChamado, "funcionario");
		
		Button btnt_4 = new Button(cpAberturaChamado, SWT.NONE);
		btnt_4.setImage(ImageRepository.REMOVE_16.getImage());
		btnt_4.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		ListElementDialogHelper.addSelectionToRemoveButton(btnt_4, valueChamado, "funcionario", Funcionario.class);
		
		Label lblDescrio = new Label(cpAberturaChamado, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio.setText("Descrição");
		
		txtProblemaReforma = new Text(cpAberturaChamado, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		txtProblemaReforma.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		new Label(cpAberturaChamado, SWT.NONE);
		new Label(cpAberturaChamado, SWT.NONE);
		new Label(cpAberturaChamado, SWT.NONE);
		
		createButtonAddItem(cpAberturaChamado, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addChamadoReforma(valueChamado);
			}
		});
		
		CTabItem tbAcontecimento = new CTabItem(tfChamado, SWT.NONE);
		tbAcontecimento.setText("Acontecimentos");
		
		Composite composite_12 = new Composite(tfChamado, SWT.NONE);
		tbAcontecimento.setControl(composite_12);
		composite_12.setLayout(new GridLayout(2, false));
		
		Composite composite_14 = new Composite(composite_12, SWT.NONE);
		composite_14.setLayout(new GridLayout(1, false));
		composite_14.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		criarTabelaAcontecimentosChamado(composite_14);
		
		Button btnAddAcontecimentoChamado = new Button(composite_12, SWT.NONE);
		btnAddAcontecimentoChamado.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1));
		btnAddAcontecimentoChamado.setImage(ImageRepository.ADD_16.getImage());
		btnAddAcontecimentoChamado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AcontecimentoChamadoDialog dialog = new AcontecimentoChamadoDialog();
				if(dialog.open() == IDialogConstants.OK_ID){
					((ChamadoReforma) valueChamado.getValue()).getAcontecimentos().add(dialog.getAcontecimentoChamado());
					tvAcontecimentoChamado.refresh();
				}
			}
		});
		
		Button btnRemoverAcontecimentoChamado = new Button(composite_12, SWT.NONE);
		btnRemoverAcontecimentoChamado.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		btnRemoverAcontecimentoChamado.setImage(ImageRepository.REMOVE_16.getImage());
		btnRemoverAcontecimentoChamado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AcontecimentoChamado acc = SelectionHelper.getObject(tvAcontecimentoChamado);
				((ChamadoReforma) valueChamado.getValue()).getAcontecimentos().remove(acc);
				tvAcontecimentoChamado.refresh();
			}
		});
		
		CTabItem tbtmFinalizar = new CTabItem(tfChamado, SWT.NONE);
		tbtmFinalizar.setText("Fechamento");
		
		Composite cpFinalizacaoChamadoReforma = new Composite(tfChamado, SWT.NONE);
		tbtmFinalizar.setControl(cpFinalizacaoChamadoReforma);
		cpFinalizacaoChamadoReforma.setLayout(new GridLayout(5, false));
		
		Label lblData_5 = new Label(cpFinalizacaoChamadoReforma, SWT.NONE);
		lblData_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_5.setText("Data");
		
		DateTextField dateTextField_4 = new DateTextField(cpFinalizacaoChamadoReforma);
		txtFinalizacaoReforma = dateTextField_4.getControl();
		GridData gd_text_36 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_36.widthHint = 76;
		txtFinalizacaoReforma.setLayoutData(gd_text_36);
		
		radiouGroupStatusReforma = new RadioGroupViewer(cpFinalizacaoChamadoReforma, SWT.NONE);
		radiouGroupStatusReforma.setContentProvider(ArrayContentProvider.getInstance());
		radiouGroupStatusReforma.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return element.equals(ChamadoReforma.ACEITO) ? "Aceito" : "Recusado";
			}
		});
		radiouGroupStatusReforma.setInput(new Object[]{ChamadoReforma.ACEITO, ChamadoReforma.RECUSADO});
		
		RadioGroup radioGroup = radiouGroupStatusReforma.getRadioGroup();
		radioGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		new Label(cpFinalizacaoChamadoReforma, SWT.NONE);
		new Label(cpFinalizacaoChamadoReforma, SWT.NONE);
		
		Label lblFuncionrio_4 = new Label(cpFinalizacaoChamadoReforma, SWT.NONE);
		lblFuncionrio_4.setText("Funcionário");
		
		txtFuncionarioFinalizacaoReforma = new Text(cpFinalizacaoChamadoReforma, SWT.BORDER);
		txtFuncionarioFinalizacaoReforma.setEditable(false);
		txtFuncionarioFinalizacaoReforma.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button btnSelecionar_6 = new Button(cpFinalizacaoChamadoReforma, SWT.NONE);
		btnSelecionar_6.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionar_6, valueFinalizacaoChamado, "funcionario");
		
		Button btnt_6 = new Button(cpFinalizacaoChamadoReforma, SWT.NONE);
		btnt_6.setImage(ImageRepository.REMOVE_16.getImage());
		btnt_6.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		ListElementDialogHelper.addSelectionToRemoveButton(btnt_6, valueFinalizacaoChamado, "funcionario", Funcionario.class);
		
		Label lblDescrio_2 = new Label(cpFinalizacaoChamadoReforma, SWT.NONE);
		lblDescrio_2.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio_2.setText("Descrição");
		
		txtDescricaoFinalReforma = new Text(cpFinalizacaoChamadoReforma, SWT.BORDER);
		txtDescricaoFinalReforma.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		new Label(cpFinalizacaoChamadoReforma, SWT.NONE);
		new Label(cpFinalizacaoChamadoReforma, SWT.NONE);
		
		Label lblValor_3 = new Label(cpFinalizacaoChamadoReforma, SWT.NONE);
		lblValor_3.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblValor_3.setText("Contas");
		
		tvContasFinalizacaoChamadoReforma = new TableViewer(cpFinalizacaoChamadoReforma, SWT.BORDER | SWT.FULL_SELECTION);
		tableContasFinalizacaoReforma = tvContasFinalizacaoChamadoReforma.getTable();
		tableContasFinalizacaoReforma.setLinesVisible(true);
		tableContasFinalizacaoReforma.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));
		
		TableViewerColumn tvcConta = new TableViewerColumn(tvContasFinalizacaoChamadoReforma, SWT.NONE);
		tvcConta.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				ContaPagarReceber conta = (ContaPagarReceber) element;
				return String.format("Conta a %s no valor de %s, com vencimento em %s - %s", conta.isApagar() ? "pagar" : "receber", 
						FormatterHelper.getCurrencyFormatter().format(conta.getValor()), FormatterHelper.getSimpleDateFormat().format(conta.getDataVencimento()), conta.getObservacoes());
			}
		});
		TableColumn tableColumn = tvcConta.getColumn();
		tableColumn.setWidth(550);
		
		Button btnAdicionarContaFinalizacaoReforma = new Button(cpFinalizacaoChamadoReforma, SWT.NONE);
		btnAdicionarContaFinalizacaoReforma.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1));
		btnAdicionarContaFinalizacaoReforma.setImage(ImageRepository.ADD_16.getImage());
		btnAdicionarContaFinalizacaoReforma.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					AdicionarContaPagarReformaDialog dialog = new AdicionarContaPagarReformaDialog(ShellHelper.getActiveShell());
					if(dialog.open() == IDialogConstants.OK_ID){
						FinalizacaoChamadoReforma fin = (FinalizacaoChamadoReforma) valueFinalizacaoChamado.getValue();
						fin.getContas().add(dialog.getConta());
					}
					tvContasFinalizacaoChamadoReforma.refresh();
				} catch (ParametroNaoInformadoException e1) {
					DialogHelper.openWarning(e1.getMessage());
				}
			}
		});
		
		new Label(cpFinalizacaoChamadoReforma, SWT.NONE);
		new Label(cpFinalizacaoChamadoReforma, SWT.NONE);
		
		Button btnRemoverContaFinalizacaoReforma = new Button(cpFinalizacaoChamadoReforma, SWT.NONE);
		btnRemoverContaFinalizacaoReforma.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		btnRemoverContaFinalizacaoReforma.setImage(ImageRepository.REMOVE_16.getImage());
		btnRemoverContaFinalizacaoReforma.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				ContaPagarReceber c = SelectionHelper.getObject(tvContasFinalizacaoChamadoReforma);
				
				if(c == null)
					return;
				
				if(c.isJaPagaRecebida()){
					DialogHelper.openWarning("A conta já foi paga/recebida, para exclui-la é necessário estorná-la.");
					return;
				}
				
				FinalizacaoChamadoReforma fin = (FinalizacaoChamadoReforma) valueFinalizacaoChamado.getValue();
				fin.getContas().remove(c);
				tvContasFinalizacaoChamadoReforma.refresh();
			}
		});
		
		createButtonAddItem(cpFinalizacaoChamadoReforma, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addFinalizacaoChamado(valueFinalizacaoChamado);
			}
		});
		
		{
			tfChamado.setSelection(0);
		}
		
		tfItens.setSelection(0);
		
		CTabItem tbtmCheckList_1 = new CTabItem(tfItens, SWT.NONE);
		tbtmCheckList_1.setText("Checklist");
		
		Composite composite_3 = new Composite(tfItens, SWT.NONE);
		tbtmCheckList_1.setControl(composite_3);
		tvCheckListAluguel = criarTabelaCheckList(composite_3);
	}

	protected void addChamadoReforma(WritableValue value) {
		ChamadoReforma chamadoReforma = (ChamadoReforma) value.getValue();
		
		if(chamadoReforma.getFinalizacao() != null && !validarComMensagem(chamadoReforma.getFinalizacao()))
			return;
		
		addItens(new ChamadoReformaService(), value, tvChamadoGeral, null);
		
		valueFinalizacaoChamado.setValue(new FinalizacaoChamadoReforma());
		tvAcontecimentoChamado.refresh();
	}
	
	private void createPhotoComposite(CTabItem tbtmFotos) {
		photoComposite = new PhotoComposite(tbtmFotos.getParent(), SWT.NONE, valueVistoria);
		tbtmFotos.setControl(photoComposite);
	}

	@Override
	protected void beforeSetIObservableValue(Aluguel obj) {
		limparTabelas(obj);
	}
	
	private void limparTabelas(Aluguel obj) {
		if(tvChamadoGeral == null)
			return;
		
		tvChamadoGeral.getTable().removeAll();
		tvComissao.getTable().removeAll();
		tvVistoria.getTable().removeAll();
	}
	
	@Override
	public void saveCurrentObject(GenericService<Aluguel> service) {
		if(!validarComMensagem(getCurrentObject()))
			return;
		
		try {
			if (!getCurrentObject().getContrato().getResolvido()) {
				getCurrentObject().getContrato().setResolvido(true);
				new ContratoPrestacaoServicoService().salvar(getCurrentObject().getContrato());
			}
		} catch (Exception e) {
			log.error("Erro ao setar resolvido ao contrato de prestação de servico.", e);
		}
		
		if(getCurrentObject().getId() == null && getCurrentObject().getParcelas().isEmpty()){
		
			ContaPagarReceberService contaService = new ContaPagarReceberService();
			ParametrosEmpresa parametros = ParametrosEmpresa.getInstance();
			AluguelService aluguelService = (AluguelService) service;
			
			if(parametros.getDiaRecebAluguel() != null && parametros.getDiaRepasseAluguel() != null){
				DateMidnight data = new DateMidnight(getCurrentObject().getDataAssinaturaContrato());
				DateMidnight dataVencimento = new DateMidnight(getCurrentObject().getDataVencimento());
				
				Months monthsBetween = Months.monthsBetween(data, dataVencimento);
	
				Calendar c = Calendar.getInstance();
				c.setTime(getCurrentObject().getDataAssinaturaContrato());
				c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
				c.set(Calendar.DAY_OF_MONTH, parametros.getDiaRecebAluguel());
				
				getCurrentObject().getParcelas().addAll(contaService.gerarParcelas(monthsBetween.getMonths(), getCurrentObject().getValor(), c.getTime(), ContaPagarReceber.RECEBER, 
						getCurrentObject().getDataGeracao(), aluguelService.geraObservacoesContaAluguel(getCurrentObject()), parametros.getTipoContaAluguel()));
	
				c.set(Calendar.DAY_OF_MONTH, parametros.getDiaRepasseAluguel());
				
				getCurrentObject().getParcelas().addAll(contaService.gerarParcelas(monthsBetween.getMonths(), getCurrentObject().getValor(), c.getTime(), ContaPagarReceber.PAGAR, 
						getCurrentObject().getDataGeracao(), aluguelService.geraObservacoesContaAluguel(getCurrentObject()), parametros.getTipoContaAluguel()));
				
				tvParcelas.refresh();
			}
		}
		
		super.saveCurrentObject(service);
	}

	protected void addFinalizacaoChamado(WritableValue value){
		// O Usuario deve salvar primeiro o chamado de reforma para depois finalizar
		if(((ChamadoReforma)valueChamado.getValue()).getId() == null){
			DialogHelper.openWarning("O chamado deve ser salvo primeiro antes de ser finalizado.");
			return;
		}
		
		if(validarComMensagem(valueFinalizacaoChamado.getValue()) && validarComMensagem(valueChamado.getValue())){
			((ChamadoReforma)valueChamado.getValue()).setFinalizacao((FinalizacaoChamadoReforma) valueFinalizacaoChamado.getValue());
			addItens(new ChamadoReformaService(), valueChamado, tvChamadoGeral, null);
			
			valueFinalizacaoChamado.setValue(new FinalizacaoChamadoReforma());
		}
	}
	
	@Override
	protected void afterSetIObservableValue(Aluguel aluguel) {
		valueComissao.setValue(new Comissao(getCurrentObject()));
		valueChamado.setValue(new ChamadoReforma(getCurrentObject()));
		valueVistoria.setValue(new Vistoria(getCurrentObject()));
		valueFinalizacaoChamado.setValue(new FinalizacaoChamadoReforma());
	}
	
	private void addComissao(WritableValue valueComissao) {
		ParametrosEmpresa parametrosEmpresa = ParametrosEmpresa.getInstance();
		if(parametrosEmpresa.getTipoContaComissao() == null){
			DialogHelper.openWarning("Não foi possivel encontrar o Tipo da Conta de Comissão nos Parâmetros da Empresa");
		}else{
			((Comissao)valueComissao.getValue()).setOrigem(parametrosEmpresa.getTipoContaComissao());
			addItens(new ComissaoService(), valueComissao, tvComissao, getCurrentObject().getComissoes());
		}
	}

	protected void addVistoria(WritableValue valueVistoria) {
		addItens(new VistoriaService(), valueVistoria, tvVistoria, getCurrentObject().getVistorias());
	}
	
	private void criarTabelaParcelas(Composite composite){
		TableViewerBuilder tvbParcela = new TableViewerBuilder(composite);
		
		tvbParcela.createColumn("Tipo").bindToProperty("tipoExtenso").build();
		tvbParcela.createColumn("Valor (R$)").bindToProperty("valor").format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
		tvbParcela.createColumn("Data de Vencimento").bindToProperty("dataVencimento").setPercentWidth(50).format(new DateStringValueFormatter()).build();
		
		tvParcelas = tvbParcela.getTableViewer();
		
	}
	
	private void criarTabelaComissao(Composite composite){
		TableViewerBuilder tvbComissao = new TableViewerBuilder(composite);
		
		tvbComissao.createColumn("Nome").bindToProperty("comissionado.nome").build();
		tvbComissao.createColumn("Valor (R$)").bindToProperty("valor").format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
		tvbComissao.createColumn("Data de Vencimento").bindToProperty("dataVencimento").setPercentWidth(50).format(new DateStringValueFormatter()).build();
		
		tvbComissao.setInput(getCurrentObject().getComissoes());
		
		WidgetHelper.addMenusToTable(tvbComissao, new ComissaoService(), valueComissao);
		
		tvComissao = tvbComissao.getTableViewer();
	}
	
	private void criarTabelaChamadosGerais(Composite composite){
		tvbChamadoGeral = new TableViewerBuilder(composite);
		
		tvbChamadoGeral.createColumn("Numero").bindToProperty("id").build();
		tvbChamadoGeral.createColumn("Data").bindToProperty("data").format(new DateStringValueFormatter()).build();
		tvbChamadoGeral.createColumn("Status").bindToProperty("status").format(new NullStringValueFormatter()).build();
		tvbChamadoGeral.createColumn("Problema").bindToProperty("problema").setPercentWidth(60).build();
		
		tvbChamadoGeral.setInput(getCurrentObject().getChamados());
		
		Table table = tvbChamadoGeral.getTable();
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		WidgetHelper.createMenuItemAlterar(menu, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChamadoReforma selecionado = (ChamadoReforma) SelectionHelper.getObject(tvbChamadoGeral.getTableViewer());
				
				if(selecionado == null)
					return;
				
				valueChamado.setValue(selecionado);
				valueFinalizacaoChamado.setValue(selecionado.getFinalizacao() != null ? selecionado.getFinalizacao() : new FinalizacaoChamadoReforma());
			}
		});
		
		WidgetHelper.createMenuItemRemover(menu, new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChamadoReforma chamadoReforma = SelectionHelper.getObject(tvChamadoGeral);
				if(chamadoReforma == null)
					return;
				if(chamadoReforma.getFinalizacao() != null && chamadoReforma.getFinalizacao().getContas() != null){
					for(ContaPagarReceber conta : chamadoReforma.getFinalizacao().getContas())
						if(conta.isJaPagaRecebida()){
							DialogHelper.openWarning("O chamado possui contas que já foram pagas/recebidas, para excluir é necessário estornar as contas primeiramente.");
							return;
						}
				}
				
				if(!DialogHelper.openConfirmation("Deseja excluir o chamado de reforma selecionado ?"))
					return;
				
				try {
					new ChamadoReformaService().removerAtivarOuDesativar(chamadoReforma);
					
					java.util.List<ChamadoReforma> input = (java.util.List<ChamadoReforma>) tvChamadoGeral.getInput();
					input.remove(chamadoReforma);
					tvChamadoGeral.refresh();
					
					valueChamado.setValue(new ChamadoReforma(getCurrentObject()));
					valueFinalizacaoChamado.setValue(new FinalizacaoChamadoReforma());
					
					DialogHelper.openInformation("Chamado de reforma removido com sucesso.");
				} catch (Exception e1) {
					log.error("Erro ao remover chamado de reforma.", e1);
					DialogHelper.openErrorMultiStatus("Erro ao excluir os chamados de reforma.", e1.getMessage());
				}
			}
		});
		
		tvChamadoGeral = tvbChamadoGeral.getTableViewer();
	}
	
	private void criarTabelaAcontecimentosChamado(Composite composite){
		tvbAcontecimentoChamado = new TableViewerBuilder(composite);
		
		tvbAcontecimentoChamado.createColumn("Data").bindToProperty("data").format(new TimeStringValueFormatter()).setPercentWidth(20).build();
		tvbAcontecimentoChamado.createColumn("Funcionário").bindToProperty("funcionario.nome").format(new NullStringValueFormatter()).setPercentWidth(20).build();
		tvbAcontecimentoChamado.createColumn("Descrição").bindToProperty("descricao").setPercentWidth(60).build();
		
		tvAcontecimentoChamado = tvbAcontecimentoChamado.getTableViewer();
		
		Table table = tvbAcontecimentoChamado.getTable();
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		WidgetHelper.createMenuItemRemover(menu, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AcontecimentoChamado acc = (AcontecimentoChamado) SelectionHelper.getObject(tvAcontecimentoChamado);
				((ChamadoReforma)valueChamado.getValue()).getAcontecimentos().remove(acc);
				tvAcontecimentoChamado.refresh();
			}
		});
	}
	
	private void criarTabelaVistoria(Composite composite){
		tvbVistoria = new TableViewerBuilder(composite);
		
		tvbVistoria.createColumn("Data da Vistoria").bindToProperty("data").format(new DateStringValueFormatter()).build();
		tvbVistoria.createColumn("Funcionário").setPercentWidth(20).bindToProperty("funcionario.nome").build();
		tvbVistoria.createColumn("Observações").setPercentWidth(60).bindToProperty("observacoes").build();
		
		tvbVistoria.setInput(getCurrentObject().getVistorias());
		
		WidgetHelper.addMenusToTable(tvbVistoria, new VistoriaService(), valueVistoria);
		
		tvVistoria = tvbVistoria.getTableViewer();
	}
	
	private TableViewer criarTabelaCheckList(Composite composite){
		return new CheckListService().criarTabela(composite);
	}
	
	@Override
	public GenericService<Aluguel> getService() {
		return service;
	}

	private DataBindingContext initBindTables(DataBindingContext bindingContext) {
		//
		tvCheckListAluguel.setContentProvider(new ObservableListContentProvider());
		tvCheckListAluguel.setInput(PojoProperties.list(VendaAluguel.class, "itensCheckList", ItemCheckList.class).observeDetail(value));
		//
		tvParcelas.setContentProvider(new ObservableListContentProvider());
		tvParcelas.setInput(PojoProperties.list(Aluguel.class, "parcelas", ContaPagarReceber.class).observeDetail(value));
		//
		tvCheckListVistoria.setContentProvider(new ObservableListContentProvider());
		tvCheckListVistoria.setInput(PojoProperties.list(Vistoria.class, "itensCheckList", ItemCheckList.class).observeDetail(valueVistoria));
		//
		tvAcontecimentoChamado.setContentProvider(new ObservableListContentProvider());
		tvAcontecimentoChamado.setInput(PojoProperties.list(ChamadoReforma.class, "acontecimentos", AcontecimentoChamado.class).observeDetail(valueChamado));
		//
		tvContasFinalizacaoChamadoReforma.setContentProvider(new ObservableListContentProvider());
		tvContasFinalizacaoChamadoReforma.setInput(PojoProperties.list(FinalizacaoChamadoReforma.class, "contas", Class.class).observeDetail(valueFinalizacaoChamado));
		//
		return bindingContext;
	}
	
	@Override
	protected Aluguel getNewValue() {
		Aluguel aluguel = new Aluguel();
		aluguel.carregarCheckList();
		return aluguel;
	}
	
	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.NONE).observe(text);
		IObservableValue valueContratonomeObserveDetailValue = PojoProperties.value(Aluguel.class, "contrato", ContratoPrestacaoServico.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueContratonomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtCliente);
		IObservableValue valueClientenomeObserveDetailValue = PojoProperties.value(Aluguel.class, "cliente.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueClientenomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_7ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtFuncionario);
		IObservableValue valueFuncionarionomeObserveDetailValue = PojoProperties.value(Aluguel.class, "funcionario.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_7ObserveWidget, valueFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_6ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtFiador);
		IObservableValue valueFiadorObserveDetailValue = PojoProperties.value(Aluguel.class, "fiador", Cliente.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_6ObserveWidget, valueFiadorObserveDetailValue, null, null);
		//
		IObservableValue observeTextModeloContratoObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtModeloContrato);
		IObservableValue valueModeloContratoObserveDetailValue = PojoProperties.value(Aluguel.class, "modeloContrato.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextModeloContratoObserveWidget, valueModeloContratoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataAssinaturaContrato);
		IObservableValue valueDataAssinaturaContratoObserveDetailValue = PojoProperties.value(Aluguel.class, "dataAssinaturaContrato", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_3ObserveWidget, valueDataAssinaturaContratoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_4ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataVencimento);
		IObservableValue valueDuracaoObserveDetailValue = PojoProperties.value(Aluguel.class, "dataVencimento", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_4ObserveWidget, valueDuracaoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtValor);
		IObservableValue valueValorObserveDetailValue = PojoProperties.value(Aluguel.class, "valor", BigDecimal.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		//
		IObservableValue observeSingleSelectionCvAjuste = ViewerProperties.singleSelection().observe(cvAjuste);
		IObservableValue valueVistoriaReajusteObserveDetailValue = PojoProperties.value(Aluguel.class, "reajuste", Indice.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionCvAjuste, valueVistoriaReajusteObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_DataVencimentobserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataVencimentoComissao);
		IObservableValue valueComissaoDataVencimentoObserveDetailValue = PojoProperties.value(Comissao.class, "dataVencimento", Date.class).observeDetail(valueComissao);
		bindingContext.bindValue(observeTextText_DataVencimentobserveWidget, valueComissaoDataVencimentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_8ObserveWidget = WidgetProperties.text(SWT.None).observe(txtComissionado);
		IObservableValue valueComissionadoComissaoObserveDetailValue = PojoProperties.value(Comissao.class, "comissionado", Comissionado.class).observeDetail(valueComissao);
		bindingContext.bindValue(observeTextText_8ObserveWidget, valueComissionadoComissaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_11ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtValorComissao);
		IObservableValue valueValorComissaoObserveDetailValue = PojoProperties.value(Comissao.class, "valor", BigDecimal.class).observeDetail(valueComissao);
		Binding bindValorComissao = bindingContext.bindValue(observeTextText_11ObserveWidget, valueValorComissaoObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		ControlDecorationSupport.create(bindValorComissao, SWT.LEFT | SWT.TOP);
		//
		IObservableValue observeTextText_34ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataVistoria);
		IObservableValue valueVistoriaDataObserveDetailValue = PojoProperties.value(Vistoria.class, "data", Date.class).observeDetail(valueVistoria);
		bindingContext.bindValue(observeTextText_34ObserveWidget, valueVistoriaDataObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_16ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtFuncionarioVistoria);
		IObservableValue valueVistoriaFuncionarionomeObserveDetailValue = PojoProperties.value(Vistoria.class, "funcionario.nome", String.class).observeDetail(valueVistoria);
		bindingContext.bindValue(observeTextText_16ObserveWidget, valueVistoriaFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_19ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtObservacoesVistoria);
		IObservableValue valueVistoriaObservacoesObserveDetailValue = PojoProperties.value(Vistoria.class, "observacoes", String.class).observeDetail(valueVistoria);
		bindingContext.bindValue(observeTextText_19ObserveWidget, valueVistoriaObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget_1 = WidgetProperties.text(SWT.Modify).observe(txtIdChamado);
		IObservableValue valueChamadoNumeroObserveDetailValue = PojoProperties.value(ChamadoReforma.class, "id", Long.class).observeDetail(valueChamado);
		bindingContext.bindValue(observeTextTextObserveWidget_1, valueChamadoNumeroObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_35ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataChamado);
		IObservableValue valueVistoriaDataAberturaObserveDetailValue = PojoProperties.value(ChamadoReforma.class, "data", Date.class).observeDetail(valueChamado);
		bindingContext.bindValue(observeTextText_35ObserveWidget, valueVistoriaDataAberturaObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_23ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtFuncionarioReforma);
		IObservableValue valueChamadoFuncionarioAberturaObserveDetailValue = PojoProperties.value(ChamadoReforma.class, "funcionario.nome", String.class).observeDetail(valueChamado);
		bindingContext.bindValue(observeTextText_23ObserveWidget, valueChamadoFuncionarioAberturaObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_24ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtProblemaReforma);
		IObservableValue valueProblemaObserveDetailValue = PojoProperties.value(ChamadoReforma.class, "problema", String.class).observeDetail(valueChamado);
		bindingContext.bindValue(observeTextText_24ObserveWidget, valueProblemaObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_36ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtFinalizacaoReforma);
		IObservableValue valueChamadoDataFechamentoObserveDetailValue = PojoProperties.value(FinalizacaoChamadoReforma.class, "data", Date.class).observeDetail(valueFinalizacaoChamado);
		bindingContext.bindValue(observeTextText_36ObserveWidget, valueChamadoDataFechamentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeSingleSelectionRadioGroupViewer = ViewerProperties.singleSelection().observe(radiouGroupStatusReforma);
		IObservableValue valueChaveLocalizacaoObserveDetailValue = PojoProperties.value(FinalizacaoChamadoReforma.class, "status", Integer.class).observeDetail(valueFinalizacaoChamado);
		bindingContext.bindValue(observeSingleSelectionRadioGroupViewer, valueChaveLocalizacaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_25ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtFuncionarioFinalizacaoReforma);
		IObservableValue valueChamadoFuncionarioFechamentonomeObserveDetailValue = PojoProperties.value(FinalizacaoChamadoReforma.class, "funcionario.nome", String.class).observeDetail(valueFinalizacaoChamado);
		bindingContext.bindValue(observeTextText_25ObserveWidget, valueChamadoFuncionarioFechamentonomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_31ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDescricaoFinalReforma);
		IObservableValue valueChamadoDescricaoConclusaoObserveDetailValue = PojoProperties.value(FinalizacaoChamadoReforma.class, "descricaoConclusao", String.class).observeDetail(valueFinalizacaoChamado);
		bindingContext.bindValue(observeTextText_31ObserveWidget, valueChamadoDescricaoConclusaoObserveDetailValue, null, null);
		//
		bindEnableControl(bindingContext);
		initBindTables(bindingContext);
		//
		return bindingContext;
	}
	
	private void bindEnableControl(DataBindingContext bindingContext){
		IObservableValue observeEnabledCpChamadoObserveWidget = WidgetProperties.enabled().observe(cpChamados);
		IObservableValue valueIdObserveDetailValue = PojoProperties.value(Aluguel.class, "id", Long.class).observeDetail(value);
		bindingContext.bindValue(observeEnabledCpChamadoObserveWidget, valueIdObserveDetailValue, null, UVSHelper.uvsLongIsNull());
		//		
		IObservableValue observeEnabledCpComissaoObserveWidget = WidgetProperties.enabled().observe(cpComissao);
		bindingContext.bindValue(observeEnabledCpComissaoObserveWidget, valueIdObserveDetailValue, null, UVSHelper.uvsLongIsNull());
		//
		IObservableValue observeEnabledCpVistoriaObserveWidget = WidgetProperties.enabled().observe(cpVistoria);
		bindingContext.bindValue(observeEnabledCpVistoriaObserveWidget, valueIdObserveDetailValue, null, UVSHelper.uvsLongIsNull());
		//
		bindingContext.bindValue(WidgetProperties.enabled().observe(photoComposite), PojoProperties.value(Vistoria.class, "id", Long.class).observeDetail(valueVistoria), null, UVSHelper.uvsLongIsNull());
		//
		List<Control> controls = Arrays.asList(dtAssinaturaContrato, dtVencimento, btnAddContrato, btnRemoveCliente, btnRemoveContrato, btnRemoveFuncionario, btnRemoverContrato, btnRemoverContrato, btnRemoverFiador, btnSelecFuncionario, btnSelecionarCliente, btnSelecionarContrato, btnSelecionarFiador, txtDataAssinaturaContrato, txtDataVencimento, txtValor, cvAjuste.getControl());
		for (Control control : controls) {
			bindingContext.bindValue(WidgetProperties.enabled().observe(control), PojoProperties.value(Aluguel.class, "id", Long.class).observeDetail(value), null, UVSHelper.uvsLongIsNotNull());
		}
	}
	
}
