package br.com.michelon.softimob.aplicacao.exception;

public class ParametroNaoInformadoException extends Exception{

	private static final long serialVersionUID = 1L;

	public ParametroNaoInformadoException() {
		super();
	}
	
	public ParametroNaoInformadoException(String msg) {
		super(msg);
	}
	
}
