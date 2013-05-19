package br.com.michelon.softimob.tela.view;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.View;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.modelo.Chave;
import br.com.michelon.softimob.tela.editor.ImovelEditor;

public class ChaveView extends GenericView<Chave>{

	private Map<String, String> atributos;
	
	public ChaveView() {
		super(false);
		
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Número", "|10|numero");
		atributos.put("Imóvel", "|90|imóvel");
	}
	
	@Override
	protected void excluir(List<Chave> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTitleView() {
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
		return ImovelEditor.ID;
	}

	@Override
	protected Object getElementToEdit(Chave object) {
		return object.getImovel();
	}
	
	@Override
	protected List<Chave> getInput() {
		return null;
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
}
