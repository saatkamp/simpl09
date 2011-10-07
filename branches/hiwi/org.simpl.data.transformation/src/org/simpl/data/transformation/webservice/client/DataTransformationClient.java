package org.simpl.data.transformation.webservice.client;

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
public class DataTransformationClient {
  static String serviceAddress = null;

  private static DataTransformation service = null;

  public static DataTransformation getService() {
    if (service == null && isAvailable()) {
      service = new DataTransformationService().getDataTransformationPort();
    }

    return service;
  }

  public static DataTransformation getService(String address) {
    if (address != null) {
      serviceAddress = address;
    }
    
    if (isAvailable()) {
      service = new DataTransformationService().getDataTransformationPort();
    } else {
      service = null;
    }

    return service;
  }
  
  /**
   * Checks if the web service is available.
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