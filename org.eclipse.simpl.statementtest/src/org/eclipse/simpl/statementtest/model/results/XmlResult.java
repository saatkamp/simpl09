package org.eclipse.simpl.statementtest.model.results;


/**
 * <b>Purpose:</b>Result that holds the plain XML result.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class XmlResult extends Result {
  String text = null;

  /**
   * Initializes the table result.
   * 
   * @param table
   * @param columns
   * @param primaryKeys
   */
  public XmlResult(String text) {
    super(text);
  }
}