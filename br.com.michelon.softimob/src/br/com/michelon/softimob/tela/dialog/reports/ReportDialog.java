package br.com.michelon.softimob.tela.dialog.reports;

import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.aplicacao.helper.ReportHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.listener.OnNenhumRegistroEncontrado;
import br.com.michelon.softimob.aplicacao.listener.OnSuccessfulListener;

public abstract class ReportDialog extends TitleAreaDialog {

	protected JasperPrint jprint;

	public ReportDialog(){
		this(ShellHelper.getActiveShell());
	}
	
	public ReportDialog(Shell shell) {
		super(shell);
		setShellStyle(SWT.MIN);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);

		criarComponentes(area);

		setTitle(getTitleDialog());
		setMessage(getMessage());

		return area;
	}

	protected abstract void criarComponentes(Composite area);

	protected abstract Map<String, Object> getParametros() throws ParametroNaoInformadoException;

	protected abstract String getCaminhoRelatorio();

	public abstract String getMessage();

	protected abstract String getTitleDialog();

	@Override
	protected void okPressed() {
		try{
			ReportHelper.gerarRelatorio(getParametros(), getCaminhoRelatorio(), new OnNenhumRegistroEncontrado() {
				@Override
				public void onError(String message) {
					setErrorMessage(message);
				}
			}, new OnSuccessfulListener() {
				@Override
				public void onSucessful(String message) {
					setErrorMessage(null);
				}
			});
		}catch(ParametroNaoInformadoException pe){
			setErrorMessage(pe.getMessage() == null ? "Informe os parâmetros corretamente." : pe.getMessage());
		}
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Softimob");
		super.configureShell(newShell);
	}

}
