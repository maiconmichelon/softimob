package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.helper.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.modelo.OrigemConta;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;

public class OrigemContaEditor extends GenericEditor {
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.OrigemContaEditor";
	
	private WritableValue value = WritableValue.withValueType(OrigemConta.class);
	private DataBindingContext m_bindingContext;
	
	private Text text;
	private Text text_1;
	private Text text_2;
	public OrigemContaEditor() {
	}

	@Override
	protected void salvar() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(3, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblNome = new Label(parent, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Descrição");
		
		text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 174;
		text.setLayoutData(gd_text);
		new Label(parent, SWT.NONE);
		
		Label lblConta = new Label(parent, SWT.NONE);
		lblConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblConta.setText("Conta");
		
		text_1 = new Text(parent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(parent, SWT.NONE);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button, value, "conta");
		
		Label lblContrapartida = new Label(parent, SWT.NONE);
		lblContrapartida.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblContrapartida.setText("Contra-Partida");
		
		text_2 = new Text(parent, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(parent, SWT.NONE);
		button_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_1.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button, value, "contaContraPartida");
		
		m_bindingContext = initDataBindings();
		
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueDescricaoObserveDetailValue = PojoProperties.value(OrigemConta.class, "descricao", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueDescricaoObserveDetailValue, null, null);
		//
		IObservableValue observeSizeText_1ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_1);
		IObservableValue valueContacodigoDescricaoObserveDetailValue = PojoProperties.value(OrigemConta.class, "conta.codigoDescricao", String.class).observeDetail(value);
		bindingContext.bindValue(observeSizeText_1ObserveWidget, valueContacodigoDescricaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_2);
		IObservableValue valueContaContraPartidacodigoDescricaoObserveDetailValue = PojoProperties.value(OrigemConta.class, "contaContraPartida.codigoDescricao", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueContaContraPartidacodigoDescricaoObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
