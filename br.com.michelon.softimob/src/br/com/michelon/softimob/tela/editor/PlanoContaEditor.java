package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.nebula.jface.viewer.radiogroup.RadioGroupViewer;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PlanoContaService;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.widget.NumberTextField;

public class PlanoContaEditor extends GenericEditor<PlanoConta> {
	public static final String ID = "br.com.michelon.softimob.tela.editor.PlanoContaEditor";
	
	private PlanoContaService service = new PlanoContaService();
	
	private Text text_1;
	private Text text_2;
	private RadioGroupViewer radioGroupViewer;
	
	public PlanoContaEditor() {
		super(PlanoConta.class);
	}

	@Override
	public GenericService<PlanoConta> getService() {
		return service;
	}
	
	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(2, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblNmero = new Label(parent, SWT.NONE);
		lblNmero.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmero.setText("Número");
		
		NumberTextField numberTextField = new NumberTextField(parent);
		text_2 = numberTextField.getControl();
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDescrio = new Label(parent, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrio.setText("Descrição");
		
		text_1 = new Text(parent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		
		radioGroupViewer = new RadioGroupViewer(parent, SWT.NONE);
		RadioGroup radioGroup = radioGroupViewer.getRadioGroup();
		radioGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		radioGroupViewer.setContentProvider(ArrayContentProvider.getInstance());
		radioGroupViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				if(element.equals(PlanoConta.ATIVA))
					return "Ativa";
				if(element.equals(PlanoConta.PASSIVA))
					return "Passiva";
				return "";
			}
		});
		radioGroupViewer.setInput(new Integer[]{PlanoConta.ATIVA, PlanoConta.PASSIVA});
		
		
		
	}
	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_2);
		IObservableValue valueCodigoObserveDetailValue = PojoProperties.value(PlanoConta.class, "codigo", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueCodigoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueDescricaoObserveDetailValue = PojoProperties.value(PlanoConta.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueDescricaoObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionRadioGroupViewer = ViewerProperties.singleSelection().observe(radioGroupViewer);
		IObservableValue valueTipoObserveDetailValue = PojoProperties.value(PlanoConta.class, "tipo", Integer.class).observeDetail(value);
		bindingContext.bindValue(observeSingleSelectionRadioGroupViewer, valueTipoObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
