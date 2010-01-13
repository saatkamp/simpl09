package org.eclipse.bpel.simpl.ui.properties;

import org.eclipse.bpel.ui.properties.BPELPropertySection;

/**
 * A BPELPropertySection for SIMPL Data-Management-Activities.
 * 
 * @author hahnml
 *
 */
public abstract class DMActivityPropertySection extends BPELPropertySection{
	
	public String statement;
	
	/**
	 * Opens a statement editor shell.
	 */
	public void openStatementEditor(String eClass, String language){
		System.out.println("StatementEditor: " + eClass + " : " + language);
		new StatementEditor(this, language, eClass);
	}
	
	public abstract void setStatement(String statement);
	
	public abstract String getStatement();
	
	public abstract String loadStatementFromModel();
	
	public abstract void saveStatementToModel();

}
