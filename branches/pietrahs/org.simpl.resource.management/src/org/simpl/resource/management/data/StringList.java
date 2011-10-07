package org.simpl.resource.management.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "stringList", namespace = "http://management.resource.simpl.org/", propOrder = { "items" })
public class StringList {
  @XmlElement(nillable = true)
  protected List<String> items;

  public List<String> getItems() {
    if (items == null) {
      items = new ArrayList<String>();
    }

    return this.items;
  }
}