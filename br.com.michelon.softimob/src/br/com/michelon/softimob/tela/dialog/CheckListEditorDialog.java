package br.com.michelon.softimob.tela.dialog;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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

import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.ValidatorHelper;
import br.com.michelon.softimob.aplicacao.service.CheckListService;
import br.com.michelon.softimob.modelo.CheckList;
import br.com.michelon.softimob.modelo.ItemCheckList;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class CheckListEditorDialog extends TitleAreaDialog{
	private Text txtCheckList;
	private Text txtNome;
	private ItemCheckList current;
	private CheckList checkList;
	private TableViewer tvItens;
	
	private Logger log = Logger.getLogger(getClass());
	
	public CheckListEditorDialog(Shell parentShell, CheckList checkList) {
		super(parentShell);
		this.checkList = checkList;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Adicionar itens a Check List");
		setTitle("Check List");

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblCheckList = new Label(composite, SWT.NONE);
		lblCheckList.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCheckList.setText("Check List");
		
		txtCheckList = new Text(composite, SWT.BORDER);
		txtCheckList.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtCheckList.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				checkList.setNome(((Text)e.widget).getText());
			}
		});
		if(checkList != null && checkList.getNome() != null)
			txtCheckList.setText(checkList.getNome());
		
		Composite cpTabela = new Composite(composite, SWT.NONE);
		cpTabela.setLayout(new GridLayout(1, false));
		cpTabela.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tvItens = criarTabelaIndices(cpTabela).getTableViewer();
		
		Group grpItem = new Group(composite, SWT.NONE);
		grpItem.setLayout(new GridLayout(2, false));
		grpItem.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		grpItem.setText("Item");
		
		Label lblNome = new Label(grpItem, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome");
		
		txtNome = new Text(grpItem, SWT.BORDER);
		txtNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		return composite;
	}
	
	private void setItens(ItemCheckList item){
		current = item;
		txtNome.setText(item.getNome());
	}
	
	private void limpar(){
		txtNome.setText(StringUtils.EMPTY);
		current = null;
	}
	
	private ItemCheckList getItemCheckList(){
		if(current == null)
			current = new ItemCheckList();
		
		current.setNome(txtNome.getText());
		
		return current;
	}
	
	private TableViewerBuilder criarTabelaIndices(Composite cp){
		TableViewerBuilder tvb = new TableViewerBuilder(cp);
		
		tvb.createColumn("Nome").bindToProperty("nome").build();
		
		tvb.setInput(checkList.getItens());
		
		tvb.getTableViewer().addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				setItens((ItemCheckList) ((IStructuredSelection)event.getSelection()).getFirstElement());
			}
		});
		
		return tvb;
	}
	
	private boolean isNovo(){
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
					ItemCheckList item = getItemCheckList();
					checkList.getItens().add(item);
				}else{
					getItemCheckList();
				}
				
				tvItens.refresh();
				
				limpar();
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
		
		Button btnFinalizar = createButton(parent, IDialogConstants.OK_ID, "Finalizar", true);
		btnFinalizar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true));
		btnFinalizar.setImage(ImageRepository.FINALIZAR_16.getImage());
		btnFinalizar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				salvar();
			}
		});
	}

	@Override
	protected Point getInitialSize() {
		return new Point(350, 400);
	}

	private void salvar() {
		try {
			ValidatorHelper.validar(checkList);
			
			new CheckListService().salvar(checkList);
		} catch (Exception e1) {
			new ValidationErrorDialog(ShellHelper.getActiveShell(), e1.getMessage());
			log.error("Erro ao salvar check list", e1);
		}
	}
	
}
