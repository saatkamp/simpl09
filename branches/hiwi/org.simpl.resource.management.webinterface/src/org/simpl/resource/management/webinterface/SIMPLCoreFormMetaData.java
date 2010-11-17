package org.simpl.resource.management.webinterface;

import java.util.ArrayList;

import org.simpl.core.helpers.Parameter;
import org.simpl.core.webservices.client.DatasourceService;
import org.simpl.core.webservices.client.DatasourceServiceClient;

/**
 * <b>Purpose: Offers elements for web forms with data from the SIMPL Core.</b><br>
 * <b>Description:</b>Connects to the SIMPL Core data source web service to retrieve the
 * data.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLCoreFormMetaData {
  private static SIMPLCoreFormMetaData instance = null;
  private ArrayList<String> types = null;
  private ArrayList<String> subtypes = null;
  private ArrayList<String> languages = null;
  private DatasourceService dataSourceService = null;

  private SIMPLCoreFormMetaData() {
    this.dataSourceService = DatasourceServiceClient.getService(RMWebConfig.getInstance()
        .getDataSourceServiceAddress());
  }

  @SuppressWarnings("unchecked")
  private ArrayList<String> getTypes() {
    if (this.types == null) {
      this.types = (ArrayList<String>) Parameter.deserialize(this.dataSourceService
          .getDataSourceTypes());
    }

    return this.types;
  }

  @SuppressWarnings("unchecked")
  private ArrayList<String> getSubtypes() {
    if (subtypes == null) {
      this.subtypes = new ArrayList<String>();

      for (String type : getTypes()) {
        for (String item : (ArrayList<String>) Parameter
            .deserialize(this.dataSourceService.getDataSourceSubTypes(type))) {
          if (!this.subtypes.contains(item)) {
            this.subtypes.add(item);
          }
        }
      }
    }

    return this.subtypes;
  }

  @SuppressWarnings("unchecked")
  private ArrayList<String> getLanguages() {
    if (this.languages == null) {
      this.languages = new ArrayList<String>();

      for (String subtype : getSubtypes()) {
        for (String item : (ArrayList<String>) Parameter.deserialize(dataSourceService
            .getDataSourceLanguages(subtype)))
          if (!this.languages.contains(item)) {
            this.languages.add(item);
          }
      }
    }

    return this.languages;
  }

  public static SIMPLCoreFormMetaData getInstance() {
    if (instance == null) {
      instance = new SIMPLCoreFormMetaData();
    }

    return instance;
  }

  /**
   * Returns SIMPL Core types as HTML select element.
   * 
   * @param selectedItem
   * @return HTML select element
   */
  public String getTypeSelect(String selectedItem) {
    String select = "<select name=\"type\">";

    select += "<option value=\"\"></option>";

    for (String item : this.getTypes()) {
      if (!item.equals(selectedItem)) {
        select += "<option value=\"" + item + "\">" + item + "</option>";
      } else {
        select += "<option selected value=\"" + item + "\">" + item + "</option>";
      }
    }

    select += "</select>";

    return select;
  }

  /**
   * Returns SIMPL Core sub types as HTML select element.
   * 
   * @param selectedItem
   * @return HTML select element
   */
  public String getSubTypeSelect(String selectedItem) {
    String select = "<select name=\"subtype\">";

    select += "<option value=\"\"></option>";

    for (String item : getSubtypes()) {
      if (!item.equals(selectedItem)) {
        select += "<option value=\"" + item + "\">" + item + "</option>";
      } else {
        select += "<option selected value=\"" + item + "\">" + item + "</option>";
      }
    }

    select += "</select>";

    return select;
  }

  /**
   * Returns SIMPL Core languages as HTML select element.
   * 
   * @param selectedItem
   * @return HTML select element
   */
  public String getLanguageSelect(String selectedItem) {
    String select = "<select name=\"language\">";

    select += "<option value=\"\"></option>";

    for (String item : getLanguages()) {
      if (!item.equals(selectedItem)) {
        select += "<option value=\"" + item + "\">" + item + "</option>";
      } else {
        select += "<option selected value=\"" + item + "\">" + item + "</option>";
      }
    }

    select += "</select>";

    return select;
  }

  public static void refresh() {
    instance = null;
  }
}