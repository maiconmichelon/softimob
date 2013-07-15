package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.PlacaEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PlacaService;
import br.com.michelon.softimob.modelo.Placa;
import br.com.michelon.softimob.tela.editor.PlacaEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class PlacaView extends GenericView<Placa>{

	private List<ColumnProperties> atributos;
	private PlacaService service = new PlacaService();
	
	public PlacaView() {
		super(false);
		
		atributos = Lists.newArrayList();
	
		atributos.add(new ColumnProperties("Número", "numero"));
		atributos.add(new ColumnProperties("Corretor", "funcionario.nome", 20));
		atributos.add(new ColumnProperties("Localização", "imovel", 60));
	}
	
	@Override
	protected String getTitleView() {
		return "Placas";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.PLACA_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Placa t) {
		return new PlacaEditorInput();
	}

	@Override
	protected String getEditorId(Placa t) {
		return PlacaEditor.ID;
	}

	@Override
	protected List<Placa> getInput() {
		return service.findAll();
	}

	@Override
	protected GenericService<Placa> getService(Object obj) {
		return service;
	}	
	
}
