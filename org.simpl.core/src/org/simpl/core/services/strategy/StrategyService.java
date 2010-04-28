package org.simpl.core.services.strategy;

import org.simpl.core.services.datasource.DataSource;

/**
 * <b>Purpose:</b> Defines the functions of the strategy service.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: StrategyService.java 1159 2010-04-20 18:19:11Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface StrategyService {
  /**
   * Gets an incomplete specified data source and finds a matching full
   * specified data source.
   * 
   * @param dataSource
   * @return
   */
  public DataSource findDataSource(DataSource dataSource);
}
