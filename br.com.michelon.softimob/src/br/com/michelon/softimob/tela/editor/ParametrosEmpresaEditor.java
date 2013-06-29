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
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.ParametrosEmpresaService;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.tela.widget.CNPJTextField;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

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
	private Text text_8;
	private Text text_9;
	private Text text_14;
	private Text text_15;
	private Text text_16;
	private Text text_17;
	private Text text_18;
	private Text text_19;
	private Text text_20;
	
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
		
		Label lblCreci = new Label(parent, SWT.NONE);
		lblCreci.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCreci.setText("CRECI");
		
		text_8 = new Text(parent, SWT.BORDER);
		GridData gd_text_8 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_8.widthHint = 112;
		text_8.setLayoutData(gd_text_8);
		
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
		button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.MODELO_CONTRATO, button, value, "contratoVenda");
		
		Label lblChecklist = new Label(composite, SWT.NONE);
		lblChecklist.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChecklist.setText("Checklist");
		
		text_9 = new Text(composite, SWT.BORDER);
		text_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_11 = new Button(composite, SWT.NONE);
		button_11.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CHECK_LIST, button_11, value, "checkListVenda");
		
		Label lblConta = new Label(composite, SWT.NONE);
		lblConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblConta.setText("Conta");
		
		text_17 = new Text(composite, SWT.BORDER);
		text_17.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_6 = new Button(composite, SWT.NONE);
		button_6.setText("...");
		
		Label lblContraPartida = new Label(composite, SWT.NONE);
		lblContraPartida.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblContraPartida.setText("Contra - Partida");
		
		text_18 = new Text(composite, SWT.BORDER);
		text_18.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_7 = new Button(composite, SWT.NONE);
		button_7.setText("...");
		
		CTabItem tbtmAlguel = new CTabItem(tabFolder, SWT.NONE);
		tbtmAlguel.setText("Aluguel");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmAlguel.setControl(composite_1);
		GridLayout gl_composite_1 = new GridLayout(3, false);
		gl_composite_1.verticalSpacing = 10;
		composite_1.setLayout(gl_composite_1);
		
		Label lblDataDeRecebimento_1 = new Label(composite_1, SWT.NONE);
		lblDataDeRecebimento_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeRecebimento_1.setText("Dia de Recebimento");
		
		text_5 = new Text(composite_1, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Label lblDataDeRepasse_1 = new Label(composite_1, SWT.NONE);
		lblDataDeRepasse_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeRepasse_1.setText("Dia de Repasse");
		
		text_6 = new Text(composite_1, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Label lblModeloDeContrato_1 = new Label(composite_1, SWT.NONE);
		lblModeloDeContrato_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModeloDeContrato_1.setText("Modelo de Contrato");
		
		text_7 = new Text(composite_1, SWT.BORDER);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnContratoAluguel = new Button(composite_1, SWT.NONE);
		btnContratoAluguel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnContratoAluguel.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.MODELO_CONTRATO, btnContratoAluguel, value, "contratoAluguel");
		
		Label lblChecklist_1 = new Label(composite_1, SWT.NONE);
		lblChecklist_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChecklist_1.setText("Checklist");
		
		text_14 = new Text(composite_1, SWT.BORDER);
		text_14.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.setText("...");
		
		Label lblConta_1 = new Label(composite_1, SWT.NONE);
		lblConta_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblConta_1.setText("Conta");
		
		text_19 = new Text(composite_1, SWT.BORDER);
		text_19.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_8 = new Button(composite_1, SWT.NONE);
		button_8.setText("...");
		
		Label lblContraPartida_1 = new Label(composite_1, SWT.NONE);
		lblContraPartida_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblContraPartida_1.setText("Contra - Partida");
		
		text_20 = new Text(composite_1, SWT.BORDER);
		text_20.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_9 = new Button(composite_1, SWT.NONE);
		button_9.setText("...");
		
		CTabItem tbtmPrestaoDeServio = new CTabItem(tabFolder, SWT.NONE);
		tbtmPrestaoDeServio.setText("Prestação de Serviço");
		
		Composite composite_6 = new Composite(tabFolder, SWT.NONE);
		tbtmPrestaoDeServio.setControl(composite_6);
		composite_6.setLayout(new GridLayout(3, false));
		
		Label lblModeloDeContrato_2 = new Label(composite_6, SWT.NONE);
		lblModeloDeContrato_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModeloDeContrato_2.setText("Modelo de Contrato");
		
		text_16 = new Text(composite_6, SWT.BORDER);
		text_16.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_3 = new Button(composite_6, SWT.NONE);
		button_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_3.setText("...");
		
		Label lblTipoDeConta_1 = new Label(composite_6, SWT.NONE);
		lblTipoDeConta_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipoDeConta_1.setText("Tipo de Conta");
		
		ComboViewer comboViewer = new ComboViewer(composite_6, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_6, SWT.NONE);
		
		CTabItem tbtmVistoria = new CTabItem(tabFolder, SWT.NONE);
		tbtmVistoria.setText("Vistoria");
		
		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		tbtmVistoria.setControl(composite_5);
		composite_5.setLayout(new GridLayout(3, false));
		
		Label lblChecklist_2 = new Label(composite_5, SWT.NONE);
		lblChecklist_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChecklist_2.setText("Checklist");
		
		text_15 = new Text(composite_5, SWT.BORDER);
		text_15.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_2 = new Button(composite_5, SWT.NONE);
		button_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_2.setText("...");
		
		CTabItem tbtmReformas = new CTabItem(tabFolder, SWT.NONE);
		tbtmReformas.setText("Reformas");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmReformas.setControl(composite_3);
		composite_3.setLayout(new GridLayout(3, false));
		
		Label lblPrazoPFinalizao = new Label(composite_3, SWT.NONE);
		lblPrazoPFinalizao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPrazoPFinalizao.setText("Prazo p/ Finalização");
		
		text_2 = new Text(composite_3, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDias = new Label(composite_3, SWT.NONE);
		lblDias.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		lblDias.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblDias.setText("Dias");
		
		Label lblResponsvel = new Label(composite_3, SWT.NONE);
		lblResponsvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblResponsvel.setText("Funcionário Responsável");
		
		text_3 = new Text(composite_3, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_10 = new Button(composite_3, SWT.NONE);
		button_10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_10.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_10.setText("...");
		
		Label lblTipoDaConta = new Label(composite_3, SWT.NONE);
		lblTipoDaConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipoDaConta.setText("Tipo da Conta");
		
		ComboViewer comboViewer_2 = new ComboViewer(composite_3, SWT.READ_ONLY);
		Combo combo_2 = comboViewer_2.getCombo();
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_3, SWT.NONE);
		
		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setText("Comissão");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite_4);
		composite_4.setLayout(new GridLayout(3, false));
		
		Label lblTipoDeConta = new Label(composite_4, SWT.NONE);
		lblTipoDeConta.setText("Tipo de Conta");
		
		ComboViewer comboViewer_3 = new ComboViewer(composite_4, SWT.READ_ONLY);
		Combo combo_3 = comboViewer_3.getCombo();
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_2 = new Label(composite_4, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

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
