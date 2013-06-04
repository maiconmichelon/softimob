package br.com.michelon.softimob.tela.editor;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.ParametrosEmpresaService;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.tela.widget.CNPJTextField;

public class ParametrosEmpresaEditor extends GenericEditor<ParametrosEmpresa>{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.ParametrosEmpresaEditor";
	
	private ParametrosEmpresaService service = new ParametrosEmpresaService();
	
	private Text text;
	private Text text_1;
	private Text text_4;
	private Text text_7;
	private Text text_10;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_3;
	private Text text_2;
	private Text text_5;
	private Text text_6;
	
	public ParametrosEmpresaEditor() {
		super(ParametrosEmpresa.class);
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		Label lblRazoSocial = new Label(parent, SWT.NONE);
		lblRazoSocial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRazoSocial.setText("Razão Social");
		
		text_1 = new Text(parent, SWT.BORDER);
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 241;
		text_1.setLayoutData(gd_text_1);
		
		Label lblCnpj = new Label(parent, SWT.NONE);
		lblCnpj.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCnpj.setText("CNPJ");
		
		CNPJTextField textField = new CNPJTextField(parent);
		text = textField.getControl();
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 112;
		text.setLayoutData(gd_text);
		
		CTabFolder tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmVenda = new CTabItem(tabFolder, SWT.NONE);
		tbtmVenda.setText("Venda");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmVenda.setControl(composite);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.verticalSpacing = 10;
		composite.setLayout(gl_composite);
		
		Label lblModeloDeContrato = new Label(composite, SWT.NONE);
		lblModeloDeContrato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModeloDeContrato.setText("Modelo de Contrato");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(composite, SWT.NONE);
		button.setText("...");
		
		Label lblChecklist = new Label(composite, SWT.NONE);
		lblChecklist.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChecklist.setText("Checklist");
		
		ComboViewer comboViewer = new ComboViewer(composite, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_11 = new Button(composite, SWT.NONE);
		button_11.setText("...");
		
		CTabItem tbtmAlguel = new CTabItem(tabFolder, SWT.NONE);
		tbtmAlguel.setText("Aluguel");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmAlguel.setControl(composite_1);
		GridLayout gl_composite_1 = new GridLayout(3, false);
		gl_composite_1.verticalSpacing = 10;
		composite_1.setLayout(gl_composite_1);
		
		Label lblDataDeRecebimento_1 = new Label(composite_1, SWT.NONE);
		lblDataDeRecebimento_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeRecebimento_1.setText("Data de Recebimento");
		
		text_5 = new Text(composite_1, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Label lblDataDeRepasse_1 = new Label(composite_1, SWT.NONE);
		lblDataDeRepasse_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeRepasse_1.setText("Data de Repasse");
		
		text_6 = new Text(composite_1, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Label lblModeloDeContrato_1 = new Label(composite_1, SWT.NONE);
		lblModeloDeContrato_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModeloDeContrato_1.setText("Modelo de Contrato");
		
		text_7 = new Text(composite_1, SWT.BORDER);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.setText("...");
		
		Label lblChecklist_1 = new Label(composite_1, SWT.NONE);
		lblChecklist_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChecklist_1.setText("Checklist");
		
		ComboViewer comboViewer_1 = new ComboViewer(composite_1, SWT.READ_ONLY);
		Combo combo_1 = comboViewer_1.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		CTabItem tbtmVistoria = new CTabItem(tabFolder, SWT.NONE);
		tbtmVistoria.setText("Vistoria");
		
		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		tbtmVistoria.setControl(composite_5);
		composite_5.setLayout(new GridLayout(2, false));
		
		Label lblChecklist_2 = new Label(composite_5, SWT.NONE);
		lblChecklist_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChecklist_2.setText("Checklist");
		
		ComboViewer comboViewer_4 = new ComboViewer(composite_5, SWT.READ_ONLY);
		Combo combo_4 = comboViewer_4.getCombo();
		combo_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CTabItem tbtmContas = new CTabItem(tabFolder, SWT.NONE);
		tbtmContas.setText("Contas");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmContas.setControl(composite_2);
		composite_2.setLayout(new GridLayout(1, false));
		
		Group grpVenda = new Group(composite_2, SWT.NONE);
		grpVenda.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpVenda.setText("Venda");
		grpVenda.setLayout(new GridLayout(3, false));
		
		Label label = new Label(grpVenda, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("Conta");
		
		text_10 = new Text(grpVenda, SWT.BORDER);
		text_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_4 = new Button(grpVenda, SWT.NONE);
		button_4.setText("...");
		
		Label label_3 = new Label(grpVenda, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("Contra-partida");
		
		text_11 = new Text(grpVenda, SWT.BORDER);
		text_11.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_5 = new Button(grpVenda, SWT.NONE);
		button_5.setText("...");
		
		Group grpAluguel = new Group(composite_2, SWT.NONE);
		grpAluguel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpAluguel.setText("Aluguel");
		grpAluguel.setLayout(new GridLayout(3, false));
		
		Label label_1 = new Label(grpAluguel, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("Conta");
		
		text_12 = new Text(grpAluguel, SWT.BORDER);
		text_12.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_6 = new Button(grpAluguel, SWT.NONE);
		button_6.setText("...");
		
		Label label_4 = new Label(grpAluguel, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("Contra-partida");
		
		text_13 = new Text(grpAluguel, SWT.BORDER);
		text_13.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_7 = new Button(grpAluguel, SWT.NONE);
		button_7.setText("...");
		
		CTabItem tbtmReformas = new CTabItem(tabFolder, SWT.NONE);
		tbtmReformas.setText("Reformas");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmReformas.setControl(composite_3);
		composite_3.setLayout(new GridLayout(4, false));
		
		Label lblPrazoPFinalizao = new Label(composite_3, SWT.NONE);
		lblPrazoPFinalizao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPrazoPFinalizao.setText("Prazo p/ Finalização");
		
		text_2 = new Text(composite_3, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDias = new Label(composite_3, SWT.NONE);
		lblDias.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		lblDias.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblDias.setText("Dias");
		new Label(composite_3, SWT.NONE);
		
		Label lblResponsvel = new Label(composite_3, SWT.NONE);
		lblResponsvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblResponsvel.setText("Funcionário Responsável");
		
		text_3 = new Text(composite_3, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button button_10 = new Button(composite_3, SWT.NONE);
		button_10.setText("...");
		
		Label lblTipoDaConta = new Label(composite_3, SWT.NONE);
		lblTipoDaConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipoDaConta.setText("Tipo da Conta a Pagar");
		
		ComboViewer comboViewer_2 = new ComboViewer(composite_3, SWT.READ_ONLY);
		Combo combo_2 = comboViewer_2.getCombo();
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		
		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setText("Comissão");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite_4);
		composite_4.setLayout(new GridLayout(2, false));
		
		Label lblTipoDeConta = new Label(composite_4, SWT.NONE);
		lblTipoDeConta.setText("Tipo de Conta");
		
		ComboViewer comboViewer_3 = new ComboViewer(composite_4, SWT.READ_ONLY);
		Combo combo_3 = comboViewer_3.getCombo();

		tabFolder.setSelection(0);
	}
	
	@Override
	public GenericService<ParametrosEmpresa> getService() {
		return service;
	}
	
	@Override
	protected ParametrosEmpresa getValorInicial(GenericEditorInput<ParametrosEmpresa> editorInput) {
		ParametrosEmpresa p = service.findParametrosEmpresa();
		
		return p != null ? p : new ParametrosEmpresa();
	}
	
}
