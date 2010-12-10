
package org.simpl.resource.management.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataConverterList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataConverterList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataConverters" type="{http://management.resource.simpl.org/}DataConverter" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataConverterList", propOrder = {
    "dataConverters"
})
public class DataConverterList {

    @XmlElement(nillable = true)
    protected List<DataConverter> dataConverters;

    /**
     * Gets the value of the dataConverters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataConverters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataConverters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataConverter }
     * 
     * 
     */
    public List<DataConverter> getDataConverters() {
        if (dataConverters == null) {
            dataConverters = new ArrayList<DataConverter>();
        }
        return this.dataConverters;
    }

}