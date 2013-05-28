package br.com.michelon.softimob.tela.editor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.jface.viewer.radiogroup.RadioGroupViewer;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.nebula.widgets.radiogroup.RadioItem;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.Images;
import org.eclipse.wb.swt.ResourceManager;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Chave;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.modelo.Estado;
import br.com.michelon.softimob.modelo.Feedback;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.modelo.Reserva;
import br.com.michelon.softimob.modelo.Rua;
import br.com.michelon.softimob.modelo.TipoComodo;
import br.com.michelon.softimob.modelo.TipoImovel;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.CEPTextField;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.DateTimeTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import de.ralfebert.rcputils.tables.TableViewerBuilder;
import de.ralfebert.rcputils.tables.format.Formatter;

public class ImovelEditor extends GenericEditor{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.ImovelEditor"; //$NON-NLS-1$
	
	private WritableValue value = WritableValue.withValueType(Imovel.class);
	private WritableValue valueProposta = WritableValue.withValueType(Proposta.class);
	private WritableValue valueFeedback = WritableValue.withValueType(Feedback.class);
	private WritableValue valueChave = WritableValue.withValueType(Chave.class);
	private WritableValue valueComodo = WritableValue.withValueType(TipoComodo.class);
	private WritableValue valueReserva = WritableValue.withValueType(Reserva.class);
	
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
	private Text text_20;
	private Text text_9;
	private Text text_6;
	private ComboViewer comboViewer_4;
	private ComboViewer cvRuas;

	private TableViewerBuilder tvbChave;
	private TableViewerBuilder tvbFeedbacks;
	private TableViewerBuilder tvbProposta;
	private TableViewerBuilder tvbComodo;
	private TableViewerBuilder tvbReserva;
	private TableViewerBuilder tvbLocacao;

	private TableViewer tvComodos;
	private TableViewer tvChaves;
	private TableViewer tvFeedbacks;
	private TableViewer tvProposta;
	private TableViewer tvReservas;
	private TableViewer tvLocacao;
	
	private Text text_32;
	private Text text_33;
	private Text text_1;
	private Text text_4;
	private Text text_26;
	private Text txtDescrio;
	private Text text_30;
	private Text text_37;
	private Text text_38;
	private Text text_40;
	private Text text_39;
	private Text text_41;
	private Text text_21;
	private Text text_29;
	private Text text_10;
	private Text text;
	private Text text_16;

	private ComboViewer cvCidades;

	private ComboViewer cvBairros;
	
	public ImovelEditor() {
		
		Imovel imovel = new Imovel();
		value.setValue(imovel);
		
		valueComodo.setValue(new Comodo());
		valueChave.setValue(new Chave(imovel));
		valueFeedback.setValue(new Feedback(imovel));
		valueProposta.setValue(new Proposta(imovel));
		valueReserva.setValue(new Reserva(imovel));
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
		text_21.setEditable(false);
		text_21.setEnabled(false);
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
		
		Button btnSelecionar_7 = new Button(composite, SWT.NONE);
		btnSelecionar_7.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, btnSelecionar_7, value, "proprietario");
		
