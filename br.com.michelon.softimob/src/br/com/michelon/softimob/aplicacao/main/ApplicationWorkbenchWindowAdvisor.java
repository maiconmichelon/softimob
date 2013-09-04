package br.com.michelon.softimob.aplicacao.main;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import br.com.michelon.softimob.aplicacao.service.FuncionarioService;
import br.com.michelon.softimob.aplicacao.service.PendenciaService;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.ChamadoReforma;
import br.com.michelon.softimob.modelo.ContaPagarReceber;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.Pendencia;
import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.modelo.Reserva;
import br.com.michelon.softimob.tela.popup.notifier.NotificationType;
import br.com.michelon.softimob.tela.popup.notifier.NotifierDialog;
import br.com.michelon.softimob.tela.view.PgtoRecContaView;

import com.google.common.base.Function;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	private Logger log = Logger.getLogger(getClass());
	
	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	@Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1024, 690));

		IStatusLineManager statusLineManager = getWindowConfigurer().getActionBarConfigurer().getStatusLineManager();
		StatusLineContributionItem sciSpacer = new StatusLineContributionItem("spacer", 800);
		sciSpacer.setText(" ");
		statusLineManager.add(sciSpacer);
		statusLineManager.update(true);
		
		configurer.setShowStatusLine(true);
		configurer.setShowProgressIndicator(true);

		configurer.setTitle("SoftImob");
	}
	
	@Override
	public void postWindowOpen() {
		addPartListener();

		showNotifications();
	}

	private void showNotifications() {
		
		List<Funcionario> aniversariantes = new FuncionarioService().findAniversariantes();
		if(aniversariantes != null && !aniversariantes.isEmpty()){
			StringBuilder sb = new StringBuilder();
			
			for(Funcionario f : aniversariantes)
				sb.append(String.format("%s esta de aniversário.\n", f.getNome()));
				
			NotifierDialog.notify(String.format("Feliz Aniversário !\n%s Aniversariante(s) no dia.", aniversariantes.size()),
					sb.toString(), NotificationType.INFO);
		}
		
		List<Pendencia> pendencias = new PendenciaService().findPendencias();
		if(pendencias != null && !pendencias.isEmpty()){
			StringBuilder sb = new StringBuilder();
			
			Multimap<Class<?>,Pendencia> map = Multimaps.index(pendencias, new Function<Pendencia, Class<?>>() {
				@Override
				public Class<?> apply(Pendencia arg0) {
					return arg0.getClass();
				}
			});
			
			Collection<Pendencia> alugueis = map.get(Aluguel.class);
			if(!alugueis.isEmpty())
				sb.append(String.format("%s aluguél(is) para vencer.\n", alugueis.size()));
			
			Collection<Pendencia> contasPagarReceber = map.get(ContaPagarReceber.class);
			if(!contasPagarReceber.isEmpty())
				sb.append(String.format("%s conta(s) para pagar/receber.\n", contasPagarReceber.size()));
			
			Collection<Pendencia> contratoPrestacaoServico = map.get(ContratoPrestacaoServico.class);
			if(!contratoPrestacaoServico.isEmpty())
				sb.append(String.format("%s contrato(s) de prestação de serviço para vencer.\n", contratoPrestacaoServico.size()));

			Collection<Pendencia> chamadosReforma = map.get(ChamadoReforma.class);
			if(!chamadosReforma.isEmpty())
				sb.append(String.format("%s chamado(s) de reforma em aberto.\n", chamadosReforma.size()));
			
			Collection<Pendencia> reservas = map.get(Reserva.class);
			if(!reservas.isEmpty())
				sb.append(String.format("%s reservas(s) em para vencer.\n", reservas.size()));
			
			Collection<Pendencia> propostas = map.get(Proposta.class);
			if(!propostas.isEmpty())
				sb.append(String.format("%s propostas(s) em aberto.\n", propostas.size()));
			
			NotifierDialog.notify(String.format("Olá, você possui %s pendência(s) !", pendencias.size()), sb.toString(), NotificationType.WARN, new MouseAdapter() {
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					try {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(PgtoRecContaView.ID);
					} catch (PartInitException e1) {
						log.error("Erro ao abrir tela de pendencias.", e1);
					}
				}
			});
		}
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
