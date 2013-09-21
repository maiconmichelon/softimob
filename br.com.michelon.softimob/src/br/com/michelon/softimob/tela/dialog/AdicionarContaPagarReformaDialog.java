package br.com.michelon.softimob.tela.dialog;

import java.math.BigDecimal;
import java.util.Date;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.FinalizacaoChamadoReforma;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;

public class AdicionarContaPagarReformaDialog extends TitleAreaDialog{
	private WritableValue value = WritableValue.withValueType(ContaPagarReceber.class);

	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private DateTextField dtfDataVencimento;
	private DateTextField dtfDataConta;

	public AdicionarContaPagarReformaDialog(Shell parentShell, Imovel imovel, FinalizacaoChamadoReforma finalizacaoChamadoReforma) throws ParametroNaoInformadoException {
		super(parentShell);
		
		value.setValue(new ContaPagarReceber(finalizacaoChamadoReforma));
	}
	
	/**
	 * @throws ParametroNaoInformadoException 
	 * @wbp.parser.constructor
	 */
	public AdicionarContaPagarReformaDialog(Shell parentShell) throws ParametroNaoInformadoException{
		this(parentShell, null, null);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Adicionar conta referente a reforma.");
		setMessage("Informe os valores referente a conta.");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label lblData = new Label(composite, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		dtfDataConta = new DateTextField(composite);
		text_3 = dtfDataConta.getControl();
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDataDeVencimento = new Label(composite, SWT.NONE);
		lblDataDeVencimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento.setText("Data de Vencimento");
		
		dtfDataVencimento = new DateTextField(composite);
		text_2 = dtfDataVencimento.getControl();
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblValor = new Label(composite, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(composite);
		text_1 = moneyTextField.getControl();
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDescrio = new Label(composite, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio.setText("Descrição");
		
		text = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		initDataBindings();
	
		return area;
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueDescricaoObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "observacoes", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueDescricaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueValorObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "valor", BigDecimal.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_2);
		IObservableValue valueDataVencimentoObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "dataVencimento", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueDataVencimentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_3);
		IObservableValue valueDataContaObserveDetailValue = PojoProperties.value(ContaPagarReceber.class, "dataConta", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_3ObserveWidget, valueDataContaObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		return bindingContext;
	}

	public ContaPagarReceber getConta() {
		return (ContaPagarReceber) value.getValue();
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Adicionar conta referente a reforma.");
		super.configureShell(newShell);
	}
	
}
