package br.com.michelon.softimob.tela.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.DateTimeTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import de.ralfebert.rcputils.properties.IValue;
import de.ralfebert.rcputils.tables.TableViewerBuilder;
import de.ralfebert.rcputils.tables.format.Formatter;

public class AluguelEditor extends GenericEditor{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.AluguelEditor";
	
	private TableViewerBuilder tvbComissao;

	private Text text;
	private Text text_1;
	private Text text_4;
	private Text text_3;
	private Text text_2;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_31;
	private Text text_25;
	private Text text_36;
	private Text text_10;
	private Text text_24;
	private Text text_23;
	private Text text_22;
	private Text text_35;
	private Text text_19;
	private Text text_18;
	private Text text_17;
	private Text text_16;
	private Text text_34;
	private TableViewerBuilder tvbChamadoGeral;
	private TableViewerBuilder tvbAndamentoChamado;
	private TableViewerBuilder tvbVistoria;
	private Text text_27;
	private Text text_28;
	
	public AluguelEditor() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(4, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblImvel = new Label(parent, SWT.NONE);
		lblImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblImvel.setText("Imóvel");
		
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button btnSelecionar = new Button(parent, SWT.NONE);
		btnSelecionar.setText("...");
		
		Label lblValor = new Label(parent, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Locatário");
		
		text_1 = new Text(parent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button button = new Button(parent, SWT.NONE);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button.setText("...");
		
		Label lblFuncionrio = new Label(parent, SWT.NONE);
		lblFuncionrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio.setText("Corretor");
		
		text_7 = new Text(parent, SWT.BORDER);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button button_2 = new Button(parent, SWT.NONE);
		button_2.setText("...");
		
		Label lblFiador = new Label(parent, SWT.NONE);
		lblFiador.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFiador.setText("Fiador");
		
		text_6 = new Text(parent, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button button_1 = new Button(parent, SWT.NONE);
		button_1.setText("...");
		
		Label lblValor_1 = new Label(parent, SWT.NONE);
		lblValor_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_1.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(parent);
		text_2 = moneyTextField.getControl();
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblData = new Label(parent, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		DateTextField dateTextField = new DateTextField(parent);
		text_3 = dateTextField.getControl();
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblDurao = new Label(parent, SWT.NONE);
		lblDurao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDurao.setText("Duração");
		
		text_4 = new Text(parent, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		lblNewLabel.setText("meses");
		new Label(parent, SWT.NONE);
		
		Label lblReajuste = new Label(parent, SWT.NONE);
		lblReajuste.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblReajuste.setText("Reajuste");
		
		text_5 = new Text(parent, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label = new Label(parent, SWT.NONE);
		label.setText("%");
		label.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		new Label(parent, SWT.NONE);
		
		CTabFolder tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmComisso = new CTabItem(tabFolder, SWT.NONE);
		tbtmComisso.setText("Comissão");
		
		Group grpComisso = new Group(tabFolder, SWT.NONE);
		tbtmComisso.setControl(grpComisso);
		grpComisso.setText("Comissão");
		grpComisso.setLayout(new GridLayout(2, false));
		
		Composite composite = new Composite(grpComisso, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		
		criarTabelaComissao(composite);
		
		Button btnAdicionar = new Button(grpComisso, SWT.NONE);
		btnAdicionar.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		btnAdicionar.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 1, 1));
		btnAdicionar.setText("Adicionar");
		
		Button btnRemover = new Button(grpComisso, SWT.NONE);
		btnRemover.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/delete/delete16.png"));
		btnRemover.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1));
		btnRemover.setText("Remover");
		
		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setText("Vistorias");
		
		CTabItem tbtmVistoria = new CTabItem(tabFolder, SWT.NONE);
		tbtmVistoria.setText("Vistorias");
		
		Composite composite_8 = new Composite(tabFolder, SWT.NONE);
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
		
		Button btnSelecionarFuncionarioVistoria = new Button(grpVistoria, SWT.NONE);
		btnSelecionarFuncionarioVistoria.setText("...");
		
		Label lblInquilino = new Label(grpVistoria, SWT.NONE);
		lblInquilino.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInquilino.setText("Inquilino");
		
		text_17 = new Text(grpVistoria, SWT.BORDER);
		text_17.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionarClienteVistoria = new Button(grpVistoria, SWT.NONE);
		btnSelecionarClienteVistoria.setText("...");
		
		Label lblArquivo = new Label(grpVistoria, SWT.NONE);
		lblArquivo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblArquivo.setText("Arquivo");
		
		text_18 = new Text(grpVistoria, SWT.BORDER);
		text_18.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_123 = new Button(grpVistoria, SWT.NONE);
		button_123.setText("...");
		
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
		
		CTabItem tbtmChamada = new CTabItem(tabFolder, SWT.NONE);
		tbtmChamada.setText("Chamados");
		
		Composite composite_6 = new Composite(tabFolder, SWT.NONE);
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
		
		Composite cpAberturaChamado = new Composite(tfChamado, SWT.NONE);
		tbtmAbertura.setControl(cpAberturaChamado);
		cpAberturaChamado.setLayout(new GridLayout(3, false));
		
		Label lblNmero_2 = new Label(cpAberturaChamado, SWT.NONE);
		lblNmero_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmero_2.setText("Número");
		
		text = new Text(cpAberturaChamado, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
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
		
		Label lblCliente_1 = new Label(cpAberturaChamado, SWT.NONE);
		lblCliente_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCliente_1.setText("Cliente");
		
		text_22 = new Text(cpAberturaChamado, SWT.BORDER);
		text_22.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Button btnSelecionar_3 = new Button(cpAberturaChamado, SWT.NONE);
		btnSelecionar_3.setText("...");
		
		Label lblFuncionrio_2 = new Label(cpAberturaChamado, SWT.NONE);
		lblFuncionrio_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio_2.setText("Funcionário");
		
		text_23 = new Text(cpAberturaChamado, SWT.BORDER);
		text_23.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar_4 = new Button(cpAberturaChamado, SWT.NONE);
		btnSelecionar_4.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnSelecionar_4.setText("...");
		
		Label lblDescrio = new Label(cpAberturaChamado, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio.setText("Descrição");
		
		text_24 = new Text(cpAberturaChamado, SWT.BORDER);
		GridData gd_text_24 = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_text_24.heightHint = 70;
		text_24.setLayoutData(gd_text_24);
		new Label(cpAberturaChamado, SWT.NONE);
		new Label(cpAberturaChamado, SWT.NONE);
		
		Button button_7 = new Button(cpAberturaChamado, SWT.NONE);
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
			tfChamado.setSelection(0);
		}
		
		
	}

	private void criarTabelaComissao(Composite composite){
		TableViewerBuilder tvbComissao = new TableViewerBuilder(composite);
		
		tvbComissao.createColumn("Nome").bindToProperty("funcionario.nome").build();
		tvbComissao.createColumn("Valor ( % )").bindToValue(new IValue() {
			//TODO AQUI TEM QUE RECALCULAR VALOR			
			@Override
			public void setValue(Object arg0, Object arg1) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public Object getValue(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		}).makeEditable().build();
		tvbComissao.createColumn("Valor (R$)").bindToProperty("valor").makeEditable().build();
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
	
	@Override
	protected void salvar() {
		
	}
}
