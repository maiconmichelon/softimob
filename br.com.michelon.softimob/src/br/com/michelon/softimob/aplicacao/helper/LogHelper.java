package br.com.michelon.softimob.aplicacao.helper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.Log;

public class LogHelper {

	public static void setLog(Object obj){
		try {
			setLog(Arrays.asList(obj));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setLog(List<Object> objs) throws IllegalArgumentException, IllegalAccessException{
		for(Object obj : objs){
			List<Field> fields = ReflectionHelper.getAtributoLog(obj.getClass());
			
			for(Field f : fields){
				Log log = (Log) f.get(obj);
				
				Funcionario func = LoginHelper.getFuncionarioLogado();
				
				if(log.getFuncionarioCadastro() == null)
					log.setFuncionarioCadastro(func);
				log.setDataAlteracao(Calendar.getInstance().getTime());
				log.setFuncionarioAlteracao(func);
			}
			
		}
	}
	
}
