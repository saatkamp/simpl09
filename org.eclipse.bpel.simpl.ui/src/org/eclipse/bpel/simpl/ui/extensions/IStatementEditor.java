/**
 * <b>Purpose:</b> The interface with all methods which have to be implemented by every StatementEditor extension contributor.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.extensions;

import org.eclipse.swt.widgets.Composite;
import org.simpl.resource.management.data.DataSource;


// TODO: Auto-generated Javadoc
/**
 * The Interface IStatementEditor.
 */
public interface IStatementEditor {

	/**
	 * Returns the composite of a StatementEditor implementation.
	 * 
	 * @return the composite of the statement editor.
	 */
	public Composite getComposite();

	/**
	 * Sets the composite of a StatementEditor implementation.
	 * 
	 * @param composite
	 *            the new composite
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
	 * Gets the statement.
	 * 
	 * @return the statement as String.
	 */
	public String getStatement();

	/**
	 * Sets the statement.
	 * 
	 * @param statement
	 *            the new statement
	 */
	public void setStatement(String statement);

	/**
	 * Sets the current selected dataSource to the composite class.
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource);
	
	/**
	 * Returns the dataSource object.
	 * 
	 * @return
	 */
	public DataSource getDataSource();
}
