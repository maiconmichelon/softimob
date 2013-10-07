package br.com.michelon.softimob.persistencia;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.BoletoSoftimob;
import br.com.michelon.softimob.modelo.ContaPagarReceber;

public interface BoletoSoftimobDAO extends CrudRepository<BoletoSoftimob, Long>, Serializable{

	BoletoSoftimob findByConta(ContaPagarReceber conta);

}
