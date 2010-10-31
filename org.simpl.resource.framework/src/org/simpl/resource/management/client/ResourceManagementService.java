
package org.simpl.resource.management.client;

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
@WebServiceClient(name = "ResourceManagementService", targetNamespace = "http://management.resource.simpl.org/", wsdlLocation = "http://localhost:8080/axis2/services/ResourceManagementService.ResourceManagementPort?wsdl")
public class ResourceManagementService
    extends Service
{

    private final static URL RESOURCEMANAGEMENTSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(org.simpl.resource.management.client.ResourceManagementService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = org.simpl.resource.management.client.ResourceManagementService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/axis2/services/ResourceManagementService.ResourceManagementPort?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/axis2/services/ResourceManagementService.ResourceManagementPort?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        RESOURCEMANAGEMENTSERVICE_WSDL_LOCATION = url;
    }

    public ResourceManagementService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ResourceManagementService() {
        super(RESOURCEMANAGEMENTSERVICE_WSDL_LOCATION, new QName("http://management.resource.simpl.org/", "ResourceManagementService"));
    }

    /**
     * 
     * @return
     *     returns ResourceManagement
     */
    @WebEndpoint(name = "ResourceManagementPort")
    public ResourceManagement getResourceManagementPort() {
        return super.getPort(new QName("http://management.resource.simpl.org/", "ResourceManagementPort"), ResourceManagement.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ResourceManagement
     */
    @WebEndpoint(name = "ResourceManagementPort")
    public ResourceManagement getResourceManagementPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://management.resource.simpl.org/", "ResourceManagementPort"), ResourceManagement.class, features);
    }

}
