package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.ClienteEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.ClienteService;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.tela.editor.ClienteEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class ClienteView extends GenericView<Cliente>{
	
	private List<ColumnProperties> atributos;
	private ClienteService service = new ClienteService();
	
	public ClienteView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Nome", "nome", 23));
		atributos.add(new ColumnProperties("CPF", "cpf",8 ));
		atributos.add(new ColumnProperties("RG", "rg", 8));
		atributos.add(new ColumnProperties("Data de Nascimento", "dataNascimento", 10));
		atributos.add(new ColumnProperties("Telefone", "telefone", 8));
		atributos.add(new ColumnProperties("Celular", "celular", 8));
		atributos.add(new ColumnProperties("E-mail", "email", 15));
		atributos.add(new ColumnProperties("Endereço", "Oendereco",20));
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
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Cliente t) {
		return new ClienteEditorInput();
	}

	@Override
	protected String getEditorId(Cliente t) {
		return ClienteEditor.ID;
	}

	@Override
	protected List<Cliente> getInput() {
		return service.findAll();
	}
}
