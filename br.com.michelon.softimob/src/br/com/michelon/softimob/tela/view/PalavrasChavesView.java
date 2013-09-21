package br.com.michelon.softimob.tela.view;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.PalavraChave;
import br.com.michelon.softimob.modelo.Venda;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewer;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewerColumn;
import br.com.michelon.softimob.tela.widget.xViewer.GenericXViewerContentProvider;
import br.com.michelon.softimob.tela.widget.xViewer.XViewerColumnProperties;

import com.google.common.collect.Maps;


public class PalavrasChavesView extends ViewPart {

	public static final String ID = "PalavrasChavesView"; //$NON-NLS-1$
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	public PalavrasChavesView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Form frmPalavraschaves = formToolkit.createForm(container);
		formToolkit.paintBordersFor(frmPalavraschaves);
		frmPalavraschaves.getBody().setLayout(new FillLayout());
		
		Composite composite = formToolkit.createComposite(frmPalavraschaves.getBody(), SWT.NONE);
		formToolkit.paintBordersFor(composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		Map<Class<?>, XViewerColumnProperties> map = Maps.newHashMap();
		map.put(Object.class.getClass(), new XViewerColumnProperties("simpleName"));
		map.put(PalavraChave.class, new XViewerColumnProperties("nome"));
		
		List<GenericXViewerColumn> columns = Arrays.asList(new GenericXViewerColumn("Nome", 400, map));
		
		GenericXViewerContentProvider contentProvider = new GenericXViewerContentProvider() {
			@Override
			public Object[] getChildrenElements(Object inputElement) {
				if(inputElement instanceof Class){
					return PalavraChave.getPalavrasChave((Class<?>) inputElement).toArray();
				
				} else if(inputElement instanceof PalavraChave){
				
					PalavraChave pc = (PalavraChave) inputElement;
					if(pc.getClazz() != null)
						return PalavraChave.getPalavrasChave(pc.getClazz()).toArray();
				}
				
				return null;
			}
		};
		
		GenericXViewer<Class<? extends PalavraChave>> xviewer = new GenericXViewer<Class<? extends PalavraChave>>(composite, SWT.BORDER, columns, contentProvider);
		xviewer.setInput(Arrays.asList(Aluguel.class, Venda.class, ContratoPrestacaoServico.class));

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
	}

	@Override
	public void setFocus() {
	}
}
