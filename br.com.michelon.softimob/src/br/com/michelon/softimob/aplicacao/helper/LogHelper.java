package br.com.michelon.softimob.aplicacao.helper;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.michelon.softimob.modelo.Log;
import br.com.michelon.softimob.modelo.Usuario;

public class LogHelper {

	public static void setLog(Object obj) {
		try{
			List<Field> fields = ReflectionHelper.getAtributoLog(obj.getClass());
			
			for(Field f : fields){
				Log log = (Log) ReflectionHelper.getAtribute(obj, f.getName());
				
				Usuario usuario = LoginHelper.getUsuarioLogado();
				
				if(log == null)
					log = new Log();

				if(log.getUsuarioCadastro() == null)
					log.setUsuarioCadastro(usuario);
				log.setDataAlteracao(Calendar.getInstance().getTime());
				log.setUsuarioAlteracao(usuario);
				
				ReflectionHelper.setAtribute(obj, f.getName(), log, Log.class);
			}
		}catch(Exception e){
			Logger.getLogger(LogHelper.class).error("Erro ao setar log", e);
		}
	}
	
}
