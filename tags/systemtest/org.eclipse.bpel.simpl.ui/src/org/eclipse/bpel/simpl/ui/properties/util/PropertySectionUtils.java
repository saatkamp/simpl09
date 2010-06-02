package org.eclipse.bpel.simpl.ui.properties.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.simpl.communication.client.Authentication;
import org.eclipse.simpl.communication.client.DataSource;
import org.eclipse.simpl.uddi.model.ModelProvider;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

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

	private final static Namespace DD_NAMESPACE = Namespace.getNamespace("http://www.apache.org/ode/schemas/dd/2007/03");

	private static final String UDDI_PREFIX = "uddi";

	private static final String DD_PREFIX = "dd";

	private static DataSource findDeploymentDescriptorDatasourceByName(Process process, String dsName) {
		DataSource datasource = null;

		bpelFile = BPELUtil.getBPELFile(process);

		bpelPath = bpelFile.getFullPath();

		projectPath = bpelPath.removeFileExtension()
				.removeLastSegments(1);

		absolutWorkspacePath = ResourcesPlugin.getWorkspace().getRoot()
				.getLocation().toOSString();
		
		File deploy = new File(absolutWorkspacePath+projectPath.toOSString()+System.getProperty("file.separator")+"deploy.xml");
		
		if (deploy.exists()){
			SAXBuilder builder = new SAXBuilder();
			Document doc;
			try {
				InputStream inputStream = new FileInputStream(deploy);

				doc = builder.build(inputStream);

				Element root = doc.getRootElement();

				List processes = root.getChildren(EL_PROCESS, DD_NAMESPACE);
				
				for (Object processObj : processes){
					Element processElement = (Element)processObj;
					if (processElement.getAttributeValue(AT_NAME).contains(process.getName())){
						List datasourceElements = processElement.getChildren(EL_DATASOURCE, DD_NAMESPACE);
						for (Object data : datasourceElements){
							String name = ((Element) data).getAttributeValue(AT_DATA_SOURCE_NAME);
							if (name.equals(dsName)){
								datasource = new DataSource();
								datasource.setName(name);
								datasource.setAddress(((Element) data).getAttributeValue(AT_DATA_SOURCE_ADDRESS));
								datasource.setType(((Element) data).getAttributeValue(AT_DATA_SOURCE_TYPE));
								datasource.setSubType(((Element) data).getAttributeValue(AT_DATA_SOURCE_SUBTYPE));
								datasource.setLanguage(((Element) data).getAttributeValue(AT_DATA_SOURCE_LANG));
								Authentication authent = new Authentication();
								authent.setUser(((Element) data).getAttributeValue(AT_DATA_SOURCE_USERNAME));
								authent.setPassword(((Element) data).getAttributeValue(AT_DATA_SOURCE_PASSWORD));
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
	
	private static List<String> getDeploymentDescriptorDatasourceNames(Process process) {
		List<String> datasources = new ArrayList<String>();

		bpelFile = BPELUtil.getBPELFile(process);

		bpelPath = bpelFile.getFullPath();

		projectPath = bpelPath.removeFileExtension()
				.removeLastSegments(1);

		absolutWorkspacePath = ResourcesPlugin.getWorkspace().getRoot()
				.getLocation().toOSString();
		
		File deploy = new File(absolutWorkspacePath+projectPath.toOSString()+System.getProperty("file.separator")+"deploy.xml");
		
		if (deploy.exists()){
			SAXBuilder builder = new SAXBuilder();
			Document doc;
			try {
				InputStream inputStream = new FileInputStream(deploy);

				doc = builder.build(inputStream);

				Element root = doc.getRootElement();

				List processes = root.getChildren(EL_PROCESS, DD_NAMESPACE);
				
				for (Object processObj : processes){
					Element processElement = (Element)processObj;
					if (processElement.getAttributeValue(AT_NAME).contains(process.getName())){
						List datasourceElements = processElement.getChildren(EL_DATASOURCE, DD_NAMESPACE);
						for (Object data : datasourceElements){
							String name = ((Element) data).getAttributeValue(AT_DATA_SOURCE_NAME);
							datasources.add(DD_PREFIX+":"+name);
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
	
	private static List<String> getUDDIDatasourceNames(){
		List<String> dataSourceNames = new ArrayList<String>();
		
		List<DataSource> dataSources = ModelProvider.getInstance().getDataSources();
		
		for (DataSource dat : dataSources){
			dataSourceNames.add(UDDI_PREFIX+":"+dat.getName());
		}
		
		return dataSourceNames;
	}
		
	public static String[] getAllDataSourceNames(Process process){
		List<String> datasources = new ArrayList<String>();
		
		datasources.addAll(getDeploymentDescriptorDatasourceNames(process));
		datasources.addAll(getUDDIDatasourceNames());
		
		return datasources.toArray(new String[0]);
	}
	
	public static DataSource findDataSourceByName(Process process, String nameWithPrefix){
		DataSource data = null;
		
		String[] name = nameWithPrefix.split(":");
		if (name[0].equals(DD_PREFIX)){
			data = findDeploymentDescriptorDatasourceByName(process, name[1]);
		}else {
			if (name[0].equals(UDDI_PREFIX)){
				data = ModelProvider.getInstance().findDataSourceByName(name[1]);
			}
		}
		return data;
	}
}
