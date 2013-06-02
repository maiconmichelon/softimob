package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.service.BairroService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Cidade;

public class BairroEditor extends GenericEditor<Bairro>{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.BairroEditor";
	
	private DataBindingContext m_bindingContext;

	private BairroService service = new BairroService();
	
	private ComboViewer comboViewer;
	private Text text;
	
	public BairroEditor() {
		super(Bairro.class);
	}
	
	@Override
	public GenericService<Bairro> getService() {
		return service;
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gridLayout = (GridLayout) parent.getLayout();
		gridLayout.verticalSpacing = 10;
		
		Label lblCidade = new Label(parent, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");
		
		comboViewer = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo_1 = comboViewer.getCombo();
		GridData gd_combo_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo_1.widthHint = 230;
		combo_1.setLayoutData(gd_combo_1);
		
		Label lblBairro = new Label(parent, SWT.NONE);
		lblBairro.setText("Bairro");
		
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		value.setValue(new Bairro());
		
		m_bindingContext = initDataBindings();
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(comboViewer);
		IObservableValue valueCidadeObserveDetailValue = PojoProperties.value(Bairro.class, "cidade", Cidade.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer, valueCidadeObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(Bairro.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
