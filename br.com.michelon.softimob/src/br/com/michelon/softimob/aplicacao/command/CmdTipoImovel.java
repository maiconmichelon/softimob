package br.com.michelon.softimob.aplicacao.command;

import br.com.michelon.softimob.aplicacao.editorInput.TipoImovelEditorInput;
import br.com.michelon.softimob.tela.editor.TipoImovelEditor;

public class CmdTipoImovel extends GenericAbstractHandler {

	public CmdTipoImovel() {
		this.editorInput = new TipoImovelEditorInput();
		this.id = TipoImovelEditor.ID;
	}
	
}
