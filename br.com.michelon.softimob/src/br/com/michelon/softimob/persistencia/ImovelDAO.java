package br.com.michelon.softimob.persistencia;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Imovel;

public interface ImovelDAO extends CrudRepository<Imovel, Long>{

	@Query("SELECT size(i.fotos) FROM Imovel i WHERE i = :imovel")
	public abstract Integer sizeImages(@Param(value = "imovel")Imovel imovel);
	
}
