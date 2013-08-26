package br.com.michelon.softimob.persistencia;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Long>{

	Usuario findByLogin(String login);
	
}
