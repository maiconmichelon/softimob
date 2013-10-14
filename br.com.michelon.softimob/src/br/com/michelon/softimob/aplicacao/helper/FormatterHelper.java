package br.com.michelon.softimob.aplicacao.helper;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import de.ralfebert.rcputils.properties.IValueFormatter;

public class FormatterHelper {

	public static final Locale BRAZIL = new Locale("pt", "BR");
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdfPeriodo = new SimpleDateFormat("MM/yyyy");
	private static SimpleDateFormat sdfHorasMinutos = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private static DecimalFormat decimalFormat;
	
	
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
				return String.format("R$%s", getDecimalFormat().format(arg0));
			}

			@Override
			public BigDecimal parse(String arg0) {
				try {
					arg0 = arg0.replace("R$", StringUtils.EMPTY);
					return (BigDecimal) getDecimalFormat().parse(arg0);
				} catch (ParseException e) {
					return null;
				}
			}
		};
	}

	public static IValueFormatter<BigDecimal, String> getDecimalFormatter() {
		return new IValueFormatter<BigDecimal, String>() {

			@Override
			public String format(BigDecimal arg0) {
				if(arg0 == null)
					return StringUtils.EMPTY;
				return getDecimalFormat().format(arg0);
			}

			@Override
			public BigDecimal parse(String arg0) {
				try {
					Number parse = getDecimalFormat().parse(arg0);
					if(parse instanceof Long)
						return BigDecimal.valueOf((Long) parse);
					Number number = getDecimalFormat().parse(arg0);
					if(number instanceof BigDecimal)
						return (BigDecimal) number;
					if(number instanceof Double)
						return new BigDecimal((Double)number);
					if(number instanceof Integer)
						return new BigDecimal((Integer)number);
					return null;
				} catch (ParseException e) {
					return null;
				}
			}
		};
	}
	
	public static DecimalFormat getDecimalFormat(){
		if(decimalFormat == null){
			decimalFormat = (DecimalFormat) DecimalFormat.getInstance(BRAZIL);
			decimalFormat.setGroupingUsed(false);
			decimalFormat.setMinimumFractionDigits(2);
		}
		return decimalFormat;
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
			return getDefaultValueFormatterToMoney().format((BigDecimal) obj);
		return obj.toString();
	}
	
	public static IValueFormatter<BigDecimal, String> getDefaultValueFormatterToMoney(){
		return getDecimalFormatter();
	}

	public static SimpleDateFormat getSimpleDateFormatHorasMinutos() {
		return sdfHorasMinutos;
	}
	
	public static String getDataFormatada(Date data){
		return DateHelper.getDataExtenso(data);
	}
	
	public static String removerAcentos(String str){
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
	}

	public static String formatarSeisNumeros(String s){
		while(s.length() != 6){
			s = "0" + s;
		}
		return s;
	}
	
	/**
	 * Passando a string "Ola" com 5 letras vai voltar "Ola  "
	 */
	public static String preencherBranco(String s, int lenght){
		String retorno = StringUtils.EMPTY;
		for (int i = 0; i < lenght; i++) {
			retorno = retorno + (i < s.length() ? s.charAt(i) : ' ');
		}
		return retorno;
	}
	
}
