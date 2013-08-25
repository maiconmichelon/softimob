package br.com.michelon.softimob.persistencia;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Pendencia;

public interface ContratoPrestacaoServicoDAO extends CrudRepository<ContratoPrestacaoServico, Long>{

	List<ContratoPrestacaoServico> findByImovel(Imovel imovel);

	List<Pendencia> findByDataVencimentoBeforeAndResolvidoFalse(Date dataVencimento);
	
}
