
package de.uni_stuttgart.simpl.rrs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReferenceProperties complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferenceProperties">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resolutionSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceProperties", propOrder = {
    "resolutionSystem"
})
public class ReferenceProperties {

    protected String resolutionSystem;

    /**
     * Gets the value of the resolutionSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolutionSystem() {
        return resolutionSystem;
    }

    /**
     * Sets the value of the resolutionSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolutionSystem(String value) {
        this.resolutionSystem = value;
    }

}
