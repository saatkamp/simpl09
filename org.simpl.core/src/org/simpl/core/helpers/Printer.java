package org.simpl.core.helpers;

import java.io.IOException;
import java.util.List;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;
import commonj.sdo.helper.XMLHelper;

/**
 * <b>Purpose:</b>Helper to print out complex objects.<br>
 * <b>Description:</b>Offers functions to print out objects.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: Printer.java 1680 2010-10-15 14:40:50Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class Printer {
  public static void printDataObject(DataObject dataObject) {
    int indent = 1;

    Printer.printDataObjectWithIndent(dataObject, indent);
  }

  /**
   * Warning: may cause errors if used between SDO operations.
   * 
   * @param dataObject
   */
  public static void printDataObjectXML(DataObject dataObject) {
    try {
      XMLHelper.INSTANCE.save(dataObject, "commonj.sdo", "dataObject", System.out);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @SuppressWarnings("rawtypes")
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
            Printer.printValue(values.get(v), property, indent);
          }
        } else {
          // For single-valued properties, print out the value
          Printer.printValue(dataObject.get(p), property, indent);
        }
      }
    }
  }

  private static void printValue(Object value, Property property, int indent) {
    // Get the name of the property
    String propertyName = property.getName();
    // Construct a string for the proper indentation
    String margin = "";
    for (int i = 0; i < indent; i++) {
      margin += "\t";
    }
    if (value != null && property.isContainment()) {
      // For containment properties, display the value
      // with printDataObject
      Type type = property.getType();
      String typeName = type.getName();
      System.out.println(margin + propertyName + " (" + typeName + "):");
      Printer.printDataObjectWithIndent((DataObject) value, indent + 1);
    } else {
      // For non-containment properties, just print the value
      System.out.println(margin + propertyName + ": " + value);
    }
  }
}