package br.com.michelon.softimob.persistencia;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.jpa.JpaEntityManagerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {

	public static final String CONFIG_LOCATION = "META-INF/application-context.xml";
	
	private static ApplicationContext ctx;
	
	public static ApplicationContext getContext(){
		initializeContext();
		return ctx;
	}
	
	public static void initializeContext(){
		if(ctx == null){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("SoftimobPU");
			
			Map properties = new HashMap(); // TODO NECESSARIO ACHAR A SOLUCAO PARA ARRANCAR ESSA ENJAMBRAÇÃO DAQUI
			properties.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_OR_EXTEND);
			properties.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_DATABASE_GENERATION);
			properties.put(PersistenceUnitProperties.DEPLOY_ON_STARTUP, "true");
			((JpaEntityManagerFactory)emf).refreshMetadata(properties);
			
			ctx = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
		}
		
	}
	
}
