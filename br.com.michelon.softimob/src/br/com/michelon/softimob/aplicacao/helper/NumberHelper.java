package br.com.michelon.softimob.aplicacao.helper;

public class NumberHelper {

	public static String extractNumbers(String str){
		return str = str.replaceAll("\\D+","");
	}
	
}
