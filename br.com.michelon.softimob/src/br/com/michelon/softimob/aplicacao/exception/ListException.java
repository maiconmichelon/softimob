package br.com.michelon.softimob.aplicacao.exception;

import java.util.List;

public class ListException extends Exception{

	private static final long serialVersionUID = 1L;
	private final List<Exception> exceptions;

	public ListException(List<Exception> exceptions) {
		super(transformarExceptions(exceptions));
		this.exceptions = exceptions;
	}

	private static String transformarExceptions(List<Exception> exceptions) {
		StringBuffer sb = new StringBuffer();
		
		for (Exception exception : exceptions) 
			sb.append(exception.getMessage() + "\n");
		
		return sb.toString();
	}
	
	public List<Exception> getExceptions() {
		return exceptions;
	}
	
}
