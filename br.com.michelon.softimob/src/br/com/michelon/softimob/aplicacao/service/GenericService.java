package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.persistencia.SpringUtils;

public class GenericService<T> {

	private CrudRepository<T, ?> crudRepository;
	private T model;
	
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	public GenericService(Class clazz){
		crudRepository = SpringUtils.getContext().getBean(clazz);
	}
	
	protected CrudRepository<T, ?> getRepository() {
		return crudRepository;
	}
	
	public void salvar(T registro) throws Exception{
		crudRepository.save(registro);
	}
	
	public List<T> findAll(){
		return (List<T>) crudRepository.findAll();
	}

	public void setModel(T object) {
		this.model = object;
	}
	
	public T getModel() {
		return model;
	}
	
}
