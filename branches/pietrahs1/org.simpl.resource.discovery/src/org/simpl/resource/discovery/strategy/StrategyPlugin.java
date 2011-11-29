package org.simpl.resource.discovery.strategy;

import org.simpl.resource.management.data.Strategy;

/**
 * <b>Purpose:</b>This abstract class is used to create strategy plug-ins for the Resource
 * Discovery.<br>
 * <b>Description:</b>A strategy plug-in implements the logic of one strategy defined in
 * {@link Strategy}.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: StrategyPlugin.java 1962 2011-10-25 10:32:46Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class StrategyPlugin implements IStrategy {
  Strategy strategy;

  /**
   * @return the strategy
   */
  public Strategy getStrategy() {
    return strategy;
  }

  /**
   * @param strategy the strategy to set
   */
  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }
}