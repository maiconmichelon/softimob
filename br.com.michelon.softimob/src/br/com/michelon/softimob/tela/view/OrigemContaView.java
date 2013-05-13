package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.OrigemContaEditorInput;
import br.com.michelon.softimob.tela.editor.OrigemContaEditor;

public class OrigemContaView extends GenericView<OrigemContaView>{

	private Map<String, String> atributos;
	
	public OrigemContaView(){
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Nome", "nome");
	}
	
	@Override
	protected void excluir(List<OrigemContaView> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Origens das contas";
	}

	@Override
	protected Image getImage() {
		return Images.CONTA_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new OrigemContaEditorInput();
	}

	@Override
	protected String getEditorId() {
		return OrigemContaEditor.ID;
	}

	@Override
	protected List<OrigemContaView> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
