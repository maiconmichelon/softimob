package br.com.michelon.softimob.aplicacao.command;

import br.com.michelon.softimob.aplicacao.editorInput.PlacaEditorInput;
import br.com.michelon.softimob.tela.editor.PlacaEditor;

public class CmdPlaca extends GenericAbstractHandler {

	public CmdPlaca() {
		this.editorInput = new PlacaEditorInput();
		this.id = PlacaEditor.ID;
	}
	
}
