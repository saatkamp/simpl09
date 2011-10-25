package org.simpl.resource.discovery.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.simpl.resource.management.ResourceManagement;
import org.simpl.resource.management.data.Strategy;
import org.simpl.resource.management.data.StrategyPluginList;

/**
 * <b>Purpose:</b>Provides access to the strategies that are loaded from strategy
 * plug-ins.<br>
 * <b>Description:</b>Strategy instances are retrieved by their concrete strategy
 * {@link #getInstance(Strategy)}.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DataFormatProvider.java 1788 2011-04-10 12:30:51Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class StrategyPluginProvider {
  /**
   * Maps the data formats to a list of supporting strategy plug-in instances.
   */
  private static HashMap<Strategy, StrategyPlugin> strategies = new HashMap<Strategy, StrategyPlugin>();

  /**
   * Initially load the strategy plug-ins.
   */
  static {
    StrategyPluginProvider.loadPlugins();
  }

  /**
   * Returns the strategy instance that supports the given strategy.
   * 
   * @param strategy
   * @return
   */
  public static StrategyPlugin getInstance(Strategy strategy) {
    return StrategyPluginProvider.strategies.get(strategy);
  }

  /**
   * Returns a list of all strategies that are supported by the loaded strategy
   * plug-ins.
   * 
   * @return
   */
  public static List<Strategy> getStrategies() {
    return new ArrayList<Strategy>(StrategyPluginProvider.strategies.keySet());
  }


  /**
   * Loads the data format plug-ins.
   */
  public static void loadPlugins() {
    ResourceManagement resourceManagement = new ResourceManagement();
    StrategyPluginList plugins = null;
    
    try {
      plugins = resourceManagement.getAllStrategyPlugins();
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    Iterator<org.simpl.resource.management.data.StrategyPlugin> pluginIterator = plugins.getStrategyPlugins().iterator();
    StrategyPlugin strategyInstance;
    Strategy strategy = null;

    while (pluginIterator.hasNext()) {
      try {
        strategyInstance = (StrategyPlugin) Class.forName(
            pluginIterator.next().getImplementation()).newInstance();
        strategy = strategyInstance.getStrategy();

        if (!StrategyPluginProvider.strategies.containsKey(strategy)) {
          StrategyPluginProvider.strategies.put(strategy, strategyInstance);
        }
      } catch (InstantiationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}