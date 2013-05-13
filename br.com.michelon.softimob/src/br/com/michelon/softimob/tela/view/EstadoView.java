package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.EstadoEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.modelo.Estado;
import br.com.michelon.softimob.tela.editor.EstadoEditor;

public class EstadoView extends GenericView<Estado>{

	private Map<String, String> atributos;
	
	public EstadoView(){
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("UF", "uf");
		atributos.put("Nome", "nome");
	}
	
	@Override
	protected void excluir(List<Estado> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Estados";
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
		return new EstadoEditorInput();
	}

	@Override
	protected String getEditorId() {
		return EstadoEditor.ID;
	}

	@Override
	protected List<Estado> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
