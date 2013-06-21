package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.aplicacao.service.ImovelService;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.tela.editor.ImovelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.imovelXViewer.ImovelXViewer;

import com.google.common.collect.Lists;

public class ImovelView extends GenericView<Imovel>{

	private List<ColumnProperties> atributos;
	private ImovelService service = new ImovelService();
	
	public ImovelView() {
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Código", "codigo"));
		atributos.add(new ColumnProperties("Endereço", "endereco"));
	}
	
	@Override
	protected void excluir(List<Imovel> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTitleView() {
		return "Imóveis";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.IMOVEL_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Imovel t) {
		return new ImovelEditorInput();
	}

	@Override
	protected String getEditorId(Imovel t) {
		return ImovelEditor.ID;
	}

	@Override
	protected List<Imovel> getInput() {
		return service.findAll();
	}

	@Override
	protected ColumnViewer criarTabela(Composite composite) {
		Composite cpTable = new Composite(composite, SWT.NONE);
		cpTable.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		XViewer imovelXViewer = new ImovelXViewer(cpTable, SWT.BORDER);
		
		return imovelXViewer;
	}
	
}
