package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.RuaEditorInput;
import br.com.michelon.softimob.modelo.Rua;
import br.com.michelon.softimob.tela.editor.RuaEditor;

public class RuaView extends GenericView<Rua>{

	private Map<String, String> atributos;
	
	public RuaView(){
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Rua", "nome");
		atributos.put("Bairro", "bairro.nome");
		atributos.put("Cidade", "bairro.cidade.nome");
		atributos.put("UF", "bairro.cidade.estado.nome");
	}
	
	@Override
	protected void excluir(List<Rua> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Ruas";
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
		return new RuaEditorInput();
	}

	@Override
	protected String getEditorId() {
		return RuaEditor.ID;
	}

	@Override
	protected List<Rua> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
