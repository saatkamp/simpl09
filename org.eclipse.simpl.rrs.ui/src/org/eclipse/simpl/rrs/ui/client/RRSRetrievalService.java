
package org.eclipse.simpl.rrs.ui.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "RRSRetrievalService", targetNamespace = "http://webservices.rrs.simpl.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface RRSRetrievalService {


    /**
     * 
     * @param epr
     * @return
     *     returns org.simpl.rrs.webservices.client.RDBSet
     */
    @WebMethod(action = "get")
    @WebResult(partName = "return")
    public RDBSet get(
        @WebParam(name = "EPR", partName = "EPR")
        EPR epr);

}