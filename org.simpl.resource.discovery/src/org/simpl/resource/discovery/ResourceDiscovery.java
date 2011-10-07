package org.simpl.resource.discovery;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.neethi.Policy;
import org.apache.neethi.PolicyEngine;
import org.apache.neethi.util.PolicyComparator;
import org.simpl.resource.management.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.LateBinding;

/**
 * <b>Purpose:</b>The Resource Discovery enables the strategic search for resources in the
 * Resource Management. This class represents the Resource Discovery and the web service
 * interface.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 * @author Michael Schneidt <michael.schneidt@arcor.de>
 */
@WebService(name = "ResourceDiscovery")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ResourceDiscovery {
  @WebMethod(action = "findDataSource")
  public DataSource findDataSource(LateBinding lateBinding) {
    boolean policyExists = (lateBinding.getPolicy() != null && !lateBinding.getPolicy()
        .equals(""));
    boolean comparePolicyExists = false;

    String policyAsString = null;
    Policy policyObj = null;
    Policy comparePolicyObj = null;
    URL policyURL = null;
    DataSource resultDataSource = new DataSource();

    if (policyExists) {
      policyObj = PolicyEngine.getPolicy(new ByteArrayInputStream(lateBinding.getPolicy()
          .getBytes()));

      switch (lateBinding.getStrategy()) {
      case FIRST_FIND:
        List<DataSource> dataSources = null;
        String address = ResourceDiscoveryConfig.getInstance().getAddress();
        
        if (address != null && !address.equals("")) {
          // access the Resource Management through web service
          try {
            org.simpl.resource.management.client.ResourceManagement resourceManagement = ResourceManagementClient
                .getService(address);
            dataSources = resourceManagement.getAllDataSources().getDataSources();
          } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
        } else {
          // access the Resource Management directly in class path
          try {
            org.simpl.resource.management.ResourceManagement resourceManagement = new ResourceManagement();
            dataSources = resourceManagement.getAllDataSources().getDataSources();
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }

        // retrieve all data sources and compare the policy to its properties description
        // policy
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

    return resultDataSource;
  }
}