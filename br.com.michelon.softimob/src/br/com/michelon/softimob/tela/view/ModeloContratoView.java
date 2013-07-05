package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ModeloContratoEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.ModeloContratoService;
import br.com.michelon.softimob.modelo.ModeloContrato;
import br.com.michelon.softimob.tela.editor.ModeloContratoEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class ModeloContratoView extends GenericView<ModeloContrato>{

	private List<ColumnProperties> atributos;
	private ModeloContratoService service = new ModeloContratoService();
	
	public ModeloContratoView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Nome", "nome", 10));
	}
	
	@Override
	protected void excluir(List<ModeloContrato> objetos) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getTitleView() {
		return "Modelos de contrato";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.CONTRATO_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(ModeloContrato t) {
		return new ModeloContratoEditorInput();
	}

	@Override
	protected String getEditorId(ModeloContrato t) {
		return ModeloContratoEditor.ID;
	}

	@Override
	protected List<ModeloContrato> getInput() {
		return service.findAll();
	}

	@Override
	protected GenericService<ModeloContrato> getService() {
		return service;
	}	
	
}
