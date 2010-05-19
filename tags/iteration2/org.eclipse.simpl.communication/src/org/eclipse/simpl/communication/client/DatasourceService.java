
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
     * @param dataSource
     * @param filter
     * @return
     *     returns java.lang.String
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "getMetaData")
    @WebResult(partName = "return")
    public String getMetaData(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "filter", partName = "filter")
        String filter)
        throws ConnectionException_Exception
    ;

    /**
     * 
     * @param statement
     * @param dataSource
     * @return
     *     returns boolean
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "executeStatement")
    @WebResult(partName = "return")
    public boolean executeStatement(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "statement", partName = "statement")
        String statement)
        throws ConnectionException_Exception
    ;

    /**
     * 
     * @param statement
     * @param dataSource
     * @return
     *     returns java.lang.String
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "retrieveData")
    @WebResult(partName = "return")
    public String retrieveData(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "statement", partName = "statement")
        String statement)
        throws ConnectionException_Exception
    ;

    /**
     * 
     * @param statement
     * @param dataSource
     * @param target
     * @return
     *     returns boolean
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "depositData")
    @WebResult(partName = "return")
    public boolean depositData(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "statement", partName = "statement")
        String statement,
        @WebParam(name = "target", partName = "target")
        String target)
        throws ConnectionException_Exception
    ;

    /**
     * 
     * @param dataSource
     * @param data
     * @return
     *     returns boolean
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "writeBack")
    @WebResult(partName = "return")
    public boolean writeBack(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "data", partName = "data")
        String data)
        throws ConnectionException_Exception
    ;

    /**
     * 
     * @param dataSource
     * @return
     *     returns java.lang.String
     * @throws ConnectionException_Exception
     */
    @WebMethod(action = "getMetaDataSchema")
    @WebResult(partName = "return")
    public String getMetaDataSchema(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource)
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

}