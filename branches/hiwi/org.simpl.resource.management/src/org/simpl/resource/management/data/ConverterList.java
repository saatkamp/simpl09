package org.simpl.resource.management.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "converterList", propOrder = { "converters" })
public class ConverterList {
  @XmlElement(name = "converter", nillable = true)
  protected List<Converter> converters;

  public List<Converter> getConverters() {
    if (converters == null) {
      converters = new ArrayList<Converter>();
    }

    return this.converters;
  }
}