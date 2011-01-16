
package org.simpl.resource.management.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.simpl.core.webservices.client.Converter;


/**
 * <p>Java class for converterList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="converterList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="converters" type="{http://webservices.core.simpl.org/}Converter" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "converterList", propOrder = {
    "converters"
})
public class ConverterList {

    @XmlElement(nillable = true)
    protected List<Converter> converters;

    /**
     * Gets the value of the converters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the converters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConverters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Converter }
     * 
     * 
     */
    public List<Converter> getConverters() {
        if (converters == null) {
            converters = new ArrayList<Converter>();
        }
        return this.converters;
    }

}
