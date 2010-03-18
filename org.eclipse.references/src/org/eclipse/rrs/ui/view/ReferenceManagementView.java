package org.eclipse.rrs.ui.view;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.rrs.model.ModelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;

/**
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 * 
 *         Erstellt nach folgender Anleitung:
 *         http://www.vogella.de/articles/EclipseJFaceTable/article.html
 */
public class ReferenceManagementView extends ViewPart {

	/**
	 * Constructor
	 */
	public ReferenceManagementView() {
		super();
	}

	private TableViewer viewer;

	public void createPartControl(Composite parent) {
		createViewer(parent);
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		viewer.setInput(ModelProvider.getInstance().getReferences());
	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION);
		createColumns(viewer);
		viewer.setContentProvider(new ReferenceContentProvider());
		viewer.setLabelProvider(new ReferenceLabelProvider());
	}

	// This will create the columns for the table
	private void createColumns(TableViewer viewer) {

		String[] titles = { "Name", "Address", "Ádapter", "Statement" };
		int[] bounds = { 150, 200, 100, 300 };

		for (int i = 0; i < titles.length; i++) {
			TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
			column.getColumn().setText(titles[i]);
			column.getColumn().setWidth(bounds[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(true);
		}
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}
