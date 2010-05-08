
package org.simpl.core.webservices.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


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

    private final static QName _ConnectionException_QNAME = new QName("http://webservices.core.simpl.org/", "ConnectionException");
    private final static QName _DataSource_QNAME = new QName("http://webservices.core.simpl.org/", "DataSource");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.simpl.core.webservices.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DataSource }
     * 
     */
    public DataSource createDataSource() {
        return new DataSource();
    }

    /**
     * Create an instance of {@link ConnectionException }
     * 
     */
    public ConnectionException createConnectionException() {
        return new ConnectionException();
    }

    /**
     * Create an instance of {@link Authentication }
     * 
     */
    public Authentication createAuthentication() {
        return new Authentication();
    }

    /**
     * Create an instance of {@link LateBinding }
     * 
     */
    public LateBinding createLateBinding() {
        return new LateBinding();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectionException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.core.simpl.org/", name = "ConnectionException")
    public JAXBElement<ConnectionException> createConnectionException(ConnectionException value) {
        return new JAXBElement<ConnectionException>(_ConnectionException_QNAME, ConnectionException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.core.simpl.org/", name = "DataSource")
    public JAXBElement<DataSource> createDataSource(DataSource value) {
        return new JAXBElement<DataSource>(_DataSource_QNAME, DataSource.class, null, value);
    }

}
