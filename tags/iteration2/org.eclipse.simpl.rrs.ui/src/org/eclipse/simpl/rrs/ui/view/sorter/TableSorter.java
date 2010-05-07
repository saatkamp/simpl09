package org.eclipse.simpl.rrs.ui.view.sorter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.simpl.rrs.ui.client.EPR;

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
		EPR epr1 = (EPR) e1;
		EPR epr2 = (EPR) e2;
		int rc = 0;
		switch (propertyIndex) {
		case 0:
			rc = epr1.getReferenceParameters().getReferenceName().compareTo(epr2.getReferenceParameters().getReferenceName());
			break;
		case 1:
			rc = epr1.getAddress().compareTo(epr2.getAddress());
			break;
		case 2:
			rc = epr1.getReferenceProperties().getResolutionSystem().compareTo(epr2.getReferenceProperties().getResolutionSystem());
			break;
		case 3:
			rc = epr1.getReferenceParameters().getDsAddress().compareTo(epr2.getReferenceParameters().getDsAddress());
			break;
		case 4:
			rc = epr1.getReferenceParameters().getStatement().compareTo(epr2.getReferenceParameters().getStatement());
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
