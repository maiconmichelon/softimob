package br.com.michelon.softimob.aplicacao.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.persistencia.SpringUtils;

public class GenericService<T> {

	private CrudRepository<T, Serializable> crudRepository;
	private T model;
	
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	public GenericService(Class clazz){
		if(SpringUtils.getContext() != null)
			crudRepository = SpringUtils.getContext().getBean(clazz);
	}
	
	protected CrudRepository<T, ?> getRepository() {
		return crudRepository;
	}
	
	public void salvar(T registro) throws Exception{
		crudRepository.save(registro);
	}
	
	public void delete(T registro) throws Exception{
		crudRepository.delete(registro);
	}
	
	public List<T> findAll(){
		return (List<T>) crudRepository.findAll();
	}

	public T findOne(Serializable id){
		return crudRepository.findOne(id);
	}
	
	public void setModel(T object) {
		this.model = object;
	}
	
	public T getModel() {
		return model;
	}
	
	@SuppressWarnings("unchecked")
	public T refreshObject(T object){
		return object;
	}
	
}
