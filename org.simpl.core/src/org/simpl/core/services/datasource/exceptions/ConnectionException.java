package org.simpl.core.services.datasource.exceptions;

/**
 * <b>Purpose:</b>Database connection exception.<br>
 * <b>Description: This exception is thrown whenever an error during a connection to a
 * data source occurs.</b><br>
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
public class ConnectionException extends Exception {

  public ConnectionException() {
    super();
  }

  public ConnectionException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public ConnectionException(String msg) {
    super(msg);
  }

  public ConnectionException(Throwable cause) {
    super(cause);
  }

}
