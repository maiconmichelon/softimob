package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Cidade;

public interface BairroDAO extends CrudRepository<Bairro, Long>{

	@Query(value="FROM Bairro b WHERE b.cidade = :cidade")
	public List<Bairro> findBairroByCidade(@Param(value="cidade") Cidade cidade);
	
}
