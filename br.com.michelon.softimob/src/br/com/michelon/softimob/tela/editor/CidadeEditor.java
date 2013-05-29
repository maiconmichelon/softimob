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

import br.com.michelon.softimob.modelo.Cidade;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.core.databinding.beans.PojoProperties;
import br.com.michelon.softimob.modelo.Estado;
import br.com.michelon.softimob.persistencia.CidadeDAO;

import org.eclipse.jface.databinding.swt.WidgetProperties;

public class CidadeEditor extends GenericEditor {

	public static final String ID = "br.com.michelon.softimob.tela.editor.CidadeEditor";
	
	private DataBindingContext m_bindingContext;
	private Text text;
	private WritableValue value = WritableValue.withValueType(Cidade.class);
	private ComboViewer comboViewer;
	private CidadeDAO daoCidade;
	
	public CidadeEditor() {
		daoCidade = new CidadeDAO();
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gridLayout = (GridLayout) parent.getLayout();
		gridLayout.verticalSpacing = 10;
		
		Label lblUf = new Label(parent, SWT.NONE);
		lblUf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUf.setText("UF");
		
		comboViewer = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblCidade = new Label(parent, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");
		
		text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 222;
		text.setLayoutData(gd_text);
		
		value.setValue(new Cidade());
		
		m_bindingContext = initDataBindings();
	}

	@Override
	protected void salvar() {
		daoCidade.salvar(value.getValue());
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(comboViewer);
		IObservableValue valueEstadoObserveDetailValue = PojoProperties.value(Cidade.class, "estado", Estado.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer, valueEstadoObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(Cidade.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
