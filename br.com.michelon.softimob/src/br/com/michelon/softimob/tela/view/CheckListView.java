package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import com.google.common.collect.Lists;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.service.CheckListService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.CheckList;
import br.com.michelon.softimob.tela.dialog.CheckListEditorDialog;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

public class CheckListView extends GenericView<CheckList>{

	public static final String ID = "br.com.michelon.softimob.tela.editor.CheckListView";
	
	private List<ColumnProperties> atributos = Lists.newArrayList();
	private CheckListService service = new CheckListService();
	
	public CheckListView() {
		super(true);
		
		atributos.add(new ColumnProperties("Nome", "nome", 100));
	}

	@Override
	protected String getTitleView() {
		return "Checklist";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.CHECKLIST_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(CheckList t) {
		return null;
	}

	@Override
	protected String getEditorId(CheckList t) {
		return null;
	}

	@Override
	protected List<CheckList> getInput() {
		return service.findAll();
	}

	@Override
	public void cadastrar() {
		alterar(new CheckList());
	}
	
	@Override
	protected void alterar(CheckList element) {
		CheckListEditorDialog dialog = new CheckListEditorDialog(ShellHelper.getActiveShell(), element);
		if(dialog.open() == IDialogConstants.OK_ID)
			atualizar();
	}
	
	@Override
	protected GenericService<CheckList> getService(Object obj) {
		return service;
	}
	
}
