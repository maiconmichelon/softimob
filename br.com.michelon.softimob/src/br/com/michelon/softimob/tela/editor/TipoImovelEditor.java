package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.modelo.TipoImovel;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;

public class TipoImovelEditor extends GenericEditor{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.TipoImovelEditor"; //$NON-NLS-1$
	
	private DataBindingContext m_bindingContext;
	private WritableValue value = WritableValue.withValueType(TipoImovel.class);
	
	private Text text;
	public TipoImovelEditor() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblTipoImvel = new Label(composite, SWT.NONE);
		lblTipoImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipoImvel.setText("Nome");
		
		text = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 261;
		text.setLayoutData(gd_text);
		
		value.setValue(new TipoImovel());
		
		m_bindingContext = initDataBindings();
	}

	@Override
	protected void salvar() {
		// TODO Auto-generated method stub
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueDescricaoObserveDetailValue = PojoProperties.value(TipoImovel.class, "descricao", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueDescricaoObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
