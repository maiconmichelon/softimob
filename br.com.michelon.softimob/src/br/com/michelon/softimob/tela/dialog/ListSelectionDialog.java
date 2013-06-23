package br.com.michelon.softimob.tela.dialog;

import java.util.Arrays;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.AbstractElementListSelectionDialog;

public class ListSelectionDialog extends AbstractElementListSelectionDialog{

	private Object[] fElements;

    /**
     * Creates a list selection dialog.
     * @param parent   the parent widget.
     * @param renderer the label renderer.
     */
    public ListSelectionDialog(Shell parent, ILabelProvider renderer) {
        super(parent, renderer);
    }

    /**
     * Sets the elements of the list.
     * @param elements the elements of the list.
     */
    public void setElements(Object[] elements) {
        fElements = elements;
    }

    /*
     * @see SelectionStatusDialog#computeResult()
     */
    @Override
	protected void computeResult() {
        setResult(Arrays.asList(getSelectedElements()));
    }

    /*
     * @see Dialog#createDialogArea(Composite)
     */
    @Override
	protected Control createDialogArea(Composite parent) {
        Composite contents = (Composite) super.createDialogArea(parent);

        createFilterText(contents);
        createFilteredList(contents);

        setListElements(fElements);

        setSelection(getInitialElementSelections().toArray());

        return contents;
    }
    
    @Override
	protected void handleEmptyList() {}
    
}
