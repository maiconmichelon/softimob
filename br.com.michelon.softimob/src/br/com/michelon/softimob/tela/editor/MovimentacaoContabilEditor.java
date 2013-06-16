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
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.MovimentacaoContabilService;
import br.com.michelon.softimob.modelo.LancamentoContabil;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;

import com.google.common.collect.Lists;

import de.ralfebert.rcputils.tables.TableViewerBuilder;
import de.ralfebert.rcputils.tables.format.Formatter;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;

public class MovimentacaoContabilEditor extends GenericEditor<MovimentacaoContabil>{
	private DataBindingContext m_bindingContext;
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.MovimentacaoContabilEditor";
	
	private WritableValue valueModeloLcto = WritableValue.withValueType(ModeloLancamentos.class);
	private MovimentacaoContabilService service = new MovimentacaoContabilService();
	
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private TableViewerBuilder tvb;
	private TableViewer tvLancamentos;
	
	public MovimentacaoContabilEditor() {
		super(MovimentacaoContabil.class);
		
		valueModeloLcto.setValue(new ModeloLancamentos());
	}

	@Override
	public GenericService<MovimentacaoContabil> getService() {
		return service;
	}
	
	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gridLayout = (GridLayout) parent.getLayout();
		gridLayout.verticalSpacing = 10;
		gridLayout.numColumns = 6;
		
		Label lblLote = new Label(parent, SWT.NONE);
		lblLote.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLote.setText("Lote");
		
		text = new Text(parent, SWT.BORDER);
		text.setEditable(false);
		text.setEnabled(false);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblDataDeLanamento = new Label(parent, SWT.NONE);
		lblDataDeLanamento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeLanamento.setText("Data de Lançamento");
		
		DateTextField dateTextField = new DateTextField(parent);
		text_5 = dateTextField.getControl();
		GridData gd_text_5 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_5.widthHint = 90;
		text_5.setLayoutData(gd_text_5);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblValor = new Label(parent, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(parent);
		text_1 = moneyTextField.getControl();
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_1.widthHint = 90;
		text_1.setLayoutData(gd_text_1);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblDebito = new Label(parent, SWT.NONE);
		lblDebito.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDebito.setText("Dédito");
		
		text_2 = new Text(parent, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(parent, SWT.NONE);
		button.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button, valueModeloLcto, "contaDebito");
		
		Label lblCredito = new Label(parent, SWT.NONE);
		lblCredito.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCredito.setText("Crédito");
		
		text_3 = new Text(parent, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(parent, SWT.NONE);
		button_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_1.setText("...");
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button_1, valueModeloLcto, "contaCredito");
		
		Label lblHistrico = new Label(parent, SWT.NONE);
		lblHistrico.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblHistrico.setText("Histórico");
		
		text_4 = new Text(parent, SWT.BORDER);
		GridData gd_text_4 = new GridData(SWT.FILL, SWT.FILL, true, false, 5, 1);
		gd_text_4.heightHint = 39;
		text_4.setLayoutData(gd_text_4);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Button btnAdicionar = new Button(parent, SWT.NONE);
		btnAdicionar.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		btnAdicionar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
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
		
		Group grpDbito = new Group(parent, SWT.NONE);
		grpDbito.setLayout(new GridLayout(2, false));
		grpDbito.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 6, 1));
		grpDbito.setText("Débito");
		
		Composite cpLctosCredito = new Composite(grpDbito, SWT.NONE);
		cpLctosCredito.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		cpLctosCredito.setLayout(new GridLayout(1, false));
		
		criarTabelaLancamentosCredito(cpLctosCredito);
		
		Label lblTotal = new Label(grpDbito, SWT.NONE);
		lblTotal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		lblTotal.setBounds(0, 0, 55, 15);
		lblTotal.setText("Total");
		
		Label lblR = new Label(grpDbito, SWT.NONE);
		lblR.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblR.setText("R$0,00");
		GridData gd_lblR = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblR.widthHint = 89;
		lblR.setLayoutData(gd_lblR);
		lblR.setBounds(0, 0, 55, 15);
		
		Group grpCrdito = new Group(parent, SWT.NONE);
		grpCrdito.setLayout(new GridLayout(2, false));
		grpCrdito.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 6, 1));
		grpCrdito.setText("Crédito");
		
		Composite cpLctosDebito = new Composite(grpCrdito, SWT.NONE);
		cpLctosDebito.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		cpLctosDebito.setLayout(new GridLayout(1, false));
		
		criarTabelaLancamentosDebito(cpLctosDebito);
		
		Label label = new Label(grpCrdito, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		label.setText("Total");
		label.setBounds(0, 0, 55, 15);
		
		Label label_1 = new Label(grpCrdito, SWT.NONE);
		GridData gd_label_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label_1.widthHint = 89;
		label_1.setLayoutData(gd_label_1);
		label_1.setText("R$0,00");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		label_1.setBounds(0, 0, 55, 15);
		
		m_bindingContext = initDataBindings();
	}

	private void criarTabelaLancamentosDebito(Composite cpLctosDebito) {
		criarTabelaLancamentosCredito(cpLctosDebito);
	}

	private void criarTabelaLancamentosCredito(Composite composite){
		tvb = new TableViewerBuilder(composite);
		
//		tvb.createColumn("Tipo").bindToProperty("tipo.descricao").build();
		tvb.createColumn("Data").bindToProperty("dataLancamento").setPercentWidth(10).format(Formatter.forDate(FormatterHelper.getSimpleDateFormat())).build();
		tvb.createColumn("Conta").bindToProperty("conta.codigoDescricao").setPercentWidth(20).build();
		tvb.createColumn("Valor").bindToProperty("valor").setPercentWidth(10).format(FormatterHelper.getCurrencyFormatter()).build();
		tvb.createColumn("Histórico").bindToProperty("historico").setPercentWidth(40).makeEditable().build();
		tvb.createColumn("Complemento").bindToProperty("complemento").setPercentWidth(40).makeEditable().build();
		
		tvb.setInput(((MovimentacaoContabil)value.getValue()).getLancamentos());
		
		tvLancamentos = tvb.getTableViewer();
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
