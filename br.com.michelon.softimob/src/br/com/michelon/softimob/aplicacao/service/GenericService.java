package br.com.michelon.softimob.aplicacao.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public class GenericService<T> {

	private final CrudRepository<T, ?> crudRepository;
	private T model;
	
	public GenericService(CrudRepository<T, ?> crudRepository){
		this.crudRepository = crudRepository;
	}
	
	public CrudRepository<T, ?> getRepository() {
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
