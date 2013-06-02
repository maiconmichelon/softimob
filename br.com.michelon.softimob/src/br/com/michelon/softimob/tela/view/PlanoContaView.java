package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.PlanoContaEditorInput;
import br.com.michelon.softimob.aplicacao.service.PlanoContaService;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.editor.PlanoContaEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class PlanoContaView extends GenericView<PlanoConta>{

	private List<ColumnProperties> atributos;
	private PlanoContaService service = new PlanoContaService();
	
	public PlanoContaView(){
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Código", "codigo"));
		atributos.add(new ColumnProperties("Nome", "nome"));
		atributos.add(new ColumnProperties("Tipo", "tipo"));
	}
	
	@Override
	protected void excluir(List<PlanoConta> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTitleView() {
		return "Plano de contas";
	}

	@Override
	protected Image getImage() {
		return Images.PLANOCONTA_32.getImage();
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

}
