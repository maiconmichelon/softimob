package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.ComodoEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.tela.editor.ComodoEditor;

public class ComodoView extends GenericView<Comodo>{

	private Map<String, String> atributos;
	
	public ComodoView(){
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Nome", "nome");
	}
	
	@Override
	protected void excluir(List<Comodo> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Cômodos";
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
		return new ComodoEditorInput();
	}

	@Override
	protected String getEditorId() {
		return ComodoEditor.ID;
	}

	@Override
	protected List<Comodo> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
