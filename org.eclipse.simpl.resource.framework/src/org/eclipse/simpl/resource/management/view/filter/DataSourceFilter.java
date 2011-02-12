package org.eclipse.simpl.resource.management.view.filter;

import java.util.regex.PatternSyntaxException;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.simpl.resource.management.client.DataSource;

public class DataSourceFilter extends ViewerFilter {

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
		DataSource ds = (DataSource) element;
		
		try {
			if (ds.getName().matches(searchString)) {
				return true;
			}
			if (ds.getAddress().matches(searchString)) {
				return true;
			}
			if (ds.getType().matches(searchString)) {
				return true;
			}
			if (ds.getSubType().matches(searchString)) {
				return true;
			}
		}catch (PatternSyntaxException e) {
			e.printStackTrace();
		}
		return false;
	}
}
