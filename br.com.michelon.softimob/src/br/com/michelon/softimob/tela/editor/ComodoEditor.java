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
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.ResourceManager;

public class ComodoEditor extends SoftimobEditor{

	public static final String ID = "br.com.michelon.softimob.tela.editor.ComodoEditor"; //$NON-NLS-1$
	
	private Text text;

	private TableViewerBuilder tvb;

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
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 160;
		text.setLayoutData(gd_text);
		new Label(composite, SWT.NONE);
		
		Group gpTipoImovel = new Group(composite, SWT.NONE);
		gpTipoImovel.setText("Imóveis");
		gpTipoImovel.setLayout(new GridLayout(2, false));
		GridData gd_gpTipoImovel = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1);
		gd_gpTipoImovel.heightHint = 127;
		gpTipoImovel.setLayoutData(gd_gpTipoImovel);
		
		Composite cpTipoImovel = new Composite(gpTipoImovel, SWT.NONE);
		cpTipoImovel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		cpTipoImovel.setLayout(new GridLayout(1, false));
		
		criarTipoImovel(cpTipoImovel);	
		
		Button btnAdicionar = new Button(gpTipoImovel, SWT.NONE);
		btnAdicionar.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		btnAdicionar.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1));
		btnAdicionar.setText("Adicionar");
		
		Button btnRemover = new Button(gpTipoImovel, SWT.NONE);
		btnRemover.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/delete/delete16.png"));
		btnRemover.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1));
		btnRemover.setText("Remover");
	}

	private void criarTipoImovel(Composite cpTipoImovel) {
		tvb = new TableViewerBuilder(cpTipoImovel);
		
		tvb.createColumn("Descrição").bindToProperty("tipoImovel.descricao").build();
//		tvb.createColumn("Pré-selecionado").bindToProperty("preSelecionado").makeEditable(new CheckboxCellEditor()).build();
	}
}
