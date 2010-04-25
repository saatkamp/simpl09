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
 * <b>Purpose:</b> The strategy service is used by a datasource service in case
 * there is given a WS-Policy instead of address, type and subtype.<br>
 * <b>Description:</b> The available data sources are retrieved from a UDDI
 * Registry that is responded by a UDDI client.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: StrategyServiceImpl.java 1159 2010-04-20 18:19:11Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class StrategyServiceImpl implements StrategyService {
  @Override
  public DataSource findDataSource(DataSource dataSource) {
    DataSource resultDataSource = null;
    boolean compareWsPolicy = dataSource.getPolicy() != null
        && !dataSource.getPolicy().equals("");
    boolean compareType = dataSource.getType() != null
        && !dataSource.getType().equals("");
    boolean compareSubtype = dataSource.getSubType() != null
        && !dataSource.getSubType().equals("");

    switch (dataSource.getStrategy()) {
    case FIRST_FIND:
      ArrayList<UddiDataSource> dataSources = UddiDataSourceReader
          .getInstance().getAllDatasources();
      String dsPolicy = null;
      Policy dsPolicyObj = null;
      Policy wsPolicyObj = null;
      URL policyURL = null;
      UddiDataSource resultUddiDataSource = null;

      // retrieve data sources and compare the policies
      for (UddiDataSource uddiDataSource : dataSources) {
        if (dataSource.getPolicy() != null
            && !dataSource.getPolicy().equals("")) {
          dsPolicy = uddiDataSource.getWsPolicy();

          // check if wsPolicy contains an URL
          try {
            policyURL = new URL(dsPolicy);
            dsPolicyObj = PolicyEngine.getPolicy(policyURL.openConnection()
                .getInputStream());
          } catch (MalformedURLException e) {
            dsPolicyObj = PolicyEngine.getPolicy(new ByteArrayInputStream(
                dataSource.getPolicy().getBytes()));
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }

        // comparison
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
      resultDataSource.setPolicy(resultUddiDataSource.getWsPolicy());
      resultDataSource.setAddress(resultUddiDataSource.getAddress());
      // TODO Username, Passwort fehlen noch aus der UddIDataSource

      break;
    default:
      break;
    }

    return resultDataSource;
  }
}