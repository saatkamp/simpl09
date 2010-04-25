package org.simpl.core.services.datasource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.simpl.core.services.strategy.Strategy;

/**
 * <b>Purpose:</b>Represents a data source with all relevant information that is
 * needed to build up a connection.<br>
 * <b>Description:</b>Type and subtype are used by the SIMPL Core to determine a
 * data source service that can respond to the data source over the address.
 * Further it holds the necessary authentication information and provides
 * information for the strategic late binding of the data source with a policy.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DataSource.java 1183 2010-04-22 20:05:13Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DataSource")
@XmlType(name = "DataSource", propOrder = { "address", "policy", "strategy",
    "subType", "type" })
public class DataSource {
  protected String address;
  protected String policy;
  protected Strategy strategy;
  protected String subType;
  protected String type;

  /**
   * Gets the value of the address property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the value of the address property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setAddress(String value) {
    this.address = value;
  }

  /**
   * Gets the value of the policy property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getPolicy() {
    return policy;
  }

  /**
   * Sets the value of the policy property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setPolicy(String value) {
    this.policy = value;
  }

  /**
   * Gets the value of the strategy property.
   * 
   * @return possible object is {@link Strategy }
   * 
   */
  public Strategy getStrategy() {
    return strategy;
  }

  /**
   * Sets the value of the strategy property.
   * 
   * @param value
   *          allowed object is {@link Strategy }
   * 
   */
  public void setStrategy(Strategy value) {
    this.strategy = value;
  }

  /**
   * Gets the value of the subType property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getSubType() {
    return subType;
  }

  /**
   * Sets the value of the subType property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setSubType(String value) {
    this.subType = value;
  }

  /**
   * Gets the value of the type property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the value of the type property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setType(String value) {
    this.type = value;
  }
}