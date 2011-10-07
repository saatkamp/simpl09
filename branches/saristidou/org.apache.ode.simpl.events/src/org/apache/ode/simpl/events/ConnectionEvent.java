package org.apache.ode.simpl.events;

public class ConnectionEvent extends SimplEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 640012880022297916L;
	
	public TYPE getType() {
		return TYPE.scopeHandling;
	}

}
