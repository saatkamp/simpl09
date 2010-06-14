package org.apache.ode.simpl.events.listener;

import java.util.HashMap;

import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSource;

public class AuditingParameters {

	private static AuditingParameters auditingParameters = null;

	private HashMap<String, String> settings = new HashMap<String, String>();
	private HashMap<String, String> lastSettings = null;

	private DataSource dataSource = new DataSource();

	private boolean mode = false;

	private final String MODE = "MODE";
	private final String AUDITING_DS_ADDRESS = "AUDITING_DS_ADDRESS";
	private final String DS_TYPE = "DS_TYPE";
	private final String DS_SUBTYPE = "DS_SUBTYPE";
	private final String DS_FORMAT = "DS_FORMAT";
	private final String DS_USER = "DS_USER";
	private final String DS_PASSWORD = "DS_PASSWORD";

	private String int_type = "";

	private String varchar_type = "";

	private AuditingParameters() {

	}

	public static AuditingParameters getInstance() {
		if (auditingParameters == null) {
			auditingParameters = new AuditingParameters();
			auditingParameters.loadSettings();
		}
		return auditingParameters;
	}

	private void loadSettings() {
		this.settings = SIMPLCore.getInstance().administrationService().loadSettings("AUDITING", "GENERAL", "lastSaved");
	}

	public void setSettings(HashMap<String, String> settings) {
		// Buffer the last set settings
		this.lastSettings = this.settings;
		// Set the new settings
		this.settings = settings;
	}

	public DataSource getDataSource() {
		if (!this.settings.isEmpty()
				&& !this.settings.equals(this.lastSettings)) {
			DataSource data = new DataSource();
			data.setAddress(this.settings.get(AUDITING_DS_ADDRESS));
			data.setType(this.settings.get(DS_TYPE));
			data.setSubType(this.settings.get(DS_SUBTYPE));
			data.setDataFormat(this.settings.get(DS_FORMAT));

			data.getAuthentication().setUser(this.settings.get(DS_USER));
			data.getAuthentication()
					.setPassword(this.settings.get(DS_PASSWORD));

			this.dataSource = data;

			if (dataSource.getSubType().equals("EmbeddedDerby")) {
				int_type = "BIGINT";
				varchar_type = "VARCHAR";
			} else if (dataSource.getSubType().equals("MySQL")) {
				int_type = "BIGINT(11)";
				varchar_type = "VARCHAR(1024)";
			} else if (dataSource.getSubType().equals("DB2")) {
				int_type = "BIGINT";
				varchar_type = "VARCHAR(1024)";
			}
		}

		return dataSource;
	}

	public boolean isMode() {
		if (!this.settings.isEmpty()
				&& !this.settings.equals(this.lastSettings)) {
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

	public String getInt_type() {
		return int_type;
	}

	public String getVarchar_type() {
		return varchar_type;
	}
	
	public String getDSFormat() {
		if (this.settings.isEmpty()){
			return "RDB";
		}else {
			return this.settings.get(DS_FORMAT);
		}
	}

}
