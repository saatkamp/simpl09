package org.simpl.resource.management.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataConverterList", propOrder = { "dataConverters" })
public class DataConverterList {
  @XmlElement(name = "dataConverter", nillable = true)
  protected List<DataConverter> dataConverters;

  public List<DataConverter> getDataConverters() {
    if (dataConverters == null) {
      dataConverters = new ArrayList<DataConverter>();
    }

    return this.dataConverters;
  }
}