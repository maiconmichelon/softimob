package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.service.BairroService;
import br.com.michelon.softimob.aplicacao.service.EstadoService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.RuaService;
import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Estado;
import br.com.michelon.softimob.modelo.Rua;

public class RuaEditor extends GenericEditor<Rua>{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.RuaEditor";
	
	private RuaService service = new RuaService();
	
	private Text text;
	private ComboViewer cvBairro;

	private ComboViewer cvCidade;

	private ComboViewer cvUF;
	
	public RuaEditor() {
		super(Rua.class);
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gridLayout = (GridLayout) parent.getLayout();
		gridLayout.verticalSpacing = 10;
		
		Label lblUf = new Label(parent, SWT.NONE);
		lblUf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUf.setText("UF");
		
		cvUF = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo = cvUF.getCombo();
		combo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		cvUF.setContentProvider(ArrayContentProvider.getInstance());
		cvUF.setInput(new EstadoService().findAll());
		cvUF.addPostSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Estado estado = (Estado) SelectionHelper.getObject(event.getSelection());
				
				if(cvCidade != null && estado != null)
					cvCidade.setInput(estado.getCidades());
				if(SelectionHelper.getObject(cvBairro.getSelection()) != null) {
					getCurrentObject().setBairro(null);
					cvBairro.setInput(null);
				}
			}
		});
		
		Label lblCidade = new Label(parent, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");
		
		cvCidade = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo_1 = cvCidade.getCombo();
		GridData gd_combo_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo_1.widthHint = 228;
		combo_1.setLayoutData(gd_combo_1);
		cvCidade.setContentProvider(ArrayContentProvider.getInstance());
		cvCidade.addPostSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Cidade cidade = (Cidade) SelectionHelper.getObject(event.getSelection());
				
				if(cvBairro != null && cidade != null)
					cvBairro.setInput(cidade.getBairros());
			}
		});
		
		Label lblBairro = new Label(parent, SWT.NONE);
		lblBairro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBairro.setText("Bairro");
		
		cvBairro = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo_2 = cvBairro.getCombo();
		GridData gd_combo_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo_2.widthHint = 228;
		combo_2.setLayoutData(gd_combo_2);
		cvBairro.setContentProvider(ArrayContentProvider.getInstance());
		cvBairro.setInput(new BairroService().findAll());

		Label lblRua = new Label(parent, SWT.NONE);
		lblRua.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRua.setText("Rua");
		
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		try {
			cvUF.setSelection(new StructuredSelection(getCurrentObject().getBairro().getCidade().getEstado()));

			cvCidade.setInput(getCurrentObject().getBairro().getCidade().getEstado().getCidades());
			cvCidade.setSelection(new StructuredSelection(getCurrentObject().getBairro().getCidade()));
			
			cvBairro.setInput(getCurrentObject().getBairro().getCidade().getBairros());
			cvBairro.setSelection(new StructuredSelection(getCurrentObject().getBairro()));
		} catch(Exception e) {
		}
		
	}

	@Override
	public GenericService<Rua> getService() {
		return service;
	}
	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(Rua.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionCvCidade = ViewerProperties.singleSelection().observe(cvBairro);
		IObservableValue valueBairrocidadeestadoObserveDetailValue = PojoProperties.value(Rua.class, "bairro", Bairro.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionCvCidade, valueBairrocidadeestadoObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
