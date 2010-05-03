package org.simpl.rrs.retrieval;


import java.util.LinkedHashMap;

import java.util.StringTokenizer;

import org.simpl.rrs.EPR;
import org.simpl.rrs.RRS;
import org.simpl.rrs.dsadapter.DSAdapter;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;

public class RetrievalServiceImpl implements RetrievalService {
	
	public Object get(EPR epr) {

		LinkedHashMap<String, String> EPRHM = EPRtoHM(epr);
		System.out.println(EPRHM.get("Address"));
		
		String dsType = null;
		String dsSubtype = null;
		String dsLanguage = null;
		Object data = null;
		
		StringTokenizer st = new StringTokenizer(EPRHM.get("adapterType"), ":");
		dsType = st.nextToken();
		dsSubtype = st.nextToken();
		dsLanguage = st.nextToken();

		// Hier der ganze selbe Mist wie bei DatasourceService, Adapter
		// auswählen
		// anhand von Type und Subtype, und Daten holen...
		DSAdapter dsAdapter = RRS.getInstance().dsAdapter(dsType, dsSubtype);
		try {
			data = dsAdapter.retrieveData(EPRHM.get("Address"), EPRHM.get("Statement"));
					} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data);
		return data;

	}
	
	public LinkedHashMap<String, String> EPRtoHM(EPR epr) {
		LinkedHashMap<String, String> HM = new LinkedHashMap<String, String>();
		
		HM.put("address", epr.getAddress());
		HM.put("adapterType", epr.getAdapterType());
		HM.put("referenceName", epr.getReferenceName());
		HM.put("Statement", epr.getStatement());
		HM.put("resolutionSystem", epr.getResolutionSystem());
		HM.put("portType", epr.getPortType());
		HM.put("portName", epr.getPortName());
		HM.put("rrsPolicy", epr.getRrsPolicy());
		
		return HM;
	}

}
