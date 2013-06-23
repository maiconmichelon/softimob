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
import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Estado;

public class BairroEditor extends GenericEditor<Bairro>{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.BairroEditor";
	
	private DataBindingContext m_bindingContext;

	private BairroService service = new BairroService();
	
	private ComboViewer cvCidade;
	private Text text;
	
	public BairroEditor() {
		super(Bairro.class);
	}
	
	@Override
	public GenericService<Bairro> getService() {
		return service;
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gridLayout = (GridLayout) parent.getLayout();
		gridLayout.verticalSpacing = 10;
		
		Label lblEstado = new Label(parent, SWT.NONE);
		lblEstado.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEstado.setText("Estado");
		
		ComboViewer cvUF = new ComboViewer(parent, SWT.READ_ONLY);
		cvUF.addPostSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				Estado estado = (Estado) SelectionHelper.getObject(event.getSelection());
				
				cvCidade.setInput(estado.getCidades());
			}
		});
		Combo cbUF = cvUF.getCombo();
		cbUF.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		cvUF.setContentProvider(ArrayContentProvider.getInstance());
		cvUF.setInput(new EstadoService().findAll());
		
		Label lblCidade = new Label(parent, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");
		
		cvCidade = new ComboViewer(parent, SWT.READ_ONLY);
		Combo cbCidade = cvCidade.getCombo();
		GridData gd_combo_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo_1.widthHint = 230;
		cbCidade.setLayoutData(gd_combo_1);
		cvCidade.setContentProvider(ArrayContentProvider.getInstance());
		
		Label lblBairro = new Label(parent, SWT.NONE);
		lblBairro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBairro.setText("Bairro");
		
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(cvCidade);
		IObservableValue valueCidadeObserveDetailValue = PojoProperties.value(Bairro.class, "cidade", Cidade.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer, valueCidadeObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(Bairro.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
