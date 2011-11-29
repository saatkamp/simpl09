package org.eclipse.simpl.statementtest.model.variables;

/**
 * <b>Purpose:</b>Element of a container variable.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: ContainerVariableElement.java 53 2010-07-04 12:31:52Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class ContainerVariableElement {
  private String name;
  private String type;
  private String value;
  private boolean required;

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

  /**
   * @return the required
   */
  public boolean isRequired() {
    return required;
  }

  /**
   * @param required
   *          the required to set
   */
  public void setRequired(boolean required) {
    this.required = required;
  }
}