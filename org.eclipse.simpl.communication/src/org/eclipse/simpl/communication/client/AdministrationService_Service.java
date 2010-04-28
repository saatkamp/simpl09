
package org.eclipse.simpl.communication.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import org.eclipse.simpl.communication.CommunicationPlugIn;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "AdministrationService", targetNamespace = "http://webservices.core.simpl.org/", wsdlLocation = "http://localhost:8080/ode/processes/AdministrationService.AdministrationServicePort?wsdl")
public class AdministrationService_Service
    extends Service
{
    private final static String AS_WSDL_ADDRESS = CommunicationPlugIn.getDefault().getPreferenceStore().getString("SIMPL_CORE_AS_ADDRESS");
    private final static URL ADMINISTRATIONSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(AdministrationService_Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = AdministrationService_Service.class.getResource(".");
            url = new URL(baseUrl, AS_WSDL_ADDRESS);
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: '" + AS_WSDL_ADDRESS + "', retrying as a local file");
            logger.warning(e.getMessage());
        }
        ADMINISTRATIONSERVICE_WSDL_LOCATION = url;
    }

    public AdministrationService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AdministrationService_Service() {
        super(ADMINISTRATIONSERVICE_WSDL_LOCATION, new QName("http://webservices.core.simpl.org/", "AdministrationService"));
    }

    /**
     * 
     * @return
     *     returns AdministrationService
     */
    @WebEndpoint(name = "AdministrationServicePort")
    public AdministrationService getAdministrationServicePort() {
        return super.getPort(new QName("http://webservices.core.simpl.org/", "AdministrationServicePort"), AdministrationService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AdministrationService
     */
    @WebEndpoint(name = "AdministrationServicePort")
    public AdministrationService getAdministrationServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.core.simpl.org/", "AdministrationServicePort"), AdministrationService.class, features);
    }

}
