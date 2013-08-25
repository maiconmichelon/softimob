package br.com.michelon.softimob.tela.dialog;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LoginDialog extends TitleAreaDialog{
	
	public LoginDialog(Shell shell) {
		super(shell);
	}
	
	private Text txtLogin;
	private Text txtSenha;
	
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Informe seu login e senha.");
		setTitle("Softimob - Sistema para imobiliária.");
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblLogin = new Label(composite, SWT.NONE);
		lblLogin.setText("Login");
		
		txtLogin = new Text(composite, SWT.BORDER);
		txtLogin.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblSenha = new Label(composite, SWT.NONE);
		lblSenha.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSenha.setText("Senha");
		
		txtSenha = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		txtSenha.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		return composite;
	}

	@Override
	protected void okPressed() {
		super.okPressed();
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(400, 250);
	}

}
