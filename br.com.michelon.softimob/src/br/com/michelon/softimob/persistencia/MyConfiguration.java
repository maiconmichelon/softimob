package br.com.michelon.softimob.persistencia;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;

import br.com.michelon.softimob.modelo.Funcionario;

	@Configuration
	public class MyConfiguration {
	
	  @Autowired
	  EntityManager entityManager;
	  @Autowired
	  BeanFactory beanFactory;
	
	  @Bean
	  public FuncionarioDAO myRepository() {
	  
	    JpaRepositoryFactoryBean<FuncionarioDAO, Funcionario ,Long> factory = new JpaRepositoryFactoryBean<FuncionarioDAO, Funcionario, Long>();
	    factory.setBeanFactory(beanFactory);
	    factory.setEntityManager(entityManager);
	    factory.setRepositoryInterface(FuncionarioDAO.class);
	    factory.afterPropertiesSet();
	    
	    return factory.getObject();
	  }
	}

