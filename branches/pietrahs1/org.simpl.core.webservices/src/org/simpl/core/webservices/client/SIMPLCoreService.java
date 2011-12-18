
package org.simpl.core.webservices.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.LateBinding;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "SIMPLCoreService", targetNamespace = "http://webservices.core.simpl.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SIMPLCoreService {


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
     * @param lateBinding
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "issueCommandByDataSource")
    @WebResult(partName = "return")
    public boolean issueCommandByDataSource(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "statement", partName = "statement")
        String statement,
        @WebParam(name = "lateBinding", partName = "lateBinding")
        LateBinding lateBinding)
        throws Exception_Exception
    ;

    /**
     * 
     * @param statement
     * @param lateBinding
     * @param dataSourceName
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "executeStatementByDataSourceName")
    @WebResult(partName = "return")
    public boolean issueCommandByDataSourceName(
        @WebParam(name = "dataSourceName", partName = "dataSourceName")
        String dataSourceName,
        @WebParam(name = "statement", partName = "statement")
        String statement,
        @WebParam(name = "lateBinding", partName = "lateBinding")
        LateBinding lateBinding)
        throws Exception_Exception
    ;

    /**
     * 
     * @param statement
     * @param dataSource
     * @param lateBinding
     * @return
     *     returns java.lang.String
     * @throws Exception_Exception
     */
    @WebMethod(action = "retrieveDataByDataSource")
    @WebResult(partName = "return")
    public String retrieveDataByDataSource(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "statement", partName = "statement")
        String statement,
        @WebParam(name = "lateBinding", partName = "lateBinding")
        LateBinding lateBinding)
        throws Exception_Exception
    ;

    /**
     * 
     * @param statement
     * @param lateBinding
     * @param dataSourceName
     * @return
     *     returns java.lang.String
     * @throws Exception_Exception
     */
    @WebMethod(action = "retrieveDataByDataSourceName")
    @WebResult(partName = "return")
    public String retrieveDataByDataSourceName(
        @WebParam(name = "dataSourceName", partName = "dataSourceName")
        String dataSourceName,
        @WebParam(name = "statement", partName = "statement")
        String statement,
        @WebParam(name = "lateBinding", partName = "lateBinding")
        LateBinding lateBinding)
        throws Exception_Exception
    ;

    /**
     * 
     * @param dataSource
     * @param lateBinding
     * @param target
     * @param dataObject
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "writeDataBackByDataSource")
    @WebResult(partName = "return")
    public boolean writeDataBackByDataSource(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "dataObject", partName = "dataObject")
        String dataObject,
        @WebParam(name = "target", partName = "target")
        String target,
        @WebParam(name = "lateBinding", partName = "lateBinding")
        LateBinding lateBinding)
        throws Exception_Exception
    ;

    /**
     * 
     * @param lateBinding
     * @param target
     * @param dataSourceName
     * @param dataObject
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "writeDataBackByDataSourceName")
    @WebResult(partName = "return")
    public boolean writeDataBackByDataSourceName(
        @WebParam(name = "dataSourceName", partName = "dataSourceName")
        String dataSourceName,
        @WebParam(name = "dataObject", partName = "dataObject")
        String dataObject,
        @WebParam(name = "target", partName = "target")
        String target,
        @WebParam(name = "lateBinding", partName = "lateBinding")
        LateBinding lateBinding)
        throws Exception_Exception
    ;

    /**
     * 
     * @param statement
     * @param dataSource
     * @param lateBinding
     * @param target
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "queryDataByDataSource")
    @WebResult(partName = "return")
    public boolean queryDataByDataSource(
        @WebParam(name = "dataSource", partName = "dataSource")
        DataSource dataSource,
        @WebParam(name = "statement", partName = "statement")
        String statement,
        @WebParam(name = "target", partName = "target")
        String target,
        @WebParam(name = "lateBinding", partName = "lateBinding")
        LateBinding lateBinding)
        throws Exception_Exception
    ;

    /**
     * 
     * @param statement
     * @param lateBinding
     * @param target
     * @param dataSourceName
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "queryDataByDataSourceName")
    @WebResult(partName = "return")
    public boolean queryDataByDataSourceName(
        @WebParam(name = "dataSourceName", partName = "dataSourceName")
        String dataSourceName,
        @WebParam(name = "statement", partName = "statement")
        String statement,
        @WebParam(name = "target", partName = "target")
        String target,
        @WebParam(name = "lateBinding", partName = "lateBinding")
        LateBinding lateBinding)
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

}
