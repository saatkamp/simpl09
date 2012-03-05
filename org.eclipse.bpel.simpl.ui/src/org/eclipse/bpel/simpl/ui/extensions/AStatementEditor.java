/**
 * <b>Purpose:</b> Abstract class which have to be implemented by every StatementEditor extension contributor.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: AStatementEditor.java 1786 2011-04-05 11:24:45Z michael.schneidt@arcor.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.extensions;

import org.eclipse.swt.widgets.Composite;
import org.simpl.resource.management.data.DataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class AStatementEditor.        
 */
public abstract class AStatementEditor implements IStatementEditor {

	/**
	 * The composite of the statement editor.
	 */
	private Composite composite = null;
	
	private String statement = null;
	
	private DataSource dataSource = null;

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.ui.extensions.IStatementEditor#getComposite()
	 */
	@Override
	public Composite getComposite() {
		// TODO Auto-generated method stub
		return this.composite;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.ui.extensions.IStatementEditor#setComposite(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void setComposite(Composite composite) {
		// TODO Auto-generated method stub
		this.composite = composite;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.ui.extensions.IStatementEditor#getStatement()
	 */
	@Override
	public String getStatement() {
		return this.statement;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.ui.extensions.IStatementEditor#setStatement(java.util.HashMap)
	 */
	@Override
	public void setStatement(String statement) {
		this.statement = statement;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.ui.extensions.IStatementEditor#getDataSource()
	 */
	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.ui.extensions.IStatementEditor#setDataSource(org.eclipse.simpl.communication.client.DataSource)
	 */
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
