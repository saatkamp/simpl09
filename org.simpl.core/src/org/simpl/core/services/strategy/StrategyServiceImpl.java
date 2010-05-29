package org.simpl.core.services.strategy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.neethi.Policy;
import org.apache.neethi.PolicyEngine;
import org.apache.neethi.util.PolicyComparator;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.uddi.client.UddiDataSource;
import org.simpl.uddi.client.UddiDataSourceReader;

/**
 * <b>Purpose:</b>Implementation of the strategy service.<br>
 * <b>Description:</b>Uses a UDDI client to respond to the UDDI registry that is defined
 * in the unspecified data source information.<br>
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
  @Override
  public DataSource findDataSource(DataSource dataSource) {
    DataSource resultDataSource = null;
    boolean compareWsPolicy = dataSource.getLateBinding().getPolicy() != null;
    boolean compareType = dataSource.getType() != null;
    boolean compareSubtype = dataSource.getSubType() != null;

    switch (dataSource.getLateBinding().getStrategy()) {
    case FIRST_FIND:
      ArrayList<UddiDataSource> dataSources = UddiDataSourceReader.getInstance(
          dataSource.getLateBinding().getUddiAddress()).getAllDatasources();
      String dsPolicy = null;
      Policy dsPolicyObj = null;
      Policy wsPolicyObj = null;
      URL policyURL = null;
      UddiDataSource resultUddiDataSource = null;

      // retrieve all data sources and compare them to the given data source
      for (UddiDataSource uddiDataSource : dataSources) {
        if (compareWsPolicy) {
          dsPolicy = uddiDataSource.getWsPolicy();

          // check if wsPolicy contains an URL
          try {
            policyURL = new URL(dsPolicy);
            dsPolicyObj = PolicyEngine.getPolicy(policyURL.openConnection()
                .getInputStream());
          } catch (MalformedURLException e) {
            dsPolicyObj = PolicyEngine.getPolicy(new ByteArrayInputStream(dataSource
                .getLateBinding().getPolicy().getBytes()));
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }

        // comparison cases
        if (compareType && compareSubtype && compareWsPolicy) {
          if (PolicyComparator.compare(wsPolicyObj, dsPolicyObj)
              && uddiDataSource.getType().equals(dataSource.getType())
              && uddiDataSource.getSubtype().equals(dataSource.getSubType())) {
            resultUddiDataSource = uddiDataSource;
            break;
          }
        } else if (compareType && compareSubtype) {
          if (uddiDataSource.getType().equals(dataSource.getType())
              && uddiDataSource.getSubtype().equals(dataSource.getSubType())) {
            resultUddiDataSource = uddiDataSource;
            break;
          }
        } else if (compareType && compareWsPolicy) {
          if (PolicyComparator.compare(wsPolicyObj, dsPolicyObj)
              && uddiDataSource.getType().equals(dataSource.getType())) {
            resultUddiDataSource = uddiDataSource;
            break;
          }
        } else if (compareSubtype && compareWsPolicy) {
          if (PolicyComparator.compare(wsPolicyObj, dsPolicyObj)
              && uddiDataSource.getSubtype().equals(dataSource.getSubType())) {
            resultUddiDataSource = uddiDataSource;
            break;
          }
        } else if (compareWsPolicy) {
          if (PolicyComparator.compare(wsPolicyObj, dsPolicyObj)) {
            resultUddiDataSource = uddiDataSource;
            break;
          }
        }
      }

      resultDataSource = new DataSource();
      resultDataSource.setType(resultUddiDataSource.getType());
      resultDataSource.setSubType(resultUddiDataSource.getSubtype());
      resultDataSource.getLateBinding().setPolicy(resultUddiDataSource.getWsPolicy());
      resultDataSource.setAddress(resultUddiDataSource.getAddress());
      resultDataSource.setDataFormat(resultUddiDataSource.getDataFormat());
      resultDataSource.getAuthentication().setUser(resultUddiDataSource.getUsername());
      resultDataSource.getAuthentication()
          .setPassword(resultUddiDataSource.getPassword());

      break;
    default:
      break;
    }

    return resultDataSource;
  }
}