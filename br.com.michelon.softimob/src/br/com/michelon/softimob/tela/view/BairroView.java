package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.BairroEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.tela.editor.BairroEditor;

public class BairroView extends GenericView<Bairro>{

	private Map<String, String> atributos;
	
	public BairroView(){
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Bairro", "nome");
		atributos.put("Cidade", "cidade.nome");
		atributos.put("UF", "cidade.estado.nome");
	}
	
	@Override
	protected void excluir(List<Bairro> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Bairro";
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
		return new BairroEditorInput();
	}

	@Override
	protected String getEditorId() {
		return BairroEditor.ID;
	}

	@Override
	protected List<Bairro> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
