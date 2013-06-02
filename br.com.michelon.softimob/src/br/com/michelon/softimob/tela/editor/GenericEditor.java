package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.springframework.dao.DataIntegrityViolationException;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.service.GenericService;

public abstract class GenericEditor<T> extends EditorPart {

	public static final String TITLE_SALVAR = "Registro salvo";
	public static final String MESSAGE_SALVAR = "Registro salvo com sucesso.";
	
	public final Class<T> mainClass;
	protected WritableValue value;
	
	public GenericEditor(Class<T> clazz) {
		mainClass = clazz;
		value = WritableValue.withValueType(mainClass);
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
				salvar(getService(), value);
			}

		});
		
		afterCreatePartControl(cpPrincipal);
	}

	public abstract void afterCreatePartControl(Composite parent);
	
	public abstract GenericService<T> getService();
	
	@SuppressWarnings("unchecked")
	public void salvar(GenericService<T> service, IObservableValue value) {
		try {
			service.salvar((T) value.getValue());
			MessageDialog.openInformation(ShellHelper.getActiveShell(), TITLE_SALVAR, MESSAGE_SALVAR);
			value.setValue(((Class<T>)value.getValueType()).newInstance());
		} catch (DataIntegrityViolationException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setFocus() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		
		value.setValue(getValorInicial((GenericEditorInput<T>) input));
	}

	protected T getValorInicial(GenericEditorInput<T> editorInput){
		if(editorInput.getModelo() != null)
			return editorInput.getModelo();
		
		try{
			return mainClass.newInstance();
		}catch(Exception e){
			return null;
		}
	}
	
	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

}
