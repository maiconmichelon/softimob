package br.com.michelon.softimob.tela.widget.cabecalhoXViewer;

import java.util.List;

import br.com.michelon.softimob.modelo.Reserva;
import br.com.michelon.softimob.tela.widget.xViewer.CabecalhoXViewer;

public class CabecalhoReserva extends CabecalhoXViewer<Reserva>{

	public CabecalhoReserva(List<Reserva> subElements) {
		super(Reserva.class, subElements, "Data", "Data de Vencimento", "Cliente", "Funcionário", "Valor", "Observações");
	}

}
