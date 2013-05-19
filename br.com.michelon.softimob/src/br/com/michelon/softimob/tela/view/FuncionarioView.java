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
		super(true);
		
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Nome", "|20|nome");
		atributos.put("Departamento", "|12|departamento.nome");
		atributos.put("Data de Nascimento", "|12|dataNascimento");
		atributos.put("Telefone/Ramal", "|12|telefoneRamal");
		atributos.put("Celular", "|12|celular");
		atributos.put("E-mail", "|12|email");
		atributos.put("Data de Admissão", "|12|dataAdmissao");
		
	}
	
	@Override
	protected void excluir(List<Funcionario> objetos) {
	}

	@Override
	protected String getTitleView() {
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
