/**
 * RRSManagementServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
package org.simpl.rrs.webservices;

import org.simpl.rrs.RRS;

/**
 * RRSManagementServiceSkeleton java skeleton for the axisService
 */
public class RRSManagementServiceSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param update
	 */

	public org.simpl.rrs.webservices.UpdateResponse update(
			org.simpl.rrs.webservices.Update update) {
		// TODO : fill this with the necessary business logic
		boolean success = RRS.getInstance().managementService().Update(update.getEpr());
		UpdateResponse response = new UpdateResponse();
		response.set_return(success);
		return response;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param delete
	 */

	public org.simpl.rrs.webservices.DeleteResponse delete(
			org.simpl.rrs.webservices.Delete delete) {
		boolean success = RRS.getInstance().managementService().Delete(delete.getEpr());
		DeleteResponse response = new DeleteResponse();
		response.set_return(success);
		return response;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param insert
	 */

	public org.simpl.rrs.webservices.InsertResponse insert(
			org.simpl.rrs.webservices.Insert insert) {
		boolean success = RRS.getInstance().managementService().Insert(insert.getEpr());
		InsertResponse response = new InsertResponse();
		response.set_return(success);
		return response;
	}

}
