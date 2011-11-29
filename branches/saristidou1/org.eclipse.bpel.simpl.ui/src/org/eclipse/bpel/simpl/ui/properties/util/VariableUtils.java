package org.eclipse.bpel.simpl.ui.properties.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.bpel.model.ContainerReferenceVariable;
import org.eclipse.bpel.model.ContainerReferenceVariables;
import org.eclipse.bpel.model.DataSourceReferenceVariable;
import org.eclipse.bpel.model.DataSourceReferenceVariables;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.bpel.ui.util.XSDUtils;
import org.eclipse.xsd.XSDTypeDefinition;

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
public class VariableUtils {
  public static final int PARAMETER_VAR = 0;
  public static final int CONTAINER_VAR = 1;
  public static final int DESCRIPTOR_VAR = 2;

  public static List<String> getUseableVariables(Process process, int varType) {
    List<XSDTypeDefinition> primitives = XSDUtils.getAdvancedPrimitives();
    List<String> variableNames = new ArrayList<String>();

    if (varType == PARAMETER_VAR) {
      // Query all variables with a primitive type
      for (XSDTypeDefinition type : primitives) {
        Variable[] vars = ModelHelper.getVariablesOfType(process, type.getName());
        for (Variable var : vars) {
          variableNames.add("#" + var.getName() + "#");
        }
      }
    }

    if (varType == CONTAINER_VAR) {
      ContainerReferenceVariables vars = ModelHelper.getContainerReferenceVariables(process);
      
      if (vars != null) {
        for (ContainerReferenceVariable var : vars.getChildren()) {
          variableNames.add("[" + var.getName() + "]");
        }
      }
    }

    if (varType == DESCRIPTOR_VAR) {
      DataSourceReferenceVariables vars = ModelHelper.getDataSourceReferenceVariables(process);

      if (vars != null) {
        for (DataSourceReferenceVariable var : vars.getChildren()) {
          variableNames.add(var.getName());
        }
      }
    }

    return variableNames;
  }

  public static List<String> getUseableVariables(Process process) {
    List<XSDTypeDefinition> primitives = XSDUtils.getAdvancedPrimitives();
    List<String> variableNames = new ArrayList<String>();

    // Query all variables with a primitive type
    for (XSDTypeDefinition type : primitives) {
      Variable[] vars = ModelHelper.getVariablesOfType(process, type.getName());
      for (Variable var : vars) {
        variableNames.add("#" + var.getName() + "#");
      }
    }

    ContainerReferenceVariables vars = ModelHelper.getContainerReferenceVariables(process);
    
    if (vars != null) {
      for (ContainerReferenceVariable var : vars.getChildren()) {
        variableNames.add("[" + var.getName() + "]");
      }
    }

    return variableNames;
  }

  /**
   * Returns the value of a data source reference variable element.
   * 
   * @param process
   * @param variableName
   * @param variableElement
   * @return
   */
  public static String getDataSourceReferenceElementValue(Process process, String variableName,
      String variableElement) {
    String value = null;

    DataSourceReferenceVariables vars = ModelHelper.getDataSourceReferenceVariables(process);

    if (vars != null) {
      for (DataSourceReferenceVariable var : vars.getChildren()) {
        if (var.getName().equals(variableName) && var.getFrom() != null) {
          String literal = var.getFrom().getLiteral();
          Pattern pattern = Pattern.compile("\\<.*?" + variableElement + "\\>(.*?)</.*?"
              + variableElement + ">");
          Matcher matcher = pattern.matcher(literal);

          if (matcher.find()) {
            value = matcher.group(1);
          }
        }
      }
    }

    return value;
  }
}