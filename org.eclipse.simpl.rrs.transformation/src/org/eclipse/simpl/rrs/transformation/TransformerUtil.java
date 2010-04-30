package org.eclipse.simpl.rrs.transformation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
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

	private static final Namespace PLNK_NAMESPACE = Namespace.getNamespace(
			"plnk", "http://docs.oasis-open.org/wsbpel/2.0/plnktype");

	private static final Namespace WSDL_NAMESPACE = Namespace
			.getNamespace("http://schemas.xmlsoap.org/wsdl/");

	// Element names
	private static final String EL_REFERENCE_VARIABLE = "referenceVariable";
	private static final String EL_REFERENCE_VARIABLES = "referenceVariables";

	// Attribute names
	private static final String AT_NAME = "name";

	private static final String AT_REFERENCE_TYPE = "referenceType";
	private static final String AT_REFERENCE_VALUE_TYPE = "valueType";

	private static final String AT_TARGET_NAMESPACE = "targetNamespace";

	public static final String RRS_RETRIEVAL_FILE = "rrsRetrieval.wsdl";
	public static final String RRS_META_DATA_FILE = "rrsMetaData.wsdl";
	public static final String RRS_RETRIEVAL_PREFIX = "rrsRet";
	public static final String RRS_META_DATA_PREFIX = "rrsMeta";

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
			String targetFileName) {
		URL u;
		InputStream is = null;
		BufferedReader buffer;
		StringBuilder string = new StringBuilder();

		// File transformDir = new File(projectPath
		// + System.getProperty("file.separator") + bpelFileName
		// + "_transformed");
		// if (!transformDir.exists()) {
		// transformDir.mkdir();
		// }

		File wsdlFileRRS = new File(projectPath
				+ System.getProperty("file.separator") + targetFileName);

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
						new FileOutputStream(wsdlFileRRS)));
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

	public static String getRRSwsdlNamespace(String projectPath,
			String targetFileName) {
		File wsdlFileRRS = new File(projectPath
				+ System.getProperty("file.separator") + targetFileName);

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
			String bpelFileName, String targetPath) {
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

		File wsdlTargetFile = new File(targetPath
				+ System.getProperty("file.separator")
				+ wsdlSourceFile.getName());

		if (wsdlSourceFile != null) {
			SAXBuilder builder = new SAXBuilder();
			Document doc;
			try {
				InputStream inputStream = new FileInputStream(wsdlSourceFile);

				doc = builder.build(inputStream);

				Element root = doc.getRootElement();

				// Do the work
				// TODO: An René's Implementierung anpassen

				String rrsRetrievalNs = getRRSwsdlNamespace(targetPath,
						RRS_RETRIEVAL_FILE);

				// Add the rrsRetrieval.wsdl (same as rrsMetaData.wsdl)
				// namespace to the process wsdl
				root.addNamespaceDeclaration(Namespace.getNamespace(
						RRS_RETRIEVAL_PREFIX, rrsRetrievalNs));

				String rrsMetaDataNs = getRRSwsdlNamespace(targetPath,
						RRS_META_DATA_FILE);

				// Add the rrsRetrieval.wsdl (same as rrsMetaData.wsdl)
				// namespace to the process wsdl
				root.addNamespaceDeclaration(Namespace.getNamespace(
						RRS_META_DATA_PREFIX, rrsMetaDataNs));

				/*
				 * Insert the following element to the process wsdl file
				 * <plnk:partnerLinkType name="RRSRetrievalType"> <plnk:role
				 * name="GET" portType="rrsRet:RRSRetrievalService"/>
				 * </plnk:partnerLinkType> <import location="rrsRetrieval.wsdl"
				 * namespace="http://webservices.rrs.simpl.org/"/>
				 */
				Element partnerLink = new Element("partnerLinkType",
						PLNK_NAMESPACE);
				partnerLink.setAttribute(AT_NAME, "RRSRetrievalType");
				Element role = new Element("role", PLNK_NAMESPACE);
				role.setAttribute(AT_NAME, "get");
				role.setAttribute("portType", RRS_RETRIEVAL_PREFIX
						+ ":RRSRetrievalService");
				partnerLink.addContent(role);

				Element importElement = new Element("import");
				importElement.setAttribute("location", RRS_RETRIEVAL_FILE);
				importElement.setAttribute("namespace", rrsRetrievalNs);
				importElement.setNamespace(WSDL_NAMESPACE);

				/*
				 * Insert the following element to the process wsdl file
				 * <plnk:partnerLinkType name="RRSMetaDataType"> <plnk:role
				 * name="getEPR" portType="rrsMeta:RRSMetaDataService"/>
				 * </plnk:partnerLinkType> <import location="rrsMetaData.wsdl"
				 * namespace="http://webservices.rrs.simpl.org/"/>
				 */
				Element partnerLink2 = new Element("partnerLinkType",
						PLNK_NAMESPACE);
				partnerLink2.setAttribute(AT_NAME, "RRSMetaDataType");
				Element role2 = new Element("role", PLNK_NAMESPACE);
				role2.setAttribute(AT_NAME, "getEPR");
				role2.setAttribute("portType", RRS_META_DATA_PREFIX
						+ ":RRSMetaDataService");
				partnerLink2.addContent(role2);

				Element importElement2 = new Element("import");
				importElement2.setAttribute("location", RRS_META_DATA_FILE);
				importElement2.setAttribute("namespace", rrsMetaDataNs);
				importElement2.setNamespace(WSDL_NAMESPACE);

				// Add the new elements to the root object
				root.addContent(0, importElement2);
				root.addContent(0, partnerLink2);
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

	/**
	 * @param string
	 * @param osString
	 */
	public static void copyAllNotTransfContent(String sourceProjectPath,
			String targetProjectPath) {
		File sourceProject = new File(sourceProjectPath);

		FileFilter filter = new FileFilter() {

			@Override
			public boolean accept(File file) {
				boolean accept = file.getName().endsWith("bpel")
						|| file.getName().endsWith("wsdl")
						|| file.getName().endsWith("xsd")
						|| file.getName().equals("deploy.xml");
				
				return accept;
			}

		};

		for (File file : sourceProject.listFiles(filter)) {
			File outputFile = new File(targetProjectPath
					+ System.getProperty("file.separator") + file.getName());
			if (!outputFile.exists()) {
				copyFile(file, outputFile);
			}
		}

	}

	private static void copyFile(File inputFile, File outputFile) {
		try {
			FileReader in = new FileReader(inputFile);
			FileWriter out = new FileWriter(outputFile);

			int c;

			while ((c = in.read()) != -1) {
				out.write(c);
			}

			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
