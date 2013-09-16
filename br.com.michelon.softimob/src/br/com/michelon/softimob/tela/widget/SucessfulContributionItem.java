package br.com.michelon.softimob.tela.widget;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.LegacyActionTools;
import org.eclipse.jface.action.StatusLineLayoutData;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.ResourceManager;

public class SucessfulContributionItem extends ContributionItem {

	private static SucessfulContributionItem sci;
	private final static int DEFAULT_CHAR_WIDTH = 40;
	public final static int CALC_TRUE_WIDTH = -1;
	
	private Logger log = Logger.getLogger(getClass());
	
	private int charWidth;
	private CLabel label;
	private Composite statusLine = null;
	private String text = Util.ZERO_LENGTH_STRING;
	private int widthHint = -1;
	private int heightHint = -1;

	private SucessfulContributionItem(String id) {
		this(id, DEFAULT_CHAR_WIDTH);
	}

	private SucessfulContributionItem(String id, int charWidth) {
		super(id);
		this.charWidth = charWidth;
		setVisible(false); // no text to start with
	}

	public static SucessfulContributionItem getInstance(){
		if(sci == null)
			sci = new SucessfulContributionItem("SUCESSFULL_MESSAGEAREA");
		return sci;
	}
	
	public void fill(Composite parent) {
		statusLine = parent;

		Label sep = new Label(parent, SWT.SEPARATOR);
		label = new CLabel(statusLine, SWT.SHADOW_NONE);
		label.setText(text);		
		
		if (charWidth == CALC_TRUE_WIDTH) {
			// compute the size of the label to get the width hint for the contribution
			Point preferredSize = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			widthHint = preferredSize.x;
			heightHint = preferredSize.y;
		} else if (widthHint < 0) {
			// Compute the size base on 'charWidth' average char widths
			GC gc = new GC(statusLine);
			gc.setFont(statusLine.getFont());
			FontMetrics fm = gc.getFontMetrics();
			widthHint = fm.getAverageCharWidth() * charWidth;
			heightHint = fm.getHeight();
			gc.dispose();
		}

		StatusLineLayoutData data = new StatusLineLayoutData();
		data.widthHint = widthHint;
		label.setLayoutData(data);

		data = new StatusLineLayoutData();
		data.heightHint = heightHint;
		sep.setLayoutData(data);
	}

	public Point getDisplayLocation() {
		if ((label != null) && (statusLine != null)) {
			return statusLine.toDisplay(label.getLocation());
		}

		return null;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		Assert.isNotNull(text);

		this.text = LegacyActionTools.escapeMnemonics(text);

		if (label != null && !label.isDisposed()) {
			label.setText(this.text);
		}

		if (this.text.length() == 0) {
			if (isVisible()) {
				setVisible(false);
				IContributionManager contributionManager = getParent();

				if (contributionManager != null) {
					contributionManager.update(true);
				}
			}
		} else {
			if (!isVisible() || charWidth == CALC_TRUE_WIDTH) {
				setVisible(true);
				IContributionManager contributionManager = getParent();

				if (contributionManager != null) {
					contributionManager.update(true);
				}
			}
		}
	}
	
	public void setSucessfulMessage(final String message){
		new Thread(new Runnable() {
			@Override
			public void run() {
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						setText(message);
						label.setBackground(ResourceManager.getColor(SWT.COLOR_DARK_GREEN));
					}
				});
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					log.error("Erro ao pausar thread para mostrar mensagem na StatusLine.", e);
				}
				
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						setText(StringUtils.EMPTY);
					}
				});
			}
		}).start();
		
	}
	
}
