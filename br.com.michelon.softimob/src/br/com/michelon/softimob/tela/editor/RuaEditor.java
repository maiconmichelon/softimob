package br.com.michelon.softimob.tela.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class RuaEditor extends SoftimobEditor{
	private Text text;
	public RuaEditor() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gridLayout = (GridLayout) parent.getLayout();
		gridLayout.verticalSpacing = 10;
		
		Label lblUf = new Label(parent, SWT.NONE);
		lblUf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUf.setText("UF");
		
		ComboViewer comboViewer = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblCidade = new Label(parent, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");
		
		ComboViewer comboViewer_1 = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo_1 = comboViewer_1.getCombo();
		GridData gd_combo_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo_1.widthHint = 228;
		combo_1.setLayoutData(gd_combo_1);
		
		Label lblBairro = new Label(parent, SWT.NONE);
		lblBairro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBairro.setText("Bairro");
		
		ComboViewer comboViewer_2 = new ComboViewer(parent, SWT.READ_ONLY);
		Combo combo_2 = comboViewer_2.getCombo();
		GridData gd_combo_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo_2.widthHint = 228;
		combo_2.setLayoutData(gd_combo_2);
		
		Label lblRua = new Label(parent, SWT.NONE);
		lblRua.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRua.setText("Rua");
		
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void salvar() {
		// TODO Auto-generated method stub
		
	}

}
