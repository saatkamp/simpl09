package org.simpl.core.exceptions;

/**
 * <b>Purpose:</b>Database data exception.<br>
 * <b>Description: This exception is thrown whenever an error during processing a
 * statement or processing its referenced data occurs from which can not be recovered.</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author hahnml<br>
 * @version $Id: UserPasswordAuthentication.java 1183 2010-04-22 20:05:13Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@SuppressWarnings("serial")
public class DataException extends Exception {

  public DataException() {
    super();
  }

  public DataException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public DataException(String msg) {
    super(msg);
  }

  public DataException(Throwable cause) {
    super(cause);
  }

}
