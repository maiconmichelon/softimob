package br.com.michelon.softimob.aplicacao.helper;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;

public class SelectionHelper {

	@SuppressWarnings("unchecked")
	public static List<Object> getObjects(Viewer viewer){
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if(selection == null)
			return null;
		
		return selection.toList();
	}

	public static Object getObject(Viewer viewer){
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if(selection == null)
			return null;
		
		return selection.getFirstElement();
	}
	
}
