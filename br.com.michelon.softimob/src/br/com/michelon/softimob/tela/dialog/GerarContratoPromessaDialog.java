package br.com.michelon.softimob.tela.dialog;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.tela.widget.DateTextField;

public class GerarContratoPromessaDialog extends TitleAreaDialog{

	private Label lblTitle;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;

	public GerarContratoPromessaDialog(Shell parentShell, Imovel imovel) {
		super(parentShell);
		
		setTitle("Geração de contrato de promessa de compra e venda.");
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public GerarContratoPromessaDialog(Shell parentShell){
		this(parentShell, null);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblDataDeIncio = new Label(composite, SWT.NONE);
		lblDataDeIncio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeIncio.setText("Data de Início");
		
		DateTextField dateTextField = new DateTextField(composite);
		text = dateTextField.getControl();
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text.widthHint = 79;
		text.setLayoutData(gd_text);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblDataDeVencimento = new Label(composite, SWT.NONE);
		lblDataDeVencimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeVencimento.setText("Data de Vencimento");
		
		DateTextField dateTextField_1 = new DateTextField(composite);
		text_1 = dateTextField_1.getControl();
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_1.widthHint = 79;
		text_1.setLayoutData(gd_text_1);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblImvel = new Label(composite, SWT.NONE);
		lblImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblImvel.setText("Imóvel");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(composite, SWT.NONE);
		button.setImage(ImageRepository.SEARCH_16.getImage());
		
		Button btnt = new Button(composite, SWT.NONE);
		btnt.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblContrato = new Label(composite, SWT.NONE);
		lblContrato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblContrato.setText("Contrato");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setImage(ImageRepository.SEARCH_16.getImage());
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setImage(ImageRepository.REMOVE_16.getImage());
	
		return composite;
	}
	
}
