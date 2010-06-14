package org.apache.ode.simpl.events.listener;

import java.util.HashMap;

import org.simpl.core.services.datasource.DataSource;

public class AuditingParameters {

	private static AuditingParameters auditingParameters = null;

	private HashMap<String, String> settings = new HashMap<String, String>();
	private HashMap<String, String> lastSettings = new HashMap<String, String>();

	private DataSource dataSource = new DataSource();

	private boolean mode = false;

	private final String MODE = "MODE";
	private final String AUDITING_DS_ADDRESS = "AUDITING_DS_ADDRESS";
	private final String DS_TYPE = "DS_TYPE";
	private final String DS_SUBTYPE = "DS_SUBTYPE";
	private final String DS_FORMAT = "DS_FORMAT";
	private final String DS_USER = "DS_USER";
	private final String DS_PASSWORD = "DS_PASSWORD";

	private AuditingParameters() {

	}

	public static AuditingParameters getInstance() {
		if (auditingParameters == null) {
			auditingParameters = new AuditingParameters();
		}
		return auditingParameters;
	}

	public void setSettings(HashMap<String, String> settings) {
		//Buffer the last set settings
		this.lastSettings = this.settings;
		//Set the new settings
		this.settings = settings;
	}

	public DataSource getDataSource() {
		if (!this.settings.isEmpty() && !this.settings.equals(this.lastSettings)) {
			DataSource data = new DataSource();
			data.setAddress(this.settings.get(AUDITING_DS_ADDRESS));
			data.setType(this.settings.get(DS_TYPE));
			data.setSubType(this.settings.get(DS_SUBTYPE));
			data.setDataFormat(this.settings.get(DS_FORMAT));

			data.getAuthentication().setUser(this.settings.get(DS_USER));
			data.getAuthentication()
					.setPassword(this.settings.get(DS_PASSWORD));

			this.dataSource = data;
		}

		return dataSource;
	}

	public boolean isMode() {
		if (!this.settings.isEmpty() && !this.settings.equals(this.lastSettings)){
			if (settings.get(MODE).contains("active")) {
				this.mode = true;
			} else {
				this.mode = false;
			}
		}
		return mode;
	}

	public void setMode(boolean mode) {
		this.mode = mode;
	}

}
