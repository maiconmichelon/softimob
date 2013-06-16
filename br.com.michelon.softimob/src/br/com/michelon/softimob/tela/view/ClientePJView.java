package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.ClienteEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.ClienteService;
import br.com.michelon.softimob.modelo.PessoaJuridica;
import br.com.michelon.softimob.tela.editor.ClientePJEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class ClientePJView extends GenericView<PessoaJuridica>{
	
	private List<ColumnProperties> atributos;
	private ClienteService service = new ClienteService();
	
	public ClientePJView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Razão Social", "nome", 23));
		atributos.add(new ColumnProperties("CNPJ", "cnpj",8 ));
		atributos.add(new ColumnProperties("Insc. Estadual", "inscricaoEstadual", 8));
		atributos.add(new ColumnProperties("Sócio Administrador", "socioAdministrador", 15));
		atributos.add(new ColumnProperties("Telefone", "telefone", 8));
		atributos.add(new ColumnProperties("Celular", "celular", 8));
		atributos.add(new ColumnProperties("E-mail", "email", 15));
		atributos.add(new ColumnProperties("Endereço", "endereco",20));
	}

	@Override
	protected void excluir(List<PessoaJuridica> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTitleView() {
		return "Pessoa Jurídica";
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
	protected GenericEditorInput<?> getIEditorInput(PessoaJuridica t) {
		return new ClienteEditorInput();
	}

	@Override
	protected String getEditorId(PessoaJuridica t) {
		return ClientePJEditor.ID;
	}

	@Override
	protected List<PessoaJuridica> getInput() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
