package br.com.michelon.softimob.tela.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class TipoImovelEditor extends SoftimobEditor{
	
	public static final String ID = "br.michelon.softimob.tela.editor.TipoImovelEditor"; //$NON-NLS-1$
	
	private Text text;
	public TipoImovelEditor() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblTipoImvel = new Label(composite, SWT.NONE);
		lblTipoImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipoImvel.setText("Descrição");
		
		text = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text.widthHint = 261;
		text.setLayoutData(gd_text);
	}

	@Override
	protected void salvar() {
		// TODO Auto-generated method stub
		
	}
	
}
