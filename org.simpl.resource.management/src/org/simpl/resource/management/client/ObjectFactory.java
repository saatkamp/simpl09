
package org.simpl.resource.management.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import org.simpl.resource.management.data.Authentication;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.ConnectorList;
import org.simpl.resource.management.data.DataContainer;
import org.simpl.resource.management.data.DataContainerList;
import org.simpl.resource.management.data.DataConverter;
import org.simpl.resource.management.data.DataConverterList;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.DataSourceList;
import org.simpl.resource.management.data.DataTransformationService;
import org.simpl.resource.management.data.DataTransformationServiceList;
import org.simpl.resource.management.data.StrategyPlugin;
import org.simpl.resource.management.data.StrategyPluginList;
import org.simpl.resource.management.data.StringList;
import org.simpl.resource.management.data.TypeDefinition;
import org.simpl.resource.management.data.TypeDefinitionList;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.simpl.resource.management.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Exception_QNAME = new QName("http://management.resource.simpl.org/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.simpl.resource.management.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StrategyPluginList }
     * 
     */
    public StrategyPluginList createStrategyPluginList() {
        return new StrategyPluginList();
    }

    /**
     * Create an instance of {@link DataTransformationServiceList }
     * 
     */
    public DataTransformationServiceList createDataTransformationServiceList() {
        return new DataTransformationServiceList();
    }

    /**
     * Create an instance of {@link TypeDefinition }
     * 
     */
    public TypeDefinition createTypeDefinition() {
        return new TypeDefinition();
    }

    /**
     * Create an instance of {@link DataContainer }
     * 
     */
    public DataContainer createDataContainer() {
        return new DataContainer();
    }

    /**
     * Create an instance of {@link DataSourceList }
     * 
     */
    public DataSourceList createDataSourceList() {
        return new DataSourceList();
    }

    /**
     * Create an instance of {@link DataTransformationService }
     * 
     */
    public DataTransformationService createDataTransformationService() {
        return new DataTransformationService();
    }

    /**
     * Create an instance of {@link Authentication }
     * 
     */
    public Authentication createAuthentication() {
        return new Authentication();
    }

    /**
     * Create an instance of {@link DataContainerList }
     * 
     */
    public DataContainerList createDataContainerList() {
        return new DataContainerList();
    }

    /**
     * Create an instance of {@link ConnectorList }
     * 
     */
    public ConnectorList createConnectorList() {
        return new ConnectorList();
    }

    /**
     * Create an instance of {@link StringList }
     * 
     */
    public StringList createStringList() {
        return new StringList();
    }

    /**
     * Create an instance of {@link DataSource }
     * 
     */
    public DataSource createDataSource() {
        return new DataSource();
    }

    /**
     * Create an instance of {@link TypeDefinitionList }
     * 
     */
    public TypeDefinitionList createTypeDefinitionList() {
        return new TypeDefinitionList();
    }

    /**
     * Create an instance of {@link StrategyPlugin }
     * 
     */
    public StrategyPlugin createStrategyPlugin() {
        return new StrategyPlugin();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link Connector }
     * 
     */
    public Connector createConnector() {
        return new Connector();
    }

    /**
     * Create an instance of {@link DataConverterList }
     * 
     */
    public DataConverterList createDataConverterList() {
        return new DataConverterList();
    }

    /**
     * Create an instance of {@link DataConverter }
     * 
     */
    public DataConverter createDataConverter() {
        return new DataConverter();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://management.resource.simpl.org/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
