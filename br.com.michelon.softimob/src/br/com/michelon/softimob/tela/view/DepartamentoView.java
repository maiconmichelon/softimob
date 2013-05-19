package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.DepartamentoEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.modelo.Departamento;
import br.com.michelon.softimob.tela.editor.DepartamentoEditor;

import com.google.common.collect.Maps;

public class DepartamentoView extends GenericView<Departamento>{

	private Map<String, String> atributos;
	
	public DepartamentoView() {
		super(true);
		
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Nome", "|10|nome");
	}
	
	@Override
	protected void excluir(List<Departamento> objetos) {
	}

	@Override
	protected String getTitleView() {
		return "Departamento";
	}

	@Override
	protected Image getImage() {
		return Images.DEPARTAMENTO_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new DepartamentoEditorInput();
	}

	@Override
	protected String getEditorId() {
		return DepartamentoEditor.ID;
	}

	@Override
	protected List<Departamento> getInput() {
		return null;
	}

}
