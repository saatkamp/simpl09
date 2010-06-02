package org.simpl.ode.evt;

public class ConnectionLost extends ConnectionEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2040860427848835580L;

	
	private String ConLost_reason;

	  public ConnectionLost() {
	  }

	  public ConnectionLost(String reason) {
	    this.ConLost_reason = reason;
	  }

	  public String getConLostReason() {
	    return this.ConLost_reason;
	  }

	  public void setConLost(String reason) {
	    this.ConLost_reason = reason;
	  }
	  
	  
		public TYPE getType() {
			return TYPE.scopeHandling;
		}

}
