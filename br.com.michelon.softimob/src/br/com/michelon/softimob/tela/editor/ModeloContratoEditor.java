package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.ModeloContratoService;
import br.com.michelon.softimob.modelo.ModeloContrato;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;

public class ModeloContratoEditor extends GenericEditor<ModeloContrato> {
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.ModeloContratoEditor";
	
	private DataBindingContext m_bindingContext;
	
	private ModeloContratoService service = new ModeloContratoService();
	
	private Text text;
	private Text text_1;

	public ModeloContratoEditor() {
		super(ModeloContrato.class);
	}
	
	@Override
	public GenericService<ModeloContrato> getService() {
		return service;
	}
	
	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(3, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblDescrio = new Label(parent, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrio.setText("Descrição");
		
		text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 260;
		text.setLayoutData(gd_text);
		new Label(parent, SWT.NONE);
		
		Label lblModelo = new Label(parent, SWT.NONE);
		lblModelo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModelo.setText("Modelo");
		
		text_1 = new Text(parent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar = new Button(parent, SWT.NONE);
		btnSelecionar.setText("...");
		
		value.setValue(new ModeloContrato());
		
		m_bindingContext = initDataBindings();
		
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(ModeloContrato.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
