package br.com.michelon.softimob.aplicacao.helper.listElementDialog;

import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SnippetSimple {

	public static void main(String[] args) {
		Display display = new Display();
		Image itemImage = new Image(display, Program
				.findProgram("jpg").getImageData()); //$NON-NLS-1$

		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Gallery gallery = new Gallery(shell, SWT.V_SCROLL | SWT.MULTI);

		// Renderers
		DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemHeight(56);
		gr.setItemWidth(72);
		gr.setAutoMargin(true);
		gallery.setGroupRenderer(gr);

		DefaultGalleryItemRenderer ir = new DefaultGalleryItemRenderer();
		gallery.setItemRenderer(ir);

		for (int g = 0; g < 2; g++) {
			GalleryItem group = new GalleryItem(gallery, SWT.NONE);
			group.setText("Group " + g); //$NON-NLS-1$
			group.setExpanded(true);

			for (int i = 0; i < 50; i++) {
				GalleryItem item = new GalleryItem(group, SWT.NONE);
				if (itemImage != null) {
					item.setImage(itemImage);
				}
				item.setText("Item " + i); //$NON-NLS-1$
			}
		}

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		if (itemImage != null)
			itemImage.dispose();
		display.dispose();
	}
}