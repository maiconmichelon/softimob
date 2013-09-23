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

import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PessoaFisicaService;
import br.com.michelon.softimob.modelo.PessoaFisica;
import br.com.michelon.softimob.modelo.PessoaFisica.EstadoCivil;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.CPFTextField;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.EnderecoGroup;
import br.com.michelon.softimob.tela.widget.PhoneTextField;

public class ClientePFEditor extends GenericEditor<PessoaFisica> {

	public static final String ID = "br.com.michelon.softimob.tela.editor.ClientePFEditor";

	private PessoaFisicaService service = new PessoaFisicaService();

	private Text text_2;
	private Text text_6;
	private Text text;
	private Text text_3;
	private Text text_5;
	private Text txtTelefone;
	private Text text_7;
	private Text text_1;
	private Text text_11;
	private ComboViewer cvEstadoCivil;

	private EnderecoGroup grpEndereco;

	public ClientePFEditor() {
		super(PessoaFisica.class);
	}

	@Override
	public GenericService<PessoaFisica> getService() {
		return service;
	}
	
	@Override
	protected void afterSetIObservableValue(PessoaFisica obj) {
		if(grpEndereco != null)
			grpEndereco.setEndereco(getCurrentObject().getEndereco());
	}

	@Override
	public void afterCreatePartControl(Composite parent1) {
		GridLayout gl2_parent = new GridLayout(1, false);
		gl2_parent.verticalSpacing = 10;
		parent1.setLayout(gl2_parent);
		parent1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));

		Composite parent = new Composite(parent1, SWT.NONE);
		parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout gl_parent = new GridLayout(4, false);
		gl_parent.verticalSpacing = 8;
		parent.setLayout(gl_parent);

		Label lblNome = new Label(parent, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome");

		text_3 = new Text(parent, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblDataDeNascimento = new Label(parent, SWT.NONE);
		lblDataDeNascimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeNascimento.setText("Data de Nascimento");

		DateTextField dateTextField = new DateTextField(parent);
		text_11 = dateTextField.getControl();
		text_11.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblEndereo = new Label(parent, SWT.NONE);
		lblEndereo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEndereo.setText("CPF");

		CPFTextField textField = new CPFTextField(parent);
		text_1 = textField.getControl();
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblCep = new Label(parent, SWT.NONE);
		lblCep.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCep.setText("RG");

		text_2 = new Text(parent, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblRg = new Label(parent, SWT.NONE);
		lblRg.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRg.setText("Filiação");

		text_5 = new Text(parent, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblCpf = new Label(parent, SWT.NONE);
		lblCpf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCpf.setText("Estado Civil");

		cvEstadoCivil = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo = cvEstadoCivil.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cvEstadoCivil.setContentProvider(ArrayContentProvider.getInstance());
		cvEstadoCivil.setInput(EstadoCivil.values());
		
		Label lblNacionalidade = new Label(parent, SWT.NONE);
		lblNacionalidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNacionalidade.setText("Nacionalidade");

		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblTelefone_1 = new Label(parent, SWT.NONE);
		lblTelefone_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTelefone_1.setText("Telefone");

		PhoneTextField phoneTextField = new PhoneTextField(parent);
		txtTelefone = phoneTextField.getControl();
		txtTelefone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblCelular = new Label(parent, SWT.NONE);
		lblCelular.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCelular.setText("Celular");

		PhoneTextField phoneTextField_1 = new PhoneTextField(parent);
		text_7 = phoneTextField_1.getControl();
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblEmail = new Label(parent, SWT.NONE);
		lblEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEmail.setText("E-mail");

		text_6 = new Text(parent, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Composite composite_1 = new Composite(parent, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		composite_1.setLayout(new GridLayout(1, false));

		grpEndereco = new EnderecoGroup(composite_1, getCurrentObject().getEndereco(), SWT.NONE);
		grpEndereco.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpEndereco.getControl().setText("Endereco");
	}

	@Override
	public void saveCurrentObject(GenericService<PessoaFisica> service) {
		if(validarComMensagem(getCurrentObject().getEndereco()))
			super.saveCurrentObject(service);
	}

	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_3);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(PessoaFisica.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_3ObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_11ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_11);
		IObservableValue valueDataNascimentoObserveDetailValue = PojoProperties.value(PessoaFisica.class, "dataNascimento", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_11ObserveWidget, valueDataNascimentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueCpfObserveDetailValue = PojoProperties.value(PessoaFisica.class, "cpf", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueCpfObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_2);
		IObservableValue valueRgObserveDetailValue = PojoProperties.value(PessoaFisica.class, "rg", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueRgObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_5ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_5);
		IObservableValue valueFiliacaoObserveDetailValue = PojoProperties.value(PessoaFisica.class, "filiacao", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_5ObserveWidget, valueFiliacaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNacionalidadeObserveDetailValue = PojoProperties.value(PessoaFisica.class, "nacionalidade", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNacionalidadeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_4ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtTelefone);
		IObservableValue valueTelefoneObserveDetailValue = PojoProperties.value(PessoaFisica.class, "telefone", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_4ObserveWidget, valueTelefoneObserveDetailValue, UVSHelper.uvsStringToPhoneTextField(), UVSHelper.uvsPhoneToStringTextField());
		//
		IObservableValue observeTextText_7ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_7);
		IObservableValue valueCelularObserveDetailValue = PojoProperties.value(PessoaFisica.class, "celular", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_7ObserveWidget, valueCelularObserveDetailValue, UVSHelper.uvsStringToPhoneTextField(), UVSHelper.uvsPhoneToStringTextField());
		//
		IObservableValue observeTextText_6ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_6);
		IObservableValue valueEmailObserveDetailValue = PojoProperties.value(PessoaFisica.class, "email", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_6ObserveWidget, valueEmailObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionCvEstadoCivil = ViewerProperties.singleSelection().observe(cvEstadoCivil);
		IObservableValue valueEstadoCivilObserveDetailValue = PojoProperties.value(PessoaFisica.class, "estadoCivil", EstadoCivil.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionCvEstadoCivil, valueEstadoCivilObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
