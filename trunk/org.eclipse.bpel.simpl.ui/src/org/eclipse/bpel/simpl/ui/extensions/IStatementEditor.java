package org.eclipse.bpel.simpl.ui.extensions;

import java.util.HashMap;

import org.eclipse.swt.widgets.Composite;

public interface IStatementEditor {
	
	public Composite getComposite();
	
	public void setComposite(Composite composite);
	
	public void createComposite(Composite composite);
	
	public HashMap<String, String> getStatement();
	
	public void setStatement(HashMap<String, String> statement);
	
}
