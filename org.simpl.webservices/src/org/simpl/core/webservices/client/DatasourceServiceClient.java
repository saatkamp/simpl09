package org.simpl.core.webservices.client;

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
public class DatasourceServiceClient {
  static String serviceAddress = null;

  private static DatasourceService service = null;

  public static DatasourceService getService() {
    if (service == null) {
      service = new DatasourceService_Service().getDatasourceServicePort();
    }

    return service;
  }

  public static DatasourceService getService(String address) {
    if (address != null) {
      serviceAddress = address;

      service = new DatasourceService_Service().getDatasourceServicePort();
    }

    return service;
  }
}