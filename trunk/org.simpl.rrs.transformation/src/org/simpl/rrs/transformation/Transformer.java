package org.simpl.rrs.transformation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
	
	private static final String REFERENCE_NAME = "name";
	private static final String REFERENCE_TYPE = "referenceType";
	private static final String REFERENCE_VALUE_TYPE = "valueType";
	private static final String REFERENCE_VARIABLE_NAME = "referenceVariable";
	private static final String REFERENCE_VARIABLES_NAME = "referenceVariables";

	private static final String VARIABLE_NAME = "variable";
	private static final String VARIABLE_TYPE = "type";
	
	private static final String IMPORT = "import";
	private static final String PARTNER_LINK = "partnerLink";

	// TODO: EPR muss noch irgendwie in das BPEL Namespace File oder in ein
	// separates, dass dann in den BPEL-Prozess integriert wird.
	// TODO: Am besten ein eigenständiges xsd-file mit der EPR mit rrs-Präfix
	private static final String EPR_TYPE = "EPR";

	private static Transformer transformer = null;

	public static Transformer getTransformer() {
		if (transformer == null) {
			transformer = new Transformer();
		}
		return transformer;
	}

	
	public String transform(String input) {
		Namespace bpelNamespace = Namespace.getNamespace("bpel", "http://docs.oasis-open.org/wsbpel/2.0/process/executable");
		
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input
				.getBytes());

		SAXBuilder builder = new SAXBuilder();
		Document doc;
		try {
			doc = builder.build(inputStream);

			// Start Transformation
			Element root = doc.getRootElement();

			//Set all the Imports
			List imports = root.getChildren(IMPORT);
			imports.addAll(addImports(bpelNamespace));
			
			//Create the RRS partnerLink
			List partnerLinks = root.getChildren(PARTNER_LINK);
			Element rrsPartnerLink = new Element(PARTNER_LINK, bpelNamespace);
			//TODO: Hier noch die richtigen Werte setzen
			rrsPartnerLink.setAttribute("name","");
			rrsPartnerLink.setAttribute("partnerLinkType","");
			rrsPartnerLink.setAttribute("myRole","");

			partnerLinks.add(rrsPartnerLink);
			
			// referenceVariables element
			Element refVariablesElement = root
					.getChild(REFERENCE_VARIABLES_NAME);

			// referenceVariable elements
			List refVariableElements = refVariablesElement
					.getChildren(REFERENCE_VARIABLE_NAME);

			// variables element
			List variableElements = root.getChildren(VARIABLE_NAME);

			for (int i = 0; i < refVariableElements.size(); i++) {
				// Find searched element with given attribute:
				Element elMain = (Element) (refVariableElements.get(i));
				if (null == elMain)
					continue;
				String name = elMain.getAttributeValue(REFERENCE_NAME);
				String referenceType = elMain.getAttributeValue(REFERENCE_TYPE);
				String valueType = elMain
						.getAttributeValue(REFERENCE_VALUE_TYPE);

				variableElements.add(createVariableElement(name, valueType, bpelNamespace));
				variableElements.add(createVariableElement(name+"EPR", EPR_TYPE, bpelNamespace));
			}

			//Insert the dereferentiation activities to the process
			//TODO: Hier müssen dann auch die PartnerLinks in den Dereferenzierungsaktivitäten
			//auf das RRS gesetzt werden.
			
			
			
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

	private Element createVariableElement(String name, String type, Namespace namespace) {
		Element variable = new Element(VARIABLE_NAME, namespace);

		variable.setAttribute(REFERENCE_NAME, name);
		variable.setAttribute(VARIABLE_TYPE, type);

		return variable;
	}

	private Collection<Object> addImports(Namespace namespace) {
		Collection<Object> imports = new ArrayList<Object>();
		
		/*
		 * <bpel:import location="asdArtifacts.wsdl" namespace="asd" 
	        importType="http://schemas.xmlsoap.org/wsdl/"
		 */
		//TODO: Noch an René's Implementierung anpassen
		Element importElement = new Element(IMPORT, namespace);
		importElement.setAttribute("location", "rrs.wsdl");
		importElement.setAttribute("namespace", "http://uni-stuttgart.de/simpl/rrs");
		importElement.setAttribute("importType", "http://schemas.xmlsoap.org/wsdl/");
		
		//Hier können noch andere Imports eingefügt werden
		imports.add(importElement);
		
		return imports;
	}
}
