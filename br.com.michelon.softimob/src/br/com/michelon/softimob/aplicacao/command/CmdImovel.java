package br.com.michelon.softimob.aplicacao.command;

import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.tela.editor.ImovelEditor;

public class CmdImovel extends GenericAbstractHandler{

	public CmdImovel() {
		editorInput = new ImovelEditorInput();
		id = ImovelEditor.ID;
	}
	
}