		Label lblAngariad = new Label(composite, SWT.NONE);
		lblAngariad.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAngariad.setText("Angariador");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnNewButton, value, "angariador");
		
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
		
		Button btnSelecionarComodo = new Button(grpCmodo, SWT.NONE);
		btnSelecionarComodo.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.COMODO, btnSelecionarComodo, valueComodo, "tipoComodo");

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
		button_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addComodo();
			}
		});
		
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
		
		ComboViewer cvUF = new ComboViewer(composite_1, SWT.READ_ONLY);
		Combo combo = cvUF.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 41;
		combo.setLayoutData(gd_combo);
		cvUF.setContentProvider(ArrayContentProvider.getInstance());
		cvUF.addPostSelectionChangedListener(new ISelectionChangedListener(){
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Estado estado = (Estado) SelectionHelper.getObject((IStructuredSelection) event.getSelection());
				
				cvCidades.setInput(estado.getCidades());
			}
		});
		new Label(composite_1, SWT.NONE);
		
		Label lblCidade = new Label(composite_1, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");
		
		cvCidades = new ComboViewer(composite_1, SWT.READ_ONLY);
		Combo combo_1 = cvCidades.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cvCidades.addPostSelectionChangedListener(new ISelectionChangedListener(){
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Cidade cidade = (Cidade) SelectionHelper.getObject((IStructuredSelection) event.getSelection());
				
				cvBairros.setInput(cidade.getBairros());
			}
		});
		new Label(composite_1, SWT.NONE);
		
		Label lblBairro = new Label(composite_1, SWT.NONE);
		lblBairro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBairro.setText("Bairro");
		
		cvBairros = new ComboViewer(composite_1, SWT.READ_ONLY);
		Combo combo_2 = cvBairros.getCombo();
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cvBairros.addPostSelectionChangedListener(new ISelectionChangedListener(){
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Bairro bairro = (Bairro) SelectionHelper.getObject((IStructuredSelection) event.getSelection());
				
				cvRuas.setInput(bairro.getRuas());
			}
		});
		new Label(composite_1, SWT.NONE);
		
		Label lblRua_1 = new Label(composite_1, SWT.NONE);
		lblRua_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRua_1.setText("Rua");
		
		cvRuas = new ComboViewer(composite_1, SWT.READ_ONLY);
		Combo combo_3 = cvRuas.getCombo();
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
		text_15.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpChave, SWT.NONE);
		new Label(grpChave, SWT.NONE);
		
		RadioGroupViewer radioGroupViewer = new RadioGroupViewer(grpChave, SWT.BORDER);
		RadioGroup radioGroup = radioGroupViewer.getRadioGroup();
		radioGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		RadioItem riCliente = new RadioItem(radioGroup, SWT.NONE);
		riCliente.setText("Cliente");
		
		RadioItem riImobiliaria = new RadioItem(radioGroup, SWT.NONE);
		riImobiliaria.setText("Imobiliária");
		new Label(grpChave, SWT.NONE);
		new Label(grpChave, SWT.NONE);
		new Label(grpChave, SWT.NONE);
		
		Button btnAddChave = new Button(grpChave, SWT.NONE);
		btnAddChave.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		btnAddChave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addChave();
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
		
		criarTabelaFeedback(cpTvbHistorico);
		
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
		
		DateTimeTextField dateTextField = new DateTimeTextField(grpNovoHistrico);
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
		
		Button btnSelecionarFuncionarioFeedback = new Button(grpNovoHistrico, SWT.NONE);
		btnSelecionarFuncionarioFeedback.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionarFuncionarioFeedback, valueFeedback, "funcionario");
		
		Label lblClienteHistorico = new Label(grpNovoHistrico, SWT.NONE);
		lblClienteHistorico.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblClienteHistorico.setText("Cliente");
		
		text_14 = new Text(grpNovoHistrico, SWT.BORDER);
		text_14.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionarClienteFeedback = new Button(grpNovoHistrico, SWT.NONE);
		btnSelecionarClienteFeedback.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, btnSelecionarClienteFeedback, valueFeedback, "cliente");
		
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
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addFeedback();
			}
		});
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
		
		Button btnSelecionarClienteProposta = new Button(grpProposta, SWT.NONE);
		btnSelecionarClienteProposta.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, btnSelecionarClienteProposta, valueProposta, "cliente");
		
		Label lblFuncionrio = new Label(grpProposta, SWT.NONE);
		lblFuncionrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio.setText("Funcionário");
		
		text_11 = new Text(grpProposta, SWT.BORDER);
		text_11.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Button btnSelecionarFuncionarioProposta = new Button(grpProposta, SWT.NONE);
		btnSelecionarFuncionarioProposta.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionarFuncionarioProposta, valueProposta, "funcionario");
		
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
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addProposta();
			}
		});
		button_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		button_3.setText("Adicionar");
		button_3.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		
		{
			tfImovel.setSelection(0);
		}
		
		CTabItem tbtmReservas_1 = new CTabItem(tfImovel, SWT.NONE);
		tbtmReservas_1.setText("Reservas");
		
		Composite composite_17 = new Composite(tfImovel, SWT.NONE);
		tbtmReservas_1.setControl(composite_17);
		composite_17.setLayout(new GridLayout(3, false));
		
		Composite composite_18 = new Composite(composite_17, SWT.NONE);
		composite_18.setLayout(new GridLayout(1, false));
		composite_18.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		
		criarTabelaReservas(composite_18);
		
		Label lblData_6 = new Label(composite_17, SWT.NONE);
		lblData_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_6.setText("Data da Reserva");
		
		DateTextField dateTextField_5 = new DateTextField(composite_17);
		text_30 = dateTextField_5.getControl();
		GridData gd_text_30 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_30.widthHint = 79;
		text_30.setLayoutData(gd_text_30);
		new Label(composite_17, SWT.NONE);
		
		Label lblDataDeVencimento = new Label(composite_17, SWT.NONE);
		lblDataDeVencimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento.setText("Data de Vencimento");
		
		DateTextField dateTextField_6 = new DateTextField(composite_17);
		text_37 = dateTextField_6.getControl();
		GridData gd_text_37 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_37.widthHint = 79;
		text_37.setLayoutData(gd_text_37);
		new Label(composite_17, SWT.NONE);
		
		Label lblCliente_2 = new Label(composite_17, SWT.NONE);
		lblCliente_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCliente_2.setText("Cliente");
		
		text_38 = new Text(composite_17, SWT.BORDER);
		text_38.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_20 = new Button(composite_17, SWT.NONE);
		button_20.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_20.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, button_20, valueReserva, "cliente");
		
		Label lblCorretor = new Label(composite_17, SWT.NONE);
		lblCorretor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCorretor.setText("Corretor");
		
		text_40 = new Text(composite_17, SWT.BORDER);
		text_40.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_21 = new Button(composite_17, SWT.NONE);
		button_21.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, button_21, valueReserva, "funcionario");
		
		Label lblValor_2 = new Label(composite_17, SWT.NONE);
		lblValor_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_2.setText("Valor");
		
		MoneyTextField moneyTextField_2 = new MoneyTextField(composite_17);
		text_39 = moneyTextField_2.getControl();
		GridData gd_text_39 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_39.widthHint = 79;
		text_39.setLayoutData(gd_text_39);
		new Label(composite_17, SWT.NONE);
		
		Label lblDescrio_4 = new Label(composite_17, SWT.NONE);
		lblDescrio_4.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio_4.setText("Observações");
		
		text_41 = new Text(composite_17, SWT.BORDER);
		GridData gd_text_41 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_41.heightHint = 51;
		text_41.setLayoutData(gd_text_41);
		new Label(composite_17, SWT.NONE);
		new Label(composite_17, SWT.NONE);
		
		Button button_22 = new Button(composite_17, SWT.NONE);
		button_22.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addReserva();
			}
		});
		button_22.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		button_22.setText("Adicionar");
		button_22.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		
		CTabItem tbtmContratoPrestaoDe = new CTabItem(tfImovel, SWT.NONE);
		tbtmContratoPrestaoDe.setText("Contrato Prestação de Serviço");
		
		Composite composite_7 = new Composite(tfImovel, SWT.NONE);
		tbtmContratoPrestaoDe.setControl(composite_7);
		composite_7.setLayout(new GridLayout(3, false));
		
		Composite composite_8 = new Composite(composite_7, SWT.NONE);
		composite_8.setLayout(new GridLayout(1, false));
		composite_8.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		
		Label lblData_2 = new Label(composite_7, SWT.NONE);
		lblData_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_2.setText("Data");
		
		DateTextField dateTextField_2 = new DateTextField(composite_7);
		text = dateTextField_2.getControl();
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_7, SWT.NONE);
		
		Label lblDataDeVencimento_1 = new Label(composite_7, SWT.NONE);
		lblDataDeVencimento_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento_1.setText("Data de Vencimento");
		
		DateTextField dateTextField_3 = new DateTextField(composite_7);
		text_16 = dateTextField_3.getControl();
		text_16.setText("");
		text_16.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_7, SWT.NONE);
		
		Label lblFuncionrio_2 = new Label(composite_7, SWT.NONE);
		lblFuncionrio_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio_2.setText("Funcionário");
		
		text_10 = new Text(composite_7, SWT.BORDER);
		text_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(composite_7, SWT.NONE);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button.setText("...");
		new Label(composite_7, SWT.NONE);
		
		Composite composite_9 = new Composite(composite_7, SWT.NONE);
		composite_9.setLayout(new GridLayout(2, false));
		
		Button button_1 = new Button(composite_9, SWT.CHECK);
		button_1.setText("Venda");
		
		Button button_2 = new Button(composite_9, SWT.CHECK);
		button_2.setText("Locação");
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);
		
		Button btnAdicionar_1 = new Button(composite_7, SWT.NONE);
		btnAdicionar_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnAdicionar_1.setText("Adicionar");
		btnAdicionar_1.setImage(Images.ADD_16.getImage());
		
		CTabItem tbtmLocacoes = new CTabItem(tfImovel, SWT.NONE);
		tbtmLocacoes.setText("Locações");
		
		Composite composite_6 = new Composite(tfImovel, SWT.NONE);
		tbtmLocacoes.setControl(composite_6);
		
		criarTabelaLocacoes(composite_6);
		
		value.setValue(new Imovel());
		
		initDataBindings();
		
	}
	
	private void criarTabelaReservas(Composite composite) {
		tvbReserva = new TableViewerBuilder(composite);
		
		tvbReserva.createColumn("Data da Reserva").bindToProperty("dataReserva").format(Formatter.forDate(FormatterHelper.getSimpleDateFormat()));
		tvbReserva.createColumn("Data de Vencimento").bindToProperty("dataVencimento").format(Formatter.forDate(FormatterHelper.getSimpleDateFormat()));
		tvbReserva.createColumn("Cliente").bindToProperty("cliente.nome").build();
		tvbReserva.createColumn("Funcionário").bindToProperty("funcionario.nome").build();
		tvbReserva.createColumn("Valor").bindToProperty("valor").build();
		
		tvbReserva.setInput(((Imovel)value.getValue()).getReservas());
		
		tvReservas = tvbReserva.getTableViewer();
	}

	private void criarTabelaChave(Composite composite){
		tvbChave = new TableViewerBuilder(composite);
		
		tvbChave.createColumn("Número").bindToProperty("numero").build();
		
		tvbChave.setInput(((Imovel)value.getValue()).getChaves());
		
		tvChaves = tvbChave.getTableViewer();
	}
	
	private void criarTabelaComodo(Composite composite){
		tvbComodo = new TableViewerBuilder(composite);
		
		tvbComodo.createColumn("Cômodo").bindToProperty("tipoComodo.nome").build();
		tvbComodo.createColumn("Descrição").bindToProperty("descricao").makeEditable().build();
		
		tvbComodo.setInput(((Imovel)value.getValue()).getComodos());
		
		tvComodos = tvbComodo.getTableViewer();
	}
	
	private void criarTabelaFeedback(Composite composite){
		tvbFeedbacks = new TableViewerBuilder(composite);
		
		tvbFeedbacks.createColumn("Data da Visita").bindToProperty("data").format(Formatter.forDate(new SimpleDateFormat("dd/MM/yyyy hh:mm"))).build();
		tvbFeedbacks.createColumn("Funcionário").bindToProperty("funcionario.nome").build();
		tvbFeedbacks.createColumn("Cliente").bindToProperty("cliente.nome").build();
		tvbFeedbacks.createColumn("Observações").setPercentWidth(60).bindToProperty("observacoes").build();
		
		tvbFeedbacks.setInput(((Imovel)value.getValue()).getFeedbacks());
		
		tvFeedbacks = tvbFeedbacks.getTableViewer();
	}
	
	private void criarTabelaProposta(Composite composite){
		tvbProposta = new TableViewerBuilder(composite);
			
		tvbProposta.createColumn("Data da Proposta").bindToProperty("data").format(Formatter.forDate(FormatterHelper.getSimpleDateFormat())).build();
		tvbProposta.createColumn("Valor").bindToProperty("valor").build();
		tvbProposta.createColumn("Cliente").bindToProperty("cliente.nome").build();
		tvbProposta.createColumn("Funcioário").bindToProperty("funcionario.nome").build();
		tvbProposta.createColumn("Observações").setPercentWidth(60).bindToProperty("observacoes").build();
		
		tvbProposta.setInput(((Imovel)value.getValue()).getPropostas());
		
		tvProposta = tvbProposta.getTableViewer();
	}

	private void criarTabelaLocacoes(Composite composite) {
		tvbLocacao = new TableViewerBuilder(composite);
		
		tvLocacao = tvbLocacao.getTableViewer();
	}
	
	private void addChave() {
		Chave chave = (Chave) valueChave.getValue();
		Imovel imovel = (Imovel) value.getValue();
		
		imovel.getChaves().add(chave);
		valueChave.setValue(new Chave(imovel));
		
		tvChaves.refresh();
	}
	
	private void addProposta() {
		Proposta proposta = (Proposta) valueProposta.getValue();
		Imovel imovel = (Imovel) value.getValue();
		
		imovel.getPropostas().add(proposta);
		valueProposta.setValue(new Proposta(imovel));
		
		tvProposta.refresh();
	}
	
	private void addComodo() {
		Comodo comodo = (Comodo) valueComodo.getValue();
		Imovel imovel = (Imovel) value.getValue();
		
		imovel.getComodos().add(comodo);
		valueComodo.setValue(new Comodo());
		
		tvComodos.refresh();
	}
	
	private void addFeedback() {
		Feedback feedback = (Feedback) valueFeedback.getValue();
		Imovel imovel = (Imovel) value.getValue();
		
		imovel.getFeedbacks().add(feedback);
		valueFeedback.setValue(new Feedback(imovel));
		
		tvFeedbacks.refresh();
	}
	
	private void addReserva() {
		Reserva reserva = (Reserva) valueReserva.getValue();
		Imovel imovel = (Imovel) value.getValue();
		
		imovel.getReservas().add(reserva);
		valueReserva.setValue(new Reserva(imovel));
		
		tvReservas.refresh();
	}
	
	@Override
	protected void salvar() {
		System.out.println("hehe");
	}
	
	protected DataBindingContext bindTables(DataBindingContext bindingContext){
		//
		IObservableValue observeSingleSelectionTableViewerComodo = ViewerProperties.input().observe(tvComodos);
		IObservableValue valueObserveDetailValueComodo = PojoProperties.value(Imovel.class, "comodos", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTableViewerComodo, valueObserveDetailValueComodo, null, null);
		//
		IObservableValue observeSingleSelectionTableViewerChave = ViewerProperties.input().observe(tvChaves);
		IObservableValue valueComissoesObserveDetailValueChave = PojoProperties.value(Imovel.class, "chaves", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTableViewerChave, valueComissoesObserveDetailValueChave, null, null);
		//
		IObservableValue observeSingleSelectionTableViewerFeedback = ViewerProperties.input().observe(tvFeedbacks);
		IObservableValue valueComissoesObserveDetailValueFeedback = PojoProperties.value(Imovel.class, "feedbacks", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTableViewerFeedback, valueComissoesObserveDetailValueFeedback, null, null);
		//
		IObservableValue observeSingleSelectionTableViewerReserva = ViewerProperties.input().observe(tvReservas);
		IObservableValue valueComissoesObserveDetailValueReserva = PojoProperties.value(Imovel.class, "reservas", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTableViewerReserva, valueComissoesObserveDetailValueReserva, null, null);
		//
		IObservableValue observeSingleSelectionTableViewerProposta = ViewerProperties.input().observe(tvProposta);
		IObservableValue valueComissoesObserveDetailValueProposta = PojoProperties.value(Imovel.class, "propostas", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTableViewerProposta, valueComissoesObserveDetailValueProposta, null, null);
		//
		return bindingContext;
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_2);
		IObservableValue valueAngariadornomeObserveDetailValue = PojoProperties.value(Imovel.class, "angariador.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueAngariadornomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtProprietarioObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtProprietario);
		IObservableValue valueProprietarionomeObserveDetailValue = PojoProperties.value(Imovel.class, "proprietario.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtProprietarioObserveWidget, valueProprietarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_5ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_5);
		IObservableValue valueAngariadornomeObserveDetailValue_1 = PojoProperties.value(Imovel.class, "metragem", Integer.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_5ObserveWidget, valueAngariadornomeObserveDetailValue_1, null, null);
		//
		IObservableValue observeTextText_21ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_21);
		IObservableValue valueIdObserveDetailValue = PojoProperties.value(Imovel.class, "id", Long.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_21ObserveWidget, valueIdObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionComboViewer_4 = ViewerProperties.singleSelection().observe(comboViewer_4);
		IObservableValue valueMetragemObserveDetailValue = PojoProperties.value(Imovel.class, "tipo", TipoImovel.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer_4, valueMetragemObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_6ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_6);
		IObservableValue valueObservacoesObserveDetailValue = PojoProperties.value(Imovel.class, "observacoes", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_6ObserveWidget, valueObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_26ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_26);
		IObservableValue valueComodoTipoComodoObserveDetailValue = PojoProperties.value(Comodo.class, "tipoComodo", TipoComodo.class).observeDetail(valueComodo);
		bindingContext.bindValue(observeTextText_26ObserveWidget, valueComodoTipoComodoObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtDescrioObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDescrio);
		IObservableValue valueComodoDescricaoObserveDetailValue = PojoProperties.value(Comodo.class, "descricao", String.class).observeDetail(valueComodo);
		bindingContext.bindValue(observeTextTxtDescrioObserveWidget, valueComodoDescricaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueEnderecocepObserveDetailValue = PojoProperties.value(Imovel.class, "endereco.cep", Integer.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueEnderecocepObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionComboViewer_3 = ViewerProperties.singleSelection().observe(cvRuas);
		IObservableValue valueEnderecoruaObserveDetailValue = PojoProperties.value(Imovel.class, "endereco.rua", Rua.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer_3, valueEnderecoruaObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_7ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_7);
		IObservableValue valueEndereconumeroObserveDetailValue = PojoProperties.value(Imovel.class, "endereco.numero", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_7ObserveWidget, valueEndereconumeroObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_8ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_8);
		IObservableValue valueEnderecocomplementoObserveDetailValue = PojoProperties.value(Imovel.class, "endereco.complemento", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_8ObserveWidget, valueEnderecocomplementoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_15ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_15);
		IObservableValue valueChaveNumeroObserveDetailValue = PojoProperties.value(Chave.class, "numero", String.class).observeDetail(valueChave);
		bindingContext.bindValue(observeTextText_15ObserveWidget, valueChaveNumeroObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_32ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_32);
		IObservableValue valueFeedbackDataObserveDetailValue = PojoProperties.value(Feedback.class, "data", Date.class).observeDetail(valueFeedback);
		bindingContext.bindValue(observeTextText_32ObserveWidget, valueFeedbackDataObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_12ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_12);
		IObservableValue valueFeedbackFuncionarionomeObserveDetailValue = PojoProperties.value(Feedback.class, "funcionario.nome", String.class).observeDetail(valueFeedback);
		bindingContext.bindValue(observeTextText_12ObserveWidget, valueFeedbackFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_14ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_14);
		IObservableValue valueFeedbackClientenomeObserveDetailValue = PojoProperties.value(Feedback.class, "cliente.nome", String.class).observeDetail(valueFeedback);
		bindingContext.bindValue(observeTextText_14ObserveWidget, valueFeedbackClientenomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_13ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_13);
		IObservableValue valueFeedbackObservacoesObserveDetailValue = PojoProperties.value(Feedback.class, "observacoes", String.class).observeDetail(valueFeedback);
		bindingContext.bindValue(observeTextText_13ObserveWidget, valueFeedbackObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_9ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_9);
		IObservableValue valuePropostaClientenomeObserveDetailValue = PojoProperties.value(Proposta.class, "cliente.nome", String.class).observeDetail(valueProposta);
		bindingContext.bindValue(observeTextText_9ObserveWidget, valuePropostaClientenomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_11ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_11);
		IObservableValue valuePropostaFuncionarionomeObserveDetailValue = PojoProperties.value(Proposta.class, "funcionario.nome", String.class).observeDetail(valueProposta);
		bindingContext.bindValue(observeTextText_11ObserveWidget, valuePropostaFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_29ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_29);
		IObservableValue valuePropostaValorObserveDetailValue = PojoProperties.value(Proposta.class, "valor", BigDecimal.class).observeDetail(valueProposta);
		bindingContext.bindValue(observeTextText_29ObserveWidget, valuePropostaValorObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_20ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_20);
		IObservableValue valuePropostaObservacoesObserveDetailValue = PojoProperties.value(Proposta.class, "observacoes", String.class).observeDetail(valueProposta);
		bindingContext.bindValue(observeTextText_20ObserveWidget, valuePropostaObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_33ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_33);
		IObservableValue valuePropostaDataObserveDetailValue = PojoProperties.value(Proposta.class, "data", Date.class).observeDetail(valueProposta);
		bindingContext.bindValue(observeTextText_33ObserveWidget, valuePropostaDataObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_30ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_30);
		IObservableValue valueReservaDataAberturaObserveDetailValue = PojoProperties.value(Reserva.class, "dataAbertura", Date.class).observeDetail(valueReserva);
		bindingContext.bindValue(observeTextText_30ObserveWidget, valueReservaDataAberturaObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_37ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_37);
		IObservableValue valueReservaDataVencimentoObserveDetailValue = PojoProperties.value(Reserva.class, "dataVencimento", Date.class).observeDetail(valueReserva);
		bindingContext.bindValue(observeTextText_37ObserveWidget, valueReservaDataVencimentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_38ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_38);
		IObservableValue valueReservaClientenomeObserveDetailValue = PojoProperties.value(Reserva.class, "cliente.nome", String.class).observeDetail(valueReserva);
		bindingContext.bindValue(observeTextText_38ObserveWidget, valueReservaClientenomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_40ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_40);
		IObservableValue valuePropostaFuncionarionomeObserveDetailValue_1 = PojoProperties.value(Reserva.class, "funcionario.nome", String.class).observeDetail(valueReserva);
		bindingContext.bindValue(observeTextText_40ObserveWidget, valuePropostaFuncionarionomeObserveDetailValue_1, null, null);
		//
		IObservableValue observeTextText_39ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_39);
		IObservableValue valueReservaValorObserveDetailValue = PojoProperties.value(Reserva.class, "valor", BigDecimal.class).observeDetail(valueReserva);
		bindingContext.bindValue(observeTextText_39ObserveWidget, valueReservaValorObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_41ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_41);
		IObservableValue valueReservaObservacoesObserveDetailValue = PojoProperties.value(Reserva.class, "observacoes", String.class).observeDetail(valueReserva);
		bindingContext.bindValue(observeTextText_41ObserveWidget, valueReservaObservacoesObserveDetailValue, null, null);
		//
		bindTables(bindingContext);
		//
		return bindingContext;
	}
}
