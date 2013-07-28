package br.com.michelon.softimob.persistencia;

import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.jpa.JpaEntityManagerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;

import com.google.common.collect.Maps;

public class SpringUtils {

	public static final String CONFIG_LOCATION = "META-INF/application-context.xml";
	
	private static ApplicationContext ctx;

	private static EntityManagerFactory emf;
	
	public static ApplicationContext getContext(){
		if(ctx == null)
			initializeContext();
		
		return ctx;
	}
	
	public static void initializeContext(){
		if(ctx == null){
			ctx = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
		}
		
		emf = ((EntityManagerFactoryInfo) ctx.getBean("entityManagerFactory")).getNativeEntityManagerFactory();
		Map<String, String> properties = Maps.newHashMap();
		properties.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_OR_EXTEND);
		properties.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_DATABASE_GENERATION);
		properties.put(PersistenceUnitProperties.DEPLOY_ON_STARTUP, "true");
		((JpaEntityManagerFactory)emf).refreshMetadata(properties);
	}
	
}
