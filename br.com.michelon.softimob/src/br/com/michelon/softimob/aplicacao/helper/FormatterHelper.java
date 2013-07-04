package br.com.michelon.softimob.aplicacao.helper;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

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
	
	public static String formatObject(Object obj){
		if(obj == null)
			return StringUtils.EMPTY;
		if(obj instanceof Date)
			return getSimpleDateFormat().format(obj);
		if(obj instanceof BigDecimal)
			return getCurrencyFormatter().format((BigDecimal) obj);
		return obj.toString();
	}
	
}
