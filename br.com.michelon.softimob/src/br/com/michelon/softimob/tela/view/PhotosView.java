package br.com.michelon.softimob.tela.view;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.nebula.widgets.gallery.NoGroupRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import br.com.michelon.softimob.modelo.Foto;

public class PhotosView extends ViewPart {

	public static final String ID = "br.com.michelon.softimob.tela.view.PhotosView"; //$NON-NLS-1$
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private GalleryItem galleryItem;

	public PhotosView() {
	}

	public void setFotos(List<Foto> fotos) {
		for (Foto foto : fotos) {
			if(foto.getArquivoFoto() == null)
				continue;
			
			try {
				ByteArrayInputStream in = new ByteArrayInputStream(foto.getArquivoFoto().getArquivo());
				DataInputStream readIn = new DataInputStream(in);

				ImageData imdata = new ImageData(readIn);
				
				readIn.close();

				Image image = new Image(Display.getCurrent(), imdata);
				
				GalleryItem galleryItem_1 = new GalleryItem(galleryItem, SWT.NONE);
				galleryItem_1.setText(foto.getNome());
				galleryItem_1.setImage(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		galleryItem.setExpanded(true);
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(final Composite parent) {
		parent.setLayout(new FillLayout(SWT.VERTICAL));

		Composite composite = formToolkit.createComposite(parent, SWT.NONE);
		formToolkit.paintBordersFor(composite);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.marginWidth = 60;
		composite.setLayout(gl_composite);

		final Canvas canvas = new Canvas(composite, SWT.NONE);
		canvas.setLayout(new GridLayout(1, false));
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(canvas);
		formToolkit.paintBordersFor(canvas);

		Gallery gallery = new Gallery(parent, SWT.BORDER);
		gallery.setGroupRenderer(new NoGroupRenderer());
		gallery.setItemRenderer(new DefaultGalleryItemRenderer());

		galleryItem = new GalleryItem(gallery, SWT.NONE);
		galleryItem.setText("New Item");

		gallery.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Gallery gal = (Gallery) e.widget;

				if (gal.getSelectionCount() < 1)
					return;

				GalleryItem gi = gal.getSelection()[0];
				Image img = gi.getImage();

				GC gc = new GC(canvas);
				gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, 0, 0, canvas.getClientArea().width, canvas.getClientArea().height);
			}

		});

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
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
