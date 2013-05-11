package br.com.michelon.softimob.aplicacao.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatterHelper {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String formatarData(Date data){
		return sdf.format(data);
	}
	
	public static SimpleDateFormat getSimpleDateFormat() {
		return sdf;
	}
	
}
