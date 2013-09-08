package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.modelo.CheckList;
import br.com.michelon.softimob.modelo.Item;
import br.com.michelon.softimob.modelo.ItemCheckList;
import br.com.michelon.softimob.persistencia.ModeloCheckListDAO;

import com.google.common.collect.Lists;

import de.ralfebert.rcputils.properties.IValue;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class CheckListService extends GenericService<CheckList>{

	public CheckListService() {
		super(ModeloCheckListDAO.class);
	}

	public TableViewer criarTabela(Composite composite) {
		final TableViewerBuilder tvbCheckList = new TableViewerBuilder(composite);
		
		TableViewerColumn tvcFinalizado = tvbCheckList.createColumn("Finalizado").setPixelWidth(70).setCustomLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return null;
			}
			
			@Override
			public Image getImage(Object element) {
				return ((ItemCheckList)element).getFinalizado() ? ImageRepository.CHECKED.getImage() : ImageRepository.UNCHECKED.getImage();
			}
		}).bindToValue(new IValue() {
			
			@Override
			public void setValue(Object element, Object value) {
				((ItemCheckList)element).setFinalizado((Boolean)value);
				tvbCheckList.getTableViewer().refresh();
			}
			
			@Override
			public Object getValue(Object element) {
				return ((ItemCheckList)element).getFinalizado();
			}
		}).build();
		tvcFinalizado.setEditingSupport(new EditingSupport(tvbCheckList.getTableViewer()) {
			
			@Override
			protected void setValue(Object element, Object value) {
				((ItemCheckList)element).setFinalizado((Boolean)value);
				getViewer().refresh();
			}
			
			@Override
			protected Object getValue(Object element) {
				return ((ItemCheckList)element).getFinalizado();
			}
			
			@Override
			protected CellEditor getCellEditor(Object element) {
				return new CheckboxCellEditor(null, SWT.CHECK | SWT.READ_ONLY);
			}
			
			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
		});
		tvbCheckList.createColumn("Item").bindToProperty("nome").setPercentWidth(40).build();
		tvbCheckList.createColumn("Observações").bindToProperty("valor").setPercentWidth(50).makeEditable().build();
		//É TUDO NO BINDING SA BIXERA XD NAO PRECISA MAIS DISSO .. s
//		tvbCheckList.setInput(input);
		
		return tvbCheckList.getTableViewer();
	}
	
	public List<ItemCheckList> getNewItens(CheckList chk){
		List<ItemCheckList> itens = Lists.newArrayList();
		if(chk == null)
			return itens;
		
		for (Item item : chk.getItens()) {
			itens.add(new ItemCheckList(item.getNome(), item.getObrigatorio()));
		}
		
		return itens;
	}
	
}
