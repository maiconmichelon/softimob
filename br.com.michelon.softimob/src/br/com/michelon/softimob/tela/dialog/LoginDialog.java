package br.com.michelon.softimob.tela.dialog;

import javax.security.auth.login.LoginException;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.helper.LoginHelper;
import br.com.michelon.softimob.aplicacao.service.UsuarioService;
import br.com.michelon.softimob.modelo.Usuario;

public class LoginDialog extends TitleAreaDialog{
	
	public LoginDialog(Shell shell) {
		super(shell);
		setShellStyle(SWT.BORDER);
	}
	
	private Text txtLogin;
	private Text txtSenha;
	
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Informe seu login e senha.");
		setTitle("Softimob - Sistema para imobiliária.");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblLogin = new Label(composite, SWT.NONE);
		lblLogin.setText("Login");
		
		txtLogin = new Text(composite, SWT.BORDER);
		txtLogin.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtLogin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				((Text)e.widget).selectAll();
			}
		});
		
		Label lblSenha = new Label(composite, SWT.NONE);
		lblSenha.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSenha.setText("Senha");
		
		txtSenha = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		txtSenha.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				((Text)e.widget).selectAll();
			}
		});
		
		return area;
	}

	@Override
	protected void okPressed() {
		try {
			logar(txtLogin.getText(), txtSenha.getText());
			super.okPressed();
		} catch (LoginException e) {
			setErrorMessage(e.getMessage());
			return;
		}
	}
	
	private void logar(String login, String senha) throws LoginException{
		if(login == null || login.isEmpty())
			throw new LoginException("Login deve ser informada.");
		if(senha == null || senha.isEmpty())
			throw new LoginException("Senha deve ser informada.");

		UsuarioService usuarioService = new UsuarioService();
		Usuario usuario = usuarioService.cadastraSeNaoHaverUsuarios(login, senha);
		if(usuario == null)
			usuario = usuarioService.findByLogin(login);
		
		if(usuario == null)
			throw new LoginException("Usuário não encontrado.");
		if(!usuario.getAtivo())
			throw new LoginException("Este usuário esta desativado.");
		if(!usuario.getSenha().equals(senha))
			throw new LoginException("Senha incorreta.");
			
		LoginHelper.setUsuarioLogado(usuario);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(400, 200);
	}

}
