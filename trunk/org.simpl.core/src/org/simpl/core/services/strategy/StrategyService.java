package org.simpl.core.services.strategy;

import org.simpl.core.services.datasource.DataSourceService;

/**
 * <b>Purpose:</b> Defines the functions of the strategy service.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface StrategyService {
  public DataSourceService findDataSourceService(String wsPolicy,
      Strategy strategy);
}
