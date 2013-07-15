package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.RuaEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.RuaService;
import br.com.michelon.softimob.modelo.Rua;
import br.com.michelon.softimob.tela.editor.RuaEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class RuaView extends GenericView<Rua>{

	private List<ColumnProperties> atributos;
	private RuaService service = new RuaService();
	
	public RuaView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Rua", "nome", 17));
		atributos.add(new ColumnProperties("Bairro", "bairro.nome"));
		atributos.add(new ColumnProperties("Cidade", "bairro.cidade.nome"));
		atributos.add(new ColumnProperties("UF", "bairro.cidade.estado.nome", 50));
	}
	
	@Override
	protected String getTitleView() {
		return "Ruas";
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
	protected GenericEditorInput<?> getIEditorInput(Rua t) {
		return new RuaEditorInput();
	}

	@Override
	protected String getEditorId(Rua t) {
		return RuaEditor.ID;
	}

	@Override
	protected List<Rua> getInput() {
		return service.findAll();
	}

	@Override
	protected GenericService<Rua> getService(Object obj) {
		return service;
	}	
	
}
