package br.com.michelon.softimob.tela.widget.cabecalhoXViewer;

import java.util.List;

import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.tela.widget.xViewer.CabecalhoXViewer;

public class CabecalhoContratoPrestacaoServico extends CabecalhoXViewer<ContratoPrestacaoServico>{

	public CabecalhoContratoPrestacaoServico(List<ContratoPrestacaoServico> subElements) {
		super(ContratoPrestacaoServico.class, subElements, "Data", "Data Vencimento", "Valor", "Funcionário", "Tipo", "Divulgar");
	}

}
