
package org.simpl.rrs.transformation.webservices.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "TransformationService", targetNamespace = "http://webservices.transformation.rrs.simpl.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface TransformationService {


    /**
     * 
     * @param bpelFileContent
     * @param rrsWSDLNamespaceURI
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "transform")
    @WebResult(partName = "return")
    public String transform(
        @WebParam(name = "bpelFileContent", partName = "bpelFileContent")
        String bpelFileContent,
        @WebParam(name = "rrsWSDLNamespaceURI", partName = "rrsWSDLNamespaceURI")
        String rrsWSDLNamespaceURI);

}