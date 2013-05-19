package br.com.michelon.softimob.aplicacao.editorInput;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class GenericEditorInput<T> implements IEditorInput{

	private T modelo;

	public GenericEditorInput(T modelo) {
		super();
		this.modelo = modelo;
	}

	public GenericEditorInput() {}

	public T getModelo() {
		return modelo;
	}

	@SuppressWarnings("unchecked")
	public void setModelo(Object t) {
		this.modelo = (T) t;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getName() {
		return StringUtils.EMPTY;
	}

	@Override
	public String getToolTipText() {
		return StringUtils.EMPTY;
	}

}
