package org.simpl.rrs.retrieval;

import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import org.simpl.rrs.RRS;
import org.simpl.rrs.dsadapter.DSAdapter;
import org.simpl.rrs.webservices.EPR;

public class RetrievalServiceImpl implements RetrievalService {

	public Object get(EPR epr) {

		LinkedHashMap<String, String> EPRHM = EPRtoHM(epr);
		System.out.println(EPRHM.get("dsAddress"));

		String dsType = null;
		String dsSubtype = null;
		@SuppressWarnings("unused")
		String dsLanguage = null;

		StringTokenizer st = new StringTokenizer(EPRHM.get("adapterType"), ":");
		dsType = st.nextToken();
		dsSubtype = st.nextToken();
		dsLanguage = st.nextToken();

		// Hier der ganze selbe Mist wie bei DatasourceService, Adapter
		// auswählen
		// anhand von Type und Subtype, und Daten holen...
		DSAdapter dsAdapter = RRS.getInstance().dsAdapter(dsType, dsSubtype);

		Object data = dsAdapter.retrieveData(epr.getReferenceParameters().getDsAddress(), epr.getReferenceParameters().getStatement());
		
		return data;

	}

	public LinkedHashMap<String, String> EPRtoHM(EPR epr) {
		LinkedHashMap<String, String> HM = new LinkedHashMap<String, String>();

		HM.put("referenceName",
				epr.getReferenceParameters().getReferenceName() == null ? ""
						: epr.getReferenceParameters().getReferenceName());
		HM.put("address", epr.getAddress() == null ? "" : epr.getAddress().toString());
		HM.put("adapterType", epr.getReferenceProperties()
				.getResolutionSystem() == null ? "" : epr
				.getReferenceProperties().getResolutionSystem());
		HM.put("statement",
				epr.getReferenceParameters().getStatement() == null ? "" : epr
						.getReferenceParameters().getStatement());
		HM.put("dsAddress",
				epr.getReferenceParameters().getDsAddress() == null ? "" : epr
						.getReferenceParameters().getDsAddress());
		HM.put("portType", epr.getPortType() == null ? "" : epr.getPortType()
				.toString());
		if (epr.getServiceName() != null) {
			HM.put("serviceName", epr.getServiceName().getQName() == null ? ""
					: epr.getServiceName().getQName().toString());
			HM.put("portName", epr.getServiceName().getPortName() == null ? ""
					: epr.getServiceName().getPortName().toString());
		} else {
			HM.put("serviceName", "");
			HM.put("portName", "");
		}
		HM.put("policy", epr.getPolicy() == null ? "" : epr.getPolicy());

		return HM;
	}
}
