package org.eclipse.simpl.core.extensions;

import org.eclipse.swt.widgets.Composite;

public abstract class AAdminConsoleComposite implements IAdminConsoleComposite {

	private Composite composite = null;

	@Override
	public Composite getComposite() {
		// TODO Auto-generated method stub
		return this.composite;
	}

	@Override
	public void setComposite(Composite composite) {
		// TODO Auto-generated method stub
		this.composite = composite;
	}

}
