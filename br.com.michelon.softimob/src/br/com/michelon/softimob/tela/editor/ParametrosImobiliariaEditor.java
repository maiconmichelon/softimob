package br.com.michelon.softimob.tela.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import br.com.michelon.softimob.tela.widget.CNPJTextField;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.CTabItem;
import br.com.michelon.softimob.tela.widget.DateTextField;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;

public class ParametrosImobiliariaEditor extends SoftimobEditor{
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_14;
	private Text text_15;
	public ParametrosImobiliariaEditor() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gridLayout = (GridLayout) parent.getLayout();
		
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
		
		Label lblDataDeRecebimento = new Label(composite, SWT.NONE);
		lblDataDeRecebimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeRecebimento.setText("Data de Recebimento");
		
		DateTextField dateTextField = new DateTextField(composite);
		text_2 = dateTextField.getControl();
		GridData gd_text_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 79;
		text_2.setLayoutData(gd_text_2);
		new Label(composite, SWT.NONE);
		
		Label lblDataDeRepasse = new Label(composite, SWT.NONE);
		lblDataDeRepasse.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeRepasse.setText("Data de Repasse");
		
		DateTextField dateTextField_1 = new DateTextField(composite);
		text_3 = dateTextField_1.getControl();
		GridData gd_text_3 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_3.widthHint = 79;
		text_3.setLayoutData(gd_text_3);
		new Label(composite, SWT.NONE);
		
		Label lblModeloDeContrato = new Label(composite, SWT.NONE);
		lblModeloDeContrato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModeloDeContrato.setText("Modelo de Contrato");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(composite, SWT.NONE);
		button.setText("...");
		
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
		
		DateTextField dateTextField_2 = new DateTextField(composite_1);
		text_5 = dateTextField_2.getControl();
		GridData gd_text_5 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_5.widthHint = 79;
		text_5.setLayoutData(gd_text_5);
		new Label(composite_1, SWT.NONE);
		
		Label lblDataDeRepasse_1 = new Label(composite_1, SWT.NONE);
		lblDataDeRepasse_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeRepasse_1.setText("Data de Repasse");
		
		DateTextField dateTextField_3 = new DateTextField(composite_1);
		text_6 = dateTextField_3.getControl();
		GridData gd_text_6 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_6.widthHint = 79;
		text_6.setLayoutData(gd_text_6);
		new Label(composite_1, SWT.NONE);
		
		Label lblModeloDeContrato_1 = new Label(composite_1, SWT.NONE);
		lblModeloDeContrato_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModeloDeContrato_1.setText("Modelo de Contrato");
		
		text_7 = new Text(composite_1, SWT.BORDER);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.setText("...");
		
		CTabItem tbtmContas = new CTabItem(tabFolder, SWT.NONE);
		tbtmContas.setText("Contas");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmContas.setControl(composite_2);
		composite_2.setLayout(new GridLayout(1, false));
		
		Group grpComisso = new Group(composite_2, SWT.NONE);
		grpComisso.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpComisso.setText("Comissão");
		grpComisso.setLayout(new GridLayout(3, false));
		
		Label lblCont = new Label(grpComisso, SWT.NONE);
		lblCont.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCont.setText("Conta");
		
		text_8 = new Text(grpComisso, SWT.BORDER);
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_2 = new Button(grpComisso, SWT.NONE);
		button_2.setText("...");
		
		Label lblContrapartida = new Label(grpComisso, SWT.NONE);
		lblContrapartida.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblContrapartida.setText("Contra-partida");
		
		text_9 = new Text(grpComisso, SWT.BORDER);
		text_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_3 = new Button(grpComisso, SWT.NONE);
		button_3.setText("...");
		
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
		
		Group grpReforma = new Group(composite_2, SWT.NONE);
		grpReforma.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpReforma.setText("Reforma");
		grpReforma.setLayout(new GridLayout(3, false));
		
		Label label_2 = new Label(grpReforma, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("Conta");
		
		text_14 = new Text(grpReforma, SWT.BORDER);
		text_14.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_8 = new Button(grpReforma, SWT.NONE);
		button_8.setText("...");
		
		Label label_5 = new Label(grpReforma, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("Contra-partida");
		
		text_15 = new Text(grpReforma, SWT.BORDER);
		text_15.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_9 = new Button(grpReforma, SWT.NONE);
		button_9.setText("...");
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void salvar() {
		// TODO Auto-generated method stub
		
	}
}
