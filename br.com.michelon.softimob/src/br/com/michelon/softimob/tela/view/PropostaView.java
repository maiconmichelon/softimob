package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PropostaService;
import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.tela.editor.ImovelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;

import com.google.common.collect.Lists;

public class PropostaView extends GenericView<Proposta>{

	private List<ColumnProperties> atributos;
	private PropostaService service = new PropostaService();
	
	public PropostaView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Imóvel", "imovel", 8));
		atributos.add(new ColumnProperties("Data", "data", 8, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Realizador", "cliente.nome", 20));
		atributos.add(new ColumnProperties("Valor", "valor", 10));
		atributos.add(new ColumnProperties("Funcionário", "funcionario.nome", 20));
		atributos.add(new ColumnProperties("Descrição", "observacoes", 40));
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
		return ImageRepository.PROPOSTA_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Proposta t) {
		return new ImovelEditorInput();
	}

	@Override
	protected Object getModelOfEditorInput(Proposta element) {
		return element.getImovel();
	}
	
	@Override
	protected String getEditorId(Proposta t) {
		return ImovelEditor.ID;
	}

	@Override
	protected List<Proposta> getInput() {
		return service.findAll();
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
	@Override
	protected GenericService<Proposta> getService() {
		return service;
	}	
	
}
