package org.simpl.resource.discovery.strategy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.neethi.Policy;
import org.apache.neethi.PolicyEngine;
import org.apache.neethi.util.PolicyComparator;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.Strategy;

/**
 * <b>Purpose:</b>Implements the first find strategy.<br>
 * <b>Description:</b>This strategy returns the first data source that matches exactly
 * with the given policy.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: FirstFindStrategy.java 1962 2011-10-25 10:32:46Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class FirstFindStrategy extends StrategyPlugin {  
  @Override
  public DataSource find(Policy policy, List<DataSource> dataSources) {
    boolean comparePolicyExists = false;
    String policyAsString = null;
    Policy comparePolicyObj = null;
    URL policyURL = null;
    DataSource resultDataSource = new DataSource();

    // compare the policy to the data sources properties description policy
    for (DataSource possibleDataSource : dataSources) {
      comparePolicyExists = (possibleDataSource.getPropertiesDescription() != null && !possibleDataSource
          .getPropertiesDescription().equals(""));

      if (comparePolicyExists) {
        policyAsString = possibleDataSource.getPropertiesDescription();

        // check if the policy contains an URL to a remote policy
        try {
          policyURL = new URL(policyAsString);
          comparePolicyObj = PolicyEngine.getPolicy(policyURL.openConnection()
              .getInputStream());
        } catch (MalformedURLException e) {
          comparePolicyObj = PolicyEngine.getPolicy(new ByteArrayInputStream(
              policyAsString.getBytes()));
        } catch (IOException e) {
          e.printStackTrace();
        }

        // comparison
        if (PolicyComparator.compare(comparePolicyObj, policy)) {
          resultDataSource = possibleDataSource;
          break;
        }
      }
    }

    return resultDataSource;
  }
  
  @Override
  public Strategy getStrategy() {
    return Strategy.FIRST_FIND;
  }
}