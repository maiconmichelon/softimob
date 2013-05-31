package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.modelo.Pendencia;

public class PendenciaView extends GenericView<Pendencia>{

	private Map<String, String> atributos;
	
	public PendenciaView() {
		super(false);
		
		atributos = Maps.newHashMap();
		
		atributos.put("Descrição", "|30|descricao");
		atributos.put("Data de Origem", "|15|dataOrigem");
		atributos.put("Data de Vencimento", "|15|dataVencimento");
		atributos.put("Valor", "|10|valor");
		
	}
	
	@Override
	protected void setMenuItems(Menu menu) {
	}
	
	@Override
	protected void excluir(List<Pendencia> objetos) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getTitleView() {
		return "Pendências";
	}

	@Override
	protected Image getImage() {
		return Images.SEARCH_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return null;
	}

	@Override
	protected String getEditorId() {
		return null;
	}

	@Override
	protected List<Pendencia> getInput() {
		return null;
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
}
