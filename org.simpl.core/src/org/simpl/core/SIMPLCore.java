package org.simpl.core;

import org.simpl.core.services.administration.AdministrationService;
import org.simpl.core.services.administration.AdministrationServiceImpl;
import org.simpl.core.services.datasource.DataSourceServiceImpl;
import org.simpl.core.services.strategy.StrategyService;
import org.simpl.core.services.strategy.StrategyServiceImpl;

/**
 * <b>Purpose: Provides access to all services and info of the SIMPLCore.</b> <br>
 * <b>Description: This class implements the singleton pattern.</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLCore {
  private static SIMPLCore instance = new SIMPLCore();
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
    return SIMPLCore.instance;
  }

  /**
   * @return The instance of the administration service.
   */
  public AdministrationService administrationService() {
    return SIMPLCore.administrationService;
  }

  /**
   * @return The instance of the strategy service.
   */
  public StrategyService strategyService() {
    return SIMPLCore.strategyService;
  }

  /**
   * @return The instance of the data source service.
   */
  public DataSourceServiceImpl dataSourceService() {
    return SIMPLCore.dataSourceService;
  }
}