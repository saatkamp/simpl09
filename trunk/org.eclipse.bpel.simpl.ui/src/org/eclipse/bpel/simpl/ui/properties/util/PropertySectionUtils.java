package org.eclipse.bpel.simpl.ui.properties.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.simpl.uddi.model.ModelProvider;
import org.eclipse.simpl.uddi.model.datasource.DataSource;
import org.eclipse.bpel.model.Process;
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

	private static final String AT_NAME = "name";

	private static final String EL_DATASOURCE = "datasources";
	
	private static final String AT_DATA_SOURCE_NAME = "dataSourceName";

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

	private static List<String> getDeploymentDescriptorDatasources(Process process) {
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
							datasources.add("dd:"+name);
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
	
	private static List<String> getUDDIDatasources(){
		List<String> dataSourceNames = new ArrayList<String>();
		
		List<DataSource> dataSources = ModelProvider.getInstance().getDataSources();
		
		for (DataSource dat : dataSources){
			dataSourceNames.add("uddi:"+dat.getName());
		}
		
		return dataSourceNames;
	}
		
	public static String[] getAllDataSources(Process process){
		List<String> datasources = new ArrayList<String>();
		
		datasources.addAll(getDeploymentDescriptorDatasources(process));
		datasources.addAll(getUDDIDatasources());
		
		return datasources.toArray(new String[0]);
	}
}
