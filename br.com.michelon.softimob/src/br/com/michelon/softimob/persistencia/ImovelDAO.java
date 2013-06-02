package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Imovel;

public interface ImovelDAO extends CrudRepository<Imovel, Long>{

}
