package org.simpl.core.administration;

/**
 * This exception is thrown whenever an error during saving or reading 
 * occurs from which can not be recovered.
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 *
 */
@SuppressWarnings("serial")
public class DataException 
extends Exception {

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

