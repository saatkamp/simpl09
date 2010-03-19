package org.eclipse.simpl.rrs.ui.view.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.simpl.rrs.model.reference.EPR;

public class ReferenceFilter extends ViewerFilter {

	private String searchString;

	public void setSearchText(String s) {
		// Search must be a substring of the existing value
		this.searchString = ".*" + s + ".*";
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchString == null || searchString.length() == 0) {
			return true;
		}
		EPR epr = (EPR) element;
		if (epr.getReferenceParameters().getReferenceName().matches(searchString)) {
			return true;
		}
		if (epr.getAddress().getUri().matches(searchString)) {
			return true;
		}
		if (epr.getReferenceProperties().getResolutionSystem().getAdapterURI().matches(searchString)) {
			return true;
		}
		if (epr.getReferenceParameters().getStatement().matches(searchString)) {
			return true;
		}

		return false;
	}
}
