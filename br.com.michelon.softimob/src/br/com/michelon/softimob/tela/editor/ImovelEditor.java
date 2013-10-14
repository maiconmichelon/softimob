package br.com.michelon.softimob.tela.editor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.jface.viewer.radiogroup.RadioGroupViewer;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.AluguelEditorInput;
import br.com.michelon.softimob.aplicacao.exception.ValidationException;
import br.com.michelon.softimob.aplicacao.helper.ContratoHelper;
import br.com.michelon.softimob.aplicacao.helper.DateHelper;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.WidgetHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.InputListener;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.AluguelService;
import br.com.michelon.softimob.aplicacao.service.ChaveService;
import br.com.michelon.softimob.aplicacao.service.ComodoService;
import br.com.michelon.softimob.aplicacao.service.ContratoPrestacaoServicoService;
import br.com.michelon.softimob.aplicacao.service.FeedbackService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.ImovelService;
import br.com.michelon.softimob.aplicacao.service.PropostaService;
import br.com.michelon.softimob.aplicacao.service.ReservaService;
import br.com.michelon.softimob.aplicacao.service.TipoComodoService;
import br.com.michelon.softimob.aplicacao.service.TipoImovelService;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.Chave;
import br.com.michelon.softimob.modelo.Chave.LocalizacaoChave;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico.TipoContrato;
import br.com.michelon.softimob.modelo.Feedback;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.ModeloContrato;
import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.modelo.Reserva;
import br.com.michelon.softimob.modelo.TipoComodo;
import br.com.michelon.softimob.modelo.TipoImovel;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.dialog.ContraPropostaDialog;
import br.com.michelon.softimob.tela.dialog.FinalizarPropostaDialog;
import br.com.michelon.softimob.tela.dialog.ValidationErrorDialog;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.DateTimeStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTimeTextField;
import br.com.michelon.softimob.tela.widget.EnderecoGroup;
import br.com.michelon.softimob.tela.widget.LoadOnFocus;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import br.com.michelon.softimob.tela.widget.NullStringValueFormatter;
import br.com.michelon.softimob.tela.widget.NumberTextField;
import br.com.michelon.softimob.tela.widget.PhotoComposite;
import br.com.michelon.softimob.tela.widget.propostaXViewer.PropostaGenericXViewer;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewer;

import com.google.common.collect.Lists;

