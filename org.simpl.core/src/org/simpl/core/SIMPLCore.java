package org.simpl.core;

import org.simpl.core.services.administration.AdministrationService;
import org.simpl.core.services.administration.AdministrationServiceImpl;
import org.simpl.core.services.datasource.DataSourceServiceImpl;
import org.simpl.core.services.strategy.StrategyService;
import org.simpl.core.services.strategy.StrategyServiceImpl;

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
  private static AdministrationServiceImpl administrationService = new AdministrationServiceImpl();
  private static StrategyServiceImpl strategyService = new StrategyServiceImpl();
  private static DataSourceServiceImpl dataSourceService = new DataSourceServiceImpl();

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
   * @return The instance of the data source service.
   */
  public DataSourceServiceImpl dataSourceService() {
    return dataSourceService;
  }

  /**
   * @return The SIMPL configuration.
   */
  public SIMPLConfig getConfig() {
    return config;
  }
}