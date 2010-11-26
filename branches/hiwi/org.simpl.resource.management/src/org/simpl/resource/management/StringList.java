package org.simpl.resource.management;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "stringList", namespace = "http://management.resource.simpl.org/", propOrder = { "item" })
public class StringList {
  @XmlElement(nillable = true)
  protected List<String> item;

  public List<String> getItems() {
    if (item == null) {
      item = new ArrayList<String>();
    }

    return this.item;
  }
}