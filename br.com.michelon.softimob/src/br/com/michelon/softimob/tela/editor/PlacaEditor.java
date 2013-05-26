package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import br.com.michelon.softimob.modelo.Placa;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;
import br.com.michelon.softimob.modelo.Imovel;

public class PlacaEditor extends GenericEditor{
	private DataBindingContext m_bindingContext;

	public static final String ID = "br.com.michelon.softimob.tela.editor.PlacaEditor"; //$NON-NLS-1$
	
	private WritableValue value = WritableValue.withValueType(Placa.class);
	
	private Text text;
	private Text text_1;
	private Text text_2;
	public PlacaEditor() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.verticalSpacing = 10;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		org.eclipse.swt.widgets.Label lblNmero = new org.eclipse.swt.widgets.Label(composite, SWT.NONE);
		lblNmero.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmero.setText("Número");
		
		text = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 134;
		text.setLayoutData(gd_text);
		new Label(composite, SWT.NONE);
		
		org.eclipse.swt.widgets.Label lblImvel = new org.eclipse.swt.widgets.Label(composite, SWT.NONE);
		lblImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblImvel.setText("Imóvel");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar = new Button(composite, SWT.NONE);
		btnSelecionar.setText("...");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Corretor");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnNewButton.setText("...");
		m_bindingContext = initDataBindings();
	}

	@Override
	protected void salvar() {
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNumeroObserveDetailValue = PojoProperties.value(Placa.class, "numero", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNumeroObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_1);
		IObservableValue valueImovelObserveDetailValue = PojoProperties.value(Placa.class, "imovel", Imovel.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueImovelObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_2);
		IObservableValue valueFuncionarionomeObserveDetailValue = PojoProperties.value(Placa.class, "funcionario.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueFuncionarionomeObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
