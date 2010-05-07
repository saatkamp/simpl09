package org.simpl.core.webservices;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "AdministrationService", targetNamespace = "http://localhost:8080/AdministrationService", wsdlLocation = "AdministrationService.wsdl")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WSAdministrationImpl implements WSAdministration {
  public String sayHi() {
    return "Hi";
  }

  public String sayHelloRemote() {
    return "...";
  }
}