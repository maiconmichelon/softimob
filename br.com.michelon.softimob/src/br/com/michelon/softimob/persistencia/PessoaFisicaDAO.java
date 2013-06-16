package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.PessoaFisica;

public interface PessoaFisicaDAO extends CrudRepository<PessoaFisica, Long>{

}
