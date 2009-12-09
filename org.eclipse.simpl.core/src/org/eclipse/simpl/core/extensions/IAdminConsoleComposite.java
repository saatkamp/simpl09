package org.eclipse.simpl.core.extensions;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

public interface IAdminConsoleComposite {
	
	public Composite getComposite();
	
	public void setComposite(Composite composite);
	
	public void createComposite(Composite composite);
	
	public void saveProperties(List<String> properties);
	
	public List<String> loadProperties();
	
	public List<String> loadDefaultProperties();
}
