package br.com.michelon.softimob.tela.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;

import de.ralfebert.rcputils.tables.TableViewerBuilder;
import org.eclipse.wb.swt.SWTResourceManager;

public class AluguelEditor extends SoftimobEditor{
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_4;
	public AluguelEditor() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		parent.setLayout(new GridLayout(4, false));
		
		Label lblImvel = new Label(parent, SWT.NONE);
		lblImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblImvel.setText("Imóvel");
		
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button btnSelecionar = new Button(parent, SWT.NONE);
		btnSelecionar.setText("Selecionar");
		
		Label lblValor = new Label(parent, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Cliente");
		
		text_1 = new Text(parent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button button = new Button(parent, SWT.NONE);
		button.setText("Selecionar");
		
		Label lblValor_1 = new Label(parent, SWT.NONE);
		lblValor_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_1.setText("Valor");
		
		text_2 = new Text(parent, SWT.BORDER);
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text_2.widthHint = 128;
		text_2.setLayoutData(gd_text_2);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblData = new Label(parent, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		DateTime dateTime = new DateTime(parent, SWT.BORDER);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblDurao = new Label(parent, SWT.NONE);
		lblDurao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDurao.setText("Duração");
		
		text_4 = new Text(parent, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		lblNewLabel.setText("meses");
		new Label(parent, SWT.NONE);
		
		Group grpComisso = new Group(parent, SWT.NONE);
		grpComisso.setText("Comissão");
		grpComisso.setLayout(new GridLayout(2, false));
		grpComisso.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1));
		
		Composite composite = new Composite(grpComisso, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		
		TableViewerBuilder tvbComissao = new TableViewerBuilder(composite);
		tvbComissao.createColumn("Nome").bindToProperty("funcionario.nome").build();
		tvbComissao.createColumn("Valor").bindToProperty("valor").makeEditable().build();
		
		Button btnAdicionar = new Button(grpComisso, SWT.NONE);
		btnAdicionar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnAdicionar.setText("Adicionar");
		
		Button btnRemover = new Button(grpComisso, SWT.NONE);
		btnRemover.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnRemover.setText("Remover");
		
	}

	@Override
	protected void salvar() {
		
	}
}
