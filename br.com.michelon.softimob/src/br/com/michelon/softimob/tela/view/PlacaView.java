package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.PlacaEditorInput;
import br.com.michelon.softimob.tela.editor.PlacaEditor;

public class PlacaView extends GenericView<PlacaView>{

	private Map<String, String> atributos;
	
	public PlacaView() {
		atributos = Maps.newLinkedHashMap();
	
		atributos.put("Número", "numero");
		atributos.put("Localização", "imovel");
	}
	
	@Override
	protected void excluir(List<PlacaView> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Placas";
	}

	@Override
	protected Image getImage() {
		return Images.PLACA_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new PlacaEditorInput();
	}

	@Override
	protected String getEditorId() {
		return PlacaEditor.ID;
	}

	@Override
	protected List<PlacaView> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
