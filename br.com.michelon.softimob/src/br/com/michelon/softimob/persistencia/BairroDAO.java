package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Cidade;

public interface BairroDAO extends CrudRepository<Bairro, Long>{

	public List<Bairro> findBairroByCidade(Cidade cidade);
	
}
