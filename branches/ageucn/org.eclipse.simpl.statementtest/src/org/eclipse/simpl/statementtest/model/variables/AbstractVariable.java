package org.eclipse.simpl.statementtest.model.variables;

import org.eclipse.bpel.model.Variable;

/**
 * <b>Purpose:</b>Base model variable where other models variables extend from.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: AbstractVariable.java 51 2010-06-29 18:21:35Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class AbstractVariable {
  Variable bpelVariable = null;

  String type = null;
  String name = null;
  String value = null;

  AbstractVariable(Variable variable) {
    this.bpelVariable = variable;
    this.name = variable.getName();

    if (variable.getType() != null) {
      this.type = variable.getType().getName();
    }

    if (variable.getFrom() != null) {
      this.value = variable.getFrom().getLiteral();
    }
  }

  /**
   * @return the variable
   */
  public Variable getBPELVariable() {
    return bpelVariable;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value
   *          the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }
}