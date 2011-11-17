package org.apache.ode.simpl.events;

public class ConnectionStarted extends ConnectionEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7681626737760100928L;

	
	public TYPE getType() {
		return TYPE.scopeHandling;
	}
	
}
