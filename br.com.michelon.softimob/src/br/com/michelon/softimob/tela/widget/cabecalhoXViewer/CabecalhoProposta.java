package br.com.michelon.softimob.tela.widget.cabecalhoXViewer;

import java.util.List;

import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.tela.widget.xViewer.CabecalhoXViewer;

public class CabecalhoProposta extends CabecalhoXViewer<Proposta>{

	public CabecalhoProposta(List<Proposta> subElements) {
		super(Proposta.class, subElements, "Data", "Cliente", "Funcionário", "Valor", "Observações");
	}

}
