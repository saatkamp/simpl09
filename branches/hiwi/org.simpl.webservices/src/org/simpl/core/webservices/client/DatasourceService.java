
package org.simpl.core.webservices.client;

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
     * @return
     *     returns org.simpl.core.webservices.client.Converter
     */
    @WebMethod(action = "getConverter")
    @WebResult(partName = "return")
    public Converter dummy();

    /**
     * 
     * @param dataSource
     * @param filter
     * @return
     *     returns java.lang.String
     * @throws Exception_Exception
     */
    @WebMethod(action = "getMetaData")
    @WebResult(partName = "return")
    public String getMetaData(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "filter", partName = "filter")
        String filter)
        throws Exception_Exception
    ;

    /**
     * 
     * @param statement
     * @param dataSource
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "executeStatement")
    @WebResult(partName = "return")
    public boolean executeStatement(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "statement", partName = "statement")
        String statement)
        throws Exception_Exception
    ;

    /**
     * 
     * @param statement
     * @param dataSource
     * @return
     *     returns java.lang.String
     * @throws Exception_Exception
     */
    @WebMethod(action = "retrieveData")
    @WebResult(partName = "return")
    public String retrieveData(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "statement", partName = "statement")
        String statement)
        throws Exception_Exception
    ;

    /**
     * 
     * @param dataSource
     * @param dataObject
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "writeBack")
    @WebResult(partName = "return")
    public boolean writeBack(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "dataObject", partName = "dataObject")
        String dataObject)
        throws Exception_Exception
    ;

    /**
     * 
     * @param dataSource
     * @param target
     * @param dataObject
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "writeData")
    @WebResult(partName = "return")
    public boolean writeData(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "dataObject", partName = "dataObject")
        String dataObject,
        @WebParam(name = "target", partName = "target")
        String target)
        throws Exception_Exception
    ;

    /**
     * 
     * @param statement
     * @param dataSource
     * @param target
     * @return
     *     returns boolean
     * @throws Exception_Exception
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
        throws Exception_Exception
    ;

    /**
     * 
     * @param dataSource
     * @return
     *     returns java.lang.String
     * @throws Exception_Exception
     */
    @WebMethod(action = "getMetaDataSchema")
    @WebResult(partName = "return")
    public String getMetaDataSchema(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource)
        throws Exception_Exception
    ;

    /**
     * 
     * @param dataSource
     * @return
     *     returns java.lang.String
     * @throws Exception_Exception
     */
    @WebMethod(action = "getDataFormatSchema")
    @WebResult(partName = "return")
    public String getDataFormatSchema(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource)
        throws Exception_Exception
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
    @WebMethod(action = "getDataSourceSubTypes")
    @WebResult(partName = "return")
    public String getDataSourceSubTypes(
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
     * @param dataSource
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getSupportedDataFormatTypes")
    @WebResult(partName = "return")
    public String getSupportedDataFormatTypes(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource);

    /**
     * 
     * @param dataSource
     * @param dataFormatType
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getSupportedConvertDataFormatTypes")
    @WebResult(partName = "return")
    public String getSupportedConvertDataFormatTypes(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "dataFormatType", partName = "dataFormatType")
        String dataFormatType);

}
