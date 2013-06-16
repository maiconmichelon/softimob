package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.PessoaJuridica;

public interface PessoaJuridicaDAO extends CrudRepository<PessoaJuridica, Long>{

}
