
package org.eclipse.simpl.rrs.transformation.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import org.eclipse.simpl.rrs.transformation.TransformerPlugIn;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "TransformationService", targetNamespace = "http://webservices.transformation.rrs.simpl.org", wsdlLocation = "http://localhost:8080/axis2/services/TransformationService?wsdl")
public class TransformationService_Service
    extends Service
{
	private final static String TRANSFORMER_WSDL = TransformerPlugIn.getDefault()
	.getPreferenceStore().getString("TRANSFORMER_ADDRESS");

    private final static URL TRANSFORMATIONSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(org.eclipse.simpl.rrs.transformation.client.TransformationService_Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = TransformationService_Service.class.getResource(".");
            url = new URL(baseUrl, TRANSFORMER_WSDL);
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: '"+ TRANSFORMER_WSDL +"', retrying as a local file");
            logger.warning(e.getMessage());
        }
        TRANSFORMATIONSERVICE_WSDL_LOCATION = url;
    }

    public TransformationService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TransformationService_Service() {
        super(TRANSFORMATIONSERVICE_WSDL_LOCATION, new QName("http://webservices.transformation.rrs.simpl.org", "TransformationService"));
    }

    /**
     * 
     * @return
     *     returns TransformationService
     */
    @WebEndpoint(name = "TransformationServicePort")
    public TransformationService getTransformationServicePort() {
        return super.getPort(new QName("http://webservices.transformation.rrs.simpl.org", "TransformationServicePort"), TransformationService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TransformationService
     */
    @WebEndpoint(name = "TransformationServicePort")
    public TransformationService getTransformationServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.transformation.rrs.simpl.org", "TransformationServicePort"), TransformationService.class, features);
    }

}
