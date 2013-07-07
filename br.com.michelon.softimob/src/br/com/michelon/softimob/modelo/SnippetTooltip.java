package br.com.michelon.softimob.modelo;

/*
 * Tool Tips example snippet: create fake tool tips for items in a Gallery
 *
 * This is almost the same snippet than Snippet 125 for Table.
 * 
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

public class SnippetTooltip {

public static void main (String[] args) {

	final Display display = new Display ();
	final Shell shell = new Shell (display);
	shell.setLayout (new FillLayout ());
	Image itemImage = new Image(display, Program
			.findProgram("jpg").getImageData()); //$NON-NLS-1$
	final Gallery gallery = new Gallery (shell, SWT.V_SCROLL| SWT.BORDER);

	GalleryItem group = new GalleryItem (gallery, SWT.NONE);
	group.setText("Group"); //$NON-NLS-1$
	group.setExpanded(true);
	
	for (int i = 0; i < 20; i++) {
		GalleryItem item = new GalleryItem (group, SWT.NONE);
		item.setText ("item " + i); //$NON-NLS-1$
		item.setImage(itemImage);
	}
	// Disable native tooltip
	gallery.setToolTipText (""); //$NON-NLS-1$
	
	// Implement a "fake" tooltip
	final Listener labelListener = new Listener () {
		public void handleEvent (Event event) {
			Label label = (Label)event.widget;
			Shell shell = label.getShell ();
			switch (event.type) {
				case SWT.MouseDown:
					Event e = new Event ();
					e.item = (TableItem) label.getData ("_TABLEITEM"); //$NON-NLS-1$
					// Assuming Gallery is single select, set the selection as if
					// the mouse down event went through to the table
					gallery.setSelection (new GalleryItem [] {(GalleryItem) e.item});
					gallery.notifyListeners (SWT.Selection, e);
					shell.dispose ();
					gallery.setFocus();
					break;
				case SWT.MouseExit:
					shell.dispose ();
					break;
			}
		}
	};
	
	Listener tableListener = new Listener () {
		Shell tip = null;
		Label label = null;
		public void handleEvent (Event event) {
			switch (event.type) {
				case SWT.Dispose:
				case SWT.KeyDown:
				case SWT.MouseMove: {
					if (tip == null) break;
					tip.dispose ();
					tip = null;
					label = null;
					break;
				}
				case SWT.MouseHover: {
					GalleryItem item = gallery.getItem (new Point (event.x, event.y));
					if (item != null) {
						if (tip != null  && !tip.isDisposed ()) tip.dispose ();
						tip = new Shell (shell, SWT.ON_TOP | SWT.NO_FOCUS | SWT.TOOL);
						tip.setBackground (display.getSystemColor (SWT.COLOR_INFO_BACKGROUND));
						FillLayout layout = new FillLayout ();
						layout.marginWidth = 2;
						tip.setLayout (layout);
						label = new Label (tip, SWT.NONE);
						label.setForeground (display.getSystemColor (SWT.COLOR_INFO_FOREGROUND));
						label.setBackground (display.getSystemColor (SWT.COLOR_INFO_BACKGROUND));
						label.setData ("_TABLEITEM", item); //$NON-NLS-1$
						label.setText (item.getText ());
						label.addListener (SWT.MouseExit, labelListener);
						label.addListener (SWT.MouseDown, labelListener);
						Point size = tip.computeSize (SWT.DEFAULT, SWT.DEFAULT);
					
						Point pt = gallery.toDisplay (event.x, event.y + 16);
						tip.setBounds (pt.x, pt.y, size.x, size.y);
						tip.setVisible (true);
					}
				}
			}
		}
	};
	gallery.addListener (SWT.Dispose, tableListener);
	gallery.addListener (SWT.KeyDown, tableListener);
	gallery.addListener (SWT.MouseMove, tableListener);
	gallery.addListener (SWT.MouseHover, tableListener);
	shell.pack ();
	shell.open();
	while (!shell.isDisposed()) {
		if (!display.readAndDispatch())
			display.sleep();
	}
	display.dispose();
}
}