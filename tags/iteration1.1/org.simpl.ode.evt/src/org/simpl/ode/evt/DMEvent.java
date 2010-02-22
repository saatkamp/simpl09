package org.simpl.ode.evt;

public abstract class DMEvent extends SimplEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5262057760751677589L;

	public TYPE getType() {
		return TYPE.scopeHandling;
		
	}
	
}
