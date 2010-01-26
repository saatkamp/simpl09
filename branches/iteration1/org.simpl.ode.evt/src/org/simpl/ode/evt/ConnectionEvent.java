package org.simpl.ode.evt;

public class ConnectionEvent extends SimplEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 640012880022297916L;
	
	public TYPE getType() {
		return TYPE.scopeHandling;
	}

}
