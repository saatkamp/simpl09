package org.eclipse.bpel.simpl.ui.extensions;

import java.util.LinkedHashMap;

import org.eclipse.swt.widgets.Composite;

/**
 * @author hahnml
 * 
 *         The interface with all methods which have to be implemented by every
 *         StatementEditor extension contributor.
 */
public interface IStatementEditor {

	/**
	 * Returns the composite of a StatementEditor implementation.
	 * 
	 * @return the composite of the statement editor.
	 */
	public Composite getComposite();

	/**
	 * 
	 * 
	 * @param composite
	 */
	public void setComposite(Composite composite);

	/**
	 * Creates the composite of a statement editor.
	 * 
	 * @param composite
	 *            The parent composite in which the statement editor will be
	 *            embedded.
	 */
	public void createComposite(Composite composite);

	/**
	 * @return the statement as HashMap.
	 */
	public LinkedHashMap<String, String> getStatement();

	/**
	 * @param statement
	 */
	public void setStatement(LinkedHashMap<String, String> statement);

}
