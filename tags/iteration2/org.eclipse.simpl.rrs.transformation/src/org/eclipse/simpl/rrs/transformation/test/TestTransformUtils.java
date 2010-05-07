package org.eclipse.simpl.rrs.transformation.test;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.simpl.rrs.transformation.TransformerUtil;

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
public class TestTransformUtils {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TransformerUtil.downloadWSDL("http://localhost:8080/ode/processes/TransformationServiceService.TransformationServicePort?wsdl", System.getProperty("user.dir"), "transform.wsdl");
	}

}
