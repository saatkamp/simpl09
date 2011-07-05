package org.simpl.resource.management.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataTransformationServiceList", propOrder = { "dataTransformationServices" })
public class DataTransformationServiceList {
  @XmlElement(name = "dataTransformationServices", nillable = true)
  protected List<DataTransformationService> dataTransformationServices;

  public List<DataTransformationService> getDataTransformationServices() {
    if (dataTransformationServices == null) {
      dataTransformationServices = new ArrayList<DataTransformationService>();
    }

    return this.dataTransformationServices;
  }
}