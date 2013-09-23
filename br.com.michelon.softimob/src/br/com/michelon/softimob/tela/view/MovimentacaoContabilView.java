package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.MovimentacaoContabilEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.MovimentacaoContabilService;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.tela.editor.MovimentacaoContabilEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.movimentacaoXViewer.MovimentacaoGenericXViewer;

import com.google.common.collect.Lists;

public class MovimentacaoContabilView extends GenericView<MovimentacaoContabil>{

	private List<ColumnProperties> atributos;
	private MovimentacaoContabilService service = new MovimentacaoContabilService();
	
	public MovimentacaoContabilView(){
		super(false, MovimentacaoContabil.class);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Código", "id", 10));
		atributos.add(new ColumnProperties("Data de Lançamento", "data", 10, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Valor", "valor", 10));
		atributos.add(new ColumnProperties("Data", "data", 10));
		atributos.add(new ColumnProperties("Histórico", "data", 10));
	}
	
	@Override
	protected ColumnViewer createTable(Composite composite) {
		return MovimentacaoGenericXViewer.createXviewer(composite);
	}
	
	@Override
	protected String getTitleView() {
		return "Movimentações Contábeis";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.MOVIMENTACAO_CONTABIL_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(MovimentacaoContabil t) {
		return new MovimentacaoContabilEditorInput();
	}

	@Override
	protected String getEditorId(MovimentacaoContabil t) {
		return MovimentacaoContabilEditor.ID;
	}

	@Override
	protected List<MovimentacaoContabil> getInput() {
		return service.findAll();
	}
	
	@Override
	protected GenericService<MovimentacaoContabil> getService(Object obj) {
		return service;
	}	

}
