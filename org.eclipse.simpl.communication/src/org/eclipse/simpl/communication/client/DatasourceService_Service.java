
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
@WebServiceClient(name = "DatasourceService", targetNamespace = "http://webservices.core.simpl.org/", wsdlLocation = "http://localhost:9090/ode/processes/DatasourceService.DatasourceServicePort?wsdl")
public class DatasourceService_Service
    extends Service
{

	private final static String DSS_WSDL_ADDRESS = CommunicationPlugIn.getDefault().getPreferenceStore().getString("SIMPL_CORE_DSS_ADDRESS");
	
    private final static URL DATASOURCESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(DatasourceService_Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = DatasourceService_Service.class.getResource(".");
            url = new URL(baseUrl, DSS_WSDL_ADDRESS);
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: '"+DSS_WSDL_ADDRESS+"', retrying as a local file");
            logger.warning(e.getMessage());
        }
        DATASOURCESERVICE_WSDL_LOCATION = url;
    }

    public DatasourceService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DatasourceService_Service() {
        super(DATASOURCESERVICE_WSDL_LOCATION, new QName("http://webservices.core.simpl.org/", "DatasourceService"));
    }

    /**
     * 
     * @return
     *     returns DatasourceService
     */
    @WebEndpoint(name = "DatasourceServicePort")
    public DatasourceService getDatasourceServicePort() {
        return super.getPort(new QName("http://webservices.core.simpl.org/", "DatasourceServicePort"), DatasourceService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DatasourceService
     */
    @WebEndpoint(name = "DatasourceServicePort")
    public DatasourceService getDatasourceServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.core.simpl.org/", "DatasourceServicePort"), DatasourceService.class, features);
    }

}
