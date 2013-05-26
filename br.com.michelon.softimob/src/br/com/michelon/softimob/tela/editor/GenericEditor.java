package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.ResourceManager;

public abstract class GenericEditor extends EditorPart {

	private DataBindingContext context;

	public GenericEditor() {
	}

	@Override
	public void createPartControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Composite cpPrincipal = new Composite(composite, SWT.NONE);
		cpPrincipal.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		cpPrincipal.setLayout(new GridLayout(2, false));
		
		Composite cpOpcoes = new Composite(composite, SWT.NONE);
		cpOpcoes.setLayout(new GridLayout(1, false));
		cpOpcoes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSalvar = new Button(cpOpcoes, SWT.NONE);
		btnSalvar.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/save/save32.png"));
		GridData gd_btnSalvar = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSalvar.heightHint = 50;
		gd_btnSalvar.widthHint = 91;
		btnSalvar.setLayoutData(gd_btnSalvar);
		btnSalvar.setText("Salvar");
		btnSalvar.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				salvar();
			}

		});
		
		afterCreatePartControl(cpPrincipal);
		
//		context = initDataBindings();
	}

	public abstract void afterCreatePartControl(Composite parent);
	
	protected abstract void salvar();

	@Override
	public void setFocus() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
//	
//	protected abstract DataBindingContext initDataBindings();
//	
//	protected void atualizaBinding(){
//		if(context != null)
//			context.updateTargets();
//	}
//	
}
