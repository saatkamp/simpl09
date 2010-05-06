/**
 * RRSRetrievalServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
package org.simpl.rrs.webservices;

import org.simpl.rrs.RRS;
import org.simpl.rrs.webservices.helper.Parameter;

/**
 * RRSRetrievalServiceSkeleton java skeleton for the axisService
 */
public class RRSRetrievalServiceSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param get
	 */

	public org.simpl.rrs.webservices.GetResponse get(
			org.simpl.rrs.webservices.Get get) {
		GetResponse response = new GetResponse();
		
		Object obj = RRS.getInstance().retrievalService().get(get.getEPR());
		response.set_return(Parameter.serialize(obj));
		
		return response; 
	}

}
