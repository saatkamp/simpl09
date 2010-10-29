package org.simpl.core.services.strategy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.neethi.Policy;
import org.apache.neethi.PolicyEngine;
import org.apache.neethi.util.PolicyComparator;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagementClient;

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
public class StrategyServiceImpl implements StrategyService {
  static Logger logger = Logger.getLogger(StrategyServiceImpl.class);

  @Override
  public DataSource findDataSource(DataSource dataSource) {

    if (logger.isDebugEnabled()) {
      logger.debug("Name of incoming ds: " + dataSource.getName());
      logger.debug("Address of incoming ds: " + dataSource.getAddress());
      logger.debug("Type of incoming ds: " + dataSource.getType());
      logger.debug("Subtype of incoming ds: " + dataSource.getSubType());
      logger
          .debug("UserName of incoming ds: " + dataSource.getAuthentication().getUser());
      logger.debug("Password of incoming ds: "
          + dataSource.getAuthentication().getPassword());
      logger.debug("Format of incoming ds: " + dataSource.getDataFormat());
      logger.debug("Policy of incoming ds: " + dataSource.getLateBinding().getPolicy());
      logger.debug("Strategy of incoming ds: "
          + dataSource.getLateBinding().getStrategy().toString());
      logger.debug("Resource Management address of incoming ds: "
          + dataSource.getLateBinding().getResourceManagementAddress());
    }

    DataSource resultDataSource = null;
    boolean hasDsPolicy = dataSource.getLateBinding().getPolicy() != null;

    if (logger.isDebugEnabled()) {
      logger.debug("Has data source a policy is: " + hasDsPolicy);
    }

    boolean comparePolicy = true;

    String wsPolicy = null;
    Policy dsPolicyObj = null;
    Policy wsPolicyObj = null;
    URL policyURL = null;
    org.simpl.core.webservices.client.DataSource resultRFDataSource = null;

    if (hasDsPolicy) {
      dsPolicyObj = PolicyEngine.getPolicy(new ByteArrayInputStream(dataSource
          .getLateBinding().getPolicy().getBytes()));

      switch (dataSource.getLateBinding().getStrategy()) {
      case FIRST_FIND:
        List<org.simpl.core.webservices.client.DataSource> dataSources = null;

        try {
          dataSources = ResourceManagementClient
              .getService(dataSource.getLateBinding().getResourceManagementAddress())
              .getAllDataSources().getDataSources();
        } catch (Exception_Exception e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }

        // retrieve all data sources and compare them to the given data
        // source
        for (org.simpl.core.webservices.client.DataSource rfDataSource : dataSources) {
          comparePolicy = rfDataSource.getLateBinding().getPolicy() != null;

          if (logger.isDebugEnabled()) {
            logger.debug("Policy of current RF ds: "
                + rfDataSource.getLateBinding().getPolicy());
          }

          if (comparePolicy) {
            wsPolicy = rfDataSource.getLateBinding().getPolicy();

            // check if wsPolicy contains an URL
            try {
              policyURL = new URL(wsPolicy);
              wsPolicyObj = PolicyEngine.getPolicy(policyURL.openConnection()
                  .getInputStream());
            } catch (MalformedURLException e) {
              wsPolicyObj = PolicyEngine.getPolicy(new ByteArrayInputStream(wsPolicy
                  .getBytes()));
            } catch (IOException e) {
              e.printStackTrace();
            }

            // comparison
            if (PolicyComparator.compare(wsPolicyObj, dsPolicyObj)) {
              resultRFDataSource = rfDataSource;
              break;
            }
          }
        }

        if (resultRFDataSource != null) {
          resultDataSource = new DataSource();
          resultDataSource.setName(resultRFDataSource.getName());
          resultDataSource.setType(resultRFDataSource.getType());
          resultDataSource.setSubType(resultRFDataSource.getSubType());

          resultDataSource.getLateBinding().setPolicy(
              resultRFDataSource.getLateBinding().getPolicy());

          resultDataSource.setAddress(resultRFDataSource.getAddress());
          resultDataSource.setDataFormat(resultRFDataSource.getDataFormat());
          resultDataSource.getAuthentication().setUser(
              resultRFDataSource.getAuthentication().getUser());
          resultDataSource.getAuthentication().setPassword(
              resultRFDataSource.getAuthentication().getPassword());
        } else {
          resultDataSource = dataSource;
        }
        break;
      default:
        break;
      }
    } else {
      // No policy is specified, so the resultDataSource is the input data
      // source
      resultDataSource = dataSource;
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
      logger.debug("Format of result ds: " + resultDataSource.getDataFormat());
      logger.debug("Policy of result ds: "
          + resultDataSource.getLateBinding().getPolicy());
      logger.debug("Resource Management address of result ds: "
          + resultDataSource.getLateBinding().getResourceManagementAddress());
    }

    return resultDataSource;
  }
}