package org.simpl.resource.management.webinterface;


import javax.servlet.http.HttpServletRequest;

// TODO: benutzen
public class DataSourceFormValidator {
  public static String validateForm(HttpServletRequest request) {
    String message = "";
    String name = request.getParameter("name");

    if (name.equals("")) {
      message = message + "Field name must not be empty <br>";
    }

    return message;
  }
}