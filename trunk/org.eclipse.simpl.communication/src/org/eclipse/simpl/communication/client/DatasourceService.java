
package org.eclipse.simpl.communication.client;

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
@WebService(name = "DatasourceService", targetNamespace = "http://webservices.core.simpl.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DatasourceService {


    /**
     * 
     * @param statement
     * @param dsAddress
     * @param dsSubtype
     * @param dsType
     * @return
     *     returns java.lang.String
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "queryData")
    @WebResult(partName = "return")
    public String queryData(
        @WebParam(name = "dsAddress", partName = "dsAddress")
        String dsAddress,
        @WebParam(name = "statement", partName = "statement")
        String statement,
        @WebParam(name = "dsType", partName = "dsType")
        String dsType,
        @WebParam(name = "dsSubtype", partName = "dsSubtype")
        String dsSubtype)
        throws ConnectionException_Exception
    ;

    /**
     * 
     * @param statement
     * @param dsAddress
     * @param dsSubtype
     * @param dsType
     * @return
     *     returns boolean
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "defineData")
    @WebResult(partName = "return")
    public boolean defineData(
        @WebParam(name = "dsAddress", partName = "dsAddress")
        String dsAddress,
        @WebParam(name = "statement", partName = "statement")
        String statement,
        @WebParam(name = "dsType", partName = "dsType")
        String dsType,
        @WebParam(name = "dsSubtype", partName = "dsSubtype")
        String dsSubtype)
        throws ConnectionException_Exception
    ;

    /**
     * 
     * @param statement
     * @param dsAddress
     * @param dsSubtype
     * @param data
     * @param dsType
     * @return
     *     returns boolean
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "manipulateData")
    @WebResult(partName = "return")
    public boolean manipulateData(
        @WebParam(name = "dsAddress", partName = "dsAddress")
        String dsAddress,
        @WebParam(name = "statement", partName = "statement")
        String statement,
        @WebParam(name = "data", partName = "data")
        String data,
        @WebParam(name = "dsType", partName = "dsType")
        String dsType,
        @WebParam(name = "dsSubtype", partName = "dsSubtype")
        String dsSubtype)
        throws ConnectionException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getDatasourceTypes")
    @WebResult(partName = "return")
    public String getDatasourceTypes();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getDatasourceSubtypes")
    @WebResult(partName = "return")
    public String getDatasourceSubtypes(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getDatasourceLanguages")
    @WebResult(partName = "return")
    public String getDatasourceLanguages(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
