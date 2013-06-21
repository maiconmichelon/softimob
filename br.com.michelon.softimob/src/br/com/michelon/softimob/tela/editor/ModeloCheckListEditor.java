package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.CheckListService;
import br.com.michelon.softimob.modelo.ItemCheckList;
import br.com.michelon.softimob.modelo.CheckList;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class ModeloCheckListEditor extends GenericEditor<CheckList>{
	private DataBindingContext m_bindingContext;
	
	private WritableValue valueItem = WritableValue.withValueType(ItemCheckList.class);
	
	private CheckListService service = new CheckListService();
	
	private Text text;
	private Text text_1;
	private TableViewerBuilder tvb;
	
	
	public ModeloCheckListEditor() {
		super(CheckList.class);
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		Label lblModelo = new Label(parent, SWT.NONE);
		lblModelo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModelo.setText("Modelo");
		
		text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 231;
		text.setLayoutData(gd_text);
		
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(new GridLayout(3, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 2, 1));
		
		Composite composite = new Composite(group, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		criarTabela(composite);
		
		Label lblItem = new Label(group, SWT.NONE);
		lblItem.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblItem.setSize(55, 15);
		lblItem.setText("Item");
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(group, SWT.NONE);
		new Label(group, SWT.NONE);
		new Label(group, SWT.NONE);
		
		Button btnAdicionar = new Button(group, SWT.NONE);
		btnAdicionar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnAdicionar.setText("Adicionar");
		btnAdicionar.setImage(ImageRepository.ADD_16.getImage());
		
		m_bindingContext = initDataBindings();
	}

	private void criarTabela(Composite cp) {
		tvb = new TableViewerBuilder(cp);
		
		tvb.createColumn("Descrição").bindToProperty("descricao").build();
		tvb.createColumn("Tipo").bindToProperty("tipo").build();
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(CheckList.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueDescricaoObserveDetailValue = PojoProperties.value(ItemCheckList.class, "descricao", String.class).observeDetail(valueItem);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueDescricaoObserveDetailValue, null, null);
		//
		return bindingContext;
	}

	@Override
	public GenericService<CheckList> getService() {
		return service;
	}
}
