package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.VendaEditorInput;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.tela.editor.VendaEditor;

import com.google.common.collect.Maps;

public class VendaView extends GenericView<Aluguel>{

	private Map<String, String> atributos;
	
	public VendaView() {
		super(false);
		
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Imóvel", "|10|imovel.numero");
		atributos.put("Data", "|15|data");
		atributos.put("Cliente", "|30|cliente.nome");
		atributos.put("Vendedor", "|30|funcionario.nome");
		atributos.put("Valor", "|15|valor");
	}
	
	@Override
	protected void excluir(List<Aluguel> objetos) {
	}

	@Override
	protected String getTitleView() {
		return "Vendas";
	}

	@Override
	protected Image getImage() {
		return Images.VENDA_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new VendaEditorInput();
	}

	@Override
	protected String getEditorId() {
		return VendaEditor.ID;
	}

	@Override
	protected List<Aluguel> getInput() {
		return null;
	}

}
