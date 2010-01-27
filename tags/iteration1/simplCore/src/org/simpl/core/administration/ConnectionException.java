package org.simpl.core.administration;

/**
 * This exception is thrown whenever an error during 
 * a connection to the embedded Apache Derby database occurs.
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 *
 */
@SuppressWarnings("serial")
public class ConnectionException 
extends Exception {

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

