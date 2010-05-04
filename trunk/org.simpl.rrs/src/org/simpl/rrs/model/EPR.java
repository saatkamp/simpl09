package org.simpl.rrs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EPR")
@XmlType(name = "EPR", propOrder = { "rrsAddress", "referenceProperties", "referenceParameters", "portType",
    "serviceName", "rrsPolicy" })
public class EPR {

	private String rrsAddress = null;
	
	private ReferenceProperties referenceProperties = null;
	
	private ReferenceParameters referenceParameters = null;
	
	private String portType = null;
	
	private ServiceName serviceName = null;
	
	private String rrsPolicy = null;

	public String getRrsAddress() {
		return rrsAddress;
	}

	public void setRrsAddress(String rrsAddress) {
		this.rrsAddress = rrsAddress;
	}

	public ReferenceProperties getProperties() {
		return referenceProperties;
	}

	public void setProperties(ReferenceProperties properties) {
		this.referenceProperties = properties;
	}

	public ReferenceParameters getParameters() {
		return referenceParameters;
	}

	public void setParameters(ReferenceParameters parameters) {
		this.referenceParameters = parameters;
	}

	public String getPortType() {
		return portType;
	}

	public void setPortType(String portType) {
		this.portType = portType;
	}

	public ServiceName getService() {
		return serviceName;
	}

	public void setService(ServiceName service) {
		this.serviceName = service;
	}

	public String getRrsPolicy() {
		return rrsPolicy;
	}

	public void setRrsPolicy(String rrsPolicy) {
		this.rrsPolicy = rrsPolicy;
	}

}