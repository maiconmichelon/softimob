package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.Funcionario;

public interface FuncionarioDAO extends JpaRepository<Funcionario, Long>, JpaSpecificationExecutor<Funcionario> {

	@Query("SELECT r FROM Funcionario r WHERE r.ativo = true AND SQL('EXTRACT (MONTH FROM ?)', r.dataNascimento) = :mes AND SQL('EXTRACT (DAY FROM ?)', r.dataNascimento) = :dia")
	List<Funcionario> findAniversariantes(@Param(value="dia")Integer dia, @Param(value="mes")Integer mes);

	List<Funcionario> findByNome(String nome);

	List<Funcionario> findByAtivoIsTrue();

}
