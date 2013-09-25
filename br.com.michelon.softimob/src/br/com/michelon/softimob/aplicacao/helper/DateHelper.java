package br.com.michelon.softimob.aplicacao.helper;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateHelper {

	public static Date getUltimoDiaMes(int mes){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, mes);
		
		int actualMaximum = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, actualMaximum);
		
		return c.getTime();
	}
	
	public static Date getUltimoDiaMes(){
		return getUltimoDiaMes(Calendar.getInstance().get(Calendar.MONTH));
	}

	public static Date getPrimeiroDiaMes() {
		return getPrimeiroDiaMes(Calendar.getInstance().get(Calendar.MONTH));
	}

	public static Date getPrimeiroDiaMes(int mes) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, mes);
		
		int actualMin = c.getMinimum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, actualMin);
		
		return c.getTime();
	}
	
	public static Date zerarHoraMinutos(Date data){
		Calendar c = Calendar.getInstance();
		
		c.setTime(data);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return c.getTime();
	}
	public static boolean isVencida(Date dataVencimento) {
		if(dataVencimento == null)
			return false;
		return DateHelper.zerarHoraMinutos(dataVencimento).compareTo(DateHelper.zerarHoraMinutos(Calendar.getInstance().getTime())) < 0 ;
	}
	
	public static String getMesExtenso(int mesInt){
		String mes = StringUtils.EMPTY;
		
		switch(mesInt){
			case 1 : mes = "Janeiro"; break;
			case 2 : mes = "Fevereiro"; break;
			case 3 : mes = "Março"; break;
			case 4 : mes = "Abril"; break;
			case 5 : mes = "Maio"; break;
			case 6 : mes = "Junho"; break;
			case 7 : mes = "Julho"; break;
			case 8 : mes = "Agosto"; break;
			case 9 : mes = "Setembro"; break;
			case 10 : mes = "Outubro"; break;
			case 11 : mes = "Novembro"; break;
			case 12 : mes = "Dezembro"; break;
			default : mes = "";
		}
		
		return mes;
	}
	
	public static String getDataExtenso(Date data){
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		
		return String.format("%s de %s de %s", c.get(Calendar.DAY_OF_MONTH), getMesExtenso(c.get(Calendar.MONTH) + 1), c.get(Calendar.YEAR));
	}
	
}
