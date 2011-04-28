
package org.simpl.resource.discovery.client;

import javax.xml.bind.annotation.XmlRegistry;

import org.simpl.resource.management.data.Authentication;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataFormat;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.LateBinding;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.simpl.resource.discovery.client package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.simpl.resource.discovery.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Connector }
     * 
     */
    public Connector createConnector() {
        return new Connector();
    }

    /**
     * Create an instance of {@link DataSource }
     * 
     */
    public DataSource createDataSource() {
        return new DataSource();
    }

    /**
     * Create an instance of {@link LateBinding }
     * 
     */
    public LateBinding createLateBinding() {
        return new LateBinding();
    }

    /**
     * Create an instance of {@link DataFormat }
     * 
     */
    public DataFormat createDataFormat() {
        return new DataFormat();
    }

    /**
     * Create an instance of {@link Authentication }
     * 
     */
    public Authentication createAuthentication() {
        return new Authentication();
    }

}
