package br.com.michelon.softimob.aplicacao.helper;

import java.util.List;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;

public class SelectionHelper {

	@SuppressWarnings("unchecked")
	public static List<Object> getObjects(ColumnViewer viewer){
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if(selection == null)
			return null;
		
		return selection.toList();
	}

	public static Object getObject(ColumnViewer viewer){
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if(selection == null)
			return null;
		
		return selection.getFirstElement();
	}
	
}
