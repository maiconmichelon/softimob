package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.tela.editor.ImovelEditor;
import br.com.michelon.softimob.tela.widget.imovelXViewer.ImovelXViewer;
import org.eclipse.swt.widgets.Label;

public class ImovelView extends GenericView<ImovelView>{

	private Map<String, String> atributos;
	
	public ImovelView() {
		atributos = Maps.newLinkedHashMap();
		
		atributos.put("Código", "codigo");
		atributos.put("Endereço", "endereco");
	}
	
	@Override
	protected void excluir(List<ImovelView> objetos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getName() {
		return "Imóveis";
	}

	@Override
	protected Image getImage() {
		return Images.IMOVEL_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new ImovelEditorInput();
	}

	@Override
	protected String getEditorId() {
		return ImovelEditor.ID;
	}

	@Override
	protected List<ImovelView> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ColumnViewer criarTabela(Composite composite) {
		composite.setLayout(new GridLayout(1, false));
		Composite cpTable = new Composite(composite, SWT.NONE);
		cpTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		composite.setLayout(new GridLayout(1, false));
		
		XViewer imovelXViewer = new ImovelXViewer(composite, SWT.BORDER);
		return imovelXViewer;
	}
	
}
