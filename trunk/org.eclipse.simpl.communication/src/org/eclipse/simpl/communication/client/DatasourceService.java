
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
     * @param dsAddress
     * @param dsSubtype
     * @param dsType
     * @return
     *     returns java.lang.String
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "getMetaData")
    @WebResult(partName = "return")
    public String getMetaData(
        @WebParam(name = "dsAddress", partName = "dsAddress")
        String dsAddress,
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
     *     returns java.lang.String
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "retrieveData")
    @WebResult(partName = "return")
    public String retrieveData(
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
     * @param target
     * @param dsType
     * @return
     *     returns boolean
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "depositData")
    @WebResult(partName = "return")
    public boolean depositData(
        @WebParam(name = "dsAddress", partName = "dsAddress")
        String dsAddress,
        @WebParam(name = "statement", partName = "statement")
        String statement,
        @WebParam(name = "dsType", partName = "dsType")
        String dsType,
        @WebParam(name = "dsSubtype", partName = "dsSubtype")
        String dsSubtype,
        @WebParam(name = "target", partName = "target")
        String target)
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
    @WebMethod(action = "executeStatement")
    @WebResult(partName = "return")
    public boolean executeStatement(
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
     * @param dsAddress
     * @param dsSubtype
     * @param data
     * @param dsType
     * @return
     *     returns boolean
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "writeBack")
    @WebResult(partName = "return")
    public boolean writeBack(
        @WebParam(name = "dsAddress", partName = "dsAddress")
        String dsAddress,
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
     * @param dsAddress
     * @param dsSubtype
     * @param dsType
     * @return
     *     returns java.lang.String
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "getMetaDataSchema")
    @WebResult(partName = "return")
    public String getMetaDataSchema(
        @WebParam(name = "dsAddress", partName = "dsAddress")
        String dsAddress,
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
    @WebMethod(action = "getDataSourceTypes")
    @WebResult(partName = "return")
    public String getDataSourceTypes();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getDataSourceSubtypes")
    @WebResult(partName = "return")
    public String getDataSourceSubtypes(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getDataSourceLanguages")
    @WebResult(partName = "return")
    public String getDataSourceLanguages(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getDataFormatTypes")
    @WebResult(partName = "return")
    public String getDataFormatTypes();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getDataFormatSubtypes")
    @WebResult(partName = "return")
    public String getDataFormatSubtypes(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
