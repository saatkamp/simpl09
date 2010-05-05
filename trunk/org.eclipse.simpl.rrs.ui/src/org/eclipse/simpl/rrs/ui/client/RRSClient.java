package org.eclipse.simpl.rrs.ui.client;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.simpl.rrs.ui.RRSUIPlugIn;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
public class RRSClient {

	private static RRSClient client = null;

	private final String RRS_MG_WSDL = RRSUIPlugIn.getDefault()
			.getPreferenceStore().getString("RRS_MG_ADDRESS");

	RRSMetaDataService metaService = null;
	RRSRetrievalService retrievalService = null;
	RRSManagementService managementService = null;

	public static RRSClient getClient() {
		if (client == null) {
			client = new RRSClient();
			if (client.isRRSAvailable()) {
				client.metaService = new RRSMetaData()
						.getRRSMetaDataServicePort();
				client.retrievalService = new RRSRetrieval()
						.getRRSRetrievalServicePort();
				client.managementService = new RRSManagement()
						.getRRSManagementServicePort();
			}
		}
		if (client.isRRSAvailable()) {
			if (client.metaService == null) {
				client.metaService = new RRSMetaData()
						.getRRSMetaDataServicePort();
			}
			if (client.retrievalService == null) {
				client.retrievalService = new RRSRetrieval()
						.getRRSRetrievalServicePort();
			}
			if (client.managementService == null) {
				client.managementService = new RRSManagement()
						.getRRSManagementServicePort();
			}
		}
		return client;
	}

	// RRS Management Methods
	public boolean insertEPR(EPR epr) {
		if (RRSClient.getClient().isRRSAvailable()) {
			return managementService.insert(epr);
		}
		return false;
	}

	public boolean updateEPR(EPR epr) {
		if (RRSClient.getClient().isRRSAvailable()) {
			return managementService.update(epr);
		}
		return false;
	}

	public boolean deleteEPR(EPR epr) {
		if (RRSClient.getClient().isRRSAvailable()) {
			return managementService.delete(epr);
		}
		return false;
	}

	// RRS Meta-Data methods
	public List<EPR> getAllEPRs() {
		List<EPR> loadedRefs = new ArrayList<EPR>();

		if (RRSClient.getClient().isRRSAvailable()) {
			loadedRefs = metaService.getAllEPR().getItem();
		}

		return loadedRefs;
	}

	public String[] getAvailableRRSAdapters() {
		if (RRSClient.getClient().isRRSAvailable()) {
			return metaService.getAvailableAdapters().getItem().toArray(
					new String[0]);
		}
		
		return new String[0];
	}

	public boolean isRRSAvailable() {
		boolean isAvailable = false;
		URL url;
		try {
			url = new URL(RRS_MG_WSDL);
			URLConnection connection = url.openConnection();
			connection.connect();
			isAvailable = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAvailable;
	}
}
