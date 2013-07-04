package br.com.michelon.softimob.tela.widget.movimentacaoXViewer;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.nebula.widgets.xviewer.IXViewerFactory;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.widgets.Composite;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;

public class MovimentacaoXViewer extends XViewer{

	private final Set<MovimentacaoContabil> runList = new HashSet<MovimentacaoContabil>();
	
	public MovimentacaoXViewer(Composite parent, int style, IXViewerFactory xViewerFactory) {
		super(parent, style, xViewerFactory);
		
		setContentProvider(new ImovelXViewerContentProvider());
		setLabelProvider(new MovimentacaoContabilXViewerLabelProvider(this));
	}

	public MovimentacaoXViewer(Composite parent, int style) {
		this(parent, style, new ImovelXViewerFactory());
	}

	public boolean isScheduled(Imovel autoRunTask) {
		return true;
	}

	public boolean isRun(Imovel autoRunTask) {
		return runList.contains(autoRunTask);
	}

	public void setRun(Imovel autoRunTask, boolean run) {
//		if (run)
//			runList.add(autoRunTask);
//		else
//			runList.remove(autoRunTask);
	}
	
}
