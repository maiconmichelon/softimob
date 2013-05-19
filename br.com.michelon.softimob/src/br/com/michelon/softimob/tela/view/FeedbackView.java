package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.modelo.Feedback;
import br.com.michelon.softimob.tela.editor.ImovelEditor;

import com.google.common.collect.Maps;

public class FeedbackView extends GenericView<Feedback>{

	private Map<String, String> atributos;
	
	public FeedbackView() {
		super(false);
		
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Data", "|10|data");
		atributos.put("Cliente", "|30|cliente.nome");
		atributos.put("Funcionário", "|30|funcionario.nome");
		atributos.put("Feedback", "|30|feedback");
	}
	
	@Override
	protected void excluir(List<Feedback> objetos) {
	}

	@Override
	protected String getTitleView() {
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
	protected Object getElementToEdit(Feedback object) {
		return object.getImovel();
	}
	
	@Override
	protected List<Feedback> getInput() {
		return null;
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
}
