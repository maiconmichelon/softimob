package br.com.michelon.softimob.tela.editor;

import java.math.BigDecimal;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.MoneyTextField;

public class ContraPropostaDialog extends TitleAreaDialog{
	private DataBindingContext m_bindingContext;
	
	private Text text;
	private Text text_1;
	private Proposta proposta;

	private WritableValue valueContraProposta = WritableValue.withValueType(Proposta.class);

	private Button btnCliente;

	private Button btnImobiliria;
	
	public ContraPropostaDialog(Shell parentShell, Proposta proposta) {
		super(parentShell);
		this.proposta = proposta;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblValor = new Label(composite, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(composite);
		text_1 = moneyTextField.getControl();
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1);
		gd_text_1.widthHint = 122;
		text_1.setLayoutData(gd_text_1);
		new Label(composite, SWT.NONE);
		
		btnCliente = new Button(composite, SWT.RADIO);
		btnCliente.setText("Cliente");
		
		btnImobiliria = new Button(composite, SWT.RADIO);
		btnImobiliria.setText("Imobiliária");
		
		Label lblObservaes = new Label(composite, SWT.NONE);
		lblObservaes.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes.setText("Observações");
		
		text = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text.heightHint = 61;
		text.setLayoutData(gd_text);
		
		setTitleAndMessage();
		
		valueContraProposta.setValue(new Proposta(proposta));
		m_bindingContext = initDataBindings();
		
		return composite;
	}
	
	@Override
	protected void okPressed() {
		Proposta prop = (Proposta) valueContraProposta.getValue();
		
		if(btnCliente.getSelection())
			prop.setCliente(proposta.getCliente());
		else
			prop.setCliente(proposta.getImovel().getProprietario());
		
	}
	
	private void setTitleAndMessage() {
		setTitle("Contra proposta");
		setMessage("Informe os valores da contra-proposta");
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueContraPropostaValorObserveDetailValue = PojoProperties.value(Proposta.class, "valor", BigDecimal.class).observeDetail(valueContraProposta);
		Binding bindValue = bindingContext.bindValue(observeTextText_1ObserveWidget, valueContraPropostaValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		ControlDecorationSupport.create(bindValue, SWT.LEFT | SWT.TOP);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueContraPropostaObservacoesObserveDetailValue = PojoProperties.value(Proposta.class, "observacoes", String.class).observeDetail(valueContraProposta);
		bindingContext.bindValue(observeTextTextObserveWidget, valueContraPropostaObservacoesObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
