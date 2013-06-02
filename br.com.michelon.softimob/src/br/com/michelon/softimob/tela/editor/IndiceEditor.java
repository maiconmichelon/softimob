package br.com.michelon.softimob.tela.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.IndiceService;
import br.com.michelon.softimob.modelo.Indice;
import de.ralfebert.rcputils.tables.TableViewerBuilder;
import de.ralfebert.rcputils.tables.format.Formatter;

public class IndiceEditor extends GenericEditor<Indice>{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.IndiceEditor";
	
	private IndiceService service = new IndiceService();
	
	private Text text;
	private TableViewerBuilder tvbIndices;
	
	public IndiceEditor() {
		super(Indice.class);
	}

	@Override
	public GenericService<Indice> getService() {
		return service;
	}
	
	@Override
	public void afterCreatePartControl(Composite parent) {
		
		Label lblIndice = new Label(parent, SWT.NONE);
		lblIndice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblIndice.setText("Indice");
		
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 2, 1));
		criarTabelaIndices(composite);
	}

	private void criarTabelaIndices(Composite composite) {
		tvbIndices = new TableViewerBuilder(composite);
		
		tvbIndices.createColumn("Mês").bindToProperty("data").format(Formatter.forDate(FormatterHelper.getSimpleDateFormatPeriodo())).build();
		tvbIndices.createColumn("Porcentagem").bindToProperty("porcentagem").build();
	}

}
