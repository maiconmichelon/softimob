package br.com.michelon.softimob.aplicacao.command;

import br.com.michelon.softimob.aplicacao.editorInput.FuncionarioEditorInput;
import br.com.michelon.softimob.tela.editor.FuncionarioEditor;

public class CmdFuncionario extends GenericAbstractHandler {

	public CmdFuncionario() {
		this.editorInput = new FuncionarioEditorInput();
		this.id = FuncionarioEditor.ID;
	}
	
}
