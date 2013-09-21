package br.com.michelon.softimob.aplicacao.helper;

import java.util.Calendar;
import java.util.Date;

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
	
}
