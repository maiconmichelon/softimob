package br.com.michelon.softimob.tela.editor;

import java.math.BigDecimal;
import java.util.Date;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.nebula.jface.viewer.radiogroup.RadioGroupViewer;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.service.ContaPagarReceberService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.OrigemContaService;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.OrigemConta;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.LoadOnFocus;
import br.com.michelon.softimob.tela.widget.MoneyTextField;

public class ContaPagarReceberEditor extends GenericEditor<ContaPagarReceber> {
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.ContaPagarReceberEditor";
	
	private ContaPagarReceberService service = new ContaPagarReceberService();
	
	private Text text;
	private Text text_3;
	private Text text_2;
	private ComboViewer cvOrigem;

	private RadioGroupViewer radioGroupViewer;
	private Text text_4;
	
	public ContaPagarReceberEditor() {
		super(ContaPagarReceber.class);
	}

	@Override
	public GenericService<ContaPagarReceber> getService() {
		return service;
	}
	
	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(3, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblDataDaConta = new Label(parent, SWT.NONE);
		lblDataDaConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDaConta.setText("Data da Conta");
		
		DateTextField dateTextField = new DateTextField(parent);
		text_2 = dateTextField.getControl();
		text_2.setText("");
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 74;
		text_2.setLayoutData(gd_text_2);
		
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblDataDeVencimento = new Label(parent, SWT.NONE);
		lblDataDeVencimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento.setText("Data de Vencimento");
		
		DateTextField dateTextField_1 = new DateTextField(parent);
		text_3 = dateTextField_1.getControl();
		text_3.setText("");
		GridData gd_text_3 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_3.widthHint = 74;
		text_3.setLayoutData(gd_text_3);
		new Label(parent, SWT.NONE);
		
		Label lblOrigem = new Label(parent, SWT.NONE);
		lblOrigem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblOrigem.setText("Origem");
		
		cvOrigem = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo_1 = cvOrigem.getCombo();
		GridData gd_combo_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_1.widthHint = 150;
		combo_1.setLayoutData(gd_combo_1);
		cvOrigem.setContentProvider(ArrayContentProvider.getInstance());
		cvOrigem.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return ((OrigemConta)element).getNome();
			}
		});
		LoadOnFocus.setFocusGainedListener(cvOrigem, new OrigemContaService());
		new Label(parent, SWT.NONE);
		
		Label lblValor = new Label(parent, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");
		
		MoneyTextField fmtValor = new MoneyTextField(parent);
		text = fmtValor.getControl();
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text.widthHint = 150;
		text.setLayoutData(gd_text);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		radioGroupViewer = new RadioGroupViewer(parent, SWT.NONE);
		radioGroupViewer.setContentProvider(ArrayContentProvider.getInstance());
		radioGroupViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return element.equals(ContaPagarReceber.PAGAR) ? "Pagar" : "Receber"; 
			}
		});
		radioGroupViewer.setInput(new Integer[]{ContaPagarReceber.PAGAR, ContaPagarReceber.RECEBER});

		RadioGroup radioGroup = radioGroupViewer.getRadioGroup();
		radioGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		
		Label lblObservaes = new Label(parent, SWT.NONE);
		lblObservaes.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes.setText("Observações");
		
		text_4 = new Text(parent, SWT.BORDER | SWT.MULTI);
		GridData gd_text_4 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_4.heightHint = 81;
		text_4.setLayoutData(gd_text_4);
		new Label(parent, SWT.NONE);
	}
	
	@Override
	protected DataBindingContext initBindings() {
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
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(cvOrigem);
		IObservableValue valueOrigemObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "origem", OrigemConta.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer, valueOrigemObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueValorObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "valor", BigDecimal.class).observeDetail(value);
		Binding bindValue = bindingContext.bindValue(observeTextTextObserveWidget, valueValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		ControlDecorationSupport.create(bindValue, SWT.LEFT | SWT.TOP);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_4);
		IObservableValue valueDescricaoObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "observacoes", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueDescricaoObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionRadioGroupViewer = ViewerProperties.singleSelection().observe(radioGroupViewer);
		IObservableValue valueChaveLocalizacaoObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "tipo", Integer.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionRadioGroupViewer, valueChaveLocalizacaoObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
