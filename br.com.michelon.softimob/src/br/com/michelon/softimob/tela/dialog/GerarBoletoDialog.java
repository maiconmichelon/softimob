package br.com.michelon.softimob.tela.dialog;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.OkListElementDialogListener;
import br.com.michelon.softimob.modelo.Cliente;

public class GerarBoletoDialog extends TitleAreaDialog {
	
	private Text txtCliente;
	private Cliente cliente;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public GerarBoletoDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Informe os dados do boleto.");
		setTitle("Gerar Boleto");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(4, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label lblCliente = new Label(container, SWT.NONE);
		lblCliente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCliente.setText("Cliente");
		
		txtCliente = new Text(container, SWT.BORDER | SWT.READ_ONLY);
		txtCliente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnAddCliente = new Button(container, SWT.NONE);
		btnAddCliente.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CLIENTE, btnAddCliente, new OkListElementDialogListener() {
			@Override
			public void ok(Object obj) {
				cliente = (Cliente) obj;
				txtCliente.setText(cliente.getNome());
			}
		});
		
		Button btnRemover = new Button(container, SWT.NONE);
		btnRemover.setImage(ImageRepository.REMOVE_16.getImage());
		btnRemover.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cliente = null;
				txtCliente.setText(StringUtils.EMPTY);
			}
		});
		
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

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 208);
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Gerar Boleto");
		super.configureShell(newShell);
	}
	
	@Override
	protected void okPressed() {
		if(cliente == null){
			setErrorMessage("Selecione um cliente.");
			return;
		}
		super.okPressed();
	}

	public Cliente getCliente() {
		return cliente;
	}
}
