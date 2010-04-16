package org.simpl.rrs.dsadapter.exceptions;

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
