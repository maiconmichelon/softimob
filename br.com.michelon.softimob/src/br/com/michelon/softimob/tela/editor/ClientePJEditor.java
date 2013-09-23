package br.com.michelon.softimob.tela.editor;

import java.util.Date;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PessoaJuridicaService;
import br.com.michelon.softimob.modelo.PessoaJuridica;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.CNPJTextField;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.EnderecoGroup;
import br.com.michelon.softimob.tela.widget.PhoneTextField;

public class ClientePJEditor extends GenericEditor<PessoaJuridica> {

	public static final String ID = "br.com.michelon.softimob.tela.editor.ClientePJEditor";

	private PessoaJuridicaService service = new PessoaJuridicaService();
	private Text text_12;
	private Text text_14;
	private Text text_15;
	private Text text_16;
	private Text text_13;
	private Text text_17;
	private Text text_19;

	private EnderecoGroup grpEndereco;
	private Text text;

	public ClientePJEditor() {
		super(PessoaJuridica.class);
	}

	@Override
	public GenericService<PessoaJuridica> getService() {
		return service;
	}

	@Override
	public void afterCreatePartControl(Composite parent1) {
		GridLayout gl2_parent = new GridLayout(1, false);
		gl2_parent.verticalSpacing = 10;
		parent1.setLayout(gl2_parent);
		parent1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));

		Composite composite_1 = new Composite(parent1, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout gl_composite_1 = new GridLayout(5, false);
		gl_composite_1.verticalSpacing = 10;
		composite_1.setLayout(gl_composite_1);

		Label lblRazoSocial = new Label(composite_1, SWT.NONE);
		lblRazoSocial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRazoSocial.setText("Razão Social");

		text_12 = new Text(composite_1, SWT.BORDER);
		text_12.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		Label lblDataDeAbertura = new Label(composite_1, SWT.NONE);
		lblDataDeAbertura.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeAbertura.setText("Data de Abertura");

		DateTextField dateTextField = new DateTextField(composite_1);
		text = dateTextField.getControl();
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		Label lblCnpj = new Label(composite_1, SWT.NONE);
		lblCnpj.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCnpj.setText("CNPJ");

		CNPJTextField textField_2 = new CNPJTextField(composite_1);
		text_14 = textField_2.getControl();
		text_14.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblInscEstadual = new Label(composite_1, SWT.NONE);
		lblInscEstadual.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInscEstadual.setText("Insc Estadual");

		text_15 = new Text(composite_1, SWT.BORDER);
		text_15.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblTelefone = new Label(composite_1, SWT.NONE);
		lblTelefone.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTelefone.setText("Telefone");

		PhoneTextField phoneTextField_2 = new PhoneTextField(composite_1);
		text_16 = phoneTextField_2.getControl();
		text_16.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblCelular_1 = new Label(composite_1, SWT.NONE);
		lblCelular_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCelular_1.setText("Celular");

		PhoneTextField phoneTextField_3 = new PhoneTextField(composite_1);
		text_13 = phoneTextField_3.getControl();
		text_13.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblEmail_1 = new Label(composite_1, SWT.NONE);
		lblEmail_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEmail_1.setText("E-mail");

		text_17 = new Text(composite_1, SWT.BORDER);
		text_17.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		Label lblScio = new Label(composite_1, SWT.NONE);
		lblScio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblScio.setText("Sócio Administrador");

		text_19 = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		text_19.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Button button = new Button(composite_1, SWT.NONE);
		button.setImage(ImageRepository.SEARCH_16.getImage());
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PESSOA_FISICA, button, button_1, value, "socioProprietario");
		
		Composite composite = new Composite(composite_1, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		composite.setLayout(new GridLayout(1, false));

		grpEndereco = new EnderecoGroup(composite, getCurrentObject().getEndereco(), SWT.NONE);
		grpEndereco.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpEndereco.getControl().setText("Endereco");
		grpEndereco.setEndereco(getCurrentObject().getEndereco());
	}

	@Override
	public void saveCurrentObject(GenericService<PessoaJuridica> service) {
		getCurrentObject().setEndereco(grpEndereco.getEndereco());
		
		if(!validarComMensagem(grpEndereco.getEndereco()))
			return;
		
		super.saveCurrentObject(service);
	}

	@Override
	protected void afterSetIObservableValue(PessoaJuridica pj) {
		if(grpEndereco != null)
			grpEndereco.setEndereco(getCurrentObject().getEndereco());
	}
	
	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextText_12ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_12);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(PessoaJuridica.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_12ObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_18ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueDataNascimentoObserveDetailValue = PojoProperties.value(PessoaJuridica.class, "dataNascimento", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_18ObserveWidget, valueDataNascimentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_14ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_14);
		IObservableValue valueCnpjObserveDetailValue = PojoProperties.value(PessoaJuridica.class, "cnpj", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_14ObserveWidget, valueCnpjObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_15ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_15);
		IObservableValue valueInscrisaoEstadualObserveDetailValue = PojoProperties.value(PessoaJuridica.class, "inscrisaoEstadual", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_15ObserveWidget, valueInscrisaoEstadualObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_16ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_16);
		IObservableValue valueTelefoneObserveDetailValue = PojoProperties.value(PessoaJuridica.class, "telefone", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_16ObserveWidget, valueTelefoneObserveDetailValue, UVSHelper.uvsStringToPhoneTextField(), UVSHelper.uvsPhoneToStringTextField());
		//
		IObservableValue observeTextText_13ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_13);
		IObservableValue valueCelularObserveDetailValue = PojoProperties.value(PessoaJuridica.class, "celular", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_13ObserveWidget, valueCelularObserveDetailValue, UVSHelper.uvsStringToPhoneTextField(), UVSHelper.uvsPhoneToStringTextField());
		//
		IObservableValue observeTextText_17ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_17);
		IObservableValue valueEmailObserveDetailValue = PojoProperties.value(PessoaJuridica.class, "email", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_17ObserveWidget, valueEmailObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_19ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_19);
		IObservableValue valueSocioProprietarionomeObserveDetailValue = PojoProperties.value(PessoaJuridica.class, "socioProprietario.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_19ObserveWidget, valueSocioProprietarionomeObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}