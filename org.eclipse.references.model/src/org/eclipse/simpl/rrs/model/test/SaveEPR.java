package org.eclipse.simpl.rrs.model.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.simpl.rrs.model.reference.Address;
import org.eclipse.simpl.rrs.model.reference.EPR;
import org.eclipse.simpl.rrs.model.reference.RRSAdapter;
import org.eclipse.simpl.rrs.model.reference.ReferenceFactory;
import org.eclipse.simpl.rrs.model.reference.ReferenceParameters;
import org.eclipse.simpl.rrs.model.reference.ReferenceProperties;
import org.eclipse.simpl.rrs.model.reference.util.ReferenceResourceFactoryImpl;

public class SaveEPR {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		ReferenceFactory factory = ReferenceFactory.eINSTANCE;
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

		//Speichern über eine XMIFactory
//		URI fileURI = URI.createFileURI("test/ref1-xmi.xml");
//		Resource resource = new XMIResourceFactoryImpl().createResource(fileURI);
//		resource.getContents().add( ref1 );
//		try {
//			resource.save(Collections.EMPTY_MAP);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		//Speichern über die ReferenceResourceFactory.
		//DIESE VARIANTE IST ZU BEVORZUGEN, DA HIER KEIN STÖRENDER XMI TAG EINGEFÜGT WIRD
		URI fileURI = URI.createFileURI("test/ref1.xml");
		Resource resource = new ReferenceResourceFactoryImpl().createResource(fileURI);
		resource.getContents().add( ref1 );
		Map<String, String> save_options = new HashMap<String, String>();
		save_options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		try {
			resource.save(save_options);
			resource.save(System.out, save_options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
