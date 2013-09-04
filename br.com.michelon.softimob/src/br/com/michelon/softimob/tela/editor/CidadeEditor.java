package br.com.michelon.softimob.tela.editor;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.service.CidadeService;
import br.com.michelon.softimob.aplicacao.service.EstadoService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.modelo.Estado;
import br.com.michelon.softimob.tela.widget.LoadOnFocus;
import br.com.michelon.softimob.tela.widget.LoadOnFocus.Load;

public class CidadeEditor extends GenericEditor<Cidade> {

	public static final String ID = "br.com.michelon.softimob.tela.editor.CidadeEditor";
	
	private CidadeService service = new CidadeService();
	private EstadoService estadoService = new EstadoService();
	
	private Text text;
	private ComboViewer cvEstado;
	
	public CidadeEditor() {
		super(Cidade.class);
	}

	@Override
	public GenericService<Cidade> getService() {
		return service;
	}
	
	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gridLayout = (GridLayout) parent.getLayout();
		gridLayout.verticalSpacing = 10;
		
		Label lblUf = new Label(parent, SWT.NONE);
		lblUf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUf.setText("UF");
		
		cvEstado = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo = cvEstado.getCombo();
		combo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		cvEstado.setContentProvider(ArrayContentProvider.getInstance());
		LoadOnFocus.setFocusGainedListener(cvEstado, new Load() {
			@Override
			public List<?> getInput() {
				return estadoService.findAll();
			}
		});
		
		Label lblCidade = new Label(parent, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");
		
		text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 222;
		text.setLayoutData(gd_text);
		
		
	}
	
	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeSingleSelectionComboViewer = ViewerProperties.singleSelection().observe(cvEstado);
		IObservableValue valueEstadoObserveDetailValue = PojoProperties.value(Cidade.class, "estado", Estado.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionComboViewer, valueEstadoObserveDetailValue, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(Cidade.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
