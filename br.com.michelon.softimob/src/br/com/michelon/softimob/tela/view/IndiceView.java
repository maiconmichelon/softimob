package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.IndiceEditorInput;
import br.com.michelon.softimob.modelo.Indice;
import br.com.michelon.softimob.tela.editor.IndiceEditor;

public class IndiceView extends GenericView<Indice>{

	private Map<String, String> atributos;
	
	public IndiceView() {
		super(true);
		
		atributos = Maps.newLinkedHashMap();
		atributos.put("nome", "Nome");
	}

	@Override
	protected void excluir(List<Indice> objetos) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getTitleView() {
		return "Indices";
	}

	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new IndiceEditorInput();
	}

	@Override
	protected String getEditorId() {
		return IndiceEditor.ID;
	}

	@Override
	protected List<Indice> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
