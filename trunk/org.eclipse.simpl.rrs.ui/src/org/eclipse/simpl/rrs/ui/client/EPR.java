
package org.eclipse.simpl.rrs.ui.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for EPR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EPR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="referenceProperties" type="{http://uni-stuttgart.de/simpl/rrs}ReferenceProperties"/>
 *         &lt;element name="referenceParameters" type="{http://uni-stuttgart.de/simpl/rrs}ReferenceParameters"/>
 *         &lt;element name="portType" type="{http://www.w3.org/2001/XMLSchema}QName" minOccurs="0"/>
 *         &lt;element name="serviceName" type="{http://uni-stuttgart.de/simpl/rrs}ServiceName" minOccurs="0"/>
 *         &lt;element name="policy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement()
@XmlType(name = "EPR", propOrder = {
    "address",
    "referenceProperties",
    "referenceParameters",
    "portType",
    "serviceName",
    "policy"
})
public class EPR {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String address;
    @XmlElement(required = true)
    protected ReferenceProperties referenceProperties;
    @XmlElement(required = true)
    protected ReferenceParameters referenceParameters;
    protected QName portType;
    protected ServiceName serviceName;
    protected String policy;

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
     * Gets the value of the referenceProperties property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceProperties }
     *     
     */
    public ReferenceProperties getReferenceProperties() {
        return referenceProperties;
    }

    /**
     * Sets the value of the referenceProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceProperties }
     *     
     */
    public void setReferenceProperties(ReferenceProperties value) {
        this.referenceProperties = value;
    }

    /**
     * Gets the value of the referenceParameters property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceParameters }
     *     
     */
    public ReferenceParameters getReferenceParameters() {
        return referenceParameters;
    }

    /**
     * Sets the value of the referenceParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceParameters }
     *     
     */
    public void setReferenceParameters(ReferenceParameters value) {
        this.referenceParameters = value;
    }

    /**
     * Gets the value of the portType property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getPortType() {
        return portType;
    }

    /**
     * Sets the value of the portType property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setPortType(QName value) {
        this.portType = value;
    }

    /**
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceName }
     *     
     */
    public ServiceName getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceName }
     *     
     */
    public void setServiceName(ServiceName value) {
        this.serviceName = value;
    }

    /**
     * Gets the value of the policy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicy() {
        return policy;
    }

    /**
     * Sets the value of the policy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicy(String value) {
        this.policy = value;
    }

}
