package org.apache.ode.simpl.ea;

import java.util.List;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.rtrep.common.extension.AbstractSyncExtensionOperation;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.simpl.ea.util.DeploymentInfos;
import org.apache.ode.simpl.ea.util.StatementUtils;
import org.simpl.core.services.datasource.DataSource;
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
	public boolean successfullExecution;

	private DeploymentInfos infos;

	protected void loadSIMPLAttributes(ExtensionContext context, Element element)
			throws FaultException {
		this.dsStatement = element.getAttribute("dsStatement").toString();
		this.dsAddress = element.getAttribute("dsAddress").toString();
		this.dsType = element.getAttribute("dsType").toString();
		this.dsSubType = element.getAttribute("dsKind").toString();
		this.dsLanguage = element.getAttribute("dsLanguage").toString();
		this.activityName = element.getAttribute("name").toString();
	}

	public void loadDeployInformation(ExtensionContext context, Element element) {
		// Path to the deployment descriptor
		String path = context.getDUDir().getPath()+"deploy.xml";
		// Process name
		String processName = context.getOActivity().getOwner().getName();

		// Die Informationen sollen nur einmal gelesen werden
		if (infos == null) {
			infos = new DeploymentInfos(path, processName);
		}
	}

	public DataSource getDataSource(String activityName, String dataSourceName) {
		return infos.getActivityDataSource(activityName, dataSourceName);
	}

	public String getActivityName() {
		return activityName;
	}

	public String getDsStatement(ExtensionContext context) {
		return StatementUtils.processStatement(context, dsStatement);
	}

	public String getDsAddress() {
		return dsAddress;
	}

	public String getDsType() {
		return dsType;
	}

	public String getDsSubType() {
		return dsSubType;
	}

	public String getDsLanguage() {
		return dsLanguage;
	}

	// Diese beiden Methoden sind nur zu Testzwecken.
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
