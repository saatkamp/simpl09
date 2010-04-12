package org.simpl.rrs.transformation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpel.model.resource.BPELResourceFactoryImpl;
import org.eclipse.bpel.model.resource.BPELResourceImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

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
public class Transformer {

	private static final String PATH = "";
	
	public static Transformer transformer = null;
	
	public static Transformer getTransformer(){
		if (transformer == null) {
			transformer = new Transformer();
		}
		return transformer;
	}
	
	public String transform(String input){ 
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		
		BPELResourceFactoryImpl factory = new BPELResourceFactoryImpl();
		BPELResourceImpl resource = (BPELResourceImpl) factory.createResource(URI.createURI(PATH));
		
		try {
			resource.doLoad(inputStream, Collections.EMPTY_MAP);
			return transformBPEL(resource, inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String transformBPEL(BPELResourceImpl resource, InputStream inpStream){
		
		//Transformieren
		
		//
		
		
		Map<String, String> save_options = new HashMap<String, String>();
		save_options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
				Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			resource.doSave(outStream, save_options);
			return new String(outStream.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
