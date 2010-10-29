package org.eclipse.bpel.simpl.ui.properties.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.simpl.communication.SIMPLCommunication;
import org.eclipse.simpl.resource.management.model.ModelProvider;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.simpl.core.webservices.client.Authentication;
import org.simpl.core.webservices.client.DataSource;

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
public class PropertySectionUtils {

	private static final String EL_PROCESS = "process";
	private static final String EL_DATASOURCE = "datasources";

	private static final String AT_NAME = "name";
	private static final String AT_DATA_SOURCE_NAME = "dataSourceName";
	private static final String AT_DATA_SOURCE_ADDRESS = "address";
	private static final String AT_DATA_SOURCE_TYPE = "type";
	private static final String AT_DATA_SOURCE_SUBTYPE = "subtype";
	private static final String AT_DATA_SOURCE_LANG = "language";
	private static final String AT_DATA_SOURCE_USERNAME = "userName";
	private static final String AT_DATA_SOURCE_PASSWORD = "password";
	private static String AT_DATA_FORMAT = "format";

	/**
	 * Die BPEL Datei des Prozesses
	 */
	private static IFile bpelFile = null;

	/**
	 * Der Pfad zur BPEL Datei des Prozesses
	 */
	private static IPath bpelPath = null;

	/**
	 * Pfad zum Projektordner = Pfad zur BPEL Datei ohne Dateiendung
	 */
	private static IPath projectPath = null;

	/**
	 * Absoluter Workspace-Pfad. Pfad in Format "OSString" umwandeln, so dass
	 * man mit java.io arbeiten kann.
	 */
	private static String absolutWorkspacePath = "";

	private final static Namespace DD_NAMESPACE = Namespace
			.getNamespace("http://www.apache.org/ode/schemas/dd/2007/03");

	private static final String RM_PREFIX = "rm";

	private static final String DD_PREFIX = "dd";

