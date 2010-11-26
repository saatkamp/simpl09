package org.simpl.resource.management;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.simpl.core.webservices.client.DataSource;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataSourceList", namespace = "http://management.resource.simpl.org/", propOrder = { "dataSources" })
public class DataSourceList {
  @XmlElement(nillable = true)
  protected List<DataSource> dataSources;

  public List<DataSource> getDataSources() {
    if (dataSources == null) {
      dataSources = new ArrayList<DataSource>();
    }

    return this.dataSources;
  }
}