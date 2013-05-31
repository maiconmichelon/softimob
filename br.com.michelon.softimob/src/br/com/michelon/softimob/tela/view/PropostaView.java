package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.tela.editor.ImovelEditor;

public class PropostaView extends GenericView<Proposta>{

	private Map<String, String> atributos;
	
	public PropostaView() {
		super(false);
		
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Imóvel", "|8|imovel.numero");
		atributos.put("Data", "|8|data");
		atributos.put("Cliente", "|20|cliente");
		atributos.put("Funcionário", "|20|funcionario.nome");
		atributos.put("Descrição", "|40|descrição");
	}
	
	@Override
	protected void excluir(List<Proposta> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTitleView() {
		return "Propostas";
	}

	@Override
	protected Image getImage() {
		return Images.PROPOSTA_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected Object getElementToEdit(Proposta object) {
		return object.getImovel();
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
	protected List<Proposta> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
}
