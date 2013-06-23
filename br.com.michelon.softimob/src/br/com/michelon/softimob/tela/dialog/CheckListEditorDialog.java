package br.com.michelon.softimob.tela.dialog;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
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

import br.com.michelon.softimob.modelo.CheckList;
import br.com.michelon.softimob.modelo.ItemCheckList;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class CheckListEditorDialog extends TitleAreaDialog{
	private Text txtCheckList;
	private Text txtNome;
	private ItemCheckList current;
	private CheckList checkList;
	
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
		
		Composite cpTabela = new Composite(composite, SWT.NONE);
		cpTabela.setLayout(new GridLayout(1, false));
		cpTabela.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		criarTabelaIndices(cpTabela);
		
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
		txtNome.setText(item.getDescricao());
	}
	
	private void limpar(){
		txtNome.setText(StringUtils.EMPTY);
	}
	
	private ItemCheckList getItemCheckList(){
		if(current == null)
			current = new ItemCheckList();
		
		current.setNome(txtNome.getText());
		
		return current;
	}
	
	private void criarTabelaIndices(Composite cp){
		TableViewerBuilder tvb = new TableViewerBuilder(cp);
		
		tvb.createColumn("Nome").bindToProperty("nome").build();
		
		tvb.setInput(checkList.getItens());
		
		tvb.getTableViewer().addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				setItens((ItemCheckList) ((IStructuredSelection)event.getSelection()).getFirstElement());
			}
		});
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
				ItemCheckList item = getItemCheckList();
				checkList.getItens().add(item);
				
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
	}

	@Override
	protected Point getInitialSize() {
		return new Point(350, 400);
	}
	
}
