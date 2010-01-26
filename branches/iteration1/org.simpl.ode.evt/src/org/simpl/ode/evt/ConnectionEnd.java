package org.simpl.ode.evt;

public class ConnectionEnd extends ConnectionEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1790830493812927754L;

	
	public TYPE getType() {
		return TYPE.scopeHandling;
	}
	
}
