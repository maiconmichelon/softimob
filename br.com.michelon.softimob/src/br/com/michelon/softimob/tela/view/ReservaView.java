package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.modelo.Reserva;
import br.com.michelon.softimob.tela.editor.ImovelEditor;

import com.google.common.collect.Maps;

public class ReservaView extends GenericView<Reserva>{

	private Map<String, String> atributos;

	public ReservaView() {
		super(false);
		
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Data da Reserva", "data");
		atributos.put("Data de Vencimento", "dataVencimento");
		atributos.put("Cliente", "cliente.nome");
		atributos.put("Valor", "valor");
		atributos.put("Funcionário", "funcionario");
		atributos.put("Descrição", "descricao");
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new ImovelEditorInput();
	}

	@Override
	protected String getEditorId() {
		return ImovelEditor.ID;
	}

	@Override
	protected List<Reserva> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getElementToEdit(Reserva object) {
		return ((Reserva)object).getImovel();
	}
	
	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
}