	@SuppressWarnings("rawtypes")
  private static DataSource findDeploymentDescriptorDatasourceByName(
			Process process, String dsName) {
		DataSource datasource = null;

		bpelFile = BPELUtil.getBPELFile(process);

		bpelPath = bpelFile.getFullPath();

		projectPath = bpelPath.removeFileExtension().removeLastSegments(1);

		absolutWorkspacePath = ResourcesPlugin.getWorkspace().getRoot()
				.getLocation().toOSString();

		File deploy = new File(absolutWorkspacePath + projectPath.toOSString()
				+ System.getProperty("file.separator") + "deploy.xml");

		if (deploy.exists()) {
			SAXBuilder builder = new SAXBuilder();
			Document doc;
			try {
				InputStream inputStream = new FileInputStream(deploy);

				doc = builder.build(inputStream);

				Element root = doc.getRootElement();

				List processes = root.getChildren(EL_PROCESS, DD_NAMESPACE);

				for (Object processObj : processes) {
					Element processElement = (Element) processObj;
					if (processElement.getAttributeValue(AT_NAME).contains(
							process.getName())) {
						List datasourceElements = processElement.getChildren(
								EL_DATASOURCE, DD_NAMESPACE);
						for (Object data : datasourceElements) {
							String name = ((Element) data)
									.getAttributeValue(AT_DATA_SOURCE_NAME);
							if (name.equals(dsName)) {
								datasource = new DataSource();
								datasource.setName(name);
								datasource
										.setAddress(((Element) data)
												.getAttributeValue(AT_DATA_SOURCE_ADDRESS));
								datasource
										.setType(((Element) data)
												.getAttributeValue(AT_DATA_SOURCE_TYPE));
								datasource
										.setSubType(((Element) data)
												.getAttributeValue(AT_DATA_SOURCE_SUBTYPE));
								datasource
										.setLanguage(((Element) data)
												.getAttributeValue(AT_DATA_SOURCE_LANG));
								datasource.setDataFormat(((Element) data)
										.getAttributeValue(AT_DATA_FORMAT));
								Authentication authent = new Authentication();
								authent
										.setUser(((Element) data)
												.getAttributeValue(AT_DATA_SOURCE_USERNAME));
								authent
										.setPassword(((Element) data)
												.getAttributeValue(AT_DATA_SOURCE_PASSWORD));
								datasource.setAuthentication(authent);
							}
						}
					}
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
		}

		return datasource;
	}

	@SuppressWarnings("rawtypes")
  private static List<String> getDeploymentDescriptorDatasourceNames(
			Process process) {
		List<String> datasources = new ArrayList<String>();

		bpelFile = BPELUtil.getBPELFile(process);

		bpelPath = bpelFile.getFullPath();

		projectPath = bpelPath.removeFileExtension().removeLastSegments(1);

		absolutWorkspacePath = ResourcesPlugin.getWorkspace().getRoot()
				.getLocation().toOSString();

		File deploy = new File(absolutWorkspacePath + projectPath.toOSString()
				+ System.getProperty("file.separator") + "deploy.xml");

		if (deploy.exists()) {
			SAXBuilder builder = new SAXBuilder();
			Document doc;
			try {
				InputStream inputStream = new FileInputStream(deploy);

				doc = builder.build(inputStream);

				Element root = doc.getRootElement();

				List processes = root.getChildren(EL_PROCESS, DD_NAMESPACE);

				for (Object processObj : processes) {
					Element processElement = (Element) processObj;
					if (processElement.getAttributeValue(AT_NAME).contains(
							process.getName())) {
						List datasourceElements = processElement.getChildren(
								EL_DATASOURCE, DD_NAMESPACE);
						for (Object data : datasourceElements) {
							String name = ((Element) data)
									.getAttributeValue(AT_DATA_SOURCE_NAME);
							datasources.add(DD_PREFIX + ":" + name);
						}
					}
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
		}

		return datasources;
	}

	private static List<String> getRFDatasourceNames() {
		List<String> dataSourceNames = new ArrayList<String>();

		List<DataSource> dataSources = ModelProvider.getInstance()
				.getDataSources();

		for (DataSource dat : dataSources) {
			dataSourceNames.add(RM_PREFIX + ":" + dat.getName());
		}

		return dataSourceNames;
	}

	public static String[] getAllDataSourceNames(Process process) {
		List<String> datasources = new ArrayList<String>();

		datasources.addAll(getDeploymentDescriptorDatasourceNames(process));
		datasources.addAll(getRFDatasourceNames());

		return datasources.toArray(new String[0]);
	}

	public static DataSource findDataSourceByName(Process process,
			String nameWithPrefix) {
		DataSource data = null;

		String[] name = nameWithPrefix.split(":");
		if (name[0].equals(DD_PREFIX)) {
			data = findDeploymentDescriptorDatasourceByName(process, name[1]);
		} else {
			if (name[0].equals(RM_PREFIX)) {
				data = ModelProvider.getInstance()
						.findDataSourceByName(name[1]);
			}
		}
		return data;
	}

	public static void downloadSchema(DataSource dataSource, Process process) {

		if (SIMPLCommunication.getConnection().isSIMPLCoreAvailable()
				&& dataSource != null) {
			bpelFile = BPELUtil.getBPELFile(process);

			bpelPath = bpelFile.getFullPath();

			projectPath = bpelPath.removeFileExtension().removeLastSegments(1);

			absolutWorkspacePath = ResourcesPlugin.getWorkspace().getRoot()
					.getLocation().toOSString();

			if (dataSource.getDataFormat() != null) {

				IPath xsdPath = projectPath.append(dataSource.getDataFormat())
						.addFileExtension(IBPELUIConstants.EXTENSION_XSD);

				File xsdFileSIMPL = new File(absolutWorkspacePath
						+ xsdPath.toOSString());

				if (!xsdFileSIMPL.exists()) {
					try {

						// TODO: Im Moment gibt es ja nur ein Datenformat
						// pro
						// Datenquelle, trotzdem muss die Implementierung
						// so angepasst werden, dass das Schema über den
						// Datenformat-Namen und nicht über ein
						// DataSource-Objekt ausgelesen werden kann
						// (=>Eindeutigkeit)
						String stream = null;
						try {
							stream = SIMPLCommunication.getConnection()
									.getDataFormatSchema(dataSource);
						} catch (Exception e) {
						}
						if (stream != null && xsdFileSIMPL.createNewFile()) {

							OutputStreamWriter out = new OutputStreamWriter(
									new FileOutputStream(xsdFileSIMPL
											.getAbsolutePath()), "UTF-8");

							out.write(stream.toCharArray());
							out.close();

						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
	}
}
