package br.com.michelon.softimob.aplicacao.helper;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.ralfebert.rcputils.properties.IValueFormatter;

public class FormatterHelper {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdfPeriodo = new SimpleDateFormat("MM/yyyy");
	
	public String formatarData(Date data){
		return sdf.format(data);
	}
	
	public static SimpleDateFormat getSimpleDateFormat() {
		return sdf;
	}

	public static IValueFormatter<BigDecimal, String> getCurrencyFormatter() {
		return new IValueFormatter<BigDecimal, String>() {

			@Override
			public String format(BigDecimal arg0) {
				return String.format("R$%s", arg0.toString().replace(".", ","));
			}

			@Override
			public BigDecimal parse(String arg0) {
				return new BigDecimal(arg0.replace(',', '.'));
			}
		};
	}

	public static DateFormat getSimpleDateFormatPeriodo() {
		return sdfPeriodo;
	}
	
}
