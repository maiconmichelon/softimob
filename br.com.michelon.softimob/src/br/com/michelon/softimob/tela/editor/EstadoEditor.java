package br.com.michelon.softimob.tela.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;

public class EstadoEditor extends SoftimobEditor {
	private Text text_1;
	private Text text;
	public EstadoEditor() {
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
		
	}

	@Override
	protected void salvar() {
		
	}

}
