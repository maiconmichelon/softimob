package br.com.michelon.softimob.aplicacao.helper;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class WidgetHelper {

	public static TableViewerBuilder createTableWithFilter(Composite composite, Map<String, String> atributos){
		Composite cpTable = new Composite(composite, SWT.NONE);
		cpTable.setLayout(new GridLayout(1, false));
		cpTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		final TableViewerBuilder tvb = new TableViewerBuilder(cpTable);
		for(String label: atributos.keySet()){
			String atributo = atributos.get(label);
			
			ColumnBuilder cb = tvb.createColumn(label);
			
			if(getFirstPositionSpecialChar(atributo) == 0 && getFirstPositionSpecialChar(atributo) < getLastPositionSpecialChar(atributo)){
				cb.setPercentWidth(Integer.parseInt(atributo.substring(1, getLastPositionSpecialChar(atributo))));
				atributo = atributo.substring(getLastPositionSpecialChar(atributo), atributo.length() - 1);
			}
			
			cb.bindToProperty(atributo).build();
		}
		return tvb;
	}

	private static int getLastPositionSpecialChar(String atributo) {
		return atributo.lastIndexOf("|");
	}

	private static int getFirstPositionSpecialChar(String atributo) {
		return atributo.indexOf("|");
	}

}
