
package org.simpl.resource.framework.client;

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
@WebServiceClient(name = "ResourceFrameworkService", targetNamespace = "http://framework.resource.simpl.org/", wsdlLocation = "http://localhost:8080/ode/processes/ResourceFrameworkService.ResourceFrameworkPort?wsdl")
public class ResourceFrameworkService
    extends Service
{

    private final static URL RESOURCEFRAMEWORKSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(org.simpl.resource.framework.client.ResourceFrameworkService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = org.simpl.resource.framework.client.ResourceFrameworkService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/ode/processes/ResourceFrameworkService.ResourceFrameworkPort?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/ode/processes/ResourceFrameworkService.ResourceFrameworkPort?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        RESOURCEFRAMEWORKSERVICE_WSDL_LOCATION = url;
    }

    public ResourceFrameworkService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ResourceFrameworkService() {
        super(RESOURCEFRAMEWORKSERVICE_WSDL_LOCATION, new QName("http://framework.resource.simpl.org/", "ResourceFrameworkService"));
    }

    /**
     * 
     * @return
     *     returns ResourceFramework
     */
    @WebEndpoint(name = "ResourceFrameworkPort")
    public ResourceFramework getResourceFrameworkPort() {
        return super.getPort(new QName("http://framework.resource.simpl.org/", "ResourceFrameworkPort"), ResourceFramework.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ResourceFramework
     */
    @WebEndpoint(name = "ResourceFrameworkPort")
    public ResourceFramework getResourceFrameworkPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://framework.resource.simpl.org/", "ResourceFrameworkPort"), ResourceFramework.class, features);
    }

}
