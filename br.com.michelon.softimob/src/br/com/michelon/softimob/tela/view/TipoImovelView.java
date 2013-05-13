package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.TipoImovelEditorInput;
import br.com.michelon.softimob.modelo.TipoImovel;
import br.com.michelon.softimob.tela.editor.TipoImovelEditor;

public class TipoImovelView extends GenericView<TipoImovel>{

	private Map<String, String> atributos;
	
	public TipoImovelView(){
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Nome", "nome");
	}
	
	@Override
	protected void excluir(List<TipoImovel> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Tipos de Imóvel";
	}

	@Override
	protected Image getImage() {
		return Images.SEARCH_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new TipoImovelEditorInput();
	}

	@Override
	protected String getEditorId() {
		return TipoImovelEditor.ID;
	}
	
	@Override
	protected List<TipoImovel> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
