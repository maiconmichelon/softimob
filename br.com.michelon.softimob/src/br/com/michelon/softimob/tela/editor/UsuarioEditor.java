package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.UsuarioService;
import br.com.michelon.softimob.modelo.Usuario;

public class UsuarioEditor extends GenericEditor<Usuario>{

	public static final String ID = "br.com.michelon.softimob.tela.editor.UsuarioEditor";
	
	private Text txtNomeUsuario;
	private Text txtSenha;
	private Button btnAdministrador;

	public UsuarioEditor() {
		super(Usuario.class);
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		Label lblNome = new Label(parent, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Login");
		
		txtNomeUsuario = new Text(parent, SWT.BORDER);
		txtNomeUsuario.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblSenha = new Label(parent, SWT.NONE);
		lblSenha.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSenha.setText("Senha");
		
		txtSenha = new Text(parent, SWT.BORDER | SWT.PASSWORD);
		txtSenha.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		
		btnAdministrador = new Button(parent, SWT.CHECK);
		btnAdministrador.setText("Administrador");
	}

	@Override
	public GenericService<Usuario> getService() {
		return new UsuarioService();
	}

	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtNomeUsuario);
		IObservableValue valueUsuarioObserveDetailValue = PojoProperties.value(Usuario.class, "login", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueUsuarioObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtSenha);
		IObservableValue valueSenhaObserveDetailValue = PojoProperties.value(Usuario.class, "senha", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueSenhaObserveDetailValue, null, null);
		//
		IObservableValue observeSelectionBtnAdministradorObserveWidget = WidgetProperties.selection().observe(btnAdministrador);
		IObservableValue valueAdministradorObserveDetailValue = PojoProperties.value(Usuario.class, "administrador", Boolean.class).observeDetail(value);
		bindingContext.bindValue(observeSelectionBtnAdministradorObserveWidget, valueAdministradorObserveDetailValue, null, null);
		//
		return bindingContext;
	}

}
