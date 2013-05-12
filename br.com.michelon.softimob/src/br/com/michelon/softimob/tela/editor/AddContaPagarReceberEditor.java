package br.com.michelon.softimob.tela.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;

public class AddContaPagarReceberEditor extends SoftimobEditor {
	private Text text;
	private Text text_1;
	public AddContaPagarReceberEditor() {
	}

	@Override
	protected void salvar() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(3, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblDataDaConta = new Label(parent, SWT.NONE);
		lblDataDaConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDaConta.setText("Data da Conta");
		
		DateTime dateTime = new DateTime(parent, SWT.BORDER);
		new Label(parent, SWT.NONE);
		
		Label lblDataDeVencimento = new Label(parent, SWT.NONE);
		lblDataDeVencimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento.setText("Data de Vencimento");
		
		DateTime dateTime_1 = new DateTime(parent, SWT.BORDER);
		new Label(parent, SWT.NONE);
		
		Label lblOrigem = new Label(parent, SWT.NONE);
		lblOrigem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblOrigem.setText("Origem");
		
		ComboViewer comboViewer_1 = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo_1 = comboViewer_1.getCombo();
		GridData gd_combo_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1);
		gd_combo_1.widthHint = 150;
		combo_1.setLayoutData(gd_combo_1);
		
		Label lblValor = new Label(parent, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");
		
		text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_text.widthHint = 150;
		text.setLayoutData(gd_text);
		new Label(parent, SWT.NONE);
		
		Button btnPagar = new Button(parent, SWT.RADIO);
		btnPagar.setText("Pagar");
		
		Button btnReceber = new Button(parent, SWT.RADIO);
		btnReceber.setText("Receber");
		
		Label lblObservaes = new Label(parent, SWT.NONE);
		lblObservaes.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes.setText("Observações");
		
		text_1 = new Text(parent, SWT.BORDER);
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_text_1.widthHint = 425;
		gd_text_1.heightHint = 44;
		text_1.setLayoutData(gd_text_1);
		
	}

}
