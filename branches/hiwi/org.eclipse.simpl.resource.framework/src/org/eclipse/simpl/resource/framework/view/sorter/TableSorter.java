package org.eclipse.simpl.resource.framework.view.sorter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.simpl.core.webservices.client.DataSource;

public class TableSorter extends ViewerSorter {
	private int propertyIndex;
	// private static final int ASCENDING = 0;
	private static final int DESCENDING = 1;

	private int direction = DESCENDING;

	public TableSorter() {
		this.propertyIndex = 0;
		direction = DESCENDING;
	}

	public void setColumn(int column) {
		if (column == this.propertyIndex) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		DataSource ds1 = (DataSource) e1;
		DataSource ds2 = (DataSource) e2;
		int rc = 0;
		switch (propertyIndex) {
		case 0:
			rc = ds1.getName().compareTo(ds2.getName());
			break;
		case 1:
			rc = ds1.getAddress().compareTo(ds2.getAddress());
			break;
		case 2:
			rc = ds1.getType().compareTo(ds2.getType());
			break;
		case 3:
			rc = ds1.getSubType().compareTo(ds2.getSubType());
			break;
		default:
			rc = 0;
		}
		// If descending order, flip the direction
		if (direction == DESCENDING) {
			rc = -rc;
		}
		return rc;
	}
}
