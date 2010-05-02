package org.simpl.rrs.retrieval;


import java.util.LinkedHashMap;

import java.util.StringTokenizer;

import org.simpl.rrs.RRS;
import org.simpl.rrs.dsadapter.DSAdapter;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;

public class RetrievalServiceImpl implements RetrievalService {
	
	public Object get(LinkedHashMap<String, String> EPR) {

		String dsType = null;
		String dsSubtype = null;
		String dsLanguage = null;
		Object data = null;
		
		StringTokenizer st = new StringTokenizer(EPR.get("adapterType"), ":");
		dsType = st.nextToken();
		dsSubtype = st.nextToken();
		dsLanguage = st.nextToken();

		// Hier der ganze selbe Mist wie bei DatasourceService, Adapter
		// auswählen
		// anhand von Type und Subtype, und Daten holen...
		DSAdapter dsAdapter = RRS.getInstance().dsAdapter(dsType, dsSubtype);
		try {
			data = dsAdapter.retrieveData(EPR.get("Address"), EPR.get("Statement"));
					} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data);
		return data;

	}

}
