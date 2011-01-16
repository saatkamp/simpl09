
package org.simpl.resource.management.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.simpl.core.webservices.client.Connector;


/**
 * <p>Java class for connectorList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="connectorList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="connectors" type="{http://webservices.core.simpl.org/}Connector" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "connectorList", propOrder = {
    "connectors"
})
public class ConnectorList {

    @XmlElement(nillable = true)
    protected List<Connector> connectors;

    /**
     * Gets the value of the connectors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the connectors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConnectors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Connector }
     * 
     * 
     */
    public List<Connector> getConnectors() {
        if (connectors == null) {
            connectors = new ArrayList<Connector>();
        }
        return this.connectors;
    }

}
