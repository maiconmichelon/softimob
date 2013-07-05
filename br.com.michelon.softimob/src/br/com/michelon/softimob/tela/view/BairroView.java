package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.BairroEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.BairroService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.tela.editor.BairroEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class BairroView extends GenericView<Bairro>{

	private List<ColumnProperties> atributos;
	private BairroService service = new BairroService();
	
	public BairroView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Bairro", "nome", 20));
		atributos.add(new ColumnProperties("Cidade", "cidade.nome", 20));
		atributos.add(new ColumnProperties("UF", "cidade.estado.nome", 60));
	}
	
	@Override
	protected void excluir(List<Bairro> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTitleView() {
		return "Bairro";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.ENDERECO.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Bairro t) {
		return new BairroEditorInput();
	}

	@Override
	protected String getEditorId(Bairro t) {
		return BairroEditor.ID;
	}

	@Override
	protected List<Bairro> getInput() {
		return service.findAll();
	}

	@Override
	protected GenericService<Bairro> getService() {
		return service;
	}
	
}
