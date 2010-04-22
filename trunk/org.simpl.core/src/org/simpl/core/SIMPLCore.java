package org.simpl.core;

import java.util.List;

import org.simpl.core.services.administration.AdministrationService;
import org.simpl.core.services.administration.AdministrationServiceImpl;
import org.simpl.core.services.dataformat.DataFormatService;
import org.simpl.core.services.dataformat.DataFormatServiceProvider;
import org.simpl.core.services.datasource.DataSourceService;
import org.simpl.core.services.datasource.DataSourceServiceProvider;
import org.simpl.core.services.strategy.StrategyService;
import org.simpl.core.services.strategy.StrategyServiceImpl;

/**
 * <b>Purpose: Provides access to all services and info of the SIMPLCore.</b> <br>
 * <b>Description: This class implements the singleton pattern.</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: SIMPLCore.java 1156 2010-04-20 13:56:37Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLCore {
  private static SIMPLCore instance = null;
  private static SIMPLConfig config = new SIMPLConfig();
  private static AdministrationService administrationService = new AdministrationServiceImpl();
  private static StrategyService strategyService = new StrategyServiceImpl();
  private static DataSourceServiceProvider dataSourceServiceProvider = new DataSourceServiceProvider();
  private static DataFormatServiceProvider dataFormatServiceProvider = new DataFormatServiceProvider();

  /**
   * Private constructor for singleton.
   */
  private SIMPLCore() {
    // do nothing
  }

  /**
   * @return The instance of SIMPLCore.
   * @throws Exception
   */
  public static synchronized SIMPLCore getInstance() {
    if (SIMPLCore.instance == null) {
      SIMPLCore.instance = new SIMPLCore();
    }

    return SIMPLCore.instance;
  }

  /**
   * @return The instance of the administration service.
   */
  public AdministrationService administrationService() {
    return administrationService;
  }

  /**
   * @return The instance of the strategy service.
   */
  public StrategyService strategyService() {
    return strategyService;
  }

  /**
   * Finds a data source service that supports the given data source.
   * 
   * @param dsType
   * @param dsSubtype
   * @return The instance of a data source service.
   */
  public DataSourceService dataSourceService(String dsType, String dsSubType) {
    return dataSourceServiceProvider.getInstance(dsType, dsSubType);
  }

  /**
   * Finds a data format service that matches the given type.
   * 
   * @param dfType
   * @return The instance of a data format service.
   */
  public DataFormatService<Object> dataFormatService(String dfType) {
    return dataFormatServiceProvider.getInstance(dfType);
  }

  /**
   * @return The SIMPL configuration.
   */
  public SIMPLConfig config() {
    return config;
  }

  /**
   * Returns all data source types supported by the SIMPL Core.
   * 
   * @return A list of data source types.
   */
  public List<String> getDataSourceTypes() {
    return dataSourceServiceProvider.getTypes();
  }

  /**
   * Returns all data source subtypes of a given data source type.
   * 
   * @param dsType
   * @return A list of data source subtypes.
   */
  public List<String> getDataSourceSubtypes(String dsType) {
    return dataSourceServiceProvider.getSubtypes(dsType);
  }

  /**
   * Returns all data source languages of a given data source subtype.
   * 
   * @param dsSubtype
   * @return List of data source languages.
   */
  public List<String> getDataSourceLanguages(String dsSubtype) {
    return dataSourceServiceProvider.getLanguages(dsSubtype);
  }

  /**
   * Returns all data source types supported by the SIMPL Core.
   * 
   * @return List of data source types.
   */
  public List<String> getDataFormatTypes() {
    return dataFormatServiceProvider.getTypes();
  }
}