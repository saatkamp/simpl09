package org.simpl.rrs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EPR")
@XmlType(name = "EPR", propOrder = { "rrsAddress", "properties", "parameters", "portType",
    "service", "rrsPolicy" })
public class EPR {

	private String rrsAddress = null;
	
	private ReferenceProperties properties = null;
	
	private ReferenceParameters parameters = null;
	
	private String portType = null;
	
	private ServiceName service = null;
	
	private String rrsPolicy = null;

	public String getRrsAddress() {
		return rrsAddress;
	}

	public void setRrsAddress(String rrsAddress) {
		this.rrsAddress = rrsAddress;
	}

	public ReferenceProperties getProperties() {
		return properties;
	}

	public void setProperties(ReferenceProperties properties) {
		this.properties = properties;
	}

	public ReferenceParameters getParameters() {
		return parameters;
	}

	public void setParameters(ReferenceParameters parameters) {
		this.parameters = parameters;
	}

	public String getPortType() {
		return portType;
	}

	public void setPortType(String portType) {
		this.portType = portType;
	}

	public ServiceName getService() {
		return service;
	}

	public void setService(ServiceName service) {
		this.service = service;
	}

	public String getRrsPolicy() {
		return rrsPolicy;
	}

	public void setRrsPolicy(String rrsPolicy) {
		this.rrsPolicy = rrsPolicy;
	}

}