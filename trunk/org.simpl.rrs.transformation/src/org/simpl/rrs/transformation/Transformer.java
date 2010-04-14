package org.simpl.rrs.transformation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

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
@SuppressWarnings("unchecked")
public class Transformer {

	// Namespace identifiers
	private static final String BPEL_PRÄFIX = "bpel";
	private static final Namespace BPEL_NAMESPACE = Namespace.getNamespace(
			BPEL_PRÄFIX,
			"http://docs.oasis-open.org/wsbpel/2.0/process/executable");

	private static final Namespace RRS_NAMESPACE = Namespace.getNamespace(
			"rrs", "http://uni-stuttgart.de/simpl/rrs");
	private static final String RRS_PARTNER_LINK_NAME = "RRS";

	// Attribute names
	private static final String AT_NAME = "name";

	private static final String AT_REFERENCE_TYPE = "referenceType";
	private static final String AT_REFERENCE_VALUE_TYPE = "valueType";

	private static final String AT_VARIABLE_TYPE = "type";

	private static final String AT_IMPORT_LOCATION = "location";
	private static final String AT_IMPORT_NS = "namespace";
	private static final String AT_IMPORT_ITYPE = "importType";

	private static final String AT_PL_TYPE = "partnerLinkType";
	private static final String AT_PL_ROLE = "myRole";

	private static final String AT_OPERATION = "operation";
	private static final String AT_INPUT_VARIABLE = "inputVariable";
	private static final String AT_OUTPUT_VARIABLE = "outputVariable";
	private static final String AT_PARTNER_LINK = "partnerLink";
	private static final String AT_VARIABLE = "variable";

	// Element names
	private static final String EL_REFERENCE_VARIABLE = "referenceVariable";
	private static final String EL_REFERENCE_VARIABLES = "referenceVariables";

	private static final String EL_VARIABLES = "variables";
	private static final String EL_VARIABLE = "variable";

	private static final String EL_IMPORT = "import";
	private static final String EL_PARTNER_LINKS = "partnerLinks";
	private static final String EL_PARTNER_LINK = "partnerLink";

	private static final String EL_INVOKE = "invoke";
	private static final String EL_REPLY = "reply";
	private static final String EL_RECEIVE = "receive";

	private static final String EL_SEQUENCE = "sequence";

	private static final String EL_PICK = "pick";

	// ReferenceType values
	private static final String ON_INSTANTIATION = "onInstantiation";
	private static final String FRESH = "fresh";

	// TODO: EPR muss noch irgendwie in das BPEL Namespace File oder in ein
	// separates, dass dann in den BPEL-Prozess integriert wird.
	// TODO: Am besten ein eigenständiges xsd-file mit der EPR mit rrs-Präfix
	private static final String EPR_TYPE = RRS_NAMESPACE.getPrefix() + ":"
			+ "EPR";

	// An array of all by reference influenced element types
	String[] types = new String[] { EL_REPLY, EL_INVOKE, EL_RECEIVE };

	// This HashMap will hold all referenceVariable names as key and later the
	// actual number of dereferentiation activities in which this
	// referenceVariable is
	// solved.
	private HashMap<String, Integer> refVarNames = new HashMap<String, Integer>();
	
	// This list holds all invoke elements from the original process
	private List invokeElements = new ArrayList();

	// This variable holds the initial receive of a process if one exists
	Element instantiationElement = null;

	// This list holds all referenceVariable elements
	private List refVariableElements = new ArrayList();

	private static Transformer transformer = null;

	public static Transformer getTransformer() {
		if (transformer == null) {
			transformer = new Transformer();
		}
		return transformer;
	}

