package br.com.michelon.softimob.aplicacao.helper;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import br.com.michelon.softimob.tela.widget.ColumnProperties;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class WidgetHelper {

	public static TableViewerBuilder createTable(Composite composite, List<ColumnProperties> attributes){
		Composite cpTable = new Composite(composite, SWT.NONE);
		cpTable.setLayout(new GridLayout(1, false));
		cpTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		final TableViewerBuilder tvb = new TableViewerBuilder(cpTable);
		
		for (ColumnProperties columnProperties : attributes) {
			ColumnBuilder cb = tvb.createColumn(columnProperties.getLabel()).bindToProperty(columnProperties.getAtributo());
			
			if(columnProperties.getTamanho() != null)
				cb.setPercentWidth(columnProperties.getTamanho());
			
			cb.build();
		}
		
		return tvb;
	}

}
