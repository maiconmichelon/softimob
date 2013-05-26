package br.com.michelon.softimob.tela.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import br.com.michelon.softimob.tela.widget.PhoneTextField;
import br.com.michelon.softimob.tela.widget.DateTextField;

public class FuncionarioEditor extends GenericEditor {
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.FuncionarioEditor"; //$NON-NLS-1$
	
	private Text text;
	private Text text_1;
	private Text text_3;
	private Text text_2;
	private Text text_4;
	private Text text_5;
	
	public FuncionarioEditor() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.verticalSpacing = 10;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblNome = new Label(composite, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome");
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDepartamento = new Label(composite, SWT.NONE);
		lblDepartamento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDepartamento.setText("Departamento");
		
		ComboViewer comboViewer = new ComboViewer(composite, SWT.READ_ONLY);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDataDeNascimento = new Label(composite, SWT.NONE);
		lblDataDeNascimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeNascimento.setText("Data de Nascimento");
		
		DateTextField dateTextField = new DateTextField(composite);
		text_4 = dateTextField.getControl();
		GridData gd_text_4 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_4.widthHint = 79;
		text_4.setLayoutData(gd_text_4);
		
		Label lblTelefoneRamal = new Label(composite, SWT.NONE);
		lblTelefoneRamal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTelefoneRamal.setText("Telefone / Ramal");
		
		text_1 = new Text(composite, SWT.BORDER);
		GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 300;
		text_1.setLayoutData(gd_text_1);
		
		Label lblCelular = new Label(composite, SWT.NONE);
		lblCelular.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCelular.setText("Celular");
		
		PhoneTextField phoneTextField = new PhoneTextField(composite);
		text_2 = phoneTextField.getControl();
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 300;
		text_2.setLayoutData(gd_text_2);
		
		Label lblEmail = new Label(composite, SWT.NONE);
		lblEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEmail.setText("E-mail");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDataDeAdmisso = new Label(composite, SWT.NONE);
		lblDataDeAdmisso.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeAdmisso.setText("Data de Admissão");
		
		DateTextField dateTextField_1 = new DateTextField(composite);
		text_5 = dateTextField_1.getControl();
		GridData gd_text_5 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_5.widthHint = 79;
		text_5.setLayoutData(gd_text_5);
	}

	@Override
	protected void salvar() {
		// TODO Auto-generated method stub
		
	}
	
}
