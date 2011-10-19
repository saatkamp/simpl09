package org.eclipse.bpel.simpl.ui.properties.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.bpel.ui.util.XSDUtils;
import org.eclipse.xsd.XSDSchema;
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
  private static final Object SIMPL_NAMESPACE = "http://www.example.org/simpl";
  public static final int PARAMETER_VAR = 0;
  public static final int CONTAINER_VAR = 1;
  public static final int DESCRIPTOR_VAR = 2;

  @SuppressWarnings("unchecked")
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
      // Query all variables with the simpl:DataContainerReferenceType
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
        
        if (targetNameSpace != null && schema.getTargetNamespace().equals(SIMPL_NAMESPACE)) {
          found = true;
        }
      }

      if (found) {
        XSDTypeDefinition contType = XSDUtils.getDataType(schema,
            "DataContainerReferenceType");
        // Query all variables with DataContainerReferenceType
        if (contType != null) {
          Variable[] vars = ModelHelper.getVariablesOfType(process, contType.getName());
          for (Variable var : vars) {
            variableNames.add("[" + var.getName() + "]");
          }
        }
      }
    }

    if (varType == DESCRIPTOR_VAR) {
      // Query all variables with the simpl:DataSourceReferenceType
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
            "DataSourceReferenceType");
        // Query all variables with DataSourceReferenceType
        if (contType != null) {
          Variable[] vars = ModelHelper.getVariablesOfType(process, contType.getName());
          for (Variable var : vars) {
            variableNames.add(var.getName());
          }
        }
      }
    }

    return variableNames;
  }

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

    // Query all variables with the simpl:DataContainerReferenceType
    // xmlns:simpl "http://www.example.org/simpl"
    List<XSDSchema> schemas = ModelHelper.getSchemas(process, false);
    Iterator<XSDSchema> iterator = schemas.iterator();
    XSDSchema schema = null;
    String targetNameSpace = null;
    boolean found = false;

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
      XSDTypeDefinition contType = XSDUtils.getDataType(schema, "DataContainerReferenceType");
      // Query all variables with DataContainerReferenceType
      Variable[] vars = ModelHelper.getVariablesOfType(process, contType.getName());
      for (Variable var : vars) {
        variableNames.add("[" + var.getName() + "]");
      }
    }

    return variableNames;
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

    // Query all variables with the simpl:DataSourceReferenceType
    // xmlns:simpl "http://www.example.org/simpl"
    List<XSDSchema> schemas = ModelHelper.getSchemas(process, false);
    Iterator<XSDSchema> iterator = schemas.iterator();
    XSDSchema schema = null;
    String targetNameSpace = null;
    boolean found = false;

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
          "DataSourceReferenceType");
      // Query all variables with DataSourceReferenceType
      Variable[] vars = ModelHelper.getVariablesOfType(process, contType.getName());
      for (Variable var : vars) {
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