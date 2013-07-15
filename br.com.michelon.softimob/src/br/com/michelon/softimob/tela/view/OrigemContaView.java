package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.OrigemContaEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.OrigemContaService;
import br.com.michelon.softimob.modelo.OrigemConta;
import br.com.michelon.softimob.tela.editor.OrigemContaEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class OrigemContaView extends GenericView<OrigemConta>{

	private List<ColumnProperties> atributos;
	private OrigemContaService service = new OrigemContaService();
	
	
	public OrigemContaView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Nome", "nome", 10));
		atributos.add(new ColumnProperties("Conta", "conta.nome", 10));
		atributos.add(new ColumnProperties("Contra-partida", "contaContraPartida.nome", 10));
	}
	
	@Override
	protected String getTitleView() {
		return "Origens das contas";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.ORIGEM_CONTA_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(OrigemConta t) {
		return new OrigemContaEditorInput();
	}

	@Override
	protected String getEditorId(OrigemConta t) {
		return OrigemContaEditor.ID;
	}

	@Override
	protected List<OrigemConta> getInput() {
		return service.findAll();
	}

	@Override
	protected GenericService<OrigemConta> getService(Object obj) {
		return service;
	}	
	
}
