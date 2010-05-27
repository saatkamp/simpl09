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
	@SuppressWarnings("unused")
	private String SCHEMA_NS_PREFIX = "";

	private final String BPEL_PREFIX = "bpel";
	private final Namespace BPEL_NAMESPACE = Namespace.getNamespace(
			BPEL_PREFIX,
			"http://docs.oasis-open.org/wsbpel/2.0/process/executable");

	private final Namespace XML_NAMESPACE = Namespace.XML_NAMESPACE;

	// // The namespace of the rrs xsd
	// private final Namespace RRS_XSD_NAMESPACE = Namespace.getNamespace("rrs",
	// "http://uni-stuttgart.de/simpl/rrs");

	// The namespaces of the RRS
	private Namespace RRS_NAMESPACE;

	private final String RRS_RETRIEVAL_PL = "RRS_Retrieval";
	private final String RRS_RETRIEVAL_PL_TYPE = "tns:RRSRetrievalType";
	private final String RRS_RETRIEVAL_PL_ROLE = "get";
	private final String RRS_RETRIEVAL_PL_PORT = "RRSRetrievalService";

	private final String RRS_META_DATA_PL = "RRS_MetaData";
	private final String RRS_META_DATA_PL_TYPE = "tns:RRSMetaDataType";
	private final String RRS_META_DATA_PL_ROLE = "getEPR";
	private final String RRS_META_DATA_PL_PORT = "RRSMetaDataService";

	// Attribute names
	private final String AT_NAME = "name";

	private final String AT_REFERENCE_TYPE = "referenceType";
	private final String AT_REFERENCE_VALUE_TYPE = "valueType";

	private final String AT_VARIABLE_TYPE = "messageType";

	private final String AT_IMPORT_LOCATION = "location";
	private final String AT_IMPORT_NS = "namespace";
	private final String AT_IMPORT_ITYPE = "importType";

	private final String AT_PL_TYPE = "partnerLinkType";
	private final String AT_PL_ROLE = "partnerRole";

	private final String AT_OPERATION = "operation";
	private final String AT_INPUT_VARIABLE = "inputVariable";
	private final String AT_OUTPUT_VARIABLE = "outputVariable";
	private final String AT_PARTNER_LINK = "partnerLink";
	private final String AT_VARIABLE = "variable";
	private final String AT_PORT_TYPE = "portType";

	// Element names
	private final String EL_REFERENCE_VARIABLE = "referenceVariable";
	private final String EL_REFERENCE_VARIABLES = "referenceVariables";

	private final String EL_VARIABLES = "variables";
	private final String EL_VARIABLE = "variable";

	private final String EL_IMPORT = "import";
	private final String EL_PARTNER_LINKS = "partnerLinks";
	private final String EL_PARTNER_LINK = "partnerLink";

	private final String EL_INVOKE = "invoke";
	private final String EL_REPLY = "reply";
	private final String EL_RECEIVE = "receive";

	private final String EL_SEQUENCE = "sequence";

	private final String EL_PICK = "pick";

	private final String EL_ASSIGN = "assign";
	private final String EL_COPY = "copy";
	private final String EL_FROM = "from";

	private final String EL_LITERAL = "literal";
	private final String EL_TO = "to";

	// Expressions
	private final String EXP_VAL = "val";

	// ReferenceType values
	private final String ON_INSTANTIATION = "onInstantiation";
	@SuppressWarnings("unused")
	private final String FRESH = "fresh";

	// private final String EPR_TYPE = RRS_XSD_NAMESPACE.getPrefix() + ":" +
	// "EPR";

	private final// An array of all by reference influenced element types
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

	private static Transformer transformer = null;

	public static Transformer getTransformer() {
		if (transformer == null) {
			transformer = new Transformer();
		}
		return transformer;
	}

	public String transform(String input, String rrsNamespace) {

		RRS_NAMESPACE = Namespace.getNamespace("rrs", rrsNamespace);

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

			// Read the prefix of the "http://www.w3.org/yyyy/XMLSchema"
			// namespace
			List namespaces = root.getAdditionalNamespaces();
			for (Object n : namespaces) {
				Namespace ns = (Namespace) n;
				if (ns.getURI().contains("XMLSchema")
						&& ns.getURI().contains("http://www.w3.org")) {
					SCHEMA_NS_PREFIX = ns.getPrefix();
				}
			}

			// Set all the new required Imports
			List imports = root.getChildren(EL_IMPORT, BPEL_NAMESPACE);

			// Add the rrs.wsdl to the process imports
			/*
			 * <bpel:import namespace="http://webservices.rrs.simpl.org/"
			 * location="RRSRetrievalService.wsdl"
			 * importType="http://schemas.xmlsoap.org/wsdl/"></bpel:import>
			 * <bpel:import namespace="http://webservices.rrs.simpl.org/"
			 * location="RRSMetaDataService.wsdl"
			 * importType="http://schemas.xmlsoap.org/wsdl/"></bpel:import>
			 */
			Element rrsWSDLImport = new Element(EL_IMPORT, BPEL_NAMESPACE);
			rrsWSDLImport.setAttribute(AT_IMPORT_LOCATION,
					"RRSMetaDataService.wsdl");
			rrsWSDLImport.setAttribute(AT_IMPORT_NS, RRS_NAMESPACE.getURI());
			rrsWSDLImport.setAttribute(AT_IMPORT_ITYPE,
					"http://schemas.xmlsoap.org/wsdl/");
			imports.add(0, rrsWSDLImport);

			rrsWSDLImport = new Element(EL_IMPORT, BPEL_NAMESPACE);
			rrsWSDLImport.setAttribute(AT_IMPORT_LOCATION,
					"RRSRetrievalService.wsdl");
			rrsWSDLImport.setAttribute(AT_IMPORT_NS, RRS_NAMESPACE.getURI());
			rrsWSDLImport.setAttribute(AT_IMPORT_ITYPE,
					"http://schemas.xmlsoap.org/wsdl/");
			imports.add(0, rrsWSDLImport);

			// Create the RRS partnerLinks
			List partnerLinks = root.getChild(EL_PARTNER_LINKS, BPEL_NAMESPACE)
					.getChildren("partnerLink", BPEL_NAMESPACE);

			Element rrsRetrievalPL = new Element(EL_PARTNER_LINK,
					BPEL_NAMESPACE);
			/*
			 * <bpel:partnerLink name="RRS_RET_Type"
			 * partnerLinkType="tns:RRS_RET_Type"
			 * partnerRole="get"></bpel:partnerLink>
			 */
			rrsRetrievalPL.setAttribute(AT_NAME, RRS_RETRIEVAL_PL);
			rrsRetrievalPL.setAttribute(AT_PL_TYPE, RRS_RETRIEVAL_PL_TYPE);
			rrsRetrievalPL.setAttribute(AT_PL_ROLE, RRS_RETRIEVAL_PL_ROLE);

			partnerLinks.add(rrsRetrievalPL);

			Element rrsMetaDataPL = new Element(EL_PARTNER_LINK, BPEL_NAMESPACE);
			/*
			 * <bpel:partnerLink name="RRS_MetaData"
			 * partnerLinkType="tns:RRS_MD_Type"
			 * partnerRole="getEPR"></bpel:partnerLink>
			 */
			rrsMetaDataPL.setAttribute(AT_NAME, RRS_META_DATA_PL);
			rrsMetaDataPL.setAttribute(AT_PL_TYPE, RRS_META_DATA_PL_TYPE);
			rrsMetaDataPL.setAttribute(AT_PL_ROLE, RRS_META_DATA_PL_ROLE);

			partnerLinks.add(rrsMetaDataPL);

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

				addAllVariables(variableElements, name, valueType);
			}

			// Detach referenceVariable elements
			root.removeChild(EL_REFERENCE_VARIABLES, BPEL_NAMESPACE);

			// The main sequence element of the process
			Element parentSequence = root.getChild(EL_SEQUENCE, BPEL_NAMESPACE);

			// The initial instantiation element of the main sequence of the
			// process
			// 1.process will be instantiated by a receive element
			instantiationElement = parentSequence.getChild(EL_RECEIVE,
					BPEL_NAMESPACE);
			if (instantiationElement == null) {
				// 2.process will be instantiated by a pick element
				instantiationElement = parentSequence.getChild(EL_PICK,
						BPEL_NAMESPACE);
			}

			// This is a backup of all invoke elements of the original process
			// file
			invokeElements.addAll(parentSequence.getChildren(EL_INVOKE,
					BPEL_NAMESPACE));

			// Create an onInstantiation sequence for all onInstantiation
			// dereferentiation activities and EPR initialization
			Element onInstSequence = new Element(EL_SEQUENCE, BPEL_NAMESPACE);
			onInstSequence.setAttribute(AT_NAME, "prepareEPRs");

			// Process the highest level sequence
			for (String type : types) {
				processActivityElements(parentSequence, onInstSequence, type);
			}

			// Add the new sequence after the instantiation process element
			parentSequence.addContent(parentSequence
					.indexOf(instantiationElement) + 1, onInstSequence);

			// Now we will insert some new Variables and Invokes to initialize
			// the reference variables (query EPR data from the RRS).
			addEPRInvokes(onInstSequence, variableElements);

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

	/**
	 * @param variableElements
	 * @param name
	 * @param valueType
	 */
	private void addAllVariables(Element variableElements, String name,
			String valueType) {
		/*
		 * <bpel:variable name="data"
		 * messageType="ns:getResponse"></bpel:variable> <bpel:variable
		 * name="dataEPR_Name" messageType="ns:getEPR"></bpel:variable>
		 * <bpel:variable name="dataEPR_Meta"
		 * messageType="ns:getEPRResponse"></bpel:variable> <bpel:variable
		 * name="dataEPR_Ret" messageType="ns:get"></bpel:variable>
		 */
		variableElements.addContent(createVariableElement(name, RRS_NAMESPACE
				.getPrefix()
				+ ":getResponse"));
		variableElements.addContent(createVariableElement(name + "EPR_Name",
				RRS_NAMESPACE.getPrefix() + ":getEPR"));
		variableElements.addContent(createVariableElement(name + "EPR_Meta",
				RRS_NAMESPACE.getPrefix() + ":getEPRResponse"));
		variableElements.addContent(createVariableElement(name + "EPR_Ret",
				RRS_NAMESPACE.getPrefix() + ":get"));
	}

	/**
	 * @param parentSequence
	 * @param variableElements
	 * 
	 */
	private void addEPRInvokes(Element onInstSequence, Element variableElements) {
		int count = 0;

		/*
		 * <bpel:sequence name="prepare"> <bpel:assign validate="no"
		 * name="setNames"> <bpel:copy> <bpel:from> <bpel:literal
		 * xml:space="preserve">asd</bpel:literal> </bpel:from> <bpel:to
		 * part="eprName" variable="dataEPR_Name"></bpel:to> </bpel:copy>
		 * </bpel:assign> <bpel:invoke name="getEPR" partnerLink="RRS_MetaData"
		 * operation="getEPR" portType="ns:RRSMetaDataService"
		 * inputVariable="dataEPR_Name"
		 * outputVariable="dataEPR_Meta"></bpel:invoke> </bpel:sequence>
		 */
		Element assignNameVariables = new Element(EL_ASSIGN, BPEL_NAMESPACE);

		assignNameVariables.setAttribute(AT_NAME, "setEPR_Names");
		assignNameVariables.setAttribute("validate", "no");
		for (String varName : refVarNames.keySet()) {
			// Add a new copy element to the assign, to initialize the generated
			// EPR name variable
			assignNameVariables.addContent(createAssignCopyElement(varName,
					varName + "EPR_Name"));

			onInstSequence.addContent(0, createRRSMetaInvokeElement(varName
					+ "EPR_Meta", varName + "EPR_Name"));
			count++;
		}
		onInstSequence.addContent(0, assignNameVariables);
		count++;

		// If we want to invoke the RRSRetrievalService we have to change the
		// message type of the EPR by
		// copying it to another message container
		/*
		 * <bpel:copy> <bpel:from part="return"
		 * variable="dataEPR_Meta"></bpel:from> <bpel:to part="EPR"
		 * variable="dataEPR_Ret"></bpel:to> </bpel:copy>
		 */
		Element assignEPRMessages = new Element(EL_ASSIGN, BPEL_NAMESPACE);

		assignEPRMessages.setAttribute(AT_NAME, "copyEPRs");
		assignEPRMessages.setAttribute("validate", "no");
		for (String varName : refVarNames.keySet()) {
			// Add a new copy element to the assign, to copy the queried
			// EPR to the RRSRetrieval-messagetype variable
			Element copy = new Element(EL_COPY, BPEL_NAMESPACE);

			Element from = new Element(EL_FROM, BPEL_NAMESPACE);
			from.setAttribute("part", "return");
			from.setAttribute(EL_VARIABLE, varName + "EPR_Meta");
			from.setText("");

			Element to = new Element(EL_TO, BPEL_NAMESPACE);
			to.setAttribute("part", "EPR");
			to.setAttribute(EL_VARIABLE, varName + "EPR_Ret");
			to.setText("");

			copy.addContent(from);
			copy.addContent(to);

			assignEPRMessages.addContent(copy);
		}
		onInstSequence.addContent(count, assignEPRMessages);
	}

	private Element createAssignCopyElement(String value, String varName) {
		/*
		 * <bpel:copy> <bpel:from> <bpel:literal
		 * xml:space="preserve">asd</bpel:literal> </bpel:from> <bpel:to
		 * part="eprName" variable="dataEPR_Name"></bpel:to> </bpel:copy>
		 */
		Element copy = new Element(EL_COPY, BPEL_NAMESPACE);

		Element from = new Element(EL_FROM, BPEL_NAMESPACE);
		Element literal = new Element(EL_LITERAL, BPEL_NAMESPACE);
		literal.setAttribute("space", "preserve", XML_NAMESPACE);
		literal.setText(value);
		from.setText("");
		from.addContent(literal);

		Element to = new Element(EL_TO, BPEL_NAMESPACE);
		to.setAttribute("part", "eprName");
		to.setAttribute(EL_VARIABLE, varName);
		to.setText("");

		copy.addContent(from);
		copy.addContent(to);

		return copy;
	}

	private Element createRRSMetaInvokeElement(String refVarName,
			String eprNameVarName) {
		/*
		 * <bpel:invoke name="getEPR" partnerLink="RRS_MetaData"
		 * operation="getEPR" portType="ns:RRSMetaDataService"
		 * inputVariable="dataEPR_Name"
		 * outputVariable="dataEPR_Meta"></bpel:invoke>
		 */
		Element invoke = new Element(EL_INVOKE, BPEL_NAMESPACE);

		invoke.setAttribute(AT_NAME, "init_" + refVarName);
		invoke.setAttribute(AT_PARTNER_LINK, RRS_META_DATA_PL);
		invoke.setAttribute(AT_OPERATION, RRS_META_DATA_PL_ROLE);
		invoke.setAttribute(AT_PORT_TYPE, RRS_NAMESPACE.getPrefix() + ":"
				+ RRS_META_DATA_PL_PORT);
		invoke.setAttribute(AT_INPUT_VARIABLE, eprNameVarName);
		invoke.setAttribute(AT_OUTPUT_VARIABLE, refVarName);
		invoke.setText("");

		return invoke;
	}

	private Element createVariableElement(String name, String type) {
		Element variable = new Element(EL_VARIABLE, BPEL_NAMESPACE);

		variable.setAttribute(AT_NAME, name);
		variable.setAttribute(AT_VARIABLE_TYPE, type);
		variable.setText("");

		return variable;
	}

	private Element createRRSRetrievalInvokeElement(String valueVarName,
			int count) {
		/*
		 * <bpel:invoke name="deRefEPR" partnerLink="RRS_RET_Type"
		 * operation="get" portType="ns:RRSRetrievalService"
		 * inputVariable="dataEPR_Ret" outputVariable="data"></bpel:invoke>
		 */
		Element invoke = new Element(EL_INVOKE, BPEL_NAMESPACE);

		invoke.setAttribute(AT_NAME, valueVarName + "Refresh_" + count);
		invoke.setAttribute(AT_PARTNER_LINK, RRS_RETRIEVAL_PL);
		invoke.setAttribute(AT_OPERATION, RRS_RETRIEVAL_PL_ROLE);
		invoke.setAttribute(AT_PORT_TYPE, RRS_NAMESPACE.getPrefix() + ":"
				+ RRS_RETRIEVAL_PL_PORT);
		invoke.setAttribute(AT_INPUT_VARIABLE, valueVarName + "EPR_Ret");
		invoke.setAttribute(AT_OUTPUT_VARIABLE, valueVarName);
		invoke.setText("");

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
				// We check only Assign activities at the moment
				processAssignElement(parentSequence, onInstSequence, element);
			}
		}
	}

	private void processReceiveReplyElement(Element onInstSequence,
			Element element) {
		if (refVarNames.containsKey(element.getAttributeValue(AT_VARIABLE))) {
			// This activity has a referenceVariable
			String varName = element.getAttributeValue(AT_VARIABLE);
			element.setAttribute(AT_VARIABLE, varName + "EPR_Ret");
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
				element.setAttribute(AT_INPUT_VARIABLE, varName + "EPR_Ret");
			}

			// Check the output variable
			if (refVarNames.containsKey(element
					.getAttributeValue(AT_OUTPUT_VARIABLE))) {
				// The output variable is a referenceVariable
				String varName = element.getAttributeValue(AT_OUTPUT_VARIABLE);
				element.setAttribute(AT_OUTPUT_VARIABLE, varName + "EPR_Ret");
			}
		}
	}

	private void processInvokeInOnlyElement(Element onInstSequence,
			Element element) {
		if (refVarNames.containsKey(element
				.getAttributeValue(AT_INPUT_VARIABLE))) {
			// This activity has a referenceVariable
			String varName = element.getAttributeValue(AT_INPUT_VARIABLE);
			element.setAttribute(AT_INPUT_VARIABLE, varName + "EPR_Ret");
		}
	}

	private void processAssignElement(Element parentSequence,
			Element onInstSequence, Element element) {
		// Get all copy elements of the assign
		List copyElements = element.getChildren(EL_COPY, BPEL_NAMESPACE);

		// Process all copy elements
		for (Object copyObj : copyElements) {
			Element copy = (Element) copyObj;

			Element from = copy.getChild(EL_FROM, BPEL_NAMESPACE);

			Element to = copy.getChild(EL_TO, BPEL_NAMESPACE);

			String expression = from.getText().trim();

			// Check the expression in the from element
			if (expression.matches(EXP_VAL + "\\([$][a-zA-Z_0-9]+\\)")) {
				/*
				 * <bpel:assign validate="no" name="Assign"> <bpel:copy>
				 * <bpel:from> <![CDATA[val($ReferenceVariable)]]> </bpel:from>
				 * <bpel:to variable="input"></bpel:to> </bpel:copy>
				 * </bpel:assign>
				 */
				// Get the variable name out of the expression
				expression = expression
						.substring(5, expression.indexOf(")", 5));
				if (refVarNames.containsKey(expression)) {
					// The expression contains a reference variable
					String varName = expression;
					Element deRefInvoke = createRRSRetrievalInvokeElement(
							varName, refVarNames.get(varName));
					if (getReferenceVariable(varName).getAttributeValue(
							AT_REFERENCE_TYPE).equals(ON_INSTANTIATION)) {
						// onInstantiation: referenced data should be loaded
						// constant (only one time)
						if (refVarNames.get(varName) == 0) {
							parentSequence.addContent(parentSequence
									.indexOf(instantiationElement) + 1,
									deRefInvoke);

							// Increase the counter for the name of the next RRS
							// invoke activity
							refVarNames.put(varName,
									refVarNames.get(varName) + 1);
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
							refVarNames.put(varName,
									refVarNames.get(varName) + 1);
						}
					}
					// Remove the expression and add a variable attribute to the
					// assign from-part
					from.removeContent();
					from.setAttribute(AT_VARIABLE, varName);
					from.setAttribute("part", "return");
				}
			} else {
				/*
				 * <bpel:assign validate="no" name="Assign"> <bpel:copy>
				 * <bpel:from variable="input"></bpel:from> <bpel:to
				 * variable="input"></bpel:to> </bpel:copy> </bpel:assign>
				 */
				// Check the FROM variable
				if (from.getAttributeValue(AT_VARIABLE) != null
						&& refVarNames.containsKey(from
								.getAttributeValue(AT_VARIABLE))) {
					// Assign from variable is a reference variable
					String varName = from.getAttributeValue(AT_VARIABLE);
					from.setAttribute(AT_VARIABLE, varName + "EPR_Ret");
				}
				// Check the TO variable
				if (to.getAttributeValue(AT_VARIABLE) != null
						&& refVarNames.containsKey(from
								.getAttributeValue(AT_VARIABLE))) {
					// Assign to variable is a reference variable
					String varName = from.getAttributeValue(AT_VARIABLE);
					from.setAttribute(AT_VARIABLE, varName + "EPR_Ret");
				}
			}
		}
	}
}
