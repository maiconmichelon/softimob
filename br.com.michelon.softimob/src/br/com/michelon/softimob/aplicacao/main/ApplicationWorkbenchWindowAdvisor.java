package br.com.michelon.softimob.aplicacao.main;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1024, 690));
		configurer.setShowCoolBar(true);
		configurer.setShowFastViewBars(true);
		configurer.setTitle("SoftImob");
	}
	
	@Override
	public void postWindowOpen() {
		setEditorAreaInvisble();
	}

	private void setEditorAreaInvisble() {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPerspectiveListener(new IPerspectiveListener() {

			public void perspectiveActivated(IWorkbenchPage page,IPerspectiveDescriptor perspective) {
			}

			public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor perspective, String changeId) {
				if (page.isEditorAreaVisible()) {
					IEditorReference[] editores = page.getEditorReferences();
					if (editores.length <= 0) {
						page.setEditorAreaVisible(false);
					}
				}
			}

		});
	}
	
}
