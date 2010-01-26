package org.simpl.ode.evt;

import org.apache.ode.bpel.evt.ScopeEvent;

public class SimplEvent extends ScopeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7020534029154006005L;

	public TYPE getType() {
		return TYPE.scopeHandling;
	}
}
