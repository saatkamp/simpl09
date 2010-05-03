package org.simpl.rrs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EPR")
@XmlType(name = "EPR")
public class EPR {

	String resolutionSystem = null;
	String adapterType = null;
	String statement = null;
	String address = null;
	String referenceName = null;
	String portType = null;
	String portName = null;
	String rrsPolicy = null;

	public String getResolutionSystem() {
		return resolutionSystem;
	}

	public void setResolutionSystem(String resolutionSystem) {
		this.resolutionSystem = resolutionSystem;
	}

	public String getAdapterType() {
		return adapterType;
	}

	public void setAdapterType(String adapterType) {
		this.adapterType = adapterType;
	}

	public String getPortType() {
		return portType;
	}

	public void setPortType(String portType) {
		this.portType = portType;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getRrsPolicy() {
		return rrsPolicy;
	}

	public void setRrsPolicy(String rrsPolicy) {
		this.rrsPolicy = rrsPolicy;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getReferenceName() {
		return referenceName;
	}
	
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
}
