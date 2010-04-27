/**
 * <b>Purpose:</b> A {@link BPELPropertySection} for SIMPL {@link DataManagementActivity}s.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.properties;

import org.eclipse.bpel.ui.properties.BPELPropertySection;

public abstract class DMActivityPropertySection extends BPELPropertySection {

	/** The statement. */
	public String statement;

	/**
	 * Opens a statement editor shell.
	 * 
	 * @param eClass
	 *            the e class
	 * @param language
	 *            the language
	 */
	public void openStatementEditor(String eClass, String language) {
		System.out.println("StatementEditor: " + eClass + " : " + language);
		new StatementEditor(this, language, eClass);
	}

	/**
	 * Sets the statement.
	 * 
	 * @param statement
	 *            the new statement
	 */
	public abstract void setStatement(String statement);

	/**
	 * Gets the statement.
	 * 
	 * @return the statement
	 */
	public abstract String getStatement();

	/**
	 * Saves the statement to the model.
	 */
	public abstract void saveStatementToModel();

}
