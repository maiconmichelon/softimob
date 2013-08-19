package br.com.michelon.softimob.tela.editor;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.ImageRepository;
import org.eclipse.wb.swt.ResourceManager;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.exception.ValidationException;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.ValidatorHelper;
import br.com.michelon.softimob.aplicacao.service.GenericService;

public abstract class GenericEditor<T> extends EditorPart {

	public static final String TITLE_SALVAR = "Registro salvo";
	public static final String MESSAGE_SALVAR = "Registro salvo com sucesso.";
	
	public final Class<T> mainClass;
	protected WritableValue value;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	
	private Logger log = Logger.getLogger(getClass());
	private Composite cpPrincipal;
	private DataBindingContext initDataBindings;
	
	public GenericEditor(Class<T> clazz) {
		mainClass = clazz;
		value = WritableValue.withValueType(mainClass);
	}
	
	@Override
	public void createPartControl(Composite parent) {
		
		Composite composite = formToolkit.createComposite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(scrolledComposite);
		formToolkit.paintBordersFor(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		cpPrincipal = new Composite(scrolledComposite, SWT.BORDER);
		cpPrincipal.setLayout(new GridLayout(2, false));
		
		afterCreatePartControl(cpPrincipal);
		scrolledComposite.setContent(cpPrincipal);
		scrolledComposite.setMinSize(cpPrincipal.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		//PARA ABRIR NO WINDOW BUILDER
		if(getEditorInput() instanceof GenericEditorInput)
			initDataBindings = initBindings();
//		else{
//			initDataBindings = this.initDataBindings();
//		}
		
		Composite cpOpcoes = new Composite(composite, SWT.BORDER);
		cpOpcoes.setLayout(new GridLayout(2, false));
		cpOpcoes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSalvar = new Button(cpOpcoes, SWT.NONE);
		btnSalvar.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/save/save32.png"));
		GridData gd_btnSalvar = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSalvar.widthHint = 80;
		btnSalvar.setLayoutData(gd_btnSalvar);
		btnSalvar.setText("Salvar");
		btnSalvar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveCurrentObject(getService());
			}
		});
		
		Button btnNovo = new Button(cpOpcoes, SWT.NONE);
		btnNovo.setImage(ImageRepository.NOVO_32.getImage());
		GridData gd_btnNovo = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_btnNovo.widthHint = 80;
		btnNovo.setLayoutData(gd_btnNovo);
		formToolkit.adapt(btnNovo, true, true);
		btnNovo.setText("Novo");
		btnNovo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resetValue();
			}
		});
	}

	protected void resetValue() {
		resetValue(getCurrentObject());
	}

	protected void resetValue(Object obj) {
		try {
			setValue(getNewValue());
		} catch (Exception e) {
			log.error("Erro ao criar novo registro.", e);
		}
	}
	
	public abstract void afterCreatePartControl(Composite parent);
	
	// NÃO PODE SER initDataBindings PORQUE O WINDOW BUILDER NÃO ABRI DAÍ !
	protected DataBindingContext initBindings(){
		DataBindingContext bindingContext = new DataBindingContext();
		return bindingContext;
	}
	
	public abstract GenericService<T> getService();

	/**
	 * Salva o value principal
	 * @param Service utilizado para salvar o objeto
	 */
	public void saveCurrentObject(GenericService<T> service) {
		salvar(getService(), value);
		updateTargets();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean salvar(GenericService service, IObservableValue value){
		try {
			if(!validarComMensagem(value.getValue()))
				return false;
				
			service.salvar(value.getValue());
			MessageDialog.openInformation(ShellHelper.getActiveShell(), TITLE_SALVAR, MESSAGE_SALVAR);
			
			setFocus();
			
			return true;
		} catch (DataIntegrityViolationException e){
			log.error("Erro ao salvar registro", e);
			MessageDialog.openError(ShellHelper.getActiveShell(), "Erro", "Erro ao salvar registro\n" + e.getMessage());
		} catch (Exception e) {
			log.error("Erro ao salvar registro", e);
			MessageDialog.openError(ShellHelper.getActiveShell(), "Erro", "Erro ao salvar registro\n" + e.getMessage());
		}
		
		return false;
	}
	
	protected <Y> void addItens(GenericService<Y> service, IObservableValue value, TableViewer tv){
		addItens(service, value, tv, null);
	}
	
	protected <Y> boolean addItens(GenericService<Y> service, IObservableValue value, ColumnViewer tv, List<Y> list){
		return addItens(service, value, tv, true, getCurrentObject());
	}
	
	@SuppressWarnings("unchecked")
	protected <Y> boolean addItens(GenericService<Y> service, IObservableValue value, ColumnViewer tv, boolean resetValue, Object father){
		if(!salvar(service, value))
			return false;
			
		List<Y> elementos = (List<Y>) tv.getInput();
		if(!elementos.contains(value.getValue()))
			elementos.add((Y) value.getValue());

		//Aqui ele tenta resetar o value
		if(resetValue){
			try {
				Class<Y> clazz = (Class<Y>) value.getValue().getClass();
				value.setValue(clazz.getConstructor(father.getClass()).newInstance(father));
				initDataBindings.updateTargets();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		tv.refresh();
		
		return true;
	}

	protected Button createButtonAddItem(Composite cp, SelectionListener listener){
		Button btnAddItem = new Button(cp, SWT.NONE);
		btnAddItem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnAddItem.setImage(ImageRepository.SAVE_16.getImage());
		btnAddItem.addSelectionListener(listener);
		return btnAddItem;
	}
	
	protected boolean validarComMensagem(Object obj){
		return ValidatorHelper.validarComMensagem(obj);
	}
	
	protected void validar(Object obj) throws ValidationException{
		ValidatorHelper.validar(obj);
	}
	
	@Override
	public void setFocus() {
		Control[] children = cpPrincipal.getChildren();
		for(Control ctr : children){
			if(ctr.isEnabled() && (ctr instanceof Text || ctr instanceof Combo)){
				ctr.forceFocus();
				break;
			}
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@SuppressWarnings("unchecked")
	protected T getCurrentObject(){
		return (T) value.getValue();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		
		if(input instanceof GenericEditorInput){
			setValue(getValorInicial((GenericEditorInput<T>) input));
		}
	}
	
	protected void beforeSetIObservableValue(T obj){}
	
	protected void setValue(T obj){
		beforeSetIObservableValue(obj);
		value.setValue(obj);
		afterSetIObservableValue(obj);
	}
	
	protected void afterSetIObservableValue(T obj) {}

	protected T getValorInicial(GenericEditorInput<T> editorInput){
		if(editorInput.getModelo() != null)
			return editorInput.getModelo();
		return getNewValue();
	}
	
	protected T getNewValue() {
		try {
			return mainClass.newInstance();
		} catch (Exception e) {
			log.error("Erro ao criar nova instancia do value.", e);
			return null;
		}
	}
	
	protected void updateTargets(){
		initDataBindings.updateTargets();
	}
	
	protected void updateModels(){
		initDataBindings.updateModels();
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
