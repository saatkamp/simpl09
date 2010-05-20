package org.eclipse.bpel.simpl.ui.properties.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.bpel.ui.util.XSDUtils;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTypeDefinition;

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
public class VariableUtils {

	private static final Object SIMPL_NAMESPACE = "http://www.example.org/simpl";

	public static List<String> getUseableVariables(Process process){
		List<XSDTypeDefinition> primitives = XSDUtils.getAdvancedPrimitives();
		List<String> variableNames = new ArrayList<String>();
		
		//Query all variables with a primitive type
		for (XSDTypeDefinition type : primitives){
			Variable[] vars = ModelHelper.getVariablesOfType(process, type.getName());
			for (Variable var : vars){
				variableNames.add("#"+ var.getName() +"#");
			}
		}
		
		//Query all variables with the simpl:ContainerReferenceType
		//xmlns:simpl "http://www.example.org/simpl"
		List<XSDSchema> schemas = ModelHelper.getSchemas(process, false);
		Iterator<XSDSchema> iterator = schemas.iterator();
		XSDSchema schema = null;
		boolean found = false;
		
		while (iterator.hasNext() && !found){
			schema = iterator.next();
			if (schema.getTargetNamespace().equals(SIMPL_NAMESPACE)){
				found = true;
			}
		}
		
		if (found){
			XSDTypeDefinition contType = XSDUtils.getDataType(schema, "ContainerReferenceType");
			//Query all variables with ContainerReferenceType
			Variable[] vars = ModelHelper.getVariablesOfType(process, contType.getName());
			for (Variable var : vars){
				variableNames.add("["+ var.getName() +"]");
			}
		}
		
		return variableNames;
	}
}
