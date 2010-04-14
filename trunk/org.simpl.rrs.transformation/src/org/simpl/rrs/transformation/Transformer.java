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
	
	//ReferenceType values
	private static final String ON_INSTANTIATION = "onInstantiation";
	private static final String FRESH = "fresh";

	// TODO: EPR muss noch irgendwie in das BPEL Namespace File oder in ein
	// separates, dass dann in den BPEL-Prozess integriert wird.
	// TODO: Am besten ein eigenständiges xsd-file mit der EPR mit rrs-Präfix
	private static final String EPR_TYPE = RRS_NAMESPACE.getPrefix()+":"+"EPR";

	// This HashMap will hold all referenceVariable names as key and later the
	// actual number of dereferentiation activities in which this
	// referenceVariable is
	// solved.
	private HashMap<String, Integer> refVarNames = new HashMap<String, Integer>();
	
	//This list holds all referenceVariable elements
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
			// TODO: Hier müssen dann auch die PartnerLinks in den
			// Dereferenzierungsaktivitäten
			// auf das RRS gesetzt werden.

			Element parentSequence = root.getChild(EL_SEQUENCE, BPEL_NAMESPACE);
			//Durchlaufen der obersten Sequence
			for (Object obj : parentSequence.getChildren()) {
				Element element = (Element) obj;
				if (element.getName().equals(EL_INVOKE)
						&& element.getAttribute(AT_OUTPUT_VARIABLE) != null) {
//					// Process all invoke activities with input and output
//					// variable
//					if (refVarNames.containsKey(element
//							.getAttributeValue(AT_INPUT_VARIABLE))
//							|| refVarNames.containsKey(element
//									.getAttributeValue(AT_OUTPUT_VARIABLE))) {
//						// This activity has a referenceVariable
//						String varName = element.getAttributeValue(AT_INPUT_VARIABLE);
//						//Check whether the input or output variable is a reference
//						if (!refVarNames.containsKey(varName)){
//							varName = element.getAttributeValue(AT_OUTPUT_VARIABLE);
//						}
//						//TODO: theoretisch könnten hier auch zwei unabhängige Referenzvariablen vorkommen,
//						//eine als inputVariable und eine andere als outputVariable
//						Element deRefInvoke = createRRSInvokeActivity(varName
//								+ "EPR", varName, refVarNames.get(varName));
//						if (getReferenceVariable(varName).getAttributeValue(AT_REFERENCE_TYPE).equals(ON_INSTANTIATION)){
//							//onInstantiation: referenced data should be loaded constant (only one time)
//							root.getChild(EL_SCOPE).addContent(0, deRefInvoke);
//						}else {
//							//fresh: referenced data should be loaded dynamic (several times)
//							Element parent = element.getParentElement();
//							int index = parent.indexOf(element);
//							if (index != -1){
//								parent.addContent(index, deRefInvoke);
//								//Increase the counter for the name of the activity
//								refVarNames.put(varName, refVarNames.get(varName)+1);
//							}
//						}
//					}
				} else if (element.getName().equals(EL_INVOKE)
						&& element.getAttribute(AT_OUTPUT_VARIABLE) == null) {
//					// Process all invoke activities with only a input variable
//					if (refVarNames.containsKey(element
//							.getAttributeValue(AT_INPUT_VARIABLE))
//							|| refVarNames.containsKey(element
//									.getAttributeValue(AT_OUTPUT_VARIABLE))) {
//						// This activity has a referenceVariable
//						String varName = element.getAttributeValue(AT_INPUT_VARIABLE);
//						Element deRefInvoke = createRRSInvokeActivity(varName
//								+ "EPR", varName, refVarNames.get(varName));
//						if (getReferenceVariable(varName).getAttributeValue(AT_REFERENCE_TYPE).equals(ON_INSTANTIATION)){
//							//onInstantiation: referenced data should be loaded constant (only one time)
//							root.getChild(EL_SCOPE).addContent(0, deRefInvoke);
//						}else {
//							//fresh: referenced data should be loaded dynamic (several times)
//							Element parent = element.getParentElement();
//							int index = parent.indexOf(element);
//							if (index != -1){
//								parent.addContent(index, deRefInvoke);
//								//Increase the counter for the name of the activity
//								refVarNames.put(varName, refVarNames.get(varName)+1);
//							}
//						}
//					}
				} else if (element.getName().equals(EL_RECEIVE)
						|| element.getName().equals(EL_REPLY)) {
					// Process all reply & receive activities
					
					System.out.println("REFNAMES: "+refVarNames.toString());
					
					if (refVarNames.containsKey(element
							.getAttributeValue(AT_VARIABLE))) {
						// This activity has a referenceVariable
						String varName = element.getAttributeValue(AT_VARIABLE);
						Element deRefInvoke = createRRSInvokeActivity(varName
								+ "EPR", varName, refVarNames.get(varName));
						if (getReferenceVariable(varName).getAttributeValue(AT_REFERENCE_TYPE).equals(ON_INSTANTIATION)){
							//onInstantiation: referenced data should be loaded constant (only one time)
							root.getChild(EL_SEQUENCE).addContent(0, deRefInvoke);
						}else {
							//fresh: referenced data should be loaded dynamic (several times)
							Element parent = element.getParentElement();
							
							System.out.println("ELEMENT: " + element.toString());
							System.out.println();
							System.out.println("PARENT: " + parent.toString());
							
							int index = parent.indexOf(element);
							
							System.out.println("INDEX: " + index);
							System.out.println();
							System.out.println("DEREF: " + deRefInvoke.toString());
							
							if (index != -1){
								parent.addContent(index, deRefInvoke);
								//Increase the counter for the name of the activity
								refVarNames.put(varName, refVarNames.get(varName)+1);
							}
						}
					}
				}
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

	private Element createRRSInvokeActivity(String refVarName,
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
	
	private Element getReferenceVariable(String name){
		Element refVariable = null;
		for (Object var : refVariableElements){
			if (((Element)var).getAttributeValue(AT_NAME).equals(name)){
				refVariable = (Element) var;
				return refVariable;
			}
		}
		
		return refVariable;
	}
}
