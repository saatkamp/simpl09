package org.simpl.core.services.administration;

import java.util.LinkedHashMap;

public interface AdministrationService {
  public boolean saveSettings(String schema, String table, String settingName,
      LinkedHashMap<String, String> settings);

  public LinkedHashMap<String, String> loadSettings(String schema, String table,
      String settingName);
}