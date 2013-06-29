package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.AluguelEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.VistoriaService;
import br.com.michelon.softimob.modelo.Vistoria;
import br.com.michelon.softimob.tela.editor.AluguelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class VistoriaView extends GenericView<Vistoria>{

	private List<ColumnProperties> atributos;
	private VistoriaService service = new VistoriaService();
	
	public VistoriaView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Data", "data"));
		atributos.add(new ColumnProperties("Funcionario", "funcionario.nome"));
		atributos.add(new ColumnProperties("Locação", "aluguel", 10));
		atributos.add(new ColumnProperties("Observações", "observacoes"));		
	}
	
	@Override
	protected void excluir(List<Vistoria> objetos) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getTitleView() {
		return "Vistorias";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.VISTORIA_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Vistoria t) {
		return new AluguelEditorInput();
	}

	@Override
	protected Object getModelOfEditorInput(Vistoria element) {
		return element.getAluguel();
	}
	
	@Override
	protected String getEditorId(Vistoria t) {
		return AluguelEditor.ID;
	}

	@Override
	protected List<Vistoria> getInput() {
		return service.findAll();
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
}
