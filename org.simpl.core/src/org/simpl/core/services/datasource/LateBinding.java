package org.simpl.core.services.datasource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.simpl.core.services.strategy.Strategy;

/**
 * <b>Purpose:</b>Late Binding information to be used for the strategy service to find a
 * data source.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: UserPasswordAuthentication.java 1183 2010-04-22 20:05:13Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LateBinding", propOrder = { "policy", "strategy", "resourceFrameworkAddress" })
public class LateBinding {
  private String policy;
  private Strategy strategy;
  private String resourceFrameworkAddress;

  /**
   * @return the policy
   */
  public String getPolicy() {
    return policy;
  }

  /**
   * @param policy
   *          the policy to set
   */
  public void setPolicy(String policy) {
    this.policy = policy;
  }

  /**
   * @return the strategy
   */
  public Strategy getStrategy() {
    return strategy;
  }

  /**
   * @param strategy
   *          the strategy to set
   */
  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  /**
   * @return the resourceFrameworkAddress
   */
  public String getResourceFrameworkAddress() {
    return resourceFrameworkAddress;
  }

  /**
   * @param resourceFrameworkAddress
   *          the resourceFrameworkAddress to set
   */
  public void setResourceFrameworkAddress(String resourceFrameworkAddress) {
    this.resourceFrameworkAddress = resourceFrameworkAddress;
  }
}