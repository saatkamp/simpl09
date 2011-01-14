
package org.simpl.resource.management.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Converter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Converter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="implementation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="connectorDataFormatName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="connectorDataFormatImplementation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workflowDataFormatName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workflowDataFormatImplementation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Converter", propOrder = {
    "id",
    "name",
    "implementation",
    "connectorDataFormatName",
    "connectorDataFormatImplementation",
    "workflowDataFormatName",
    "workflowDataFormatImplementation"
})
public class Converter {

    protected String id;
    protected String name;
    protected String implementation;
    protected String connectorDataFormatName;
    protected String connectorDataFormatImplementation;
    protected String workflowDataFormatName;
    protected String workflowDataFormatImplementation;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the implementation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImplementation() {
        return implementation;
    }

    /**
     * Sets the value of the implementation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImplementation(String value) {
        this.implementation = value;
    }

    /**
     * Gets the value of the connectorDataFormatName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectorDataFormatName() {
        return connectorDataFormatName;
    }

    /**
     * Sets the value of the connectorDataFormatName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectorDataFormatName(String value) {
        this.connectorDataFormatName = value;
    }

    /**
     * Gets the value of the connectorDataFormatImplementation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectorDataFormatImplementation() {
        return connectorDataFormatImplementation;
    }

    /**
     * Sets the value of the connectorDataFormatImplementation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectorDataFormatImplementation(String value) {
        this.connectorDataFormatImplementation = value;
    }

    /**
     * Gets the value of the workflowDataFormatName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowDataFormatName() {
        return workflowDataFormatName;
    }

    /**
     * Sets the value of the workflowDataFormatName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowDataFormatName(String value) {
        this.workflowDataFormatName = value;
    }

    /**
     * Gets the value of the workflowDataFormatImplementation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowDataFormatImplementation() {
        return workflowDataFormatImplementation;
    }

    /**
     * Sets the value of the workflowDataFormatImplementation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowDataFormatImplementation(String value) {
        this.workflowDataFormatImplementation = value;
    }

}
