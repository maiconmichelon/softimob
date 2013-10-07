package br.com.michelon.softimob.tela.dialog;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.service.AluguelService;
import br.com.michelon.softimob.aplicacao.service.ContaPagarReceberService;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import br.com.michelon.softimob.tela.widget.NumberTextField;

import com.google.common.collect.Lists;

public class ParcelaDialog extends TitleAreaDialog{
	
	private Text txtValor;
	private DateTextField dtfInicio;
	private Text txtNumParcelas;
	private Button btnPagar;
	private Button btnReceber;
	private Aluguel aluguel;
	private List<ContaPagarReceber> contas = Lists.newArrayList(); 
	
	public ParcelaDialog(Shell parentShell, Aluguel aluguel) {
		super(parentShell);
		this.aluguel = aluguel;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Adicionar parcelas");
		setTitle("Parcelas");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblInicio = new Label(composite, SWT.NONE);
		lblInicio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInicio.setText("Inicio");
		
		dtfInicio = new DateTextField(composite);
		Text txtDia = dtfInicio.getControl();
		txtDia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label lblNumDeParcelas = new Label(composite, SWT.NONE);
		lblNumDeParcelas.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNumDeParcelas.setText("Núm de Parcelas");
		
		NumberTextField numberTextField = new NumberTextField(composite);
		txtNumParcelas = numberTextField.getControl();
		txtNumParcelas.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblValor = new Label(composite, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(composite);
		txtValor = moneyTextField.getControl();
		txtValor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(composite, SWT.NONE);
		
		btnPagar = new Button(composite, SWT.RADIO);
		btnPagar.setText("Pagar");
		btnPagar.setSelection(true);
		
		btnReceber = new Button(composite, SWT.RADIO);
		btnReceber.setText("Receber");
		new Label(composite, SWT.NONE);
		
		return composite;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(274, 261);
	}
	
	protected void configureShell(Shell newShell) {
		newShell.setText("Adicionar parcelas");
		super.configureShell(newShell);
	};
	
	@Override
	protected void buttonPressed(int buttonId) {
		if(buttonId == IDialogConstants.OK_ID){
			Date dataInicio = dtfInicio.getValue();
			BigDecimal valor = FormatterHelper.getDefaultValueFormatterToMoney().parse(txtValor.getText());
			Integer numParcelas = Integer.parseInt(txtNumParcelas.getText());
			
			ParametrosEmpresa parametrosEmpresa = ParametrosEmpresa.getInstance();
			
			if(parametrosEmpresa.getTipoContaAluguel() == null){
				setErrorMessage("O tipo da conta de locação deve ser cadastrada.");
				return;
			} else {
				contas.addAll(new ContaPagarReceberService().gerarParcelas(numParcelas, valor, dataInicio, btnPagar.getSelection() ? ContaPagarReceber.PAGAR : ContaPagarReceber.RECEBER, 
						aluguel.getDataGeracao(), new AluguelService().geraObservacoesContaAluguel(aluguel), parametrosEmpresa.getTipoContaAluguel()));
			}
		}
		super.buttonPressed(buttonId);
	}
	
	public List<ContaPagarReceber> getParcelas() {
		return contas;
	}
	
}
