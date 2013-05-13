package br.com.michelon.softimob.tela.editor;

import java.math.BigDecimal;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.ListElementDialogHelper;
import br.com.michelon.softimob.modelo.Chave;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.Feedback;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.modelo.Vistoria;
import de.ralfebert.rcputils.tables.TableViewerBuilder;
import de.ralfebert.rcputils.tables.format.Formatter;
import br.com.michelon.softimob.tela.widget.DateTextField;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import br.com.michelon.softimob.tela.widget.CEPTextField;
import br.com.michelon.softimob.tela.widget.DateTimeTextField;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ImovelEditor extends SoftimobEditor{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.ImovelEditor"; //$NON-NLS-1$
	
	private WritableValue value = WritableValue.withValueType(Imovel.class);
	private WritableValue valueProposta = WritableValue.withValueType(Proposta.class);
	private WritableValue valueHistorico = WritableValue.withValueType(Feedback.class);
	private WritableValue valueVistoria = WritableValue.withValueType(Vistoria.class);
	private WritableValue valueChave = WritableValue.withValueType(Chave.class);
	
	private Text txtProprietario;
	private Text text_2;
	private Text text_3;
	private Text text_5;
	private Text text_7;
	private Text text_8;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_14;
	private Text text_15;
	private Text text_16;
	private Text text_17;
	private Text text_18;
	private Text text_19;
	private Text text_20;
	private Text text_9;
	private Text text_21;
	private Text text_6;
	private ComboViewer comboViewer_4;
	private ComboViewer comboViewer_3;

	private TableViewerBuilder tvbChave;

	private TableViewerBuilder tvbHistorico;

	private TableViewerBuilder tvbVistoria;

	private TableViewerBuilder tvbProposta;

	private TableViewerBuilder tvbComodo;
	private Text text;
	private Text text_22;
	private Text text_23;
	private Text text_24;
	private Text text_27;
	private Text text_28;
	private Text text_31;

	private TableViewerBuilder tvbChamadoGeral;

	private TableViewerBuilder tvbAndamentoChamado;
	private Text text_32;
	private Text text_33;
	private Text text_34;
	private Text text_35;
	private Text text_36;
	private Text text_29;
	private Text text_1;
	private Text text_10;
	private Text text_25;
	private Text text_4;
	private Text text_26;
	private Text txtDescrio;
	
	public ImovelEditor() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite = new GridLayout(8, false);
		gl_composite.verticalSpacing = 6;
		composite.setLayout(gl_composite);
		
		Label lblCdigo = new Label(composite, SWT.NONE);
		lblCdigo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCdigo.setText("Código");
		
		text_21 = new Text(composite, SWT.BORDER);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblValor = new Label(composite, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(composite);
		text_4 = moneyTextField.getControl();
		GridData gd_text_4 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_4.widthHint = 101;
		text_4.setLayoutData(gd_text_4);
		new Label(composite, SWT.NONE);
		
		Label lblTipoImvel = new Label(composite, SWT.NONE);
		lblTipoImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipoImvel.setText("Tipo Imóvel");
		
		comboViewer_4 = new ComboViewer(composite, SWT.READ_ONLY);
		Combo combo_4 = comboViewer_4.getCombo();
		combo_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblProprietario = new Label(composite, SWT.NONE);
		lblProprietario.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProprietario.setText("Proprietário");
		
		txtProprietario = new Text(composite, SWT.BORDER);
		txtProprietario.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		ListElementDialogHelper.addListElementDialogToText(txtProprietario, value , "proprietario");
		
		Button btnSelecionar_7 = new Button(composite, SWT.NONE);
		btnSelecionar_7.setText("...");
		
		Label lblAngariad = new Label(composite, SWT.NONE);
		lblAngariad.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAngariad.setText("Angariador");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setText("...");
		
		Label lblMetragem = new Label(composite, SWT.NONE);
		lblMetragem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMetragem.setText("Metragem");
		
		text_5 = new Text(composite, SWT.BORDER);
		
		Label lblFotos = new Label(composite, SWT.NONE);
		lblFotos.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFotos.setText("Fotos");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Button btnSelecionar_8 = new Button(composite, SWT.NONE);
		btnSelecionar_8.setText("...");
		
		Label lblObservaes_3 = new Label(composite, SWT.NONE);
		lblObservaes_3.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes_3.setText("Observações");
		
		text_6 = new Text(composite, SWT.BORDER);
		GridData gd_text_6 = new GridData(SWT.FILL, SWT.CENTER, false, true, 5, 1);
		gd_text_6.heightHint = 32;
		text_6.setLayoutData(gd_text_6);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Composite composite_15 = new Composite(composite, SWT.NONE);
		composite_15.setLayout(new GridLayout(2, false));
		composite_15.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Button btnVenda = new Button(composite_15, SWT.CHECK);
		btnVenda.setText("Venda");
		
		Button btnAluguel = new Button(composite_15, SWT.CHECK);
		btnAluguel.setText("Aluguel");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		CTabFolder tfImovel = new CTabFolder(parent, SWT.BORDER);
		tfImovel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tfImovel.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmDescrio_1 = new CTabItem(tfImovel, SWT.NONE);
		tbtmDescrio_1.setText("Cômodos");
		
		Composite composite_5 = new Composite(tfImovel, SWT.NONE);
		tbtmDescrio_1.setControl(composite_5);
		composite_5.setLayout(new GridLayout(2, false));
				
		Composite composite_10 = new Composite(composite_5, SWT.NONE);
		composite_10.setLayout(new GridLayout(1, false));
		composite_10.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		
		criarTabelaComodo(composite_10);
		
		Button btnAdicionar = new Button(composite_5, SWT.NONE);
		btnAdicionar.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/novo/novo16.png"));
		btnAdicionar.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 1, 1));
		btnAdicionar.setText("Novo");
		
		Button btnRemover = new Button(composite_5, SWT.NONE);
		btnRemover.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/delete/delete16.png"));
		btnRemover.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1));
		btnRemover.setText("Remover");
		
		Group grpCmodo = new Group(composite_5, SWT.NONE);
		grpCmodo.setLayout(new GridLayout(3, false));
		grpCmodo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		grpCmodo.setText("Cômodo");
		
		Label lblCmodo = new Label(grpCmodo, SWT.NONE);
		lblCmodo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCmodo.setText("Cômodo");
		
		text_26 = new Text(grpCmodo, SWT.BORDER);
		text_26.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar_9 = new Button(grpCmodo, SWT.NONE);
		btnSelecionar_9.setText("...");
		
		Label lblDescrio_3 = new Label(grpCmodo, SWT.NONE);
		lblDescrio_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrio_3.setText("Descrição");
		
		txtDescrio = new Text(grpCmodo, SWT.BORDER);
		txtDescrio.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpCmodo, SWT.NONE);
		new Label(grpCmodo, SWT.NONE);
		new Label(grpCmodo, SWT.NONE);
		
		Button button_9 = new Button(grpCmodo, SWT.NONE);
		button_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		button_9.setText("Adicionar");
		button_9.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		
		CTabItem tbtmEndereo = new CTabItem(tfImovel, SWT.NONE);
		tbtmEndereo.setText("Endereço");
		
		Composite composite_1 = new Composite(tfImovel, SWT.NONE);
		tbtmEndereo.setControl(composite_1);
		GridLayout gl_composite_1 = new GridLayout(3, false);
		composite_1.setLayout(gl_composite_1);
		
		Label lblCep_1 = new Label(composite_1, SWT.NONE);
		lblCep_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCep_1.setText("CEP");
		
		CEPTextField textField = new CEPTextField(composite_1);
		text_1 = textField.getControl();
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 124;
		text_1.setLayoutData(gd_text_1);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblUf = new Label(composite_1, SWT.NONE);
		lblUf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUf.setText("UF");
		
		ComboViewer comboViewer = new ComboViewer(composite_1, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 41;
		combo.setLayoutData(gd_combo);
		new Label(composite_1, SWT.NONE);
		
		Label lblCidade = new Label(composite_1, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");
		
		ComboViewer comboViewer_1 = new ComboViewer(composite_1, SWT.READ_ONLY);
		Combo combo_1 = comboViewer_1.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Label lblBairro = new Label(composite_1, SWT.NONE);
		lblBairro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBairro.setText("Bairro");
		
		ComboViewer comboViewer_2 = new ComboViewer(composite_1, SWT.READ_ONLY);
		Combo combo_2 = comboViewer_2.getCombo();
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Label lblRua_1 = new Label(composite_1, SWT.NONE);
		lblRua_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRua_1.setText("Rua");
		
		comboViewer_3 = new ComboViewer(composite_1, SWT.READ_ONLY);
		Combo combo_3 = comboViewer_3.getCombo();
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Label lblNmero = new Label(composite_1, SWT.NONE);
		lblNmero.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmero.setText("Número");
		
		text_7 = new Text(composite_1, SWT.BORDER);
		GridData gd_text_7 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_7.widthHint = 98;
		text_7.setLayoutData(gd_text_7);
		new Label(composite_1, SWT.NONE);
		
		Label lblComplemento = new Label(composite_1, SWT.NONE);
		lblComplemento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblComplemento.setText("Complemento");
		
		text_8 = new Text(composite_1, SWT.BORDER);
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		CTabItem tbtmChaves = new CTabItem(tfImovel, SWT.NONE);
		tbtmChaves.setText("Chaves");
		
		Composite composite_2 = new Composite(tfImovel, SWT.NONE);
		tbtmChaves.setControl(composite_2);
		composite_2.setLayout(new GridLayout(2, false));
		
		Composite cpTvbChave = new Composite(composite_2, SWT.NONE);
		cpTvbChave.setLayout(new GridLayout(1, false));
		cpTvbChave.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		criarTabelaChave(cpTvbChave);
		
		Button btnNovo = new Button(composite_2, SWT.NONE);
		btnNovo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNovo.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/novo/novo16.png"));
		btnNovo.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 1, 1));
		btnNovo.setText("Novo");
		
		Button btnRemover_3 = new Button(composite_2, SWT.NONE);
		btnRemover_3.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/delete/delete16.png"));
		btnRemover_3.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1));
		btnRemover_3.setText("Remover");
		
		Group grpChave = new Group(composite_2, SWT.NONE);
		grpChave.setText("Chave");
		grpChave.setLayout(new GridLayout(3, false));
		grpChave.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblNmero_1 = new Label(grpChave, SWT.NONE);
		lblNmero_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmero_1.setText("Número");
		
		text_15 = new Text(grpChave, SWT.BORDER);
		text_15.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(grpChave, SWT.NONE);
		
		Button btnImobiliria = new Button(grpChave, SWT.RADIO);
		btnImobiliria.setText("Imobiliária");
		
		Button btnCliente = new Button(grpChave, SWT.RADIO);
		btnCliente.setText("Cliente");
		new Label(grpChave, SWT.NONE);
		new Label(grpChave, SWT.NONE);
		
		Button btnAddChave = new Button(grpChave, SWT.NONE);
		btnAddChave.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		btnAddChave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		GridData gd_btnAddChave = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnAddChave.heightHint = 30;
		gd_btnAddChave.widthHint = 84;
		btnAddChave.setLayoutData(gd_btnAddChave);
		btnAddChave.setText("Adicionar");
		
		CTabItem tbtmHistricos = new CTabItem(tfImovel, SWT.NONE);
		tbtmHistricos.setText("Históricos");
		
		Composite composite_3 = new Composite(tfImovel, SWT.NONE);
		tbtmHistricos.setControl(composite_3);
		composite_3.setLayout(new GridLayout(2, false));
		
		Composite cpTvbHistorico = new Composite(composite_3, SWT.NONE);
		cpTvbHistorico.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		cpTvbHistorico.setLayout(new GridLayout(1, false));
		
		criarTabelaHistorico(cpTvbHistorico);
		
		Button button_10 = new Button(composite_3, SWT.NONE);
		button_10.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 1, 1));
		button_10.setText("Novo");
		button_10.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/novo/novo16.png"));
		
		Button button_15 = new Button(composite_3, SWT.NONE);
		button_15.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		button_15.setText("Remover");
		button_15.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/delete/delete16.png"));
		
		Group grpNovoHistrico = new Group(composite_3, SWT.NONE);
		GridLayout gl_grpNovoHistrico = new GridLayout(3, false);
		grpNovoHistrico.setLayout(gl_grpNovoHistrico);
		grpNovoHistrico.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		grpNovoHistrico.setText("Histórico");
		
		Label lblData_1 = new Label(grpNovoHistrico, SWT.NONE);
		lblData_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_1.setText("Data");
		
		DateTextField dateTextField = new DateTextField(grpNovoHistrico);
		text_32 = dateTextField.getControl();
		GridData gd_text_32 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_32.widthHint = 73;
		text_32.setLayoutData(gd_text_32);
		new Label(grpNovoHistrico, SWT.NONE);
		
		Label lblFuncionrio_1 = new Label(grpNovoHistrico, SWT.NONE);
		lblFuncionrio_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio_1.setText("Funcionário");
		
		text_12 = new Text(grpNovoHistrico, SWT.BORDER);
		text_12.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar_2 = new Button(grpNovoHistrico, SWT.NONE);
		btnSelecionar_2.setText("...");
		
		Label lblClienteHistorico = new Label(grpNovoHistrico, SWT.NONE);
		lblClienteHistorico.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblClienteHistorico.setText("Cliente");
		
		text_14 = new Text(grpNovoHistrico, SWT.BORDER);
		text_14.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_4 = new Button(grpNovoHistrico, SWT.NONE);
		button_4.setText("...");
		
		Label lblObservaes = new Label(grpNovoHistrico, SWT.NONE);
		lblObservaes.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes.setText("Observações");
		
		text_13 = new Text(grpNovoHistrico, SWT.BORDER);
		GridData gd_text_13 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_13.heightHint = 51;
		text_13.setLayoutData(gd_text_13);
		new Label(grpNovoHistrico, SWT.NONE);
		new Label(grpNovoHistrico, SWT.NONE);
		
		Button button_5 = new Button(grpNovoHistrico, SWT.NONE);
		button_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		button_5.setText("Adicionar");
		button_5.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		
		CTabItem tbtmPropostas = new CTabItem(tfImovel, SWT.NONE);
		tbtmPropostas.setText("Propostas");
		
		Composite composite_4 = new Composite(tfImovel, SWT.NONE);
		tbtmPropostas.setControl(composite_4);
		composite_4.setLayout(new GridLayout(2, false));
		
		Composite cpTvbProposta = new Composite(composite_4, SWT.NONE);
		cpTvbProposta.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		criarTabelaProposta(cpTvbProposta);
		
		Button button_11 = new Button(composite_4, SWT.NONE);
		button_11.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 1, 1));
		button_11.setText("Novo");
		button_11.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/novo/novo16.png"));
		
		Button button_16 = new Button(composite_4, SWT.NONE);
		button_16.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		button_16.setText("Remover");
		button_16.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/delete/delete16.png"));
		
		Group grpProposta = new Group(composite_4, SWT.NONE);
		grpProposta.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		grpProposta.setText("Proposta");
		GridLayout gl_grpProposta = new GridLayout(3, false);
		grpProposta.setLayout(gl_grpProposta);
		
		Label lblData = new Label(grpProposta, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		DateTextField dateTextField_1 = new DateTextField(grpProposta);
		text_33 = dateTextField_1.getControl();
		GridData gd_text_33 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_33.widthHint = 55;
		text_33.setLayoutData(gd_text_33);
		new Label(grpProposta, SWT.NONE);
		
		Label lblCliente = new Label(grpProposta, SWT.NONE);
		lblCliente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCliente.setText("Cliente");
		
		text_9 = new Text(grpProposta, SWT.BORDER);
		text_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar_1 = new Button(grpProposta, SWT.NONE);
		btnSelecionar_1.setText("...");
		
		Label lblFuncionrio = new Label(grpProposta, SWT.NONE);
		lblFuncionrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio.setText("Funcionário");
		
		text_11 = new Text(grpProposta, SWT.BORDER);
		text_11.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Button button_2 = new Button(grpProposta, SWT.NONE);
		button_2.setText("...");
		
		Label lblValor_1 = new Label(grpProposta, SWT.NONE);
		lblValor_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_1.setText("Valor");
		
		MoneyTextField moneyTextField_1 = new MoneyTextField(grpProposta);
		text_29 = moneyTextField_1.getControl();
		GridData gd_text_29 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_29.widthHint = 114;
		text_29.setLayoutData(gd_text_29);
		new Label(grpProposta, SWT.NONE);
		
		Label lblObservaes_2 = new Label(grpProposta, SWT.NONE);
		lblObservaes_2.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes_2.setText("Observações");
		
		text_20 = new Text(grpProposta, SWT.BORDER);
		GridData gd_text_20 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_20.heightHint = 49;
		text_20.setLayoutData(gd_text_20);
		new Label(grpProposta, SWT.NONE);
		new Label(grpProposta, SWT.NONE);
		
		Button button_3 = new Button(grpProposta, SWT.NONE);
		button_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		button_3.setText("Adicionar");
		button_3.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		
		CTabItem tbtmVistoria = new CTabItem(tfImovel, SWT.NONE);
		tbtmVistoria.setText("Vistorias");
		
		Composite composite_8 = new Composite(tfImovel, SWT.NONE);
		tbtmVistoria.setControl(composite_8);
		composite_8.setLayout(new GridLayout(2, false));
		
		Composite cpVistoria = new Composite(composite_8, SWT.NONE);
		cpVistoria.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		
		criarTabelaVistoria(cpVistoria);
		
		Button button_12 = new Button(composite_8, SWT.NONE);
		button_12.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 1, 1));
		button_12.setText("Novo");
		button_12.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/novo/novo16.png"));
		
		Button button_17 = new Button(composite_8, SWT.NONE);
		button_17.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		button_17.setText("Remover");
		button_17.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/delete/delete16.png"));
		
		Group grpVistoria = new Group(composite_8, SWT.NONE);
		GridLayout gl_grpVistoria = new GridLayout(3, false);
		grpVistoria.setLayout(gl_grpVistoria);
		grpVistoria.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		grpVistoria.setText("Vistoria");
		
		Label lblData_2 = new Label(grpVistoria, SWT.NONE);
		lblData_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_2.setText("Data");
		
		DateTextField dateTextField_2 = new DateTextField(grpVistoria);
		text_34 = dateTextField_2.getControl();
		GridData gd_text_34 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_34.widthHint = 50;
		text_34.setLayoutData(gd_text_34);
		new Label(grpVistoria, SWT.NONE);
		
		Label lblFuncion = new Label(grpVistoria, SWT.NONE);
		lblFuncion.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncion.setText("Funcionário");
		
		text_16 = new Text(grpVistoria, SWT.BORDER);
		text_16.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar = new Button(grpVistoria, SWT.NONE);
		btnSelecionar.setText("...");
		
		Label lblInquilino = new Label(grpVistoria, SWT.NONE);
		lblInquilino.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInquilino.setText("Inquilino");
		
		text_17 = new Text(grpVistoria, SWT.BORDER);
		text_17.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(grpVistoria, SWT.NONE);
		button.setText("...");
		
		Label lblArquivo = new Label(grpVistoria, SWT.NONE);
		lblArquivo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblArquivo.setText("Arquivo");
		
		text_18 = new Text(grpVistoria, SWT.BORDER);
		text_18.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(grpVistoria, SWT.NONE);
		button_1.setText("...");
		
		Label lblObservaes_1 = new Label(grpVistoria, SWT.NONE);
		lblObservaes_1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes_1.setText("Observações");
		
		text_19 = new Text(grpVistoria, SWT.BORDER);
		GridData gd_text_19 = new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1);
		gd_text_19.heightHint = 54;
		text_19.setLayoutData(gd_text_19);
		new Label(grpVistoria, SWT.NONE);
		new Label(grpVistoria, SWT.NONE);
		
		Button button_6 = new Button(grpVistoria, SWT.NONE);
		button_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		button_6.setText("Adicionar");
		button_6.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		
		CTabItem tbtmChamada = new CTabItem(tfImovel, SWT.NONE);
		tbtmChamada.setText("Chamados");
		
		Composite composite_6 = new Composite(tfImovel, SWT.NONE);
		tbtmChamada.setControl(composite_6);
		composite_6.setLayout(new GridLayout(2, false));
		
		Composite composite_7 = new Composite(composite_6, SWT.NONE);
		composite_7.setLayout(new GridLayout(1, false));
		GridData gd_composite_7 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2);
		gd_composite_7.heightHint = 120;
		composite_7.setLayoutData(gd_composite_7);
		
		criarTabelaChamadosGerais(composite_7);
		
		Button button_13 = new Button(composite_6, SWT.NONE);
		button_13.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 1, 1));
		button_13.setText("Novo");
		button_13.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/novo/novo16.png"));
		
		Button button_14 = new Button(composite_6, SWT.NONE);
		button_14.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		button_14.setText("Remover");
		button_14.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/delete/delete16.png"));
		
		Composite composite_9 = new Composite(composite_6, SWT.NONE);
		composite_9.setLayout(new GridLayout(1, false));
		composite_9.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		CTabFolder tfChamado = new CTabFolder(composite_9, SWT.BORDER);
		tfChamado.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tfChamado.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmAbertura = new CTabItem(tfChamado, SWT.NONE);
		tbtmAbertura.setText("Abertura");
		
		Composite composite_11 = new Composite(tfChamado, SWT.NONE);
		tbtmAbertura.setControl(composite_11);
		composite_11.setLayout(new GridLayout(3, false));
		
		Label lblNmero_2 = new Label(composite_11, SWT.NONE);
		lblNmero_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmero_2.setText("Número");
		
		text = new Text(composite_11, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(composite_11, SWT.NONE);
		
		Label lblData_3 = new Label(composite_11, SWT.NONE);
		lblData_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_3.setText("Data");
		
		DateTextField dateTextField_3 = new DateTextField(composite_11);
		text_35 = dateTextField_3.getControl();
		GridData gd_text_35 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_35.widthHint = 70;
		text_35.setLayoutData(gd_text_35);
		new Label(composite_11, SWT.NONE);
		
		Label lblCliente_1 = new Label(composite_11, SWT.NONE);
		lblCliente_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCliente_1.setText("Cliente");
		
		text_22 = new Text(composite_11, SWT.BORDER);
		text_22.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Button btnSelecionar_3 = new Button(composite_11, SWT.NONE);
		btnSelecionar_3.setText("...");
		
		Label lblFuncionrio_2 = new Label(composite_11, SWT.NONE);
		lblFuncionrio_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio_2.setText("Funcionário");
		
		text_23 = new Text(composite_11, SWT.BORDER);
		text_23.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar_4 = new Button(composite_11, SWT.NONE);
		btnSelecionar_4.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnSelecionar_4.setText("...");
		
		Label lblDescrio = new Label(composite_11, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio.setText("Descrição");
		
		text_24 = new Text(composite_11, SWT.BORDER);
		GridData gd_text_24 = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_text_24.heightHint = 70;
		text_24.setLayoutData(gd_text_24);
		new Label(composite_11, SWT.NONE);
		new Label(composite_11, SWT.NONE);
		
		Button button_7 = new Button(composite_11, SWT.NONE);
		button_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		button_7.setText("Adicionar");
		button_7.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		
		CTabItem tbAndamento = new CTabItem(tfChamado, SWT.NONE);
		tbAndamento.setText("Andamento");
		
		Composite composite_12 = new Composite(tfChamado, SWT.NONE);
		tbAndamento.setControl(composite_12);
		composite_12.setLayout(new GridLayout(4, false));
		
		Composite composite_14 = new Composite(composite_12, SWT.NONE);
		composite_14.setLayout(new GridLayout(1, false));
		composite_14.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 2));
		
		criarTabelaAndamentoChamado(composite_14);
		
		Button button_18 = new Button(composite_12, SWT.NONE);
		button_18.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 1, 1));
		button_18.setText("Novo");
		button_18.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/novo/novo16.png"));
		
		Button button_19 = new Button(composite_12, SWT.NONE);
		button_19.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1));
		button_19.setText("Remover");
		button_19.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/delete/delete16.png"));
		
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
		button_8.setText("Adicionar");
		button_8.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		
		CTabItem tbtmFinalizar = new CTabItem(tfChamado, SWT.NONE);
		tbtmFinalizar.setText("Finalizar");
		
		Composite composite_13 = new Composite(tfChamado, SWT.NONE);
		tbtmFinalizar.setControl(composite_13);
		composite_13.setLayout(new GridLayout(3, false));
		
		Label lblData_5 = new Label(composite_13, SWT.NONE);
		lblData_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_5.setText("Data");
		
		DateTextField dateTextField_4 = new DateTextField(composite_13);
		text_36 = dateTextField_4.getControl();
		GridData gd_text_36 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_36.widthHint = 76;
		text_36.setLayoutData(gd_text_36);
		new Label(composite_13, SWT.NONE);
		
		Label lblFuncionrio_4 = new Label(composite_13, SWT.NONE);
		lblFuncionrio_4.setText("Funcionário");
		
		text_25 = new Text(composite_13, SWT.BORDER);
		text_25.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar_6 = new Button(composite_13, SWT.NONE);
		btnSelecionar_6.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnSelecionar_6.setText("...");
		new Label(composite_13, SWT.NONE);
		
		Composite composite_16 = new Composite(composite_13, SWT.NONE);
		composite_16.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		composite_16.setLayout(new GridLayout(2, false));
		
		Button btnAceito = new Button(composite_16, SWT.RADIO);
		btnAceito.setText("Aceito");
		
		Button btnRecusado = new Button(composite_16, SWT.RADIO);
		btnRecusado.setText("Recusado");
		new Label(composite_13, SWT.NONE);
		
		Label lblDescrio_2 = new Label(composite_13, SWT.NONE);
		lblDescrio_2.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio_2.setText("Descrição");
		
		text_31 = new Text(composite_13, SWT.BORDER);
		text_31.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		new Label(composite_13, SWT.NONE);
		new Label(composite_13, SWT.NONE);
		
		Button btnFechar = new Button(composite_13, SWT.NONE);
		btnFechar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnFechar.setText("Fechar");
		btnFechar.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/finalizar/finish16.png"));
		
		{
			tfImovel.setSelection(0);
			tfChamado.setSelection(0);
		}
		
		value.setValue(new Imovel());
		
