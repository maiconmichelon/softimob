package br.com.michelon.softimob.tela.widget.propostaXViewer;

import org.eclipse.nebula.widgets.xviewer.XViewerColumn;
import org.eclipse.nebula.widgets.xviewer.XViewerColumn.SortDataType;
import org.eclipse.nebula.widgets.xviewer.XViewerFactory;
import org.eclipse.swt.SWT;

public class PropostaXViewerFactory extends XViewerFactory {

	public PropostaXViewerFactory(String namespace) {
		super(namespace);
	}

	public PropostaXViewerFactory() {
		super(COLUMN_NAMESPACE);
		registerColumns(imovel, data, realizador, valor, funcionario, observacoes);
	}

	private static String COLUMN_NAMESPACE = "br.com.michelon.softimob";
	public static XViewerColumn imovel = new XViewerColumn(COLUMN_NAMESPACE+ ".imovel", "Imóvel", 80, SWT.LEFT, true, SortDataType.String,false, null);
	public static XViewerColumn data = new XViewerColumn(COLUMN_NAMESPACE+ ".data", "Data", 80, SWT.LEFT, true, SortDataType.Date, false, null);
	public static XViewerColumn realizador = new XViewerColumn(COLUMN_NAMESPACE+ ".realizador", "Realizador", 80, SWT.LEFT, true, SortDataType.String,false, null);
	public static XViewerColumn valor = new XViewerColumn(COLUMN_NAMESPACE+ ".valor", "Valor", 80, SWT.LEFT, true, SortDataType.Float,false, null);
	public static XViewerColumn funcionario = new XViewerColumn(COLUMN_NAMESPACE+ ".funcionario", "Funcionário", 80, SWT.LEFT, true, SortDataType.String,false, null);
	public static XViewerColumn observacoes = new XViewerColumn(COLUMN_NAMESPACE+ ".observacoes", "Observações", 80, SWT.LEFT, true, SortDataType.String,false, null);
	
	@Override
	public boolean isAdmin() {
		return true;
	}

	@Override
	public boolean isLoadedStatusLabelAvailable() {
		return false;
	}

	@Override
	public boolean isFilterUiAvailable() {
		return false;
	}

	@Override
	public boolean isSearchUiAvailable() {
		return false;
	}

}
