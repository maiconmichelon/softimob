package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.ComissaoService;
import br.com.michelon.softimob.modelo.Comissao;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class ComissaoView extends GenericView<Comissao>{

	private List<ColumnProperties> atributos;
	private ComissaoService service = new ComissaoService();
	
	public ComissaoView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Nome", "comissionado.nome", 20));
		atributos.add(new ColumnProperties("Valor", "valor", 10));
	}

	@Override
	protected void excluir(List<Comissao> objetos) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getTitleView() {
		return "Comissão";
	}

	@Override
	protected Image getImage() {
		return Images.COMISSAO_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Comissao t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getEditorId(Comissao t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Comissao> getInput() {
		return service.findAll();
	}

}
