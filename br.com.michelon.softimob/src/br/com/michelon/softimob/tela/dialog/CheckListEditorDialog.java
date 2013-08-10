package br.com.michelon.softimob.tela.dialog;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.ValidatorHelper;
import br.com.michelon.softimob.aplicacao.service.CheckListService;
import br.com.michelon.softimob.modelo.CheckList;

public class CheckListEditorDialog extends TitleAreaDialog{
	private Text txtCheckList;
	private Text txtNome;
	private String current;
	private CheckList checkList;
	
	private Logger log = Logger.getLogger(getClass());
	private ListViewer lstItens;
	
	public CheckListEditorDialog(Shell parentShell, CheckList checkList) {
		super(parentShell);
		
		if(checkList == null)
			checkList = new CheckList();
		
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
		
		lstItens = new ListViewer(cpTabela, SWT.BORDER | SWT.V_SCROLL);
		List list = lstItens.getList();
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		lstItens.setContentProvider(ArrayContentProvider.getInstance());
		prepararList(lstItens);
//		tvItens = criarTabelaIndices(cpTabela).getTableViewer();
		
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
	
	private void setItens(String item){
		current = item;
		txtNome.setText(item);
	}
	
	private void limpar(){
		txtNome.setText(StringUtils.EMPTY);
		current = null;
	}
	
	private String getItens(){
		if(current == null)
			current = new String();
		
		current = txtNome.getText();
		
		return current;
	}
	
	private void prepararList(ListViewer listViewer) {
		if(checkList != null)
			listViewer.setInput(checkList.getItens());
		
		listViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				setItens((String) ((IStructuredSelection)event.getSelection()).getFirstElement());
			}
		});
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
					String item = getItens();
					
					if(ValidatorHelper.validarComMensagem(item))
						checkList.getItens().add(item);
					else{
						current = null;
						return;
					}
				}else{
					checkList.getItens().remove(current);
					checkList.getItens().add(getItens());
				}
				
				lstItens.refresh();
				
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
		
		Button btnFinalizar = new Button(parent, SWT.PUSH);
		btnFinalizar.setText("Finalizar");
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
			if(ValidatorHelper.validarComMensagem(checkList)){
				new CheckListService().salvar(checkList);
				DialogHelper.openInformation("Registro salvo com sucesso.");
				close();
			}
		} catch (Exception e1) {
			new ValidationErrorDialog(ShellHelper.getActiveShell(), e1.getMessage());
			log.error("Erro ao salvar check list", e1);
		}
	}
	
}
