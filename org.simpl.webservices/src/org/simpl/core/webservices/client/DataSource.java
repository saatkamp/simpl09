
package org.simpl.core.webservices.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataSource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataSource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authentication" type="{http://webservices.core.simpl.org/}Authentication" minOccurs="0"/>
 *         &lt;element name="lateBinding" type="{http://webservices.core.simpl.org/}LateBinding" minOccurs="0"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFormatName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFormatImplementation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="connectorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="connectorImplementation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="connectorPropertiesDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSource", propOrder = {
    "id",
    "name",
    "address",
    "type",
    "subType",
    "authentication",
    "lateBinding",
    "language",
    "dataFormatName",
    "dataFormatImplementation",
    "connectorName",
    "connectorImplementation",
    "connectorPropertiesDescription"
})
public class DataSource {

    protected String id;
    protected String name;
    protected String address;
    protected String type;
    protected String subType;
    protected Authentication authentication;
    protected LateBinding lateBinding;
    protected String language;
    protected String dataFormatName;
    protected String dataFormatImplementation;
    protected String connectorName;
    protected String connectorImplementation;
    protected String connectorPropertiesDescription;

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
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the subType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubType() {
        return subType;
    }

    /**
     * Sets the value of the subType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubType(String value) {
        this.subType = value;
    }

    /**
     * Gets the value of the authentication property.
     * 
     * @return
     *     possible object is
     *     {@link Authentication }
     *     
     */
    public Authentication getAuthentication() {
        return authentication;
    }

    /**
     * Sets the value of the authentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Authentication }
     *     
     */
    public void setAuthentication(Authentication value) {
        this.authentication = value;
    }

    /**
     * Gets the value of the lateBinding property.
     * 
     * @return
     *     possible object is
     *     {@link LateBinding }
     *     
     */
    public LateBinding getLateBinding() {
        return lateBinding;
    }

    /**
     * Sets the value of the lateBinding property.
     * 
     * @param value
     *     allowed object is
     *     {@link LateBinding }
     *     
     */
    public void setLateBinding(LateBinding value) {
        this.lateBinding = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the dataFormatName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFormatName() {
        return dataFormatName;
    }

    /**
     * Sets the value of the dataFormatName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFormatName(String value) {
        this.dataFormatName = value;
    }

    /**
     * Gets the value of the dataFormatImplementation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFormatImplementation() {
        return dataFormatImplementation;
    }

    /**
     * Sets the value of the dataFormatImplementation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFormatImplementation(String value) {
        this.dataFormatImplementation = value;
    }

    /**
     * Gets the value of the connectorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectorName() {
        return connectorName;
    }

    /**
     * Sets the value of the connectorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectorName(String value) {
        this.connectorName = value;
    }

    /**
     * Gets the value of the connectorImplementation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectorImplementation() {
        return connectorImplementation;
    }

    /**
     * Sets the value of the connectorImplementation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectorImplementation(String value) {
        this.connectorImplementation = value;
    }

    /**
     * Gets the value of the connectorPropertiesDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectorPropertiesDescription() {
        return connectorPropertiesDescription;
    }

    /**
     * Sets the value of the connectorPropertiesDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectorPropertiesDescription(String value) {
        this.connectorPropertiesDescription = value;
    }

}
