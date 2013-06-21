package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.VendaEditorInput;
import br.com.michelon.softimob.aplicacao.service.VendaService;
import br.com.michelon.softimob.modelo.Venda;
import br.com.michelon.softimob.tela.editor.VendaEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class VendaView extends GenericView<Venda>{

	private List<ColumnProperties> atributos;
	private VendaService service = new VendaService();
	
	public VendaView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Imóvel", "imovel.numero", 10));
		atributos.add(new ColumnProperties("Data", "data", 15));
		atributos.add(new ColumnProperties("Cliente", "cliente.nome", 30));
		atributos.add(new ColumnProperties("Vendedor", "funcionario.nome", 30));
		atributos.add(new ColumnProperties("Valor", "valor", 15));
	}
	
	@Override
	protected void excluir(List<Venda> objetos) {
	}

	@Override
	protected String getTitleView() {
		return "Vendas";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.VENDA_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Venda t) {
		return new VendaEditorInput();
	}

	@Override
	protected String getEditorId(Venda t) {
		return VendaEditor.ID;
	}

	@Override
	protected List<Venda> getInput() {
		return service.findAll();
	}

}
