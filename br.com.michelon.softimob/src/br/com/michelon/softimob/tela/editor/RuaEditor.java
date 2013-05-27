package br.com.michelon.softimob.tela.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.modelo.Rua;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.core.databinding.beans.PojoProperties;
import br.com.michelon.softimob.modelo.Bairro;
import org.eclipse.jface.databinding.swt.WidgetProperties;

public class RuaEditor extends GenericEditor{
	private DataBindingContext m_bindingContext;

	public static final String ID = "br.com.michelon.softimob.tela.editor.RuaEditor";
	
	private WritableValue value = WritableValue.withValueType(Rua.class);
	private Text text;
	private ComboViewer comboViewer_2;
	
	public RuaEditor() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gridLayout = (GridLayout) parent.getLayout();
		gridLayout.verticalSpacing = 10;
		
		Label lblUf = new Label(parent, SWT.NONE);
		lblUf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUf.setText("UF");
		
		ComboViewer comboViewer = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblCidade = new Label(parent, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");
		
		ComboViewer comboViewer_1 = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo_1 = comboViewer_1.getCombo();
		GridData gd_combo_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo_1.widthHint = 228;
		combo_1.setLayoutData(gd_combo_1);
		
		Label lblBairro = new Label(parent, SWT.NONE);
		lblBairro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBairro.setText("Bairro");
		
		comboViewer_2 = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo_2 = comboViewer_2.getCombo();
		GridData gd_combo_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo_2.widthHint = 228;
		combo_2.setLayoutData(gd_combo_2);
		
		Label lblRua = new Label(parent, SWT.NONE);
		lblRua.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRua.setText("Rua");
		
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
		IObservableValue observeSingleSelectionComboViewer_2 = ViewerProperties.singleSelection().observe(comboViewer_2);
		IObservableValue valueBairroObserveDetailValue = PojoProperties.value(Rua.class, "bairro", Bairro.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer_2, valueBairroObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(Rua.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
