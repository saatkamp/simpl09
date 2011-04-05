package org.apache.ode.simpl.events.listener;

import java.util.HashMap;

import org.simpl.core.services.SIMPLCoreAdministrationService;
import org.simpl.resource.management.data.Authentication;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataFormat;
import org.simpl.resource.management.data.DataSource;

public class AuditingParameters {

  private static AuditingParameters auditingParameters = null;

  private HashMap<String, String> settings = new HashMap<String, String>();
  private HashMap<String, String> lastSettings = null;

  private DataSource dataSource = new DataSource();
  private boolean mode = false;
  private boolean ddMode = false;

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
    this.settings = SIMPLCoreAdministrationService.getInstance().getService()
        .loadSettings("AUDITING", "GENERAL", "lastSaved");
    this.dataSource = getDataSource();
  }

  public void setSettings(HashMap<String, String> settings) {
    // Buffer the last set settings
    this.lastSettings = this.settings;
    // Set the new settings
    this.settings = settings;
  }

  public DataSource getDataSource() {
    if (!this.settings.isEmpty() && !this.settings.equals(this.lastSettings)) {
      DataSource data = new DataSource();
      Connector connector = new Connector();
      DataFormat dataFormat = new DataFormat();
      Authentication authentication = new Authentication();

      data.setAddress(this.settings.get(AUDITING_DS_ADDRESS));
      data.setType(this.settings.get(DS_TYPE));
      data.setSubType(this.settings.get(DS_SUBTYPE));
      dataFormat.setName(this.settings.get(DS_FORMAT));

      connector.setConverterDataFormat(dataFormat);
      data.setConnector(connector);

      authentication.setUser(this.settings.get(DS_USER));
      authentication.setPassword(this.settings.get(DS_PASSWORD));
      data.setAuthentication(authentication);

      this.dataSource = data;

      if (dataSource.getSubType().equals("EmbeddedDerby")
          || dataSource.getSubType().equals("Derby")) {
        int_type = "BIGINT";
        varchar_type = "VARCHAR(1024)";
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
    if (!this.settings.isEmpty() && !this.settings.equals(this.lastSettings)) {
      // Check the auditing mode from the SIMPL Admin console and the DD
      if (settings.get(MODE).equals("active")) {
        // The DD auditing mode is decisive, if the auditing is activated over the admin
        // console
        if (this.ddMode) {
          this.mode = true;
        } else {
          this.mode = false;
        }
      } else {
        // If the auditing in the admin console is turned off, it must be off in every
        // case
        this.mode = false;
      }
    }
    // TODO: remove output
    System.out.println("AUDITING IS ON: " + this.mode);
    return mode;
  }

  /**
   * Sets the auditing mode from the deployment descriptor
   * 
   * @param mode
   *          to set
   */
  public void setMode(boolean mode) {
    this.ddMode = mode;
  }

  public String getInt_type() {
    return int_type;
  }

  public String getVarchar_type() {
    return varchar_type;
  }

  public String getDSFormat() {
    if (this.settings.isEmpty()) {
      return "RDB";
    } else {
      return this.settings.get(DS_FORMAT);
    }
  }
}
