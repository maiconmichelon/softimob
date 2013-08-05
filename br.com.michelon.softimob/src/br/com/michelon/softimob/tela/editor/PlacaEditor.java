package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PlacaService;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Placa;

public class PlacaEditor extends GenericEditor<Placa>{

	public static final String ID = "br.com.michelon.softimob.tela.editor.PlacaEditor"; //$NON-NLS-1$
	
	private PlacaService service= new PlacaService();
	
	private Text text;
	private Text text_1;
	private Text text_2;
	
	public PlacaEditor() {
		super(Placa.class);
	}
	
	@Override
	public GenericService<Placa> getService() {
		return service;
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl_composite = new GridLayout(4, false);
		gl_composite.verticalSpacing = 10;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		org.eclipse.swt.widgets.Label lblNmero = new org.eclipse.swt.widgets.Label(composite, SWT.NONE);
		lblNmero.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmero.setText("Número");
		
		text = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 134;
		text.setLayoutData(gd_text);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		org.eclipse.swt.widgets.Label lblImvel = new org.eclipse.swt.widgets.Label(composite, SWT.NONE);
		lblImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblImvel.setText("Imóvel");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setEditable(false);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar = new Button(composite, SWT.NONE);
		btnSelecionar.setImage(ImageRepository.SEARCH_16.getImage());
		
		Button button = new Button(composite, SWT.NONE);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.IMOVEL, btnSelecionar, button, value, "imovel");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Corretor");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setEditable(false);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setImage(ImageRepository.SEARCH_16.getImage());
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setImage(ImageRepository.REMOVE_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnNewButton, button_1, value, "funcionario");
	}

	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNumeroObserveDetailValue = PojoProperties.value(Placa.class, "numero", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNumeroObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_1);
		IObservableValue valueImovelObserveDetailValue = PojoProperties.value(Placa.class, "imovel", Imovel.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueImovelObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_2);
		IObservableValue valueFuncionarionomeObserveDetailValue = PojoProperties.value(Placa.class, "funcionario.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueFuncionarionomeObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
