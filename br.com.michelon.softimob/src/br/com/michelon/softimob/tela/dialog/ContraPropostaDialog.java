package br.com.michelon.softimob.tela.dialog;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.nebula.jface.viewer.radiogroup.RadioGroupViewer;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.ValidatorHelper;
import br.com.michelon.softimob.aplicacao.service.PropostaService;
import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;

public class ContraPropostaDialog extends TitleAreaDialog {

	private Text text;
	private Text text_1;
	private Proposta proposta;
	private Logger log = Logger.getLogger(getClass());

	private WritableValue valueContraProposta = WritableValue.withValueType(Proposta.class);
	private RadioGroup radioGroup;
	private RadioGroupViewer radioGroupViewer;
	private Label lblData;
	private Text text_2;
	private DateTextField dateTextField;

	public ContraPropostaDialog(Shell parentShell, Proposta proposta) {
		super(parentShell);
		this.proposta = proposta;
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite area = (Composite) super.createDialogArea(parent);
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblValor = new Label(composite, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");

		MoneyTextField moneyTextField = new MoneyTextField(composite);
		text_1 = moneyTextField.getControl();
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 120;
		text_1.setLayoutData(gd_text_1);
		
		lblData = new Label(composite, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		dateTextField = new DateTextField(composite);
		text_2 = dateTextField.getControl();
		GridData gd_text_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 120;
		text_2.setLayoutData(gd_text_2);
		new Label(composite, SWT.NONE);

		radioGroupViewer = new RadioGroupViewer(composite, SWT.NONE);
		radioGroup = radioGroupViewer.getRadioGroup();
		radioGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		radioGroupViewer.setContentProvider(ArrayContentProvider.getInstance());
		radioGroupViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return element.equals(Proposta.CONTRA_PROPOSTA_CLIENTE) ? "Cliente" : "Proprietario";
			}
		});
		radioGroupViewer.setInput(new Integer[] { Proposta.CONTRA_PROPOSTA_CLIENTE, Proposta.CONTRA_PROPOSTA_PROPRIETARIO });

		Label lblObservaes = new Label(composite, SWT.NONE);
		lblObservaes.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes.setText("Observações");

		text = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.heightHint = 61;
		text.setLayoutData(gd_text);

		setTitleAndMessage();

		valueContraProposta.setValue(new Proposta(proposta));
		initDataBindings();

		return area;
	}

	protected void okPressed() {
		try {
			if (ValidatorHelper.validarComMensagem(proposta) && ValidatorHelper.validarComMensagem(getCurrentValue())){
				
				if(getCurrentValue().getTipoContraProposta() == null || getCurrentValue().getTipoContraProposta().equals(Proposta.PRIMEIRA)){
					DialogHelper.openWarning("Selecione quem fez a contraproposta.");
					return;
				}
				
				proposta.setContraProposta(getCurrentValue());
				proposta.setStatus(Proposta.CONTRAPROPOSTA);
				proposta.setDataFechamento(((Proposta)valueContraProposta.getValue()).getData());
				
				new PropostaService().salvar(proposta);
				DialogHelper.openInformation("Contraproposta salva com sucesso.");
				
				super.okPressed();
			}
		} catch (Exception e) {
			log.error("Erro ao salvar contraproposta.", e);
			DialogHelper.openErrorMultiStatus("Erro ao gerar contraproposta.", e.getMessage());
		}
	};

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Contraproposta");
		super.configureShell(newShell);
	}
	
	private Proposta getCurrentValue(){
		return (Proposta) valueContraProposta.getValue();
	}
	
	private void setTitleAndMessage() {
		setTitle("Contraproposta");
		setMessage("Informe os valores da contraproposta");
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueContraPropostaValorObserveDetailValue = PojoProperties.value(Proposta.class, "valor", BigDecimal.class).observeDetail(valueContraProposta);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueContraPropostaValorObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueContraPropostaObservacoesObserveDetailValue = PojoProperties.value(Proposta.class, "observacoes", String.class).observeDetail(valueContraProposta);
		bindingContext.bindValue(observeTextTextObserveWidget, valueContraPropostaObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionRadioGroupViewer = ViewerProperties.singleSelection().observe(radioGroupViewer);
		IObservableValue valueChaveLocalizacaoObserveDetailValue = PojoProperties.value(Proposta.class, "tipoContraProposta", Integer.class).observeDetail(valueContraProposta);
		bindingContext.bindValue(observeSingleSelectionRadioGroupViewer, valueChaveLocalizacaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_2);
		IObservableValue valueContraPropostaDataObserveDetailValue = PojoProperties.value(Proposta.class, "data", Date.class).observeDetail(valueContraProposta);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueContraPropostaDataObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		return bindingContext;
	}
}
