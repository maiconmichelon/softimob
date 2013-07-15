package br.com.michelon.softimob.aplicacao.helper;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class WidgetHelper {

	private static Logger log = Logger.getLogger(WidgetHelper.class);
	
	public static TableViewerBuilder createTable(Composite composite, List<ColumnProperties> attributes){
		Composite cpTable = new Composite(composite, SWT.NONE);
		cpTable.setLayout(new FillLayout());
//		cpTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		final TableViewerBuilder tvb = new TableViewerBuilder(cpTable);
		
		for (ColumnProperties columnProperties : attributes) {
			ColumnBuilder cb = tvb.createColumn(columnProperties.getLabel()).bindToProperty(columnProperties.getAtributo());
			
			if(columnProperties.getTamanho() != null)
				cb.setPercentWidth(columnProperties.getTamanho());
			if(columnProperties.getFormatter() != null)
				cb.format(columnProperties.getFormatter());
			
			cb.build();
		}
		
		return tvb;
	}

	public static MenuItem createMenuItemAlterar(Menu menu, SelectionListener listener){
		MenuItem miAlterar = new MenuItem(menu, SWT.BORDER);
		miAlterar.setText("Alterar");
		miAlterar.setImage(ImageRepository.ALTERAR16.getImage());
		miAlterar.addSelectionListener(listener);
		return miAlterar;
	}
	
	public static void createMenuItemAlterar(Menu menu, final IObservableValue value, final TableViewer tableViewer){
		createMenuItemAlterar(menu, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				value.setValue(SelectionHelper.getObject(tableViewer));
			}
		});
	}
	
	/*
	 * Menu que excluir o elemento selecionado através do service e retira ele da tabela =)
	 */
	public static <Y> void createMenuItemRemover(final TableViewerBuilder tvb, final GenericService<Y> service, Menu menu) {
		createMenuItemRemover(menu, new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				Y obj = (Y) SelectionHelper.getObject(tvb.getTableViewer().getSelection());
				try {
					if(!DialogHelper.openConfirmation("Deseja excluir o registro ?"))
						return;
					
					service.removerAtivarOuDesativar(obj);
					
					DialogHelper.openInformation("Registro excluido com sucesso");
					
					List<Y> elements = (List<Y>) tvb.getTableViewer().getInput();
					elements.remove(obj);
					tvb.getTableViewer().refresh();
				} catch (Exception e1) {
					log.error("Erro ao excluir " + obj.getClass(), e1);
					DialogHelper.openError("Erro ao excluir registro.\n" + e1.getMessage());
				}
			}
		});
	}
	
	public static MenuItem createMenuItemRemover(Menu menu, SelectionListener listener){
		MenuItem miRemover = new MenuItem(menu, SWT.BORDER);
		miRemover.setText("Remover");
		miRemover.setImage(ImageRepository.REMOVE_16.getImage());
		miRemover.addSelectionListener(listener);
		return miRemover;
	}
	
	/**
	 * Adiciona o menu item na tabela para remover o item e o double click para setar item selecionado no value.
	 * @return 
	 */
	public static <Y> Menu addMenusToTable(final TableViewerBuilder tvb, final GenericService<Y> service, final IObservableValue value){
		Table table = tvb.getTable();
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		createMenuItemAlterar(menu, value, tvb.getTableViewer());
		
		createMenuItemRemover(tvb, service, menu);
		
		return menu;
	}

}
