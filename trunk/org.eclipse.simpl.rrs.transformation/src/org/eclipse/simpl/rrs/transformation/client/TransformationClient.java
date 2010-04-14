package org.eclipse.simpl.rrs.transformation.client;

import java.net.URL;
import java.net.URLConnection;

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
public class TransformationClient {

	private static TransformationClient client = null;
	
	public static TransformationClient getClient(){
		if (client == null){
			client = new TransformationClient();
		}
		return client;
	}
	
	TransformationService transformationService = new TransformationService_Service().getTransformationServicePort();
	
	public String transform (String bpelFileContent){
		String response = "";
		
		response = transformationService.transform(bpelFileContent);
		
		return response;
	}
	
	public boolean isTransformerAvailable() {
		boolean isAvailable = false;
		URL url;
		try {
			url = new URL(
					"http://localhost:8080/ode/processes/TransformationService.TransformationServicePort?wsdl");
			URLConnection connection = url.openConnection();
			connection.connect();
			isAvailable = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAvailable;
	}
}
