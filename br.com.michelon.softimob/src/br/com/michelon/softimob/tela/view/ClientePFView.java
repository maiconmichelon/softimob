package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.ClienteEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PessoaFisicaService;
import br.com.michelon.softimob.modelo.PessoaFisica;
import br.com.michelon.softimob.tela.editor.ClientePFEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.TelefoneValueFormatter;

import com.google.common.collect.Lists;

public class ClientePFView extends GenericView<PessoaFisica>{
	
	private List<ColumnProperties> atributos;
	
	private PessoaFisicaService service = new PessoaFisicaService();
	
	public ClientePFView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Nome", "nome", 23));
		atributos.add(new ColumnProperties("CPF", "cpf",8 ));
		atributos.add(new ColumnProperties("RG", "rg", 8));
		atributos.add(new ColumnProperties("Data de Nascimento", "dataNascimento", 10, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Telefone", "telefone", 8, new TelefoneValueFormatter()));
		atributos.add(new ColumnProperties("Celular", "celular", 8, new TelefoneValueFormatter()));
		atributos.add(new ColumnProperties("E-mail", "email", 15));
		atributos.add(new ColumnProperties("Endereço", "endereco",20));
	}

	@Override
	protected String getTitleView() {
		return "Pessoas Físicas";
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
	protected GenericEditorInput<?> getIEditorInput(PessoaFisica t) {
		return new ClienteEditorInput();
	}

	@Override
	protected String getEditorId(PessoaFisica t) {
		return ClientePFEditor.ID;
	}

	@Override
	protected List<PessoaFisica> getInput() {
		return service.findAll();
	}
	
	@Override
	protected GenericService<PessoaFisica> getService(Object obj) {
		return service;
	}
	
}
