package br.com.michelon.softimob.tela.editor;

import java.util.Date;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.service.DepartamentoService;
import br.com.michelon.softimob.aplicacao.service.FuncionarioService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Departamento;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.EnderecoGroup;
import br.com.michelon.softimob.tela.widget.LoadOnFocus;
import br.com.michelon.softimob.tela.widget.PhoneTextField;

public class FuncionarioEditor extends GenericEditor<Funcionario> {
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.FuncionarioEditor"; //$NON-NLS-1$
	
	private FuncionarioService service = new FuncionarioService();
	
	private Text text;
	private Text text_3;
	private Text text_2;
	private Text text_4;
	private Text text_5;
	private ComboViewer cvDepartamento;
	private Text text_1;

	private EnderecoGroup grpEndereco;
	
	public FuncionarioEditor() {
		super(Funcionario.class);
	}

	@Override
	public GenericService<Funcionario> getService() {
		return service;
	};
	
	@Override
	public void afterCreatePartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.verticalSpacing = 10;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblNome = new Label(composite, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome");
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDepartamento = new Label(composite, SWT.NONE);
		lblDepartamento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDepartamento.setText("Departamento");
		
		cvDepartamento = new ComboViewer(composite, SWT.READ_ONLY);
		Combo combo = cvDepartamento.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cvDepartamento.setContentProvider(ArrayContentProvider.getInstance());
		LoadOnFocus.setFocusGainedListener(cvDepartamento, new DepartamentoService());
		
		Label lblDataDeNascimento = new Label(composite, SWT.NONE);
		lblDataDeNascimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeNascimento.setText("Data de Nascimento");
		
		DateTextField dateTextField = new DateTextField(composite);
		text_4 = dateTextField.getControl();
		GridData gd_text_4 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_4.widthHint = 79;
		text_4.setLayoutData(gd_text_4);
		
		Label lblTelefone = new Label(composite, SWT.NONE);
		lblTelefone.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTelefone.setText("Telefone");
		
		PhoneTextField phoneTextField_1 = new PhoneTextField(composite);
		text_1 = phoneTextField_1.getControl();
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCelular = new Label(composite, SWT.NONE);
		lblCelular.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCelular.setText("Celular");
		
		PhoneTextField phoneTextField = new PhoneTextField(composite);
		text_2 = phoneTextField.getControl();
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 300;
		text_2.setLayoutData(gd_text_2);
		
		Label lblEmail = new Label(composite, SWT.NONE);
		lblEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEmail.setText("E-mail");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDataDeAdmisso = new Label(composite, SWT.NONE);
		lblDataDeAdmisso.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeAdmisso.setText("Data de Admissão");
		
		DateTextField dateTextField_1 = new DateTextField(composite);
		text_5 = dateTextField_1.getControl();
		GridData gd_text_5 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_5.widthHint = 79;
		text_5.setLayoutData(gd_text_5);
		
		grpEndereco = new EnderecoGroup(composite, getCurrentObject().getEndereco(), SWT.NONE);
		grpEndereco.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		grpEndereco.getControl().setText("Endereco");
		grpEndereco.setEndereco(getCurrentObject().getEndereco());
	}
	
	@Override
	protected void afterSetIObservableValue(Funcionario func) {
		if(grpEndereco != null)
			grpEndereco.setEndereco(getCurrentObject().getEndereco());
	}
	
	@Override
	public void saveCurrentObject(GenericService<Funcionario> service) {
		if(validarComMensagem(getCurrentObject().getEndereco()))
			super.saveCurrentObject(service);
	}
	
	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(Funcionario.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(cvDepartamento);
		IObservableValue valueDepartamentoObserveDetailValue = PojoProperties.value(Funcionario.class, "departamento", Departamento.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer, valueDepartamentoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_4ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_4);
		IObservableValue valueDataNascimentoObserveDetailValue = PojoProperties.value(Funcionario.class, "dataNascimento", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_4ObserveWidget, valueDataNascimentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_2);
		IObservableValue valueCelularObserveDetailValue = PojoProperties.value(Funcionario.class, "celular", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueCelularObserveDetailValue, UVSHelper.uvsStringToPhoneTextField(), UVSHelper.uvsPhoneToStringTextField());
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_3);
		IObservableValue valueEmailObserveDetailValue = PojoProperties.value(Funcionario.class, "email", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_3ObserveWidget, valueEmailObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_5ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_5);
		IObservableValue valueDataAdmissaoObserveDetailValue = PojoProperties.value(Funcionario.class, "dataAdmissao", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_5ObserveWidget, valueDataAdmissaoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueTelefoneObserveDetailValue = PojoProperties.value(Funcionario.class, "telefone", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueTelefoneObserveDetailValue, UVSHelper.uvsStringToPhoneTextField(), UVSHelper.uvsPhoneToStringTextField());
		//
		return bindingContext;
	}
}
