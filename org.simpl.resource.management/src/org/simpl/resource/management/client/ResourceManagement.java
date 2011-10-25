
package org.simpl.resource.management.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.ConnectorList;
import org.simpl.resource.management.data.DataConverter;
import org.simpl.resource.management.data.DataConverterList;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.DataSourceList;
import org.simpl.resource.management.data.DataTransformationService;
import org.simpl.resource.management.data.DataTransformationServiceList;
import org.simpl.resource.management.data.StrategyPlugin;
import org.simpl.resource.management.data.StrategyPluginList;
import org.simpl.resource.management.data.StringList;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ResourceManagement", targetNamespace = "http://management.resource.simpl.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ResourceManagement {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "addConnector")
    @WebResult(partName = "return")
    public boolean addConnector(
        @WebParam(name = "arg0", partName = "arg0")
        Connector arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "deleteDataConverter")
    @WebResult(partName = "return")
    public boolean deleteDataConverter(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "updateConnector")
    @WebResult(partName = "return")
    public boolean updateConnector(
        @WebParam(name = "arg0", partName = "arg0")
        Connector arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "deleteDataTransformationService")
    @WebResult(partName = "return")
    public boolean deleteDataTransformationService(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "addDataTransformationService")
    @WebResult(partName = "return")
    public boolean addDataTransformationService(
        @WebParam(name = "arg0", partName = "arg0")
        DataTransformationService arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "updateDataTransformationService")
    @WebResult(partName = "return")
    public boolean updateDataTransformationService(
        @WebParam(name = "arg0", partName = "arg0")
        DataTransformationService arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "createResourceManagementTables")
    @WebResult(partName = "return")
    public boolean createResourceManagementTables()
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "deleteConnector")
    @WebResult(partName = "return")
    public boolean deleteConnector(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "addDataConverter")
    @WebResult(partName = "return")
    public boolean addDataConverter(
        @WebParam(name = "arg0", partName = "arg0")
        DataConverter arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "updateDataConverter")
    @WebResult(partName = "return")
    public boolean updateDataConverter(
        @WebParam(name = "arg0", partName = "arg0")
        DataConverter arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "addDataSource")
    @WebResult(partName = "return")
    public boolean addDataSource(
        @WebParam(name = "arg0", partName = "arg0")
        DataSource arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "updateDataSource")
    @WebResult(partName = "return")
    public boolean updateDataSource(
        @WebParam(name = "arg0", partName = "arg0")
        DataSource arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "deleteDataSource")
    @WebResult(partName = "return")
    public boolean deleteDataSource(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns org.simpl.resource.management.client.DataSourceList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getAllDataSources")
    @WebResult(partName = "return")
    public DataSourceList getAllDataSources()
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns org.simpl.resource.management.client.StrategyPluginList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getAllStrategyPlugins")
    @WebResult(partName = "return")
    public StrategyPluginList getAllStrategyPlugins()
        throws Exception_Exception
    ;

    /**
     * 
     * @param type
     * @return
     *     returns org.simpl.resource.management.client.DataSourceList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getDataSourcesByType")
    @WebResult(partName = "return")
    public DataSourceList getDataSourcesByType(
        @WebParam(name = "type", partName = "type")
        String type)
        throws Exception_Exception
    ;

    /**
     * 
     * @param subType
     * @param type
     * @return
     *     returns org.simpl.resource.management.client.DataSourceList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getDataSourcesByTypeAndSubtype")
    @WebResult(partName = "return")
    public DataSourceList getDataSourcesByTypeAndSubtype(
        @WebParam(name = "type", partName = "type")
        String type,
        @WebParam(name = "subType", partName = "subType")
        String subType)
        throws Exception_Exception
    ;

    /**
     * 
     * @param name
     * @return
     *     returns org.simpl.resource.management.client.DataSource
     * @throws Exception_Exception
     */
    @WebMethod(action = "getDataSourcesByName")
    @WebResult(partName = "return")
    public DataSource getDataSourceByName(
        @WebParam(name = "name", partName = "name")
        String name)
        throws Exception_Exception
    ;

    /**
     * 
     * @param id
     * @return
     *     returns org.simpl.resource.management.client.DataSource
     * @throws Exception_Exception
     */
    @WebMethod(action = "getDataSourceById")
    @WebResult(partName = "return")
    public DataSource getDataSourceById(
        @WebParam(name = "id", partName = "id")
        int id)
        throws Exception_Exception
    ;

    /**
     * 
     * @param id
     * @return
     *     returns org.simpl.resource.management.client.Connector
     * @throws Exception_Exception
     */
    @WebMethod(action = "getConnectorById")
    @WebResult(partName = "return")
    public Connector getConnectorById(
        @WebParam(name = "id", partName = "id")
        int id)
        throws Exception_Exception
    ;

    /**
     * 
     * @param id
     * @return
     *     returns org.simpl.resource.management.client.DataConverter
     * @throws Exception_Exception
     */
    @WebMethod(action = "getDataConverterById")
    @WebResult(partName = "return")
    public DataConverter getDataConverterById(
        @WebParam(name = "id", partName = "id")
        int id)
        throws Exception_Exception
    ;

    /**
     * 
     * @param id
     * @return
     *     returns org.simpl.resource.management.client.DataTransformationService
     * @throws Exception_Exception
     */
    @WebMethod(action = "getDataTransformationServiceById")
    @WebResult(partName = "return")
    public DataTransformationService getDataTransformationServiceById(
        @WebParam(name = "id", partName = "id")
        int id)
        throws Exception_Exception
    ;

    /**
     * 
     * @param id
     * @return
     *     returns org.simpl.resource.management.client.StrategyPlugin
     * @throws Exception_Exception
     */
    @WebMethod(action = "getStrategyPluginById")
    @WebResult(partName = "return")
    public StrategyPlugin getStrategyPluginById(
        @WebParam(name = "id", partName = "id")
        int id)
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns org.simpl.resource.management.client.ConnectorList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getAllConnectors")
    @WebResult(partName = "return")
    public ConnectorList getAllConnectors()
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns org.simpl.resource.management.client.DataConverterList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getAllDataConverters")
    @WebResult(partName = "return")
    public DataConverterList getAllDataConverters()
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns org.simpl.resource.management.client.DataTransformationServiceList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getAllDataTransformationServices")
    @WebResult(partName = "return")
    public DataTransformationServiceList getAllDataTransformationServices()
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns org.simpl.resource.management.client.StringList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getAllLanguages")
    @WebResult(partName = "return")
    public StringList getAllLanguages()
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns org.simpl.resource.management.client.StringList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getDataSourceTypes")
    @WebResult(partName = "return")
    public StringList getDataSourceTypes()
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns org.simpl.resource.management.client.StringList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getDataSourceSubTypes")
    @WebResult(partName = "return")
    public StringList getDataSourceSubTypes(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns org.simpl.resource.management.client.StringList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getDataSourceLanguages")
    @WebResult(partName = "return")
    public StringList getDataSourceLanguages(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws Exception_Exception
     */
    @WebMethod(action = "getDataFormatSchema")
    @WebResult(partName = "return")
    public String getDataFormatSchema(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws Exception_Exception
     */
    @WebMethod(action = "getLanguageStatementDescription")
    @WebResult(partName = "return")
    public String getLanguageStatementDescription(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns org.simpl.resource.management.client.StringList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getSupportedDataFormats")
    @WebResult(partName = "return")
    public StringList getSupportedDataFormats(
        @WebParam(name = "arg0", partName = "arg0")
        DataSource arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns org.simpl.resource.management.client.StringList
     * @throws Exception_Exception
     */
    @WebMethod(action = "getSupportedConvertDataFormats")
    @WebResult(partName = "return")
    public StringList getSupportedConvertDataFormats(
        @WebParam(name = "arg0", partName = "arg0")
        DataSource arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "addStrategyPlugin")
    @WebResult(partName = "return")
    public boolean addStrategyPlugin(
        @WebParam(name = "arg0", partName = "arg0")
        StrategyPlugin arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "updateStrategyPlugin")
    @WebResult(partName = "return")
    public boolean updateStrategyPlugin(
        @WebParam(name = "arg0", partName = "arg0")
        StrategyPlugin arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod(action = "deleteStrategyPlugin")
    @WebResult(partName = "return")
    public boolean deleteStrategyPlugin(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0)
        throws Exception_Exception
    ;

}
