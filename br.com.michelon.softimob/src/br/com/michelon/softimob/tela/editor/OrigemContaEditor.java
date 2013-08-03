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
import br.com.michelon.softimob.aplicacao.service.OrigemContaService;
import br.com.michelon.softimob.modelo.OrigemConta;

public class OrigemContaEditor extends GenericEditor<OrigemConta> {
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.OrigemContaEditor";
	
	private OrigemContaService service = new OrigemContaService();
	
	private Text text;
	private Text text_1;
	private Text text_2;
	
	public OrigemContaEditor() {
		super(OrigemConta.class);
	}
	
	@Override
	public OrigemContaService getService() {
		return service;
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(4, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblNome = new Label(parent, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Descrição");
		
		text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 174;
		text.setLayoutData(gd_text);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblConta = new Label(parent, SWT.NONE);
		lblConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblConta.setText("Conta");
		
		text_1 = new Text(parent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(parent, SWT.NONE);
		button.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button, value, "conta");
		
		Button btnt = new Button(parent, SWT.NONE);
		btnt.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnt.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblContrapartida = new Label(parent, SWT.NONE);
		lblContrapartida.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblContrapartida.setText("Contra-Partida");
		
		text_2 = new Text(parent, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(parent, SWT.NONE);
		button_1.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button_1, value, "contaContraPartida");
		
		Button btnt_1 = new Button(parent, SWT.NONE);
		btnt_1.setImage(ImageRepository.REMOVE_16.getImage());
	}
	
	@Override
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueDescricaoObserveDetailValue = PojoProperties.value(OrigemConta.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueDescricaoObserveDetailValue, null, null);
		//
		IObservableValue observeSizeText_1ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_1);
		IObservableValue valueContacodigoDescricaoObserveDetailValue = PojoProperties.value(OrigemConta.class, "conta.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeSizeText_1ObserveWidget, valueContacodigoDescricaoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.NONE).observe(text_2);
		IObservableValue valueContaContraPartidacodigoDescricaoObserveDetailValue = PojoProperties.value(OrigemConta.class, "contaContraPartida.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueContaContraPartidacodigoDescricaoObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
