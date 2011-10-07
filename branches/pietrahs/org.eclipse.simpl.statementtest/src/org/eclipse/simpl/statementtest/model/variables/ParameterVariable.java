package org.eclipse.simpl.statementtest.model.variables;

import org.eclipse.bpel.model.Variable;

/**
 * <b>Purpose: Model of a parameter variable (simple type).</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: ParameterVariable.java 51 2010-06-29 18:21:35Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class ParameterVariable extends AbstractVariable {
  boolean quote = false;

  /**
   * The constructor.
   */
  public ParameterVariable(Variable variable) {
    super(variable);
    this.bpelVariable = variable;
  }

  /**
   * @return the quote
   */
  public boolean isQuote() {
    return quote;
  }

  /**
   * @param quote
   *          the quote to set
   */
  public void setQuote(boolean quote) {
    this.quote = quote;
  }

  /**
   * String interpretation of the variable.
   */
  @Override
  public String toString() {
    String toString = null;

    if (this.quote) {
      toString = "'" + value + "'";
    } else {
      toString = value.toString();
    }

    return toString;
  }
}