package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.TipoImovelEditorInput;
import br.com.michelon.softimob.aplicacao.service.TipoImovelService;
import br.com.michelon.softimob.modelo.TipoImovel;
import br.com.michelon.softimob.tela.editor.TipoImovelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class TipoImovelView extends GenericView<TipoImovel>{

	private List<ColumnProperties> atributos;
	private TipoImovelService service = new TipoImovelService();
	
	public TipoImovelView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Nome", "nome", 60));
	}
	
	@Override
	protected void excluir(List<TipoImovel> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTitleView() {
		return "Tipos de Imóvel";
	}

	@Override
	protected Image getImage() {
		return Images.IMOVEL_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(TipoImovel t) {
		return new TipoImovelEditorInput();
	}

	@Override
	protected String getEditorId() {
		return TipoImovelEditor.ID;
	}
	
	@Override
	protected List<TipoImovel> getInput() {
		return service.findAll();
	}

}
