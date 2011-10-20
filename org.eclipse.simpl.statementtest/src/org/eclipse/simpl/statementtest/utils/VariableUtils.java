package org.eclipse.simpl.statementtest.utils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.bpel.model.ContainerVariable;
import org.eclipse.bpel.model.ContainerVariables;
import org.eclipse.bpel.model.DescriptorVariable;
import org.eclipse.bpel.model.DescriptorVariables;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.bpel.ui.util.XSDUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDTypeDefinition;

/**
 * <b>Purpose:</b>Util functions to access and handle variables from the process.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de> <br>
 * @version $Id: VariableUtils.java 51 2010-06-29 18:21:35Z hiwi $ <br>
 * @link http://code.google.com/p/simpl09/
 */
public class VariableUtils {
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

    ContainerVariables vars = ModelHelper.getContainerVariables(process);
    
    if (vars != null) {
      for (ContainerVariable var : vars.getChildren()) {
        variableNames.add("[" + var.getName() + "]");
      }
    }

    return variableNames;
  }

  /**
   * @param statement
   * @return A list with statement parameter variable names.
   */
  public static List<String> getParameterVariablesFromStatement(String statement,
      Process process) {
    ArrayList<String> parameterVariables = new ArrayList<String>();
    Pattern parameterPattern = Pattern.compile("#(.*?)#");
    Matcher matcher = parameterPattern.matcher(statement);
    List<String> useableVariables = VariableUtils.getUseableVariables(process);

    while (matcher.find()) {
      // the variable may not be able to use or may not exist
      if (useableVariables.contains(matcher.group(0))
          && VariableUtils.getVariableByName(matcher.group(1), process) != null) {
        parameterVariables.add(matcher.group(1));
      }
    }

    return parameterVariables;
  }

  /**
   * @param statement
   * @return A list with statement container variable names.
   */
  public static List<String> getContainerVariablesFromStatement(String statement,
      Process process) {
    ArrayList<String> containerVariables = new ArrayList<String>();
    Pattern parameterPattern = Pattern.compile("\\[(.*?)\\]");
    Matcher matcher = parameterPattern.matcher(statement);
    List<String> useableVariables = VariableUtils.getUseableVariables(process);

    while (matcher.find()) {
      // the variable may not be able to use or may not exist
      if (useableVariables.contains(matcher.group(0))
          && VariableUtils.getVariableByName(matcher.group(1), process) != null) {
        containerVariables.add(matcher.group(1));
      }
    }

    // remove duplicates
    containerVariables = new ArrayList<String>(new LinkedHashSet<String>(
        containerVariables));

    return containerVariables;
  }

  public static List<String> getDescriptorVariablesFromProcess(Process process) {
    List<String> variableNames = new ArrayList<String>();

    DescriptorVariables vars = ModelHelper.getDescriptorVariables(process);

    if (vars != null) {
      for (DescriptorVariable var : vars.getChildren()) {
        variableNames.add(var.getName());
      }
    }

    return variableNames;
  }

  /**
   * Returns a variable from the process by name.
   * 
   * @param name
   * @return
   */
  public static Variable getVariableByName(String name, Process process) {
    Variable variable = null;
    EList<EObject> variables = null;

    // get all BPEL variables
    variables = process.getVariables().eContents();

    // find the variable by name
    for (EObject eObject : variables) {
      Variable var = ((Variable) eObject);

      if (var.getName().equals(name)) {
        variable = var;
        break;
      }
    }

    return variable;
  }

  /**
   * Returns the value of a descriptor variable element.
   * 
   * @param process
   * @param variableName
   * @param variableElement
   * @return
   */
  public static String getDescriptorElementValue(Process process, String variableName,
      String variableElement) {
    String value = null;

    DescriptorVariables vars = ModelHelper.getDescriptorVariables(process);

    if (vars != null) {
      for (DescriptorVariable var : vars.getChildren()) {
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