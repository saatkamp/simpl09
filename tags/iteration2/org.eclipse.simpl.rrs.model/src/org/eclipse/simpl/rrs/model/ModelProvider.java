package org.eclipse.simpl.rrs.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.simpl.rrs.model.rrs.EPR;
import org.eclipse.simpl.rrs.model.rrs.RRSFactory;
import org.eclipse.simpl.rrs.model.rrs.ReferenceParameters;
import org.eclipse.simpl.rrs.model.rrs.ReferenceProperties;
import org.eclipse.simpl.rrs.model.rrs.util.RRSResourceFactoryImpl;

public class ModelProvider {

	private static final String RRS_FILE_PATH = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "rrs"
			+ System.getProperty("file.separator");

	private static ModelProvider content;
	private List<EPR> references;

	private ModelProvider() {
		references = new ArrayList<EPR>();
		references = loadAllReferences();
		System.out.println(RRS_FILE_PATH);
	}

	public static synchronized ModelProvider getInstance() {
		if (content != null) {
			return content;
		}
		content = new ModelProvider();
		return content;
	}

	public EPR find(String id) {
		EPR found = null;
		for (EPR ref : this.references) {
			if (ref.toString().equals(id)) {
				found = ref;
				continue;
			}
		}
		return found;
	}

	public List<EPR> getReferences() {
		return this.references;
	}

	public void saveReference(EPR ref) {
		URI fileURI = URI.createFileURI(RRS_FILE_PATH
				+ ref.getReferenceParameters().getReferenceName() + ".xml");
		Resource resource = new RRSResourceFactoryImpl()
				.createResource(fileURI);

		resource.getContents().add(ref);
		Map<String, String> save_options = new HashMap<String, String>();
		save_options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
				Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		try {
			resource.save(save_options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteReference(EPR ref) {
		String fileName = ref.getReferenceParameters().getReferenceName()
				+ ".xml";

		File eprFile = new File(RRS_FILE_PATH + fileName);
		if (eprFile.exists() && eprFile.isFile()) {
			eprFile.delete();
		}
	}

	private List<EPR> loadAllReferences() {
		List<EPR> loadedRefs = new ArrayList<EPR>();

		File rrsFolder = new File(RRS_FILE_PATH);
		if (rrsFolder.exists() && rrsFolder.isDirectory()) {
			String[] fileNames = rrsFolder.list();
			for (String fileName : fileNames) {
				loadedRefs.add(loadReference(fileName));
			}
		}
		return loadedRefs;
	}

	private EPR loadReference(String fileName) {
		EPR reference = null;

		URI fileURI = URI.createFileURI(RRS_FILE_PATH + fileName);

		Resource resource = new RRSResourceFactoryImpl()
				.createResource(fileURI);

		try {
			resource.load(Collections.EMPTY_MAP);

			EList<EObject> contents = resource.getContents();
			if (!contents.isEmpty() && contents.get(0) instanceof EPR) {
				reference = (EPR) contents.get(0);

			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		return reference;
	}

	/**
	 * 
	 */
	public void refresh() {
		references.clear();
		
		// TODO Hier müssen noch die neuen EPR's eingefügt werden!
		RRSFactory factory = RRSFactory.eINSTANCE;

		List<HashMap<String, String>> eprs = new ArrayList<HashMap<String, String>>();

		// eprs = loadAllReferences();

		for (HashMap<String, String> epr : eprs) {
			EPR newEPR = factory.createEPR();

			newEPR.setAddress(epr.get("address"));

			ReferenceParameters param = factory.createReferenceParameters();
			param.setReferenceName(epr.get("name"));
			param.setStatement(epr.get("statement"));
			newEPR.setReferenceParameters(param);

			ReferenceProperties prop = factory.createReferenceProperties();
			prop.setResolutionSystem(epr.get("adapter"));
			newEPR.setReferenceProperties(prop);
			newEPR.setPortType(QName.valueOf(epr.get("portType")));
			newEPR.setPolicy(epr.get("policy"));

			references.add(newEPR);

		}
	}

}
