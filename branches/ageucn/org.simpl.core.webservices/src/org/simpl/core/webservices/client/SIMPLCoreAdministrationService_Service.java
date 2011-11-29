
package org.simpl.core.webservices.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "SIMPLCoreAdministrationService", targetNamespace = "http://webservices.core.simpl.org/", wsdlLocation = "http://localhost:8080/ode/processes/SIMPLCoreAdministrationService.SIMPLCoreAdministrationServicePort?wsdl")
public class SIMPLCoreAdministrationService_Service
    extends Service
{

    private final static URL SIMPLCOREADMINISTRATIONSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(org.simpl.core.webservices.client.SIMPLCoreAdministrationService_Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = org.simpl.core.webservices.client.SIMPLCoreAdministrationService_Service.class.getResource(".");
            url = new URL(baseUrl, SIMPLCoreAdministrationServiceClient.serviceAddress);
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: '" + SIMPLCoreAdministrationServiceClient.serviceAddress + "', retrying as a local file");
            logger.warning(e.getMessage());
        }
        SIMPLCOREADMINISTRATIONSERVICE_WSDL_LOCATION = url;
    }

    public SIMPLCoreAdministrationService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SIMPLCoreAdministrationService_Service() {
        super(SIMPLCOREADMINISTRATIONSERVICE_WSDL_LOCATION, new QName("http://webservices.core.simpl.org/", "SIMPLCoreAdministrationService"));
    }

    /**
     * 
     * @return
     *     returns SIMPLCoreAdministrationService
     */
    @WebEndpoint(name = "SIMPLCoreAdministrationServicePort")
    public SIMPLCoreAdministrationService getSIMPLCoreAdministrationServicePort() {
        return super.getPort(new QName("http://webservices.core.simpl.org/", "SIMPLCoreAdministrationServicePort"), SIMPLCoreAdministrationService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SIMPLCoreAdministrationService
     */
    @WebEndpoint(name = "SIMPLCoreAdministrationServicePort")
    public SIMPLCoreAdministrationService getSIMPLCoreAdministrationServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.core.simpl.org/", "SIMPLCoreAdministrationServicePort"), SIMPLCoreAdministrationService.class, features);
    }

}