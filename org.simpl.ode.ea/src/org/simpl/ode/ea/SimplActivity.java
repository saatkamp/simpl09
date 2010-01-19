package org.simpl.ode.ea;

import java.util.List;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.rtrep.common.extension.AbstractSyncExtensionOperation;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

public class SimplActivity extends AbstractSyncExtensionOperation {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {
		// TODO Auto-generated method stub

	}

	protected String getStatement(ExtensionContext context, Element element)
			throws FaultException {
		String statement = null;
		statement = element.getAttribute("dsStatement").toString();
		return statement;
	}

	protected String getdsAddress(ExtensionContext context, Element element)
			throws FaultException {
		String dsAddress = null;
		dsAddress = element.getAttribute("dsAddress").toString();
		return dsAddress;
	}

	protected DataObject getDataObject(String dsAddress, String Statement){
		DataObject data = null;
		
		return data;
	}
	
	protected String dsStatement;
	protected String dsAddress;
	
	
//Diese beiden Methoden sind nur zu Testzwecken.
	public static void printDataObject(ExtensionContext context, DataObject dataObject, int indent) {
		// For each Property
		List properties = dataObject.getInstanceProperties();
		for (int p = 0, size = properties.size(); p < size; p++) {
			if (dataObject.isSet(p)) {
				Property property = (Property) properties.get(p);
				if (property.isMany()) {
					// For many-valued properties, process a list of values
					List values = dataObject.getList(p);
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

	public static void printValue(ExtensionContext context, Object value, Property property, int indent) {
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
			context.printToConsole((margin + propertyName + " (" + typeName + "):"));
			printDataObject(context, (DataObject) value, indent + 1);
		} else {
			// For non-containment properties, just print the value
			context.printToConsole(margin + propertyName + ": " + value);
		}
	}


}
