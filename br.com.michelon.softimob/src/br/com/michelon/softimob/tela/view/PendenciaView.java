package br.com.michelon.softimob.tela.view;

import java.util.Calendar;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.PendenciaService;
import br.com.michelon.softimob.modelo.Pendencia;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import br.com.michelon.softimob.tela.widget.DateStringValueFormatter;
import br.com.michelon.softimob.tela.widget.DateTextField;

import com.google.common.collect.Lists;

public class PendenciaView extends GenericView<Pendencia>{

	private List<ColumnProperties> atributos;
	private PendenciaService service = new PendenciaService();
	private DateTextField dateTextField;
	
	public PendenciaView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Descrição", "descricao",30));
		atributos.add(new ColumnProperties("Data de Origem", "dataGeracao", 15, new DateStringValueFormatter()));
		atributos.add(new ColumnProperties("Data de Vencimento", "dataVencimento", 15, new DateStringValueFormatter()));
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
		return (GenericEditorInput<?>) t.getEditorInput();
	}

	@Override
	protected String getEditorId(Pendencia t) {
		return t.getIdEditor();
	}

	@Override
	protected List<Pendencia> getInput() {
		return service.findPendencias(dateTextField.getValue());
	}

	@Override
	@SuppressWarnings("unchecked")
	protected GenericService<Pendencia> getService(Object obj) {
		return (GenericService<Pendencia>) ((Pendencia)obj).getService();
	}
	
	@Override
	protected List<Action> createMoreActions() {
		return null;
	}

	@Override
	public void createComponentsCpTop(Composite parent, FormToolkit frm) {
		Label lblDataDeVencimento = new Label(parent, SWT.NONE);
		frm.adapt(lblDataDeVencimento, true, true);
		lblDataDeVencimento.setText("Vencimento");
		
		dateTextField = new DateTextField(parent);
		dateTextField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		frm.adapt(dateTextField);
		frm.paintBordersFor(dateTextField);
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		dateTextField.setValue(c.getTime());
		
		Button btnBuscar = new Button(parent, SWT.NONE);
		frm.adapt(btnBuscar, true, true);
		btnBuscar.setText("Buscar");
		btnBuscar.setImage(ImageRepository.SEARCH_16.getImage());
		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				atualizar();
			}
		});
	}
	
}
