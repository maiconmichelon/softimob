package br.com.michelon.softimob.tela.editor;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.service.ClienteService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.tela.widget.CEPTextField;
import br.com.michelon.softimob.tela.widget.CPFTextField;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.PhoneTextField;

public class ClientePFEditor extends GenericEditor<Cliente> {

	private ClienteService service = new ClienteService();

	private Text text_2;
	private Text text_6;
	private Text text_9;
	private Text text_10;
	private Text text;
	private Text text_3;
	private Text text_5;
	private Text text_4;
	private Text text_7;
	private Text text_1;
	private Text text_8;
	private Text text_11;

	public ClientePFEditor() {
		super(Cliente.class);
	}

	@Override
	public GenericService<Cliente> getService() {
		return service;
	}

	@Override
	public void afterCreatePartControl(Composite parent1) {
		GridLayout gl2_parent = new GridLayout(1, false);
		gl2_parent.verticalSpacing = 10;
		parent1.setLayout(gl2_parent);
		parent1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1,
				1));
		
				Composite parent = new Composite(parent1, SWT.NONE);
				parent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
				GridLayout gl_parent = new GridLayout(5, false);
				gl_parent.verticalSpacing = 8;
				parent.setLayout(gl_parent);
				
						Label lblNome = new Label(parent, SWT.NONE);
						lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
								1, 1));
						lblNome.setText("Nome");
						
								text_3 = new Text(parent, SWT.BORDER);
								text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
										1));
								new Label(parent, SWT.NONE);
								new Label(parent, SWT.NONE);
								new Label(parent, SWT.NONE);
								
										Label lblDataDeNascimento = new Label(parent, SWT.NONE);
										lblDataDeNascimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
												false, false, 1, 1));
										lblDataDeNascimento.setText("Data de Nascimento");
										
												DateTextField dateTextField = new DateTextField(parent);
												text_11 = dateTextField.getControl();
												text_11.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
														1, 1));
												new Label(parent, SWT.NONE);
												new Label(parent, SWT.NONE);
												new Label(parent, SWT.NONE);
												
														Label lblEndereo = new Label(parent, SWT.NONE);
														lblEndereo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
																false, 1, 1));
														lblEndereo.setText("CPF");
														
																CPFTextField textField = new CPFTextField(parent);
																text_1 = textField.getControl();
																text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
																		1));
																
																		Label lblCep = new Label(parent, SWT.NONE);
																		lblCep.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
																				1, 1));
																		lblCep.setText("RG");
																		
																				text_2 = new Text(parent, SWT.BORDER);
																				text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
																						1));
																				new Label(parent, SWT.NONE);
																				
																						Label lblRg = new Label(parent, SWT.NONE);
																						lblRg.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
																								1, 1));
																						lblRg.setText("Filiação");
																						
																								text_5 = new Text(parent, SWT.BORDER);
																								text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
																										1));
																								
																										Label lblCpf = new Label(parent, SWT.NONE);
																										lblCpf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
																												1, 1));
																										lblCpf.setText("Estado Civil");
																										
																												ComboViewer comboViewer = new ComboViewer(parent, SWT.READ_ONLY);
																												Combo combo = comboViewer.getCombo();
																												combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
																														1));
																												new Label(parent, SWT.NONE);
																												
																														Label lblNacionalidade = new Label(parent, SWT.NONE);
																														lblNacionalidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
																																false, false, 1, 1));
																														lblNacionalidade.setText("Nacionalidade");
																														
																																text = new Text(parent, SWT.BORDER);
																																text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
																																new Label(parent, SWT.NONE);
																																new Label(parent, SWT.NONE);
																																new Label(parent, SWT.NONE);
																																
																																		Label lblTelefone_1 = new Label(parent, SWT.NONE);
																																		lblTelefone_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
																																				false, 1, 1));
																																		lblTelefone_1.setText("Telefone");
																																		
																																				PhoneTextField phoneTextField = new PhoneTextField(parent);
																																				text_4 = phoneTextField.getControl();
																																				text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
																																						1));
																																				
																																						Label lblCelular = new Label(parent, SWT.NONE);
																																						lblCelular.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
																																								false, 1, 1));
																																						lblCelular.setText("Celular");
																																						
																																								PhoneTextField phoneTextField_1 = new PhoneTextField(parent);
																																								text_7 = phoneTextField_1.getControl();
																																								text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
																																										1));
																																								new Label(parent, SWT.NONE);
																																								
																																										Label lblEmail = new Label(parent, SWT.NONE);
																																										lblEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
																																												false, 1, 1));
																																										lblEmail.setText("E-mail");
																																										
																																												text_6 = new Text(parent, SWT.BORDER);
																																												text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
																																														1));
																																												new Label(parent, SWT.NONE);
																																												new Label(parent, SWT.NONE);
																																												new Label(parent, SWT.NONE);

		Group grpEndereo = new Group(parent1, SWT.NONE);
		grpEndereo.setText("Endereço");
		GridLayout gl_grpEndereo = new GridLayout(4, false);
		gl_grpEndereo.verticalSpacing = 10;
		grpEndereo.setLayout(gl_grpEndereo);
		grpEndereo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				4, 1));

		Label lblCep_1 = new Label(grpEndereo, SWT.NONE);
		lblCep_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblCep_1.setText("CEP");

		CEPTextField textField_1 = new CEPTextField(grpEndereo);
		text_8 = textField_1.getControl();
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(grpEndereo, SWT.NONE);
		new Label(grpEndereo, SWT.NONE);

		Label lblUf = new Label(grpEndereo, SWT.NONE);
		lblUf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblUf.setText("UF");

		ComboViewer comboViewer_1 = new ComboViewer(grpEndereo, SWT.READ_ONLY);
		Combo combo_1 = comboViewer_1.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblCidade = new Label(grpEndereo, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblCidade.setText("Cidade");

		ComboViewer comboViewer_2 = new ComboViewer(grpEndereo, SWT.READ_ONLY);
		Combo combo_2 = comboViewer_2.getCombo();
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblBairro = new Label(grpEndereo, SWT.NONE);
		lblBairro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblBairro.setText("Bairro");

		ComboViewer comboViewer_3 = new ComboViewer(grpEndereo, SWT.READ_ONLY);
		Combo combo_3 = comboViewer_3.getCombo();
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblRua = new Label(grpEndereo, SWT.NONE);
		lblRua.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblRua.setText("Rua");

		ComboViewer comboViewer_4 = new ComboViewer(grpEndereo, SWT.READ_ONLY);
		Combo combo_4 = comboViewer_4.getCombo();
		combo_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblNmero = new Label(grpEndereo, SWT.NONE);
		lblNmero.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNmero.setText("Número");

		text_9 = new Text(grpEndereo, SWT.BORDER);
		text_9.setText("");
		text_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(grpEndereo, SWT.NONE);
		new Label(grpEndereo, SWT.NONE);

		Label lblComplemento = new Label(grpEndereo, SWT.NONE);
		lblComplemento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblComplemento.setText("Complemento");

		text_10 = new Text(grpEndereo, SWT.BORDER);
		text_10.setText("");
		text_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		new Label(grpEndereo, SWT.NONE);
		new Label(grpEndereo, SWT.NONE);
	}

}
