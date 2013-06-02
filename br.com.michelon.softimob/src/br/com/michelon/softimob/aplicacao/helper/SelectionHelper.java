package br.com.michelon.softimob.aplicacao.helper;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;

public class SelectionHelper {

	public static List<Object> getObjects(StructuredViewer viewer){
		return getObjects((IStructuredSelection)viewer.getSelection());
	}

	public static Object getObject(StructuredViewer viewer){
		return getObject((IStructuredSelection)viewer.getSelection());
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object> getObjects(IStructuredSelection selection){
		if(selection == null)
			return null;
		
		return selection.toList();
	}
	
	public static Object getObject(IStructuredSelection selection){
		if(selection == null)
			return null;
		
		return selection.getFirstElement();
	}

	public static Object getObject(ISelection selection) {
		return ((IStructuredSelection) selection).getFirstElement();
	}
	
}