import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class ImovelEditor extends GenericEditor<Imovel>{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.ImovelEditor"; //$NON-NLS-1$
	
	private Logger log = Logger.getLogger(getClass());
	
	private WritableValue valueProposta = WritableValue.withValueType(Proposta.class);
	private WritableValue valueFeedback = WritableValue.withValueType(Feedback.class);
	private WritableValue valueChave = WritableValue.withValueType(Chave.class);
	private WritableValue valueComodo = WritableValue.withValueType(TipoComodo.class);
	private WritableValue valueReserva = WritableValue.withValueType(Reserva.class);
	private WritableValue valueContrato = WritableValue.withValueType(ContratoPrestacaoServico.class);
	
	private ImovelService service = new ImovelService();
	
	private ComboViewer cvTipoImovel;

	private TableViewerBuilder tvbChave;
	private TableViewerBuilder tvbFeedbacks;
	private TableViewerBuilder tvbComodo;
	private TableViewerBuilder tvbReserva;
	private TableViewerBuilder tvbLocacao;
	private TableViewerBuilder tvbContatoPrestacaoServico;
	private GenericXViewer<Proposta> propostaXViewer;
	
	private TableViewer tvComodos;
	private TableViewer tvChaves;
	private TableViewer tvFeedbacks;
	private TableViewer tvReservas;
	private TableViewer tvContratosPrestacaoServico;
	private Tree propostaTree;
	
	private Text txtProprietario;
	private Text txtAngariador;
	private Text txtMetragem;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_14;
	private Text text_15;
	private Text text_20;
	private Text text_9;
	private Text txtDataFeedback;
	private Text text_33;
	private Text txtValorImovel;
	private Text text_26;
	private Text txtDescrio;
	private Text text_30;
	private Text text_37;
	private Text text_38;
	private Text text_40;
	private Text text_39;
	private Text text_41;
	private Text txtCodigoImovel;
	private Text text_29;
	private Text text_10;
	private Text text;
	private Text text_16;

	private RadioGroupViewer radioGroupViewer;
	private Button btnDivulgar;
	private RadioGroupViewer radioGroupViewer_1;
	private EnderecoGroup grpEndereco;
	private Text txtObservacoes;

	private CTabFolder tfImovel;
	private CTabItem tbtmEndereo;
	private Text txtQuantidadeComodos;
	private Text txtModeloContratoPrestacaoServico;

	public ImovelEditor() {
		super(Imovel.class);
	}
	
	@Override
	public GenericService<Imovel> getService() {
		return service;
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite = new GridLayout(8, false);
		gl_composite.verticalSpacing = 6;
		composite.setLayout(gl_composite);
		
		Label lblCdigo = new Label(composite, SWT.NONE);
		lblCdigo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCdigo.setText("Código");
		
		txtCodigoImovel = new Text(composite, SWT.BORDER);
		txtCodigoImovel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 7, 1));
		txtCodigoImovel.setEditable(false);
		txtCodigoImovel.setEnabled(false);
		
		Label lblTipoImvel = new Label(composite, SWT.NONE);
		lblTipoImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipoImvel.setText("Tipo");
		
		cvTipoImovel = new ComboViewer(composite, SWT.READ_ONLY);
		Combo combo_4 = cvTipoImovel.getCombo();
		combo_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		cvTipoImovel.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return ((TipoImovel)element).getNome();
			}
		});
		cvTipoImovel.setContentProvider(ArrayContentProvider.getInstance());
		LoadOnFocus.setFocusGainedListener(cvTipoImovel, new TipoImovelService());
		
		Label lblProprietario = new Label(composite, SWT.NONE);
		lblProprietario.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProprietario.setText("Proprietário");
		
		txtProprietario = new Text(composite, SWT.BORDER);
		txtProprietario.setEditable(false);
		txtProprietario.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionarProprietario = new Button(composite, SWT.NONE);
		btnSelecionarProprietario.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, btnSelecionarProprietario, value, "proprietario");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_1, value, "proprietario", Cliente.class);
		
		Label lblAngariad = new Label(composite, SWT.NONE);
		lblAngariad.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAngariad.setText("Angariador");
		
		txtAngariador = new Text(composite, SWT.BORDER);
		txtAngariador.setEditable(false);
		txtAngariador.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecAngariador = new Button(composite, SWT.NONE);
		btnSelecAngariador.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecAngariador, value, "angariador");
		
		Button btnt = new Button(composite, SWT.NONE);
		btnt.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnt, value, "angariador", Funcionario.class);
		
		Label lblMetragem = new Label(composite, SWT.NONE);
		lblMetragem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMetragem.setText("Metragem");
		
		txtMetragem = new Text(composite, SWT.BORDER);
		txtMetragem.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		new FormattedText(txtMetragem).setFormatter(new NumberFormatter("##############"));
		
		Label lblObservaes_3 = new Label(composite, SWT.NONE);
		lblObservaes_3.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes_3.setText("Observações");
		
		txtObservacoes = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		GridData gd_text_6 = new GridData(SWT.FILL, SWT.FILL, true, false, 5, 1);
		gd_text_6.heightHint = 34;
		txtObservacoes.setLayoutData(gd_text_6);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		tfImovel = new CTabFolder(parent, SWT.BORDER);
		tfImovel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tfImovel.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		tbtmEndereo = new CTabItem(tfImovel, SWT.NONE);
		tbtmEndereo.setText("Endereço");
		
		Composite composite_1 = new Composite(tfImovel, SWT.NONE);
		tbtmEndereo.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		grpEndereco = new EnderecoGroup(composite_1, getCurrentObject().getEndereco(), SWT.NONE);
		grpEndereco.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		CTabItem tbtmDescrio_1 = new CTabItem(tfImovel, SWT.NONE);
		tbtmDescrio_1.setText("Cômodos");
		
		Composite composite_5 = new Composite(tfImovel, SWT.NONE);
		tbtmDescrio_1.setControl(composite_5);
		composite_5.setLayout(new GridLayout(1, false));
		
		Composite composite_10 = new Composite(composite_5, SWT.NONE);
		composite_10.setLayout(new GridLayout(1, false));
		composite_10.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		criarTabelaComodo(composite_10);
		
		Group grpCmodo = new Group(composite_5, SWT.NONE);
		grpCmodo.setLayout(new GridLayout(4, false));
		grpCmodo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpCmodo.setText("Cômodo");
		
		Label lblNome = new Label(grpCmodo, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome");
		
		text_26 = new Text(grpCmodo, SWT.BORDER | SWT.SEARCH | SWT.ICON_CANCEL);
		text_26.setEditable(false);
		text_26.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Button btnSelecionarComodo = new Button(grpCmodo, SWT.NONE);
		btnSelecionarComodo.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.COMODO, btnSelecionarComodo, valueComodo, "tipoComodo", new InputListener<TipoComodo>() {

			private TipoComodoService service = new TipoComodoService();
			
			@Override
			public List<TipoComodo> getInput() {
				return service.findByTipoImovel(getCurrentObject().getTipo());
			}
		});
		
		Button btnRemoverComodo = new Button(grpCmodo, SWT.NONE);
		btnRemoverComodo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnRemoverComodo.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(btnRemoverComodo, valueComodo, "tipoComodo", TipoComodo.class);
		
		Label lblQuantidade = new Label(grpCmodo, SWT.NONE);
		lblQuantidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblQuantidade.setText("Quantidade");
		
		NumberTextField numberTextField = new NumberTextField(grpCmodo);
		txtQuantidadeComodos = numberTextField.getControl();
		txtQuantidadeComodos.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpCmodo, SWT.NONE);
		new Label(grpCmodo, SWT.NONE);

		Label lblDescrio_3 = new Label(grpCmodo, SWT.NONE);
		lblDescrio_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrio_3.setText("Descrição");
		
		txtDescrio = new Text(grpCmodo, SWT.BORDER);
		txtDescrio.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpCmodo, SWT.NONE);
		new Label(grpCmodo, SWT.NONE);
		new Label(grpCmodo, SWT.NONE);
		new Label(grpCmodo, SWT.NONE);
		new Label(grpCmodo, SWT.NONE);
		
		createButtonAddItem(grpCmodo, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addComodo();
			}
		});
		
		CTabItem tbtmChaves = new CTabItem(tfImovel, SWT.NONE);
		tbtmChaves.setText("Chaves");
		
		Composite composite_2 = new Composite(tfImovel, SWT.NONE);
		tbtmChaves.setControl(composite_2);
		composite_2.setLayout(new GridLayout(1, false));
		
		Composite cpTvbChave = new Composite(composite_2, SWT.NONE);
		cpTvbChave.setLayout(new GridLayout(1, false));
		cpTvbChave.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		criarTabelaChave(cpTvbChave);
		
		Group grpChave = new Group(composite_2, SWT.NONE);
		grpChave.setText("Chave");
		grpChave.setLayout(new GridLayout(3, false));
		grpChave.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNmero_1 = new Label(grpChave, SWT.NONE);
		lblNmero_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmero_1.setText("Número");
		
		text_15 = new Text(grpChave, SWT.BORDER);
		text_15.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpChave, SWT.NONE);
		new Label(grpChave, SWT.NONE);
		
		radioGroupViewer = new RadioGroupViewer(grpChave, SWT.NONE);
		radioGroupViewer.setContentProvider(ArrayContentProvider.getInstance());
		
		RadioGroup radioGroup = radioGroupViewer.getRadioGroup();
		radioGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		radioGroupViewer.setInput(new Object[]{LocalizacaoChave.CLIENTE, LocalizacaoChave.IMOBILIARIA});
		
		new Label(grpChave, SWT.NONE);
		new Label(grpChave, SWT.NONE);
		new Label(grpChave, SWT.NONE);
		
		Button createButtonAddItem = createButtonAddItem(grpChave, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addChave();
			}
		});
		new Label(grpChave, SWT.NONE);
		createButtonAddItem.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false, 1, 1));
		
