package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.FuncionarioEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.tela.editor.FuncionarioEditor;

public class FuncionarioView extends GenericView<Funcionario>{

	private Map<String, String> atributos;
	
	public FuncionarioView() {
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Nome", "nome");
		atributos.put("Departamento", "departamento.nome");
		atributos.put("Telefone/Ramal", "telefoneRamal");
		atributos.put("Celular", "celular");
		atributos.put("E-mail", "email");
		
	}
	
	@Override
	protected void excluir(List<Funcionario> objetos) {
	}

	@Override
	protected String getName() {
		return "Funcionários";
	}

	@Override
	protected Image getImage() {
		return Images.FUNCIONARIO_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new FuncionarioEditorInput();
	}

	@Override
	protected String getEditorId() {
		return FuncionarioEditor.ID;
	}

	@Override
	protected List<Funcionario> getInput() {
		return null;
	}

}
