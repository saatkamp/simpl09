package org.simpl.core.services.datasource;

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
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DataSource {
  private String address;
  private String type;
  private String subType;
  private String policy;
  private Strategy strategy;
  private Authentication authentication;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubType() {
    return subType;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public String getPolicy() {
    return policy;
  }

  public void setPolicy(String policy) {
    this.policy = policy;
  }

  public Strategy getStrategy() {
    return strategy;
  }

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  public Authentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(Authentication authentication) {
    this.authentication = authentication;
  }
}