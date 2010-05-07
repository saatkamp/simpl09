package org.apache.ode.simpl.events;


public class DMStarted extends DMEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2670619597171375704L;
	
	public TYPE getType() {
		return TYPE.scopeHandling;
	}
}
