package org.eclipse.bpel.apache.ode.deploy.ui.pages;

import org.eclipse.bpel.apache.ode.deploy.model.dd.TDatasource;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class DataSourceLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		TDatasource datasource = (TDatasource) element;
		
		switch (columnIndex) {
		case 0:
			return datasource.getDataSourceName();
		case 1:
			return datasource.getAddress();
		case 2:
			return datasource.getType();
		case 3:
			return datasource.getSubtype();
		case 4:
			return datasource.getLanguage();
		case 5:
			return datasource.getFormat();
		case 6:
			return datasource.getUserName();
		case 7:
			return datasource.getPassword().replaceAll(".", "*");
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
