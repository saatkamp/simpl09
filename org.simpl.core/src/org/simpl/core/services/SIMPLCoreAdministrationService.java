package org.simpl.core.services;

import org.simpl.core.administration.AdministrationServiceInterface;
import org.simpl.core.administration.AdministrationServiceImpl;

/**
 * <b>Purpose: Provides singleton access to the SIMPL Core administration service.</b> <br>
 * <b>Description: This class implements the singleton pattern.</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: SIMPLCore.java 1783 2011-03-28 09:51:39Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLCoreAdministrationService {
  private static SIMPLCoreAdministrationService instance = new SIMPLCoreAdministrationService();
  private static AdministrationServiceImpl service = new AdministrationServiceImpl();

  /**
   * Private constructor for singleton.
   */
  private SIMPLCoreAdministrationService() {
    // do nothing
  }

  /**
   * @return The instance of SIMPLCore.
   * @throws Exception
   */
  public static synchronized SIMPLCoreAdministrationService getInstance() {
    return SIMPLCoreAdministrationService.instance;
  }

  /**
   * @return The instance of the administration service.
   */
  public AdministrationServiceInterface getService() {
    return SIMPLCoreAdministrationService.service;
  }
}