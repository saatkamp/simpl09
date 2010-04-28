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

	// The namespace of the rrs xsd
	private static final Namespace RRS_XSD_NAMESPACE = Namespace.getNamespace(
			"rrs", "http://uni-stuttgart.de/simpl/rrs");

	// The namespace of the rrs wsdl
	private static Namespace RRS_WSDL_NAMESPACE;

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
	private static final String AT_PORT_TYPE = "portType";

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

	private static final String EL_ASSIGN = "assign";
	private static final String EL_COPY = "copy";
	private static final String EL_FROM = "from";

	// Expressions
	private static final String EXP_VAL = "val";

	// ReferenceType values
	private static final String ON_INSTANTIATION = "onInstantiation";
	@SuppressWarnings("unused")
	private static final String FRESH = "fresh";

	private static final String EPR_TYPE = RRS_XSD_NAMESPACE.getPrefix() + ":"
			+ "EPR";

	// An array of all by reference influenced element types
	String[] types = new String[] { EL_REPLY, EL_INVOKE, EL_RECEIVE, EL_ASSIGN };

	// This HashMap will hold all referenceVariable names as key and later the
	// actual number of dereferentiation activities in which this
	// referenceVariable is solved.
	private HashMap<String, Integer> refVarNames = new HashMap<String, Integer>();

	// This list holds all invoke elements from the original process
	private List invokeElements = new ArrayList();

	// This variable holds the initial receive of a process if one exists
	Element instantiationElement = null;

	// This list holds all referenceVariable elements
	private List refVariableElements = new ArrayList();

	// This boolean tells wether the RRS xsd and wsdl namespace are equal or not
	private boolean oneRRSNamespace = true;

	private static Transformer transformer = null;

	public static Transformer getTransformer() {
		if (transformer == null) {
			transformer = new Transformer();
		}
		return transformer;
	}

	public String transform(String input, String rrsWSDLnamespace) {

		RRS_WSDL_NAMESPACE = Namespace
				.getNamespace("rrsWSDL", rrsWSDLnamespace);

		if (RRS_XSD_NAMESPACE.getURI().equals(RRS_WSDL_NAMESPACE.getURI())) {
			oneRRSNamespace = true;
		} else {
			oneRRSNamespace = false;
		}

		ByteArrayInputStream inputStream = new ByteArrayInputStream(input
				.getBytes());

		SAXBuilder builder = new SAXBuilder();
		Document doc;
		try {
			doc = builder.build(inputStream);

			// Start Transformation
			Element root = doc.getRootElement();

			if (oneRRSNamespace) {
				// Add the rrs xsd namespace prefix to the process element
				root.addNamespaceDeclaration(RRS_XSD_NAMESPACE);
			} else {
				// Add the rrs wsdl namespace prefix to the process element
				root.addNamespaceDeclaration(RRS_WSDL_NAMESPACE);
				// Add the rrs xsd namespace prefix to the process element
				root.addNamespaceDeclaration(RRS_XSD_NAMESPACE);
			}

			// Set all the new required Imports
			List imports = root.getChildren(EL_IMPORT, BPEL_NAMESPACE);

			// // Add the rrs.wsdl to the process imports
			// // TODO: Noch an René's Implementierung anpassen
			// Element rrsWSDLImport = new Element(EL_IMPORT, BPEL_NAMESPACE);
			// rrsWSDLImport.setAttribute(AT_IMPORT_LOCATION, "rrs.wsdl");
			// rrsWSDLImport.setAttribute(AT_IMPORT_NS, RRS_NAMESPACE.getURI());
			// rrsWSDLImport.setAttribute(AT_IMPORT_ITYPE,
			// "http://schemas.xmlsoap.org/wsdl/");
			// imports.add(0, rrsWSDLImport);

			// Add the rrs.xsd to the process imports
			// TODO: rrs.xsd muss im workspace des Prozesses liegen
			Element rrsXSDImport = new Element(EL_IMPORT, BPEL_NAMESPACE);
			rrsXSDImport.setAttribute(AT_IMPORT_LOCATION, "rrs.xsd");
			rrsXSDImport.setAttribute(AT_IMPORT_NS, RRS_XSD_NAMESPACE.getURI());
			rrsXSDImport.setAttribute(AT_IMPORT_ITYPE,
					"http://www.w3.org/2001/XMLSchema");
			imports.add(0, rrsXSDImport);

			// Create the RRS partnerLink
			List partnerLinks = root.getChild(EL_PARTNER_LINKS, BPEL_NAMESPACE)
					.getChildren("partnerLink", BPEL_NAMESPACE);
			Element rrsPartnerLink = new Element(EL_PARTNER_LINK,
					BPEL_NAMESPACE);
			/*
			 * <bpel:partnerLink name="RRS" partnerLinkType="tns:RRSGetType"
			 * partnerRole="GET"> </bpel:partnerLink>
			 */
			rrsPartnerLink.setAttribute(AT_NAME, RRS_PARTNER_LINK_NAME);
			rrsPartnerLink.setAttribute(AT_PL_TYPE, "tns:RRSGetType");
			rrsPartnerLink.setAttribute(AT_PL_ROLE, "GET");

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

			// This is a backup of all invoke elements of the original process
			// file
			invokeElements.addAll(parentSequence.getChildren(EL_INVOKE,
					BPEL_NAMESPACE));

			// Create an onInstantiation sequence for all onInstantiation
			// dereferentiation activities
			Element onInstSequence = new Element(EL_SEQUENCE, BPEL_NAMESPACE);
			onInstSequence.setAttribute(AT_NAME, "prepare");

			// Add the new sequence after the instantiation process element
			parentSequence.addContent(parentSequence
					.indexOf(instantiationElement) + 1, onInstSequence);

			// Durchlaufen der obersten Sequence

			for (String type : types) {
				processActivityElements(parentSequence, onInstSequence, type);
			}

			// If the prepare sequence remains empty it would be deleted
			if (onInstSequence.getChildren().isEmpty()) {
				onInstSequence.detach();
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
		 * <bpel:invoke name="#VarName#Refresh_#counter#" partnerLink="RRS"
		 * operation="transform" portType="ns:TransformationService"
		 * inputVariable="dataEPR" outputVariable="data"> </bpel:invoke>
		 */
		Element invoke = new Element(EL_INVOKE, BPEL_NAMESPACE);

		// TODO: Hier müssen nochmal alle Attribute überprüft und an
		// René's Implementierung angepasst werden
		invoke.setAttribute(AT_NAME, valueVarName + "Refresh_" + count);
		invoke.setAttribute(AT_PARTNER_LINK, RRS_PARTNER_LINK_NAME);
		invoke.setAttribute(AT_OPERATION, "get");
		if (oneRRSNamespace) {
			invoke.setAttribute(AT_PORT_TYPE, RRS_XSD_NAMESPACE.getPrefix()
					+ ":" + "RRSRetrievalService");
		} else {
			invoke.setAttribute(AT_PORT_TYPE, RRS_WSDL_NAMESPACE.getPrefix()
					+ ":" + "RRSRetrievalService");
		}
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
				// Exclude the invoke elements we have created a few steps
				// before
				// FIXME: This is probably a BUG. When we parse the invoke
				// elements we will also see
				// our own inserted dereferentiation invoke elements. This
				// should be fixed.
				if (invokeElements.contains(element)) {
					processInvokeElement(onInstSequence, element);
				}
			} else if (type.equals(EL_REPLY) || type.equals(EL_RECEIVE)) {
				// Process all reply activities
				processReceiveReplyElement(onInstSequence, element);
			} else {
				processAnyElement(onInstSequence, element);
			}
		}
	}

	private void processReceiveReplyElement(Element onInstSequence,
			Element element) {
		if (refVarNames.containsKey(element.getAttributeValue(AT_VARIABLE))) {
			// This activity has a referenceVariable
			String varName = element.getAttributeValue(AT_VARIABLE);
			element.setAttribute(AT_VARIABLE, varName + "EPR");
		}
	}

	// TODO: Falls nur inputVariable vorhanden Eingabe an
	// processInvokeInOnlyElement(Element, Element)
	// weiterleiten
	private void processInvokeElement(Element onInstSequence, Element element) {
		// Check if the invoke has an in- and output variable or not
		if (element.getAttribute(AT_OUTPUT_VARIABLE) == null) {
			processInvokeInOnlyElement(onInstSequence, element);
		} else {
			// Check the input variable
			if (refVarNames.containsKey(element
					.getAttributeValue(AT_INPUT_VARIABLE))) {
				// The input variable is a referenceVariable
				String varName = element.getAttributeValue(AT_INPUT_VARIABLE);
				element.setAttribute(AT_INPUT_VARIABLE, varName + "EPR");
			}

			// Check the output variable
			if (refVarNames.containsKey(element
					.getAttributeValue(AT_OUTPUT_VARIABLE))) {
				// The output variable is a referenceVariable
				String varName = element.getAttributeValue(AT_OUTPUT_VARIABLE);
				element.setAttribute(AT_OUTPUT_VARIABLE, varName + "EPR");
			}
		}
	}

	private void processInvokeInOnlyElement(Element onInstSequence,
			Element element) {
		if (refVarNames.containsKey(element
				.getAttributeValue(AT_INPUT_VARIABLE))) {
			// This activity has a referenceVariable
			String varName = element.getAttributeValue(AT_INPUT_VARIABLE);
			element.setAttribute(AT_INPUT_VARIABLE, varName + "EPR");
		}
	}

	private void processAnyElement(Element onInstSequence, Element element) {
		// We check only Assign activities at the moment

		/*
		 * <bpel:assign validate="no" name="Assign"> <bpel:copy> <bpel:from>
		 * <![CDATA[val($ReferenceVariable)]]> </bpel:from> <bpel:to
		 * variable="input"></bpel:to> </bpel:copy> </bpel:assign>
		 */

		// Check the expression
		Element from = element.getChild(EL_COPY, BPEL_NAMESPACE).getChild(
				EL_FROM, BPEL_NAMESPACE);

		String expression = from.getText().trim();

		if (expression.matches(EXP_VAL + "\\([$][a-zA-Z_0-9]+\\)")) {
			expression = expression.substring(5, expression.indexOf(")", 5));
			if (refVarNames.containsKey(expression)) {
				// The expression contains a reference variable
				String varName = expression;
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
		} else {
			/*
			 * <bpel:assign validate="no" name="Assign"> <bpel:copy> <bpel:from
			 * variable="input"></bpel:from> <bpel:to
			 * variable="input"></bpel:to> </bpel:copy> </bpel:assign>
			 */
			if (from.getAttributeValue(AT_VARIABLE) != null
					&& refVarNames.containsKey(from
							.getAttributeValue(AT_VARIABLE))) {
				// Assign from variable is a reference variable
				String varName = from.getAttributeValue(AT_VARIABLE);
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
}
