package br.com.michelon.softimob.aplicacao.helper;

import java.util.List;

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
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.tela.widget.ColumnProperties;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class WidgetHelper {

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

	public static void createMenuItemAlterar(Menu menu, SelectionListener listener){
		MenuItem miAlterar = new MenuItem(menu, SWT.BORDER);
		miAlterar.setText("Alterar");
		miAlterar.setImage(ImageRepository.ALTERAR16.getImage());
		miAlterar.addSelectionListener(listener);
	}
	
	public static void createMenuItemAlterar(Menu menu, final IObservableValue value, final TableViewer tableViewer){
		createMenuItemAlterar(menu, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				value.setValue(SelectionHelper.getObject(tableViewer));
			}
		});
	}
	
	public static void createMenuItemRemover(Menu menu, SelectionListener listener){
		MenuItem miRemover = new MenuItem(menu, SWT.BORDER);
		miRemover.setText("Remover");
		miRemover.setImage(ImageRepository.REMOVE_16.getImage());
		miRemover.addSelectionListener(listener);
	}
	
	public static void createMenuItemRemover(Menu menu, final TableViewer tableViewer){
		createMenuItemRemover(menu, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer.remove(SelectionHelper.getObjects(tableViewer).toArray());
			}
		});
	}

	public static Menu createBasicMenus(TableViewer tableViewer, IObservableValue value){
		Menu menu = new Menu(tableViewer.getTable());
		tableViewer.getTable().setMenu(menu);
		
		createMenuItemAlterar(menu, value, tableViewer);
		createMenuItemRemover(menu, tableViewer);
		
		return menu;
	}
	
}
