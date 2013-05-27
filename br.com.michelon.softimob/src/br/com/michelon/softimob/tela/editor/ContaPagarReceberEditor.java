package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.DateTextField;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;
import java.util.Date;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import br.com.michelon.softimob.modelo.OrigemConta;
import java.math.BigDecimal;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.nebula.jface.viewer.radiogroup.RadioGroupViewer;
import org.eclipse.nebula.widgets.radiogroup.RadioItem;

public class ContaPagarReceberEditor extends GenericEditor {
	private DataBindingContext m_bindingContext;
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.ContaPagarReceberEditor";
	
	private WritableValue value = WritableValue.withValueType(ContaPagarReceber.class);
	
	private Text text;
	private Text text_1;
	private Text text_3;
	private Text text_2;
	private ComboViewer comboViewer;
	
	public ContaPagarReceberEditor() {
	}

	@Override
	protected void salvar() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(2, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblDataDaConta = new Label(parent, SWT.NONE);
		lblDataDaConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDaConta.setText("Data da Conta");
		
		DateTextField dateTextField = new DateTextField(parent);
		text_2 = dateTextField.getControl();
		text_2.setText("");
		GridData gd_text_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 74;
		text_2.setLayoutData(gd_text_2);
		
		Label lblDataDeVencimento = new Label(parent, SWT.NONE);
		lblDataDeVencimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento.setText("Data de Vencimento");
		
		DateTextField dateTextField_1 = new DateTextField(parent);
		text_3 = dateTextField_1.getControl();
		text_3.setText("");
		GridData gd_text_3 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_3.widthHint = 74;
		text_3.setLayoutData(gd_text_3);
		
		Label lblOrigem = new Label(parent, SWT.NONE);
		lblOrigem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblOrigem.setText("Origem");
		
		comboViewer = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo_1 = comboViewer.getCombo();
		GridData gd_combo_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo_1.widthHint = 150;
		combo_1.setLayoutData(gd_combo_1);
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return ((OrigemConta)element).getDescricao();
			}
		});
		
		Label lblValor = new Label(parent, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");
		
		text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text.widthHint = 150;
		text.setLayoutData(gd_text);
		new Label(parent, SWT.NONE);
		
		RadioGroupViewer radioGroupViewer = new RadioGroupViewer(parent, SWT.BORDER);
		RadioGroup radioGroup = radioGroupViewer.getRadioGroup();
		radioGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		RadioItem riPagar = new RadioItem(radioGroup, SWT.NONE);
		riPagar.setText("Pagar");
		
		RadioItem riReceber = new RadioItem(radioGroup, SWT.NONE);
		riReceber.setText("Receber");
		
		Label lblObservaes = new Label(parent, SWT.NONE);
		lblObservaes.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes.setText("Observações");
		
		text_1 = new Text(parent, SWT.BORDER);
		GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text_1.widthHint = 425;
		gd_text_1.heightHint = 44;
		text_1.setLayoutData(gd_text_1);
		m_bindingContext = initDataBindings();
		
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_2);
		IObservableValue valueDataContaObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "dataConta", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueDataContaObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_3);
		IObservableValue valueDataVencimentoObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "dataVencimento", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_3ObserveWidget, valueDataVencimentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(comboViewer);
		IObservableValue valueOrigemObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "origem", OrigemConta.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer, valueOrigemObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueValorObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "valor", BigDecimal.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueDescricaoObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "observacoes", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueDescricaoObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
