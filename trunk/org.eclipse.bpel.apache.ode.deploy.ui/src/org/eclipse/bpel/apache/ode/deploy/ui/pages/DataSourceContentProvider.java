package org.eclipse.bpel.apache.ode.deploy.ui.pages;

import org.eclipse.bpel.apache.ode.deploy.model.dd.TDatasource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class DataSourceContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		@SuppressWarnings("unchecked")
		EList<TDatasource> datasources = (EList<TDatasource>) inputElement;
		return datasources.toArray();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}


}
