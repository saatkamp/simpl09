package org.apache.ode.simpl.ea;

import java.util.List;

import org.apache.ode.bpel.rtrep.common.extension.AbstractSyncExtensionOperation;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.simpl.ea.util.StatementUtils;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

public abstract class DataManagementActivity extends
		AbstractSyncExtensionOperation {

	private String dmCommand;
	private String dataResource;
	private String activityName;
	public boolean successfulExecution;
	
	private static String processName = "";

	/**
	 * Loads all standard attributes of a DataManagementActivity.
	 * 
	 * @param context in which the SIMPL activities are executed.
	 * @param element which represents an DataManagementActivity.
	 */
	protected void loadSIMPLAttributes(ExtensionContext context, Element element) {
		this.dmCommand = element.getAttribute("dmCommand").toString();
		this.dataResource = element.getAttribute("dataResource").toString();
		this.activityName = element.getAttribute("name").toString();
		
		DataManagementActivity.setProcessName(context.getOActivity().getOwner().getName());
	}
	
	/**
	 * Getter for the activity name attribute.
	 * 
	 * @return name of the activity.
	 */
	public String getActivityName() {
		return activityName;
	}

	public String getDmCommand(ExtensionContext context) {
		return StatementUtils.processStatement(context, dmCommand);
	}

	/**
	 * Getter for the activity data source id attribute.
	 * 
	 * @return id of the activity data source.
	 */
	public String getDataResource() {
		return dataResource;
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
