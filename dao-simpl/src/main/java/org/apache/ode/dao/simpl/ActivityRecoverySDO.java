package org.apache.ode.dao.simpl;

import java.sql.Date;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

public class ActivityRecoverySDO {

	DataObject activityID;
	DataObject channel;
	DataObject reason;
	DataObject dateTime;
	DataObject details;
	DataObject actions;
	DataObject retries;
	
	public ActivityRecoverySDO () {
		activityID = DataFactory.INSTANCE.create("http://example.com/IPO", "actvityID");
		channel = DataFactory.INSTANCE.create("http://example.com/IPO", "channel");
		reason = DataFactory.INSTANCE.create("http://example.com/IPO", "reason");
		dateTime = DataFactory.INSTANCE.create("http://example.com/IPO", "dateTime");
		details = DataFactory.INSTANCE.create("http://example.com/IPO", "details");
		actions = DataFactory.INSTANCE.create("http://example.com/IPO", "acitons");
		retries = DataFactory.INSTANCE.create("http://example.com/IPO", "retries");
				
	}
	
	public void sendActivityId(long id, long activityId) {
		this.activityID.set("id", id);
		this.activityID.set("activityID", activityId);		
	}
	
	public void sendChannel (long id, String channel) {
		this.channel.set("id", id);
		this.channel.set("channel", channel);
	}
	
	public void sendReason (long id, String reason) {
		this.reason.set("id", id);
		this.reason.set("reason",reason);
	}
	
	public void sendDateTime (long id, java.util.Date dateTime2) {
		this.dateTime.set("id", id);
		this.dateTime.set("dateTime", dateTime2);
	}
	
	public void sendDetails (long id, String details) {
		this.details.set("id", id);
		this.details.set("details", details);
		
	}
	
	public void sendAction (long id, String actions) {
		this.actions.set("id", id);
		this.actions.set("actions", actions);
	}
	
	public void sendRetries (long id, int retries) {
		this.retries.set("id", id);
		this.retries.set("retries", retries);
	}

}
