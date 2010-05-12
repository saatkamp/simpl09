/**
 * RRSRetrievalServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
package org.simpl.rrs.webservices;

import java.sql.ResultSet;

import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import org.simpl.rrs.RRS;
import org.simpl.rrs.webservices.helper.JDBCUtil;

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

		ResultSet res = RRS.getInstance().retrievalService().get(get.getEPR());
		Document doc = JDBCUtil.toDocument(res);
		XMLOutputter out = new XMLOutputter();
		Object obj = out.outputString(doc);
		response.set_return(obj);

		return response;
	}

}
