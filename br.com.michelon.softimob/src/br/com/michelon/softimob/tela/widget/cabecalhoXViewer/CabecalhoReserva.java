package br.com.michelon.softimob.tela.widget.cabecalhoXViewer;

import java.util.List;

import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.modelo.Reserva;
import br.com.michelon.softimob.tela.widget.xViewer.CabecalhoXViewer;

public class CabecalhoReserva extends CabecalhoXViewer<Reserva>{

	public CabecalhoReserva(List<Reserva> subElements) {
		super(Reserva.class, subElements, ImageRepository.RESERVA16.getImage(), "Data da Reserva", "Data de Vencimento", "Cliente", "Funcionário", "Valor", "Observações");
	}

}
