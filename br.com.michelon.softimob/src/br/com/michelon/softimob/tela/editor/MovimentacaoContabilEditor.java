package br.com.michelon.softimob.tela.editor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.modelo.LancamentoContabil;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;

import com.google.common.collect.Lists;

import de.ralfebert.rcputils.tables.TableViewerBuilder;
import de.ralfebert.rcputils.tables.format.Formatter;

public class MovimentacaoContabilEditor extends GenericEditor{
	private DataBindingContext m_bindingContext;
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.MovimentacaoContabilEditor";
	
	private WritableValue value = WritableValue.withValueType(MovimentacaoContabil.class);
	private WritableValue valueModeloLcto = WritableValue.withValueType(ModeloLancamentos.class);
	
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private TableViewerBuilder tvb;
	private TableViewer tvLancamentos;
	
	public MovimentacaoContabilEditor() {
		value.setValue(new MovimentacaoContabil());
		valueModeloLcto.setValue(new ModeloLancamentos());
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gridLayout = (GridLayout) parent.getLayout();
		gridLayout.numColumns = 3;
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1);
		gd_composite.heightHint = 160;
		composite.setLayoutData(gd_composite);
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		
		criarTabelaLancamentos(composite_1);
		
		Button btnNovo = new Button(composite, SWT.NONE);
		btnNovo.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/novo/novo16.png"));
		btnNovo.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 1, 1));
		btnNovo.setText("Novo");
		
		Button btnRemover = new Button(composite, SWT.NONE);
		btnRemover.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/delete/delete16.png"));
		btnRemover.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1));
		btnRemover.setText("Remover");
		
		Label lblLote = new Label(parent, SWT.NONE);
		lblLote.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLote.setText("Lote");
		
		text = new Text(parent, SWT.BORDER);
		text.setEditable(false);
		text.setEnabled(false);
		text.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		
		Label lblDataDeLanamento = new Label(parent, SWT.NONE);
		lblDataDeLanamento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeLanamento.setText("Data de Lançamento");
		
		DateTextField dateTextField = new DateTextField(parent);
		text_5 = dateTextField.getControl();
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		
		Label lblValor = new Label(parent, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(parent);
		text_1 = moneyTextField.getControl();
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		
		Label lblDebito = new Label(parent, SWT.NONE);
		lblDebito.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDebito.setText("Dédito");
		
		text_2 = new Text(parent, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(parent, SWT.NONE);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button, valueModeloLcto, "contaDebito");
		
		Label lblCredito = new Label(parent, SWT.NONE);
		lblCredito.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCredito.setText("Crédito");
		
		text_3 = new Text(parent, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(parent, SWT.NONE);
		button_1.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button_1, valueModeloLcto, "contaCredito");
		
		Label lblHistrico = new Label(parent, SWT.NONE);
		lblHistrico.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblHistrico.setText("Histórico");
		
		text_4 = new Text(parent, SWT.BORDER);
		GridData gd_text_4 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_4.heightHint = 61;
		text_4.setLayoutData(gd_text_4);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Button btnAdicionar = new Button(parent, SWT.NONE);
		btnAdicionar.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		btnAdicionar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnAdicionar.setText("Adicionar");
		btnAdicionar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MovimentacaoContabil mov = (MovimentacaoContabil) value.getValue();
				ModeloLancamentos mod = (ModeloLancamentos) valueModeloLcto.getValue();
				mov.getLancamentos().addAll(mod.getLancamentos());
				
				tvLancamentos.refresh();
				
				valueModeloLcto.setValue(new ModeloLancamentos());
			}
		});

		m_bindingContext = initDataBindings();
	}

	private void criarTabelaLancamentos(Composite composite){
		tvb = new TableViewerBuilder(composite);
		
		tvb.createColumn("Tipo").bindToProperty("tipo.descricao").build();
		tvb.createColumn("Data").bindToProperty("dataLancamento").format(Formatter.forDate(FormatterHelper.getSimpleDateFormat())).build();
		tvb.createColumn("Conta").bindToProperty("conta.codigoDescricao").build();
		tvb.createColumn("Valor").bindToProperty("valor").format(FormatterHelper.getCurrencyFormatter()).build();
		tvb.createColumn("Histórico").bindToProperty("historico").makeEditable().build();
		tvb.createColumn("Complemento").bindToProperty("complemento").makeEditable().build();
		
		tvb.setInput(((MovimentacaoContabil)value.getValue()).getLancamentos());
		
		tvLancamentos = tvb.getTableViewer();
	}
	
	@Override
	protected void salvar() {
		// TODO Auto-generated method stub
	}

	public class ModeloLancamentos{
		
		private PlanoConta contaDebito;
		private PlanoConta contaCredito;
		private BigDecimal valor;
		private String historico;
		private Date dataLancamento;
		
		public PlanoConta getContaDebito() {
			return contaDebito;
		}

		public void setContaDebito(PlanoConta contaDebito) {
			this.contaDebito = contaDebito;
		}

		public PlanoConta getContaCredito() {
			return contaCredito;
		}

		public void setContaCredito(PlanoConta contaCredito) {
			this.contaCredito = contaCredito;
		}

		public BigDecimal getValor() {
			return valor;
		}

		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}

		public String getHistorico() {
			return historico;
		}

		public void setHistorico(String historico) {
			this.historico = historico;
		}

		public Date getDataLancamento() {
			return dataLancamento;
		}

		public void setDataLancamento(Date dataLancamento) {
			this.dataLancamento = dataLancamento;
		}
		
		private List<LancamentoContabil> getLancamentos() {
			List<LancamentoContabil> lctos = Lists.newArrayList();
			
			LancamentoContabil lctoDeb = LancamentoContabil.createDebito(dataLancamento, contaDebito, valor, historico, StringUtils.EMPTY);
			LancamentoContabil lctoCred = LancamentoContabil.createCredito(dataLancamento, contaCredito, valor, historico, StringUtils.EMPTY);
		
			lctos.add(lctoCred);
			lctos.add(lctoDeb);
			
			return lctos;
		}
		
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueIdObserveDetailValue = PojoProperties.value(MovimentacaoContabil.class, "id", Long.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueIdObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_5ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_5);
		IObservableValue valueModeloLctoDataLancamentoObserveDetailValue = PojoProperties.value(ModeloLancamentos.class, "dataLancamento", Date.class).observeDetail(valueModeloLcto);
		bindingContext.bindValue(observeTextText_5ObserveWidget, valueModeloLctoDataLancamentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		// //TODO data da movimentao deve ser somente setada no momento que for salva, verificando se a data de todos os lancamentos sao iguais
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueModeloLctoValorObserveDetailValue = PojoProperties.value(ModeloLancamentos.class, "valor", BigDecimal.class).observeDetail(valueModeloLcto);
		Binding bindValue = bindingContext.bindValue(observeTextText_1ObserveWidget, valueModeloLctoValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		ControlDecorationSupport.create(bindValue, SWT.LEFT | SWT.TOP);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_2);
		IObservableValue valueModeloLctoContaDebitoObserveDetailValue = PojoProperties.value(ModeloLancamentos.class, "contaDebito.codigoDescricao", PlanoConta.class).observeDetail(valueModeloLcto);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueModeloLctoContaDebitoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_3);
		IObservableValue valueModeloLctoContaCreditoObserveDetailValue = PojoProperties.value(ModeloLancamentos.class, "contaCredito.codigoDescricao", PlanoConta.class).observeDetail(valueModeloLcto);
		bindingContext.bindValue(observeTextText_3ObserveWidget, valueModeloLctoContaCreditoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_4ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_4);
		IObservableValue valueModeloLctoHistoricoObserveDetailValue = PojoProperties.value(ModeloLancamentos.class, "historico", String.class).observeDetail(valueModeloLcto);
		bindingContext.bindValue(observeTextText_4ObserveWidget, valueModeloLctoHistoricoObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionTableViewer = ViewerProperties.input().observe(tvLancamentos);
		IObservableValue valueComissoesObserveDetailValue = PojoProperties.value(MovimentacaoContabil.class, "lancamentos", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTableViewer, valueComissoesObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
