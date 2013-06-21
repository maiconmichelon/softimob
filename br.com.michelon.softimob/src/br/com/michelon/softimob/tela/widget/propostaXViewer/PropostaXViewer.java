package br.com.michelon.softimob.tela.widget.propostaXViewer;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.nebula.widgets.xviewer.IXViewerFactory;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.widgets.Composite;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Proposta;

public class PropostaXViewer extends XViewer{

	private final Set<Proposta> runList = new HashSet<Proposta>();
	
	public PropostaXViewer(Composite parent, int style, IXViewerFactory xViewerFactory) {
		super(parent, style, xViewerFactory);
		
		setContentProvider(new PropostaXViewerContentProvider());
		setLabelProvider(new PropostaXViewerLabelProvider(this));
	}

	public PropostaXViewer(Composite parent, int style) {
		this(parent, style, new PropostaXViewerFactory());
	}

	public boolean isScheduled(Proposta autoRunTask) {
		return true;
	}

	public boolean isRun(Imovel autoRunTask) {
		return runList.contains(autoRunTask);
	}

	public void setRun(Proposta autoRunTask, boolean run) {
		if (run)
			runList.add(autoRunTask);
		else
			runList.remove(autoRunTask);
	}
	
}
