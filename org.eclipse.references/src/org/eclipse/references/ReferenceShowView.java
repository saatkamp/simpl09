package org.eclipse.references;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class ReferenceShowView extends ViewPart {

	private Label label;
	
	
	public ReferenceShowView() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		label = new Label(parent, 0);
        label.setText("Hello World");
	}

	@Override
	public void setFocus() {
		label.setFocus();
	}
}
