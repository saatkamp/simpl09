
package org.simpl.core.webservices.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import org.simpl.resource.management.client.Authentication;
import org.simpl.resource.management.client.Connector;
import org.simpl.resource.management.client.DataFormat;
import org.simpl.resource.management.client.DataSource;
import org.simpl.resource.management.client.LateBinding;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.simpl.core.webservices.client package. 
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

    private final static QName _Exception_QNAME = new QName("http://webservices.core.simpl.org/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.simpl.core.webservices.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
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
     * Create an instance of {@link DataSource }
     * 
     */
    public DataSource createDataSource() {
        return new DataSource();
    }

    /**
     * Create an instance of {@link Connector }
     * 
     */
    public Connector createConnector() {
        return new Connector();
    }

    /**
     * Create an instance of {@link Authentication }
     * 
     */
    public Authentication createAuthentication() {
        return new Authentication();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.core.simpl.org/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
