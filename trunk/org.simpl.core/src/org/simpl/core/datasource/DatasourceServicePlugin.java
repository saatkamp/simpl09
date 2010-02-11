package org.simpl.core.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XSDHelper;

public abstract class DatasourceServicePlugin implements DatasourceService {
  protected String datasourceType = "default"; // Database, Filesystem, ...
  protected String datasourceMetaDataType = "default";
  protected List<String> datasourceSubtypes = new ArrayList<String>(); // DB2, MySQL, ...
  protected HashMap<String, List<String>> datasourceLanguages = new HashMap<String, List<String>>(); // SQL,

  // XQuery,
  // ...

  protected String getDatasourceType() {
    return datasourceType;
  }

  protected List<String> getDatasourceSubtypes() {
    return datasourceSubtypes;
  }

  protected HashMap<String, List<String>> getDatasourceLanguages() {
    return datasourceLanguages;
  }

  protected void setDatasourceType(String dsType) {
    this.datasourceType = dsType;
  }

  protected void setDatasourceMetaDataType(String dsMetaDataType) {
    this.datasourceMetaDataType = dsMetaDataType;
  }

  protected void addDatasourceSubtype(String dsSubtype) {
    if (!this.datasourceSubtypes.contains(dsSubtype)) {
      this.datasourceSubtypes.add(dsSubtype);
    }
  }

  protected void addDatasourceLanguage(String dsSubtype, String dsLanguage) {
    List<String> languages = null;

    if (datasourceLanguages.containsKey(dsSubtype)) {
      languages = datasourceLanguages.get(dsSubtype);
    } else {
      languages = new ArrayList<String>();
    }

    if (!languages.contains(dsLanguage)) {
      languages.add(dsLanguage);
      this.datasourceLanguages.put(dsSubtype, languages);
    }
  }

  protected DataObject createDatasourceMetaDataObject() {
    DataObject sdo = null;
    InputStream is = getClass().getResourceAsStream(
        "/org/simpl/core/datasource/DatasourceMetaData.xsd");

    XSDHelper.INSTANCE.define(is, null);

    try {
      is.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    sdo = DataFactory.INSTANCE.create(
        "http:///org.simpl.core/src/org/simpl/core/datasource/metadata/dbMetaData.xsd",
        this.datasourceMetaDataType);

    return sdo;
  }

  @SuppressWarnings("unchecked")
  protected void printDataObject(DataObject dataObject, int indent) {
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

  private void printValue(Object value, Property property, int indent) {
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
      printDataObject((DataObject) value, indent + 1);
    } else {
      // For non-containment properties, just print the value
      System.out.println(margin + propertyName + ": " + value);
    }
  }
}