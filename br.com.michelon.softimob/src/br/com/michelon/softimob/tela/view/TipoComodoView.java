package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.ComodoEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.TipoComodoService;
import br.com.michelon.softimob.modelo.TipoComodo;
import br.com.michelon.softimob.tela.editor.TipoComodoEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class TipoComodoView extends GenericView<TipoComodo>{

	private List<ColumnProperties> atributos;
	private TipoComodoService service = new TipoComodoService();
	
	public TipoComodoView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Nome", "nome", 10));
	}
	
	@Override
	protected void excluir(List<TipoComodo> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTitleView() {
		return "Tipos de Cômodo";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.COMODO32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(TipoComodo t) {
		return new ComodoEditorInput();
	}

	@Override
	protected String getEditorId(TipoComodo t) {
		return TipoComodoEditor.ID;
	}

	@Override
	protected List<TipoComodo> getInput() {
		return service.findAll();
	}

	@Override
	protected GenericService<TipoComodo> getService() {
		return service;
	}	
	
}
