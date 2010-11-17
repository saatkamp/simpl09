package org.simpl.resource.management;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataSourceConnectorList", namespace = "http://management.resource.simpl.org/", propOrder = { "dataSourceConnectors" })
public class DataSourceConnectorList {

  @XmlElement(nillable = true)
  protected List<DataSourceConnector> dataSourceConnectors;

  public List<DataSourceConnector> getDataSourceConnectors() {
    if (dataSourceConnectors == null) {
      dataSourceConnectors = new ArrayList<DataSourceConnector>();
    }
    
    return this.dataSourceConnectors;
  }
}