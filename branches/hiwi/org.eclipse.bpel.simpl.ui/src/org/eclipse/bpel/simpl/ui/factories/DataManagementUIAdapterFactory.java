/**
 * <b>Purpose:</b> This factory is used to create {@link ActivityAdapter}s for every {@link DataManagementActivity}.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.factories;

import org.eclipse.bpel.simpl.model.util.ModelAdapterFactory;
import org.eclipse.bpel.simpl.ui.adapters.IssueActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.QueryActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.RetrieveDataActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.TransferActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.WriteDataBackActivityAdapter;
import org.eclipse.emf.common.notify.Adapter;

public class DataManagementUIAdapterFactory extends ModelAdapterFactory{

	private QueryActivityAdapter queryActivityAdapter;
	private IssueActivityAdapter issueActivityAdapter;
	private RetrieveDataActivityAdapter retrieveDataActivityAdapter;
	private WriteDataBackActivityAdapter writeDataBackActivityAdapter;
	private TransferActivityAdapter transferActivityAdapter;
	
	static private DataManagementUIAdapterFactory instance;
	
	/**
	 * Get the instance of the factory.
	 * 
	 * @return an instance of this DataManagement UI factory
	 */
	
	public static DataManagementUIAdapterFactory getInstance() {
		if (instance == null) {
			instance = new DataManagementUIAdapterFactory();
		}
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.model.util.ModelAdapterFactory#createQueryActivityAdapter()
	 */
	@Override
	public Adapter createQueryActivityAdapter() {
		if (this.queryActivityAdapter == null) {
			this.queryActivityAdapter = new QueryActivityAdapter();
		}
		return this.queryActivityAdapter;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.model.util.ModelAdapterFactory#createissueActivityAdapter()
	 */
	@Override
	public Adapter createIssueActivityAdapter() {
		if (this.issueActivityAdapter == null) {
			this.issueActivityAdapter = new IssueActivityAdapter();
		}
		return this.issueActivityAdapter;
	}
		
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.model.util.ModelAdapterFactory#createRetrieveDataActivityAdapter()
	 */
	@Override
	public Adapter createRetrieveDataActivityAdapter() {
		if (this.retrieveDataActivityAdapter == null) {
			this.retrieveDataActivityAdapter = new RetrieveDataActivityAdapter();
		}
		return this.retrieveDataActivityAdapter;
	}

  /* (non-Javadoc)
   * @see org.eclipse.bpel.simpl.model.util.ModelAdapterFactory#createWriteDataBackActivityAdapter()
   */
  @Override
  public Adapter createWriteDataBackActivityAdapter() {
    if (this.writeDataBackActivityAdapter == null) {
      this.writeDataBackActivityAdapter = new WriteDataBackActivityAdapter();
    }
    return this.writeDataBackActivityAdapter;
  }
	
	@Override
	public Adapter createTransferActivityAdapter() {
		if (this.transferActivityAdapter == null) {
			this.transferActivityAdapter = new TransferActivityAdapter();
		}
		return this.transferActivityAdapter;
	}
}
