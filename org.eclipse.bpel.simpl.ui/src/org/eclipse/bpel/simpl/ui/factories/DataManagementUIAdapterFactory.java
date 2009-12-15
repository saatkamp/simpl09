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
	
	
	
	
	@Override
	public Adapter createQueryActivityAdapter() {
		if (this.queryActivityAdapter == null) {
			this.queryActivityAdapter = new QueryActivityAdapter();
		}
		return this.queryActivityAdapter;
	}
	
	@Override
	public Adapter createInsertActivityAdapter() {
		if (this.insertActivityAdapter == null) {
			this.insertActivityAdapter = new InsertActivityAdapter();
		}
		return this.insertActivityAdapter;
	}
	
	@Override
	public Adapter createUpdateActivityAdapter() {
		if (this.updateActivityAdapter == null) {
			this.updateActivityAdapter = new UpdateActivityAdapter();
		}
		return this.updateActivityAdapter;
	}
	
	@Override
	public Adapter createDeleteActivityAdapter() {
		if (this.deleteActivityAdapter == null) {
			this.deleteActivityAdapter = new DeleteActivityAdapter();
		}
		return this.deleteActivityAdapter;
	}
	
	@Override
	public Adapter createCreateActivityAdapter() {
		if (this.createActivityAdapter == null) {
			this.createActivityAdapter = new CreateActivityAdapter();
		}
		return this.createActivityAdapter;
	}
	
	@Override
	public Adapter createDropActivityAdapter() {
		if (this.dropActivityAdapter == null) {
			this.dropActivityAdapter = new DropActivityAdapter();
		}
		return this.dropActivityAdapter;
	}
	
	@Override
	public Adapter createCallActivityAdapter() {
		if (this.callActivityAdapter == null) {
			this.callActivityAdapter = new CallActivityAdapter();
		}
		return this.callActivityAdapter;
	}
	
	@Override
	public Adapter createRetrieveDataActivityAdapter() {
		if (this.retrieveDataActivityAdapter == null) {
			this.retrieveDataActivityAdapter = new RetrieveDataActivityAdapter();
		}
		return this.retrieveDataActivityAdapter;
	}
}
