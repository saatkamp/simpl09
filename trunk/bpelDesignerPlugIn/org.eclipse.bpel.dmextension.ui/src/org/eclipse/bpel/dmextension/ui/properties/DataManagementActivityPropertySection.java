package org.eclipse.bpel.dmextension.ui.properties;

import org.eclipse.bpel.common.ui.details.viewers.CComboViewer;
import org.eclipse.bpel.common.ui.flatui.FlatFormAttachment;
import org.eclipse.bpel.common.ui.flatui.FlatFormData;
import org.eclipse.bpel.common.ui.flatui.FlatFormLayout;
import org.eclipse.bpel.ui.IHelpContextIds;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.properties.BPELPropertySection;
import org.eclipse.bpel.ui.properties.EditController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.PlatformUI;

public class DataManagementActivityPropertySection extends BPELPropertySection {

	protected Label label;
	
	protected List list;
	
	protected Composite parentComposite;
	
	protected String[] items = new String[]{"UPDATE", "INSERT", "DELETE", "SELECT", "..."};
	
	/**
	 * Make this section use all the vertical space it can get. 
	 * 
	 */
	@Override
	public boolean shouldUseExtraSpace() { 
		return true;
	}
	
	protected void createDataManagementWidgets(Composite composite) {
		FlatFormData data;
		
		 // serializable
		 data = new FlatFormData();
		 label = getWidgetFactory().createLabel(composite, "Choose a SQL-Statement:");
		 data.left = new FlatFormAttachment(0, 0);
		 label.setLayoutData(data);
		 list = getWidgetFactory().createList(composite, 0);
		 list.setItems(items);
	}

	@Override
	protected void createClient(Composite parent) {
		Composite composite = parentComposite = fWidgetFactory.createComposite(parent, SWT.NONE);
		FlatFormLayout layout = new FlatFormLayout();
		layout.marginWidth = layout.marginHeight = 0;
		composite.setLayout(layout);

		createDataManagementWidgets(composite);
	}

}
