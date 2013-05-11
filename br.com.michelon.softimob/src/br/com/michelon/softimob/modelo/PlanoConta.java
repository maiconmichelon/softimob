package br.com.michelon.softimob.modelo;

public class PlanoConta {

	private Long codigo;
	
	private String descricao;
	
	public String getCodigoDescricao() {
		return String.format("%s - %s", codigo , descricao);
	}

}
