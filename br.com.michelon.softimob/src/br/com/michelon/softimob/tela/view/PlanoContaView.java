package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.PlanoContaEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PlanoContaService;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.editor.PlanoContaEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class PlanoContaView extends GenericView<PlanoConta>{

	private List<ColumnProperties> atributos;
	private PlanoContaService service = new PlanoContaService();
	
	public PlanoContaView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Código", "codigo", 15));
		atributos.add(new ColumnProperties("Nome", "nome", 30));
		atributos.add(new ColumnProperties("Tipo", "tipoExtenso", 55));
	}
	
	@Override
	protected String getTitleView() {
		return "Plano de contas";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.PLANO_CONTA_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(PlanoConta t) {
		return new PlanoContaEditorInput();
	}

	@Override
	protected String getEditorId(PlanoConta t) {
		return PlanoContaEditor.ID;
	}

	@Override
	protected List<PlanoConta> getInput() {
		return service.findAll();
	}

	@Override
	protected GenericService<PlanoConta> getService(Object obj) {
		return service;
	}	
	
}
