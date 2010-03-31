
package org.simpl.core.webservices.client;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@SuppressWarnings("serial")
@WebFault(name = "ConnectionException", targetNamespace = "http://webservices.core.simpl.org/")
public class ConnectionException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ConnectionException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public ConnectionException_Exception(String message, ConnectionException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public ConnectionException_Exception(String message, ConnectionException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.simpl.core.webservices.client.ConnectionException
     */
    public ConnectionException getFaultInfo() {
        return faultInfo;
    }

}