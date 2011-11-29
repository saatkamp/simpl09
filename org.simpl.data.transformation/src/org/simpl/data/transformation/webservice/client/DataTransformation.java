
package org.simpl.data.transformation.webservice.client;

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
@WebService(name = "DataTransformation", targetNamespace = "http://webservice.transformation.data.simpl.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DataTransformation {


    /**
     * 
     * @param serviceImpl
     * @param data
     * @param connectorImpl
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "convert")
    @WebResult(partName = "return")
    public String convert(
        @WebParam(name = "serviceImpl", partName = "serviceImpl")
        String serviceImpl,
        @WebParam(name = "data", partName = "data")
        String data,
        @WebParam(name = "connectorImpl", partName = "connectorImpl")
        String connectorImpl);

    /**
     * 
     * @param serviceImpl
     * @param data
     * @param connectorImpl
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "convertTo")
    @WebResult(partName = "return")
    public String convertTo(
        @WebParam(name = "serviceImpl", partName = "serviceImpl")
        String serviceImpl,
        @WebParam(name = "data", partName = "data")
        String data,
        @WebParam(name = "connectorImpl", partName = "connectorImpl")
        String connectorImpl);

    /**
     * 
     * @param serviceImpl
     * @param data
     * @param connectorImpl
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "convertFrom")
    @WebResult(partName = "return")
    public String convertFrom(
        @WebParam(name = "serviceImpl", partName = "serviceImpl")
        String serviceImpl,
        @WebParam(name = "data", partName = "data")
        String data,
        @WebParam(name = "connectorImpl", partName = "connectorImpl")
        String connectorImpl);

}
