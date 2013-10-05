package br.com.michelon.softimob.tela.dialog;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.exception.ContaJaPagaRecebidaException;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.service.ContaPagarReceberService;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.Indice;
import br.com.michelon.softimob.modelo.IndiceMes;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class ReajusteDialog extends TitleAreaDialog {
	
	private Text txtReajuste;
	private Text txtValorAntigo;
	private Text txtNovoValor;
	private Text txtData;

	private Logger log = Logger.getLogger(getClass());
	private Table table;
	private TableViewer tvContas;
	
	private WritableValue value = WritableValue.withValueType(ModeloReajuste.class);
	private DataBindingContext context;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ReajusteDialog(List<ContaPagarReceber> parcelas, Indice indice) {
		super(ShellHelper.getActiveShell());
		value.setValue(new ModeloReajuste(parcelas, indice, new Date()));
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Confirme os valores.");
		setTitle("Reajuste");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label lblAPartirDe = new Label(container, SWT.NONE);
		lblAPartirDe.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAPartirDe.setText("A partir de");
		
		DateTextField dateTextField = new DateTextField(container);
		txtData = dateTextField.getControl();
		txtData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtData.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				getModelo().calculaNovoValor();
				context.updateTargets();
				tvContas.refresh();
			}
		});
		
		Label lblltimosMeses = new Label(container, SWT.NONE);
		lblltimosMeses.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblltimosMeses.setText("Últimos 12 meses");
		
		txtReajuste = new Text(container, SWT.BORDER | SWT.READ_ONLY);
		txtReajuste.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblValorAntigo = new Label(container, SWT.NONE);
		lblValorAntigo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValorAntigo.setText("Valor Antigo");
		
		txtValorAntigo = new Text(container, SWT.BORDER | SWT.READ_ONLY);
		txtValorAntigo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNovoValor = new Label(container, SWT.NONE);
		lblNovoValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNovoValor.setText("Novo Valor");
		
		txtNovoValor = new MoneyTextField(container);
		txtNovoValor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtNovoValor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				context.updateTargets();
				tvContas.refresh();
			}
		});
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		tvContas = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table = tvContas.getTable();
		table.setLinesVisible(true);
		
		TableViewerColumn tvcVencimento = new TableViewerColumn(tvContas, SWT.NONE);
		TableColumn tblclmnVencimento = tvcVencimento.getColumn();
		tblclmnVencimento.setWidth(100);
		tblclmnVencimento.setText("Vencimento");
		tvcVencimento.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				ContaPagarReceber c = (ContaPagarReceber) element;
				return FormatterHelper.getSimpleDateFormat().format(c.getDataVencimento());
			}
		});
		
		TableViewerColumn tvcValor = new TableViewerColumn(tvContas, SWT.NONE);
		tvcValor.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				ContaPagarReceber c = (ContaPagarReceber) element;
				BigDecimal valor = isContaParaReajustar(getModelo().getData(), c) ? getModelo().getNovoValor() : c.getValor();
				return FormatterHelper.getDefaultValueFormatterToMoney().format(valor);
			}
		});
		TableColumn tblclmnValor = tvcValor.getColumn();
		tblclmnValor.setWidth(100);
		tblclmnValor.setText("Valor");
		
		context = initDataBindings();
		
		return area;
	}

	private ModeloReajuste getModelo(){
		return (ModeloReajuste) value.getValue();
	}
	
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Reajuste de Locação");
		super.configureShell(newShell);
	}
	
	@Override
	protected void okPressed() {
		if(DialogHelper.openConfirmation("Deseja fazer o reajuste das parcelas ?")){
			try {
				reajustar(getModelo().getIndice(), getModelo().getContas(), getModelo().getData());

				new ContaPagarReceberService().salvar(getModelo().getContas());
				DialogHelper.openConfirmation("Parcelas reajustadas com sucesso.");
			} catch (ContaJaPagaRecebidaException e) {
				setErrorMessage(e.getMessage());
			} catch (Exception e) {
				log.error("Erro ao reajustar parcelas.", e);
				DialogHelper.openErrorMultiStatus("Erro ao reajustar parcelas.", e.getMessage());
			}
		}
		super.okPressed();
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(301, 391);
	}
	
	public void reajustar(Indice indice, List<ContaPagarReceber> contas, Date dataInicial) throws ContaJaPagaRecebidaException{
		for(ContaPagarReceber c : contas)
			if(c.isJaPagaRecebida())
				throw new ContaJaPagaRecebidaException("Para que os valores sejam reajustados, é necessário que nenhuma conta esteja paga.");
		
		BigDecimal totalIndice = FormatterHelper.getDefaultValueFormatterToMoney().parse(txtNovoValor.getText());
		
		for(ContaPagarReceber conta : filterContasToReajuste(contas, dataInicial)){
			conta.setValor(totalIndice);
		}
	}

	private List<ContaPagarReceber> filterContasToReajuste(List<ContaPagarReceber> contas, final Date data){
		return Lists.newArrayList(Collections2.filter(contas, new Predicate<ContaPagarReceber>() {
			@Override
			public boolean apply(ContaPagarReceber arg0) {
				return isContaParaReajustar(data, arg0);
			}
		}));
	}
	
	private boolean isContaParaReajustar(final Date data, ContaPagarReceber arg0) {
		return arg0.getDataVencimento().compareTo(data) >= 0;
	}
	
	public class ModeloReajuste{

		private List<ContaPagarReceber> contas;
		
		private Indice indice;
		
		private BigDecimal novoValor;

		private Date data;

		public ModeloReajuste(List<ContaPagarReceber> contas, Indice indice, Date data) {
			this.contas = contas;
			this.indice = indice;
			setData(data);
		}

		public Date getData() {
			return data;
		}
		
		public void setData(Date data) {
			this.data = data;
		}
		
		public List<ContaPagarReceber> getContas() {
			Collections.sort(contas, new Comparator<ContaPagarReceber>() {
				@Override
				public int compare(ContaPagarReceber o1, ContaPagarReceber o2) {
					return o1.getDataVencimento().compareTo(o2.getDataVencimento());
				}
			});
			
			return contas;
		}

		public Indice getIndice() {
			return indice;
		}

		public BigDecimal getNovoValor() {
			if(novoValor == null)
				return getUltimoValor();
			return novoValor;
		}

		public void setNovoValor(BigDecimal novoValor) {
			this.novoValor = novoValor;
		}
		
		public BigDecimal getUltimoValor(){
			List<ContaPagarReceber> contasFiltradas = Lists.newArrayList(Collections2.filter(getContas(), new Predicate<ContaPagarReceber>() {
				@Override
				public boolean apply(ContaPagarReceber arg0) {
					return arg0.getDataVencimento().compareTo(getData()) <= 0;
				}
			}));

			if(contasFiltradas == null || contasFiltradas.isEmpty())
				return BigDecimal.ZERO;
			
			Collections.sort(contasFiltradas, new Comparator<ContaPagarReceber>() {
				@Override
				public int compare(ContaPagarReceber o1, ContaPagarReceber o2) {
					return o1.getDataVencimento().compareTo(o2.getDataVencimento());
				}
			});
			
			return contasFiltradas.get(contasFiltradas.size() - 1).getValor();
		}
		
		public BigDecimal getSomaIndiceUltimos12Meses(){
			List<IndiceMes> indices = getIndice().getIndices();
			BigDecimal totalIndice = BigDecimal.ZERO;

			Calendar c = Calendar.getInstance();
			c.setTime(getData());
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
			Date dataMenosUmAno = c.getTime();
			
			for(IndiceMes indiceMes : indices)
				if(dataMenosUmAno.compareTo(indiceMes.getData()) <= 0 && indiceMes.getData().compareTo(getData()) <= 0)
					totalIndice = totalIndice.add(BigDecimal.valueOf(indiceMes.getPorcentagem()));
			return totalIndice;
		}
		
		public void calculaNovoValor(){
			setNovoValor(getSomaIndiceUltimos12Meses().add(new BigDecimal(100)).multiply(getUltimoValor()).divide(new BigDecimal(100)));
		}
		
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtDataObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtData);
		IObservableValue valueDataObserveDetailValue = PojoProperties.value(ModeloReajuste.class, "data", Date.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtDataObserveWidget, valueDataObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextTxtReajusteObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtReajuste);
		IObservableValue valueSomaIndiceUltimos12MesesObserveDetailValue = PojoProperties.value(ModeloReajuste.class, "somaIndiceUltimos12Meses", BigDecimal.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtReajusteObserveWidget, valueSomaIndiceUltimos12MesesObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		//
		IObservableValue observeTextTxtValorAntigoObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtValorAntigo);
		IObservableValue valueUltimoValorObserveDetailValue = PojoProperties.value(ModeloReajuste.class, "ultimoValor", BigDecimal.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtValorAntigoObserveWidget, valueUltimoValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		//
		IObservableValue observeTextTxtNovoValorObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtNovoValor);
		IObservableValue valueNovoValorObserveDetailValue = PojoProperties.value(ModeloReajuste.class, "novoValor", BigDecimal.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtNovoValorObserveWidget, valueNovoValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		//
		tvContas.setContentProvider(new ObservableListContentProvider());
		tvContas.setInput(PojoProperties.list(ModeloReajuste.class, "contas", ModeloReajuste.class).observeDetail(value));
		//
		return bindingContext;
	}
}
