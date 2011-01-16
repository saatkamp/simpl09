package org.simpl.resource.management;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.simpl.core.webservices.client.Connector;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "connectorList", namespace = "http://management.resource.simpl.org/", propOrder = { "connectors" })
public class ConnectorList {

  @XmlElement(nillable = true)
  protected List<Connector> connectors;

  public List<Connector> getConnectors() {
    if (connectors == null) {
      connectors = new ArrayList<Connector>();
    }

    return this.connectors;
  }
}