package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.service.ImovelService;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.tela.widget.EnderecoGroup;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class BuscaAvancadaImovelView extends ViewPart {

	public static final String ID = "br.com.michelon.softimob.tela.view.BuscaAvancadaImovelView"; //$NON-NLS-1$
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private TableViewerBuilder tvbComodo;
	private Text text_21;

	public BuscaAvancadaImovelView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(1, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite = new GridLayout(5, false);
		gl_composite.verticalSpacing = 10;
		composite.setLayout(gl_composite);
		
		Label lblCdigo = new Label(composite, SWT.NONE);
		lblCdigo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCdigo.setText("Código");
		
		text_21 = new Text(composite, SWT.BORDER);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblValor = new Label(composite, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, true, 1, 1));
		lblValor.setText("Valor de");
		
		Composite composite_4 = new Composite(composite, SWT.NONE);
		GridLayout gl_composite_4 = new GridLayout(3, false);
		gl_composite_4.marginWidth = 0;
		composite_4.setLayout(gl_composite_4);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 2));
		
		MoneyTextField moneyTextField = new MoneyTextField(composite_4);
		text_3 = moneyTextField.getControl();
		
		Label lblAt = new Label(composite_4, SWT.NONE);
		lblAt.setText("até");
		
		MoneyTextField moneyTextField_1 = new MoneyTextField(composite_4);
		text = moneyTextField_1.getControl();
		
		text_2 = new Text(composite_4, SWT.BORDER);
		
		Label label = new Label(composite_4, SWT.NONE);
		label.setText("até");
		
		text_1 = new Text(composite_4, SWT.BORDER);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblMetragem = new Label(composite, SWT.NONE);
		lblMetragem.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		lblMetragem.setText("Metragem de");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Composite composite_15 = new Composite(composite, SWT.NONE);
		composite_15.setLayout(new GridLayout(2, false));
		composite_15.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Button btnVenda = new Button(composite_15, SWT.CHECK);
		btnVenda.setSelection(true);
		btnVenda.setText("Venda");
		
		Button btnAluguel = new Button(composite_15, SWT.CHECK);
		btnAluguel.setSelection(true);
		btnAluguel.setText("Locação");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Composite composite_5 = new Composite(composite, SWT.NONE);
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 5, 1));
		composite_5.setLayout(new GridLayout(6, false));
		
		Label lblAngariad = new Label(composite_5, SWT.NONE);
		lblAngariad.setText("Angariador");
		
		text_4 = new Text(composite_5, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(composite_5, SWT.NONE);
		btnNewButton.setText("...");
		
		Label lblProprietario = new Label(composite_5, SWT.NONE);
		lblProprietario.setText("Proprietário");
		
		text_5 = new Text(composite_5, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar_7 = new Button(composite_5, SWT.NONE);
		btnSelecionar_7.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnSelecionar_7.setText("...");
		
		Label lblTipoImvel = new Label(composite_5, SWT.NONE);
		lblTipoImvel.setText("Tipo Imóvel");
		
		ComboViewer comboViewer = new ComboViewer(composite_5, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		
		Label lblCidade = new Label(composite_5, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");
		
		ComboViewer comboViewer_2 = new ComboViewer(composite_5, SWT.READ_ONLY);
		Combo combo_2 = comboViewer_2.getCombo();
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblBairro = new Label(composite_5, SWT.NONE);
		lblBairro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBairro.setText("Bairro");
		
		ComboViewer comboViewer_1 = new ComboViewer(composite_5, SWT.READ_ONLY);
		Combo combo_1 = comboViewer_1.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite_5, SWT.NONE);
		
		Label lblObservaes_3 = new Label(composite, SWT.NONE);
		lblObservaes_3.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes_3.setText("Observações");
		
		text_6 = new Text(composite, SWT.BORDER);
		GridData gd_text_6 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 4, 1);
		gd_text_6.heightHint = 60;
		gd_text_6.widthHint = 449;
		text_6.setLayoutData(gd_text_6);
		new Label(composite, SWT.NONE);
		
		Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setLayout(new GridLayout(2, false));
		
		Button btnReservado = new Button(composite_3, SWT.CHECK);
		btnReservado.setSelection(true);
		btnReservado.setText("Não Reservado");
		
		Button btnReservado_1 = new Button(composite_3, SWT.CHECK);
		btnReservado_1.setText("Reservado");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TabItem tbtmCmodos = new TabItem(tabFolder, SWT.NONE);
		tbtmCmodos.setText("Cômodos");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmCmodos.setControl(composite_1);
		composite_1.setLayout(new GridLayout(2, false));
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		criarTabelaComodo(composite_2);
		
		Button btnAdicionar = new Button(composite_1, SWT.NONE);
		btnAdicionar.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1));
		btnAdicionar.setText("Adicionar");
		btnAdicionar.setImage(ImageRepository.ADD_16.getImage());
		
		Button btnRemover = new Button(composite_1, SWT.NONE);
		btnRemover.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1));
		btnRemover.setText("Remover");
		btnRemover.setImage(ImageRepository.REMOVE_16.getImage());
		
		Button btnNewButton_1 = new Button(parent, SWT.NONE);
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 82;
		gd_btnNewButton_1.heightHint = 46;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("Buscar");
		btnNewButton_1.setImage(ImageRepository.SEARCH_32.getImage());
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				buscar();
			}
		});
		
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	
	private void buscar(){
		List<Imovel> imoveis = new ImovelService().findImoveis(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		System.out.println(imoveis.size());
	}
	
	private void criarTabelaComodo(Composite composite){
		tvbComodo = new TableViewerBuilder(composite);
		
		tvbComodo.createColumn("Cômodo").bindToProperty("tipoComodo.nome").build();
		tvbComodo.createColumn("Descrição").bindToProperty("descricao").makeEditable().build();
	}
}
