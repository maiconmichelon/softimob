package br.com.michelon.softimob.aplicacao.helper;

import br.com.michelon.softimob.modelo.Funcionario;

public class LoginHelper {

	private static Funcionario funcionarioLogado;

	public static Funcionario getFuncionarioLogado(){
		return funcionarioLogado;
	}
	
	public static void setFuncionarioLogado(Funcionario funcionarioLogado){
		LoginHelper.funcionarioLogado = funcionarioLogado;
		
	}
	
}
