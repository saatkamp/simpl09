package org.eclipse.simpl.rrs.ui.client;

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.simpl.rrs.model.rrs.EPR;
import org.eclipse.simpl.rrs.ui.RRSUIPlugIn;



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
public class RRSClient {

	private static RRSClient client = null;
	
	private final String RRS_MG_WSDL = RRSUIPlugIn.getDefault().getPreferenceStore().getString("RRS_MG_ADDRESS");
	private final String RRS_MD_WSDL = RRSUIPlugIn.getDefault().getPreferenceStore().getString("RRS_MD_ADDRESS");
	
	public static RRSClient getClient(){
		if (client == null){
			client = new RRSClient();
		}
		return client;
	}
	
	//TODO: Hier müssen noch alle Clientmethoden bereitgestellt werden (siehe SIMPL Communication).
//	private String executeTransformation (String bpelFileContent){
//		TransformationService transformationService = new TransformationService_Service().getTransformationServicePort();
//		
//		String response = "";
//		
//		response = transformationService.transform(bpelFileContent);
//		
//		return response;
//	}
	
	//RRS Management Methods
	public EPR insertEPR(HashMap<String, String> epr){
		return null;
	}
	
	public EPR updateEPR(HashMap<String, String> epr){
		return null;
	}
	
	public boolean deleteEPR(HashMap<String, String> epr){
		return false;
	}
	
	//RRS Meta-Data methods
	public List<EPR> getAllEPRs(){
		return null;
	}
	
	public List<String> getAvailableRRSAdapters(){
		return null;
	}
	
	public boolean isRRSAvailable() {
		boolean isAvailable = false;
		URL url;
		try {
			url = new URL(RRS_MG_WSDL);
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
