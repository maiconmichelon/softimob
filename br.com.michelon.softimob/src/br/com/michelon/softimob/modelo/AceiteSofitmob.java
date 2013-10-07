package br.com.michelon.softimob.modelo;

public enum AceiteSofitmob {

	ACEITE("Aceite", org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite.A), 
	NAOACEITE("Não aceite", org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite.N);
	
	private final String descricao;
	private final org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite aceite;

	private AceiteSofitmob(String descricao, org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite aceite) {
		this.descricao = descricao;
		this.aceite = aceite;
	}

	public org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite getAceite() {
		return aceite;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
	
}