//		initDataBindings();
	}
	
	private void criarTabelaChave(Composite composite){
		tvbChave = new TableViewerBuilder(composite);
		
		tvbChave.createColumn("Número").bindToProperty("numero").build();
	}
	
	private void criarTabelaComodo(Composite composite){
		tvbComodo = new TableViewerBuilder(composite);
		
		tvbComodo.createColumn("Cômodo").bindToProperty("tipoComodo.nome").build();
		tvbComodo.createColumn("Descrição").bindToProperty("descricao").makeEditable().build();
	}
	
	private void criarTabelaHistorico(Composite composite){
		tvbHistorico = new TableViewerBuilder(composite);
		
		tvbHistorico.createColumn("Data da Visita").bindToProperty("data").build();
		tvbHistorico.createColumn("Funcionário").bindToProperty("funcionario.nome").build();
		tvbHistorico.createColumn("Cliente").bindToProperty("cliente.nome").build();
		tvbHistorico.createColumn("Observações").setPercentWidth(60).bindToProperty("observacoes").build();
	}
	
	private void criarTabelaChamadosGerais(Composite composite){
		tvbChamadoGeral = new TableViewerBuilder(composite);
		
		tvbChamadoGeral.createColumn("Numero").bindToProperty("numero").build();
		tvbChamadoGeral.createColumn("Data").bindToProperty("dataAbertura").format(Formatter.forDate(FormatterHelper.getSimpleDateFormat())).build();
		tvbChamadoGeral.createColumn("Cliente").bindToProperty("inquilino.nome").build();
		tvbChamadoGeral.createColumn("Problema").bindToProperty("problema").build();
		tvbChamadoGeral.createColumn("Status").bindToProperty("status").build();
	}
	
	private void criarTabelaAndamentoChamado(Composite composite){
		tvbAndamentoChamado = new TableViewerBuilder(composite);
		
		tvbAndamentoChamado.createColumn("Data").bindToProperty("data").format(Formatter.forDate(FormatterHelper.getSimpleDateFormat())).build();
		tvbAndamentoChamado.createColumn("Funcionário").bindToProperty("funcionario.nome").build();
		tvbAndamentoChamado.createColumn("Descrição").bindToProperty("descricao").build();
	}
	
	private void criarTabelaVistoria(Composite composite){
		tvbVistoria = new TableViewerBuilder(composite);
		
		tvbVistoria.createColumn("Data da Vistoria").bindToProperty("data").build();
		tvbVistoria.createColumn("Funcionário").bindToProperty("funcionario.nome").build();
		tvbVistoria.createColumn("Inquilino").bindToProperty("inquilino.nome").build();
		tvbVistoria.createColumn("Fotos").bindToProperty("fotos").build();
		tvbVistoria.createColumn("Observações").setPercentWidth(60).bindToProperty("observacoes").build();
	}

	private void criarTabelaProposta(Composite composite){
		tvbProposta = new TableViewerBuilder(composite);
			
		tvbProposta.createColumn("Data de vistoria").bindToProperty("data").build();
		tvbProposta.createColumn("Valor").bindToProperty("valor").build();
		tvbProposta.createColumn("Cliente").bindToProperty("cliente.nome").build();
		tvbProposta.createColumn("Funcioário").bindToProperty("funcionario.nome").build();
		tvbProposta.createColumn("Observações").setPercentWidth(60).bindToProperty("observacoes").build();
	}

	@Override
	protected void salvar() {
		// TODO Auto-generated method stub
		
	}
}
