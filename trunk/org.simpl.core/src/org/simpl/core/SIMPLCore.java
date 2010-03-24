package org.simpl.core;

import java.util.List;

import org.simpl.core.services.administration.AdministrationService;
import org.simpl.core.services.administration.AdministrationServiceImpl;
import org.simpl.core.services.dataformat.DataFormatServiceProvider;
import org.simpl.core.services.datasource.DataSourceService;
import org.simpl.core.services.datasource.DataSourceServiceProvider;
import org.simpl.core.services.storage.StorageService;
import org.simpl.core.services.storage.StorageServiceImpl;

/**
 * <b>Purpose: Provides access to all services and info of the SIMPLCore.</b> <br>
 * <b>Description: This class implements the singleton pattern.</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLCore {
  private static SIMPLCore instance = null;
  private static SIMPLConfig config = new SIMPLConfig();
  private static AdministrationService administrationService = new AdministrationServiceImpl();
  private static StorageService storageService = new StorageServiceImpl();
  private static DataSourceServiceProvider dataSourceServiceProvider = new DataSourceServiceProvider();
  private static DataFormatServiceProvider dataFormatServiceProvider = new DataFormatServiceProvider();

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
   * Returns the instance of a data source service dependent on the given type and subtype.
   * 
   * @param dsType
   * @param dsSubtype
   * @return
   */
  public DataSourceService dataSourceService(String dsType, String dsSubtype) {
    return dataSourceServiceProvider.getInstance(dsType, dsSubtype);
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
   * Returns all data source types supported by the simpl core.
   * 
   * @return
   */
  public List<String> getDataSourceTypes() {
    return dataSourceServiceProvider.getTypes();
  }

  /**
   * Returns all data source subtypes of a given data source type.
   * 
   * @param dsType
   * @return
   */
  public List<String> getDataSourceSubtypes(String dsType) {
    return dataSourceServiceProvider.getSubtypes(dsType);
  }

  /**
   * Returns all data source languages of a given data source subtype.
   * 
   * @param dsSubtype
   * @return
   */
  public List<String> getDataSourceLanguages(String dsSubtype) {
    return dataSourceServiceProvider.getLanguages(dsSubtype);
  }
  
  /**
   * Returns all data source types supported by the simpl core.
   * 
   * @return
   */
  public List<String> getDataFormatTypes() {
    return dataFormatServiceProvider.getTypes();
  }
}