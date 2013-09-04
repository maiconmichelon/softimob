package br.com.michelon.softimob.tela.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.jasperassistant.designer.viewer.ReportViewer;
import com.jasperassistant.designer.viewer.ViewerComposite;

public class ReportView extends ViewPart {

	public static final String ID = "br.com.michelon.softimob.tela.view.ReportView"; //$NON-NLS-1$

	public ReportView() {
	}

	// Using the SWTJasperViewer
	private ViewerComposite viewerComposite;

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);

		viewerComposite = new ViewerComposite(container, SWT.BORDER);
		viewerComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));

	}

	public ReportViewer getReportViewer() {
		return (ReportViewer) viewerComposite.getReportViewer();
	}

	public Composite getReportViewerComposite() {
		return viewerComposite;
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	
}
