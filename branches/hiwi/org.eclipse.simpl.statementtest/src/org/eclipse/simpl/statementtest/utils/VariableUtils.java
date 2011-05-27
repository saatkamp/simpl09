package org.eclipse.simpl.statementtest.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.bpel.ui.util.XSDUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDSchema;
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
  private static final Object SIMPL_NAMESPACE = "http://www.example.org/simpl";

  @SuppressWarnings("unchecked")
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

    // Query all variables with the simpl:ContainerReferenceType
    // xmlns:simpl "http://www.example.org/simpl"
    List<XSDSchema> schemas = ModelHelper.getSchemas(process, false);
    Iterator<XSDSchema> iterator = schemas.iterator();
    XSDSchema schema = null;
    boolean found = false;
    String targetNameSpace = null;
    
    while (iterator.hasNext() && !found) {
      schema = iterator.next();
      
      if (schema != null) {
        targetNameSpace = schema.getTargetNamespace();
      }
      
      if (targetNameSpace != null && targetNameSpace.equals(VariableUtils.SIMPL_NAMESPACE)) {
        found = true;
      }
    }

    if (found) {
      XSDTypeDefinition contType = XSDUtils.getDataType(schema, "ContainerReferenceType");
      // Query all variables with ContainerReferenceType
      Variable[] vars = ModelHelper.getVariablesOfType(process, contType.getName());
      for (Variable var : vars) {
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

  @SuppressWarnings("unchecked")
  public static List<String> getDescriptorVariablesFromProcess(Process process) {
    List<String> variableNames = new ArrayList<String>();

    // Query all variables with the simpl:LogicalDataSourceDescriptorType
    // xmlns:simpl "http://www.example.org/simpl"
    List<XSDSchema> schemas = ModelHelper.getSchemas(process, false);
    Iterator<XSDSchema> iterator = schemas.iterator();
    XSDSchema schema = null;
    boolean found = false;
    String targetNameSpace = null;
    
    while (iterator.hasNext() && !found) {
      schema = iterator.next();

      if (schema != null) {
        targetNameSpace = schema.getTargetNamespace();
      }

      if (targetNameSpace != null && targetNameSpace.equals(SIMPL_NAMESPACE)) {
        found = true;
      }
    }

    if (found) {
      XSDTypeDefinition contType = XSDUtils.getDataType(schema,
          "LogicalDataSourceDescriptorType");
      // Query all variables with LogicalDataSourceDescriptorType
      Variable[] vars = ModelHelper.getVariablesOfType(process, contType.getName());
      for (Variable var : vars) {
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
  @SuppressWarnings("unchecked")
  public static String getDescriptorElementValue(Process process, String variableName,
      String variableElement) {
    String value = null;

    // Query all variables with the simpl:LogicalDataSourceDescriptorType
    // xmlns:simpl "http://www.example.org/simpl"
    List<XSDSchema> schemas = ModelHelper.getSchemas(process, false);
    Iterator<XSDSchema> iterator = schemas.iterator();
    XSDSchema schema = null;
    boolean found = false;
    String targetNameSpace = null;

    while (iterator.hasNext() && !found) {
      schema = iterator.next();

      if (schema != null) {
        targetNameSpace = schema.getTargetNamespace();
      }

      if (targetNameSpace != null && targetNameSpace.equals(SIMPL_NAMESPACE)) {
        found = true;
      }
    }

    if (found) {
      XSDTypeDefinition contType = XSDUtils.getDataType(schema,
          "LogicalDataSourceDescriptorType");
      // Query all variables with LogicalDataSourceDescriptorType
      Variable[] vars = ModelHelper.getVariablesOfType(process, contType.getName());
      for (Variable var : vars) {
        if (var.getName().equals(variableName)) {
          if (var.getFrom() != null && var.getFrom().getLiteral() != null) {
            String literal = var.getFrom().getLiteral();
            Pattern pattern = Pattern.compile("\\<.*?" + variableElement
                + "\\>(.*?)</.*?" + variableElement + ">");
            Matcher matcher = pattern.matcher(literal);

            if (matcher.find()) {
              value = matcher.group(1);
            }
          }
        }
      }
    }

    return value;
  }
}