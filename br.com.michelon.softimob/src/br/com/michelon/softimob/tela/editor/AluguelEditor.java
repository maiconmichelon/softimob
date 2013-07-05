package br.com.michelon.softimob.tela.editor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.jface.viewer.radiogroup.RadioGroupViewer;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.WidgetHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.AcontecimentoChamadoService;
import br.com.michelon.softimob.aplicacao.service.AluguelService;
import br.com.michelon.softimob.aplicacao.service.ChamadoReformaService;
import br.com.michelon.softimob.aplicacao.service.ComissaoService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.VistoriaService;
import br.com.michelon.softimob.modelo.AcontecimentoChamado;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.ChamadoReforma;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.Comissao;
import br.com.michelon.softimob.modelo.Comissionado;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.FinalizacaoChamadoReforma;
import br.com.michelon.softimob.modelo.ItemCheckList;
import br.com.michelon.softimob.modelo.Vistoria;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.dialog.AdicionarContaPagarReformaDialog;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.DateTimeTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import br.com.michelon.softimob.tela.widget.NullStringValueFormatter;
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
	private Text text_1;
	private Text text_4;
	private Text text_3;
	private Text text_2;
	private Text text_6;
	private Text text_7;
	private Text text_31;
	private Text text_25;
	private Text text_36;
	private Text text_10;
	private Text text_24;
	private Text text_23;
	private Text text_35;
	private Text text_19;
	private Text text_18;
	private Text text_16;
	private Text text_34;
	private Text text_27;
	private Text text_28;
	private Text text_8;
	private Text text_9;
	private Text text_11;
	private Text textChamado;
	private Text txtDataVencimentoComissao;

	private TableViewerBuilder tvbChamadoGeral;
	private TableViewerBuilder tvbAndamentoChamado;
	private TableViewerBuilder tvbVistoria;
	private TableViewerBuilder tvbCheckList;
	private TableViewer tvVistoria;
	private TableViewer tvAndamentoChamado;
	private TableViewer tvChamadoGeral;
	private TableViewer tvComissao;
	private TableViewer tvCheckList;

	private RadioGroupViewer radioGroupViewer;

	public AluguelEditor() {
		super(Aluguel.class);
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(6, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblImvel = new Label(parent, SWT.NONE);
		lblImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblImvel.setText("Imóvel");
		
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar = new Button(parent, SWT.NONE);
		btnSelecionar.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CONTRATO_SERVICO, btnSelecionar, value, "contrato");
		
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblValor = new Label(parent, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Locatário");
		
		text_1 = new Text(parent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(parent, SWT.NONE);
		button.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, button, value, "cliente");
		
		Label lblFuncionrio = new Label(parent, SWT.NONE);
		lblFuncionrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio.setText("Corretor");
		
		text_7 = new Text(parent, SWT.BORDER);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_2 = new Button(parent, SWT.NONE);
		button_2.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, button_2, value, "funcionario");
		
		Label lblFiador = new Label(parent, SWT.NONE);
		lblFiador.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFiador.setText("Fiador");
		
		text_6 = new Text(parent, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(parent, SWT.NONE);
		button_1.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, button_1, value, "fiador");
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblData = new Label(parent, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		DateTextField dateTextField = new DateTextField(parent);
		text_3 = dateTextField.getControl();
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblDurao = new Label(parent, SWT.NONE);
		lblDurao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDurao.setText("Duração");
		
		text_4 = new Text(parent, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		lblNewLabel.setText("meses");
		
		Label lblValor_1 = new Label(parent, SWT.NONE);
		lblValor_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_1.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(parent);
		text_2 = moneyTextField.getControl();
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
//		Label lblReajuste = new Label(parent, SWT.NONE);
//		lblReajuste.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		lblReajuste.setText("Reajuste");
//		
//		text_5 = new Text(parent, SWT.BORDER);
//		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
//		
//		Button btnIndice = new Button(parent, SWT.NONE);
//		btnIndice.setText("...");
//		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.INDICE, button_1, value, "");
		
		CTabFolder tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 6, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmComisso = new CTabItem(tabFolder, SWT.NONE);
		tbtmComisso.setText("Comissão");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmComisso.setControl(composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setLayout(new GridLayout(1, false));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		criarTabelaComissao(composite_1);

		Group grpComisso = new Group(composite, SWT.NONE);
		grpComisso.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpComisso.setText("Comissão");
		grpComisso.setLayout(new GridLayout(3, false));
		
		Label lblComissionado = new Label(grpComisso, SWT.NONE);
		lblComissionado.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblComissionado.setText("Comissionado");
		
		text_8 = new Text(grpComisso, SWT.BORDER);
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(grpComisso, SWT.NONE);
		btnNewButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnNewButton.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.COMISSIONADO, btnNewButton, valueComissao, "comissionado");
		
		Label lblDataDeVencimento = new Label(grpComisso, SWT.NONE);
		lblDataDeVencimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento.setText("Data de Vencimento");
		
		DateTextField dateTextField_1 = new DateTextField(grpComisso);
		txtDataVencimentoComissao = dateTextField_1.getControl();
		txtDataVencimentoComissao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpComisso, SWT.NONE);
		
		Label lblPorcentagem = new Label(grpComisso, SWT.NONE);
		lblPorcentagem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPorcentagem.setText("Porcentagem");
		
		text_9 = new Text(grpComisso, SWT.BORDER);
		text_9.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(grpComisso, SWT.NONE);
		
		Label lblValor_2 = new Label(grpComisso, SWT.NONE);
		lblValor_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_2.setText("Valor");
		
		text_11 = new Text(grpComisso, SWT.BORDER);
		text_11.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		new Label(grpComisso, SWT.NONE);
		
		Button btnAdicionar = new Button(grpComisso, SWT.NONE);
		btnAdicionar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnAdicionar.setText("Registrar");
		btnAdicionar.setImage(ImageRepository.ADD_16.getImage());
		btnAdicionar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addComissao(valueComissao);
			}
		});
		
		CTabItem tbtmVistoria = new CTabItem(tabFolder, SWT.NONE);
		tbtmVistoria.setText("Vistorias");
		
		Composite composite_8 = new Composite(tabFolder, SWT.NONE);
		tbtmVistoria.setControl(composite_8);
		composite_8.setLayout(new GridLayout(1, false));
		
		Composite cpVistoria = new Composite(composite_8, SWT.NONE);
		cpVistoria.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		criarTabelaVistoria(cpVistoria);
		
		CTabFolder tabFolder_1 = new CTabFolder(composite_8, SWT.BORDER);
		tabFolder_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		tabFolder_1.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmVistoria_1 = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmVistoria_1.setText("Vistoria");
		
		Composite cpAddVistoria = new Composite(tabFolder_1, SWT.NONE);
		tbtmVistoria_1.setControl(cpAddVistoria);
		GridLayout gl_grpVistoria = new GridLayout(3, false);
		cpAddVistoria.setLayout(gl_grpVistoria);
		
		Label lblData_2 = new Label(cpAddVistoria, SWT.NONE);
		lblData_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_2.setText("Data");
		
		DateTextField dateTextField_2 = new DateTextField(cpAddVistoria);
		text_34 = dateTextField_2.getControl();
		GridData gd_text_34 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_34.widthHint = 50;
		text_34.setLayoutData(gd_text_34);
		new Label(cpAddVistoria, SWT.NONE);
		
		Label lblFuncion = new Label(cpAddVistoria, SWT.NONE);
		lblFuncion.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncion.setText("Funcionário");
		
		text_16 = new Text(cpAddVistoria, SWT.BORDER);
		text_16.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionarFuncionarioVistoria = new Button(cpAddVistoria, SWT.NONE);
		btnSelecionarFuncionarioVistoria.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnSelecionarFuncionarioVistoria.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionarFuncionarioVistoria, valueVistoria, "funcionario");
		
		Label lblArquivo = new Label(cpAddVistoria, SWT.NONE);
		lblArquivo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblArquivo.setText("Arquivo");
		
		text_18 = new Text(cpAddVistoria, SWT.BORDER);
		text_18.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_123 = new Button(cpAddVistoria, SWT.NONE);
		button_123.setText("...");
		
		Label lblObservaes_1 = new Label(cpAddVistoria, SWT.NONE);
		lblObservaes_1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes_1.setText("Observações");
		
		text_19 = new Text(cpAddVistoria, SWT.BORDER);
		GridData gd_text_19 = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		gd_text_19.heightHint = 40;
		text_19.setLayoutData(gd_text_19);
		new Label(cpAddVistoria, SWT.NONE);
		
		CTabItem tbtmCheckList = new CTabItem(tabFolder_1, SWT.NONE);
		tbtmCheckList.setText("Check List");
		
		Composite composite_2 = new Composite(tabFolder_1, SWT.NONE);
		tbtmCheckList.setControl(composite_2);
		criarTabelaCheckList(composite_2);
		
		Button button_6 = new Button(composite_8, SWT.NONE);
		button_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		button_6.setText("Registrar");
		button_6.setImage(ImageRepository.SAVE_16.getImage());
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addVistoria(valueVistoria);
			}
		});
		
		CTabItem tbtmChamada = new CTabItem(tabFolder, SWT.NONE);
		tbtmChamada.setText("Chamados");
		
		Composite composite_6 = new Composite(tabFolder, SWT.NONE);
		tbtmChamada.setControl(composite_6);
		composite_6.setLayout(new GridLayout(1, false));
		
		Composite composite_7 = new Composite(composite_6, SWT.NONE);
		composite_7.setLayout(new GridLayout(1, false));
		GridData gd_composite_7 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite_7.heightHint = 120;
		composite_7.setLayoutData(gd_composite_7);
		
		criarTabelaChamadosGerais(composite_7);
		
		Composite composite_9 = new Composite(composite_6, SWT.NONE);
		composite_9.setLayout(new GridLayout(1, false));
		composite_9.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		CTabFolder tfChamado = new CTabFolder(composite_9, SWT.BORDER);
		tfChamado.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tfChamado.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmAbertura = new CTabItem(tfChamado, SWT.NONE);
		tbtmAbertura.setText("Abertura");
		
		Composite cpAberturaChamado = new Composite(tfChamado, SWT.NONE);
		tbtmAbertura.setControl(cpAberturaChamado);
		cpAberturaChamado.setLayout(new GridLayout(3, false));
		
		Label lblNmero_2 = new Label(cpAberturaChamado, SWT.NONE);
		lblNmero_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmero_2.setText("Número");
		
		textChamado = new Text(cpAberturaChamado, SWT.BORDER);
		textChamado.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		textChamado.setEnabled(false);
		new Label(cpAberturaChamado, SWT.NONE);
		
		Label lblData_3 = new Label(cpAberturaChamado, SWT.NONE);
		lblData_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_3.setText("Data");
		
		DateTextField dateTextField_3 = new DateTextField(cpAberturaChamado);
		text_35 = dateTextField_3.getControl();
		GridData gd_text_35 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_35.widthHint = 70;
		text_35.setLayoutData(gd_text_35);
		new Label(cpAberturaChamado, SWT.NONE);
		
		Label lblFuncionrio_2 = new Label(cpAberturaChamado, SWT.NONE);
		lblFuncionrio_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio_2.setText("Funcionário");
		
		text_23 = new Text(cpAberturaChamado, SWT.BORDER);
		text_23.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar_4 = new Button(cpAberturaChamado, SWT.NONE);
		btnSelecionar_4.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnSelecionar_4.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionar_4, valueChamado, "funcionario");
		
		Label lblDescrio = new Label(cpAberturaChamado, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio.setText("Descrição");
		
		text_24 = new Text(cpAberturaChamado, SWT.BORDER);
		text_24.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		new Label(cpAberturaChamado, SWT.NONE);
		new Label(cpAberturaChamado, SWT.NONE);
		
		Button button_7 = new Button(cpAberturaChamado, SWT.NONE);
		button_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		button_7.setText("Registrar");
		button_7.setImage(ImageRepository.SAVE_16.getImage());
		button_7.addSelectionListener(new SelectionAdapter() {
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
		composite_14.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 2));
		
		criarTabelaAndamentoChamado(composite_14);
		
		Label lblData_4 = new Label(composite_12, SWT.NONE);
		lblData_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_4.setText("Data");
		
		DateTimeTextField dateTimeTextField = new DateTimeTextField(composite_12);
		text_10 = dateTimeTextField.getControl();
		text_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_12, SWT.NONE);
		new Label(composite_12, SWT.NONE);
		
		Label lblFuncionrio_3 = new Label(composite_12, SWT.NONE);
		lblFuncionrio_3.setText("Funcionário");
		
		text_27 = new Text(composite_12, SWT.BORDER);
		GridData gd_text_27 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text_27.widthHint = 133;
		text_27.setLayoutData(gd_text_27);
		
		Button btnSelecionar_5 = new Button(composite_12, SWT.NONE);
		btnSelecionar_5.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnSelecionar_5.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionar_5, valueAcontecimentoChamado, "funcionario");
		new Label(composite_12, SWT.NONE);
		
		Label lblDescrio_1 = new Label(composite_12, SWT.NONE);
		lblDescrio_1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio_1.setText("Descrição");
		
		text_28 = new Text(composite_12, SWT.BORDER);
		text_28.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1));
		new Label(composite_12, SWT.NONE);
		new Label(composite_12, SWT.NONE);
		new Label(composite_12, SWT.NONE);
		
		Button button_8 = new Button(composite_12, SWT.NONE);
		button_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		button_8.setText("Registrar");
		button_8.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		button_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addAndamentoChamadoReforma(valueAcontecimentoChamado);
			}
		});
		
		CTabItem tbtmFinalizar = new CTabItem(tfChamado, SWT.NONE);
		tbtmFinalizar.setText("Fechamento");
		
		Composite composite_13 = new Composite(tfChamado, SWT.NONE);
		tbtmFinalizar.setControl(composite_13);
		composite_13.setLayout(new GridLayout(5, false));
		
		Label lblData_5 = new Label(composite_13, SWT.NONE);
		lblData_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_5.setText("Data");
		
		DateTextField dateTextField_4 = new DateTextField(composite_13);
		text_36 = dateTextField_4.getControl();
		GridData gd_text_36 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_36.widthHint = 76;
		text_36.setLayoutData(gd_text_36);
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		
		radioGroupViewer = new RadioGroupViewer(composite_13, SWT.NONE);
		radioGroupViewer.setContentProvider(ArrayContentProvider.getInstance());
		radioGroupViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return element.equals(ChamadoReforma.ACEITO) ? "Aceito" : "Recusado";
			}
		});
		radioGroupViewer.setInput(new Object[]{ChamadoReforma.ACEITO, ChamadoReforma.RECUSADO});
		
		RadioGroup radioGroup = radioGroupViewer.getRadioGroup();
		radioGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		
		Label lblFuncionrio_4 = new Label(composite_13, SWT.NONE);
		lblFuncionrio_4.setText("Funcionário");
		
		text_25 = new Text(composite_13, SWT.BORDER);
		text_25.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar_6 = new Button(composite_13, SWT.NONE);
		btnSelecionar_6.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionar_6, valueChamado, "funcionario");
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		
		Label lblDescrio_2 = new Label(composite_13, SWT.NONE);
		lblDescrio_2.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio_2.setText("Descrição");
		
		text_31 = new Text(composite_13, SWT.BORDER);
		text_31.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));
		new Label(composite_13, SWT.NONE);
		
		Label lblValor_3 = new Label(composite_13, SWT.NONE);
		lblValor_3.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblValor_3.setText("Contas");
		
		org.eclipse.swt.widgets.List list = new org.eclipse.swt.widgets.List(composite_13, SWT.BORDER);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 2, 2));
		
		Button btnAdicionar_1 = new Button(composite_13, SWT.NONE);
		btnAdicionar_1.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		btnAdicionar_1.setImage(ImageRepository.ADD_16.getImage());
		btnAdicionar_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new AdicionarContaPagarReformaDialog(ShellHelper.getActiveShell()).open();
			}
		});
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		
		Button btnFinalizar = new Button(composite_13, SWT.NONE);
		btnFinalizar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addFinalizacaoChamado(valueFinalizacaoChamado);
			}
		});
		btnFinalizar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnFinalizar.setText("Finalizar");
		btnFinalizar.setImage(ImageRepository.FINALIZAR_16.getImage());
		
		{
			tfChamado.setSelection(0);
		}
		
		tabFolder.setSelection(0);
		
		CTabItem tbtmCheckList_1 = new CTabItem(tabFolder, SWT.NONE);
		tbtmCheckList_1.setText("Check List");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmCheckList_1.setControl(composite_3);
		criarTabelaCheckList(composite_3);
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
	protected void afterSetIObservableValue() {
		valueComissao.setValue(new Comissao(getCurrentObject()));
		valueChamado.setValue(new ChamadoReforma(getCurrentObject()));
		valueVistoria.setValue(new Vistoria(getCurrentObject()));
		valueAcontecimentoChamado.setValue(new AcontecimentoChamado((ChamadoReforma) valueChamado.getValue()));
		valueFinalizacaoChamado.setValue(new FinalizacaoChamadoReforma());
	}
	
	private void addComissao(WritableValue valueComissao) {
		addItens(new ComissaoService(), valueComissao, tvComissao, getCurrentObject().getComissoes());
	}

	protected void addVistoria(WritableValue valueVistoria) {
		addItens(new VistoriaService(), valueVistoria, tvVistoria, getCurrentObject().getVistorias());
	}
	
	private void criarTabelaComissao(Composite composite){
		TableViewerBuilder tvbComissao = new TableViewerBuilder(composite);
		
		tvbComissao.createColumn("Nome").bindToProperty("comissionado.nome").build();
//		tvbComissao.createColumn("Valor ( % )").bindToValue(new IValue() {
//			//TODO AQUI TEM QUE RECALCULAR VALOR			
//			@Override
//			public void setValue(Object arg0, Object arg1) {
//				// TODO Auto-generated method stub
//			}
//			
//			@Override
//			public Object getValue(Object arg0) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		}).makeEditable().build();
		tvbComissao.createColumn("Valor (R$)").bindToProperty("valor").build();
		tvbComissao.createColumn("Data de Vencimento").bindToProperty("dataVencimento").format(new DateStringValueFormatter()).build();
		
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
//		tvbVistoria.createColumn("Fotos").bindToProperty("arquivo").build();
		tvbVistoria.createColumn("Observações").setPercentWidth(60).bindToProperty("observacoes").build();
		
		tvbVistoria.setInput(getCurrentObject().getVistorias());
		
		WidgetHelper.addMenusToTable(tvbVistoria, new VistoriaService(), valueVistoria);
		
		tvVistoria = tvbVistoria.getTableViewer();
	}
	
	private void criarTabelaCheckList(Composite composite){
		tvbCheckList = new TableViewerBuilder(composite);
		
		tvbCheckList.createColumn("Item").bindToProperty("item.nome").build();
		tvbCheckList.createColumn("Finalizado").setCustomLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return null;
			}
			
			@Override
			public Image getImage(Object element) {
				return ((ItemCheckList)element).getFinalizado() ? ImageRepository.CHECKED.getImage() : ImageRepository.UNCHECKED.getImage();
			}
		}).build();
		tvbCheckList.createColumn("Observações").bindToProperty("observacoes").setPercentWidth(80).build();
		
		tvCheckList = tvbCheckList.getTableViewer();
	}
	
	@Override
	public GenericService<Aluguel> getService() {
		return service;
	}

	private DataBindingContext initBindTables(DataBindingContext context) {
		//
		IObservableValue observeSingleSelectionTableViewerAndamento = ViewerProperties.input().observe(tvAndamentoChamado);
		IObservableValue valueObserveDetailValueAndamento = PojoProperties.value(ChamadoReforma.class, "acontecimentoChamado", List.class).observeDetail(valueChamado);
		context.bindValue(observeSingleSelectionTableViewerAndamento, valueObserveDetailValueAndamento, null, null);
		//
		IObservableValue observeSingleSelectionTableViewerChamado = ViewerProperties.input().observe(tvChamadoGeral);
		IObservableValue valueObserveDetailValueChamado = PojoProperties.value(Aluguel.class, "chamados", List.class).observeDetail(value);
		context.bindValue(observeSingleSelectionTableViewerChamado, valueObserveDetailValueChamado, null, null);
		//
		IObservableValue observeSingleSelectionTableViewerVistoria = ViewerProperties.input().observe(tvVistoria);
		IObservableValue valueObserveDetailValueVistoria = PojoProperties.value(Aluguel.class, "vistorias", List.class).observeDetail(value);
		context.bindValue(observeSingleSelectionTableViewerVistoria, valueObserveDetailValueVistoria, null, null);
		//
		IObservableValue observeSingleSelectionTableViewerComissao = ViewerProperties.input().observe(tvComissao);
		IObservableValue valueObserveDetailValueComissao = PojoProperties.value(Aluguel.class, "comissoes", List.class).observeDetail(value);
		context.bindValue(observeSingleSelectionTableViewerComissao, valueObserveDetailValueComissao, null, null);
		//
		return context;
	}
	
	@Override
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.NONE).observe(text);
		IObservableValue valueContratonomeObserveDetailValue = PojoProperties.value(Aluguel.class, "contrato", ContratoPrestacaoServico.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueContratonomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_1);
		IObservableValue valueClientenomeObserveDetailValue = PojoProperties.value(Aluguel.class, "cliente.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueClientenomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_7ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_7);
		IObservableValue valueFuncionarionomeObserveDetailValue = PojoProperties.value(Aluguel.class, "funcionario.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_7ObserveWidget, valueFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_6ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_6);
		IObservableValue valueFiadorObserveDetailValue = PojoProperties.value(Aluguel.class, "fiador", Cliente.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_6ObserveWidget, valueFiadorObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_3);
		IObservableValue valueDataAssinaturaContratoObserveDetailValue = PojoProperties.value(Aluguel.class, "dataAssinaturaContrato", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_3ObserveWidget, valueDataAssinaturaContratoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_4ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_4);
		IObservableValue valueDuracaoObserveDetailValue = PojoProperties.value(Aluguel.class, "duracao", Integer.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_4ObserveWidget, valueDuracaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_2);
		IObservableValue valueValorObserveDetailValue = PojoProperties.value(Aluguel.class, "valor", BigDecimal.class).observeDetail(value);
		Binding bindValor = bindingContext.bindValue(observeTextText_2ObserveWidget, valueValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		ControlDecorationSupport.create(bindValor, SWT.LEFT | SWT.TOP);
		//
//		IObservableValue observeTextText_5ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_5);
//		IObservableValue valueReajusteObserveDetailValue = PojoProperties.value(Aluguel.class, "reajuste", Integer.class).observeDetail(value);
//		bindingContext.bindValue(observeTextText_5ObserveWidget, valueReajusteObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_DataVencimentobserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataVencimentoComissao);
		IObservableValue valueComissaoDataVencimentoObserveDetailValue = PojoProperties.value(Comissao.class, "dataVencimento", Date.class).observeDetail(valueComissao);
		bindingContext.bindValue(observeTextText_DataVencimentobserveWidget, valueComissaoDataVencimentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_8ObserveWidget = WidgetProperties.text(SWT.None).observe(text_8);
		IObservableValue valueComissionadoComissaoObserveDetailValue = PojoProperties.value(Comissao.class, "comissionado", Comissionado.class).observeDetail(valueComissao);
		bindingContext.bindValue(observeTextText_8ObserveWidget, valueComissionadoComissaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_11ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_11);
		IObservableValue valueValorComissaoObserveDetailValue = PojoProperties.value(Comissao.class, "valor", BigDecimal.class).observeDetail(valueComissao);
		Binding bindValorComissao = bindingContext.bindValue(observeTextText_11ObserveWidget, valueValorComissaoObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		ControlDecorationSupport.create(bindValorComissao, SWT.LEFT | SWT.TOP);
		//
		IObservableValue observeTextText_34ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_34);
		IObservableValue valueVistoriaDataObserveDetailValue = PojoProperties.value(Vistoria.class, "data", Date.class).observeDetail(valueVistoria);
		bindingContext.bindValue(observeTextText_34ObserveWidget, valueVistoriaDataObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_16ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_16);
		IObservableValue valueVistoriaFuncionarionomeObserveDetailValue = PojoProperties.value(Vistoria.class, "funcionario.nome", String.class).observeDetail(valueVistoria);
		bindingContext.bindValue(observeTextText_16ObserveWidget, valueVistoriaFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_19ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_19);
		IObservableValue valueVistoriaObservacoesObserveDetailValue = PojoProperties.value(Vistoria.class, "observacoes", String.class).observeDetail(valueVistoria);
		bindingContext.bindValue(observeTextText_19ObserveWidget, valueVistoriaObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget_1 = WidgetProperties.text(SWT.Modify).observe(textChamado);
		IObservableValue valueChamadoNumeroObserveDetailValue = PojoProperties.value(ChamadoReforma.class, "id", Long.class).observeDetail(valueChamado);
		bindingContext.bindValue(observeTextTextObserveWidget_1, valueChamadoNumeroObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_35ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_35);
		IObservableValue valueVistoriaDataAberturaObserveDetailValue = PojoProperties.value(ChamadoReforma.class, "data", Date.class).observeDetail(valueChamado);
		bindingContext.bindValue(observeTextText_35ObserveWidget, valueVistoriaDataAberturaObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_23ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_23);
		IObservableValue valueChamadoFuncionarioAberturaObserveDetailValue = PojoProperties.value(ChamadoReforma.class, "funcionario.nome", String.class).observeDetail(valueChamado);
		bindingContext.bindValue(observeTextText_23ObserveWidget, valueChamadoFuncionarioAberturaObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_24ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_24);
		IObservableValue valueProblemaObserveDetailValue = PojoProperties.value(ChamadoReforma.class, "problema", String.class).observeDetail(valueChamado);
		bindingContext.bindValue(observeTextText_24ObserveWidget, valueProblemaObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_10ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_10);
		IObservableValue valueAcontecimentoChamadoDataObserveDetailValue = PojoProperties.value(AcontecimentoChamado.class, "data", Date.class).observeDetail(valueAcontecimentoChamado);
		bindingContext.bindValue(observeTextText_10ObserveWidget, valueAcontecimentoChamadoDataObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_27ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_27);
		IObservableValue valueAcontecimentoChamadoFuncionarionomeObserveDetailValue = PojoProperties.value(AcontecimentoChamado.class, "funcionario.nome", String.class).observeDetail(valueAcontecimentoChamado);
		bindingContext.bindValue(observeTextText_27ObserveWidget, valueAcontecimentoChamadoFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_28ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_28);
		IObservableValue valueAcontecimentoChamadoDescricaoObserveDetailValue = PojoProperties.value(AcontecimentoChamado.class, "descricao", String.class).observeDetail(valueAcontecimentoChamado);
		bindingContext.bindValue(observeTextText_28ObserveWidget, valueAcontecimentoChamadoDescricaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_36ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_36);
		IObservableValue valueChamadoDataFechamentoObserveDetailValue = PojoProperties.value(FinalizacaoChamadoReforma.class, "data", Date.class).observeDetail(valueFinalizacaoChamado);
		bindingContext.bindValue(observeTextText_36ObserveWidget, valueChamadoDataFechamentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeSingleSelectionRadioGroupViewer = ViewerProperties.singleSelection().observe(radioGroupViewer);
		IObservableValue valueChaveLocalizacaoObserveDetailValue = PojoProperties.value(FinalizacaoChamadoReforma.class, "status", Integer.class).observeDetail(valueFinalizacaoChamado);
		bindingContext.bindValue(observeSingleSelectionRadioGroupViewer, valueChaveLocalizacaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_25ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_25);
		IObservableValue valueChamadoFuncionarioFechamentonomeObserveDetailValue = PojoProperties.value(FinalizacaoChamadoReforma.class, "funcionario.nome", String.class).observeDetail(valueFinalizacaoChamado);
		bindingContext.bindValue(observeTextText_25ObserveWidget, valueChamadoFuncionarioFechamentonomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_31ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_31);
		IObservableValue valueChamadoDescricaoConclusaoObserveDetailValue = PojoProperties.value(FinalizacaoChamadoReforma.class, "descricaoConclusao", String.class).observeDetail(valueFinalizacaoChamado);
		bindingContext.bindValue(observeTextText_31ObserveWidget, valueChamadoDescricaoConclusaoObserveDetailValue, null, null);
		//
//		initBindTables(bindingContext);
		//
		return bindingContext;
	}
}
