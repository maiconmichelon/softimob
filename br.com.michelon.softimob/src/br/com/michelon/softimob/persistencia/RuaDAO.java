package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Bairro;
import br.com.michelon.softimob.modelo.Rua;

public interface RuaDAO extends CrudRepository<Rua, Long>{

	@Query(value="SELECT r FROM Rua r WHERE r.bairro = :bairro")
	public List<Rua> findRuaByBairro(@Param(value="bairro") Bairro bairro);

	public Rua findByNome(String nome);
	
}
