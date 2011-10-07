package org.simpl.resource.management.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "connectorList", propOrder = { "connectors" })
public class ConnectorList {
  @XmlElement(name = "connector", nillable = true)
  protected List<Connector> connectors;

  public List<Connector> getConnectors() {
    if (connectors == null) {
      connectors = new ArrayList<Connector>();
    }

    return this.connectors;
  }
}