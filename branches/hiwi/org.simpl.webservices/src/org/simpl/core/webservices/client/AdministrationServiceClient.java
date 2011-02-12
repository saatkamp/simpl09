package org.simpl.core.webservices.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
public class AdministrationServiceClient {
  static String serviceAddress = null;

  private static AdministrationService service = null;

  public static AdministrationService getService() {
    if (service == null && isAvailable()) {
      service = new AdministrationService_Service().getAdministrationServicePort();
    }

    return service;
  }

  public static AdministrationService getService(String address) {
    if (address != null) {
      serviceAddress = address;
    }
    
    if (isAvailable()) {
      service = new AdministrationService_Service().getAdministrationServicePort();
    } else {
      service = null;
    }

    return service;
  }
  
  /**
   * Checks if the administration web service is available.
   * 
   * @return
   */
  public static boolean isAvailable() {
    boolean available = false;

    HttpURLConnection con;

    try {
      con = (HttpURLConnection) new URL(serviceAddress).openConnection();
      con.setRequestMethod("HEAD");

      if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
        available = true;
      }
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return available;
  }
}