package org.simpl.resource.management.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataSourceList", propOrder = { "dataSources" })
public class DataSourceList {
  @XmlElement(name="dataSource", nillable = true)
  protected List<DataSource> dataSources;

  public List<DataSource> getDataSources() {
    if (dataSources == null) {
      dataSources = new ArrayList<DataSource>();
    }

    return this.dataSources;
  }
}