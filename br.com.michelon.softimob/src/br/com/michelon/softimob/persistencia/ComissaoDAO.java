package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Comissao;
import br.com.michelon.softimob.modelo.VendaAluguel;

public interface ComissaoDAO extends CrudRepository<Comissao, Long>{

	List<Comissao> findByVendaAluguel(VendaAluguel vendaAluguel);

}
