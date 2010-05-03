package org.simpl.rrs.retrieval;

import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import org.simpl.rrs.RRS;
import org.simpl.rrs.dsadapter.DSAdapter;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;
import org.simpl.rrs.model.EPR;

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
			data = dsAdapter.retrieveData(EPRHM.get("Address"), EPRHM
					.get("Statement"));
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data);
		return data;

	}

	public LinkedHashMap<String, String> EPRtoHM(EPR epr) {
		LinkedHashMap<String, String> HM = new LinkedHashMap<String, String>();

		HM.put("referenceName",
				epr.getParameters().getReferenceName() == null ? "" : epr
						.getParameters().getReferenceName());
		HM.put("rrsAddress", epr.getRrsAddress() == null ? "" : epr
				.getRrsAddress());
		HM.put("adapterType", epr.getProperties().getRrsAdapter() == null ? ""
				: epr.getProperties().getRrsAdapter());
		HM.put("statement", epr.getParameters().getStatement() == null ? ""
				: epr.getParameters().getStatement());
		HM.put("dsAddress", epr.getParameters().getDsAddress() == null ? ""
				: epr.getParameters().getDsAddress());
		HM.put("portType", epr.getPortType() == null ? "" : epr.getPortType());
		if (epr.getService() != null) {
			HM.put("serviceName",
					epr.getService().getServiceName() == null ? "" : epr
							.getService().getServiceName());
			HM.put("portName", epr.getService().getPortName() == null ? ""
					: epr.getService().getPortName());
		} else {
			HM.put("serviceName", "");
			HM.put("portName", "");
		}
		HM.put("rrsPolicy", epr.getRrsPolicy() == null ? "" : epr
				.getRrsPolicy());

		return HM;
	}
}