//		GridData gd_btnAddChave = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
//		gd_btnAddChave.heightHint = 30;
//		gd_btnAddChave.widthHint = 84;
//		btnAddChave.setLayoutData(gd_btnAddChave);
		
		CTabItem tbtmHistricos = new CTabItem(tfImovel, SWT.NONE);
		tbtmHistricos.setText("Feedbacks");
		
		Composite composite_3 = new Composite(tfImovel, SWT.NONE);
		tbtmHistricos.setControl(composite_3);
		composite_3.setLayout(new GridLayout(1, false));
		
		Composite cpTvbHistorico = new Composite(composite_3, SWT.NONE);
		cpTvbHistorico.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		cpTvbHistorico.setLayout(new GridLayout(1, false));
		
		criarTabelaFeedback(cpTvbHistorico);
		
		Group grpNovoHistrico = new Group(composite_3, SWT.NONE);
		GridLayout gl_grpNovoHistrico = new GridLayout(4, false);
		grpNovoHistrico.setLayout(gl_grpNovoHistrico);
		grpNovoHistrico.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpNovoHistrico.setText("Feedback");
		
		Label lblData_1 = new Label(grpNovoHistrico, SWT.NONE);
		lblData_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_1.setText("Data");
		
		DateTimeTextField dateTextField = new DateTimeTextField(grpNovoHistrico);
		txtDataFeedback = dateTextField.getControl();
		txtDataFeedback.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpNovoHistrico, SWT.NONE);
		new Label(grpNovoHistrico, SWT.NONE);
		
		Label lblFuncionrio_1 = new Label(grpNovoHistrico, SWT.NONE);
		lblFuncionrio_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio_1.setText("Funcionário");
		
		text_12 = new Text(grpNovoHistrico, SWT.BORDER);
		text_12.setEditable(false);
		text_12.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionarFuncionarioFeedback = new Button(grpNovoHistrico, SWT.NONE);
		btnSelecionarFuncionarioFeedback.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionarFuncionarioFeedback, valueFeedback, "funcionario");
		
		Button button_3 = new Button(grpNovoHistrico, SWT.NONE);
		button_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_3.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_3, valueFeedback, "funcionario", Funcionario.class);
		
		Label lblClienteHistorico = new Label(grpNovoHistrico, SWT.NONE);
		lblClienteHistorico.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblClienteHistorico.setText("Cliente");
		
		text_14 = new Text(grpNovoHistrico, SWT.BORDER);
		text_14.setEditable(false);
		text_14.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionarClienteFeedback = new Button(grpNovoHistrico, SWT.NONE);
		btnSelecionarClienteFeedback.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, btnSelecionarClienteFeedback, valueFeedback, "cliente");
		
		Button button_4 = new Button(grpNovoHistrico, SWT.NONE);
		button_4.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_4, valueFeedback, "cliente", Cliente.class);
		
		Label lblObservaes = new Label(grpNovoHistrico, SWT.NONE);
		lblObservaes.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes.setText("Observações");
		
		text_13 = new Text(grpNovoHistrico, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_text_13 = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_text_13.heightHint = 51;
		text_13.setLayoutData(gd_text_13);
		new Label(grpNovoHistrico, SWT.NONE);
		new Label(grpNovoHistrico, SWT.NONE);
		new Label(grpNovoHistrico, SWT.NONE);
		
		createButtonAddItem(grpNovoHistrico, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addFeedback();
			}
		});
		
