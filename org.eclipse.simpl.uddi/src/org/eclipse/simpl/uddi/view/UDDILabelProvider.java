package org.eclipse.simpl.uddi.view;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.simpl.communication.client.DataSource;
import org.eclipse.swt.graphics.Image;

public class UDDILabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		DataSource datasource = (DataSource) element;
		switch (columnIndex) {
		case 0:
			return datasource.getName();
		case 1:
			return datasource.getAddress();
		case 2:
			return datasource.getType();
		case 3:
			return datasource.getSubType();
		case 4:
			return datasource.getLanguage();
		case 5:
			return datasource.getDataFormat();
		case 6:
			return datasource.getAuthentication().getUser();
		case 7:
			return datasource.getAuthentication().getPassword().replaceAll(".", "*");
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
