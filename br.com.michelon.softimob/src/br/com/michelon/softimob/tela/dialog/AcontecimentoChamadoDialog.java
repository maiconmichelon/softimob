package br.com.michelon.softimob.tela.dialog;

import java.util.Date;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.helper.ValidatorHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.modelo.AcontecimentoChamado;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.DateTimeTextField;

public class AcontecimentoChamadoDialog extends TitleAreaDialog {

	private WritableValue valueAcontecimentoChamado = WritableValue.withValueType(AcontecimentoChamado.class);
	private Text txtDataAcontecimentoChamado;
	private Text txtFuncionarioAcontecimentoReforma;
	private Text txtDescricaoAcontecimentoReforma;
	
	public AcontecimentoChamadoDialog() {
		super(ShellHelper.getActiveShell());
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		
		setMessage("Informe os dados do Acontecimento.");
		setTitle("Acontecimento de Chamado de Reforma");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(4, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label lblData_4 = new Label(container, SWT.NONE);
		lblData_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData_4.setText("Data");
		
		DateTimeTextField dateTimeTextField = new DateTimeTextField(container);
		txtDataAcontecimentoChamado = dateTimeTextField.getControl();
		txtDataAcontecimentoChamado.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblFuncionrio_3 = new Label(container, SWT.NONE);
		lblFuncionrio_3.setText("Funcionário");
		
		txtFuncionarioAcontecimentoReforma = new Text(container, SWT.BORDER);
		txtFuncionarioAcontecimentoReforma.setEditable(false);
		GridData gd_text_27 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text_27.widthHint = 133;
		txtFuncionarioAcontecimentoReforma.setLayoutData(gd_text_27);
		
		Button btnSelecionar_5 = new Button(container, SWT.NONE);
		btnSelecionar_5.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, btnSelecionar_5, valueAcontecimentoChamado, "funcionario");
		
		Button btnt_5 = new Button(container, SWT.NONE);
		btnt_5.setImage(ImageRepository.REMOVE_16.getImage());
		btnt_5.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		ListElementDialogHelper.addSelectionToRemoveButton(btnt_5, valueAcontecimentoChamado, "funcionario", Funcionario.class);
		
		Label lblDescrio_1 = new Label(container, SWT.NONE);
		lblDescrio_1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrio_1.setText("Descrição");
		
		txtDescricaoAcontecimentoReforma = new Text(container, SWT.BORDER | SWT.MULTI);
		GridData gd_txtDescricaoAcontecimentoReforma = new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1);
		gd_txtDescricaoAcontecimentoReforma.heightHint = 50;
		txtDescricaoAcontecimentoReforma.setLayoutData(gd_txtDescricaoAcontecimentoReforma);
		
		valueAcontecimentoChamado.setValue(new AcontecimentoChamado());
		initDataBinding();
		
		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected void okPressed() {
		AcontecimentoChamado acontecimento = (AcontecimentoChamado) valueAcontecimentoChamado.getValue();
		if(ValidatorHelper.validarComMensagem(acontecimento))
			super.okPressed();
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 265);
	}

	private DataBindingContext initDataBinding(){
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextText_10ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDataAcontecimentoChamado);
		IObservableValue valueAcontecimentoChamadoDataObserveDetailValue = PojoProperties.value(AcontecimentoChamado.class, "data", Date.class).observeDetail(valueAcontecimentoChamado);
		bindingContext.bindValue(observeTextText_10ObserveWidget, valueAcontecimentoChamadoDataObserveDetailValue, UVSHelper.uvsStringToDate(), UVSHelper.uvsDateToString());
		//
		IObservableValue observeTextText_27ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtFuncionarioAcontecimentoReforma);
		IObservableValue valueAcontecimentoChamadoFuncionarionomeObserveDetailValue = PojoProperties.value(AcontecimentoChamado.class, "funcionario.nome", String.class).observeDetail(valueAcontecimentoChamado);
		bindingContext.bindValue(observeTextText_27ObserveWidget, valueAcontecimentoChamadoFuncionarionomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_28ObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDescricaoAcontecimentoReforma);
		IObservableValue valueAcontecimentoChamadoDescricaoObserveDetailValue = PojoProperties.value(AcontecimentoChamado.class, "descricao", String.class).observeDetail(valueAcontecimentoChamado);
		bindingContext.bindValue(observeTextText_28ObserveWidget, valueAcontecimentoChamadoDescricaoObserveDetailValue, null, null);
		//
		return bindingContext;
	}

	public AcontecimentoChamado getAcontecimentoChamado() {
		return (AcontecimentoChamado) valueAcontecimentoChamado.getValue();
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Acontecimento de Chamado de Reforma");
		super.configureShell(newShell);
	}
	
}
