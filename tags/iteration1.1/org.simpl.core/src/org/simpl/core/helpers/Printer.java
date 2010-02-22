package org.simpl.core.helpers;

import java.util.List;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

/**
 * <b>Purpose:</b> Helper to print out complex objects. <br>
 * <b>Description:</b> Offers functions to print out objects. <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class Printer {
  public static void printDataObject(DataObject dataObject) {
    int indent = 1;

    printDataObjectWithIndent(dataObject, indent);
  }

  @SuppressWarnings("unchecked")
  private static void printDataObjectWithIndent(DataObject dataObject, int indent) {
    // For each Property
    List properties = dataObject.getInstanceProperties();
    for (int p = 0, size = properties.size(); p < size; p++) {
      if (dataObject.isSet(p)) {
        Property property = (Property) properties.get(p);
        if (property.isMany()) {
          // For many-valued properties, process a list of values
          List values = dataObject.getList(p);
          for (int v = 0, count = values.size(); v < count; v++) {
            printValue(values.get(v), property, indent);
          }
        } else {
          // For single-valued properties, print out the value
          printValue(dataObject.get(p), property, indent);
        }
      }
    }
  }

  private static void printValue(Object value, Property property, int indent) {
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
      System.out.println(margin + propertyName + " (" + typeName + "):");
      printDataObjectWithIndent((DataObject) value, indent + 1);
    } else {
      // For non-containment properties, just print the value
      System.out.println(margin + propertyName + ": " + value);
    }
  }
}