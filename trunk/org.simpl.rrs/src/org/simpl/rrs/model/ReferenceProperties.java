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
@XmlType(name = "ReferenceProperties", propOrder = { "rrsAdapter"})
public class ReferenceProperties {

	private String rrsAdapter = null;

	public String getRrsAdapter() {
		return rrsAdapter;
	}

	public void setRrsAdapter(String rrsAdapter) {
		this.rrsAdapter = rrsAdapter;
	}
}
