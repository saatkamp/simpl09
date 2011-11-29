/**
 * <b>Purpose:</b> This factory is used to create {@link ActivityAdapter}s for every {@link DataManagementActivity}.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: DataManagementUIAdapterFactory.java 1755 2011-01-17 16:16:42Z michael.schneidt@arcor.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.factories;

import org.eclipse.bpel.simpl.model.util.ModelAdapterFactory;
import org.eclipse.bpel.simpl.ui.adapters.IssueCommandActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.QueryActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.RetrieveDataActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.TransferDataActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.WriteDataBackActivityAdapter;
import org.eclipse.emf.common.notify.Adapter;

public class DataManagementUIAdapterFactory extends ModelAdapterFactory{

	private QueryActivityAdapter queryDataActivityAdapter;
	private IssueCommandActivityAdapter issueCommandActivityAdapter;
	private RetrieveDataActivityAdapter retrieveDataActivityAdapter;
	private WriteDataBackActivityAdapter writeDataBackActivityAdapter;
	private TransferDataActivityAdapter transferDataActivityAdapter;
	
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
	public Adapter createQueryDataActivityAdapter() {
		if (this.queryDataActivityAdapter == null) {
			this.queryDataActivityAdapter = new QueryActivityAdapter();
		}
		return this.queryDataActivityAdapter;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.model.util.ModelAdapterFactory#createissueActivityAdapter()
	 */
	@Override
	public Adapter createIssueCommandActivityAdapter() {
		if (this.issueCommandActivityAdapter == null) {
			this.issueCommandActivityAdapter = new IssueCommandActivityAdapter();
		}
		return this.issueCommandActivityAdapter;
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
	public Adapter createTransferDataActivityAdapter() {
		if (this.transferDataActivityAdapter == null) {
			this.transferDataActivityAdapter = new TransferDataActivityAdapter();
		}
		return this.transferDataActivityAdapter;
	}
}
