package org.simpl.core.services.datasource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.simpl.core.services.datasource.auth.Authentication;
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
@XmlType(name = "DataSource", propOrder = { "name", "address", "policy",
    "strategy", "type", "subType", "authentication" })
public class DataSource {
  private String name = "";
  private String address = "";
  private String policy = "";
  private Strategy strategy = null;
  private String type = "";
  private String subType = "";
  private Authentication authentication = new Authentication();

  public String getAddress() {
    return address;
  }

  public void setAddress(String value) {
    this.address = value;
  }

  public String getPolicy() {
    return policy;
  }

  public void setPolicy(String value) {
    this.policy = value;
  }

  public Strategy getStrategy() {
    return strategy;
  }

  public void setStrategy(Strategy value) {
    this.strategy = value;
  }

  public String getSubType() {
    return subType;
  }

  public void setSubType(String value) {
    this.subType = value;
  }

  public String getType() {
    return type;
  }

  public void setType(String value) {
    this.type = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public Authentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(Authentication authentication) {
    this.authentication = authentication;
  }
}