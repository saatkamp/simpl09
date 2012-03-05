package org.simpl.resource.management.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataContainerList", propOrder = { "dataContainers" })
public class DataContainerList {
  @XmlElement(name = "dataContainer", nillable = true)
  protected List<DataContainer> dataContainers;

  public List<DataContainer> getDataContainers() {
    if (dataContainers == null) {
      dataContainers = new ArrayList<DataContainer>();
    }

    return this.dataContainers;
  }
}
