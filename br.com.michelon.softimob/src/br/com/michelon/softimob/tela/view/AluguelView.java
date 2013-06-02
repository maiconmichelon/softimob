package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.AluguelEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.AluguelService;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.tela.editor.AluguelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class AluguelView extends GenericView<Aluguel>{

	private List<ColumnProperties> atributos;
	private AluguelService service = new AluguelService();
	
	public AluguelView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Imóvel", "imovel.numero", 7));
		atributos.add(new ColumnProperties("Locatário", "locatario.nome", 20));
		atributos.add(new ColumnProperties("Corretor", "|20|corretor.nome", 20));
		atributos.add(new ColumnProperties("Fiador", "|20|fiador.nome",20));
		atributos.add(new ColumnProperties("Valor", "|10|valor",10));
		atributos.add(new ColumnProperties("Data", "|10|data",10));
		atributos.add(new ColumnProperties("Duração", "|10|duracao",10));
		atributos.add(new ColumnProperties("Reajuste", "|10|reajuste", 10));
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
		return Images.ALUGUEL_32.getImage();
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

}
