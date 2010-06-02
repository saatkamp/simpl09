
package org.simpl.rrs.webservices.client;

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
@WebService(name = "RRSManagementService", targetNamespace = "http://webservices.rrs.simpl.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class, EPR.class
})
public interface RRSManagementService {


    /**
     * 
     * @param epr
     * @return
     *     returns boolean
     */
    @WebMethod(action = "delete")
    @WebResult(partName = "return")
    public boolean delete(
        @WebParam(name = "epr", partName = "epr")
        EPR epr);

    /**
     * 
     * @param epr
     * @return
     *     returns boolean
     */
    @WebMethod(action = "insert")
    @WebResult(partName = "return")
    public boolean insert(
        @WebParam(name = "epr", partName = "epr")
        EPR epr);

    /**
     * 
     * @param epr
     * @return
     *     returns boolean
     */
    @WebMethod(action = "update")
    @WebResult(partName = "return")
    public boolean update(
        @WebParam(name = "epr", partName = "epr")
        EPR epr);

}
