
package org.simpl.resource.management.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Connector complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Connector">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="implementation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="converterDataFormatName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="converterDataFormatImplementation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propertiesDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Connector", propOrder = {
    "id",
    "name",
    "implementation",
    "converterDataFormatName",
    "converterDataFormatImplementation",
    "propertiesDescription"
})
public class Connector {

    protected String id;
    protected String name;
    protected String implementation;
    protected String converterDataFormatName;
    protected String converterDataFormatImplementation;
    protected String propertiesDescription;

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
     * Gets the value of the converterDataFormatName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConverterDataFormatName() {
        return converterDataFormatName;
    }

    /**
     * Sets the value of the converterDataFormatName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConverterDataFormatName(String value) {
        this.converterDataFormatName = value;
    }

    /**
     * Gets the value of the converterDataFormatImplementation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConverterDataFormatImplementation() {
        return converterDataFormatImplementation;
    }

    /**
     * Sets the value of the converterDataFormatImplementation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConverterDataFormatImplementation(String value) {
        this.converterDataFormatImplementation = value;
    }

    /**
     * Gets the value of the propertiesDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertiesDescription() {
        return propertiesDescription;
    }

    /**
     * Sets the value of the propertiesDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertiesDescription(String value) {
        this.propertiesDescription = value;
    }

}
