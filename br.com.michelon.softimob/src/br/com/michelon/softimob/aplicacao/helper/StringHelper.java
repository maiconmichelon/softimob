package br.com.michelon.softimob.aplicacao.helper;

import java.text.Normalizer;

public class StringHelper {

	public String removeAccents(String string){
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		string = string.replaceAll("[^\\p{ASCII}]", "");
		return string;
	}
	
}
