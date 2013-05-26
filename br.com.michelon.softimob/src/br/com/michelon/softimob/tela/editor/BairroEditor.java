package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;

import br.com.michelon.softimob.modelo.Bairro;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.core.databinding.beans.PojoProperties;
import br.com.michelon.softimob.modelo.Cidade;
import org.eclipse.jface.databinding.swt.WidgetProperties;

public class BairroEditor extends GenericEditor{
	private DataBindingContext m_bindingContext;
	public static final String ID = "br.com.michelon.softimob.tela.editor.BairroEditor";
	private Text text;
	private WritableValue value = WritableValue.withValueType(Bairro.class);
	private ComboViewer comboViewer;
	
	public BairroEditor() {
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
		m_bindingContext = initDataBindings();
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void salvar() {
		// TODO Auto-generated method stub
		
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
