package org.eclipse.bpel.simpl.ui.extensions;

import java.util.HashMap;

import org.eclipse.swt.widgets.Composite;

public abstract class AStatementEditor implements IStatementEditor {

	private Composite composite = null;
	private HashMap<String, String> statement = null;

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
	
	@Override
	public HashMap<String, String> getStatement() {
		return this.statement;
	}
	
	@Override
	public void setStatement(HashMap<String, String> statement) {
		this.statement = statement;
	}

}
