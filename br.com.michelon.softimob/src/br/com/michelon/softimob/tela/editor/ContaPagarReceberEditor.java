package br.com.michelon.softimob.tela.editor;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.tela.widget.DateTextField;

public class ContaPagarReceberEditor extends GenericEditor {
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.ContaPagarReceberEditor";
	private Text text;
	private Text text_1;
	private Text text_3;
	private Text text_2;
	public ContaPagarReceberEditor() {
	}

	@Override
	protected void salvar() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(2, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblDataDaConta = new Label(parent, SWT.NONE);
		lblDataDaConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDaConta.setText("Data da Conta");
		
		DateTextField dateTextField = new DateTextField(parent);
		text_2 = dateTextField.getControl();
		text_2.setText("");
		GridData gd_text_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 74;
		text_2.setLayoutData(gd_text_2);
		
		Label lblDataDeVencimento = new Label(parent, SWT.NONE);
		lblDataDeVencimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento.setText("Data de Vencimento");
		
		DateTextField dateTextField_1 = new DateTextField(parent);
		text_3 = dateTextField_1.getControl();
		text_3.setText("");
		GridData gd_text_3 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_3.widthHint = 74;
		text_3.setLayoutData(gd_text_3);
		
		Label lblOrigem = new Label(parent, SWT.NONE);
		lblOrigem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblOrigem.setText("Origem");
		
		ComboViewer comboViewer_1 = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo_1 = comboViewer_1.getCombo();
		GridData gd_combo_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo_1.widthHint = 150;
		combo_1.setLayoutData(gd_combo_1);
		
		Label lblValor = new Label(parent, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");
		
		text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text.widthHint = 150;
		text.setLayoutData(gd_text);
		new Label(parent, SWT.NONE);
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		composite.setLayout(new GridLayout(2, false));
		
		Button btnPagar = new Button(composite, SWT.RADIO);
		btnPagar.setText("Pagar");
		
		Button btnReceber = new Button(composite, SWT.RADIO);
		btnReceber.setText("Receber");
		
		Label lblObservaes = new Label(parent, SWT.NONE);
		lblObservaes.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes.setText("Observações");
		
		text_1 = new Text(parent, SWT.BORDER);
		GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text_1.widthHint = 425;
		gd_text_1.heightHint = 44;
		text_1.setLayoutData(gd_text_1);
		
	}

}
