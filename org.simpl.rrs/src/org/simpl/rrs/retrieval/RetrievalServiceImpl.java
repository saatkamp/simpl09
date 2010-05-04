package org.simpl.rrs.retrieval;

import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import org.simpl.rrs.RRS;
import org.simpl.rrs.dsadapter.DSAdapter;
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

		data = dsAdapter.retrieveData(EPRHM.get("Address"), EPRHM
				.get("Statement"));

		System.out.println(data);
		return data;

	}

	public LinkedHashMap<String, String> EPRtoHM(EPR epr) {
		LinkedHashMap<String, String> HM = new LinkedHashMap<String, String>();

		HM.put("referenceName",
				epr.getReferenceParameters().getReferenceName() == null ? ""
						: epr.getReferenceParameters().getReferenceName());
		HM.put("address", epr.getAddress() == null ? "" : epr.getAddress());
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
			HM.put("serviceName", epr.getServiceName().getValue() == null ? ""
					: epr.getServiceName().getValue().toString());
			HM.put("portName", epr.getServiceName().getPortName() == null ? ""
					: epr.getServiceName().getPortName());
		} else {
			HM.put("serviceName", "");
			HM.put("portName", "");
		}
		HM.put("policy", epr.getPolicy() == null ? "" : epr.getPolicy());

		return HM;
	}
}
