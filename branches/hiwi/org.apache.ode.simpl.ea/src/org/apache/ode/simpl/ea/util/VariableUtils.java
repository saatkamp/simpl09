package org.apache.ode.simpl.ea.util;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
  /**
   * Returns the value of a descriptor variable element.
   * 
   * @param context
   * @param variableName
   * @param variableElement
   * @return
   */
  public static String getLDSDValue(ExtensionContext context,
      String variableName, String variableElement) {
    String value = null;

    try {
      Node varContent = context.readVariable(variableName);      
      NodeList nodes = varContent.getChildNodes();

      for (int i = 0; nodes.getLength() > 0; i++) {
        Node current = nodes.item(i);

        if (current != null) {
          String name = current.getLocalName();
          
          if (name != null && name.equals(variableElement)) {
            value = current.getTextContent();
            break;
          }
        }
      }
    } catch (FaultException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.out.println("Resolved '" + variableElement + "' from LDSD variable '" + variableName + "': " + value);
    
    return value;
  }
}