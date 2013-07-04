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

public class BalanceteDialog extends TitleAreaDialog{
	private Text text;
	private Text text_1;

	public BalanceteDialog() {
		super(ShellHelper.getActiveShell());
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Balancete");
		setMessage("Informe o periodo");
		
		Composite area = (Composite) super.createDialogArea(parent);
		
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		Label lblDataInicial = new Label(composite, SWT.NONE);
		lblDataInicial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataInicial.setText("Data Inicial ");
		
		DateTextField dateTextField = new DateTextField(composite);
		text = dateTextField.getControl();
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDataFinal = new Label(composite, SWT.NONE);
		lblDataFinal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataFinal.setText("Data Final");
		
		DateTextField dateTextField_1 = new DateTextField(composite);
		text_1 = dateTextField_1.getControl();
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		return area;
	}
	
}
