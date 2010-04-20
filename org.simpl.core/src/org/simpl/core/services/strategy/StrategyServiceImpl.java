package org.simpl.core.services.strategy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.neethi.Policy;
import org.apache.neethi.PolicyEngine;
import org.apache.neethi.util.PolicyComparator;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSourceService;
import org.simpl.uddi.client.UddiDataSource;
import org.simpl.uddi.client.UddiDataSourceReader;

/**
 * <b>Purpose:</b> The strategy service is used by a datasource service in case
 * there is given a WS-Policy instead of address, username and password.<br>
 * <b>Description:</b> The available data sources are retrieved from a UDDI
 * Registry that is responded by a UDDI client.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class StrategyServiceImpl implements StrategyService {
  @Override
  public DataSourceService findDataSourceService(String wsPolicy,
      Strategy strategy) {
    DataSourceService resultDataSourceService = null;

    switch (strategy) {
    case FIRST_FIND:
      ArrayList<UddiDataSource> dataSources = UddiDataSourceReader
          .getInstance().getAllDatasources();
      String dsPolicy = null;
      Policy dsPolicyObj = null;
      Policy wsPolicyObj = null;
      URL policyURL = null;
      UddiDataSource resultUddiDataSource = null;
      String dsType = null;
      String dsSubtype = null;

      // retrieve data sources and compare the policies
      for (UddiDataSource uddiDataSource : dataSources) {
        dsPolicy = uddiDataSource.getAttributeValue("wspolicy");

        // check if wsPolicy contains an URL
        try {
          policyURL = new URL(dsPolicy);
          dsPolicyObj = PolicyEngine.getPolicy(policyURL.openConnection()
              .getInputStream());
        } catch (MalformedURLException e) {
          dsPolicyObj = PolicyEngine.getPolicy(new ByteArrayInputStream(
              wsPolicy.getBytes()));
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        // compare the policy assertions
        if (PolicyComparator.compare(wsPolicyObj, dsPolicyObj)) {
          resultUddiDataSource = uddiDataSource;
          break;
        }
      }

      dsType = resultUddiDataSource.getAttributeValue("type");
      dsSubtype = resultUddiDataSource.getAttributeValue("subtype");

      resultDataSourceService = SIMPLCore.getInstance().dataSourceService(
          dsType, dsSubtype);
      break;
    default:
      break;
    }

    return resultDataSourceService;
  }
}
