package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.CidadeEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.tela.editor.CidadeEditor;

public class CidadeView extends GenericView<Cidade>{

	private Map<String, String> atributos;
	
	public CidadeView(){
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Nome", "|20|nome");
		atributos.put("UF", "|80|uf");
	}
	
	@Override
	protected void excluir(List<Cidade> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Cidades";
	}

	@Override
	protected Image getImage() {
		return Images.ENDERECO.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new CidadeEditorInput();
	}

	@Override
	protected String getEditorId() {
		return CidadeEditor.ID;
	}

	@Override
	protected List<Cidade> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
