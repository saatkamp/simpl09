package org.simpl.core;

import java.util.List;

import org.simpl.core.administration.AdministrationService;
import org.simpl.core.administration.AdministrationServiceImpl;
import org.simpl.core.datasource.DatasourceService;
import org.simpl.core.datasource.DatasourceServiceProvider;
import org.simpl.core.storage.StorageService;
import org.simpl.core.storage.StorageServiceImpl;

/**
 * <b>Purpose: Provides access to all services of the SIMPLCore.</b> <br>
 * <b>Description: This class implements the singleton pattern.</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLCore {
  private static SIMPLCore instance = null;
  private static SIMPLConfig config = new SIMPLConfig();
  private static AdministrationService administrationService = new AdministrationServiceImpl();
  private static DatasourceServiceProvider datasourceServiceProvider = new DatasourceServiceProvider();
  private static StorageService storageService = new StorageServiceImpl();

  /**
   * Private constructor for singleton.
   */
  private SIMPLCore() {
    // do nothing
  }

  /**
   * Returns the instance of SIMPLCore.
   * 
   * @return Instance of SIMPLCore.
   * @throws Exception
   */
  public static synchronized SIMPLCore getInstance() {
    if (SIMPLCore.instance == null) {
      SIMPLCore.instance = new SIMPLCore();
    }

    return SIMPLCore.instance;
  }

  /**
   * Returns the instance of the administration service
   * 
   * @return
   */
  public AdministrationService administrationService() {
    return administrationService;
  }

  /**
   * Returns the instance of a datasource service dependent on the given type and subtype.
   * 
   * @param dsType
   * @param dsSubtype
   * @return
   */
  public DatasourceService datasourceService(String dsType, String dsSubtype) {
    return datasourceServiceProvider.getInstance(dsType, dsSubtype);
  }

  /**
   * Returns the instance of the storage service.
   * 
   * @return
   */
  public StorageService storageService() {
    return storageService;
  }

  /**
   * Returns the SIMPL configuration.
   * 
   * @return
   */
  public SIMPLConfig config() {
    return config;
  }

  /**
   * Returns all datasource types supported by the simpl core.
   * 
   * @return
   */
  public List<String> getDatasourceTypes() {
    return datasourceServiceProvider.getDatasourceTypes();
  }

  /**
   * Returns all datasource subtypes of a given datasource type.
   * 
   * @param dsType
   * @return
   */
  public List<String> getDatasourceSubtypes(String dsType) {
    return datasourceServiceProvider.getDatasourceSubtypes(dsType);
  }

  /**
   * Returns all datasource languages of a given datasource subtype.
   * 
   * @param dsSubtype
   * @return
   */
  public List<String> getDatasourceLanguages(String dsSubtype) {
    return datasourceServiceProvider.getDatasourceLanguages(dsSubtype);
  }
}