//		Button button_5 = new Button(grpNovoHistrico, SWT.NONE);
//		button_5.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				addFeedback();
//			}
//		});
//		button_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
//		button_5.setImage(ImageRepository.ADD_16.getImage());
		
		CTabItem tbtmFotos = new CTabItem(tfImovel, SWT.NONE);
		tbtmFotos.setText("Fotos");
		
		PhotoComposite photoComposite = new PhotoComposite(tfImovel, SWT.NONE, value);
		tbtmFotos.setControl(photoComposite);
		
		CTabItem tbtmPropostas = new CTabItem(tfImovel, SWT.NONE);
		tbtmPropostas.setText("Propostas");
		
		Composite composite_4 = new Composite(tfImovel, SWT.NONE);
		tbtmPropostas.setControl(composite_4);
		composite_4.setLayout(new GridLayout(1, false));
		
		Composite cpTvbProposta = new Composite(composite_4, SWT.NONE);
		cpTvbProposta.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		criarTabelaProposta(cpTvbProposta);
		
		Group grpProposta = new Group(composite_4, SWT.NONE);
		grpProposta.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpProposta.setText("Proposta");
		GridLayout gl_grpProposta = new GridLayout(4, false);
		grpProposta.setLayout(gl_grpProposta);
		
		Label lblData = new Label(grpProposta, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		DateTextField dateTextField_1 = new DateTextField(grpProposta);
		text_33 = dateTextField_1.getControl();
		text_33.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpProposta, SWT.NONE);
		new Label(grpProposta, SWT.NONE);
		
		Label lblCliente = new Label(grpProposta, SWT.NONE);
		lblCliente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCliente.setText("Cliente");
		
		text_9 = new Text(grpProposta, SWT.BORDER);
		text_9.setEditable(false);
		text_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionarClienteProposta = new Button(grpProposta, SWT.NONE);
		btnSelecionarClienteProposta.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, btnSelecionarClienteProposta, valueProposta, "cliente");
		
		Button button_5 = new Button(grpProposta, SWT.NONE);
		button_5.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_5, valueProposta, "cliente", Cliente.class);
		
		Label lblFuncionrio = new Label(grpProposta, SWT.NONE);
		lblFuncionrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio.setText("Funcionário");
		
		text_11 = new Text(grpProposta, SWT.BORDER);
		text_11.setEditable(false);
		text_11.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Button btnSelecionarFuncionarioProposta = new Button(grpProposta, SWT.NONE);
		btnSelecionarFuncionarioProposta.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionarFuncionarioProposta, valueProposta, "funcionario");
		
		Button button_6 = new Button(grpProposta, SWT.NONE);
		button_6.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_6.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_6, valueProposta, "funcionario", Funcionario.class);
		
		Label lblValor_1 = new Label(grpProposta, SWT.NONE);
		lblValor_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_1.setText("Valor");
		
		MoneyTextField moneyTextField_1 = new MoneyTextField(grpProposta);
		text_29 = moneyTextField_1.getControl();
		GridData gd_text_29 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_29.widthHint = 114;
		text_29.setLayoutData(gd_text_29);
		new Label(grpProposta, SWT.NONE);
		new Label(grpProposta, SWT.NONE);
		
		Label lblObservaes_2 = new Label(grpProposta, SWT.NONE);
		lblObservaes_2.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes_2.setText("Observações");
		
		text_20 = new Text(grpProposta, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		GridData gd_text_20 = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_text_20.heightHint = 49;
		text_20.setLayoutData(gd_text_20);
		new Label(grpProposta, SWT.NONE);
		new Label(grpProposta, SWT.NONE);
		new Label(grpProposta, SWT.NONE);
		
		createButtonAddItem(grpProposta, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addProposta();
			}
		});
		
		{
			tfImovel.setSelection(0);
		}
		
		CTabItem tbtmReservas_1 = new CTabItem(tfImovel, SWT.NONE);
		tbtmReservas_1.setText("Reservas");
		
		Composite composite_17 = new Composite(tfImovel, SWT.NONE);
		tbtmReservas_1.setControl(composite_17);
		composite_17.setLayout(new GridLayout(1, false));
		
		Composite composite_18 = new Composite(composite_17, SWT.NONE);
		composite_18.setLayout(new GridLayout(1, false));
		composite_18.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		criarTabelaReservas(composite_18);
		
		Group grpReserva = new Group(composite_17, SWT.NONE);
		grpReserva.setLayout(new GridLayout(4, false));
		grpReserva.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		grpReserva.setText("Reserva");
		
		Label lblData_6 = new Label(grpReserva, SWT.NONE);
		lblData_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_6.setText("Data da Reserva");
		
		DateTextField dateTextField_5 = new DateTextField(grpReserva);
		text_30 = dateTextField_5.getControl();
		text_30.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpReserva, SWT.NONE);
		new Label(grpReserva, SWT.NONE);
		
		Label lblDataDeVencimento = new Label(grpReserva, SWT.NONE);
		lblDataDeVencimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento.setText("Data de Vencimento");
		
		DateTextField dateTextField_6 = new DateTextField(grpReserva);
		text_37 = dateTextField_6.getControl();
		text_37.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpReserva, SWT.NONE);
		new Label(grpReserva, SWT.NONE);
		
		Label lblCliente_2 = new Label(grpReserva, SWT.NONE);
		lblCliente_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCliente_2.setText("Cliente");
		
		text_38 = new Text(grpReserva, SWT.BORDER);
		text_38.setEditable(false);
		text_38.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_20 = new Button(grpReserva, SWT.NONE);
		button_20.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, button_20, valueReserva, "cliente");
		
		Button button_7 = new Button(grpReserva, SWT.NONE);
		button_7.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_7.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_7, valueReserva, "cliente", Cliente.class);
		
		Label lblCorretor = new Label(grpReserva, SWT.NONE);
		lblCorretor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCorretor.setText("Corretor");
		
		text_40 = new Text(grpReserva, SWT.BORDER);
		text_40.setEditable(false);
		text_40.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_21 = new Button(grpReserva, SWT.NONE);
		button_21.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, button_21, valueReserva, "funcionario");
		
		Button button_8 = new Button(grpReserva, SWT.NONE);
		button_8.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_8, valueReserva, "funcionario", Funcionario.class);
		
		Label lblValor_2 = new Label(grpReserva, SWT.NONE);
		lblValor_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_2.setText("Valor");
		
		MoneyTextField moneyTextField_2 = new MoneyTextField(grpReserva);
		text_39 = moneyTextField_2.getControl();
		text_39.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpReserva, SWT.NONE);
		new Label(grpReserva, SWT.NONE);
		
		Label lblDescrio_4 = new Label(grpReserva, SWT.NONE);
		lblDescrio_4.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio_4.setText("Observações");
		
		text_41 = new Text(grpReserva, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		GridData gd_text_41 = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_text_41.heightHint = 28;
		text_41.setLayoutData(gd_text_41);
		new Label(grpReserva, SWT.NONE);
		new Label(grpReserva, SWT.NONE);
		new Label(grpReserva, SWT.NONE);
		
		createButtonAddItem(grpReserva, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					addReserva();
				} catch (ValidationException e1) {
					new ValidationErrorDialog(ShellHelper.getActiveShell(), e1.getMessage()).open();
				}
			}
		});
		
		CTabItem tbtmContratoPrestaoDe = new CTabItem(tfImovel, SWT.NONE);
		tbtmContratoPrestaoDe.setText("Contrato Prestação de Serviço");
		
		Composite composite_7 = new Composite(tfImovel, SWT.NONE);
		tbtmContratoPrestaoDe.setControl(composite_7);
		composite_7.setLayout(new GridLayout(1, false));
		
		Composite composite_8 = new Composite(composite_7, SWT.NONE);
		composite_8.setLayout(new GridLayout(1, false));
		GridData gd_composite_8 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite_8.heightHint = 77;
		composite_8.setLayoutData(gd_composite_8);
		criarTabelaContratoPrestacaoServico(composite_8);
		
		Group grpContratoPrestaoDe = new Group(composite_7, SWT.NONE);
		grpContratoPrestaoDe.setLayout(new GridLayout(4, false));
		grpContratoPrestaoDe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpContratoPrestaoDe.setText("Contrato Prestação de Serviço");
		
		Label lblFuncionrio_2 = new Label(grpContratoPrestaoDe, SWT.NONE);
		lblFuncionrio_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFuncionrio_2.setText("Funcionário");
		
		text_10 = new Text(grpContratoPrestaoDe, SWT.BORDER);
		text_10.setEditable(false);
		text_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(grpContratoPrestaoDe, SWT.NONE);
		button.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, button, valueContrato, "funcionario");
		
		Button button_9 = new Button(grpContratoPrestaoDe, SWT.NONE);
		button_9.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_9.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_9, valueContrato, "funcionario", Funcionario.class);
		
		new Label(grpContratoPrestaoDe, SWT.NONE);
		
		radioGroupViewer_1 = new RadioGroupViewer(grpContratoPrestaoDe, SWT.NONE);
		RadioGroup radioGroup_1 = radioGroupViewer_1.getRadioGroup();
		radioGroup_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		radioGroupViewer_1.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return element.toString();
			}
		});
		radioGroupViewer_1.setContentProvider(ArrayContentProvider.getInstance());
		radioGroupViewer_1.setInput(ContratoPrestacaoServico.TipoContrato.values());
		new Label(grpContratoPrestaoDe, SWT.NONE);
		new Label(grpContratoPrestaoDe, SWT.NONE);
		
		Label lblData_2 = new Label(grpContratoPrestaoDe, SWT.NONE);
		lblData_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_2.setText("Data");
		
		DateTextField dateTextField_2 = new DateTextField(grpContratoPrestaoDe);
		text = dateTextField_2.getControl();
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpContratoPrestaoDe, SWT.NONE);
		new Label(grpContratoPrestaoDe, SWT.NONE);
		
		Label lblDataDeVencimento_1 = new Label(grpContratoPrestaoDe, SWT.NONE);
		lblDataDeVencimento_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento_1.setText("Data de Vencimento");
		
		DateTextField dateTextField_3 = new DateTextField(grpContratoPrestaoDe);
		text_16 = dateTextField_3.getControl();
		text_16.setText("");
		text_16.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpContratoPrestaoDe, SWT.NONE);
		new Label(grpContratoPrestaoDe, SWT.NONE);
		
		Label lblValor = new Label(grpContratoPrestaoDe, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor do imóvel");
		
		MoneyTextField moneyTextField = new MoneyTextField(grpContratoPrestaoDe);
		txtValorImovel = moneyTextField.getControl();
		txtValorImovel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpContratoPrestaoDe, SWT.NONE);
		new Label(grpContratoPrestaoDe, SWT.NONE);
		
		Label lblContrato = new Label(grpContratoPrestaoDe, SWT.NONE);
		lblContrato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblContrato.setText("Contrato");
		
		txtModeloContratoPrestacaoServico = new Text(grpContratoPrestaoDe, SWT.BORDER | SWT.READ_ONLY);
		txtModeloContratoPrestacaoServico.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_2 = new Button(grpContratoPrestaoDe, SWT.NONE);
		button_2.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.MODELO_CONTRATO, button_2, valueContrato, "modeloContrato");
		
		Button button_10 = new Button(grpContratoPrestaoDe, SWT.NONE);
		button_10.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionToRemoveButton(button_10, valueContrato, "modeloContrato", ModeloContrato.class);
		new Label(grpContratoPrestaoDe, SWT.NONE);
		
		btnDivulgar = new Button(grpContratoPrestaoDe, SWT.CHECK);
		btnDivulgar.setText("Divulgar");
		new Label(grpContratoPrestaoDe, SWT.NONE);
		
		createButtonAddItem(grpContratoPrestaoDe, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addContratoPrestacaoServico();
			}
		});
		
		CTabItem tbtmLocacoes = new CTabItem(tfImovel, SWT.NONE);
		tbtmLocacoes.setText("Locações");
		
		Composite composite_6 = new Composite(tfImovel, SWT.NONE);
		tbtmLocacoes.setControl(composite_6);
		
		criarTabelaLocacoes(composite_6);
	}
	
	@Override
	protected void beforeSetIObservableValue(Imovel obj) {
		limparTabelas();
	}
	
	@Override
	protected void afterSetIObservableValue(Imovel obj) {
		Imovel imovel = getCurrentObject();
		
		valueComodo.setValue(new Comodo(imovel));
		valueChave.setValue(new Chave(imovel));
		valueFeedback.setValue(new Feedback(imovel));
		valueProposta.setValue(new Proposta(imovel));
		valueReserva.setValue(new Reserva(imovel));
		valueContrato.setValue(new ContratoPrestacaoServico(imovel));
		
		if(grpEndereco != null)
			grpEndereco.setEndereco(getCurrentObject().getEndereco());
		
	}
	
	private void criarTabelaContratoPrestacaoServico(Composite composite) {
		tvbContatoPrestacaoServico = new TableViewerBuilder(composite);
		
		tvbContatoPrestacaoServico.createColumn("Data").setPercentWidth(10).bindToProperty("dataInicio").format(new DateStringValueFormatter()).build();
		tvbContatoPrestacaoServico.createColumn("Data de Vencimento").setPercentWidth(10).bindToProperty("dataVencimento").format(new DateStringValueFormatter()).build();
		tvbContatoPrestacaoServico.createColumn("Valor de Imóvel").setPercentWidth(10).bindToProperty("valorImovel").format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
		tvbContatoPrestacaoServico.createColumn("Funcionário").setPercentWidth(20).format(new NullStringValueFormatter()).bindToProperty("funcionario.nome").build();
		tvbContatoPrestacaoServico.createColumn("Tipo").setPercentWidth(10).bindToProperty("tipo").build();
		tvbContatoPrestacaoServico.createColumn("Divulgar").bindToProperty("divulgarExtenso").build();
		
		tvbContatoPrestacaoServico.setInput(getCurrentObject().getContratos());
		tvContratosPrestacaoServico = tvbContatoPrestacaoServico.getTableViewer();
		
		Menu menus = WidgetHelper.addMenusToTable(tvbContatoPrestacaoServico, new ContratoPrestacaoServicoService(), valueContrato);
		
		MenuItem miGerarContrato = new MenuItem(menus, SWT.BORDER);
		miGerarContrato.setText("Gerar contrato");
		miGerarContrato.setImage(ImageRepository.CONTRATO_16.getImage());
		miGerarContrato.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ContratoPrestacaoServico contrato = (ContratoPrestacaoServico) SelectionHelper.getObject(tvContratosPrestacaoServico.getSelection());
				
				if(contrato == null)
					return;
				
				ContratoHelper.gerarContrato(contrato.getModeloContrato().getArquivo(), contrato);
			}
		});
		
	}

	private void criarTabelaReservas(Composite composite) {
		tvbReserva = new TableViewerBuilder(composite);
		
		tvbReserva.createColumn("Data da Reserva").setPercentWidth(10).bindToProperty("dataReserva").format(new DateStringValueFormatter()).build();
		tvbReserva.createColumn("Data de Vencimento").setPercentWidth(10).bindToProperty("dataVencimento").format(new DateStringValueFormatter()).build();
		tvbReserva.createColumn("Cliente").setPercentWidth(15).bindToProperty("cliente.nome").build();
		tvbReserva.createColumn("Funcionário").setPercentWidth(15).bindToProperty("funcionario.nome").format(new NullStringValueFormatter()).build();
		tvbReserva.createColumn("Valor").bindToProperty("valor").setPercentWidth(10).format(FormatterHelper.getDefaultValueFormatterToMoney()).build();
		tvbReserva.createColumn("Observações").setPercentWidth(40).bindToProperty("observacoes").format(new NullStringValueFormatter()).build();
		
		tvbReserva.setInput(((Imovel)value.getValue()).getReservas());
		
		tvReservas = tvbReserva.getTableViewer();
		
		WidgetHelper.addMenusToTable(tvbReserva, new ReservaService(), valueReserva);
	}

	private void criarTabelaChave(Composite composite){
		tvbChave = new TableViewerBuilder(composite);
		
		tvbChave.createColumn("Número").setPercentWidth(30).bindToProperty("numero").build();
		tvbChave.createColumn("Localização").setPercentWidth(70).bindToProperty("localizacao").build();
		
		tvbChave.setInput(((Imovel)value.getValue()).getChaves());
		
		tvChaves = tvbChave.getTableViewer();
		
		WidgetHelper.addMenusToTable(tvbChave, new ChaveService(), valueChave);
	}
	
	private void criarTabelaComodo(Composite composite){
		tvbComodo = new TableViewerBuilder(composite);
		
		tvbComodo.createColumn("Cômodo").setPercentWidth(30).bindToProperty("tipoComodo.nome").build();
		tvbComodo.createColumn("Quantidade").setPercentWidth(10).bindToProperty("quantidade").build();
		tvbComodo.createColumn("Descrição").setPercentWidth(60).bindToProperty("descricao").build();
		
		tvbComodo.setInput(((Imovel)value.getValue()).getComodos());
		
		tvComodos = tvbComodo.getTableViewer();
		
		WidgetHelper.addMenusToTable(tvbComodo, new ComodoService() , valueComodo);
	}
	
	private void criarTabelaFeedback(Composite composite){
		tvbFeedbacks = new TableViewerBuilder(composite);
		
		tvbFeedbacks.createColumn("Data da Visita").setPercentWidth(10).bindToProperty("data").format(new DateTimeStringValueFormatter()).build();
		tvbFeedbacks.createColumn("Funcionário").setPercentWidth(15).bindToProperty("funcionario.nome").format(new NullStringValueFormatter()).build();
		tvbFeedbacks.createColumn("Cliente").setPercentWidth(15).bindToProperty("cliente.nome").build();
		tvbFeedbacks.createColumn("Observações").setPercentWidth(60).bindToProperty("observacoes").build();
		
		tvbFeedbacks.setInput(((Imovel)value.getValue()).getFeedbacks());
		
		tvFeedbacks = tvbFeedbacks.getTableViewer();
		
		WidgetHelper.addMenusToTable(tvbFeedbacks, new FeedbackService(), valueFeedback);
	}
	
	private void criarTabelaProposta(Composite composite){
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite cpTable = new Composite(composite, SWT.NONE);
		cpTable.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		propostaXViewer = PropostaGenericXViewer.createXviewer(cpTable);
		propostaXViewer.setInput(((Imovel)value.getValue()).getPropostas());
		propostaTree = propostaXViewer.getTree();
		
		Menu menu = new Menu(propostaTree);
		propostaTree.setMenu(menu);
		
		WidgetHelper.createMenuItemAlterar(menu, valueProposta, propostaXViewer);
		
		MenuItem miContraProposta = new MenuItem(menu, SWT.BORDER);
		miContraProposta.setText("Contraproposta");
		miContraProposta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Proposta propostaSelecionada = (Proposta) SelectionHelper.getObject(propostaXViewer.getSelection());
				
				if(propostaSelecionada == null)
					return;
				
				if(propostaSelecionada.getContraProposta() != null){
					DialogHelper.openWarning("A proposta selecionada já possui uma contraproposta.");
					return;
				}
				
				if(propostaSelecionada.getStatus() != null){
					DialogHelper.openWarning("A proposta já foi fechada, para realizar uma contraproposta é preciso que ela seja aberta.");
					return;
				}
				
				ContraPropostaDialog dialog = new ContraPropostaDialog(ShellHelper.getActiveShell(), propostaSelecionada);
				if(dialog.open() == IDialogConstants.OK_ID){
					propostaXViewer.setInput(getCurrentObject().getPropostas());
				}
			}
		});
		
		MenuItem finalizarProposta = new MenuItem(menu, SWT.BORDER);
		finalizarProposta.setText("Fechar/Reabrir proposta");
		finalizarProposta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Proposta propostaSelecionada = (Proposta) SelectionHelper.getObject(propostaXViewer.getSelection());
				
				if(propostaSelecionada == null)
					return;
				
				if(propostaSelecionada.getContraProposta() != null){
					DialogHelper.openWarning("A proposta selecionada possui uma contraproposta.");
					return;
				}
				
				if(propostaSelecionada.getStatus() == null){
					new FinalizarPropostaDialog(ShellHelper.getActiveShell(), propostaSelecionada).open();
				} else {
					if(DialogHelper.openConfirmation("Deseja reabrir a proposta ? ")){
						propostaSelecionada.setStatus(null);
						propostaSelecionada.setDataFechamento(null);

						try {
							new PropostaService().salvar(propostaSelecionada);
						} catch (Exception e1) {
							log.error("Erro ao reabrir proposta.", e1);
						}
					}
				}
				
				propostaXViewer.setInput(getCurrentObject().getPropostas());
			}
		});
		
		WidgetHelper.createMenuItemRemover(propostaXViewer, new PropostaService(), menu);
	}

	private void criarTabelaLocacoes(Composite composite) {
		tvbLocacao = new TableViewerBuilder(composite);
		
		tvbLocacao.createColumn("Locatário").setPercentWidth(10).bindToProperty("cliente.nome").build();
		tvbLocacao.createColumn("Fiador").setPercentWidth(10).bindToProperty("fiador.nome").build();
		tvbLocacao.createColumn("Corretor").setPercentWidth(10).bindToProperty("funcionario.nome").build();
		tvbLocacao.createColumn("Valor").setPercentWidth(10).format(FormatterHelper.getDefaultValueFormatterToMoney()).bindToProperty("valor").build();
		tvbLocacao.createColumn("Data").setPercentWidth(10).format(new DateStringValueFormatter()).bindToProperty("dataAssinaturaContrato").build();
		tvbLocacao.createColumn("Data de Vencimento").setPercentWidth(10).bindToProperty("dataVencimento").format(new DateStringValueFormatter()).build();
		tvbLocacao.createColumn("Reajuste").setPercentWidth(10).bindToProperty("reajuste").build();
		
		tvbLocacao.setInput(new AluguelService().findByImovel(getCurrentObject()));
		tvbLocacao.getTableViewer().addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				Aluguel aluguel = (Aluguel) SelectionHelper.getObject(tvbLocacao.getTableViewer());
				AluguelEditorInput ei = new AluguelEditorInput();
				ei.setModelo(aluguel);
				
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(ei, AluguelEditor.ID);
				} catch (PartInitException e) {
					log.error("Erro ao abrir tela de locação.", e);
				}
			}
		});
		
		tvbLocacao.getTableViewer();
	}
	
	private void addContratoPrestacaoServico() {
		ContratoPrestacaoServico contratoPrestacaoServico = (ContratoPrestacaoServico)valueContrato.getValue();
		
		if(DateHelper.zerarHoraMinutos(contratoPrestacaoServico.getDataInicio()).compareTo(DateHelper.zerarHoraMinutos(contratoPrestacaoServico.getDataVencimento())) > 0){
			DialogHelper.openWarning("A data do contrato não pode ser maior que a data de vencimento.");
			return;
		}
			
		
		contratoPrestacaoServico.setCliente(getCurrentObject().getProprietario());
		addItens(new ContratoPrestacaoServicoService(), valueContrato, tvContratosPrestacaoServico, getCurrentObject().getContratos());
	}
	
	private void addChave() {
		addItens(new ChaveService(), valueChave, tvChaves, getCurrentObject().getChaves());
	}
	
	private void addProposta() {
		addItens(new PropostaService(), valueProposta, propostaXViewer, getCurrentObject().getPropostas(), false);
		propostaXViewer.setInput(getCurrentObject().getPropostas());
	}
	
	private void addComodo() {
		addItens(new ComodoService(), valueComodo, tvComodos, getCurrentObject().getComodos());
	}
	
	private void addFeedback() {
		addItens(new FeedbackService(), valueFeedback, tvFeedbacks, getCurrentObject().getFeedbacks());
	}
	
	private void addReserva() throws ValidationException {
		ReservaService reservaService = new ReservaService();
		Reserva reserva = (Reserva) valueReserva.getValue();
		
		if(reserva.getDataReserva().compareTo(reserva.getDataVencimento()) > 0)
			throw new ValidationException("A data de vencimento não pode ser menor que a data da reserva");

		List<Reserva> intersecao = reservaService.findIntersecao(reserva);
		for(Reserva i : intersecao)
			if(!i.equals(reserva))
				throw new ValidationException("Já existe uma reserva entre que acaba ou começa entre os mesmos dias.");
		
		addItens(reservaService, valueReserva, tvReservas, getCurrentObject().getReservas());
	}
	
	@Override
	public void saveCurrentObject(GenericService<Imovel> service) {

		boolean isNewObject = getCurrentObject().getId() == null;
		
		if(validarComMensagem(getCurrentObject().getEndereco()) && validarComMensagem(getCurrentObject())){
			super.saveCurrentObject(service);
			
			if(isNewObject){
				List<TipoComodo> tiposComodo = new TipoComodoService().findByTipoComodoAndSelecionadoIsTrue(getCurrentObject().getTipo());

				if(tiposComodo == null || tiposComodo.isEmpty())
					return;
				
				ComodoService comodoService = new ComodoService();
				List<Comodo> comodos = Lists.newArrayList();
				
				for(TipoComodo tipoComodo : tiposComodo){
					Comodo comodo = new Comodo(getCurrentObject());
					comodo.setDescricao("");
					comodo.setTipoComodo(tipoComodo);
					comodo.setQuantidade(1);
					
					comodos.add(comodo);
				}
				
				try {
					comodoService.salvar(comodos);
					
					Imovel imovel = getCurrentObject();
					
					value.setValue(null);
					value.setValue(imovel);

					tvComodos.setInput(imovel.getComodos());
					
					tfImovel.setSelection(1);
				} catch (Exception e) {
					log.error("Erro ao salvar cômodos do imóvel.", e);
				}
			}
		}
	}
	
	private void limparTabelas(){
		if(tvbChave == null)
			return;
		
		tvbChave.getTable().removeAll();
		tvbComodo.getTable().removeAll();
		tvbContatoPrestacaoServico.getTable().removeAll();
		tvbFeedbacks.getTable().removeAll();
		tvbReserva.getTable().removeAll();
		propostaXViewer.getTree().removeAll();
		tvbLocacao.getTable().removeAll();
	}
	
	protected DataBindingContext bindTables(DataBindingContext bindingContext){
		//
		IObservableValue observeSingleSelectionTableViewerComodo = ViewerProperties.input().observe(tvComodos);
		IObservableValue valueObserveDetailValueComodo = PojoProperties.value(Imovel.class, "comodos", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTableViewerComodo, valueObserveDetailValueComodo, null, null);
		//
		IObservableValue observeSingleSelectionTableViewerChave = ViewerProperties.input().observe(tvChaves);
		IObservableValue valueComissoesObserveDetailValueChave = PojoProperties.value(Imovel.class, "chaves", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTableViewerChave, valueComissoesObserveDetailValueChave, null, null);
		//
		IObservableValue observeSingleSelectionTableViewerFeedback = ViewerProperties.input().observe(tvFeedbacks);
		IObservableValue valueComissoesObserveDetailValueFeedback = PojoProperties.value(Imovel.class, "feedbacks", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTableViewerFeedback, valueComissoesObserveDetailValueFeedback, null, null);
		//
		IObservableValue observeSingleSelectionTableViewerReserva = ViewerProperties.input().observe(tvReservas);
		IObservableValue valueComissoesObserveDetailValueReserva = PojoProperties.value(Imovel.class, "reservas", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTableViewerReserva, valueComissoesObserveDetailValueReserva, null, null);
		//
		IObservableValue observeSingleSelectionTableViewerProposta = ViewerProperties.input().observe(propostaXViewer);
		IObservableValue valueComissoesObserveDetailValueProposta = PojoProperties.value(Imovel.class, "propostas", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTableViewerProposta, valueComissoesObserveDetailValueProposta, null, null);
		//
		IObservableValue observeSingleSelectionTableViewerContrato = ViewerProperties.input().observe(tvContratosPrestacaoServico);
		IObservableValue valueComissoesObserveDetailValueContrato = PojoProperties.value(Imovel.class, "contratos", List.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionTableViewerContrato, valueComissoesObserveDetailValueContrato, null, null);
		//
		return bindingContext;
	}
	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtAngariador);
		IObservableValue valueAngariadornomeObserveDetailValue = PojoProperties.value(Imovel.class, "angariador.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueAngariadornomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtProprietarioObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtProprietario);
		IObservableValue valueProprietarionomeObserveDetailValue = PojoProperties.value(Imovel.class, "proprietario.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtProprietarioObserveWidget, valueProprietarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_5ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtMetragem);
		IObservableValue valueAngariadornomeObserveDetailValue_1 = PojoProperties.value(Imovel.class, "metragem", Integer.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_5ObserveWidget, valueAngariadornomeObserveDetailValue_1, null, null);
		//
		IObservableValue observeTextText_21ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtCodigoImovel);
		IObservableValue valueIdObserveDetailValue = PojoProperties.value(Imovel.class, "id", Long.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_21ObserveWidget, valueIdObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionComboViewer_4 = ViewerProperties.singleSelection().observe(cvTipoImovel);
		IObservableValue valueMetragemObserveDetailValue = PojoProperties.value(Imovel.class, "tipo", TipoImovel.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer_4, valueMetragemObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_6ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtObservacoes);
		IObservableValue valueObservacoesObserveDetailValue = PojoProperties.value(Imovel.class, "observacoes", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_6ObserveWidget, valueObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_26ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_26);
		IObservableValue valueComodoTipoComodoObserveDetailValue = PojoProperties.value(Comodo.class, "tipoComodo", TipoComodo.class).observeDetail(valueComodo);
		bindingContext.bindValue(observeTextText_26ObserveWidget, valueComodoTipoComodoObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtQuantidadeComodosObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtQuantidadeComodos);
		IObservableValue valueComodoQuantidadeObserveDetailValue = PojoProperties.value(Comodo.class, "quantidade", Integer.class).observeDetail(valueComodo);
		bindingContext.bindValue(observeTextTxtQuantidadeComodosObserveWidget, valueComodoQuantidadeObserveDetailValue, UVSHelper.uvsStringToInteger(), UVSHelper.uvsIntegerToString());
		//
		IObservableValue observeTextTxtDescrioObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDescrio);
		IObservableValue valueComodoDescricaoObserveDetailValue = PojoProperties.value(Comodo.class, "descricao", String.class).observeDetail(valueComodo);
		bindingContext.bindValue(observeTextTxtDescrioObserveWidget, valueComodoDescricaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_32ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataFeedback);
		IObservableValue valueFeedbackDataObserveDetailValue = PojoProperties.value(Feedback.class, "data", Date.class).observeDetail(valueFeedback);
		bindingContext.bindValue(observeTextText_32ObserveWidget, valueFeedbackDataObserveDetailValue, UVSHelper.uvsStringToDateTimeStamp(), UVSHelper.uvsDateTimeStampToString());
		//
		IObservableValue observeTextText_12ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_12);
		IObservableValue valueFeedbackFuncionarionomeObserveDetailValue = PojoProperties.value(Feedback.class, "funcionario.nome", String.class).observeDetail(valueFeedback);
		bindingContext.bindValue(observeTextText_12ObserveWidget, valueFeedbackFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_14ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_14);
		IObservableValue valueFeedbackClientenomeObserveDetailValue = PojoProperties.value(Feedback.class, "cliente.nome", String.class).observeDetail(valueFeedback);
		bindingContext.bindValue(observeTextText_14ObserveWidget, valueFeedbackClientenomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_13ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_13);
		IObservableValue valueFeedbackObservacoesObserveDetailValue = PojoProperties.value(Feedback.class, "observacoes", String.class).observeDetail(valueFeedback);
		bindingContext.bindValue(observeTextText_13ObserveWidget, valueFeedbackObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_9ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_9);
		IObservableValue valuePropostaClientenomeObserveDetailValue = PojoProperties.value(Proposta.class, "cliente.nome", String.class).observeDetail(valueProposta);
		bindingContext.bindValue(observeTextText_9ObserveWidget, valuePropostaClientenomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_11ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_11);
		IObservableValue valuePropostaFuncionarionomeObserveDetailValue = PojoProperties.value(Proposta.class, "funcionario.nome", String.class).observeDetail(valueProposta);
		bindingContext.bindValue(observeTextText_11ObserveWidget, valuePropostaFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_29ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_29);
		IObservableValue valuePropostaValorObserveDetailValue = PojoProperties.value(Proposta.class, "valor", BigDecimal.class).observeDetail(valueProposta);
		Binding bindValue = bindingContext.bindValue(observeTextText_29ObserveWidget, valuePropostaValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		ControlDecorationSupport.create(bindValue, SWT.LEFT | SWT.TOP);
		//
		IObservableValue observeTextText_20ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_20);
		IObservableValue valuePropostaObservacoesObserveDetailValue = PojoProperties.value(Proposta.class, "observacoes", String.class).observeDetail(valueProposta);
		bindingContext.bindValue(observeTextText_20ObserveWidget, valuePropostaObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_33ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_33);
		IObservableValue valuePropostaDataObserveDetailValue = PojoProperties.value(Proposta.class, "data", Date.class).observeDetail(valueProposta);
		bindingContext.bindValue(observeTextText_33ObserveWidget, valuePropostaDataObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_30ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_30);
		IObservableValue valueReservadataReservaObserveDetailValue = PojoProperties.value(Reserva.class, "dataReserva", Date.class).observeDetail(valueReserva);
		bindingContext.bindValue(observeTextText_30ObserveWidget, valueReservadataReservaObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_37ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_37);
		IObservableValue valueReservaDataVencimentoObserveDetailValue = PojoProperties.value(Reserva.class, "dataVencimento", Date.class).observeDetail(valueReserva);
		bindingContext.bindValue(observeTextText_37ObserveWidget, valueReservaDataVencimentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_38ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_38);
		IObservableValue valueReservaClientenomeObserveDetailValue = PojoProperties.value(Reserva.class, "cliente.nome", String.class).observeDetail(valueReserva);
		bindingContext.bindValue(observeTextText_38ObserveWidget, valueReservaClientenomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_40ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_40);
		IObservableValue valuePropostaFuncionarionomeObserveDetailValue_1 = PojoProperties.value(Reserva.class, "funcionario.nome", String.class).observeDetail(valueReserva);
		bindingContext.bindValue(observeTextText_40ObserveWidget, valuePropostaFuncionarionomeObserveDetailValue_1, null, null);
		//
		IObservableValue observeTextText_39ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_39);
		IObservableValue valueReservaValorObserveDetailValue = PojoProperties.value(Reserva.class, "valor", BigDecimal.class).observeDetail(valueReserva);
		Binding bindValue2 = bindingContext.bindValue(observeTextText_39ObserveWidget, valueReservaValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		ControlDecorationSupport.create(bindValue2, SWT.LEFT | SWT.TOP);
		//
		IObservableValue observeTextText_41ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_41);
		IObservableValue valueReservaObservacoesObserveDetailValue = PojoProperties.value(Reserva.class, "observacoes", String.class).observeDetail(valueReserva);
		bindingContext.bindValue(observeTextText_41ObserveWidget, valueReservaObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionRadioGroupViewer = ViewerProperties.singleSelection().observe(radioGroupViewer);
		IObservableValue valueChaveLocalizacaoObserveDetailValue = PojoProperties.value(Chave.class, "localizacao", LocalizacaoChave.class).observeDetail(valueChave);
		bindingContext.bindValue(observeSingleSelectionRadioGroupViewer, valueChaveLocalizacaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_15ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_15);
		IObservableValue valueChaveNumeroObserveDetailValue = PojoProperties.value(Chave.class, "numero", String.class).observeDetail(valueChave);
		bindingContext.bindValue(observeTextText_15ObserveWidget, valueChaveNumeroObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionRadioGroupViewer_1 = ViewerProperties.singleSelection().observe(radioGroupViewer_1);
		IObservableValue valueContratoTipoObserveDetailValue = PojoProperties.value(ContratoPrestacaoServico.class, "tipo", TipoContrato.class).observeDetail(valueContrato);
		bindingContext.bindValue(observeSingleSelectionRadioGroupViewer_1, valueContratoTipoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_10ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_10);
		IObservableValue valueContratoFuncionarionomeObserveDetailValue = PojoProperties.value(ContratoPrestacaoServico.class, "funcionario.nome", String.class).observeDetail(valueContrato);
		bindingContext.bindValue(observeTextText_10ObserveWidget, valueContratoFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeModeloContratoObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtModeloContratoPrestacaoServico);
		IObservableValue valueContratoModeloContratoObserveDetailValue = PojoProperties.value(ContratoPrestacaoServico.class, "modeloContrato.nome", String.class).observeDetail(valueContrato);
		bindingContext.bindValue(observeModeloContratoObserveWidget, valueContratoModeloContratoObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueContratoDataInicioObserveDetailValue = PojoProperties.value(ContratoPrestacaoServico.class, "dataInicio", Date.class).observeDetail(valueContrato);
		bindingContext.bindValue(observeTextTextObserveWidget, valueContratoDataInicioObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_16ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_16);
		IObservableValue valueContratoDataVencimentoObserveDetailValue = PojoProperties.value(ContratoPrestacaoServico.class, "dataVencimento", Date.class).observeDetail(valueContrato);
		bindingContext.bindValue(observeTextText_16ObserveWidget, valueContratoDataVencimentoObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_4ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtValorImovel);
		IObservableValue valueContratoValorObserveDetailValue = PojoProperties.value(ContratoPrestacaoServico.class, "valorImovel", BigDecimal.class).observeDetail(valueContrato);
		bindingContext.bindValue(observeTextText_4ObserveWidget, valueContratoValorObserveDetailValue, null, null);
		//
		IObservableValue observeSelectionBtnDivulgarObserveWidget = WidgetProperties.selection().observe(btnDivulgar);
		IObservableValue valueContratoDivulgarObserveDetailValue = PojoProperties.value(ContratoPrestacaoServico.class, "divulgar", Boolean.class).observeDetail(valueContrato);
		bindingContext.bindValue(observeSelectionBtnDivulgarObserveWidget, valueContratoDivulgarObserveDetailValue, null, null);
		//
		for(CTabItem c : tfImovel.getItems()){
			if(c.equals(tbtmEndereo))
				continue;
			bindingContext.bindValue(WidgetProperties.enabled().observe(c.getControl()), PojoProperties.value(Imovel.class, "id", Long.class).observeDetail(value), null, UVSHelper.uvsLongIsNull());
		}
		//
		return bindingContext;
	}

}
