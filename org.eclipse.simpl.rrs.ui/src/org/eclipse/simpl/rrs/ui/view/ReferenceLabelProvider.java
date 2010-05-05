package org.eclipse.simpl.rrs.ui.view;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.simpl.rrs.ui.client.EPR;
import org.eclipse.swt.graphics.Image;

public class ReferenceLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	// // We use icons
	// // We use icons
	// private static final Image CHECKED = Activator.getImageDescriptor(
	// "icons/checked.gif").createImage();
	// private static final Image UNCHECKED = Activator.getImageDescriptor(
	// "icons/unchecked.gif").createImage();

	// @Override
	// public Image getColumnImage(Object element, int columnIndex) {
	// // In case you don't like image just return null here
	// if (columnIndex == 3) {
	// if (((Person) element).isMarried()) {
	// return CHECKED;
	// } else {
	// return UNCHECKED;
	// }
	// }
	// return null;
	// }

	@Override
	public String getColumnText(Object element, int columnIndex) {
		EPR reference = (EPR) element;
		//TODO: Vorerst werden nur "Name der EPR", "Adapter zur Auflösung", "Statement"
		switch (columnIndex) {
		case 0:
			return reference.getReferenceParameters().getReferenceName();
		case 1:
			return reference.getAddress();
		case 2:
			return reference.getReferenceProperties().getResolutionSystem();
		case 3:
			return reference.getReferenceParameters().getDsAddress();
		case 4:
			return reference.getReferenceParameters().getStatement();
		default:
			throw new RuntimeException("Too much columns");
		}

	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
