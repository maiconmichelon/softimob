package br.com.michelon.softimob.tela.editor;

import java.util.Date;
import java.util.List;

import javax.persistence.RollbackException;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.persistence.exceptions.DatabaseException;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.ImageRepository;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.michelon.softimob.aplicacao.annotation.Log;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.exception.ValidationException;
import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;
import br.com.michelon.softimob.aplicacao.helper.SWTHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.ValidatorHelper;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Usuario;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;

public abstract class GenericEditor<T> extends EditorPart {

	public static final String TITLE_SALVAR = "Registro salvo";
	public static final String MESSAGE_SALVAR = "Registro salvo com sucesso.";
	
	public final Class<T> mainClass;
	protected WritableValue value;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	
	private Logger log = Logger.getLogger(getClass());
	private Composite cpPrincipal;
	private DataBindingContext initDataBindings;
	private Label lblUsuarioCadastro;
	private Label lblDataAlteracao;
	private Label lblUsuarioAlteracao;
	private boolean hasNovo;
	
	public GenericEditor(Class<T> clazz) {
		this(clazz, true);
	}
	
	public GenericEditor(Class<T> clazz, boolean hasNovo) {
		mainClass = clazz;
		this.hasNovo = hasNovo;
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
		
		Composite cpOpcoes = new Composite(composite, SWT.BORDER);
		GridLayout gl_cpOpcoes = new GridLayout(5, false);
		cpOpcoes.setLayout(gl_cpOpcoes);
		cpOpcoes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSalvar = new Button(cpOpcoes, SWT.NONE);
		btnSalvar.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/save/save32.png"));
		GridData gd_btnSalvar = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 3);
		gd_btnSalvar.widthHint = 80;
		gd_btnSalvar.heightHint = 50;
		btnSalvar.setLayoutData(gd_btnSalvar);
		btnSalvar.setText("Salvar");
		btnSalvar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveCurrentObject(getService());
				setFocus();
			}
		});
		
		if(hasNovo){
			Button btnNovo = new Button(cpOpcoes, SWT.NONE);
			btnNovo.setImage(ImageRepository.NOVO_32.getImage());
			GridData gd_btnNovo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 3);
			gd_btnNovo.widthHint = 80;
			gd_btnNovo.heightHint = 50;
			btnNovo.setLayoutData(gd_btnNovo);
			formToolkit.adapt(btnNovo, true, true);
			btnNovo.setText("Novo");
			
			btnNovo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					resetValue();
					setFocus();
				}
			});
		}
		
		if(valueHasLog()){
			Label label = new Label(cpOpcoes, SWT.NONE);
			label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 3));
			formToolkit.adapt(label, true, true);

			Label lblNewLabel = new Label(cpOpcoes, SWT.NONE);
			lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 1, 1));
			lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
			lblNewLabel.setText("Usuário Cadastro");
			
			lblUsuarioCadastro = new Label(cpOpcoes, SWT.NONE);
			GridData gd_lblFulano = new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1);
			gd_lblFulano.widthHint = 93;
			lblUsuarioCadastro.setLayoutData(gd_lblFulano);
			lblUsuarioCadastro.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
			
			Label lblNewLabel_1 = new Label(cpOpcoes, SWT.NONE);
			lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
			lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
			lblNewLabel_1.setText("Usuário Alteração");
			
			lblUsuarioAlteracao = new Label(cpOpcoes, SWT.NONE);
			lblUsuarioAlteracao.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
			lblUsuarioAlteracao.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
			
			Label lblDataDeCadastro = new Label(cpOpcoes, SWT.NONE);
			lblDataDeCadastro.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 1, 1));
			lblDataDeCadastro.setText("Data Alteração");
			lblDataDeCadastro.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
			
			lblDataAlteracao = new Label(cpOpcoes, SWT.NONE);
			lblDataAlteracao.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 1, 1));
			lblDataAlteracao.setText("");
			lblDataAlteracao.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		}
		
		//PARA ABRIR NO WINDOW BUILDER
		if(getEditorInput() instanceof GenericEditorInput){
			initDataBindings = initBindings();
			bindLog(initDataBindings);
		}
