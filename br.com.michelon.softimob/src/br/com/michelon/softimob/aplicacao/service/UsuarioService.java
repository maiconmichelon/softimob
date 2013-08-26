package br.com.michelon.softimob.aplicacao.service;

import org.apache.log4j.Logger;

import br.com.michelon.softimob.modelo.Usuario;
import br.com.michelon.softimob.persistencia.UsuarioDAO;

public class UsuarioService extends GenericService<Usuario>{

	private Logger log = Logger.getLogger(getClass());
	
	public UsuarioService() {
		super(UsuarioDAO.class);
	}

	protected UsuarioDAO getRepository() {
		return (UsuarioDAO) super.getRepository(); 
	}
	
	public Usuario findByLogin(String login){
		return getRepository().findByLogin(login);
	}

	public Usuario cadastraSeNaoHaverUsuarios(String login, String senha) {
		long count = getRepository().count();
		if(count == 0L){
			Usuario usuario = new Usuario();
			
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setAdministrador(true);
			
			try {
				salvar(usuario);
			} catch (Exception e) {
				log.error("Erro ao cadastrar primeiro usuario.", e);
			}
		}
		return null;
	}
	
}
