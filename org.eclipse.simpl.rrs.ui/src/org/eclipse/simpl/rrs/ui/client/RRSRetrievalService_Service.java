
package org.eclipse.simpl.rrs.ui.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import org.eclipse.simpl.rrs.ui.RRSUIPlugIn;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "RRSRetrievalService", targetNamespace = "http://webservices.rrs.simpl.org/", wsdlLocation = "http://localhost:8080/axis2/services/RRSRetrievalService?wsdl")
public class RRSRetrievalService_Service
    extends Service
{
	
	private final static String RRS_RET_WSDL = RRSUIPlugIn.getDefault()
	.getPreferenceStore().getString("RRS_RET_ADDRESS");

    private final static URL RRSRETRIEVALSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(org.eclipse.simpl.rrs.ui.client.RRSRetrievalService_Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = RRSRetrievalService_Service.class.getResource(".");
            url = new URL(baseUrl, RRS_RET_WSDL);
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: '"+ RRS_RET_WSDL+ "', retrying as a local file");
            logger.warning(e.getMessage());
        }
        RRSRETRIEVALSERVICE_WSDL_LOCATION = url;
    }

    public RRSRetrievalService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RRSRetrievalService_Service() {
        super(RRSRETRIEVALSERVICE_WSDL_LOCATION, new QName("http://webservices.rrs.simpl.org/", "RRSRetrievalService"));
    }

    /**
     * 
     * @return
     *     returns RRSRetrievalService
     */
    @WebEndpoint(name = "RRSRetrievalServicePort")
    public RRSRetrievalService getRRSRetrievalServicePort() {
        return super.getPort(new QName("http://webservices.rrs.simpl.org/", "RRSRetrievalServicePort"), RRSRetrievalService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RRSRetrievalService
     */
    @WebEndpoint(name = "RRSRetrievalServicePort")
    public RRSRetrievalService getRRSRetrievalServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.rrs.simpl.org/", "RRSRetrievalServicePort"), RRSRetrievalService.class, features);
    }

}
