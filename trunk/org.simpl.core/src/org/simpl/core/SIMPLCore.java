package org.simpl.core;

import org.simpl.core.administration.AdministrationService;
import org.simpl.core.administration.AdministrationServiceImpl;
import org.simpl.core.datasource.DatasourceServiceProvider;
import org.simpl.core.storage.StorageService;
import org.simpl.core.storage.StorageServiceImpl;

/**
 * Provides access to all services of the SIMPLCore.
 * 
 * This class implements the singleton pattern.
 * 
 * @author schneimi
 * 
 *         SVN $Id: SIMPLCore.java 451 2009-11-30 14:37:55Z michael.schneidt@arcor.de $
 */
public class SIMPLCore {
  private static SIMPLCore instance = null;
  public static AdministrationService administrationService = new AdministrationServiceImpl();
  public static DatasourceServiceProvider datasourceServiceProvider = new DatasourceServiceProvider();
  public static StorageService storageService = new StorageServiceImpl();

  /**
   * Returns the instance of SIMPLCore.
   * 
   * @return Instance of SIMPLCore.
   */
  public static synchronized SIMPLCore getInstance() {
    if (SIMPLCore.instance == null) {
      SIMPLCore.instance = new SIMPLCore();
    }

    return SIMPLCore.instance;
  }
}