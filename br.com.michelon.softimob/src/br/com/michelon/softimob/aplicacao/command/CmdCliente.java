package br.com.michelon.softimob.aplicacao.command;

import br.com.michelon.softimob.aplicacao.editorInput.ClienteEditorInput;
import br.com.michelon.softimob.tela.editor.ClienteEditor;

public class CmdCliente extends GenericAbstractHandler {

	public CmdCliente() {
		this.editorInput = new ClienteEditorInput();
		this.id = ClienteEditor.ID;
	}
	
}
