package org.simpl.core.services.administration;

import java.util.LinkedHashMap;

/**
 * <b>Purpose:</b><br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hahnml
 * @version $Id: DatasourceServicePlugin.java 904 2010-02-22 11:23:43Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface AdministrationService {
  public boolean saveSettings(String schema, String table, String settingName,
      LinkedHashMap<String, String> settings);

  public LinkedHashMap<String, String> loadSettings(String schema, String table,
      String settingName);
}