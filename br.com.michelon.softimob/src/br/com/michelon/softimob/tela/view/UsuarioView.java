package br.com.michelon.softimob.tela.view;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.wb.swt.ImageRepository;

import com.google.common.collect.Lists;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.UsuarioEditorInput;
import br.com.michelon.softimob.aplicacao.helper.LoginHelper;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.UsuarioService;
import br.com.michelon.softimob.modelo.Usuario;
import br.com.michelon.softimob.tela.editor.UsuarioEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

public class UsuarioView extends GenericView<Usuario>{

	public UsuarioView() {
		super(true);
	}

	private UsuarioService service = new UsuarioService();

	@Override
	protected String getTitleView() {
		return "Usuário";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.USER_32.getImage();
	}

	@Override
	protected void createMenuItens(Menu menu) {
		if(LoginHelper.isAdminLogado())
			super.createMenuItens(menu);
	}
	
	@Override
	protected List<Action> createMoreActions() {
		if(LoginHelper.isAdminLogado())
			return super.createMoreActions();
		return Lists.newArrayList();
	}
	
	@Override
	protected IDoubleClickListener getDoubleClickListener() {
		if(LoginHelper.isAdminLogado())
			return super.getDoubleClickListener();
		return null;
	}
	
	@Override
	public List<ColumnProperties> getColumns() {
		return Arrays.asList(new ColumnProperties("Login", "login", 600));
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Usuario t) {
		return new UsuarioEditorInput();
	}

	@Override
	protected String getEditorId(Usuario t) {
		return UsuarioEditor.ID;
	}

	@Override
	protected List<Usuario> getInput() {
		return service.findAll();
	}

	@Override
	protected GenericService<Usuario> getService(Object obj) {
		return service;
	}

}
