package br.com.michelon.softimob.aplicacao.main;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import da.Departamento;
import da.Rua;

import antlr.StringUtils;
import br.com.michelon.softimob.aplicacao.service.DepartamentoService;
import br.com.michelon.softimob.persistencia.DepartamentoDAO;
import br.com.michelon.softimob.persistencia.SpringUtils;

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
		
//		SpringUtils.initializeContext();
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		DepartamentoDAO dao = ctx.getBean(DepartamentoDAO.class);		
		
		Departamento d = new Departamento();
		
		d.setNome("huhausdgasud");
		try {
			dao.save(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void postWindowOpen() {
		addPartListener();
	}

	private void addPartListener() {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().addPartListener(new IPartListener() {
			
			@Override
			public void partOpened(IWorkbenchPart part) {}
			
			@Override
			public void partDeactivated(IWorkbenchPart part) {}
			
			@Override
			public void partClosed(IWorkbenchPart part) {
				IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				
				if(activePage == null)
					return;

				IEditorReference[] refs = activePage.getEditorReferences();
				if(refs.length == 0)
					activePage.setEditorAreaVisible(false);
			}
			
			@Override
			public void partBroughtToTop(IWorkbenchPart part) {}
			
			@Override
			public void partActivated(IWorkbenchPart part) {}
			
		});
	}
	
}
