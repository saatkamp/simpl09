package org.eclipse.simpl.settings.extensions;

import org.eclipse.swt.widgets.Composite;

public abstract class ASettingsComposite implements ISettingsComposite {

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
