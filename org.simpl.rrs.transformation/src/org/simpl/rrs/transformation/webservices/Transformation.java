package org.simpl.rrs.transformation.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.rrs.transformation.Transformer;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
@WebService(name = "TransformationService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Transformation {
	@WebMethod(action = "transform")
	public String transform(
			@WebParam(name = "bpelFileContent", targetNamespace = "") String bpelFileContent, 
			@WebParam(name = "rrsWSDLNamespaceURI", targetNamespace = "") String rrsWSDLNamespaceURI) {
		String response = Transformer.getTransformer().transform(bpelFileContent, rrsWSDLNamespaceURI);

		return response;
	}
}
