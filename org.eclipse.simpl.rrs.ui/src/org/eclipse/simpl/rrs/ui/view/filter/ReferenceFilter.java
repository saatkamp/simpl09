package org.eclipse.simpl.rrs.ui.view.filter;

import java.util.regex.PatternSyntaxException;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.uni_stuttgart.simpl.rrs.EPR;

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
		try {
			if (epr.getReferenceParameters().getReferenceName().matches(searchString)) {
				return true;
			}
			if (epr.getAddress().matches(searchString)) {
				return true;
			}
			if (epr.getReferenceProperties().getResolutionSystem().matches(searchString)) {
				return true;
			}
			if (epr.getReferenceParameters().getDsAddress().matches(searchString)) {
				return true;
			}
			if (epr.getReferenceParameters().getStatement().matches(searchString)) {
				return true;
			}
		}catch (PatternSyntaxException e) {
			e.printStackTrace();
		}
		return false;
	}
}
