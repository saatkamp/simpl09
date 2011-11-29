package org.simpl.resource.management.webinterface.utils;
/**
 * <b>Purpose:</b><br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 *
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class Check {
  public static boolean empty(String string) {
    boolean empty = (string == null) || string.equals("");
    
    return empty;
  }
}