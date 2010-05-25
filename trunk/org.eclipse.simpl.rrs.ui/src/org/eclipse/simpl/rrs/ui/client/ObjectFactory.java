
package org.eclipse.simpl.rrs.ui.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.eclipse.simpl.rrs.ui.client package. 
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

    private final static QName _RdbData_QNAME = new QName("http://webservices.rrs.simpl.org/", "rdbData");
    private final static QName _Epr_QNAME = new QName("http://webservices.rrs.simpl.org/", "epr");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.eclipse.simpl.rrs.ui.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceName }
     * 
     */
    public ServiceName createServiceName() {
        return new ServiceName();
    }

    /**
     * Create an instance of {@link StringArray }
     * 
     */
    public StringArray createStringArray() {
        return new StringArray();
    }

    /**
     * Create an instance of {@link ReferenceParameters }
     * 
     */
    public ReferenceParameters createReferenceParameters() {
        return new ReferenceParameters();
    }

    /**
     * Create an instance of {@link EPRArray }
     * 
     */
    public EPRArray createEPRArray() {
        return new EPRArray();
    }

    /**
     * Create an instance of {@link Table }
     * 
     */
    public Table createTable() {
        return new Table();
    }

    /**
     * Create an instance of {@link Column }
     * 
     */
    public Column createColumn() {
        return new Column();
    }

    /**
     * Create an instance of {@link ReferenceProperties }
     * 
     */
    public ReferenceProperties createReferenceProperties() {
        return new ReferenceProperties();
    }

    /**
     * Create an instance of {@link EPR }
     * 
     */
    public EPR createEPR() {
        return new EPR();
    }

    /**
     * Create an instance of {@link RDBSet }
     * 
     */
    public RDBSet createRDBSet() {
        return new RDBSet();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RDBSet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.rrs.simpl.org/", name = "rdbData")
    public JAXBElement<RDBSet> createRdbData(RDBSet value) {
        return new JAXBElement<RDBSet>(_RdbData_QNAME, RDBSet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EPR }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.rrs.simpl.org/", name = "epr")
    public JAXBElement<EPR> createEpr(EPR value) {
        return new JAXBElement<EPR>(_Epr_QNAME, EPR.class, null, value);
    }

}
