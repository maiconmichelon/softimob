package br.com.michelon.softimob.tela.dialog;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import br.com.michelon.softimob.tela.widget.DateTextField;
import org.eclipse.swt.widgets.Button;

public class RazaoDialog extends TitleAreaDialog{
	private Text text;
	private Text text_1;
	private Text text_2;

	public RazaoDialog() {
		super(ShellHelper.getActiveShell());
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Razão");
		setMessage("Informe o periodo e a conta");
		
		Composite area = (Composite) super.createDialogArea(parent);
		
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		Label lblDataInicial = new Label(composite, SWT.NONE);
		lblDataInicial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataInicial.setText("Data Inicial ");
		
		DateTextField dateTextField = new DateTextField(composite);
		text = dateTextField.getControl();
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label lblDataFinal = new Label(composite, SWT.NONE);
		lblDataFinal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataFinal.setText("Data Final");
		
		DateTextField dateTextField_1 = new DateTextField(composite);
		text_1 = dateTextField_1.getControl();
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label lblPlanoDeConta = new Label(composite, SWT.NONE);
		lblPlanoDeConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPlanoDeConta.setText("Plano de Conta");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(composite, SWT.NONE);
		button.setText("...");
		
		return area;
	}
	
}
