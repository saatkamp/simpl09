package org.simpl.core.services.strategy;

import org.simpl.core.services.datasource.DataSource;

/**
 * <b>Purpose:</b> Defines the functions of the strategy service.<br>
 * <b>Description:</b>The strategy service is used for the late binding to find an
 * appropriate data source for data source service operations that are given incomplete
 * data source information and non-functional requirements as WS-Policy Assertions. In
 * order to find a data source it responds to a resource framework and compares the
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
public interface StrategyService {
  /**
   * Finds a matching full specified data source to the given data source's functional
   * requirements and non-functional late binding information.
   * 
   * @param dataSource
   * @return
   */
  public DataSource findDataSource(DataSource dataSource);
}