package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.aplicacao.service.ChaveService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Chave;
import br.com.michelon.softimob.tela.editor.ImovelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class ChaveView extends GenericView<Chave>{

	private List<ColumnProperties> atributos;
	private ChaveService service = new ChaveService();
	
	public ChaveView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Número", "numero", 10));
		atributos.add(new ColumnProperties("Localização", "localizacao", 30));
		atributos.add(new ColumnProperties("Imóvel", "imovel", 90));
	}
	
	@Override
	protected void excluir(List<Chave> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTitleView() {
		return "Chaves";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.CHAVE_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Chave t) {
		return new ImovelEditorInput();
	}

	@Override
	protected Object getModelOfEditorInput(Chave element) {
		return element.getImovel();
	}
	
	@Override
	protected String getEditorId(Chave t) {
		return ImovelEditor.ID;
	}

	@Override
	protected List<Chave> getInput() {
		return service.findAll();
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
	@Override
	protected GenericService<Chave> getService() {
		return service;
	}
	
}
