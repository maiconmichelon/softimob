package br.com.michelon.softimob.tela.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class ValidationErrorDialog extends TitleAreaDialog {
	private final String erros;
	private final String acao;

	public ValidationErrorDialog(Shell parentShell, String erros) {
		this(parentShell, erros, "salvar o registro");
	}
	
	public ValidationErrorDialog(Shell parentShell, String erros, String acao) {
		super(parentShell);
		this.erros = erros;
		this.acao = acao;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Erro ao " + acao);
		setMessage("Os seguintes erros foram encontrados ao " + acao + ".");

		Composite area = (Composite) super.createDialogArea(parent);
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		StyledText txtErros = new StyledText(composite, SWT.BORDER);
		txtErros.setEditable(false);
		txtErros.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		txtErros.setText(erros);
		
		return area;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Softimob");
		super.configureShell(newShell);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(421, 253);
	}

}
