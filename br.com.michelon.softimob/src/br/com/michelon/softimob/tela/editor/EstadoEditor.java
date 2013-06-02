package br.com.michelon.softimob.tela.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;

import br.com.michelon.softimob.aplicacao.service.EstadoService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Estado;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;

public class EstadoEditor extends GenericEditor<Estado> {
	
	private DataBindingContext m_bindingContext;
	public static final String ID = "br.com.michelon.softimob.tela.editor.EstadoEditor";
	private Text text_1;
	private Text text;
	
	private EstadoService service = new EstadoService();
	
	public EstadoEditor() {
		super(Estado.class);
	}

	@Override
	public GenericService<Estado> getService() {
		return service;
	}
	
	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gridLayout = (GridLayout) parent.getLayout();
		gridLayout.verticalSpacing = 10;
		
		Label lblUf = new Label(parent, SWT.NONE);
		lblUf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUf.setText("UF");
		
		FormattedText formattedText = new FormattedText(parent, SWT.BORDER);
		formattedText.setFormatter(new MaskFormatter("UU"));
		text = formattedText.getControl();
		GridData gd_text = new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1);
		gd_text.widthHint = 92;
		text.setLayoutData(gd_text);
		
		Label lblNomeExtenso = new Label(parent, SWT.NONE);
		lblNomeExtenso.setText("Nome Extenso");
		
		text_1 = new Text(parent, SWT.BORDER);
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 288;
		text_1.setLayoutData(gd_text_1);
		
		m_bindingContext = initDataBindings();
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueUfObserveDetailValue = PojoProperties.value(Estado.class, "uf", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueUfObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(Estado.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
