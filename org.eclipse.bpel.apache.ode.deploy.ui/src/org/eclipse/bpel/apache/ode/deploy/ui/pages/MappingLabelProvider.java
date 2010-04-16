package org.eclipse.bpel.apache.ode.deploy.ui.pages;

import org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
public class MappingLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		TActivityMapping mapping = (TActivityMapping) element;
		
		switch (columnIndex) {
		case 0:
			return mapping.getActivity();
		case 1:
			return mapping.getDataSourceName();
		case 2:
			return mapping.getPolicy().getLocalPath();
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
