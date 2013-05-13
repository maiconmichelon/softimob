package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.PlanoContaEditorInput;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.editor.PlanoContaEditor;

import com.google.common.collect.Maps;

public class PlanoContaView extends GenericView<PlanoConta>{

	private Map<String, String> atributos;
	
	public PlanoContaView(){
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Código", "código");
		atributos.put("Nome", "nome");
		atributos.put("Tipo", "tipo");
	}
	
	@Override
	protected void excluir(List<PlanoConta> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Plano de contas";
	}

	@Override
	protected Image getImage() {
		return Images.PLANOCONTA_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new PlanoContaEditorInput();
	}

	@Override
	protected String getEditorId() {
		return PlanoContaEditor.ID;
	}

	@Override
	protected List<PlanoConta> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
