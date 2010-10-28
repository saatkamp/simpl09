package org.simpl.resource.framework.client;


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
public class ResourceFrameworkClient {
  public static String serviceAddress = "http://localhost:8080/ode/processes/ResourceFrameworkService.ResourceFrameworkPort?wsdl";
  private static ResourceFramework service = null;

  public static ResourceFramework getService() {
    if (service == null) {
      service = new ResourceFrameworkService().getResourceFrameworkPort();
    }

    return service;
  }

  public static ResourceFramework getService(String address) {
    if (address != null) {
      serviceAddress = address;
    }

    service = new ResourceFrameworkService().getResourceFrameworkPort();
    
    return service;
  }
}