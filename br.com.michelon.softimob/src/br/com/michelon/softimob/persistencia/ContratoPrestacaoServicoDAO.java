package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.Imovel;

public interface ContratoPrestacaoServicoDAO extends CrudRepository<ContratoPrestacaoServico, Long>{

	List<ContratoPrestacaoServico> findByImovel(Imovel imovel);
	
}
