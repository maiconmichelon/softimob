package br.com.michelon.softimob.aplicacao.helper;

import br.com.michelon.softimob.modelo.Usuario;

public class LoginHelper {

	private static Usuario usuarioLogado;

	public static Usuario getUsuarioLogado(){
		return usuarioLogado;
	}
	
	public static void setUsuarioLogado(Usuario usuarioLogado){
		LoginHelper.usuarioLogado = usuarioLogado;
		
	}
	
	public static boolean isAdminLogado(){
		return usuarioLogado.getAdministrador();
	}
	
}
