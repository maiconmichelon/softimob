package br.com.michelon.softimob.tela.editor;

import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class ComodoEditor extends SoftimobEditor{

	public static final String ID = "br.michelon.softimob.tela.editor.ComodoEditor"; //$NON-NLS-1$
	
	private Text text;

	public ComodoEditor() {
	}
	
	@Override
	protected void salvar() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblDescrio = new Label(composite, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrio.setText("Descrição");
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Composite cpTipoImovel = new Composite(composite, SWT.NONE);
		cpTipoImovel.setLayout(new GridLayout(1, false));
		cpTipoImovel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		
		Button btnAdicionar = new Button(composite, SWT.NONE);
		btnAdicionar.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1));
		btnAdicionar.setText("Adicionar");
		new Label(composite, SWT.NONE);
		
		Button btnRemover = new Button(composite, SWT.NONE);
		btnRemover.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnRemover.setText("Remover");
		
		TableViewerBuilder tvb = new TableViewerBuilder(cpTipoImovel);
		
		tvb.createColumn("Descrição").bindToProperty("tipoImovel.descricao").build();
		tvb.createColumn("Pré-selecionado").bindToProperty("preSelecionado").makeEditable(new CheckboxCellEditor()).build();
		
	}
}
