
package org.eclipse.simpl.rrs.ui.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import net.java.dev.jaxb.array.StringArray;
import de.uni_stuttgart.simpl.rrs.EPR;
import de.uni_stuttgart.simpl.rrs.EPRArray;
import de.uni_stuttgart.simpl.rrs.ObjectFactory;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "RRSMetaDataService", targetNamespace = "http://webservices.rrs.simpl.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class,
    net.java.dev.jaxb.array.ObjectFactory.class
})
public interface RRSMetaDataService {


    /**
     * 
     * @param eprName
     * @return
     *     returns de.uni_stuttgart.simpl.rrs.EPR
     */
    @WebMethod(action = "getEPR")
    @WebResult(partName = "return")
    public EPR getEPR(
        @WebParam(name = "eprName", partName = "eprName")
        String eprName);

    /**
     * 
     * @return
     *     returns de.uni_stuttgart.simpl.rrs.EPRArray
     */
    @WebMethod(action = "getAllEPR")
    @WebResult(partName = "return")
    public EPRArray getAllEPR();

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod(action = "getAvailableAdapters")
    @WebResult(partName = "return")
    public StringArray getAvailableAdapters();
}
