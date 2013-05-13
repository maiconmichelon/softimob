package br.com.michelon.softimob.tela.view;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.View;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.tela.editor.ImovelEditor;

public class ChaveView extends GenericView<View>{

	private Map<String, String> atributos;
	
	public ChaveView() {
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Número", "numero");
		atributos.put("Imóvel", "imóvel");
	}
	
	@Override
	protected void excluir(List<View> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Chaves";
	}

	@Override
	protected Image getImage() {
		return Images.CHAVE_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new ImovelEditorInput();
	}

	@Override
	protected String getEditorId() {
		return new ImovelEditor().ID;
	}

	@Override
	protected List<View> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
