package org.apache.ode.simpl.ea;

import java.util.List;

import org.apache.ode.bpel.rtrep.common.extension.AbstractSyncExtensionOperation;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.simpl.ea.util.DeploymentInfos;
import org.apache.ode.simpl.ea.util.StatementUtils;
import org.simpl.resource.management.client.DataSource;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

public abstract class DataManagementActivity extends
		AbstractSyncExtensionOperation {

	private String dsStatement;
	private String dsAddress;
	private String dsType;
	private String dsSubType;
	private String dsLanguage;
	private String activityName;
	public boolean successfulExecution;
	
	private static String deployDir = "";
	private static String processName = "";

	/**
	 * Loads all standard attributes of a DataManagementActivity.
	 * 
	 * @param context in which the SIMPL activities are executed.
	 * @param element which represents an DataManagementActivity.
	 */
	protected void loadSIMPLAttributes(ExtensionContext context, Element element) {
		this.dsStatement = element.getAttribute("dsStatement").toString();
		this.dsAddress = element.getAttribute("dsAddress").toString();
		this.dsType = element.getAttribute("dsType").toString();
		this.dsSubType = element.getAttribute("dsKind").toString();
		this.dsLanguage = element.getAttribute("dsLanguage").toString();
		this.activityName = element.getAttribute("name").toString();
		
		DataManagementActivity.setProcessName(context.getOActivity().getOwner().getName());
		DataManagementActivity.setDeployDir(context.getDUDir().getPath() + "deploy.xml");
	}

	/**
	 * Returns the DataSource object on which the given activity points.
	 * 
	 * @param activityName of which the data source should get.
	 * @param dataSourceName of the queried data source.
	 * @return The DataSource object which matches to the given parameters.
	 */
	public DataSource getDataSource(String activityName, String dataSourceName) {
		return DeploymentInfos.getActivityDataSource(activityName, dataSourceName);
	}

	/**
	 * Getter for the activity name attribute.
	 * 
	 * @return name of the activity.
	 */
	public String getActivityName() {
		return activityName;
	}

	public String getDsStatement(ExtensionContext context) {
		return StatementUtils.processStatement(context, dsStatement);
	}

	/**
	 * Getter for the activity data source address attribute.
	 * 
	 * @return address of the activity data source.
	 */
	public String getDsAddress() {
		return dsAddress;
	}

	/**
	 * Getter for the activity data source type attribute.
	 * 
	 * @return type of the activity data source.
	 */
	public String getDsType() {
		return dsType;
	}

	/**
	 * Getter for the activity data source subtype attribute.
	 * 
	 * @return subtype of the activity data source.
	 */
	public String getDsSubType() {
		return dsSubType;
	}

	/**
	 * Getter for the activity data source language attribute.
	 * 
	 * @return language of the activity data source.
	 */
	public String getDsLanguage() {
		return dsLanguage;
	}

	/**
	 * Getter for the path of the deployment directory.
	 * 
	 * @return path to the deployment directory of the process.
	 */
	public static String getDeployDir() {
		return deployDir;
	}

	/**
	 * Getter for the current process name.
	 * 
	 * @return current processed process name.
	 */
	public static String getProcessName() {
		return processName;
	}

	/**
	 * Setter for the path of the deployment directory.
	 * 
	 * @param deployDir to set.
	 */
	public static void setDeployDir(String deployDir) {
		DataManagementActivity.deployDir = deployDir;
	}

	/**
	 * Setter for the current process name.
	 * 
	 * @param processName to set.
	 */
	public static void setProcessName(String processName) {
		DataManagementActivity.processName = processName;
	}

	// These two methods are just for testing the implementation
	@SuppressWarnings("unchecked")
	public static void printDataObject(ExtensionContext context,
			DataObject dataObject, int indent) {
		// For each Property
		List<DataObject> properties = (List<DataObject>) dataObject
				.getInstanceProperties();
		for (int p = 0, size = properties.size(); p < size; p++) {
			if (dataObject.isSet(p)) {
				Property property = (Property) properties.get(p);
				if (property.isMany()) {
					// For many-valued properties, process a list of values
					List<DataObject> values = (List<DataObject>) dataObject
							.getList(p);
					for (int v = 0, count = values.size(); v < count; v++) {
						printValue(context, values.get(v), property, indent);
					}
				} else {
					// For single-valued properties, print out the value
					printValue(context, dataObject.get(p), property, indent);
				}
			}
		}
	}

	public static void printValue(ExtensionContext context, Object value,
			Property property, int indent) {
		// Get the name of the property
		String propertyName = property.getName();
		// Construct a string for the proper indentation
		String margin = "";
		for (int i = 0; i < indent; i++)
			margin += "\t";
		if (value != null && property.isContainment()) {
			// For containment properties, display the value
			// with printDataObject
			Type type = property.getType();
			String typeName = type.getName();
			context
					.printToConsole((margin + propertyName + " (" + typeName + "):"));
			printDataObject(context, (DataObject) value, indent + 1);
		} else {
			// For non-containment properties, just print the value
			context.printToConsole(margin + propertyName + ": " + value);
		}
	}

}
