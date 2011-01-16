package org.simpl.resource.management;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.simpl.core.webservices.client.DataFormat;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataFormatList", namespace = "http://management.resource.simpl.org/", propOrder = { "dataFormats" })
public class DataFormatList {

  @XmlElement(nillable = true)
  protected List<DataFormat> dataFormats;

  public List<DataFormat> getDataFormats() {
    if (dataFormats == null) {
      dataFormats = new ArrayList<DataFormat>();
    }

    return this.dataFormats;
  }
}