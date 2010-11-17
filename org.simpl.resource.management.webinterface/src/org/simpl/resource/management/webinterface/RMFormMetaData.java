package org.simpl.resource.management.webinterface;

import java.util.ArrayList;

import org.simpl.resource.management.client.DataSourceConnector;
import org.simpl.resource.management.client.DataSourceConnectorList;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;

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
public class RMFormMetaData {
  private static RMFormMetaData instance = null;
  private ArrayList<String> dataFormats = null;
  private ResourceManagement resourceManagement = null;

  private RMFormMetaData() {
    this.resourceManagement = ResourceManagementClient.getService(RMWebConfig
        .getInstance().getResourceManagementAddress());
  }

  private ArrayList<String> getDataFormats() throws Exception {
    DataSourceConnectorList dataConnectors = resourceManagement.getDataSourceConnectors();

    if (this.dataFormats == null) {
      this.dataFormats = new ArrayList<String>();

      for (DataSourceConnector dataSourceConnector : dataConnectors
          .getDataSourceConnectors()) {

        if (!this.dataFormats.contains(dataSourceConnector.getDataConverterDataFormat())) {
          this.dataFormats.add(dataSourceConnector.getDataConverterDataFormat());
        }
      }
    }

    return this.dataFormats;
  }

  public static RMFormMetaData getInstance() {
    if (instance == null) {
      instance = new RMFormMetaData();
    }

    return instance;
  }

  /**
   * Returns the data formats as HTML select element.
   * 
   * @param selectedItem
   * @return HTML select element
   * @throws Exception
   */
  public String getDataFormatSelect(String selectedItem) throws Exception {
    String select = "<select name=\"dataformat\">";

    select += "<option value=\"\"></option>";

    for (String item : this.getDataFormats()) {
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