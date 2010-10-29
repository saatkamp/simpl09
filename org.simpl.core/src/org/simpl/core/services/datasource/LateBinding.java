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
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LateBinding", propOrder = { "policy", "strategy", "resourceManagementAddress" })
public class LateBinding {
  private String policy;
  private Strategy strategy;
  private String resourceManagementAddress;

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
   * @return the resourceManagementAddress
   */
  public String getResourceManagementAddress() {
    return resourceManagementAddress;
  }

  /**
   * @param resourceManagementAddress
   *          the resourceManagementAddress to set
   */
  public void setResourceManagementAddress(String resourceManagementAddress) {
    this.resourceManagementAddress = resourceManagementAddress;
  }
}