package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Arquivo;

public interface ArquivoDAO extends CrudRepository<Arquivo, Long>{

}
