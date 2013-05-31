package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.modelo.Vistoria;
import br.com.michelon.softimob.tela.editor.ImovelEditor;

public class VistoriaView extends GenericView<Vistoria>{

	private Map<String, String> atributos;
	
	public VistoriaView() {
		super(false);
		
		atributos = Maps.newHashMap();
		
		atributos.put("Imovel", "|10|imovel.numero");
	}
	
	@Override
	protected void excluir(List<Vistoria> objetos) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getTitleView() {
		return "Vistorias";
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
		return new ImovelEditorInput();
	}

	@Override
	protected String getEditorId() {
		return ImovelEditor.ID;
	}

	@Override
	protected Object getElementToEdit(Vistoria object) {
		return object.getImovel();
	}
	
	@Override
	protected List<Vistoria> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
}
