package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.VendaAluguel;
import br.com.michelon.softimob.modelo.Vistoria;

public interface VistoriaDAO extends CrudRepository<Vistoria, Long>{

	List<Vistoria> findByVendaAluguel(VendaAluguel vendaAluguel);

}
