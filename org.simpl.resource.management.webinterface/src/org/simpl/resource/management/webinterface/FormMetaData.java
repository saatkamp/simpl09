package org.simpl.resource.management.webinterface;

import java.util.ArrayList;
import java.util.HashSet;

import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;
import org.simpl.resource.management.data.DataConverter;
import org.simpl.resource.management.data.DataConverterList;

/**
 * <b>Purpose: Offers elements for web forms with data from the Resource Management.</b><br>
 * <b>Description:</b>Connects to the Resource Management web service to retrieve the
 * data.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class FormMetaData {
  private static FormMetaData instance = null;
  private ArrayList<String> dataFormats = null;
  private ResourceManagement resourceManagement = null;
  private ArrayList<String> types = null;
  private ArrayList<String> subTypes = null;
  private ArrayList<String> languages = null;

  private FormMetaData() {
    this.resourceManagement = ResourceManagementClient.getService(RMWebConfig
        .getInstance().getResourceManagementAddress());
  }

  public static FormMetaData getInstance() {
    if (instance == null) {
      instance = new FormMetaData();
    }

    return instance;
  }

  /**
   * Returns the data formats as HTML select element.
   * 
   * @param name
   *          Name of the form element
   * @param selectedItem
   *          Selected item
   * @return HTML select element
   * @throws Exception
   */
  public String getDataFormatSelect(String name, String selectedItem) throws Exception {
    this.retrieveDataConverters();

    String select = this
        .createSelectElement(name, selectedItem, this.dataFormats);

    return select;
  }

  /**
   * Returns the data source types as HTML select element.
   * 
   * @param selectedItem
   *          Selected item
   * @return HTML select element
   * @throws Exception
   */
  public String getTypeSelect(String selectedItem) throws Exception {
    this.retrieveTypes();

    String select = this.createSelectElement("type", selectedItem, this.types);

    return select;
  }

  /**
   * Returns the data source sub types as HTML select element.
   * 
   * @param selectedItem
   *          Selected item
   * @return HTML select element
   * @throws Exception
   */
  public String getSubTypeSelect(String selectedItem) throws Exception {
    this.retrieveSubTypes();
    
    String select = this.createSelectElement("subtype", selectedItem, subTypes);

    return select;
  }

  /**
   * Returns the data source languages as HTML select element.
   * 
   * @param selectedItem
   *          Selected item
   * @return HTML select element
   * @throws Exception
   */
  public String getLanguageSelect(String selectedItem) throws Exception {
    this.retrieveLanguages();

    String select = this.createSelectElement("language", selectedItem, this.languages);

    return select;
  }

  /**
   * Resets the resource management data.
   */
  public static void refresh() {
    instance = null;
  }

  /**
   * Retrieves the data format names from the resource management.
   * 
   * @throws Exception
   */
  private void retrieveDataConverters() throws Exception {
    if (this.dataFormats == null) {
      this.dataFormats = new ArrayList<String>();
      
      DataConverterList dataConverters = resourceManagement.getAllDataConverters();
      
      for (DataConverter dataConverter : dataConverters.getDataConverters()) {
        if (!this.dataFormats.contains(dataConverter.getWorkflowDataFormat())) {
          this.dataFormats.add(dataConverter.getWorkflowDataFormat());
        }
      }
    }
  }

  /**
   * Retrieves the data source types from the resource management.
   * 
   * @throws Exception
   */
  private void retrieveTypes() throws Exception {
    if (this.types == null) {
      this.types = (ArrayList<String>) this.resourceManagement.getDataSourceTypes()
          .getItems();
    }
  }

  /**
   * Retrieves the data source sub types from the resource management.
   * 
   * @throws Exception
   */
  private void retrieveSubTypes() throws Exception {
    if (this.subTypes == null) {
      if (this.types == null) {
        this.retrieveTypes();
      }

      this.subTypes = new ArrayList<String>();

      for (String type : this.types) {
        this.subTypes.addAll(this.resourceManagement.getDataSourceSubTypes(type)
            .getItems());
      }
      
      // remove duplicates
      HashSet<String> h = new HashSet<String>(this.subTypes);
      this.subTypes.clear();
      this.subTypes.addAll(h);
    }
  }

  /**
   * Retrieves the data source languages from the resource management.
   * 
   * @throws Exception
   */
  private void retrieveLanguages() throws Exception {
    if (this.languages == null) {
      if (this.subTypes == null) {
        this.retrieveSubTypes();
      }

      this.languages = new ArrayList<String>();

      for (String subtype : this.subTypes) {
        this.languages.addAll(this.resourceManagement.getDataSourceLanguages(subtype)
            .getItems());
      }
      
      // remove duplicates
      HashSet<String> h = new HashSet<String>(this.languages);
      this.languages.clear();
      this.languages.addAll(h);
    }
  }

  /**
   * Creates a HTML select element.
   * 
   * @param name
   *          Element name
   * @param selectedItem
   *          Selected item
   * @param items
   *          Array of items
   * @return
   */
  private String createSelectElement(String name, String selectedItem,
      ArrayList<String> items) {
    String select = "<select name=\"" + name + "\">";

    select += "<option value=\"\"></option>";

    for (String item : items) {
      if (!item.equals(selectedItem)) {
        select += "<option value=\"" + item + "\">" + item + "</option>";
      } else {
        select += "<option selected value=\"" + item + "\">" + item + "</option>";
      }
    }

    select += "</select>";

    return select;
  }
}