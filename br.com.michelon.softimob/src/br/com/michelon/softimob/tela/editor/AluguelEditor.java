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
import br.com.michelon.softimob.tela.widget.DateTimeTextField;
import br.com.michelon.softimob.tela.widget.DateTextField;
import br.com.michelon.softimob.tela.widget.MoneyTextField;
import org.eclipse.wb.swt.ResourceManager;

public class AluguelEditor extends SoftimobEditor{
	private Text text;
	private Text text_1;
	private Text text_4;
	private Text text_3;
	private Text text_2;
	private TableViewerBuilder tvbComissao;
	public AluguelEditor() {
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(4, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblImvel = new Label(parent, SWT.NONE);
		lblImvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblImvel.setText("Imóvel");
		
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button btnSelecionar = new Button(parent, SWT.NONE);
		btnSelecionar.setText("...");
		
		Label lblValor = new Label(parent, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Cliente");
		
		text_1 = new Text(parent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button button = new Button(parent, SWT.NONE);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button.setText("...");
		
		Label lblValor_1 = new Label(parent, SWT.NONE);
		lblValor_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor_1.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(parent);
		text_2 = moneyTextField.getControl();
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		
		Label lblData = new Label(parent, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data");
		
		DateTextField dateTextField = new DateTextField(parent);
		text_3 = dateTextField.getControl();
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
		
		criarTabelaComissao(composite);
		
		Button btnAdicionar = new Button(grpComisso, SWT.NONE);
		btnAdicionar.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/add/add16.png"));
		btnAdicionar.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true, 1, 1));
		btnAdicionar.setText("Adicionar");
		
		Button btnRemover = new Button(grpComisso, SWT.NONE);
		btnRemover.setImage(ResourceManager.getPluginImage("br.com.michelon.softimob", "icons/delete/delete16.png"));
		btnRemover.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1));
		btnRemover.setText("Remover");
		
	}

	private void criarTabelaComissao(Composite composite){
		TableViewerBuilder tvbComissao = new TableViewerBuilder(composite);
		
		tvbComissao.createColumn("Nome").bindToProperty("funcionario.nome").build();
		tvbComissao.createColumn("Valor").bindToProperty("valor").makeEditable().build();
	}
	
	@Override
	protected void salvar() {
		
	}
}
