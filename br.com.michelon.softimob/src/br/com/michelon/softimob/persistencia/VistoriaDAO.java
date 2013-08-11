package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.VendaAluguel;
import br.com.michelon.softimob.modelo.Vistoria;

public interface VistoriaDAO extends CrudRepository<Vistoria, Long>{

	List<Vistoria> findByVendaAluguel(VendaAluguel vendaAluguel);

	@Query("SELECT size(i.fotos) FROM Vistoria i WHERE i = :vistoria")
	public abstract Integer sizeImages(@Param(value = "vistoria")Vistoria vistoria);

}
