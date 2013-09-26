package br.com.michelon.softimob.tela.dialog;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.ValidatorHelper;
import br.com.michelon.softimob.aplicacao.service.IndiceService;
import br.com.michelon.softimob.modelo.Indice;
import br.com.michelon.softimob.modelo.IndiceMes;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTextField;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class IndiceEditorDialog extends TitleAreaDialog{
	
	private Text txtNome;
	private Text txtData;
	private Text txtPorcentagem;
	private DateTextField dtIndice;
	private IndiceMes current;
	private Indice indice;
	private TableViewer tvIndices;
	private Logger log = Logger.getLogger(getClass());
	
	public IndiceEditorDialog(Shell parentShell, Indice indice) {
		super(parentShell);
		
		if(indice == null)
			indice = new Indice();
		
		this.indice = indice;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Adicionar novos índices");
		setTitle("Índice");

		Composite area = (Composite) super.createDialogArea(parent);
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label lblNome = new Label(composite, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome");
		
		txtNome = new Text(composite, SWT.BORDER);
		txtNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				indice.setNome(((Text)e.widget).getText());
			}
		});
		
		if(indice != null && indice.getNome() != null)
			txtNome.setText(indice.getNome());
		
		Composite cpTabela = new Composite(composite, SWT.NONE);
		cpTabela.setLayout(new GridLayout(1, false));
		cpTabela.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tvIndices = criarTabelaIndices(cpTabela, indice.getIndices()).getTableViewer();
		
		Group grpIndiceDoPerodo = new Group(composite, SWT.NONE);
		grpIndiceDoPerodo.setLayout(new GridLayout(2, false));
		grpIndiceDoPerodo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		grpIndiceDoPerodo.setText("Indice do Período");
		
		Label lblData = new Label(grpIndiceDoPerodo, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		dtIndice = new DateTextField(grpIndiceDoPerodo);
		txtData = dtIndice.getControl();
		txtData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPorcentagem = new Label(grpIndiceDoPerodo, SWT.NONE);
		lblPorcentagem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPorcentagem.setText("Porcentagem");
		
		txtPorcentagem = new Text(grpIndiceDoPerodo, SWT.BORDER);
		txtPorcentagem.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		return area;
	}
	
	private void setItens(IndiceMes indice){
		current = indice;
		dtIndice.setValue(indice.getData());
		txtPorcentagem.setText(indice.getPorcentagem() != null ? indice.getPorcentagem().toString() : StringUtils.EMPTY);
	}
	
	private void limpar(){
		dtIndice.setValue(null);
		current = null;
		txtPorcentagem.setText(StringUtils.EMPTY);
	}
	
	private IndiceMes getIndiceMes(){
		if(current == null)
			current = new IndiceMes();
		
		current.setData((Date) dtIndice.getValue());
		try{
			current.setPorcentagem(new Double(txtPorcentagem.getText()));
		}catch(NumberFormatException ne){}
		
		return current;
	}
	
	private TableViewerBuilder criarTabelaIndices(Composite cp, List<IndiceMes> indices){
		TableViewerBuilder tvb = new TableViewerBuilder(cp);
		
		tvb.createColumn("Data").bindToProperty("data").format(new DateStringValueFormatter()).build();
		tvb.createColumn("Porcentagem").bindToProperty("porcentagem").build();
		
		tvb.getTableViewer().addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				setItens((IndiceMes) ((IStructuredSelection)event.getSelection()).getFirstElement());
			}
		});
		
		tvb.setInput(indices);
		
		return tvb;
	}
	
	private boolean isNovo() {
		return current == null;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setLayout(new GridLayout(3, false));
		
		Button btnRegistrar = new Button(parent, SWT.PUSH);
		btnRegistrar.setText("Registrar");
		btnRegistrar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true));
		btnRegistrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {

				if(isNovo()){ 
					IndiceMes indiceMes = getIndiceMes();
					
					if(ValidatorHelper.validarComMensagem(indiceMes))
						indice.getIndices().add(indiceMes);
					else {
						current = null;
						return;
					}
				} else {
					getIndiceMes();
				}
				
				tvIndices.refresh();
				
				limpar();
				txtNome.forceFocus();
			}
		});
		btnRegistrar.setImage(ImageRepository.SAVE_16.getImage());
		
		Button btnNovo = new Button(parent, SWT.PUSH);
		btnNovo.setText("Novo");
		btnNovo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				limpar();
			}
		});
		btnNovo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true));
		btnNovo.setImage(ImageRepository.NOVO_16.getImage());
		
		Button btnFinalizar = new Button(parent, SWT.PUSH);
		btnFinalizar.setText("Finalizar");
		btnFinalizar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true));
		btnFinalizar.setImage(ImageRepository.FINALIZAR_16.getImage());
		btnFinalizar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(ValidatorHelper.validarComMensagem(indice)){
						new IndiceService().salvar(indice);
						DialogHelper.openInformation("Índice registrado com sucesso.");
						close();
					}
				} catch (Exception e1) {
					log.error("Erro ao salvar indice", e1);
				}
			}
		});
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Indices");
		
		super.configureShell(newShell);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(350, 400);
	}
	
}
