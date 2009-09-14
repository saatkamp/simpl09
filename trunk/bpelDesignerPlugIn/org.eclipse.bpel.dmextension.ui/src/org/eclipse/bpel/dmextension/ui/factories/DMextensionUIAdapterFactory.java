package org.eclipse.bpel.dmextension.ui.factories;

import org.eclipse.bpel.dmextension.model.util.ModelAdapterFactory;
import org.eclipse.bpel.dmextension.ui.adapters.CallProcedureActivityAdapter;
import org.eclipse.bpel.dmextension.ui.adapters.CreateActivityAdapter;
import org.eclipse.bpel.dmextension.ui.adapters.DataManagementActivityAdapter;
import org.eclipse.bpel.dmextension.ui.adapters.DeleteActivityAdapter;
import org.eclipse.bpel.dmextension.ui.adapters.InsertActivityAdapter;
import org.eclipse.bpel.dmextension.ui.adapters.RetrieveSetActivityAdapter;
import org.eclipse.bpel.dmextension.ui.adapters.SelectActivityAdapter;
import org.eclipse.bpel.dmextension.ui.adapters.UpdateActivityAdapter;
import org.eclipse.bpel.dmextension.ui.adapters.WriteBackActivityAdapter;
import org.eclipse.emf.common.notify.Adapter;

public class DMextensionUIAdapterFactory extends ModelAdapterFactory{

	private DataManagementActivityAdapter dataManagementActivityAdapter;
	private CallProcedureActivityAdapter callProcedureActivityAdapter;
	private CreateActivityAdapter createActivityAdapter;
	private DeleteActivityAdapter deleteActivityAdapter;
	private InsertActivityAdapter insertActivityAdapter;
	private RetrieveSetActivityAdapter retrieveSetActivityAdapter;
	private SelectActivityAdapter selectActivityAdapter;
	private UpdateActivityAdapter updateActivityAdapter;
	private WriteBackActivityAdapter writeBackActivityAdapter;
	
	@Override
	public Adapter createDataManagementActivityAdapter() {
		if (this.dataManagementActivityAdapter == null) {
			this.dataManagementActivityAdapter = new DataManagementActivityAdapter();
		}
		return this.dataManagementActivityAdapter;
	}
	
	@Override
	public Adapter createCallProcedureActivityAdapter() {
		if (this.callProcedureActivityAdapter == null) {
			this.callProcedureActivityAdapter = new CallProcedureActivityAdapter();
		}
		return this.dataManagementActivityAdapter;
	}
	
	@Override
	public Adapter createCreateActivityAdapter() {
		if (this.createActivityAdapter == null) {
			this.createActivityAdapter = new CreateActivityAdapter();
		}
		return this.dataManagementActivityAdapter;
	}
	
	@Override
	public Adapter createDeleteActivityAdapter() {
		if (this.deleteActivityAdapter == null) {
			this.deleteActivityAdapter = new DeleteActivityAdapter();
		}
		return this.dataManagementActivityAdapter;
	}
	
	@Override
	public Adapter createInsertActivityAdapter() {
		if (this.insertActivityAdapter == null) {
			this.insertActivityAdapter = new InsertActivityAdapter();
		}
		return this.dataManagementActivityAdapter;
	}
	
	@Override
	public Adapter createRetrieveSetActivityAdapter() {
		if (this.retrieveSetActivityAdapter == null) {
			this.retrieveSetActivityAdapter = new RetrieveSetActivityAdapter();
		}
		return this.dataManagementActivityAdapter;
	}
	
	@Override
	public Adapter createSelectActivityAdapter() {
		if (this.selectActivityAdapter == null) {
			this.selectActivityAdapter = new SelectActivityAdapter();
		}
		return this.dataManagementActivityAdapter;
	}
	
	@Override
	public Adapter createUpdateActivityAdapter() {
		if (this.updateActivityAdapter == null) {
			this.updateActivityAdapter = new UpdateActivityAdapter();
		}
		return this.dataManagementActivityAdapter;
	}
	
	@Override
	public Adapter createWriteBackActivityAdapter() {
		if (this.writeBackActivityAdapter == null) {
			this.writeBackActivityAdapter = new WriteBackActivityAdapter();
		}
		return this.dataManagementActivityAdapter;
	}
}
