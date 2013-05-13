package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ModeloContratoEditorInput;
import br.com.michelon.softimob.modelo.ModeloContrato;
import br.com.michelon.softimob.tela.editor.ModeloContratoEditor;

public class ModeloContratoView extends GenericView<ModeloContrato>{

	private Map<String, String> atributos;
	
	public ModeloContratoView(){
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Descrição", "descricao");
	}
	
	@Override
	protected void excluir(List<ModeloContrato> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Modelos de contrato";
	}

	@Override
	protected Image getImage() {
		return Images.CONTRATO_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new ModeloContratoEditorInput();
	}

	@Override
	protected String getEditorId() {
		return ModeloContratoEditor.ID;
	}

	@Override
	protected List<ModeloContrato> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
