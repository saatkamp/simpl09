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
package org.eclipse.bpel.simpl.model.util;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.adapters.INamespaceMap;
import org.eclipse.bpel.model.util.BPELUtils;
import org.eclipse.bpel.simpl.model.ModelPackage;

// TODO: Auto-generated Javadoc
/**
 * The Class DataManagementUtils.
 */
public class DataManagementUtils {

	/**
	 * Adds the simpl namespace to the given process.
	 * 
	 * @param process
	 *            the process
	 * @return the preferred namespace prefix
	 */
	public static String addNamespace(Process process) {
		String nsPrefix = ModelPackage.eINSTANCE.getNsPrefix();
		String nsURI = ModelPackage.eINSTANCE.getNsURI();
		INamespaceMap<String, String> nsMap = BPELUtils
				.getNamespaceMap(process);
		nsMap.put(nsPrefix, nsURI);
		return nsPrefix;
	}

}
