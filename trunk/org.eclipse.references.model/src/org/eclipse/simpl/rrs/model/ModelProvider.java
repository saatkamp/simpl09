package org.eclipse.simpl.rrs.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.simpl.rrs.model.reference.Address;
import org.eclipse.simpl.rrs.model.reference.EPR;
import org.eclipse.simpl.rrs.model.reference.RRSAdapter;
import org.eclipse.simpl.rrs.model.reference.ReferenceFactory;
import org.eclipse.simpl.rrs.model.reference.ReferenceParameters;
import org.eclipse.simpl.rrs.model.reference.ReferenceProperties;

public class ModelProvider {

	private static ModelProvider content;
	private List<EPR> references;

	private ModelProvider() {
		references = new ArrayList<EPR>();
		// Image here some fancy database access to read the EPRs and to
		// put them into the model
		ReferenceFactory factory = ReferenceFactory.eINSTANCE;
		/*
		 * EPR = {reference.getReferenceParameters().getReferenceName(),
		 *     reference.getAddress().getUri(),
		 *     reference.getReferenceProperties().getResolutionSystem().getAdapterURI(),
		 *     reference.getReferenceParameters().getStatement()}
		 */

		EPR ref1 = factory.createEPR();
		ReferenceParameters param1 = factory.createReferenceParameters();
		ReferenceProperties prop1 = factory.createReferenceProperties();
		RRSAdapter rrs1 = factory.createRRSAdapter();
		rrs1.setAdapterURI("RDB-Adapter");
		prop1.setResolutionSystem(rrs1);
		param1.setReferenceName("simulationsDaten");
		param1.setStatement("SELECT * FROM simdaten");
		Address addr1 = factory.createAddress();
		addr1.setUri("http://localhost:8080/rrs");
		ref1.setReferenceParameters(param1);
		ref1.setReferenceProperties(prop1);
		ref1.setAddress(addr1);

		EPR ref2 = factory.createEPR();
		ReferenceParameters param2 = factory.createReferenceParameters();
		ReferenceProperties prop2 = factory.createReferenceProperties();
		RRSAdapter rrs2 = factory.createRRSAdapter();
		rrs2.setAdapterURI("MySQL-Adapter");
		prop2.setResolutionSystem(rrs2);
		param2.setReferenceName("visualisierungsDaten");
		param2.setStatement("SELECT * FROM visdaten");
		Address addr2 = factory.createAddress();
		addr2.setUri("http://localhost:8080/rrs");
		ref2.setReferenceParameters(param2);
		ref2.setReferenceProperties(prop2);
		ref2.setAddress(addr2);
		
		references.add(ref1);
		references.add(ref2);
	}

	public static synchronized ModelProvider getInstance() {
		if (content != null) {
			return content;
		}
		content = new ModelProvider();
		return content;
	}
	
	public EPR find(String id){
		EPR found = null;
		for (EPR ref : references){
			if (ref.toString().equals(id)){
				found = ref;
				continue;
			}
		}
		return found;
	}

	public List<EPR> getReferences() {
		return references;
	}

}
