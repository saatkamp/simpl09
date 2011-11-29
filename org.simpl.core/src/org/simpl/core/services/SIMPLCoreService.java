package org.simpl.core.services;

import org.simpl.core.SIMPLCoreInterface;

/**
 * <b>Purpose: Provides singleton access to the SIMPL Core connector service.</b> <br>
 * <b>Description: This class implements the singleton pattern.</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLCoreService {
  private static SIMPLCoreService instance = new SIMPLCoreService();
  private static SIMPLCoreInterface service = new SIMPLCoreInterface();

  /**
   * Private constructor for singleton.
   */
  private SIMPLCoreService() {
    // do nothing
  }

  /**
   * @return The instance of SIMPLCore.
   * @throws Exception
   */
  public static synchronized SIMPLCoreService getInstance() {
    return SIMPLCoreService.instance;
  }

  /**
   * @return The instance of the SIMPL Core interface.
   */
  public SIMPLCoreInterface getService() {
    return SIMPLCoreService.service;
  }
}