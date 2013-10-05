package br.com.michelon.softimob.tela.dialog;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.nebula.jface.viewer.radiogroup.RadioGroupViewer;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.helper.SelectionHelper;
import br.com.michelon.softimob.aplicacao.service.PropostaService;
import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.tela.widget.DateTextField;

public class FinalizarPropostaDialog extends TitleAreaDialog {
	
	private Proposta proposta;
	private Logger log = Logger.getLogger(getClass());

	private RadioGroup radioGroup;
	private RadioGroupViewer radioGroupViewer;

	private DateTextField dtData;
	private Text txtData;
	
	public FinalizarPropostaDialog(Shell parentShell, Proposta proposta) {
		super(parentShell);
		this.proposta = proposta;
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite area = (Composite) super.createDialogArea(parent);
		Composite composite = new Composite(area, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridData gd_text_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 120;

		Label lblData = new Label(composite, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		dtData = new DateTextField(composite);
		txtData = dtData.getControl();
		GridData gd_text_3 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_3.widthHint = 120;
		txtData.setLayoutData(gd_text_3);
		new Label(composite, SWT.NONE);
		
		radioGroupViewer = new RadioGroupViewer(composite, SWT.NONE);
		radioGroup = radioGroupViewer.getRadioGroup();
		radioGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		radioGroupViewer.setContentProvider(ArrayContentProvider.getInstance());
		radioGroupViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return element.equals(Proposta.ACEITA) ? "Aceita" : "Recusada";
			}
		});
		radioGroupViewer.setInput(new Integer[] { Proposta.ACEITA, Proposta.RECUSADA});

		setTitleAndMessage();

		return area;
	}

	protected void okPressed() {
		try {
			Integer selecao = (Integer) SelectionHelper.getObject(radioGroupViewer.getSelection());

			if (selecao == null){
				DialogHelper.openWarning("Informe o status da proposta.");
				return;
			}
				
			proposta.setStatus(selecao);
			proposta.setDataFechamento(dtData.getValue());
			new PropostaService().salvar(proposta);
			
			DialogHelper.openInformation("Proposta finalizada com sucesso.");
			super.okPressed();
		} catch (Exception e) {
			log.error("Erro ao finalizar proposta.", e);
			DialogHelper.openErrorMultiStatus("Erro ao finalizar proposta.", e.getMessage());
		}
	};

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Finalizar Proposta");
		super.configureShell(newShell);
	}
	
	private void setTitleAndMessage() {
		setTitle("Finalizar Proposta");
		setMessage("Informe se a proposta foi aceita ou recusada.");
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(346, 218);
	}
	
}
