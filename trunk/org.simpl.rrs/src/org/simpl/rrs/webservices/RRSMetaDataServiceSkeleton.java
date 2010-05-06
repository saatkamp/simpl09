/**
 * RRSMetaDataServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
package org.simpl.rrs.webservices;

import org.simpl.rrs.RRS;

/**
 * RRSMetaDataServiceSkeleton java skeleton for the axisService
 */
public class RRSMetaDataServiceSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param getAvailableAdapters
	 */

	public org.simpl.rrs.webservices.GetAvailableAdaptersResponse getAvailableAdapters(
			org.simpl.rrs.webservices.GetAvailableAdapters getAvailableAdapters) {
		GetAvailableAdaptersResponse response = new GetAvailableAdaptersResponse();
		StringArray array = new StringArray();
		
		String[] result  = RRS.getInstance().metadataService().getAvailableAdapters();
		
		array.setItem(result);
		response.set_return(array);

		return response;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getAllEPR
	 */

	public org.simpl.rrs.webservices.GetAllEPRResponse getAllEPR(
			org.simpl.rrs.webservices.GetAllEPR getAllEPR) {
		GetAllEPRResponse response = new GetAllEPRResponse();
		
		EPRArray array = RRS.getInstance().metadataService().getAllEPR();

		response.set_return(array);
		return response;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getEPR
	 */

	public org.simpl.rrs.webservices.GetEPRResponse getEPR(
			org.simpl.rrs.webservices.GetEPR getEPR) {
		GetEPRResponse response = new GetEPRResponse();
		
		EPR epr = RRS.getInstance().metadataService().getEPR(getEPR.getEprName());

		response.set_return(epr);
		return response;
	}

}
