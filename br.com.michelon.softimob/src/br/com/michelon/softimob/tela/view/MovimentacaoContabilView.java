package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.MovimentacaoContabilEditorInput;
import br.com.michelon.softimob.tela.editor.MovimentacaoContabilEditor;

public class MovimentacaoContabilView extends GenericView<MovimentacaoContabilView>{

	private Map<String, String> atributos;
	
	public MovimentacaoContabilView(){
		super(false);
		
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Código", "codigo");
		atributos.put("Data de Lançamento", "dataLancamento");
		atributos.put("Valor", "valor");
	}
	
	@Override
	protected void excluir(List<MovimentacaoContabilView> objetos) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getTitleView() {
		return "Movimentações Contábeis";
	}

	@Override
	protected Image getImage() {
		return Images.MOVIMENTACAO_CONTABIL_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new MovimentacaoContabilEditorInput();
	}

	@Override
	protected String getEditorId() {
		return MovimentacaoContabilEditor.ID;
	}

	@Override
	protected List<MovimentacaoContabilView> getInput() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
