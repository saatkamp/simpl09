package org.simpl.ode.evt;

public class DMFailure extends DMEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5070833298842612149L;
	
	  private String Failure_reason;

	  public DMFailure() {
	  }

	  public DMFailure(String reason) {
	    this.Failure_reason = reason;
	  }

	  public String getFailureReason() {
	    return this.Failure_reason;
	  }

	  public void setFailureReason(String reason) {
	    this.Failure_reason = reason;
	  }
	  
	  
		public TYPE getType() {
			return TYPE.scopeHandling;
		}

}
