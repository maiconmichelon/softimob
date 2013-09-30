package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.IndiceEditorInput;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.IndiceService;
import br.com.michelon.softimob.modelo.Indice;
import br.com.michelon.softimob.tela.dialog.IndiceEditorDialog;
import br.com.michelon.softimob.tela.editor.IndiceEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class IndiceView extends GenericView<Indice>{

	private List<ColumnProperties> atributos;
	private IndiceService service = new IndiceService();
	
	public IndiceView() {
		super(true);
		
		atributos = Lists.newArrayList();
		atributos.add(new ColumnProperties("Nome", "nome"));
	}

	@Override
	protected String getTitleView() {
		return "Indices";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.INDICE_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Indice t) {
		return new IndiceEditorInput();
	}

	@Override
	protected String getEditorId(Indice t) {
		return IndiceEditor.ID;
	}

	@Override
	protected List<Indice> getInput() {
		return service.findAll();
	}

	@Override
	public void cadastrar() {
		alterar(new Indice());
	}
	
	@Override
	protected void alterar(Indice element) {
		IndiceEditorDialog dialog = new IndiceEditorDialog(ShellHelper.getActiveShell(), element);
		dialog.open();
		atualizar();
	}

	@Override
	protected GenericService<Indice> getService(Object obj) {
		return service;
	}	
	
}
