package org.apache.ode.simpl.events;

public class DMFailure extends DMEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5070833298842612149L;

	private String DM_activity_name;
	private String DM_activity_dsAddress;
	private String DM_activity_statement;
	private String Failure_reason;

	public DMFailure() {
	}

	public DMFailure(String reason) {
		this.Failure_reason = reason;
	}
	
	public DMFailure(String dMActivityName,
			String failureReason) {
		this.DM_activity_name = dMActivityName;
		this.Failure_reason = failureReason;
	}

	public DMFailure(String dMActivityName, String dMActivityDsAddress,
			String failureReason) {
		this.DM_activity_name = dMActivityName;
		this.DM_activity_dsAddress = dMActivityDsAddress;
		this.Failure_reason = failureReason;
	}

	public DMFailure(String dMActivityName, String dMActivityDsAddress,
			String dMActivityStatement, String failureReason) {
		super();
		DM_activity_name = dMActivityName;
		DM_activity_dsAddress = dMActivityDsAddress;
		DM_activity_statement = dMActivityStatement;
		Failure_reason = failureReason;
	}

	public TYPE getType() {
		return TYPE.scopeHandling;
	}

	public String getFailureReason() {
		return this.Failure_reason;
	}

	public void setFailureReason(String reason) {
		this.Failure_reason = reason;
	}

	public String getDM_activity_name() {
		return DM_activity_name;
	}

	public void setDM_activity_name(String dMActivityName) {
		DM_activity_name = dMActivityName;
	}

	public String getDM_activity_dsAddress() {
		return DM_activity_dsAddress;
	}

	public void setDM_activity_dsAddress(String dMActivityDsAddress) {
		DM_activity_dsAddress = dMActivityDsAddress;
	}

	public String getDM_activity_statement() {
		return DM_activity_statement;
	}

	public void setDM_activity_statement(String dMActivityStatement) {
		DM_activity_statement = dMActivityStatement;
	}

}
