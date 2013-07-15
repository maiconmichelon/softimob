package br.com.michelon.softimob.tela.view;

import java.util.Date;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PendenciaService;
import br.com.michelon.softimob.modelo.Pendencia;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class PendenciaView extends GenericView<Pendencia>{

	private List<ColumnProperties> atributos;
	private PendenciaService service = new PendenciaService();
	
	public PendenciaView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Descrição", "descricao",30));
		atributos.add(new ColumnProperties("Data de Origem", "dataOrigem", 15));
		atributos.add(new ColumnProperties("Data de Vencimento", "dataVencimento",15));
		atributos.add(new ColumnProperties("Valor", "valor",10));
		
	}
	
	@Override
	protected void createMenuItens(Menu menu) {
	}
	
	@Override
	protected String getTitleView() {
		return "Pendências";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.PENDENCIA_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Pendencia t) {
		return null;
	}

	@Override
	protected String getEditorId(Pendencia t) {
		return null;
	}

	@Override
	protected List<Pendencia> getInput() {
		return service.findPendencias(new Date());
	}

	@Override
	protected GenericService<Pendencia> getService(Object obj) {
		return null;
	}
	
	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
}