//		else{
//			initDataBindings = this.initDataBindings();
//		}
		
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
	
	private void bindLog(DataBindingContext bindingContext) {
		if(valueHasLog()){
			IObservableValue observeTextLblDataCadastroObserveWidget = WidgetProperties.text().observe(lblDataAlteracao);
			IObservableValue valueDataCadastroObserveDetailValue = PojoProperties.value(mainClass, "log.dataAlteracao", Date.class).observeDetail(value);
			bindingContext.bindValue(observeTextLblDataCadastroObserveWidget, valueDataCadastroObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
			//
			IObservableValue observeTextLblUsuarioCadastroObserveWidget = WidgetProperties.text().observe(lblUsuarioCadastro);
			IObservableValue valueLogusuarioCadastrologinObserveDetailValue = PojoProperties.value(mainClass, "log.usuarioCadastro", Usuario.class).observeDetail(value);
			bindingContext.bindValue(observeTextLblUsuarioCadastroObserveWidget, valueLogusuarioCadastrologinObserveDetailValue, null, null);
			//
			IObservableValue observeTextLblUltimaAlteracaoObserveWidget = WidgetProperties.text().observe(lblUsuarioAlteracao);
			IObservableValue valueLogusuarioAlteracaologinObserveDetailValue = PojoProperties.value(mainClass, "log.usuarioAlteracao", Usuario.class).observeDetail(value);
			bindingContext.bindValue(observeTextLblUltimaAlteracaoObserveWidget, valueLogusuarioAlteracaologinObserveDetailValue, null, null);
		}
	}
	
	public abstract GenericService<T> getService();

	/**
	 * Salva o value principal
	 * @param Service utilizado para salvar o objeto
	 */
	public void saveCurrentObject(GenericService<T> service) {
		salvar(getService(), value, true);
		updateTargets();
	}
	
	private boolean valueHasLog(){
		return !ReflectionHelper.getFieldByAnnotation(mainClass, Log.class).isEmpty();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean salvar(GenericService service, IObservableValue value, boolean mostrarMensagem){
		try {
			if(!validarComMensagem(value.getValue()))
				return false;
				
			service.salvar(value.getValue());
			if(mostrarMensagem)
				MessageDialog.openInformation(ShellHelper.getActiveShell(), TITLE_SALVAR, MESSAGE_SALVAR);
			
			setFocus();
			
			return true;
		} catch (PSQLException e){
			MessageDialog.openError(ShellHelper.getActiveShell(), "Erro", "Erro ao salvar registro\n" + e.getMessage());
		} catch (DataIntegrityViolationException e){
			log.error("Erro ao salvar registro", e);
			MessageDialog.openError(ShellHelper.getActiveShell(), "Erro", "Erro ao salvar registro\n" + e.getMessage());
		} catch (Exception e) {
			if(e.getCause() != null && e.getCause().getClass().equals(RollbackException.class)){
				RollbackException re = (RollbackException) e.getCause();
				if(re.getCause() != null && re.getCause().getClass().equals(DatabaseException.class)){
					DatabaseException de = (DatabaseException) re.getCause();
					if(de.getErrorCode() == 4002 && de.getCause() instanceof PSQLException && ((PSQLException)de.getCause()).getSQLState().equals("23505")){
						DialogHelper.openWarning("Já existe um registro com estas caracteristicas.");
						return false;
					}
				}
			}
			log.error("Erro ao salvar registro", e);
			MessageDialog.openError(ShellHelper.getActiveShell(), "Erro", "Erro ao salvar registro\n" + e.getMessage());
		}
		
		return false;
	}
	
	protected <Y> void addItens(GenericService<Y> service, IObservableValue value, TableViewer tv){
		addItens(service, value, tv, null);
	}
	
	protected <Y> boolean addItens(GenericService<Y> service, IObservableValue value, ColumnViewer tv, List<Y> list){
		return addItens(service, value, tv, list, true);
	}

	protected <Y> boolean addItens(GenericService<Y> service, IObservableValue value, ColumnViewer tv, List<Y> list, boolean addItenOnTable){
		return addItens(service, value, tv, true, getCurrentObject(), addItenOnTable);
	}
	
	@SuppressWarnings("unchecked")
	protected <Y> boolean addItens(GenericService<Y> service, IObservableValue value, ColumnViewer tv, boolean resetValue, Object father, boolean addItenOnTable){
		if(!salvar(service, value, false))
			return false;
			
		SWTHelper.setSuccesfulMessageInBottomScreen("Item salvo com sucesso !");
		
		List<Y> elementos = (List<Y>) tv.getInput();
		if(!elementos.contains(value.getValue()) && addItenOnTable)
			elementos.add((Y) value.getValue());

		//Aqui ele tenta resetar o value
		if(resetValue){
			try {
				Class<Y> clazz = (Class<Y>) value.getValue().getClass();
				value.setValue(clazz.getConstructor(father.getClass()).newInstance(father));
				initDataBindings.updateTargets();
			} catch (Exception e) {
				log.error("Erro ao criar nova instancia do item.", e);
			}
		}
		
		tv.refresh();
		
		return true;
	}

	protected Button createButtonAddItem(Composite cp, SelectionListener listener){
		Button btnAddItem = new Button(cp, SWT.NONE);
		btnAddItem.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 1, 1));
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
		Control firsControlTextCombo = getFirsControlTextCombo(children);
		if(firsControlTextCombo != null)
			firsControlTextCombo.forceFocus();
			
	}

	public Control getFirsControlTextCombo(Control[] children){
		for(Control ctr : children){
			if(ctr.isEnabled() && (ctr instanceof Text || ctr instanceof Combo))
				return ctr;
			if(ctr instanceof Composite)
				return getFirsControlTextCombo(((Composite) ctr).getChildren());
		}
		
		return null;
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
