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
import org.eclipse.bpel.simpl.ui.adapters.CallActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.CreateActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.DeleteActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.InsertActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.RetrieveDataActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.QueryActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.UpdateActivityAdapter;
import org.eclipse.bpel.simpl.ui.adapters.DropActivityAdapter;
import org.eclipse.emf.common.notify.Adapter;

public class DataManagementUIAdapterFactory extends ModelAdapterFactory{

	private QueryActivityAdapter queryActivityAdapter;
	private InsertActivityAdapter insertActivityAdapter;
	private UpdateActivityAdapter updateActivityAdapter;
	private DeleteActivityAdapter deleteActivityAdapter;
	private CreateActivityAdapter createActivityAdapter;
	private DropActivityAdapter dropActivityAdapter;
	private CallActivityAdapter callActivityAdapter;
	private RetrieveDataActivityAdapter retrieveDataActivityAdapter;
	
	
	
	
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
	 * @see org.eclipse.bpel.simpl.model.util.ModelAdapterFactory#createInsertActivityAdapter()
	 */
	@Override
	public Adapter createInsertActivityAdapter() {
		if (this.insertActivityAdapter == null) {
			this.insertActivityAdapter = new InsertActivityAdapter();
		}
		return this.insertActivityAdapter;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.model.util.ModelAdapterFactory#createUpdateActivityAdapter()
	 */
	@Override
	public Adapter createUpdateActivityAdapter() {
		if (this.updateActivityAdapter == null) {
			this.updateActivityAdapter = new UpdateActivityAdapter();
		}
		return this.updateActivityAdapter;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.model.util.ModelAdapterFactory#createDeleteActivityAdapter()
	 */
	@Override
	public Adapter createDeleteActivityAdapter() {
		if (this.deleteActivityAdapter == null) {
			this.deleteActivityAdapter = new DeleteActivityAdapter();
		}
		return this.deleteActivityAdapter;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.model.util.ModelAdapterFactory#createCreateActivityAdapter()
	 */
	@Override
	public Adapter createCreateActivityAdapter() {
		if (this.createActivityAdapter == null) {
			this.createActivityAdapter = new CreateActivityAdapter();
		}
		return this.createActivityAdapter;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.model.util.ModelAdapterFactory#createDropActivityAdapter()
	 */
	@Override
	public Adapter createDropActivityAdapter() {
		if (this.dropActivityAdapter == null) {
			this.dropActivityAdapter = new DropActivityAdapter();
		}
		return this.dropActivityAdapter;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.model.util.ModelAdapterFactory#createCallActivityAdapter()
	 */
	@Override
	public Adapter createCallActivityAdapter() {
		if (this.callActivityAdapter == null) {
			this.callActivityAdapter = new CallActivityAdapter();
		}
		return this.callActivityAdapter;
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
}