	public String transform(String input) {

		ByteArrayInputStream inputStream = new ByteArrayInputStream(input
				.getBytes());

		SAXBuilder builder = new SAXBuilder();
		Document doc;
		try {
			doc = builder.build(inputStream);

			// Start Transformation
			Element root = doc.getRootElement();

			// Add the rrs namespace prefix to the process element
			root.addNamespaceDeclaration(RRS_NAMESPACE);

			// Set all the Imports
			List imports = root.getChildren(EL_IMPORT, BPEL_NAMESPACE);

			// Add the rrs.wsdl to the process imports
			// TODO: Noch an René's Implementierung anpassen
			Element rrsWSDLImport = new Element(EL_IMPORT, BPEL_NAMESPACE);
			rrsWSDLImport.setAttribute(AT_IMPORT_LOCATION, "rrs.wsdl");
			rrsWSDLImport.setAttribute(AT_IMPORT_NS, RRS_NAMESPACE.getURI());
			rrsWSDLImport.setAttribute(AT_IMPORT_ITYPE,
					"http://schemas.xmlsoap.org/wsdl/");
			imports.add(0, rrsWSDLImport);

			// Add the rrs.xsd to the process imports
			// TODO: rrs.xsd muss im workspace des Prozesses liegen
			Element rrsXSDImport = new Element(EL_IMPORT, BPEL_NAMESPACE);
			rrsXSDImport.setAttribute(AT_IMPORT_LOCATION, "rrs.xsd");
			rrsXSDImport.setAttribute(AT_IMPORT_NS, RRS_NAMESPACE.getURI());
			rrsXSDImport.setAttribute(AT_IMPORT_ITYPE,
					"http://www.w3.org/2001/XMLSchema");
			imports.add(0, rrsXSDImport);

			// Create the RRS partnerLink
			List partnerLinks = root.getChild(EL_PARTNER_LINKS, BPEL_NAMESPACE)
					.getChildren("partnerLink", BPEL_NAMESPACE);
			Element rrsPartnerLink = new Element(EL_PARTNER_LINK,
					BPEL_NAMESPACE);
			// TODO: Hier noch die richtigen Werte setzen und klären, ob das so
			// alles geht oder noch was fehlt (PartnerLinkType?!)
			rrsPartnerLink.setAttribute(AT_NAME, RRS_PARTNER_LINK_NAME);
			rrsPartnerLink.setAttribute(AT_PL_TYPE, "");
			rrsPartnerLink.setAttribute(AT_PL_ROLE, "");

			partnerLinks.add(rrsPartnerLink);

			// read referenceVariable elements
			refVariableElements = root.getChild(EL_REFERENCE_VARIABLES,
					BPEL_NAMESPACE).getChildren(EL_REFERENCE_VARIABLE,
					BPEL_NAMESPACE);

			// variables element
			Element variableElements = root.getChild(EL_VARIABLES,
					BPEL_NAMESPACE);

			for (int i = 0; i < refVariableElements.size(); i++) {
				// Find searched element with given attribute:
				Element elMain = (Element) (refVariableElements.get(i));
				if (null == elMain)
					continue;
				String name = elMain.getAttributeValue(AT_NAME);
				String valueType = elMain
						.getAttributeValue(AT_REFERENCE_VALUE_TYPE);

				refVarNames.put(name, 0);

				variableElements.addContent(createVariableElement(name,
						valueType));
				variableElements.addContent(createVariableElement(name + "EPR",
						EPR_TYPE));
			}

			// Detach referenceVariable elements
			root.removeChild(EL_REFERENCE_VARIABLES, BPEL_NAMESPACE);

			// Insert the dereferentiation activities to the process

			// The main sequence element of the process
			Element parentSequence = root.getChild(EL_SEQUENCE, BPEL_NAMESPACE);

			// The initial instantiation element of the main sequence of the
			// process
			// process will be instantiated by a receive element
			instantiationElement = parentSequence.getChild(EL_RECEIVE,
					BPEL_NAMESPACE);
			if (instantiationElement == null) {
				// process will be instantiated by a pick element
				instantiationElement = parentSequence.getChild(EL_PICK,
						BPEL_NAMESPACE);
			}
			
			// This is a backup of all invoke elements of the original process file
			invokeElements.addAll(parentSequence.getChildren(EL_INVOKE, BPEL_NAMESPACE));

			// Create an onInstantiation sequence for all onInstantiation
			// dereferentiation activities
			Element onInstSequence = new Element(EL_SEQUENCE, BPEL_NAMESPACE);
			onInstSequence.setAttribute(AT_NAME, "prepare");

			// Add the new sequence after the instantiation process element
			parentSequence.addContent(parentSequence
					.indexOf(instantiationElement) + 1, onInstSequence);

			// Durchlaufen der obersten Sequence
			// TODO: Hier muss unbedingt noch geklärt werden, wie Prozesse
			// aussehen können
			// und welche Konstrukte und Verschachtelungen möglich sind

			for (String type : types) {
				processActivityElements(parentSequence, onInstSequence, type);
			}

			XMLOutputter outp = new XMLOutputter();
			outp.setFormat(Format.getPrettyFormat());
			return outp.outputString(doc);

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	private Element createVariableElement(String name, String type) {
		Element variable = new Element(EL_VARIABLE, BPEL_NAMESPACE);

		variable.setAttribute(AT_NAME, name);
		variable.setAttribute(AT_VARIABLE_TYPE, type);

		return variable;
	}

	private Element createRRSInvokeElement(String refVarName,
			String valueVarName, int count) {
		/*
		 * <invoke name="refNameRefresh_1" partnerLink="RRS" operation="GET"
		 * inputVariable="refNameEPR" outputVariable="refName"/>
		 */
		Element invoke = new Element(EL_INVOKE, BPEL_NAMESPACE);

		invoke.setAttribute(AT_NAME, valueVarName + "Refresh_" + count);
		invoke.setAttribute(AT_PARTNER_LINK, RRS_PARTNER_LINK_NAME);
		invoke.setAttribute(AT_OPERATION, "GET");
		invoke.setAttribute(AT_INPUT_VARIABLE, refVarName);
		invoke.setAttribute(AT_OUTPUT_VARIABLE, valueVarName);

		return invoke;
	}

	private Element getReferenceVariable(String name) {
		Element refVariable = null;
		for (Object var : refVariableElements) {
			if (((Element) var).getAttributeValue(AT_NAME).equals(name)) {
				refVariable = (Element) var;
				return refVariable;
			}
		}

		return refVariable;
	}

	private void processActivityElements(Element parentSequence,
			Element onInstSequence, String type) {
		/*
		 * A clone of the list of child elements of the sequence. This is needed
		 * because the children list is "live" and so manipulating the original
		 * list during iterating over it will cause problems.
		 */
		List clone = new ArrayList();
		clone.addAll(parentSequence.getChildren(type, BPEL_NAMESPACE));
		for (Object obj : clone) {
			Element element = (Element) obj;

			if (type.equals(EL_INVOKE)) {
				//Exclude the invoke elements we have created a few steps before
				//FIXME: This is probably a BUG. When we parse the invoke elements we will also see
				//our own inserted dereferentiation invoke elements. This should be fixed.
				if (invokeElements.contains(element)){
					processInvokeElement(onInstSequence, element);
				}
			} else if (type.equals(EL_REPLY)) {
				// Process all reply activities
				processReplyElement(onInstSequence, element);
			} else if (type.equals(EL_RECEIVE)) {
				processReceiveElement(onInstSequence, element);
			}
		}
	}

	private void processReplyElement(Element onInstSequence, Element element) {
		if (refVarNames.containsKey(element.getAttributeValue(AT_VARIABLE))) {
			// This activity has a referenceVariable
			String varName = element.getAttributeValue(AT_VARIABLE);
			Element deRefInvoke = createRRSInvokeElement(varName + "EPR",
					varName, refVarNames.get(varName));
			if (getReferenceVariable(varName).getAttributeValue(
					AT_REFERENCE_TYPE).equals(ON_INSTANTIATION)) {
				// onInstantiation: referenced data should be loaded
				// constant (only one time)
				if (refVarNames.get(varName) == 0) {
					onInstSequence.addContent(deRefInvoke);

					// Increase the counter for the name of the next RRS
					// invoke activity
					refVarNames.put(varName, refVarNames.get(varName) + 1);
				}
			} else {
				// fresh: referenced data should be loaded dynamic
				// (several times)
				Element parent = element.getParentElement();

				int index = parent.indexOf(element);

				if (index != -1) {
					parent.addContent(index, deRefInvoke);
					// Increase the counter for the name of the next RRS
					// invoke activity
					refVarNames.put(varName, refVarNames.get(varName) + 1);
				}
			}
		}
	}

	private void processReceiveElement(Element onInstSequence, Element element) {
		// Check if the instantiation element is the input element. If this is
		// true we won't do anything.
		if (instantiationElement != element) {

			if (refVarNames.containsKey(element.getAttributeValue(AT_VARIABLE))) {
				// This activity has a referenceVariable
				String varName = element.getAttributeValue(AT_VARIABLE);
				Element deRefInvoke = createRRSInvokeElement(varName + "EPR",
						varName, refVarNames.get(varName));
				if (getReferenceVariable(varName).getAttributeValue(
						AT_REFERENCE_TYPE).equals(ON_INSTANTIATION)) {
					// onInstantiation: referenced data should be loaded
					// constant (only one time)
					if (refVarNames.get(varName) == 0) {
						onInstSequence.addContent(deRefInvoke);

						// Increase the counter for the name of the next RRS
						// invoke activity
						refVarNames.put(varName, refVarNames.get(varName) + 1);
					}
				} else {
					// fresh: referenced data should be loaded dynamic
					// (several times)
					Element parent = element.getParentElement();

					int index = parent.indexOf(element);

					if (index != -1) {
						parent.addContent(index, deRefInvoke);
						// Increase the counter for the name of the next RRS
						// invoke activity
						refVarNames.put(varName, refVarNames.get(varName) + 1);
					}
				}
			}
		}
	}

	// TODO: Falls nur inputVariable vorhanden Eingabe an
	// processInvokeInOnlyElement(Element, Element, Element)
	// weiterleiten
	private void processInvokeElement(Element onInstSequence, Element element) {
		//Check if the invoke has an in- and output variable or not
		if (element.getAttribute(AT_OUTPUT_VARIABLE) == null){
			processInvokeInOnlyElement(onInstSequence, element);
		}else {
			//Check the input variable
			if (refVarNames.containsKey(element.getAttributeValue(AT_INPUT_VARIABLE))) {
				// The input variable is a referenceVariable
				String varName = element.getAttributeValue(AT_INPUT_VARIABLE);
				Element deRefInvoke = createRRSInvokeElement(varName + "EPR",
						varName, refVarNames.get(varName));
				if (getReferenceVariable(varName).getAttributeValue(
						AT_REFERENCE_TYPE).equals(ON_INSTANTIATION)) {
					// onInstantiation: referenced data should be loaded
					// constant (only one time)
					if (refVarNames.get(varName) == 0) {
						onInstSequence.addContent(deRefInvoke);

						// Increase the counter for the name of the next RRS
						// invoke activity
						refVarNames.put(varName, refVarNames.get(varName) + 1);
					}
				} else {
					// fresh: referenced data should be loaded dynamic
					// (several times)
					Element parent = element.getParentElement();

					int index = parent.indexOf(element);

					if (index != -1) {
						parent.addContent(index, deRefInvoke);
						// Increase the counter for the name of the next RRS
						// invoke activity
						refVarNames.put(varName, refVarNames.get(varName) + 1);
					}
				}
			}
			
			//Check the output variable
			if (refVarNames.containsKey(element.getAttributeValue(AT_OUTPUT_VARIABLE))) {
				// The output variable is a referenceVariable
				String varName = element.getAttributeValue(AT_OUTPUT_VARIABLE);
				Element deRefInvoke = createRRSInvokeElement(varName + "EPR",
						varName, refVarNames.get(varName));
				if (getReferenceVariable(varName).getAttributeValue(
						AT_REFERENCE_TYPE).equals(ON_INSTANTIATION)) {
					// onInstantiation: referenced data should be loaded
					// constant (only one time)
					if (refVarNames.get(varName) == 0) {
						onInstSequence.addContent(deRefInvoke);

						// Increase the counter for the name of the next RRS
						// invoke activity
						refVarNames.put(varName, refVarNames.get(varName) + 1);
					}
				} else {
					// fresh: referenced data should be loaded dynamic
					// (several times)
					Element parent = element.getParentElement();

					int index = parent.indexOf(element);

					if (index != -1) {
						parent.addContent(index, deRefInvoke);
						// Increase the counter for the name of the next RRS
						// invoke activity
						refVarNames.put(varName, refVarNames.get(varName) + 1);
					}
				}
			}
		}
	}

	private void processInvokeInOnlyElement(Element onInstSequence,
			Element element) {
		if (refVarNames.containsKey(element.getAttributeValue(AT_INPUT_VARIABLE))) {
			// This activity has a referenceVariable
			String varName = element.getAttributeValue(AT_INPUT_VARIABLE);
			Element deRefInvoke = createRRSInvokeElement(varName + "EPR",
					varName, refVarNames.get(varName));
			if (getReferenceVariable(varName).getAttributeValue(
					AT_REFERENCE_TYPE).equals(ON_INSTANTIATION)) {
				// onInstantiation: referenced data should be loaded
				// constant (only one time)
				if (refVarNames.get(varName) == 0) {
					onInstSequence.addContent(deRefInvoke);

					// Increase the counter for the name of the next RRS
					// invoke activity
					refVarNames.put(varName, refVarNames.get(varName) + 1);
				}
			} else {
				// fresh: referenced data should be loaded dynamic
				// (several times)
				Element parent = element.getParentElement();

				int index = parent.indexOf(element);

				if (index != -1) {
					parent.addContent(index, deRefInvoke);
					// Increase the counter for the name of the next RRS
					// invoke activity
					refVarNames.put(varName, refVarNames.get(varName) + 1);
				}
			}
		}
	}
	
//	/**
//	 * This method checks if two generated dereferentiation (RRS invoke) elements
//	 * are doing the same.
//	 * 
//	 * @param elm1 one invoke element
//	 * @param elm2 another invoke element
//	 * @return true, if the elements have the same attribute values and only the number
//	 * in the name is different, for example:
//	 * Element1: <bpel:invoke name="dataRefresh_0" partnerLink="RRS" operation="GET" inputVariable="dataEPR" outputVariable="data" />
//	 * Element2: <bpel:invoke name="dataRefresh_1" partnerLink="RRS" operation="GET" inputVariable="dataEPR" outputVariable="data" />
//	 * 
//	 * These to elements are resolving the same reference twice, so one is not necassary
//	 * and don't have to be generated. 
//	 */
//	private boolean isInvokeElementEqual(Element elm1, Element elm2){
//		//FIXME: This is probably a BUG. When we parse the invoke elements we will also see
//		//our own inserted dereferentiation invoke elements. This should be fixed and then this
//		//Method won't be necassary any more.
//		boolean equal = false;
//		
//		String[] split1 = elm1.getAttributeValue(AT_NAME).split("_");
//		String name1 = split1[0];
//		
//		String[] split2 = elm2.getAttributeValue(AT_NAME).split("_");
//		String name2 = split2[0];
//		
//		if (elm1.getAttributeValue(AT_OUTPUT_VARIABLE).equals(elm2.getAttributeValue(AT_OUTPUT_VARIABLE)) &&
//				elm1.getAttributeValue(AT_INPUT_VARIABLE).equals(elm2.getAttributeValue(AT_INPUT_VARIABLE))&&
//						elm1.getAttributeValue(AT_OPERATION).equals(elm2.getAttributeValue(AT_OPERATION))&&
//								elm1.getAttributeValue(AT_PARTNER_LINK).equals(elm2.getAttributeValue(AT_PARTNER_LINK))&&
//										name1.equals(name2)){
//			equal = true;
//		}
//		
//		return equal;
//	}
}
