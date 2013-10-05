package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.PessoaFisica;

public interface PessoaFisicaDAO extends CrudRepository<PessoaFisica, Long>{

	@Query("SELECT r FROM PessoaFisica r WHERE r.ativo = :ativo")
	List<PessoaFisica> findAtivos(@Param(value="ativo") Boolean ativo);

	@Query("SELECT r FROM PessoaFisica r WHERE r.ativo = true AND SQL('EXTRACT (MONTH FROM ?)', r.dataNascimento) = :mes AND SQL('EXTRACT (DAY FROM ?)', r.dataNascimento) = :dia")
	List<PessoaFisica> findAnivesariantes(@Param(value="dia")Integer dia, @Param(value="mes")Integer mes);

}
