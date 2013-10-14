package br.com.michelon.softimob.tela.dialog;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.service.TipoComodoService;
import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.modelo.TipoComodo;
import br.com.michelon.softimob.tela.widget.NumberTextField;

public class ComodoDialog extends TitleAreaDialog{
	public ComodoDialog(Shell shell) {
		super(shell);
	}
	
	private Text text_2;
	
	private Comodo comodo;
	private ComboViewer comboViewer;
	private Text txtQuantidade;
	
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Informe as descrições do cômodo.");
		setTitle("Seleção de cômodo");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblComodo = new Label(composite, SWT.NONE);
		lblComodo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblComodo.setText("Comodo");
		
		comboViewer = new ComboViewer(composite, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setInput(new TipoComodoService().findAtivados());
		
		Label lblQuantidade = new Label(composite, SWT.NONE);
		lblQuantidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblQuantidade.setText("Quantidade");
		
		NumberTextField numberTextField = new NumberTextField(composite);
		txtQuantidade = numberTextField.getControl();
		txtQuantidade.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDescricao = new Label(composite, SWT.NONE);
		lblDescricao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescricao.setText("Descrição");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		return area;
	}

	@Override
	protected void okPressed() {
		TipoComodo tipo = (TipoComodo) SelectionHelper.getObject(comboViewer.getSelection());

		if(tipo == null){
			setErrorMessage("Selecione um tipo de cômodo");
			return;
		}
		
		comodo = new Comodo(null);
		comodo.setDescricao(text_2.getText());
		
		if(!txtQuantidade.getText().isEmpty())
			comodo.setQuantidade(new Integer(txtQuantidade.getText()));
		
		comodo.setTipoComodo(tipo);
		
		super.okPressed();
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Softimob");
		super.configureShell(newShell);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(400, 250);
	}

	public Comodo getComodo() {
		return comodo;
	}
	
}
