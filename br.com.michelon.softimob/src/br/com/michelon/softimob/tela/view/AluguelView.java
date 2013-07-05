package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.AluguelEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.AluguelService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.tela.editor.AluguelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;

import com.google.common.collect.Lists;

public class AluguelView extends GenericView<Aluguel>{

	private List<ColumnProperties> atributos;
	private AluguelService service = new AluguelService();
	
	public AluguelView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Imóvel", "contrato", 7));
		atributos.add(new ColumnProperties("Locatário", "cliente.nome", 20));
		atributos.add(new ColumnProperties("Corretor", "funcionario.nome", 20));
		atributos.add(new ColumnProperties("Fiador", "fiador.nome",20));
		atributos.add(new ColumnProperties("Valor", "valor", 10));
		atributos.add(new ColumnProperties("Data", "dataAssinaturaContrato", 10, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Duração", "duracao",10));
		atributos.add(new ColumnProperties("Reajuste", "reajuste", 10));
	}
	
	@Override
	protected void excluir(List<Aluguel> objetos) {
	}

	@Override
	protected String getTitleView() {
		return "Aluguéis";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.ALUGUEL_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Aluguel t) {
		return new AluguelEditorInput();
	}

	@Override
	protected String getEditorId(Aluguel t) {
		return AluguelEditor.ID;
	}

	@Override
	protected List<Aluguel> getInput() {
		return service.findAll();
	}

	@Override
	protected GenericService<Aluguel> getService() {
		return service;
	}
	
}
