package br.com.michelon.softimob.aplicacao.helper;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class WidgetHelper {

	public static TableViewerBuilder createTableWithFilter(FormToolkit form, Composite composite, Map<String, String> atributos){
		Composite cpTable = new Composite(composite, SWT.NONE);
		cpTable.setLayout(new GridLayout(1, false));
		cpTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		final TableViewerBuilder tvb = new TableViewerBuilder(cpTable);
		for(String key: atributos.keySet()){
			tvb.createColumn(key).bindToProperty(atributos.get(key)).build();
		}
		return tvb;
	}

}
