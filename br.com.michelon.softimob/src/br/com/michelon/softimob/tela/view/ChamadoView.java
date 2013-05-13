package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.modelo.ChamadoReforma;
import br.com.michelon.softimob.tela.editor.ImovelEditor;

import com.google.common.collect.Maps;

public class ChamadoView extends GenericView<ChamadoReforma>{

	private Map<String, String> atributos;
	
	public ChamadoView(){
		atributos = Maps.newLinkedHashMap();
	}
	
	@Override
	protected void excluir(List<ChamadoReforma> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Chamados";
	}

	@Override
	protected Image getImage() {
		return Images.REFORMA_32.getImage();
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
	protected List<ChamadoReforma> getInput() {
		return null;
	}

}
