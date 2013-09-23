package br.com.michelon.softimob.aplicacao.exception;

public class ContaJaPagaRecebidaException extends Exception{

	private static final long serialVersionUID = 1L;

	public ContaJaPagaRecebidaException() {
		super("A conta já foi paga ou recebida, para remove-la é necessário estorna-la.");
	}
	
}
