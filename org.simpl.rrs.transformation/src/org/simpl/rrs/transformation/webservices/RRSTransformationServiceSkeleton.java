
/**
 * RRSTransformationServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
package org.simpl.rrs.transformation.webservices;

import org.simpl.rrs.transformation.Transformer;

/**
 * RRSTransformationServiceSkeleton java skeleton for the axisService
 */
public class RRSTransformationServiceSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param transform
	 */

	public org.simpl.rrs.transformation.webservices.TransformResponse transform(
			org.simpl.rrs.transformation.webservices.Transform transform) {
		TransformResponse response = new TransformResponse();

		response.set_return(Transformer.getTransformer().transform(
				transform.getBpelFileContent(), transform.getRrsWSDLNsURI()));

		return response;
	}

}
