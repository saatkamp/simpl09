package org.eclipse.rrs.ui.view;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.rrs.model.reference.EPR;

public class ReferenceContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		@SuppressWarnings("unchecked")
		List<EPR> references = (List<EPR>) inputElement;
		return references.toArray();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}


}
