package br.com.michelon.softimob.tela.editor;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.jface.viewer.radiogroup.RadioGroupViewer;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.WidgetHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.AcontecimentoChamadoService;
import br.com.michelon.softimob.aplicacao.service.AluguelService;
import br.com.michelon.softimob.aplicacao.service.ChamadoReformaService;
import br.com.michelon.softimob.aplicacao.service.CheckListService;
import br.com.michelon.softimob.aplicacao.service.ComissaoService;
import br.com.michelon.softimob.aplicacao.service.ContaPagarReceberService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
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
import br.com.michelon.softimob.modelo.ItemCheckList;
import br.com.michelon.softimob.modelo.ModeloContrato;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.modelo.VendaAluguel;
import br.com.michelon.softimob.modelo.Vistoria;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.dialog.AdicionarContaPagarReformaDialog;
import br.com.michelon.softimob.tela.dialog.ParcelaDialog;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.DateTimeTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import br.com.michelon.softimob.tela.widget.NullStringValueFormatter;
import br.com.michelon.softimob.tela.widget.PhotoComposite;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class AluguelEditor extends GenericEditor<Aluguel>{
	public static final String ID = "br.com.michelon.softimob.tela.editor.AluguelEditor";
	
	private WritableValue valueVistoria = WritableValue.withValueType(Vistoria.class);
	private WritableValue valueChamado = WritableValue.withValueType(ChamadoReforma.class);
	private WritableValue valueAcontecimentoChamado = WritableValue.withValueType(AcontecimentoChamado.class);
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
	private Text txtDataAcontecimentoChamado;
	private Text txtProblemaReforma;
	private Text txtFuncionarioReforma;
	private Text txtDataChamado;
	private Text txtObservacoesVistoria;
	private Text text_18;
	private Text txtFuncionarioVistoria;
	private Text txtDataVistoria;
	private Text txtFuncionarioAndamentoReforma;
	private Text txtDescricaoAndamentoReforma;
	private Text txtComissionado;
	private Text txtModeloContrato;
	private Text txtValorComissao;
	private Text txtIdChamado;
	private Text txtDataVencimentoComissao;

	private TableViewerBuilder tvbChamadoGeral;
	private TableViewerBuilder tvbAndamentoChamado;
	private TableViewerBuilder tvbVistoria;
	private TableViewer tvVistoria;
	private TableViewer tvAndamentoChamado;
	private TableViewer tvChamadoGeral;
	private TableViewer tvComissao;
	private TableViewer tvCheckListAluguel;
	private TableViewer tvCheckListVistoria;
	private TableViewer tvParcelas;

	private RadioGroupViewer radiouGroupStatusReforma;
	private List listContasReforma;

	private CTabFolder tfItens;
	private Composite cpChamados;
	private Composite cpVistoria;

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
		
		Button btnSelecionar = new Button(parent, SWT.NONE);
		btnSelecionar.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CONTRATO_SERVICO_LOCACAO, btnSelecionar, value, "contrato");
		
		Button button_3 = new Button(parent, SWT.NONE);
		button_3.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_3, value, "contrato", ContratoPrestacaoServico.class);
		
		Label lblFuncionrio = new Label(parent, SWT.NONE);
		lblFuncionrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio.setText("Corretor");
		
		txtFuncionario = new Text(parent, SWT.BORDER);
		txtFuncionario.setEditable(false);
		txtFuncionario.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_2 = new Button(parent, SWT.NONE);
		button_2.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, button_2, value, "funcionario");
		
		Button btnt = new Button(parent, SWT.NONE);
		btnt.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnt, value, "funcionario", Funcionario.class);
		
		Label lblValor = new Label(parent, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Locatário");
		
		txtCliente = new Text(parent, SWT.BORDER);
		txtCliente.setEditable(false);
		txtCliente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(parent, SWT.NONE);
		button.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, button, value, "cliente");
		
		Button button_4 = new Button(parent, SWT.NONE);
		button_4.setImage(ImageRepository.REMOVE_16.getImage());
		button_4.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		ListElementDialogHelper.addSelectionToRemoveButton(button_4, value, "cliente", Cliente.class);
		
		Label lblFiador = new Label(parent, SWT.NONE);
		lblFiador.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFiador.setText("Fiador");
		
		txtFiador = new Text(parent, SWT.BORDER);
		txtFiador.setEditable(false);
		txtFiador.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(parent, SWT.NONE);
		button_1.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, button_1, value, "fiador");
		
		Button btnt_1 = new Button(parent, SWT.NONE);
		btnt_1.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnt_1, value, "fiador", Cliente.class);
		
		Label lblModeloContrato = new Label(parent, SWT.NONE);
		lblModeloContrato.setText("Modelo de Contrato");
		lblModeloContrato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtModeloContrato = new Text(parent, SWT.BORDER);
		txtModeloContrato.setEditable(false);
		txtModeloContrato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnAddContrato = new Button(parent, SWT.NONE);
		btnAddContrato.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.MODELO_CONTRATO, btnAddContrato, value, "modeloContrato");
		
		Button btnRemoverContrato = new Button(parent, SWT.NONE);
		btnRemoverContrato.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnRemoverContrato, value, "modeloContrato", ModeloContrato.class);
		
		Label lblReajuste = new Label(parent, SWT.NONE);
		lblReajuste.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblReajuste.setText("Reajuste");
		
		ComboViewer comboViewer = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Label lblData = new Label(parent, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		DateTextField dateTextField = new DateTextField(parent);
		dateTextField.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		txtDataAssinaturaContrato = dateTextField.getControl();
		txtDataAssinaturaContrato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDurao = new Label(parent, SWT.NONE);
		lblDurao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDurao.setText("Data de Vencimento");
		
		DateTextField dtVencimento = new DateTextField(parent);
		dtVencimento.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		txtDataVencimento = dtVencimento.getControl();
		txtDataVencimento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblValor_1 = new Label(parent, SWT.NONE);
		lblValor_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_1.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(parent);
		txtValor = moneyTextField.getControl();
		txtValor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		tfItens = new CTabFolder(parent, SWT.BORDER);
		tfItens.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 8, 1));
		tfItens.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmComisso = new CTabItem(tfItens, SWT.NONE);
		tbtmComisso.setText("Comissão");
		
		Composite composite = new Composite(tfItens, SWT.NONE);
		tbtmComisso.setControl(composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setLayout(new GridLayout(1, false));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		criarTabelaComissao(composite_1);

		Group grpComisso = new Group(composite, SWT.NONE);
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
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		
		createButtonAddItem(grpComisso, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addComissao(valueComissao);
			}
		});
		
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
		
		Label lblArquivo = new Label(cpAddVistoria, SWT.NONE);
		lblArquivo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblArquivo.setText("Arquivo");
		
		text_18 = new Text(cpAddVistoria, SWT.BORDER);
		text_18.setEditable(false);
		text_18.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_123 = new Button(cpAddVistoria, SWT.NONE);
		button_123.setImage(ImageRepository.SEARCH_16.getImage());
		
		Button btnt_3 = new Button(cpAddVistoria, SWT.NONE);
		btnt_3.setImage(ImageRepository.REMOVE_16.getImage());
		
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
		tbtmCheckList.setText("Check List");
		
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
		
		CTabItem tbAndamento = new CTabItem(tfChamado, SWT.NONE);
		tbAndamento.setText("Andamento");
		
		Composite composite_12 = new Composite(tfChamado, SWT.NONE);
		tbAndamento.setControl(composite_12);
		composite_12.setLayout(new GridLayout(4, false));
		
		Composite composite_14 = new Composite(composite_12, SWT.NONE);
		composite_14.setLayout(new GridLayout(1, false));
		composite_14.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		
		criarTabelaAndamentoChamado(composite_14);
		
		Label lblData_4 = new Label(composite_12, SWT.NONE);
		lblData_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_4.setText("Data");
		
		DateTimeTextField dateTimeTextField = new DateTimeTextField(composite_12);
		txtDataAcontecimentoChamado = dateTimeTextField.getControl();
		txtDataAcontecimentoChamado.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_12, SWT.NONE);
		new Label(composite_12, SWT.NONE);
		
		Label lblFuncionrio_3 = new Label(composite_12, SWT.NONE);
		lblFuncionrio_3.setText("Funcionário");
		
		txtFuncionarioAndamentoReforma = new Text(composite_12, SWT.BORDER);
		txtFuncionarioAndamentoReforma.setEditable(false);
		GridData gd_text_27 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text_27.widthHint = 133;
		txtFuncionarioAndamentoReforma.setLayoutData(gd_text_27);
		
		Button btnSelecionar_5 = new Button(composite_12, SWT.NONE);
		btnSelecionar_5.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionar_5, valueAcontecimentoChamado, "funcionario");
		
		Button btnt_5 = new Button(composite_12, SWT.NONE);
		btnt_5.setImage(ImageRepository.REMOVE_16.getImage());
		btnt_5.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		ListElementDialogHelper.addSelectionToRemoveButton(btnt_5, valueAcontecimentoChamado, "funcionario", Funcionario.class);
		
		Label lblDescrio_1 = new Label(composite_12, SWT.NONE);
		lblDescrio_1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio_1.setText("Descrição");
		
		txtDescricaoAndamentoReforma = new Text(composite_12, SWT.BORDER);
		txtDescricaoAndamentoReforma.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1));
		new Label(composite_12, SWT.NONE);
		new Label(composite_12, SWT.NONE);
		new Label(composite_12, SWT.NONE);
		
		createButtonAddItem(composite_12, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addAndamentoChamadoReforma(valueAcontecimentoChamado);
			}
		});
		
		CTabItem tbtmFinalizar = new CTabItem(tfChamado, SWT.NONE);
		tbtmFinalizar.setText("Fechamento");
		
		Composite composite_13 = new Composite(tfChamado, SWT.NONE);
		tbtmFinalizar.setControl(composite_13);
		composite_13.setLayout(new GridLayout(4, false));
		
		Label lblData_5 = new Label(composite_13, SWT.NONE);
		lblData_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_5.setText("Data");
		
		DateTextField dateTextField_4 = new DateTextField(composite_13);
		txtFinalizacaoReforma = dateTextField_4.getControl();
		GridData gd_text_36 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_36.widthHint = 76;
		txtFinalizacaoReforma.setLayoutData(gd_text_36);
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		
		radiouGroupStatusReforma = new RadioGroupViewer(composite_13, SWT.NONE);
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
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		
		Label lblFuncionrio_4 = new Label(composite_13, SWT.NONE);
		lblFuncionrio_4.setText("Funcionário");
		
		txtFuncionarioFinalizacaoReforma = new Text(composite_13, SWT.BORDER);
		txtFuncionarioFinalizacaoReforma.setEditable(false);
		txtFuncionarioFinalizacaoReforma.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar_6 = new Button(composite_13, SWT.NONE);
		btnSelecionar_6.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionar_6, valueFinalizacaoChamado, "funcionario");
		
		Button btnt_6 = new Button(composite_13, SWT.NONE);
		btnt_6.setImage(ImageRepository.REMOVE_16.getImage());
		btnt_6.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		ListElementDialogHelper.addSelectionToRemoveButton(btnt_6, valueFinalizacaoChamado, "funcionario", Funcionario.class);
		
		Label lblDescrio_2 = new Label(composite_13, SWT.NONE);
		lblDescrio_2.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio_2.setText("Descrição");
		
		txtDescricaoFinalReforma = new Text(composite_13, SWT.BORDER);
		txtDescricaoFinalReforma.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		
		Label lblValor_3 = new Label(composite_13, SWT.NONE);
		lblValor_3.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblValor_3.setText("Contas");
		
		listContasReforma = new org.eclipse.swt.widgets.List(composite_13, SWT.BORDER);
		listContasReforma.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		Button btnAdicionar_1 = new Button(composite_13, SWT.NONE);
		btnAdicionar_1.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		btnAdicionar_1.setImage(ImageRepository.ADD_16.getImage());
		btnAdicionar_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					AdicionarContaPagarReformaDialog dialog = new AdicionarContaPagarReformaDialog(ShellHelper.getActiveShell());
					if(dialog.open() == IDialogConstants.OK_ID){
						FinalizacaoChamadoReforma fin = (FinalizacaoChamadoReforma) valueFinalizacaoChamado.getValue();
						fin.getContas().add(dialog.getConta());
						listContasReforma.redraw();
					}
				} catch (ParametroNaoInformadoException e1) {
					DialogHelper.openWarning(e1.getMessage());
				}
			}
		});
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		
		createButtonAddItem(composite_13, new SelectionAdapter() {
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
		tbtmCheckList_1.setText("Check List");
		
		Composite composite_3 = new Composite(tfItens, SWT.NONE);
		tbtmCheckList_1.setControl(composite_3);
		tvCheckListAluguel = criarTabelaCheckList(composite_3);
	}

	private void createPhotoComposite(CTabItem tbtmFotos) {
		PhotoComposite photoComposite = new PhotoComposite(tbtmFotos.getParent(), SWT.NONE, valueVistoria);
		tbtmFotos.setControl(photoComposite);
	}

	@Override
	protected void beforeSetIObservableValue(Aluguel obj) {
		limparTabelas(obj);
	}
	
	private void limparTabelas(Aluguel obj) {
		if(tvAndamentoChamado == null)
			return;
		
		tvAndamentoChamado.getTable().removeAll();
		tvChamadoGeral.getTable().removeAll();
		tvComissao.getTable().removeAll();
		tvVistoria.getTable().removeAll();
	}
	
	@Override
	public void saveCurrentObject(GenericService<Aluguel> service) {
		java.util.List<ContaPagarReceber> parcelas = getCurrentObject().getParcelas();
		if(getCurrentObject().getId() == null && parcelas.isEmpty()){
			ContaPagarReceberService contaService = new ContaPagarReceberService();
			ParametrosEmpresa parametros = ParametrosEmpresa.getInstance();
			AluguelService aluguelService = (AluguelService) service;
			
//			contaService.gerarParcelas(null, getCurrentObject().getValor(), null, ContaPagarReceber.PAGAR, 
//					getCurrentObject().getDataGeracao(), aluguelService.geraObservacoesContaAluguel(getCurrentObject()), parametros.getTipoContaAluguel());
//			
//			contaService.gerarParcelas(null, getCurrentObject().getValor(), null, ContaPagarReceber.RECEBER, 
//					getCurrentObject().getDataGeracao(), aluguelService.geraObservacoesContaAluguel(getCurrentObject()), parametros.getTipoContaAluguel());
		}
		
		super.saveCurrentObject(service);
	}

	protected void addChamadoReforma(WritableValue value) {
		ChamadoReforma chamadoReforma = (ChamadoReforma) value.getValue();
		
		if(chamadoReforma.getFinalizacao() != null && !validarComMensagem(chamadoReforma.getFinalizacao()))
			return;
		
		addItens(new ChamadoReformaService(), value, tvChamadoGeral, null);
		
		valueAcontecimentoChamado.setValue(new AcontecimentoChamado((ChamadoReforma) valueChamado.getValue()));
		valueFinalizacaoChamado.setValue(new FinalizacaoChamadoReforma());
	}

	protected void addAndamentoChamadoReforma(WritableValue value){
		ChamadoReforma chamadoReforma = (ChamadoReforma)valueChamado.getValue();
		if(chamadoReforma.getId() == null){
			DialogHelper.openError("O chamado deve ser salvo primeiro antes de ser adicionado novos acontecimentos a ele.");
			return;
		}

		AcontecimentoChamado acontecimento = (AcontecimentoChamado) valueAcontecimentoChamado.getValue();

		if(validarComMensagem(acontecimento)){
			ChamadoReforma findOne = new ChamadoReformaService().findOne(chamadoReforma.getId());
			acontecimento.setChamadoReforma(findOne);
			addItens(new AcontecimentoChamadoService(), valueAcontecimentoChamado, tvAndamentoChamado, true, findOne);
		}
	}

	protected void addFinalizacaoChamado(WritableValue value){
		// O Usuario deve salvar primeiro o chamado de reforma para depois finalizar
		if(((ChamadoReforma)valueChamado.getValue()).getId() == null){
			DialogHelper.openError("O chamado deve ser salvo primeiro antes de ser finalizado.");
			return;
		}
		
		if(validarComMensagem(valueFinalizacaoChamado.getValue()) && validarComMensagem(valueChamado.getValue())){
			((ChamadoReforma)valueChamado.getValue()).setFinalizacao((FinalizacaoChamadoReforma) valueFinalizacaoChamado.getValue());
			addItens(new ChamadoReformaService(), valueChamado, tvChamadoGeral, null);
			
			valueFinalizacaoChamado.setValue(new FinalizacaoChamadoReforma());
			valueAcontecimentoChamado.setValue(new AcontecimentoChamado((ChamadoReforma) valueChamado.getValue()));
		}
	}
	
	@Override
	protected void afterSetIObservableValue(Aluguel aluguel) {
		valueComissao.setValue(new Comissao(getCurrentObject()));
		valueChamado.setValue(new ChamadoReforma(getCurrentObject()));
		valueVistoria.setValue(new Vistoria(getCurrentObject()));
		valueAcontecimentoChamado.setValue(new AcontecimentoChamado((ChamadoReforma) valueChamado.getValue()));
		valueFinalizacaoChamado.setValue(new FinalizacaoChamadoReforma());
	}
	
	private void addComissao(WritableValue valueComissao) {
		ParametrosEmpresa parametrosEmpresa = ParametrosEmpresa.getInstance();
		if(parametrosEmpresa.getTipoContaComissao() == null){
			DialogHelper.openWarning("Não foi possivel encontrar o tipo da conta de comissão nos Parâmetros da Empresa");
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
		
		tvComissao = tvbComissao.getTableViewer();
	}
	
	private void criarTabelaChamadosGerais(Composite composite){
		tvbChamadoGeral = new TableViewerBuilder(composite);
		
		tvbChamadoGeral.createColumn("Numero").bindToProperty("id").build();
		tvbChamadoGeral.createColumn("Data").bindToProperty("data").format(new DateStringValueFormatter()).build();
		tvbChamadoGeral.createColumn("Problema").bindToProperty("problema").build();
		tvbChamadoGeral.createColumn("Status").bindToProperty("status").format(new NullStringValueFormatter()).build();
		
		tvbChamadoGeral.setInput(getCurrentObject().getChamados());
		
		Table table = tvbChamadoGeral.getTable();
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		WidgetHelper.createMenuItemAlterar(menu, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChamadoReforma selecionado = (ChamadoReforma) SelectionHelper.getObject(tvbChamadoGeral.getTableViewer());
				valueChamado.setValue(selecionado);
				valueFinalizacaoChamado.setValue(selecionado.getFinalizacao() != null ? selecionado.getFinalizacao() : new FinalizacaoChamadoReforma());
				tvbAndamentoChamado.setInput(selecionado.getAcontecimentos());
			}
		});
		
		tvChamadoGeral = tvbChamadoGeral.getTableViewer();
	}
	
	private void criarTabelaAndamentoChamado(Composite composite){
		tvbAndamentoChamado = new TableViewerBuilder(composite);
		
		tvbAndamentoChamado.createColumn("Data").bindToProperty("data").format(new DateStringValueFormatter()).build();
		tvbAndamentoChamado.createColumn("Funcionário").bindToProperty("funcionario.nome").format(new NullStringValueFormatter()).build();
		tvbAndamentoChamado.createColumn("Descrição").bindToProperty("descricao").build();
		
		tvAndamentoChamado = tvbAndamentoChamado.getTableViewer();
	}
	
	private void criarTabelaVistoria(Composite composite){
		tvbVistoria = new TableViewerBuilder(composite);
		
		tvbVistoria.createColumn("Data da Vistoria").bindToProperty("data").format(new DateStringValueFormatter()).build();
		tvbVistoria.createColumn("Funcionário").bindToProperty("funcionario.nome").build();
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
//		IObservableValue observeTextText_5ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_5);
//		IObservableValue valueReajusteObserveDetailValue = PojoProperties.value(Aluguel.class, "reajuste", Integer.class).observeDetail(value);
//		bindingContext.bindValue(observeTextText_5ObserveWidget, valueReajusteObserveDetailValue, null, null);
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
		IObservableValue observeTextText_10ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataAcontecimentoChamado);
		IObservableValue valueAcontecimentoChamadoDataObserveDetailValue = PojoProperties.value(AcontecimentoChamado.class, "data", Date.class).observeDetail(valueAcontecimentoChamado);
		bindingContext.bindValue(observeTextText_10ObserveWidget, valueAcontecimentoChamadoDataObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_27ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtFuncionarioAndamentoReforma);
		IObservableValue valueAcontecimentoChamadoFuncionarionomeObserveDetailValue = PojoProperties.value(AcontecimentoChamado.class, "funcionario.nome", String.class).observeDetail(valueAcontecimentoChamado);
		bindingContext.bindValue(observeTextText_27ObserveWidget, valueAcontecimentoChamadoFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_28ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDescricaoAndamentoReforma);
		IObservableValue valueAcontecimentoChamadoDescricaoObserveDetailValue = PojoProperties.value(AcontecimentoChamado.class, "descricao", String.class).observeDetail(valueAcontecimentoChamado);
		bindingContext.bindValue(observeTextText_28ObserveWidget, valueAcontecimentoChamadoDescricaoObserveDetailValue, null, null);
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
		IObservableList itemsListObserveWidget = WidgetProperties.items().observe(listContasReforma);
		IObservableList valueFinalizacaoChamadoContasObserveDetailList = PojoProperties.list(FinalizacaoChamadoReforma.class, "contas", java.util.List.class).observeDetail(valueFinalizacaoChamado);
		bindingContext.bindList(itemsListObserveWidget, valueFinalizacaoChamadoContasObserveDetailList, null, null);
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
		IObservableValue observeEnabledCpVistoriaObserveWidget = WidgetProperties.enabled().observe(cpVistoria);
		bindingContext.bindValue(observeEnabledCpVistoriaObserveWidget, valueIdObserveDetailValue, null, UVSHelper.uvsLongIsNull());
	}
	
}
