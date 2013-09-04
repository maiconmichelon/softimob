package br.com.michelon.softimob.tela.widget.imovelXViewerGenerico;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.modelo.Chave;
import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.Endereco;
import br.com.michelon.softimob.modelo.Feedback;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.modelo.Reserva;
import br.com.michelon.softimob.tela.widget.cabecalhoXViewer.CabecalhoChave;
import br.com.michelon.softimob.tela.widget.cabecalhoXViewer.CabecalhoComodo;
import br.com.michelon.softimob.tela.widget.cabecalhoXViewer.CabecalhoContratoPrestacaoServico;
import br.com.michelon.softimob.tela.widget.cabecalhoXViewer.CabecalhoFeedback;
import br.com.michelon.softimob.tela.widget.cabecalhoXViewer.CabecalhoProposta;
import br.com.michelon.softimob.tela.widget.cabecalhoXViewer.CabecalhoReserva;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewer;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewerColumn;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewerContentProvider;
import br.com.michelon.softimob.tela.widget.xViewer.XViewerColumnProperties;

import com.google.common.collect.Maps;

public class ImovelGenericXViewer {

	public static GenericXViewer<Imovel> createXviewer(Composite cp){
		
		Map<Class<?>, XViewerColumnProperties> m1 = Maps.newHashMap();
		m1.put(Imovel.class, new XViewerColumnProperties("id", ImageRepository.IMOVEL_16.getImage(), false));
		m1.put(Endereco.class, new XViewerColumnProperties("cep", ImageRepository.ENDERECO16.getImage(), false));
		m1.put(Comodo.class, new XViewerColumnProperties("tipoComodo.nome"));
		m1.put(Chave.class, new XViewerColumnProperties("numero"));
		m1.put(Feedback.class, new XViewerColumnProperties("data"));
		m1.put(Proposta.class, new XViewerColumnProperties("data"));
		m1.put(Reserva.class, new XViewerColumnProperties("dataReserva"));
		m1.put(ContratoPrestacaoServico.class, new XViewerColumnProperties("dataInicio"));
		GenericXViewerColumn c1 = new GenericXViewerColumn("Código", 200, m1);
		
		Map<Class<?>, XViewerColumnProperties> m2 = Maps.newHashMap();
		m2.put(Imovel.class, new XViewerColumnProperties("metragem"));
		m2.put(Endereco.class, new XViewerColumnProperties("rua.bairro.cidade.estado.uf"));
		m2.put(Comodo.class, new XViewerColumnProperties("quantidade"));
		m2.put(Chave.class, new XViewerColumnProperties("localizacao"));
		m2.put(Feedback.class, new XViewerColumnProperties("funcionario.nome"));
		m2.put(Proposta.class, new XViewerColumnProperties("cliente.nome"));
		m2.put(Reserva.class, new XViewerColumnProperties("dataVencimento"));
		m2.put(ContratoPrestacaoServico.class, new XViewerColumnProperties("dataVencimento"));
		GenericXViewerColumn c2 = new GenericXViewerColumn("Metragem", 170, m2);
		
		Map<Class<?>, XViewerColumnProperties> m3 = Maps.newHashMap();
		m3.put(Imovel.class, new XViewerColumnProperties("angariador.nome"));
		m3.put(Endereco.class, new XViewerColumnProperties("rua.bairro.cidade.nome"));
		m3.put(Comodo.class, new XViewerColumnProperties("descricao"));
		m3.put(Feedback.class, new XViewerColumnProperties("cliente.nome"));
		m3.put(Proposta.class, new XViewerColumnProperties("funcionario.nome"));
		m3.put(Reserva.class, new XViewerColumnProperties("cliente.nome"));
		m3.put(ContratoPrestacaoServico.class, new XViewerColumnProperties("valor"));
		GenericXViewerColumn c3 = new GenericXViewerColumn("Angariador", 170, m3);
		
		Map<Class<?>, XViewerColumnProperties> m4 = Maps.newHashMap();
		m4.put(Imovel.class, new XViewerColumnProperties("tipo.nome"));
		m4.put(Endereco.class, new XViewerColumnProperties("rua.bairro.nome"));
		m4.put(Feedback.class, new XViewerColumnProperties("observacoes"));
		m4.put(Proposta.class, new XViewerColumnProperties("valor"));
		m4.put(Reserva.class, new XViewerColumnProperties("funcionario.nome"));
		m4.put(ContratoPrestacaoServico.class, new XViewerColumnProperties("funcionario.nome"));
		GenericXViewerColumn c4 = new GenericXViewerColumn("Tipo", 170, m4);

		Map<Class<?>, XViewerColumnProperties> m5 = Maps.newHashMap();
		m5.put(Imovel.class, new XViewerColumnProperties("proprietario.nome"));
		m5.put(Endereco.class, new XViewerColumnProperties("rua.nome"));
		m5.put(Proposta.class, new XViewerColumnProperties("observacoes"));
		m5.put(Reserva.class, new XViewerColumnProperties("valor"));
		m5.put(ContratoPrestacaoServico.class, new XViewerColumnProperties("tipo"));
		GenericXViewerColumn c5 = new GenericXViewerColumn("Cliente", 170, m5);
		
		Map<Class<?>, XViewerColumnProperties> m6 = Maps.newHashMap();
		m6.put(Imovel.class, new XViewerColumnProperties("Observacoes"));
		m6.put(Endereco.class, new XViewerColumnProperties("numero"));
		m6.put(Reserva.class, new XViewerColumnProperties("observacoes"));
		m6.put(ContratoPrestacaoServico.class, new XViewerColumnProperties("divulgarExtenso"));
		GenericXViewerColumn c6 = new GenericXViewerColumn("Observações", 250, m6);
		
		List<GenericXViewerColumn> columns = Arrays.asList(c1, c2, c3, c4, c5, c6);
		
		GenericXViewer<Imovel> viewerMovimentacoes = new GenericXViewer<Imovel>(cp, SWT.BORDER | SWT.FULL_SELECTION, columns, new GenericXViewerContentProvider() {
			
			@Override
			public Object[] getChildrenElements(Object parentElement) {
				if(parentElement instanceof Imovel){
					Imovel imovel = (Imovel) parentElement;
					return new Object[]{imovel.getEndereco(), new CabecalhoComodo(imovel.getComodos()), new CabecalhoChave(imovel.getChaves())
						, new CabecalhoContratoPrestacaoServico(imovel.getContratos()), new CabecalhoFeedback(imovel.getFeedbacks())
						, new CabecalhoProposta(imovel.getPropostas()), new CabecalhoReserva(imovel.getReservas())
					};
				}
				if(parentElement instanceof Proposta)
					return new Object[]{((Proposta) parentElement).getContraProposta()};

				return null;
			}
			
			@Override
			public boolean hasChildren(Object element) {
				return element instanceof Imovel ? true : super.hasChildren(element);
			}
			
		});
		
		return viewerMovimentacoes;
	}
	
}
