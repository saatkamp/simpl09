package org.simpl.core.discovery;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.neethi.Policy;
import org.apache.neethi.PolicyEngine;
import org.apache.neethi.util.PolicyComparator;
import org.simpl.core.clients.RMClient;
import org.simpl.resource.management.client.DataSource;
import org.simpl.resource.management.client.LateBinding;

/**
 * <b>Purpose:</b>Implementation of the strategy service.<br>
 * <b>Description:</b>Uses the resource management client to respond to the resource
 * management that is defined in the data source late binding information.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: StrategyServiceImpl.java 1159 2010-04-20 18:19:11Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DiscoveryServiceImpl implements DiscoveryService {
  static Logger logger = Logger.getLogger(DiscoveryServiceImpl.class);

  @Override
  public DataSource findDataSource(LateBinding lateBinding) {
    if (logger.isDebugEnabled()) {
      logger.debug("Policy: " + lateBinding.getPolicy());
      logger.debug("Strategy: "
          + lateBinding.getStrategy().toString());
    }

    boolean policyExists = lateBinding.getPolicy() != null;
    boolean comparePolicy = true;
    
    String policyAsString = null;
    Policy policyObj = null;
    Policy comparePolicyObj = null;
    URL policyURL = null;
    DataSource resultDataSource = null;

    if (policyExists) {
      policyObj = PolicyEngine.getPolicy(new ByteArrayInputStream(lateBinding.getPolicy().getBytes()));

      switch (lateBinding.getStrategy()) {
      case FIRST_FIND:
        List<DataSource> dataSources = null;

        dataSources = RMClient.getInstance().getAllDataSources().getDataSource();

        // retrieve all data sources and compare the policy to its properties description policy
        for (DataSource possibleDataSource : dataSources) {
          comparePolicy = possibleDataSource.getPropertiesDescription() != null;

          if (logger.isDebugEnabled()) {
            logger.debug("Policy of possible data source is: "
                + possibleDataSource.getPropertiesDescription());
          }

          if (comparePolicy) {
            policyAsString = possibleDataSource.getPropertiesDescription();

            // check if the policy contains an URL to a remote policy
            try {
              policyURL = new URL(policyAsString);
              comparePolicyObj = PolicyEngine.getPolicy(policyURL.openConnection()
                  .getInputStream());
            } catch (MalformedURLException e) {
              comparePolicyObj = PolicyEngine.getPolicy(new ByteArrayInputStream(policyAsString
                  .getBytes()));
            } catch (IOException e) {
              e.printStackTrace();
            }

            // comparison
            if (PolicyComparator.compare(comparePolicyObj, policyObj)) {
              resultDataSource = possibleDataSource;
              break;
            }
          }
        }

        break;
      default:
        break;
      }
    }

    if (logger.isDebugEnabled()) {
      logger.debug("Name of result ds: " + resultDataSource.getName());
      logger.debug("Address of result ds: " + resultDataSource.getAddress());
      logger.debug("Type of result ds: " + resultDataSource.getType());
      logger.debug("Subtype of result ds: " + resultDataSource.getSubType());
      logger.debug("UserName of result ds: "
          + resultDataSource.getAuthentication().getUser());
      logger.debug("Password of result ds: "
          + resultDataSource.getAuthentication().getPassword());
      logger.debug("Format of result ds: "
          + resultDataSource.getConnector().getConverterDataFormat().getName());
      logger.debug("Policy of result ds: "
          + resultDataSource.getPropertiesDescription());
    }

    return resultDataSource;
  }
}