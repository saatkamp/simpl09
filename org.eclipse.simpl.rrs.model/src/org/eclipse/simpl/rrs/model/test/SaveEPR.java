package org.eclipse.simpl.rrs.model.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.ResolutionSyntax;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.simpl.rrs.model.rrs.EPR;
import org.eclipse.simpl.rrs.model.rrs.RRSFactory;
import org.eclipse.simpl.rrs.model.rrs.ReferenceParameters;
import org.eclipse.simpl.rrs.model.rrs.ReferenceProperties;
import org.eclipse.simpl.rrs.model.rrs.util.RRSResourceFactoryImpl;

public class SaveEPR {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		RRSFactory factory = RRSFactory.eINSTANCE;
		EPR ref1 = factory.createEPR();
		ReferenceParameters param1 = factory.createReferenceParameters();
		ReferenceProperties prop1 = factory.createReferenceProperties();
		prop1.setResolutionSystem("RDB-Adapter");
		param1.setReferenceName("simulationsDaten");
		param1.setStatement("SELECT * FROM simdaten");
		ref1.setAddress("http://localhost:8080/rrs");
		ref1.setReferenceParameters(param1);
		ref1.setReferenceProperties(prop1);
		
		EPR ref2 = factory.createEPR();
		ReferenceParameters param2 = factory.createReferenceParameters();
		ReferenceProperties prop2 = factory.createReferenceProperties();
		prop2.setResolutionSystem("RDB-Adapter");
		param2.setReferenceName("simulationsDaten");
		param2.setStatement("SELECT * FROM simdaten");
		ref2.setAddress("http://localhost:8080/rrs");
		ref2.setReferenceParameters(param2);
		ref2.setReferenceProperties(prop2);
		
		//Speichern über die ReferenceResourceFactory.
		//DIESE VARIANTE IST ZU BEVORZUGEN, DA HIER KEIN STÖRENDER XMI TAG EINGEFÜGT WIRD
		URI fileURI = URI.createFileURI("test/ref1.xml");
		Resource resource = new RRSResourceFactoryImpl().createResource(fileURI);
		resource.getContents().add( ref2 );
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
