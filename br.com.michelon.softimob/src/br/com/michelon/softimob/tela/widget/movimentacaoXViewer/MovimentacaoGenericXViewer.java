package br.com.michelon.softimob.tela.widget.movimentacaoXViewer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.com.michelon.softimob.modelo.LancamentoContabil;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewer;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewerColumn;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewerContentProvider;
import br.com.michelon.softimob.tela.widget.xViewer.XViewerColumnProperties;

import com.google.common.collect.Maps;

public class MovimentacaoGenericXViewer {

	public static GenericXViewer<MovimentacaoContabil> createXviewer(Composite cp){
		
		Map<Class<?>, XViewerColumnProperties> m1 = Maps.newHashMap();
		m1.put(MovimentacaoContabil.class, new XViewerColumnProperties("id"));
		m1.put(LancamentoContabil.class, new XViewerColumnProperties("tipo"));
		GenericXViewerColumn c1 = new GenericXViewerColumn("Lote", 100, m1);
		
		Map<Class<?>, XViewerColumnProperties> m2 = Maps.newHashMap();
		m2.put(MovimentacaoContabil.class, new XViewerColumnProperties("data"));
		m2.put(LancamentoContabil.class, new XViewerColumnProperties("conta"));
		GenericXViewerColumn c2 = new GenericXViewerColumn("Data", 100, m2);
		
		Map<Class<?>, XViewerColumnProperties> m3 = Maps.newHashMap();
		m3.put(MovimentacaoContabil.class, new XViewerColumnProperties("valor"));
		m3.put(LancamentoContabil.class, new XViewerColumnProperties("valor"));
		GenericXViewerColumn c3 = new GenericXViewerColumn("Valor", 100, m3);
		
		Map<Class<?>, XViewerColumnProperties> m4 = Maps.newHashMap();
		m4.put(LancamentoContabil.class, new XViewerColumnProperties("historico"));
		GenericXViewerColumn c4 = new GenericXViewerColumn("Histórico", 400, m4);

		Map<Class<?>, XViewerColumnProperties> m5 = Maps.newHashMap();
		m5.put(LancamentoContabil.class, new XViewerColumnProperties("complemento"));
		GenericXViewerColumn c5 = new GenericXViewerColumn("Complemento", 400, m5);
		
		List<GenericXViewerColumn> columns = Arrays.asList(c1, c2, c3, c4, c5);
		
		GenericXViewer<MovimentacaoContabil> viewerMovimentacoes = new GenericXViewer<MovimentacaoContabil>(cp, SWT.BORDER | SWT.FULL_SELECTION, columns, new GenericXViewerContentProvider() {
			
			@Override
			public Object[] getChildrenElements(Object parentElement) {
				if(parentElement instanceof MovimentacaoContabil){
					return ((MovimentacaoContabil)parentElement).getLancamentos().toArray();
				}
				return null;
			}
		});
		
		return viewerMovimentacoes;
	}
	
}
