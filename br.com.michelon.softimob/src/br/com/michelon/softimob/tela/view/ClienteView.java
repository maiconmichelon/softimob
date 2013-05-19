package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.ClienteEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.persistencia.ClienteDAO;
import br.com.michelon.softimob.tela.editor.ClienteEditor;

import com.google.common.collect.Maps;

public class ClienteView extends GenericView<Cliente>{
	
	private Map<String, String> atributos;
	private ClienteDAO dao = new ClienteDAO();
	
	public ClienteView(){
		super(true);
		
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Nome", "|23|nome");
		atributos.put("CPF", "|8|cpf");
		atributos.put("RG", "|8|rg");
		atributos.put("Data de Nascimento", "|10|dataNascimento");
		atributos.put("Telefone", "|8|telefone");
		atributos.put("Celular", "|8|celular");
		atributos.put("E-mail", "|15|email");
		atributos.put("Endereço", "|20|endereco");
	}
	
	@Override
	protected void excluir(List<Cliente> objetos) {
	}

	@Override
	protected String getTitleView() {
		return "Clientes";
	}

	@Override
	protected Image getImage() {
		return Images.CLIENTE_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new ClienteEditorInput();
	}

	@Override
	protected String getEditorId() {
		return ClienteEditor.ID;
	}

	@Override
	protected List<Cliente> getInput() {
		return dao.findAll();
	}
}
