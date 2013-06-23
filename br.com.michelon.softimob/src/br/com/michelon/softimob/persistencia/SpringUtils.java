package br.com.michelon.softimob.persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
			ctx = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
		}
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SoftimobPU");
	}
	
}
