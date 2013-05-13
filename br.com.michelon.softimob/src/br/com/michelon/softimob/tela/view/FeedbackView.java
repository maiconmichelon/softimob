package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.modelo.Feedback;
import br.com.michelon.softimob.tela.editor.ImovelEditor;

public class FeedbackView extends GenericView<Feedback>{

	private Map<String, String> atributos;
	
	public FeedbackView() {
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Data", "data");
		atributos.put("Cliente", "cliente.nome");
		atributos.put("Funcionário", "funcionario.nome");
		atributos.put("Feedback", "feedback");
	}
	
	@Override
	protected void excluir(List<Feedback> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Feedbacks";
	}

	@Override
	protected Image getImage() {
		return Images.FEEDBACK_32_2.getImage();
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
	protected List<Feedback> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
