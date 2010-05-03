package org.simpl.rrs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceParameters", propOrder = { "referenceName", "statement", "dsAddress"})
public class ReferenceParameters {
	
	private String referenceName = null;
	private String statement = null;
	private String dsAddress = null;
	
	public String getReferenceName() {
		return referenceName;
	}
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getDsAddress() {
		return dsAddress;
	}
	public void setDsAddress(String dsAddress) {
		this.dsAddress = dsAddress;
	}
}
