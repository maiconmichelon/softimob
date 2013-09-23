package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.ClienteEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PessoaJuridicaService;
import br.com.michelon.softimob.modelo.PessoaJuridica;
import br.com.michelon.softimob.tela.editor.ClientePJEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.TelefoneValueFormatter;

import com.google.common.collect.Lists;

public class ClientePJView extends GenericView<PessoaJuridica>{
	
	private List<ColumnProperties> atributos;
	private PessoaJuridicaService service = new PessoaJuridicaService();
	
	public ClientePJView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Razão Social", "nome", 23));
		atributos.add(new ColumnProperties("CNPJ", "cnpj",8 ));
		atributos.add(new ColumnProperties("Insc. Estadual", "inscrisaoEstadual", 8));
		atributos.add(new ColumnProperties("Sócio Administrador", "socioProprietario.nome", 15));
		atributos.add(new ColumnProperties("Telefone", "telefone", 8, new TelefoneValueFormatter()));
		atributos.add(new ColumnProperties("Celular", "celular", 8, new TelefoneValueFormatter()));
		atributos.add(new ColumnProperties("E-mail", "email", 15));
		atributos.add(new ColumnProperties("Endereço", "endereco",20));
	}

	@Override
	protected String getTitleView() {
		return "Pessoa Jurídica";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.CLIENTE_32.getImage();
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
		return service.findAll();
	}
	
	@Override
	protected GenericService<PessoaJuridica> getService(Object obj) {
		return service;
	}
	
}
