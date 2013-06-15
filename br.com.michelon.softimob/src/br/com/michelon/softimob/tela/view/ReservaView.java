package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.aplicacao.service.ReservaService;
import br.com.michelon.softimob.modelo.Reserva;
import br.com.michelon.softimob.tela.editor.ImovelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class ReservaView extends GenericView<Reserva>{

	private List<ColumnProperties> atributos;
	private ReservaService service = new ReservaService();
	
	public ReservaView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Data da Reserva", "data"));
		atributos.add(new ColumnProperties("Data de Vencimento", "dataVencimento"));
		atributos.add(new ColumnProperties("Cliente", "cliente.nome"));
		atributos.add(new ColumnProperties("Valor", "valor"));
		atributos.add(new ColumnProperties("Funcionário", "funcionario"));
		atributos.add(new ColumnProperties("Descrição", "descricao"));
	}

	@Override
	protected void excluir(List<Reserva> objetos) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getTitleView() {
		return "Reservas";
	}

	@Override
	protected Image getImage() {
		return Images.RESERVA32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Reserva t) {
		return new ImovelEditorInput();
	}

	@Override
	protected Object getModelOfEditorInput(Reserva element) {
		return element.getImovel();
	}
	
	@Override
	protected String getEditorId(Reserva t) {
		return ImovelEditor.ID;
	}

	@Override
	protected List<Reserva> getInput() {
		return service.findAll();
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
}
