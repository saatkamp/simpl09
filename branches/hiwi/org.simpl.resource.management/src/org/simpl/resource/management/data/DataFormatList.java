package org.simpl.resource.management.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataFormatList", propOrder = { "dataFormats" })
public class DataFormatList {
  @XmlElement(name = "dataFormat", nillable = true)
  protected List<DataFormat> dataFormats;

  public List<DataFormat> getDataFormats() {
    if (dataFormats == null) {
      dataFormats = new ArrayList<DataFormat>();
    }

    return this.dataFormats;
  }
}