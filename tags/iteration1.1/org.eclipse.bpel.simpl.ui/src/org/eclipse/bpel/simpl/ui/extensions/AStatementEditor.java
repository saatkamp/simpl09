package org.eclipse.bpel.simpl.ui.extensions;

import org.eclipse.swt.widgets.Composite;

/**
 * @author hahnml
 * 
 *         Abstract class which have to be implemented by every StatementEditor
 *         extension contributor.
 */
public abstract class AStatementEditor implements IStatementEditor {

	/**
	 * The composite of the statement editor.
	 */
	private Composite composite = null;
	
	private String statement = null;

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

}
