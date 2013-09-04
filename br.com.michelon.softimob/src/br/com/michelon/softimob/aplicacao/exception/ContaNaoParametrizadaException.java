package br.com.michelon.softimob.aplicacao.exception;

public class ContaNaoParametrizadaException extends Exception{

	private static final long serialVersionUID = 1L;

	public ContaNaoParametrizadaException() {
		super();
	}
	
	public ContaNaoParametrizadaException(String msg) {
		super(msg);
	}
	
}
