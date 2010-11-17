
package org.simpl.resource.management.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataSourceConnectorList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataSourceConnectorList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataSourceConnectors" type="{http://management.resource.simpl.org/}DataSourceConnector" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataSourceConnectorList", propOrder = {
    "dataSourceConnectors"
})
public class DataSourceConnectorList {

    @XmlElement(nillable = true)
    protected List<DataSourceConnector> dataSourceConnectors;

    /**
     * Gets the value of the dataSourceConnectors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSourceConnectors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSourceConnectors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSourceConnector }
     * 
     * 
     */
    public List<DataSourceConnector> getDataSourceConnectors() {
        if (dataSourceConnectors == null) {
            dataSourceConnectors = new ArrayList<DataSourceConnector>();
        }
        return this.dataSourceConnectors;
    }

}
