package org.simpl.core.services.discovery;

import org.simpl.resource.management.client.DataSource;
import org.simpl.resource.management.client.LateBinding;

/**
 * <b>Purpose:</b> TODO: Defines the functions of the strategy service.<br>
 * <b>Description:</b>The strategy service is used for the late binding to find an
 * appropriate data source for data source service operations that are given incomplete
 * data source information and non-functional requirements as WS-Policy Assertions. In
 * order to find a data source it responds to the resource management and compares the
 * properties.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: StrategyService.java 1159 2010-04-20 18:19:11Z michael.schneidt@arcor.de
 *          $<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface DiscoveryService {
  /**
   * Finds a data source to the given late binding information.
   * 
   * @param dataSource
   * @return
   */
  public DataSource findDataSource(LateBinding lateBinding);
}