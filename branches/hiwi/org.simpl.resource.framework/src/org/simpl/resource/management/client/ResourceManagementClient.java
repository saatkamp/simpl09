package org.simpl.resource.management.client;


/**
 * <b>Purpose:</b><br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class ResourceManagementClient {
  public static String serviceAddress = "http://localhost:8080/axis2/services/ResourceManagementService.ResourceManagementPort?wsdl";
  private static ResourceManagement service = null;

  public static ResourceManagement getService() {
    if (service == null) {
      service = new ResourceManagementService().getResourceManagementPort();
    }

    return service;
  }

  public static ResourceManagement getService(String address) {
    if (address != null) {
      serviceAddress = address;
    }

    service = new ResourceManagementService().getResourceManagementPort();
    
    return service;
  }
}