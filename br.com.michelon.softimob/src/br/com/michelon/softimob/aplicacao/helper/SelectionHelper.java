package br.com.michelon.softimob.aplicacao.helper;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;

public class SelectionHelper {

	@SuppressWarnings("unchecked")
	public static <T> List<T> getObjects(StructuredViewer viewer){
		return (List<T>) getObjects((IStructuredSelection)viewer.getSelection());
	}

	@SuppressWarnings("unchecked")
	public static <T> T getObject(StructuredViewer viewer){
		return (T) getObject((IStructuredSelection)viewer.getSelection());
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getObjects(IStructuredSelection selection){
		if(selection == null)
			return null;
		
		return selection.toList();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getObject(IStructuredSelection selection){
		if(selection == null)
			return null;
		
		return (T) selection.getFirstElement();
	}

	public static Object getObject(ISelection selection) {
		return ((IStructuredSelection) selection).getFirstElement();
	}
	
}
