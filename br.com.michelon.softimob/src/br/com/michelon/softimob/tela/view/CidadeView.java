package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.CidadeEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.CidadeService;
import br.com.michelon.softimob.modelo.Cidade;
import br.com.michelon.softimob.tela.editor.CidadeEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class CidadeView extends GenericView<Cidade>{

	private List<ColumnProperties> atributos;
	private CidadeService service = new CidadeService();
	
	public CidadeView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Nome", "nome", 20));
		atributos.add(new ColumnProperties("UF", "uf", 80));
	}
	
	@Override
	protected void excluir(List<Cidade> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTitleView() {
		return "Cidades";
	}

	@Override
	protected Image getImage() {
		return Images.ENDERECO.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Cidade t) {
		return new CidadeEditorInput();
	}

	@Override
	protected String getEditorId(Cidade t) {
		return CidadeEditor.ID;
	}

	@Override
	protected List<Cidade> getInput() {
		return service.findAll();
	}

}
