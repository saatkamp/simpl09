package org.simpl.core;

import org.simpl.core.services.administration.AdministrationService;
import org.simpl.core.services.administration.AdministrationServiceImpl;
import org.simpl.core.services.datasource.DataSourceServiceImpl;
import org.simpl.core.services.datasource.DataSourceServiceInterface;
import org.simpl.core.services.discovery.DiscoveryService;
import org.simpl.core.services.discovery.DiscoveryServiceImpl;

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
  private static DiscoveryServiceImpl strategyService = new DiscoveryServiceImpl();
  private static DataSourceServiceImpl dataSourceService = new DataSourceServiceImpl();
  private static DataSourceServiceInterface dataSourceServiceInterface = new DataSourceServiceInterface();

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
  public DiscoveryService strategyService() {
    return SIMPLCore.strategyService;
  }

  /**
   * @return The instance of the data source service interface.
   */
  public DataSourceServiceInterface dataSourceServiceInterface() {
    return SIMPLCore.dataSourceServiceInterface;
  }

  /**
   * @return The instance of the data source service implementation.
   */
  public DataSourceServiceImpl dataSourceService() {
    return SIMPLCore.dataSourceService;
  }
}