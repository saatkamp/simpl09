package org.eclipse.simpl.resource.management.view.filter;

import java.util.regex.PatternSyntaxException;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.simpl.resource.management.data.DataSource;

public class DataSourceFilter extends ViewerFilter {

	private String searchString;

	public void setSearchText(String s) {
		// Search must be a substring of the existing value
		this.searchString = ".*" + s.toLowerCase() + ".*";
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchString == null || searchString.length() == 0) {
			return true;
		}
		DataSource ds = (DataSource) element;
		
		try {
			if (ds.getName().toLowerCase().matches(searchString)) {
				return true;
			}
			if (ds.getAddress().toLowerCase().matches(searchString)) {
				return true;
			}
			if (ds.getType().toLowerCase().matches(searchString)) {
				return true;
			}
			if (ds.getSubType().toLowerCase().matches(searchString)) {
				return true;
			}
	     if (ds.getLanguage().toLowerCase().matches(searchString)) {
	        return true;
	      }
	     if (ds.getAPIType().toLowerCase().matches(searchString)) {
	        return true;
	      }
	     if (ds.getDriverName().toLowerCase().matches(searchString)) {
         return true;
       }
	     if (ds.getAddressPrefix().toLowerCase().matches(searchString)) {
         return true;
       }
       if (ds.getConnector().getDataConverter().getWorkflowDataFormat().toLowerCase().matches(searchString)) {
         return true;
       }
		}catch (PatternSyntaxException e) {
			e.printStackTrace();
		}
		return false;
	}
}
