package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.ClienteEditorInput;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.persistencia.ClienteDAO;
import br.com.michelon.softimob.tela.editor.ClienteEditor;

import com.google.common.collect.Maps;

public class ClienteView extends GenericView<Cliente>{
	
	private Map<String, String> atributos;
	private ClienteDAO dao = new ClienteDAO();
	
	public ClienteView(){
		atributos = Maps.newHashMap();
		
		atributos.put("Nome", "nome");
		atributos.put("CPF", "cpf");
		atributos.put("RG", "rg");
		atributos.put("Telefone", "telefone");
		atributos.put("Celular", "celular");
		atributos.put("E-mail", "email");
		atributos.put("Endereço", "endereco");
	}
	
	@Override
	protected void excluir(List<Cliente> objetos) {
	}

	@Override
	protected String getName() {
		return "Clientes";
	}

	@Override
	protected Image getImage() {
		return Images.CLIENTE_32.getPluginImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected IEditorInput getIEditorInput() {
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
