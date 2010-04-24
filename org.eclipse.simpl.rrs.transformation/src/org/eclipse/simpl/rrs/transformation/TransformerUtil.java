package org.eclipse.simpl.rrs.transformation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
public class TransformerUtil {

	// Namespace identifiers
	private static final String BPEL_PRÄFIX = "bpel";
	private static final Namespace BPEL_NAMESPACE = Namespace.getNamespace(
			BPEL_PRÄFIX,
			"http://docs.oasis-open.org/wsbpel/2.0/process/executable");
	
	private static final Namespace PLNK_NAMESPACE = Namespace.getNamespace("plnk", "http://docs.oasis-open.org/wsbpel/2.0/plnktype");

	// Element names
	private static final String EL_REFERENCE_VARIABLE = "referenceVariable";
	private static final String EL_REFERENCE_VARIABLES = "referenceVariables";

	// Attribute names
	private static final String AT_NAME = "name";

	private static final String AT_REFERENCE_TYPE = "referenceType";
	private static final String AT_REFERENCE_VALUE_TYPE = "valueType";

	private static final String AT_TARGET_NAMESPACE = "targetNamespace";

	public static List getReferenceVariables(String bpelFilePath) {
		List refVariableElements = new ArrayList();

		SAXBuilder builder = new SAXBuilder();
		Document doc;
		try {
			InputStream inputStream = new FileInputStream(bpelFilePath);

			doc = builder.build(inputStream);

			Element root = doc.getRootElement();

			// read referenceVariable elements
			if (root.getChild(EL_REFERENCE_VARIABLES, BPEL_NAMESPACE) != null) {
				refVariableElements = root.getChild(EL_REFERENCE_VARIABLES,
						BPEL_NAMESPACE).getChildren(EL_REFERENCE_VARIABLE,
						BPEL_NAMESPACE);
			}

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return refVariableElements;
	}

	public static boolean areAllRefVarsFullSpecified(String bpelFilePath) {
		boolean fullSpecified = false;

		List refVariableElements = getReferenceVariables(bpelFilePath);
		for (Object var : refVariableElements) {
			Element element = (Element) var;

			if (null == element)
				continue;
			String name = element.getAttributeValue(AT_NAME);
			String valueType = element
					.getAttributeValue(AT_REFERENCE_VALUE_TYPE);
			String referencType = element.getAttributeValue(AT_REFERENCE_TYPE);

			if (name == null || name.isEmpty()) {
				fullSpecified = false;
			} else if (valueType == null || valueType.isEmpty()) {
				fullSpecified = false;
			} else if (referencType == null || referencType.isEmpty()) {
				fullSpecified = false;
			} else {
				fullSpecified = true;
			}
		}

		return fullSpecified;
	}

	public static void downloadWSDL(String sourceURI, String projectPath,
			String bpelFileName) {
		URL u;
		InputStream is = null;
		BufferedReader buffer;
		StringBuilder string = new StringBuilder();

		File transformDir = new File(projectPath
				+ System.getProperty("file.separator") + bpelFileName
				+ "_transformed");
		if (!transformDir.exists()) {
			transformDir.mkdir();
		}

		File wsdlFileRRS = new File(projectPath
				+ System.getProperty("file.separator") + bpelFileName
				+ "_transformed" + System.getProperty("file.separator")
				+ "rrs.wsdl");

		// Create the rrs.wsdl file, if necassary
		if (!wsdlFileRRS.exists()) {
			try {
				u = new URL(sourceURI);

				is = u.openStream(); // throws an IOException

				buffer = new BufferedReader(new InputStreamReader(is));

				String line;
				while ((line = buffer.readLine()) != null) {
					string.append(line);
					string.append("\n");
				}

			} catch (MalformedURLException mue) {
				mue.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {

				try {
					is.close();
				} catch (IOException ioe) {

				}
			}

			try {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(projectPath
								+ System.getProperty("file.separator")
								+ bpelFileName + "_transformed"
								+ System.getProperty("file.separator")
								+ "rrs.wsdl")));
				out.write(string.toString());
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param string
	 * @param bpelFileName
	 * @return
	 */
	public static String getRRSwsdlNamespace(String projectPath,
			String bpelFileName) {
		File wsdlFileRRS = new File(projectPath
				+ System.getProperty("file.separator") + bpelFileName
				+ "_transformed" + System.getProperty("file.separator")
				+ "rrs.wsdl");

		SAXBuilder builder = new SAXBuilder();
		Document doc;
		try {
			InputStream inputStream = new FileInputStream(wsdlFileRRS);

			doc = builder.build(inputStream);

			Element root = doc.getRootElement();

			String targetNamespace = root
					.getAttributeValue(AT_TARGET_NAMESPACE);

			return targetNamespace;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
	 * This method reads the bpel process wsdl file and adds some definitions
	 * for the rrs partnerLink.
	 * 
	 * @param sourcePath
	 * @param targetPath
	 * @param bpelFileName
	 */
	public static void copyAndEditProcessWSDL(String projectPath,
			String bpelFileName) {
		File projectFolder = new File(projectPath);

		File wsdlSourceFile = null;
		
		File[] files = projectFolder.listFiles(new FileFilter() {
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".wsdl");
			}
		});
		
		for (File file : files) {
			if (file.getName().contains(bpelFileName)) {
				wsdlSourceFile = file;
			}
		}

		File wsdlTargetFile = new File(projectPath
				+ System.getProperty("file.separator") + bpelFileName
				+ "_transformed" + System.getProperty("file.separator")
				+ wsdlSourceFile.getName());

		if (wsdlSourceFile != null){
			SAXBuilder builder = new SAXBuilder();
			Document doc;
			try {
				InputStream inputStream = new FileInputStream(wsdlSourceFile);

				doc = builder.build(inputStream);

				Element root = doc.getRootElement();
				
				//Do the work
				//TODO: An René's Implementierung anpassen
				
				String rrsNamespace = getRRSwsdlNamespace(projectPath,bpelFileName);
				
				//Add the rrs.wsdl namespace to the process wsdl
				root.addNamespaceDeclaration(Namespace.getNamespace("rrs", rrsNamespace));
				
				/*
				 * Insert the following element to the process wsdl file
				 * 	<plnk:partnerLinkType name="RRSRetrievalType">
    			 *		<plnk:role name="GET" portType="rrs:RRSRetrievalService"/>
  				 *	</plnk:partnerLinkType>
    			 *	<import location="rrs.wsdl" namespace="http://webservices.rrs.simpl.org/"/>
				 */
				Element partnerLink = new Element("partnerLinkType", PLNK_NAMESPACE);
				partnerLink.setAttribute(AT_NAME, "RRSRetrievalType");
				Element role = new Element("role", PLNK_NAMESPACE);
				role.setAttribute(AT_NAME, "GET");
				role.setAttribute("portType", "rrs:RRSRetrievalService");
				partnerLink.addContent(role);
				
				Element importElement = new Element("import");
				importElement.setAttribute("location", "rrs.wsdl");
				importElement.setAttribute("namespace", rrsNamespace);
				
				//Add the new element to the root object
				root.addContent(0, importElement);
				root.addContent(0, partnerLink);
				
				OutputStream outputStream = new FileOutputStream(wsdlTargetFile);
				
				XMLOutputter outp = new XMLOutputter();
				outp.setFormat(Format.getPrettyFormat());
				outp.output(doc, outputStream);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
