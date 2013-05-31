package br.com.michelon.softimob.aplicacao.command;

import br.com.michelon.softimob.aplicacao.editorInput.ParametrosEmpresaEditorInput;
import br.com.michelon.softimob.tela.editor.ParametrosEmpresaEditor;

public class CmdParametroEmpresa extends GenericAbstractHandler{
	
	public CmdParametroEmpresa() {
		editorInput = new ParametrosEmpresaEditorInput();
		id = ParametrosEmpresaEditor.ID;
	}

}
