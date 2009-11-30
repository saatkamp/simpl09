package org.simpl.core;

import org.simpl.core.administration.AdministrationServiceFactory;
import org.simpl.core.datasource.DatasourceServiceFactory;

/**
 * Provides access to all services of the SIMPLCore.
 * 
 * This class implements the singleton pattern.
 * 
 * @author schneimi
 * 
 * SVN $Id$
 */
public class SIMPLCore {
  private static SIMPLCore instance = null;
  public static AdministrationServiceFactory administrationService;
  public static DatasourceServiceFactory datasourceService;

  public static synchronized SIMPLCore getInstance() {
    if (SIMPLCore.instance == null) {
      SIMPLCore.instance = new SIMPLCore();
    }

    return SIMPLCore.instance;
  }
}