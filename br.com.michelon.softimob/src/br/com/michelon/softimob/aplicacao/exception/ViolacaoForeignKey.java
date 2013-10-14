package br.com.michelon.softimob.aplicacao.exception;

public class ViolacaoForeignKey extends Exception{

	private static final long serialVersionUID = 1L;

	public ViolacaoForeignKey(String msg) {
		super(msg);
	}
	
}
