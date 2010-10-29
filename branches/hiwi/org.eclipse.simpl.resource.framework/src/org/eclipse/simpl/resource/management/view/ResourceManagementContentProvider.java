package org.eclipse.simpl.resource.management.view;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.simpl.core.webservices.client.DataSource;

public class ResourceManagementContentProvider implements IStructuredContentProvider {
	@Override
	public Object[] getElements(Object inputElement) {
		@SuppressWarnings("unchecked")
		List<DataSource> references = (List<DataSource>) inputElement;
		
		return references.toArray();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}